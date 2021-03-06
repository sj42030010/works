package com.xh.media.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.xh.media.model.SysGroupOrigin;
import com.xh.media.model.SysGroups;
import com.xh.media.service.IndexXhMediaOriginService;

@Controller
public class SiteCodeController {
	private IndexXhMediaOriginService indexXhMediaOriginService;
	private ApplicationContext context;
	private Gson gson;
	public SiteCodeController() {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		indexXhMediaOriginService = (IndexXhMediaOriginService)context.getBean("indexXhMediaOriginService");
		gson = new Gson();
	}
	
	@RequestMapping("/siteCode")
	public void getSiteCode(HttpServletRequest request,HttpServletResponse response)throws Exception{
		// TODO Auto-generated method stub
				List<SysGroups> originList = (List<SysGroups>)request.getSession().getAttribute("groups");
				String resultStr = gson.toJson(originList);
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(resultStr);
				response.getWriter().flush();
				response.getWriter().close();
	}
	
	@RequestMapping("/originList")
	public void geOriginList(HttpServletRequest request,HttpServletResponse response)throws Exception{
		// TODO Auto-generated method stub
				List<SysGroupOrigin> originList = (List<SysGroupOrigin>)request.getSession().getAttribute("origins");
				String resultStr = gson.toJson(originList);
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(resultStr);
				response.getWriter().flush();
				response.getWriter().close();
	}

}
