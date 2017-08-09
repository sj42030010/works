package com.xh.media.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import wap.util.Key;

import com.google.gson.Gson;
import com.xh.media.model.LogDayXhMediaProgramAnalysis;
import com.xh.media.model.LogDayXhMediaUser;
import com.xh.media.model.LogXhMediaProgramAnalysis;
import com.xh.media.model.LogXhMediaUser;
import com.xh.media.model.LogXhMediaVisit;
import com.xh.media.model.SysGroups;
import com.xh.media.service.LogDayXhMediaUserService;
import com.xh.media.service.LogXhMediaUserService;
@Controller
public class NewAndOldUserController extends AbstractController{

	private Gson gson;
	private ApplicationContext context;
	private LogXhMediaUserService logXhMediaUserService;
	private LogDayXhMediaUserService logDayXhMediaUserService;
	public NewAndOldUserController() {
     gson=new Gson();
     context=new ClassPathXmlApplicationContext("applicationContext.xml");
     logXhMediaUserService=(LogXhMediaUserService) context.getBean("logXhMediaUserService");
     logDayXhMediaUserService=(LogDayXhMediaUserService) context.getBean("logDayXhMediaUserService");
	}
	
	@RequestMapping("/newandolduser")
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0,HttpServletResponse arg1) {

		try {
			String sort = "";
			String order = "";
			if(arg0.getParameter("sort")!=null){
				sort = arg0.getParameter("sort");
			}else{
				sort = "new_User";
			}
			if(arg0.getParameter("order")!=null){
				order = arg0.getParameter("order");
			}else{
				order = "desc";
			}
			
			int  action=0;
			if(arg0.getParameter("action") != null)
				action = Integer.parseInt(arg0.getParameter("action"));
			String originId = "";
			if(arg0.getParameter("originId") != null)
				originId = arg0.getParameter("originId");
			String channelId = "";
			if(arg0.getParameter("channelId") != null)
				channelId = arg0.getParameter("channelId");
			String startTime = "";
			if(arg0.getParameter("startTime")!=null)
				startTime = arg0.getParameter("startTime");
			String endTime = "";
			if(arg0.getParameter("endTime")!=null)
				endTime = arg0.getParameter("endTime");
			String resultStr = "";
			Map map = new HashMap();
			List<LogXhMediaUser> userList = new ArrayList<LogXhMediaUser>();
			List<LogDayXhMediaUser> userDayList = new ArrayList<LogDayXhMediaUser>();
			
			List<SysGroups> groupList = (List<SysGroups>) arg0.getSession().getAttribute("groups");
			List sitcodList=new ArrayList();
			for (SysGroups sysGroups : groupList) {
				sitcodList.add(sysGroups.getId());
			}
			
			String[] soStr = sort.split(",");
			String[] orStr = order.split(",");
			List orderList=new ArrayList();
			for(int i=0;i<soStr.length;i++){
				if("newUser".equals(soStr[i])){
					orderList.add("new_User "+orStr[i]);
				}else if("activeUser".equals(soStr[i])){
					orderList.add("active_User "+orStr[i]);
				}else if ("useLength".equals(soStr[i])) {
					orderList.add("use_Length "+orStr[i]);
				}else{
					orderList.add(soStr[i]+" "+orStr[i]);
				}
			}
			switch (action) {
			case 1:
				if("".equals(originId)&&"".equals(channelId)&&"".equals(startTime)&&"".equals(endTime)){
					LogDayXhMediaUser record=new LogDayXhMediaUser();
					if (sitcodList.size()>0) {
						record.setSitcodList(sitcodList);
					}
					record.setOrderList(orderList);
					userDayList = logDayXhMediaUserService.findSumUserList(record);
					for (LogDayXhMediaUser logDayXhMediaUser : userDayList) {
						if (logDayXhMediaUser.getActiveUser()>0) {
							int AverageUserLength=logDayXhMediaUser.getUseLength()/logDayXhMediaUser.getActiveUser();
							logDayXhMediaUser.setUseLength(AverageUserLength);
						}else{
							logDayXhMediaUser.setUseLength(0);
						}
						
					}
					map.put("total", userDayList.size());
					map.put("rows", userDayList);
				}else{
					if("".equals(startTime)){
						startTime = "1900-01-01";
					}	
					if("".equals(endTime)){
						Timestamp ts = new Timestamp(System.currentTimeMillis());
						String nowTime = ts+"";
						endTime = nowTime.substring(0,10);
					}
//					if (startTime.equals(endTime)) {
//						LogXhMediaUser record = new LogXhMediaUser();
//						if(!"".equals(originId)){
//							record.setSiteCode(originId);
//						}
//						if(!"".equals(channelId)){
//							record.setOriginId(channelId);
//						}
//						record.setStartTime(startTime);
//						record.setEndTime(endTime);
//						record.setOrderList(orderList);
//						if (sitcodList.size()>0) {
//							record.setSitcodList(sitcodList);
//						}
//						userList = logXhMediaUserService.getUserListBySearchOneDay(record);	
//						for (LogXhMediaUser logDayXhMediaUser : userList) {
//							if (logDayXhMediaUser.getActiveUser()>0) {
//								int AverageUserLength=logDayXhMediaUser.getUseLength()/logDayXhMediaUser.getActiveUser();
//								logDayXhMediaUser.setUseLength(AverageUserLength);
//							}else{
//								logDayXhMediaUser.setUseLength(0);
//							}
//						}
//						map.put("total", userList.size());
//						map.put("rows", userList);
//					}else{
						LogDayXhMediaUser recordDay = new LogDayXhMediaUser();
						if(!"".equals(originId)){
							recordDay.setSiteCode(originId);
						}
						if(!"".equals(channelId)){
							recordDay.setOriginId(channelId);
						}
						recordDay.setStartTime(startTime);
						recordDay.setEndTime(endTime);
						recordDay.setOrderList(orderList);
						if (sitcodList.size()>0) {
							recordDay.setSitcodList(sitcodList);
						}
						userDayList = logDayXhMediaUserService.getUserListBySearch(recordDay);
						for (LogDayXhMediaUser logDayXhMediaUser : userDayList) {
							if (logDayXhMediaUser.getActiveUser()>0) {
								int AverageUserLength=logDayXhMediaUser.getUseLength()/logDayXhMediaUser.getActiveUser();
								logDayXhMediaUser.setUseLength(AverageUserLength);
							}else{
								logDayXhMediaUser.setUseLength(0);
							}
						}
						map.put("total", userDayList.size());
						map.put("rows", userDayList);
					}
					
//				}
				break;
			case 2:
				if("".equals(originId)&&"".equals(channelId)&&"".equals(startTime)&&"".equals(endTime)){
					LogDayXhMediaUser record=new LogDayXhMediaUser();
					if (sitcodList.size()>0) {
						record.setSitcodList(sitcodList);
					}
					userDayList = logDayXhMediaUserService.findSumUserListForCharts(record);
					for (LogDayXhMediaUser logDayXhMediaUser : userDayList) {
						if (logDayXhMediaUser.getActiveUser()>0) {
							int AverageUserLength=logDayXhMediaUser.getUseLength()/logDayXhMediaUser.getActiveUser();
							logDayXhMediaUser.setUseLength(AverageUserLength);
						}else{
							logDayXhMediaUser.setUseLength(0);
						}
					}
					map.put("total", userDayList.size());
					map.put("rows", userDayList);
				}else{
					if("".equals(startTime)){
						startTime = "1900-01-01";
					}	
					if("".equals(endTime)){
						Timestamp ts = new Timestamp(System.currentTimeMillis());
						String nowTime = ts+"";
						endTime = nowTime.substring(0,10);
					}
					
					if (startTime.equals(endTime)) {
						LogXhMediaUser record = new LogXhMediaUser();
						if(!"".equals(originId)){
							record.setSiteCode(originId);
						}
						if(!"".equals(channelId)){
							record.setOriginId(channelId);
						}
						record.setStartTime(startTime);
						record.setEndTime(endTime);
						if (sitcodList.size()>0) {
							record.setSitcodList(sitcodList);
						}
					userList = logXhMediaUserService.getUserListBySearchOneDayForCharts(record);
					for (LogXhMediaUser logDayXhMediaUser : userList) {
						if (logDayXhMediaUser.getActiveUser()>0) {
							int AverageUserLength=logDayXhMediaUser.getUseLength()/logDayXhMediaUser.getActiveUser();
							logDayXhMediaUser.setUseLength(AverageUserLength);
						}else{
							logDayXhMediaUser.setUseLength(0);
						}
					}
					userList=fillUpZero(userList);
					map.put("total", userList.size());
					map.put("rows", userList);
					}else{
						LogDayXhMediaUser recordDay = new LogDayXhMediaUser();
						if(!"".equals(originId)){
						    recordDay.setSiteCode(originId);
						}
						if(!"".equals(channelId)){
						    recordDay.setOriginId(channelId);
						}
						recordDay.setStartTime(startTime);
						recordDay.setEndTime(endTime);
						if (sitcodList.size()>0) {
							recordDay.setSitcodList(sitcodList);
						}
					userDayList = logDayXhMediaUserService.getUserListBySearchForCharts(recordDay);
					for (LogDayXhMediaUser logDayXhMediaUser : userDayList) {
						if (logDayXhMediaUser.getActiveUser()>0) {
							int AverageUserLength=logDayXhMediaUser.getUseLength()/logDayXhMediaUser.getActiveUser();
							logDayXhMediaUser.setUseLength(AverageUserLength);
						}else{
							logDayXhMediaUser.setUseLength(0);
						}
					}
					map.put("total", userDayList.size());
					map.put("rows", userDayList);
					}
				}
				break;
			case 3:
				if("".equals(originId)&&"".equals(channelId)&&"".equals(startTime)&&"".equals(endTime)){
					LogDayXhMediaUser record=new LogDayXhMediaUser();
					if (sitcodList.size()>0) {
						record.setSitcodList(sitcodList);
					}
					userDayList = logDayXhMediaUserService.findUserDayListForChartsPie(record);
					List<LogDayXhMediaUser> userDayList1=new ArrayList<LogDayXhMediaUser>();
					int newUser=0;
					for (int i = 0; i < userDayList.size(); i++) {
					     if (i<10) {
					    	 userDayList1.add(userDayList.get(i));
						}else{
							newUser+=userDayList.get(i).getNewUser();
						}
					}
					if (newUser>0) {
						record.setOriginName("其他");
						record.setNewUser(newUser);
						userDayList1.add(record);
					}
					map.put("total", userDayList1.size());
					map.put("rows", userDayList1); 
				}else{
					if("".equals(startTime)){
						startTime = "1900-01-01";
					}	
					if("".equals(endTime)){
						Timestamp ts = new Timestamp(System.currentTimeMillis());
						String nowTime = ts+"";
						endTime = nowTime.substring(0,10);
					}
					if (startTime.equals(endTime)) {
						LogXhMediaUser record = new LogXhMediaUser();
						if(!"".equals(originId)){
							record.setSiteCode(originId);
						}
						if(!"".equals(channelId)){
							record.setOriginId(channelId);
						}
						record.setStartTime(startTime);
						record.setEndTime(endTime);
						if (sitcodList.size()>0) {
							record.setSitcodList(sitcodList);
						}
					userList = logXhMediaUserService.getUserListBySearchOneDayForChartsPie(record);
					List<LogXhMediaUser> userDayList1=new ArrayList<LogXhMediaUser>();
					int newUser=0;
					for (int i = 0; i < userList.size(); i++) {
					     if (i<10) {
					    	 userDayList1.add(userList.get(i));
						}else{
							newUser+=userDayList.get(i).getNewUser();
						}
					}
					if (newUser>0) {
						record.setOriginName("其他");
						record.setNewUser(newUser);
						userDayList1.add(record);
					}
					
					map.put("total", userDayList1.size());
					map.put("rows", userDayList1);
					}else{
					LogDayXhMediaUser recordDay = new LogDayXhMediaUser();
					
					if(!"".equals(originId)){
					    recordDay.setSiteCode(originId);
					}
					if(!"".equals(channelId)){
					    recordDay.setOriginId(channelId);
					}
					recordDay.setStartTime(startTime);
					recordDay.setEndTime(endTime);
					if (sitcodList.size()>0) {
						recordDay.setSitcodList(sitcodList);
					}
					userDayList = logDayXhMediaUserService.getUserDayListBySearchForChartsPie(recordDay);
					List<LogDayXhMediaUser> userDayList1=new ArrayList<LogDayXhMediaUser>();
					int newUser=0;
					for (int i = 0; i < userDayList.size(); i++) {
					     if (i<10) {
					    	 userDayList1.add(userDayList.get(i));
						}else{
							newUser+=userDayList.get(i).getNewUser();
						}
					}
					if (newUser>0) {
						recordDay.setOriginName("其他");
						recordDay.setNewUser(newUser);
						userDayList1.add(recordDay);
					}
					map.put("total", userDayList1.size());
					map.put("rows", userDayList1);
					}
				}
				break;
			case 4:
				if("".equals(originId)&&"".equals(channelId)&&"".equals(startTime)&&"".equals(endTime)){
					LogDayXhMediaUser record=new LogDayXhMediaUser();
					if (sitcodList.size()>0) {
						record.setSitcodList(sitcodList);
					}
					record.setOrderList(orderList);
					userDayList = logDayXhMediaUserService.findSumUserList(record);
					for (LogDayXhMediaUser logDayXhMediaUser : userDayList) {
						if (logDayXhMediaUser.getActiveUser()>0) {
							int AverageUserLength=logDayXhMediaUser.getUseLength()/logDayXhMediaUser.getActiveUser();
							logDayXhMediaUser.setUseLength(AverageUserLength);
						}else{
							logDayXhMediaUser.setUseLength(0);
						}
						
					}
					map.put("total", userDayList.size());
					map.put("rows", userDayList);
				}else{
					if("".equals(startTime)){
						startTime = "1900-01-01";
					}	
					if("".equals(endTime)){
						Timestamp ts = new Timestamp(System.currentTimeMillis());
						String nowTime = ts+"";
						endTime = nowTime.substring(0,10);
					}
					if (startTime.equals(endTime)) {
						LogXhMediaUser record = new LogXhMediaUser();
						if(!"".equals(originId)){
							record.setSiteCode(originId);
						}
						if(!"".equals(channelId)){
							record.setOriginId(channelId);
						}
						record.setStartTime(startTime);
						record.setEndTime(endTime);
						record.setOrderList(orderList);
						if (sitcodList.size()>0) {
							record.setSitcodList(sitcodList);
						}
						userList = logXhMediaUserService.getUserListBySearchOneDay(record);	
						for (LogXhMediaUser logDayXhMediaUser : userList) {
							if (logDayXhMediaUser.getActiveUser()>0) {
								int AverageUserLength=logDayXhMediaUser.getUseLength()/logDayXhMediaUser.getActiveUser();
								logDayXhMediaUser.setUseLength(AverageUserLength);
							}else{
								logDayXhMediaUser.setUseLength(0);
							}
						}
						map.put("total", userList.size());
						map.put("rows", userList);
					}else{
						LogDayXhMediaUser recordDay = new LogDayXhMediaUser();
						if(!"".equals(originId)){
							recordDay.setSiteCode(originId);
						}
						if(!"".equals(channelId)){
							recordDay.setOriginId(channelId);
						}
						recordDay.setStartTime(startTime);
						recordDay.setEndTime(endTime);
						recordDay.setOrderList(orderList);
						if (sitcodList.size()>0) {
							recordDay.setSitcodList(sitcodList);
						}
						userDayList = logDayXhMediaUserService.getUserListBySearch(recordDay);
						for (LogDayXhMediaUser logDayXhMediaUser : userDayList) {
							if (logDayXhMediaUser.getActiveUser()>0) {
								int AverageUserLength=logDayXhMediaUser.getUseLength()/logDayXhMediaUser.getActiveUser();
								logDayXhMediaUser.setUseLength(AverageUserLength);
							}else{
								logDayXhMediaUser.setUseLength(0);
							}
						}
						map.put("total", userDayList.size());
						map.put("rows", userDayList);
					}
				}
				export2Excel(arg0,arg1,userList,userDayList);
				break;
			case 5://汇总数据
				List<LogDayXhMediaUser> selectUserDataSum = logDayXhMediaUserService.selectUserDataSum();
				for (LogDayXhMediaUser logDayXhMediaUser : selectUserDataSum) {
					if (logDayXhMediaUser.getActiveUser()>0) {
						int AverageUserLength=logDayXhMediaUser.getUseLength()/logDayXhMediaUser.getActiveUser();
						logDayXhMediaUser.setUseLength(AverageUserLength);
					}else{
						logDayXhMediaUser.setUseLength(0);
					}
				}
				map.put("total", selectUserDataSum.size());
				map.put("rows", selectUserDataSum);
			break;
			default:
				break;
			}
			resultStr = gson.toJson(map);
			arg1.setCharacterEncoding("UTF-8");
			arg1.getWriter().write(resultStr);
			arg1.getWriter().flush();
			arg1.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 数据表格详情
	 */
	@RequestMapping("/newandolduser_detail")
	public void dataDetail(HttpServletRequest request,HttpServletResponse response){
		String date="";
		if (request.getParameter("date")!=null) {
			date=request.getParameter("date");
		}
		String siteCode="";
		if (request.getParameter("siteCode")!=null) {
			siteCode=request.getParameter("siteCode");
		}
		String originId="";
		if (request.getParameter("originId")!=null) {
			originId=request.getParameter("originId");
		}
		
		String sort = "";
		String order = "";
		if(request.getParameter("sort")!=null){
			sort = request.getParameter("sort");
		}else{
			sort = "new_User";
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
			if("newUser".equals(soStr[i])){
				orderList.add("new_User "+orStr[i]);
			}else if("activeUser".equals(soStr[i])){
				orderList.add("active_User "+orStr[i]);
			}else if ("useLength".equals(soStr[i])) {
				orderList.add("use_Length "+orStr[i]);
			}else{
				orderList.add(soStr[i]+" "+orStr[i]);
			}
		}
		LogXhMediaUser logXhMediaUser=new LogXhMediaUser();
		if (!"".equals(date)) {
			logXhMediaUser.setDate(date);
		}
		if (!"".equals(siteCode)) {
			logXhMediaUser.setSiteCode(siteCode);
		}
		if (!"".equals(originId)) {
			logXhMediaUser.setOriginId(originId);
		}
		logXhMediaUser.setOrderList(orderList);
		
		List<LogXhMediaUser> userListDetail = logXhMediaUserService.getUserListDetail(logXhMediaUser);
		for (LogXhMediaUser logDayXhMediaUser : userListDetail) {
			if (logDayXhMediaUser.getActiveUser()>0) {
				int AverageUserLength=logDayXhMediaUser.getUseLength()/logDayXhMediaUser.getActiveUser();
				logDayXhMediaUser.setUseLength(AverageUserLength);
			}else{
				logDayXhMediaUser.setUseLength(0);
			}
		}
		Map map=new HashMap();
		map.put("total", userListDetail.size());
		map.put("rows", userListDetail);
		String resultStr = gson.toJson(map);
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write(resultStr);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 折线图详情
	 */
	@RequestMapping("/newandolduser_detail_line")
	public void chartLineDetail(HttpServletRequest request,HttpServletResponse response){
		String date="";
		if (request.getParameter("date")!=null) {
			date=request.getParameter("date");
		}
		String siteCode="";
		if (request.getParameter("siteCode")!=null) {
			siteCode=request.getParameter("siteCode");
		}
		String originId="";
		if (request.getParameter("originId")!=null) {
			originId=request.getParameter("originId");
		}
		LogXhMediaUser logXhMediaUser=new LogXhMediaUser();
		if (!"".equals(date)) {
			logXhMediaUser.setDate(date);
		}
		if (!"".equals(siteCode)) {
			logXhMediaUser.setSiteCode(siteCode);
		}
		if (!"".equals(originId)) {
			logXhMediaUser.setOriginId(originId);
		}
		
		List<LogXhMediaUser> userChartLineDetail = logXhMediaUserService.getUserChartLineDetail(logXhMediaUser);
		for (LogXhMediaUser logDayXhMediaUser : userChartLineDetail) {
		if (logDayXhMediaUser.getActiveUser()>0) {
			int AverageUserLength=logDayXhMediaUser.getUseLength()/logDayXhMediaUser.getActiveUser();
			logDayXhMediaUser.setUseLength(AverageUserLength);
		}else{
			logDayXhMediaUser.setUseLength(0);
		}
	}
		List<LogXhMediaUser> fillUpZero = fillUpZero(userChartLineDetail);
		Map map=new HashMap();
		map.put("total", fillUpZero.size());
		map.put("rows", fillUpZero);
		String resultStr = gson.toJson(map);
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write(resultStr);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 饼状图详情
	 */
	@RequestMapping("/newandolduser_detail_pie")
	public void chartPieDetail(HttpServletRequest request,HttpServletResponse response){
		String date="";
		if (request.getParameter("date")!=null) {
			date=request.getParameter("date");
		}
		String siteCode="";
		if (request.getParameter("siteCode")!=null) {
			siteCode=request.getParameter("siteCode");
		}
		String originId="";
		if (request.getParameter("originId")!=null) {
			originId=request.getParameter("originId");
		}
		LogXhMediaUser logXhMediaUser=new LogXhMediaUser();
		if (!"".equals(date)) {
			logXhMediaUser.setDate(date);
		}
		if (!"".equals(siteCode)) {
			logXhMediaUser.setSiteCode(siteCode);
		}
		if (!"".equals(originId)) {
			logXhMediaUser.setOriginId(originId);
		}
		List<LogXhMediaUser> userChartPieDetail = logXhMediaUserService.getUserChartPieDetail(logXhMediaUser);
		for (LogXhMediaUser logDayXhMediaUser : userChartPieDetail) {
		if (logDayXhMediaUser.getActiveUser()>0) {
			int AverageUserLength=logDayXhMediaUser.getUseLength()/logDayXhMediaUser.getActiveUser();
			logDayXhMediaUser.setUseLength(AverageUserLength);
		}else{
			logDayXhMediaUser.setUseLength(0);
		}
	  }
		Map map=new HashMap();
		map.put("total", userChartPieDetail.size());
		map.put("rows", userChartPieDetail);
		String resultStr = gson.toJson(map);
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write(resultStr);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/detailDataSum")
	public void detailDataSum(HttpServletRequest request,HttpServletResponse response){
		String date="";
		if (request.getParameter("date")!=null) {
			date=request.getParameter("date");
		}
		String siteCode="";
		if (request.getParameter("siteCode")!=null) {
			siteCode=request.getParameter("siteCode");
		}
		String originId="";
		if (request.getParameter("originId")!=null) {
			originId=request.getParameter("originId");
		}
		LogXhMediaUser logXhMediaUser=new LogXhMediaUser();
		if (!"".equals(date)) {
			logXhMediaUser.setDate(date);
		}
		if (!"".equals(siteCode)) {
			logXhMediaUser.setSiteCode(siteCode);
		}
		if (!"".equals(originId)) {
			logXhMediaUser.setOriginId(originId);
		}
		List<LogXhMediaUser> selectDetailSumData = logXhMediaUserService.selectDetailSumData(logXhMediaUser);
		for (LogXhMediaUser logDayXhMediaUser : selectDetailSumData) {
		if (logDayXhMediaUser.getActiveUser()>0) {
			int AverageUserLength=logDayXhMediaUser.getUseLength()/logDayXhMediaUser.getActiveUser();
			logDayXhMediaUser.setUseLength(AverageUserLength);
		}else{
			logDayXhMediaUser.setUseLength(0);
		}
	  }
		Map map=new HashMap();
		map.put("total", selectDetailSumData.size());
		map.put("rows", selectDetailSumData);
		String resultStr = gson.toJson(map);
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write(resultStr);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *  缺数补零
	 * @param userList
	 */
	private List<LogXhMediaUser> fillUpZero(List<LogXhMediaUser> userList) {
		List<LogXhMediaUser> minutesList=new ArrayList<LogXhMediaUser>();
		for (int i = 0; i < Key.hours.length; i++) {
			LogXhMediaUser user1=new LogXhMediaUser();
			user1.setMinute("00");
			user1.setHour(Key.hours[i]);
			user1.setActiveUser(0);
			user1.setNewUser(0);
			user1.setUseLength(0);
			minutesList.add(user1);
			for (int j = 0; j < Key.minutes.length; j++) {
				LogXhMediaUser user=new LogXhMediaUser();
				user.setMinute(Key.minutes[j]);
				user.setHour(Key.hours[i]+":"+Key.minutes[j]);
				user.setActiveUser(0);
				user.setNewUser(0);
				user.setUseLength(0);
				minutesList.add(user);
			}
		}
		for (LogXhMediaUser logXhMediaUser : userList) {
			for (LogXhMediaUser minutesListUser : minutesList) {
				if (logXhMediaUser.getHour().equals(minutesListUser.getHour().substring(0,2))&&logXhMediaUser.getMinute().equals(minutesListUser.getMinute())) {
					minutesListUser.setActiveUser(logXhMediaUser.getActiveUser());
					minutesListUser.setNewUser(logXhMediaUser.getNewUser());
					minutesListUser.setUseLength(logXhMediaUser.getUseLength());
				}
				
			}
		}
		
		return minutesList;
	}

	/**
	 * 把数据写入Excel表格中并保存在服务器的指定目录中
	 * @param arg0
	 * @param arg1              
	 * @param visitListForExcel
	 * @param visitDayListForExcel
	 */
	private void export2Excel(HttpServletRequest arg0,HttpServletResponse arg1, 
			List<LogXhMediaUser> userList, List<LogDayXhMediaUser> userDayList){
		String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");
		String docsPath = arg0.getSession().getServletContext().getRealPath("docs");
		String fileName = "新老访客" + System.currentTimeMillis() + ".xlsx";
		String filePath = docsPath + FILE_SEPARATOR + fileName;
	 try {
			if (userList.size()>0) {
				// 输出流
				OutputStream os = new FileOutputStream(filePath);
				// 工作区
				XSSFWorkbook wb = new XSSFWorkbook();
				XSSFSheet sheet = wb.createSheet("新老访客");
				XSSFRow rowTitle = sheet.createRow(0);
				rowTitle.createCell(0).setCellValue("日期");
				rowTitle.createCell(1).setCellValue("小时");
				rowTitle.createCell(2).setCellValue("站点");
				rowTitle.createCell(3).setCellValue("渠道");
				rowTitle.createCell(4).setCellValue("新增用户");
				rowTitle.createCell(5).setCellValue("活跃用户");
				rowTitle.createCell(6).setCellValue("人均使用时长(秒)");
				for (int i = 0; i < userList.size(); i++) {
					// 创建第一个sheet
					// 生成第一行
					XSSFRow row = sheet.createRow(i+1);
					// 给这一行的第一列赋值
					row.createCell(0).setCellValue(userList.get(i).getDate());
					row.createCell(1).setCellValue(userList.get(i).getHour());
					row.createCell(2).setCellValue(userList.get(i).getSiteCode());
					row.createCell(3).setCellValue(userList.get(i).getOriginName());
					row.createCell(4).setCellValue(userList.get(i).getNewUser());
					row.createCell(5).setCellValue(userList.get(i).getActiveUser());
					row.createCell(6).setCellValue(userList.get(i).getUseLength());
				}
				// 写文件
				wb.write(os);
				// 关闭输出流
				os.close();
			}else{
				// 输出流
				OutputStream os = new FileOutputStream(filePath);
				// 工作区
				XSSFWorkbook wb = new XSSFWorkbook();
				XSSFSheet sheet = wb.createSheet("视频分析明细");
				XSSFRow rowTitle = sheet.createRow(0);
				rowTitle.createCell(0).setCellValue("日期");
				rowTitle.createCell(1).setCellValue("站点");
				rowTitle.createCell(2).setCellValue("渠道");
				rowTitle.createCell(3).setCellValue("新增用户");
				rowTitle.createCell(4).setCellValue("活跃用户");
				rowTitle.createCell(5).setCellValue("人均使用时长");
				for (int i = 0; i < userDayList.size(); i++) {
					// 创建第一个sheet
					// 生成第一行
					XSSFRow row = sheet.createRow(i+1);
					// 给这一行的第一列赋值
					row.createCell(0).setCellValue(userDayList.get(i).getDate());
					row.createCell(1).setCellValue(userDayList.get(i).getSiteCode());
					row.createCell(2).setCellValue(userDayList.get(i).getOriginName());
					row.createCell(3).setCellValue(userDayList.get(i).getNewUser());
					row.createCell(4).setCellValue(userDayList.get(i).getActiveUser());
					row.createCell(5).setCellValue(userDayList.get(i).getUseLength());
				}
				// 写文件
				wb.write(os);
				// 关闭输出流
				os.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
//		download(filePath,arg1);
	}
	
}
