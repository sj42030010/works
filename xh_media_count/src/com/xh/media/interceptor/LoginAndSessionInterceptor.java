package com.xh.media.interceptor;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import wap.util.Util;
//登录及session鉴定
public class LoginAndSessionInterceptor extends HandlerInterceptorAdapter {
	private String loginUrl;
	private List<String> excludedUrls;
	public String getLoginUrl() {
		return loginUrl;
	}
	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}
	public List<String> getExcludedUrls() {
		return excludedUrls;
	}
	public void setExcludedUrls(List<String> excludedUrls) {
		this.excludedUrls = excludedUrls;
	}
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
    	try{
//	        long beginTime = System.currentTimeMillis();//开始时间
//	        startTimeThreadLocal.set(beginTime);//线程绑定变量（该数据只有当前请求的线程可见）
    		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+request.getSession().getAttribute("username"));
//    		System.out.println("request.getServletPath()=="+request.getServletPath());
//    		System.out.println("getLoginUrl=="+this.getLoginUrl());
    		String requestUri = request.getRequestURI();
//    		System.out.println("requestUri=="+requestUri);
    		for (String url : excludedUrls) {
//    			System.out.println("excludedUrls=="+url);
    			if (requestUri.indexOf(url)>-1) {
//    				System.out.println(url);
    				return true;
    			}
    		}
	        if(request.getServletPath().startsWith(this.getLoginUrl())) {
	            return true;
	        }
	        //2、TODO 比如退出、首页等页面无需登录，即此处要放行 允许游客的请求
	        //3、如果用户已经登录 放行  
	        if(request.getSession().getAttribute("username") != null) {
//	        	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	            //更好的实现方式的使用cookie
	        	//此处进行访问权限判定
	            return true;
	        }else{
	        
		        //4、非法请求 即这些请求需要登录后才能访问
		        //重定向到登录页面
//		        System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbb=="+request.getContextPath() + "login.jsp");
//		        System.out.println("4444444444444444444444444444=="+"login.jsp");
//		        System.out.println("5555555555555555555555555555=="+request.getServletPath());
//		        response.sendRedirect("www.baidu.com");
		        String msg = "alert('session失效需要重新登录!');location.href='login.jsp';";
//		        request.getRequestDispatcher(this.getLoginUrl()+".jsp").forward(request, response);
		        response.setContentType("text/html; charset=UTF-8"); 
		        response.setHeader("Cache-Control", "no-cache"); 
		        PrintWriter pw = response.getWriter(); 
		        pw.write("<SCRIPT TYPE='text/javascript'>" + msg + "</SCRIPT>"); 
		        pw.close(); 
//		        Util.writeJsToFrontPage(response, msg);
		        return false;
	        }
        }catch(Exception e){
        	e.printStackTrace();
        	return false;
        }
    }
}
