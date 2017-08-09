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
import com.xh.media.model.LogDayXhMediaAppCount;
import com.xh.media.model.LogXhMediaAppCount;
import com.xh.media.model.SysGroupOrigin;
import com.xh.media.model.SysGroups;
import com.xh.media.service.LogDayXhMediaAppCountService;
import com.xh.media.service.LogXhMediaAppCountService;
import com.xh.media.service.SysGroupOriginService;
import com.xh.media.service.SysGroupsService;

@Controller
public class AppDataController {
	private Gson gson;
	private ApplicationContext context;
	private LogXhMediaAppCountService logXhMediaAppCountService;
	private LogDayXhMediaAppCountService logDayXhMediaAppCountService;
	private SysGroupsService sysGroupsService;
	private SysGroupOriginService sysGroupOriginService;
	public AppDataController() {
		 gson=new Gson();
	     context=new ClassPathXmlApplicationContext("applicationContext.xml");
	     logXhMediaAppCountService=(LogXhMediaAppCountService) context.getBean("logXhMediaAppCountService");
	     logDayXhMediaAppCountService=(LogDayXhMediaAppCountService) context.getBean("logDayXhMediaAppCountService");
	     sysGroupsService = (SysGroupsService)context.getBean("sysGroupsService");
	     sysGroupOriginService = (SysGroupOriginService)context.getBean("sysGroupOriginService");
	}
	
	
	@RequestMapping("/getAppData")
	public void getAppData(HttpServletRequest request,HttpServletResponse response) throws IOException{
		List<LogDayXhMediaAppCount> appData=new ArrayList<LogDayXhMediaAppCount>();
		LogDayXhMediaAppCount ldxmac = new LogDayXhMediaAppCount();
		List<SysGroups> groupList = (List<SysGroups>) request.getSession().getAttribute("groups");
		List<SysGroupOrigin> originsList = (List<SysGroupOrigin>) request.getSession().getAttribute("origins");
		List sitecodeList = new ArrayList();
		List originList = new ArrayList();
//		for (SysGroups sysGroups : groupList) {
//			sitecodeList.add(sysGroups.getId());
//			List<SysGroupOrigin> oriList = new ArrayList<SysGroupOrigin>();
//			oriList = sysGroupOriginService.findOriginListBySiteCode(String.valueOf(sysGroups.getId()));
//			if(oriList.size()>0)
//			{
//				for(SysGroupOrigin sgo:oriList)
//				{
//					originList.add(sgo.getOriginId());
//				}
//			}
//		}
		for(SysGroupOrigin sgo:originsList)
		{
			originList.add(sgo.getOriginId());
		}
		String originId = "";
		if (request.getParameter("originId")!=null && !"".equals(request.getParameter("originId"))) {
			originId = request.getParameter("originId");
			originList = new ArrayList();
			originList.add(originId);
		}
//		ldxmac.setSitcodList(sitecodeList);
		ldxmac.setOriginList(originList);
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
		ldxmac.setOrderList(orderList);
		
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
		
		ldxmac.setStartTime(startTime);
		ldxmac.setEndTime(endTime);
		appData = logDayXhMediaAppCountService.getAppDataNew(ldxmac);
		if (appData.size()>0) {
			for (LogDayXhMediaAppCount logDayXhMediaAppCount : appData) {
				SysGroupOrigin sgo = sysGroupOriginService.findOriginByOriginId(logDayXhMediaAppCount.getOriginId());
				logDayXhMediaAppCount.setOriginName(sgo.getOriginName());
//				logDayXhMediaAppCount.setNonLocal(logDayXhMediaAppCount.getLocal()+logDayXhMediaAppCount.getNonLocal());
			}
			Map map=new HashMap();
			map.put("rows", appData);
			map.put("total", appData.size());
			String result = gson.toJson(map);
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(result);
			response.getWriter().close();
		}
  }
	
	@RequestMapping("/getDayAppDataDetail")
	public void getDayAppDetail(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// List<LogXhMediaAppCount> appDetailList = new
		// ArrayList<LogXhMediaAppCount>();
		List<LogXhMediaAppCount> appDetailList = new ArrayList<LogXhMediaAppCount>();
		LogXhMediaAppCount appDetail = new LogXhMediaAppCount();
		String originId = request.getParameter("originId");
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

		appDetail.setOriginId(originId);
		appDetail.setDate(date);
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
		appDetail.setOrderList(orderList);

		appDetailList = logXhMediaAppCountService
				.searchAppDataDetail(appDetail);

		for (LogXhMediaAppCount appCount : appDetailList) {
			SysGroupOrigin sgo = sysGroupOriginService.findOriginByOriginId(appCount.getOriginId());
			appCount.setOriginName(sgo.getOriginName());
		}

		Map map = new HashMap();
		map.put("rows", appDetailList);
		map.put("total", appDetailList.size());
		String result = gson.toJson(map);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(result);
		response.getWriter().close();
	}
}
