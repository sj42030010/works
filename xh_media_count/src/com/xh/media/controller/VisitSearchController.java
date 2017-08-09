package com.xh.media.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.google.gson.Gson;
import com.xh.media.model.LogXhMediaVisit;
import com.xh.media.service.LogXhMediaVisitService;

public class VisitSearchController extends AbstractController{
	private ApplicationContext context;
	private LogXhMediaVisitService logXhMediaVisitService;
	private Gson gson;
	
	public VisitSearchController() {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		logXhMediaVisitService=(LogXhMediaVisitService) context.getBean("logXhMediaVisitService");
	    gson=new Gson();
	}
	
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0,
			HttpServletResponse arg1)  {
		try{
			List<LogXhMediaVisit> limitList = logXhMediaVisitService.selectByLimit(4);
			System.out.println("搜索結果》》》》"+limitList.size());
			Map map = new HashMap();
			map.put("total", limitList.size());
			map.put("rows", limitList);
			String resultStr = gson.toJson(map);
			arg1.setCharacterEncoding("UTF-8");
			arg1.getWriter().write(resultStr);
			arg1.getWriter().flush();
			arg1.getWriter().close();
			}catch(Exception e){
				e.printStackTrace();
			}
			return null;
	}

}
