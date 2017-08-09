package com.xh.media.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.google.gson.Gson;
import com.xh.media.model.IndexXhMediaOrigin;
import com.xh.media.model.SysGroups;
import com.xh.media.service.IndexXhMediaOriginService;
@Controller
public class OriginController extends AbstractController {
	private IndexXhMediaOriginService indexXhMediaOriginService;
	private ApplicationContext context;
	private Gson gson;
	
	public OriginController(){
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		indexXhMediaOriginService = (IndexXhMediaOriginService)context.getBean("indexXhMediaOriginService");
		gson = new Gson();
	}
	@RequestMapping("/origin")
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		List<SysGroups> originList = (List<SysGroups>)arg0.getSession().getAttribute("groups");
		String resultStr = gson.toJson(originList);
		arg1.setCharacterEncoding("UTF-8");
		arg1.getWriter().write(resultStr);
		arg1.getWriter().flush();
		arg1.getWriter().close();
		return null;
	}
	
	
	@RequestMapping("/getOrigin")
	public void getOrigin(HttpServletRequest request,HttpServletResponse response)throws Exception{
		
		String siteCode = "";
		if(request.getParameter("siteCode") != null && !"".equals(request.getParameter("siteCode")))
		{
			siteCode = request.getParameter("siteCode");
		}
		List<IndexXhMediaOrigin> originList = new ArrayList<IndexXhMediaOrigin>();
		if(!"".equals(siteCode))
		{
			originList = indexXhMediaOriginService.getAllOriginBySiteCode(siteCode);
		}
		else
		{
			originList = indexXhMediaOriginService.getAllOrigin();
		}	
		String resultStr1 = gson.toJson(originList);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(resultStr1);
		response.getWriter().flush();
		response.getWriter().close();
	}
	
	@RequestMapping("/getAllOrigin")
	public void getAllOrigin(HttpServletRequest request,HttpServletResponse response)throws Exception{
		
		List<IndexXhMediaOrigin> allOrigin = indexXhMediaOriginService.getAllOrigin();
		
		String resultStr1 = gson.toJson(allOrigin);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(resultStr1);
		response.getWriter().flush();
		response.getWriter().close();
		
	}
	

}
