package com.xh.media.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.xh.media.model.LogDayXhMediaSiteCount;
import com.xh.media.model.LogXhMediaSiteCount;
import com.xh.media.model.SysGroupOrigin;
import com.xh.media.model.SysGroups;
import com.xh.media.service.LogDayXhMediaSiteCountService;
import com.xh.media.service.LogXhMediaSiteCountService;
import com.xh.media.service.SysGroupOriginService;
import com.xh.media.service.SysGroupsService;

@Controller
public class SiteDataController {
	private Gson gson;
	private ApplicationContext context;
	private LogXhMediaSiteCountService logXhMediaSiteCountService;
	private LogDayXhMediaSiteCountService logDayXhMediaSiteCountService;
	private SysGroupsService sysGroupsService;
	private SysGroupOriginService sysGroupOriginService;
	public SiteDataController() {
		 gson=new Gson();
	     context=new ClassPathXmlApplicationContext("applicationContext.xml");
	     logXhMediaSiteCountService=(LogXhMediaSiteCountService) context.getBean("logXhMediaSiteCountService");
	     logDayXhMediaSiteCountService=(LogDayXhMediaSiteCountService) context.getBean("logDayXhMediaSiteCountService");
	     sysGroupsService = (SysGroupsService)context.getBean("sysGroupsService");
	     sysGroupOriginService = (SysGroupOriginService)context.getBean("sysGroupOriginService");
	}
	
	
	@RequestMapping("/getSiteData")
	public void getSiteData(HttpServletRequest request,HttpServletResponse response) throws IOException{
		List<LogDayXhMediaSiteCount> siteDataList=new ArrayList<LogDayXhMediaSiteCount>();
		LogDayXhMediaSiteCount ldxmsc = new LogDayXhMediaSiteCount();
		List<SysGroups> groupList = (List<SysGroups>) request.getSession().getAttribute("groups");
		List sitecodeList = new ArrayList();
		for(SysGroups sg:groupList)
		{
			sitecodeList.add(sg.getId());
		}
		String siteCode = "";
		if (request.getParameter("siteCode")!=null && !"".equals(request.getParameter("siteCode"))) {
			siteCode = request.getParameter("siteCode");
			sitecodeList = new ArrayList();
			sitecodeList.add(siteCode);
		}
//		ldxmac.setSitcodList(sitecodeList);
		ldxmsc.setSitcodList(sitecodeList);
		String sort = "";
		String order = "";
		if(request.getParameter("sort")!=null){
			sort = request.getParameter("sort");
		}else{
			sort = "start_pv";
		}
		if(request.getParameter("order")!=null){
			order = request.getParameter("order");
		}else{
			order = "desc";
		}
		String[] soStr = sort.split(",");
		String[] orStr = order.split(",");
		List orderList=new ArrayList();
		for(int i=0;i<soStr.length;i++){
			if("startPv".equals(soStr[i])){
				orderList.add("start_pv "+orStr[i]);
			}else if("originName".equals(soStr[i])){
				orderList.add("origin_id "+orStr[i]);
			}else if("siteName".equals(soStr[i])){
				orderList.add("site_code "+orStr[i]);
			}else if("startUv".equals(soStr[i])){
				orderList.add("start_uv "+orStr[i]);
			}else if("newUser".equals(soStr[i])){
				orderList.add("new_user "+orStr[i]);
			}else if("commentNumber".equals(soStr[i])){
				orderList.add("comment_number "+orStr[i]);
			}else if("collectionNumber".equals(soStr[i])){
				orderList.add("collection_number "+orStr[i]);
			}else if("shareNumber".equals(soStr[i])){
				orderList.add("share_number "+orStr[i]);
			}else if("nonLocal".equals(soStr[i])){
				orderList.add("non_local "+orStr[i]);
			}else{
				orderList.add(soStr[i]+" "+orStr[i]);
			}
		}
		ldxmsc.setOrderList(orderList);
		
		String startTime = "1900-01-01";
		String endTime = "";
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		String nowTime = String.valueOf(ts);
		endTime = nowTime.substring(0, 10);
		if (request.getParameter("startTime") != null
				&& !"".equals(request.getParameter("startTime"))) {
			startTime = request.getParameter("startTime");
		}
		if (request.getParameter("endTime") != null
				&& !"".equals(request.getParameter("endTime"))) {
			endTime = request.getParameter("endTime");
		}
		
		ldxmsc.setStartTime(startTime);
		ldxmsc.setEndTime(endTime);
		siteDataList = logDayXhMediaSiteCountService.getSiteDataNew(ldxmsc);
		if (siteDataList.size()>0) {
			for (LogDayXhMediaSiteCount logDayXhMediaSiteCount : siteDataList) {
				SysGroups sg = sysGroupsService.selectByPrimaryKey(Integer.parseInt(logDayXhMediaSiteCount.getSiteCode()));
				logDayXhMediaSiteCount.setSiteName(sg.getName());
//				logDayXhMediaAppCount.setNonLocal(logDayXhMediaAppCount.getLocal()+logDayXhMediaAppCount.getNonLocal());
			}
			Map map=new HashMap();
			map.put("rows", siteDataList);
			map.put("total", siteDataList.size());
			String result = gson.toJson(map);
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(result);
			response.getWriter().close();
		}
  }
	
	@RequestMapping("/getDaySiteDataDetail")
	public void getDaySiteDetail(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// List<LogXhMediaSiteCount> appDetailList = new
		// ArrayList<LogXhMediaAppCount>();
		List<LogXhMediaSiteCount> siteDetailList = new ArrayList<LogXhMediaSiteCount>();
		LogXhMediaSiteCount siteDetail = new LogXhMediaSiteCount();
		String siteCode = request.getParameter("siteCode");
		String date = request.getParameter("date");
//		String startTime = "1900-01-01";
//		String endTime = "";
//		Timestamp ts = new Timestamp(System.currentTimeMillis());
//		String nowTime = String.valueOf(ts);
//		endTime = nowTime.substring(0, 10);
//		if (request.getParameter("startTime") != null
//				&& !"".equals(request.getParameter("startTime"))) {
//			startTime = request.getParameter("startTime");
//		}
//		if (request.getParameter("endTime") != null
//				&& !"".equals(request.getParameter("endTime"))) {
//			endTime = request.getParameter("endTime");
//		}

		siteDetail.setSiteCode(siteCode);
		siteDetail.setDate(date);
//		appDetail.setStartTime(startTime);
//		appDetail.setEndTime(endTime);

		String sort = "";
		String order = "";
		if (request.getParameter("sort") != null) {
			sort = request.getParameter("sort");
		} else {
			sort = "date";
		}
		if (request.getParameter("order") != null) {
			order = request.getParameter("order");
		} else {
			order = "desc";
		}
		String[] soStr = sort.split(",");
		String[] orStr = order.split(",");
		List orderList = new ArrayList();
		for (int i = 0; i < soStr.length; i++) {
			if ("startPv".equals(soStr[i])) {
				orderList.add("start_pv " + orStr[i]);
			} else if ("startUv".equals(soStr[i])) {
				orderList.add("start_uv " + orStr[i]);
			} else if ("newUser".equals(soStr[i])) {
				orderList.add("new_user " + orStr[i]);
			} else if ("commentNumber".equals(soStr[i])) {
				orderList.add("comment_number " + orStr[i]);
			} else if ("collectionNumber".equals(soStr[i])) {
				orderList.add("collection_number " + orStr[i]);
			} else if ("shareNumber".equals(soStr[i])) {
				orderList.add("share_number " + orStr[i]);
			} else if ("nonLocal".equals(soStr[i])) {
				orderList.add("non_local " + orStr[i]);
			} else {
				orderList.add(soStr[i] + " " + orStr[i]);
			}
		}
		siteDetail.setOrderList(orderList);

		siteDetailList = logXhMediaSiteCountService
				.searchSiteDataDetail(siteDetail);

		for (LogXhMediaSiteCount siteCount : siteDetailList) {
			SysGroups sg = sysGroupsService.selectByPrimaryKey(Integer.parseInt(siteCount.getSiteCode()));
			siteCount.setSiteName(sg.getName());
		}

		Map map = new HashMap();
		map.put("rows", siteDetailList);
		map.put("total", siteDetailList.size());
		String result = gson.toJson(map);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(result);
		response.getWriter().close();
	}
}
