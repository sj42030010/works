package com.xh.media.interceptor;

import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.xh.media.model.SysUseLog;
import com.xh.media.model.SysUsers;
import com.xh.media.service.IndexXhMediaChannelService;
import com.xh.media.service.SysGroupsService;
import com.xh.media.service.SysUserLogService;
//请求、响应日志记录
public class LogRecordHandlerInterceptor extends HandlerInterceptorAdapter {
	private NamedThreadLocal<Long>  startTimeThreadLocal = new NamedThreadLocal<Long>("StopWatch-StartTime");
	private ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	private SysUserLogService sysUseLogService = (SysUserLogService)context.getBean("sysUserLogService"); 
	private SysGroupsService sysGroupsService = (SysGroupsService)context.getBean("sysGroupsService");
	private String requestPar;

	public String getRequestPar() {
		return requestPar;
	}

	public void setRequestPar(String requestPar) {
		this.requestPar = requestPar;
	}

	/*
	 * preHandle：预处理回调方法，实现处理器的预处理（如登录检查），第三个参数为响应的处理器（如我们上一章的Controller实现）；
	 * 返回值：
	 * 		true表示继续流程（如调用下一个拦截器或处理器）；
	 * 		false表示流程中断（如登录检查失败），不会继续调用其他的拦截器或处理器，此时我们需要通过response来产生响应；
    */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
    	HashMap map = new HashMap();
    	Enumeration enumeration = request.getParameterNames();
    	StringBuffer req = new StringBuffer("");
    	while(enumeration.hasMoreElements()){
    		String name=(String)enumeration.nextElement();
    		String values[] = request.getParameterValues(name);
    		req.append(name+":"+values[0]+";");
    	}
    	this.setRequestPar(req.toString());
    	return true;
    }
    
    /*
     * postHandle：后处理回调方法，实现处理器的后处理（但在渲染视图之前），此时我们可以通过modelAndView（模型和视图对象）
     * 对模型数据进行处理或对视图进行处理，modelAndView也可能为null。
     */
    //将请求数据计入日志
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView){
        long beginTime = System.currentTimeMillis();//开始时间
        startTimeThreadLocal.set(beginTime);//线程绑定变量（该数据只有当前请求的线程可见）
    	String requestURL = "";
    	requestURL = request.getServletPath();
    	String viewName = "";
    	if(modelAndView != null){
    		viewName = modelAndView.getViewName();
    	}
    	SysUsers su = new SysUsers();
//		String userAccount = "";
		int userId = 0;
		int siteCode = 0;
		String siteName = "";
		if(request.getSession().getAttribute("userInfo") != null){
			su = (SysUsers)request.getSession().getAttribute("userInfo");
			userId = su.getId();
			siteCode = Integer.parseInt(su.getDept());
			siteName = sysGroupsService.selectByPrimaryKey(siteCode).getName();
		}
    	//记录用户登录情况
    	if("/login".equals(requestURL)&&"index".equals(viewName)){
    		SysUseLog sul = new SysUseLog();
    		sul.setOperationType((short)1);
    		sul.setOperationName("登录");
    		sul.setOperationContent("");
    		sul.setSiteCode(siteCode);
    		sul.setSiteName(siteName);
    		sul.setModelName("首页");
    		sul.setUserId(userId);
    		sysUseLogService.insertSelective(sul);
    	}
    	if(requestURL.startsWith("/sysuser")){
    		String action = requestURL.replace("/sysuser", "");
    		SysUseLog sul = new SysUseLog();
    		sul.setSiteCode(siteCode);
    		sul.setSiteName(siteName);
    		sul.setModelName("用户管理");
    		sul.setUserId(userId);
    		if("list".equals(action)){
    			sul.setOperationType((short)2);
        		sul.setOperationName("查看");
        		sul.setOperationContent("");
    		}else if("insert".equals(action)){
    			sul.setOperationType((short)3);
        		sul.setOperationName("添加");
        		sul.setOperationContent(this.getRequestPar());
    		}else if("update".equals(action)){
    			sul.setOperationType((short)4);
        		sul.setOperationName("修改");
        		sul.setOperationContent(this.getRequestPar());
    		}else if("delete".equals(action)){
    			sul.setOperationType((short)5);
        		sul.setOperationName("删除");
        		sul.setOperationContent(this.getRequestPar());
    		}
    		sysUseLogService.insertSelective(sul);
    	}
//    	try{
//    		System.out.println("response==="+response.getWriter().toString());
//    		response.getWriter()..println()
//    	}catch(Exception e){
//    		e.printStackTrace();
//    	}
    	System.out.println("request url====="+requestURL);
    	System.out.println("ModelAndView ====="+viewName+"--");
    }
    
    /*
     * afterCompletion：整个请求处理完毕回调方法，即在视图渲染完毕时回调，如性能监控中我们可以在此记录结束时间
     * 并输出消耗时间，还可以进行一些资源清理，类似于try-catch-finally中的finally，但仅调用处理器执行链中
     * preHandle返回true的拦截器的afterCompletion。
     */
    //将请求数据计入日志
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex){
        long endTime = System.currentTimeMillis();//2、结束时间
        long beginTime = startTimeThreadLocal.get();//得到线程绑定的局部变量（开始时间）
        long consumeTime = endTime - beginTime;//3、消耗的时间
        if(consumeTime > 500) {//此处认为处理时间超过500毫秒的请求为慢请求
            //TODO 记录到日志文件
            System.out.println(String.format("%s consume %d millis", request.getRequestURI(), consumeTime));
        }
        String requestURL = request.getServletPath();
//        SysUsers su = (SysUsers)request.getSession().getAttribute("userInfo");
//		String userAccount = su.getUserAccount();
//		int siteCode = Integer.parseInt(su.getDept());
//		String siteName = sysGroupsService.selectByPrimaryKey(siteCode).getName();
//		
//        if(requestURL.indexOf("index")>-1){
        	System.out.println("after request url====="+requestURL);
//        }
    }
}
