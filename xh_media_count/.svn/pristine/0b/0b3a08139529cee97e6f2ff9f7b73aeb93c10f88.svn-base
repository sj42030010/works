package com.xh.media.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.xh.media.model.SysUseLog;
import com.xh.media.service.CountByHourService;
import com.xh.media.service.SysUserLogService;

@Controller
public class SysUserLogController {
	private ApplicationContext applicationContext;
	private SysUserLogService sysUserLogService;
	private Gson gson;
	public SysUserLogController() {
		applicationContext= new ClassPathXmlApplicationContext("applicationContext.xml");
		sysUserLogService=(SysUserLogService) applicationContext.getBean("sysUserLogService");
		gson=new Gson();
	}
	
	private String startHMS = " 00:00:00";
	private String endHMS = " 23:59:59";
	
	@RequestMapping("/loadData")
	public void getAllData(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		List<SysUseLog> selectAllData = sysUserLogService.selectAllData();
		Map map=new HashMap();
		map.put("total", selectAllData.size());
		map.put("rows", selectAllData);
		String resultStr = gson.toJson(map);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(resultStr);
		response.getWriter().flush();
		response.getWriter().close();
	}
    @RequestMapping("/searchUserLog")
	public void searchUserLog(HttpServletRequest request,HttpServletResponse response) throws IOException{
		int siteCode=-1;
		if (request.getParameter("siteCode")!=null&&!"".equals(request.getParameter("siteCode"))) {
			siteCode=Integer.parseInt(request.getParameter("siteCode"));
		}
    	String account="";
    	if (request.getParameter("account")!=null) {
    		account=request.getParameter("account");
		}
		String startTime = "";
		if(request.getParameter("startTime")!=null){
			startTime = request.getParameter("startTime")+startHMS;
			System.out.println("startTime11111"+startTime);
		}
		String endTime = "";
		if(request.getParameter("endTime")!=null){
			endTime = request.getParameter("endTime")+endHMS;
			System.out.println("endTime11111"+endTime);
		}
			
		if(startHMS.equals(startTime)){
			startTime = "1900-01-01"+startHMS;
			System.out.println("startTime22222"+startTime);
		}	
		if(endHMS.equals(endTime)){
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			String nowTime = ts+"";
			endTime = nowTime.substring(0,19);
			System.out.println("endTime22222"+endTime);
		}
		SysUseLog sysUseLog=new SysUseLog();
		if (siteCode!=-1) {
			sysUseLog.setSiteCode(siteCode);
		}
		if (!"".equals(account)) {
			sysUseLog.setUserAccount(account);
		}
		sysUseLog.setStartTime(startTime);
		sysUseLog.setEndTime(endTime);
		
		List<SysUseLog> searchUserLog = sysUserLogService.searchUserLog(sysUseLog);
		Map map=new HashMap();
		map.put("total", searchUserLog.size());
		map.put("rows", searchUserLog);
		String resultStr = gson.toJson(map);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(resultStr);
		response.getWriter().flush();
		response.getWriter().close();
		
	}
}
