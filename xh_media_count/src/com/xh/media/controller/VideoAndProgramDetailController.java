package com.xh.media.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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
import com.xh.media.model.LogXhMediaProgramAnalysis;
import com.xh.media.model.SysGroups;
import com.xh.media.service.LogDayXhMediaProgramAnalysisService;
import com.xh.media.service.LogXhMediaProgramAnalysisService;
@Controller
public class VideoAndProgramDetailController extends AbstractController{
	private Gson gson;
	private ApplicationContext context;
	private LogXhMediaProgramAnalysisService logXhMediaProgramAnalysisService;
	private LogDayXhMediaProgramAnalysisService logDayXhMediaProgramAnalysisService;
	public VideoAndProgramDetailController() {
		 gson=new Gson();
		 context=new ClassPathXmlApplicationContext("applicationContext.xml");
		 logXhMediaProgramAnalysisService = (LogXhMediaProgramAnalysisService) context.getBean("logXhMediaProgramAnalysisService");
		 logDayXhMediaProgramAnalysisService=(LogDayXhMediaProgramAnalysisService) context.getBean("logDayXhMediaProgramAnalysisService");
	}

	@RequestMapping("/videoAndProgramDetail")
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0,HttpServletResponse arg1){
		try {
			int action=0;
			if(arg0.getParameter("action") != null)
				action = Integer.parseInt(arg0.getParameter("action"));
			String from="";
			if(arg0.getParameter("from") != null)
				from = arg0.getParameter("from");
			String globalId="";
			if(arg0.getParameter("globalId") != null)
				globalId = arg0.getParameter("globalId");
			String siteCode="";
			if(arg0.getParameter("siteCode") != null)
				siteCode = arg0.getParameter("siteCode");
			String startTime = "";
			if(arg0.getParameter("startTime")!=null)
				startTime = arg0.getParameter("startTime");
			String endTime = "";
			if(arg0.getParameter("endTime")!=null)
				endTime = arg0.getParameter("endTime");
			String resultStr = "";
			Map map = new HashMap();
			List<LogXhMediaProgramAnalysis> videoAnalysisList = new ArrayList<LogXhMediaProgramAnalysis>();
			List<LogDayXhMediaProgramAnalysis> videoAnalysisDayList = new ArrayList<LogDayXhMediaProgramAnalysis>();
			List<SysGroups> groupList = (List<SysGroups>) arg0.getSession().getAttribute("groups");
			List sitcodList=new ArrayList();
				for (SysGroups sysGroups : groupList) {
					sitcodList.add(sysGroups.getId());
				}
				
				String sort = "";
				String order = "";
				if(arg0.getParameter("sort")!=null){
					sort = arg0.getParameter("sort");
				}else{
					if (from.equals(2)) {
						sort = "play_Number";
					}else{
						sort = "pv";
					}
					
				}
				if(arg0.getParameter("order")!=null){
					order = arg0.getParameter("order");
				}else{
					order = "desc";
				}
				String[] soStr = sort.split(",");
				String[] orStr = order.split(",");
				List orderList=new ArrayList();
				for(int i=0;i<soStr.length;i++){
					if("playNumber".equals(soStr[i])){
						orderList.add("play_Number "+orStr[i]);
					}else if("playTime".equals(soStr[i])){
						orderList.add("play_Time "+orStr[i]);
					}else if("playUserNumber".equals(soStr[i])){
						orderList.add("play_User_Number "+orStr[i]);
					}else if("timeLength".equals(soStr[i])){
						orderList.add("time_Length "+orStr[i]);
					}else if("pubDate".equals(soStr[i])){
						orderList.add("pub_Date "+orStr[i]);
					}else{
						orderList.add(soStr[i]+" "+orStr[i]);
					}
				}	
			switch (action) {
			case 1:
				if("".equals(startTime)&&"".equals(endTime)){
					LogDayXhMediaProgramAnalysis recordDay = new LogDayXhMediaProgramAnalysis();
					if (sitcodList.size()>0) {
						recordDay.setSitcodList(sitcodList);	
					}
					recordDay.setOrderList(orderList);
					if (!"".equals(siteCode)) {
						recordDay.setSiteCode(siteCode);
					}
				    if(!"".equals(globalId))
				    	recordDay.setGlobalId(globalId);
					if ("2".equals(from)) {//2代表视频
						videoAnalysisDayList=logDayXhMediaProgramAnalysisService.getSummaryVideoDetail(recordDay);	
					}else{
						videoAnalysisDayList=logDayXhMediaProgramAnalysisService.getSummaryProgramDetail(recordDay);
					}
					for (LogDayXhMediaProgramAnalysis logXhMediaProgramAnalysis : videoAnalysisDayList) {
						if("0".equals(logXhMediaProgramAnalysis.getTimeLength()+"")){
							logXhMediaProgramAnalysis.setAlreadyPlay("0");
						}else{
							int temp=logXhMediaProgramAnalysis.getPlayTime()*100/logXhMediaProgramAnalysis.getTimeLength();
							logXhMediaProgramAnalysis.setAlreadyPlay(temp+"");
						}
					}
					map.put("total", videoAnalysisDayList.size());
					map.put("rows", videoAnalysisDayList);
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
							LogXhMediaProgramAnalysis record = new LogXhMediaProgramAnalysis();
							if(!"".equals(globalId)){
								record.setGlobalId(globalId);
							}
							record.setStartTime(startTime);
							record.setEndTime(endTime);
							record.setGlobalId(globalId);
							record.setOrderList(orderList);
							if (sitcodList.size()>0) {
								record.setSitcodList(sitcodList);
							}
							if (!"".equals(siteCode)) {
								record.setSiteCode(siteCode);
							}
							if ("2".equals(from)) {
								videoAnalysisList = logXhMediaProgramAnalysisService.getVideoDetailBySearchOneDay(record);
							}else{
								videoAnalysisList = logXhMediaProgramAnalysisService.getProgramDetailBySearchOneDay(record);
							}
							for (LogXhMediaProgramAnalysis logXhMediaProgramAnalysis : videoAnalysisList) {
								if("0".equals(logXhMediaProgramAnalysis.getTimeLength()+"")){
									logXhMediaProgramAnalysis.setAlreadyPlay("0");
								}else{
									int temp=logXhMediaProgramAnalysis.getPlayTime()*100/logXhMediaProgramAnalysis.getTimeLength();
									logXhMediaProgramAnalysis.setAlreadyPlay(temp+"");
								}
							}
							map.put("total", videoAnalysisList.size());
							map.put("rows", videoAnalysisList);
						}else{
							LogDayXhMediaProgramAnalysis recordDay = new LogDayXhMediaProgramAnalysis();
							if(!"".equals(globalId)){
								recordDay.setGlobalId(globalId);
							}
							recordDay.setStartTime(startTime);
							recordDay.setEndTime(endTime);
							if (sitcodList.size()>0) {
								recordDay.setSitcodList(sitcodList);
							}
							recordDay.setOrderList(orderList);
							if (!"".equals(siteCode)) {
								recordDay.setSiteCode(siteCode);
							}
							if ("2".equals(from)) {
								videoAnalysisDayList = logDayXhMediaProgramAnalysisService.getVideoDetailBySearch(recordDay);	
							}else{
								videoAnalysisDayList = logDayXhMediaProgramAnalysisService.getProgramDetailBySearch(recordDay);
							}
							for (LogDayXhMediaProgramAnalysis logXhMediaProgramAnalysis : videoAnalysisDayList) {
								if("0".equals(logXhMediaProgramAnalysis.getTimeLength()+"")){
									logXhMediaProgramAnalysis.setAlreadyPlay("0");
								}else{
									int temp=logXhMediaProgramAnalysis.getPlayTime()*100/logXhMediaProgramAnalysis.getTimeLength();
									logXhMediaProgramAnalysis.setAlreadyPlay(temp+"");
								}
							}
							map.put("total", videoAnalysisDayList.size());
							map.put("rows", videoAnalysisDayList);
						}
					     }
				break;
			case 2:
				if("".equals(startTime)&&"".equals(endTime)){
					LogDayXhMediaProgramAnalysis recordDay = new LogDayXhMediaProgramAnalysis();
					if (sitcodList.size()>0) {
						recordDay.setSitcodList(sitcodList);
					}	
				    if(!"".equals(globalId)){
				    	recordDay.setGlobalId(globalId);
				    }
				    if (sitcodList.size()>0) {
						recordDay.setSitcodList(sitcodList);
					}
				    if (!"".equals(siteCode)) {
						recordDay.setSiteCode(siteCode);
					}
					if ("2".equals(from)) {//2代表视频
						videoAnalysisDayList=logDayXhMediaProgramAnalysisService.getVideoDetailForChart(recordDay);	
					}else{
						videoAnalysisDayList=logDayXhMediaProgramAnalysisService.getProgramDetailForChart(recordDay);
					}
					for (LogDayXhMediaProgramAnalysis logXhMediaProgramAnalysis : videoAnalysisDayList) {
						if("0".equals(logXhMediaProgramAnalysis.getTimeLength()+"")){
							logXhMediaProgramAnalysis.setAlreadyPlay("0");
						}else{
							int temp=logXhMediaProgramAnalysis.getPlayTime()*100/logXhMediaProgramAnalysis.getTimeLength();
							logXhMediaProgramAnalysis.setAlreadyPlay(temp+"");
						}
					}
					map.put("total", videoAnalysisDayList.size());
					map.put("rows", videoAnalysisDayList);
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
							LogXhMediaProgramAnalysis record = new LogXhMediaProgramAnalysis();
							if(!"".equals(globalId)){
								record.setGlobalId(globalId);
							}
							record.setStartTime(startTime);
							record.setEndTime(endTime);
						    if (sitcodList.size()>0) {
								record.setSitcodList(sitcodList);
							}
						    if (!"".equals(siteCode)) {
								record.setSiteCode(siteCode);
							}
							if ("2".equals(from)) {
								videoAnalysisList = logXhMediaProgramAnalysisService.getVideoDetailChartBySearchOneDay(record);
							}else{
								videoAnalysisList = logXhMediaProgramAnalysisService.getProgramDetailChartBySearchOneDay(record);
							}
							for (LogXhMediaProgramAnalysis logXhMediaProgramAnalysis : videoAnalysisList) {
								if("0".equals(logXhMediaProgramAnalysis.getTimeLength()+"")){
									logXhMediaProgramAnalysis.setAlreadyPlay("0");
								}else{
									int temp=logXhMediaProgramAnalysis.getPlayTime()*100/logXhMediaProgramAnalysis.getTimeLength();
									logXhMediaProgramAnalysis.setAlreadyPlay(temp+"");
								}
							}
							videoAnalysisList=fillUpZero(from,videoAnalysisList);
							map.put("total", videoAnalysisList.size());
							map.put("rows", videoAnalysisList);
						}else{
							LogDayXhMediaProgramAnalysis recordDay = new LogDayXhMediaProgramAnalysis();
							if(!"".equals(globalId)){
								recordDay.setGlobalId(globalId);
							}
							recordDay.setStartTime(startTime);
							recordDay.setEndTime(endTime);
							 if (sitcodList.size()>0) {
									recordDay.setSitcodList(sitcodList);
								}
							 if (!"".equals(siteCode)) {
									recordDay.setSiteCode(siteCode);
								}
							if ("2".equals(from)) {
								videoAnalysisDayList = logDayXhMediaProgramAnalysisService.getVideoDetailChartBySearch(recordDay);	
							}else{
								videoAnalysisDayList = logDayXhMediaProgramAnalysisService.getProgramDetailChartBySearch(recordDay);
							}
							for (LogDayXhMediaProgramAnalysis logXhMediaProgramAnalysis : videoAnalysisDayList) {
								if("0".equals(logXhMediaProgramAnalysis.getTimeLength()+"")){
									logXhMediaProgramAnalysis.setAlreadyPlay("0");
								}else{
									int temp=logXhMediaProgramAnalysis.getPlayTime()*100/logXhMediaProgramAnalysis.getTimeLength();
									logXhMediaProgramAnalysis.setAlreadyPlay(temp+"");
								}
							}
							map.put("total", videoAnalysisDayList.size());
							map.put("rows", videoAnalysisDayList);
						}
					     }
				break;
			case 3:
				if("".equals(startTime)&&"".equals(endTime)){
					LogDayXhMediaProgramAnalysis recordDay = new LogDayXhMediaProgramAnalysis();
					if (sitcodList.size()>0) {
						recordDay.setSitcodList(sitcodList);
					}
						
					if ("3".equals(from)) {
						if("".equals(startTime)){
							startTime = "1900-01-01";
						}	
						if("".equals(endTime)){
							Timestamp ts = new Timestamp(System.currentTimeMillis());
							String nowTime = ts+"";
							endTime = nowTime.substring(0,10);
						}
						if(!"".equals(globalId)){
							recordDay.setGlobalId(globalId);
						}
						recordDay.setStartTime(startTime);
						recordDay.setEndTime(endTime);
						  if (sitcodList.size()>0) {
								recordDay.setSitcodList(sitcodList);
							}
						  if (!"".equals(siteCode)) {
								recordDay.setSiteCode(siteCode);
							}
						videoAnalysisDayList = logDayXhMediaProgramAnalysisService.getProgramDetailForChartPie(recordDay);
						map.put("total", videoAnalysisDayList.size());
						map.put("rows", videoAnalysisDayList);
					}
				}else{
					if ("3".equals(from)) {
						if("".equals(startTime)){
							startTime = "1900-01-01";
						}	
						if("".equals(endTime)){
							Timestamp ts = new Timestamp(System.currentTimeMillis());
							String nowTime = ts+"";
							endTime = nowTime.substring(0,10);
						}
						
						if (startTime.equals(endTime)) {
							LogXhMediaProgramAnalysis record = new LogXhMediaProgramAnalysis();
							if(!"".equals(globalId)){
								record.setGlobalId(globalId);
							}
							record.setStartTime(startTime);
							record.setEndTime(endTime);
						    if (sitcodList.size()>0) {
								record.setSitcodList(sitcodList);
							}
						    if (!"".equals(siteCode)) {
								record.setSiteCode(siteCode);
							}
							videoAnalysisList = logXhMediaProgramAnalysisService.getProgramDetailChartPieBySearchOneDay(record);
							map.put("total", videoAnalysisList.size());
							map.put("rows", videoAnalysisList);
						}else{
						LogDayXhMediaProgramAnalysis recordDay = new LogDayXhMediaProgramAnalysis();
						if(!"".equals(globalId)){
							recordDay.setGlobalId(globalId);
						}
						if (!"".equals(siteCode)) {
							recordDay.setSiteCode(siteCode);
						}
						recordDay.setStartTime(startTime);
						recordDay.setEndTime(endTime);
						  if (sitcodList.size()>0) {
								recordDay.setSitcodList(sitcodList);
							}
						videoAnalysisDayList = logDayXhMediaProgramAnalysisService.getProgramDetailCharPietBySearch(recordDay);
						if (videoAnalysisDayList.size()>0) {
							if (videoAnalysisDayList.get(0)==null) {
								map.put("rows", null);
							}else{
								map.put("rows", videoAnalysisDayList);
							}
						}
						map.put("total", videoAnalysisDayList.size());
						}
					}					
				}
				break;
			case 4:
				if("".equals(startTime)&&"".equals(endTime)){
					LogDayXhMediaProgramAnalysis recordDay = new LogDayXhMediaProgramAnalysis();
					if (sitcodList.size()>0) {
						recordDay.setSitcodList(sitcodList);	
					}
					if (!"".equals(siteCode)) {
						recordDay.setSiteCode(siteCode);
					}
				    if(!"".equals(globalId))
				    	recordDay.setGlobalId(globalId);
					if ("2".equals(from)) {//2代表视频
						videoAnalysisDayList=logDayXhMediaProgramAnalysisService.getSummaryVideoDetail(recordDay);	
					}else{
						videoAnalysisDayList=logDayXhMediaProgramAnalysisService.getSummaryProgramDetail(recordDay);
					}
					for (LogDayXhMediaProgramAnalysis logXhMediaProgramAnalysis : videoAnalysisDayList) {
						if("0".equals(logXhMediaProgramAnalysis.getTimeLength()+"")){
							logXhMediaProgramAnalysis.setAlreadyPlay("0");
						}else{
							int temp=logXhMediaProgramAnalysis.getPlayTime()*100/logXhMediaProgramAnalysis.getTimeLength();
							logXhMediaProgramAnalysis.setAlreadyPlay(temp+"");
						}
					}
					map.put("total", videoAnalysisDayList.size());
					map.put("rows", videoAnalysisDayList);
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
								LogXhMediaProgramAnalysis record = new LogXhMediaProgramAnalysis();
								if(!"".equals(globalId)){
									record.setGlobalId(globalId);
								}
								record.setStartTime(startTime);
								record.setEndTime(endTime);
								record.setGlobalId(globalId);
								if (sitcodList.size()>0) {
									record.setSitcodList(sitcodList);
								}
								if (!"".equals(siteCode)) {
									record.setSiteCode(siteCode);
								}
								if ("2".equals(from)) {
									videoAnalysisList = logXhMediaProgramAnalysisService.getVideoDetailBySearchOneDay(record);
								}else{
									videoAnalysisList = logXhMediaProgramAnalysisService.getProgramDetailBySearchOneDay(record);
								}
								for (LogXhMediaProgramAnalysis logXhMediaProgramAnalysis : videoAnalysisList) {
									if("0".equals(logXhMediaProgramAnalysis.getTimeLength()+"")){
										logXhMediaProgramAnalysis.setAlreadyPlay("0");
									}else{
										int temp=logXhMediaProgramAnalysis.getPlayTime()*100/logXhMediaProgramAnalysis.getTimeLength();
										logXhMediaProgramAnalysis.setAlreadyPlay(temp+"");
									}
								}
								map.put("total", videoAnalysisList.size());
								map.put("rows", videoAnalysisList);
							}else{
								LogDayXhMediaProgramAnalysis recordDay = new LogDayXhMediaProgramAnalysis();
								if(!"".equals(globalId)){
									recordDay.setGlobalId(globalId);
								}
								recordDay.setStartTime(startTime);
								recordDay.setEndTime(endTime);
								if (sitcodList.size()>0) {
									recordDay.setSitcodList(sitcodList);
								}
								if (!"".equals(siteCode)) {
									recordDay.setSiteCode(siteCode);
								}
								if ("2".equals(from)) {
									videoAnalysisDayList = logDayXhMediaProgramAnalysisService.getVideoDetailBySearch(recordDay);	
								}else{
									videoAnalysisDayList = logDayXhMediaProgramAnalysisService.getProgramDetailBySearch(recordDay);
								}
								for (LogDayXhMediaProgramAnalysis logXhMediaProgramAnalysis : videoAnalysisDayList) {
									if("0".equals(logXhMediaProgramAnalysis.getTimeLength()+"")){
										logXhMediaProgramAnalysis.setAlreadyPlay("0");
									}else{
										int temp=logXhMediaProgramAnalysis.getPlayTime()*100/logXhMediaProgramAnalysis.getTimeLength();
										logXhMediaProgramAnalysis.setAlreadyPlay(temp+"");
									}
								}
								map.put("total", videoAnalysisDayList.size());
								map.put("rows", videoAnalysisDayList);
							}
						   }
				
				export2Excel(arg0, arg1, videoAnalysisList, videoAnalysisDayList, from);
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

	private List<LogXhMediaProgramAnalysis> fillUpZero(String from,List<LogXhMediaProgramAnalysis> videoAnalysisList) {
		List<LogXhMediaProgramAnalysis>  minutesList=new ArrayList<LogXhMediaProgramAnalysis>();
			if ("2".equals(from)) {
				for (int i = 0; i < Key.hours.length; i++) {
					LogXhMediaProgramAnalysis visit1=new LogXhMediaProgramAnalysis();
					visit1.setMinute("00");
					visit1.setPlayTime(0);
					visit1.setPlayNumber(0);
					visit1.setPlayUserNumber(0);
					visit1.setTimeLength(0);
					visit1.setHour(Key.hours[i]);
					minutesList.add(visit1);
					for (int j = 0; j < Key.minutes.length; j++) {
						LogXhMediaProgramAnalysis visit=new LogXhMediaProgramAnalysis();
						visit.setMinute(Key.minutes[j]);
						visit.setPlayTime(0);
						visit.setPlayNumber(0);
						visit.setPlayUserNumber(0);
						visit.setTimeLength(0);
						visit.setHour(Key.hours[i]+":"+Key.minutes[j]);
						minutesList.add(visit);
					}
			}
				for (LogXhMediaProgramAnalysis logXhMediaVisit : videoAnalysisList) {
					for (LogXhMediaProgramAnalysis muinutesVisit : minutesList) {
							if (logXhMediaVisit.getHour().equals(muinutesVisit.getHour().substring(0,2))&&logXhMediaVisit.getMinute().equals(muinutesVisit.getMinute())) {
								muinutesVisit.setPlayTime(logXhMediaVisit.getPlayTime());
								muinutesVisit.setPlayNumber(logXhMediaVisit.getPlayNumber());
								muinutesVisit.setPlayUserNumber(logXhMediaVisit.getPlayUserNumber());
								muinutesVisit.setTimeLength(logXhMediaVisit.getTimeLength());
							}
					}
				}
			}else{
				for (int i = 0; i < Key.hours.length; i++) {
					LogXhMediaProgramAnalysis visit1=new LogXhMediaProgramAnalysis();
					visit1.setMinute("00");
					visit1.setPv(0);
					visit1.setUv(0);
					visit1.setIp("0");
					visit1.setHour(Key.hours[i]);
					minutesList.add(visit1);
					for (int j = 0; j < Key.minutes.length; j++) {
						LogXhMediaProgramAnalysis visit=new LogXhMediaProgramAnalysis();
						visit.setMinute(Key.minutes[j]);
						visit.setPv(0);
						visit.setUv(0);
						visit.setIp("0");
						visit.setHour(Key.hours[i]+":"+Key.minutes[j]);
						minutesList.add(visit);
					}
			}
				for (LogXhMediaProgramAnalysis logXhMediaVisit : videoAnalysisList) {
					for (LogXhMediaProgramAnalysis muinutesVisit : minutesList) {
							if (logXhMediaVisit.getHour().equals(muinutesVisit.getHour().substring(0,2))&&logXhMediaVisit.getMinute().equals(muinutesVisit.getMinute())) {
								muinutesVisit.setPv(logXhMediaVisit.getPv());
								muinutesVisit.setUv(logXhMediaVisit.getUv());
								muinutesVisit.setIp(logXhMediaVisit.getIp());
							}
					}
				}
			}

		return minutesList;
	}

	/**
	 * 获取合并后的视频分析列表
	 * @return
	 */
//	private List<LogDayXhMediaProgramAnalysis> getDetailListByFrom(int from,LogDayXhMediaProgramAnalysis record) {
//		List<LogDayXhMediaProgramAnalysis> mergeVideoAnalysisList = new ArrayList<LogDayXhMediaProgramAnalysis>();//合并后的数据集合
//		List<LogDayXhMediaProgramAnalysis> nativeVideoAnalysisList = new ArrayList<LogDayXhMediaProgramAnalysis>();
//		List<LogDayXhMediaProgramAnalysis> recommendVideoAnalysisList = new ArrayList<LogDayXhMediaProgramAnalysis>();
//		if (from==2) {
//			nativeVideoAnalysisList=logDayXhMediaProgramAnalysisService.getNativeVideoDetail(record);
//			recommendVideoAnalysisList=logDayXhMediaProgramAnalysisService.getRecommendVideoDetail(record);
//		}else{
//			nativeVideoAnalysisList=logDayXhMediaProgramAnalysisService.getNativeProgramDetail(record);
//			recommendVideoAnalysisList=logDayXhMediaProgramAnalysisService.getRecommendProgramDetail(record);
//		}
//		boolean flag=false;
//		for (LogDayXhMediaProgramAnalysis recommendAnalysis : recommendVideoAnalysisList) {
//			 for (LogDayXhMediaProgramAnalysis nativeAnalysis : nativeVideoAnalysisList) {
//				 flag=false;
//				 if (nativeAnalysis.getGlobalId().equals(recommendAnalysis.getSource())) {
//					 nativeAnalysis.setPlayTime(nativeAnalysis.getPlayTime()+recommendAnalysis.getPlayTime());
//					 nativeAnalysis.setTimeLength(nativeAnalysis.getTimeLength()+recommendAnalysis.getTimeLength());
//					 nativeAnalysis.setPlayNumber(nativeAnalysis.getPlayNumber()+recommendAnalysis.getPlayNumber());
//					 nativeAnalysis.setPlayUserNumber(nativeAnalysis.getPlayUserNumber()+recommendAnalysis.getPlayUserNumber());
//					 nativeAnalysis.setPv(nativeAnalysis.getPv()+recommendAnalysis.getPv());
//					 nativeAnalysis.setUv(nativeAnalysis.getUv()+recommendAnalysis.getUv());
//					 String nativeip = nativeAnalysis.getIp();
//					 String recomip = recommendAnalysis.getIp();
//					 int ip=Integer.parseInt(nativeip)+Integer.parseInt(recomip);
//					 nativeAnalysis.setIp(Integer.toString(ip));
//					 flag=true;
//					 break;
//					}
//				}
//			 if (!flag) {
//				 mergeVideoAnalysisList.add(recommendAnalysis);
//			}
//		}
//		for (LogDayXhMediaProgramAnalysis nativeAnalysis : nativeVideoAnalysisList) {
//			mergeVideoAnalysisList.add(nativeAnalysis);
//		}
//        if (from==2) {
//        	videoListSort(mergeVideoAnalysisList);
//		}else{
//			programListSort(mergeVideoAnalysisList);
//		}
//		return mergeVideoAnalysisList;
//	}

	/**
	 * 把视频详情页合并后的数据进行排序
	 * @param mergeVideoAnalysisList
	 */
	public void videoListSort(List<LogDayXhMediaProgramAnalysis> mergeVideoAnalysisList){
		Collections.sort(mergeVideoAnalysisList,new Comparator<LogDayXhMediaProgramAnalysis>(){
			@Override
			public int compare(LogDayXhMediaProgramAnalysis o1,LogDayXhMediaProgramAnalysis o2) {
				try {
                    if (o1.getPlayTime() > o2.getPlayTime()) {
                        return -1;
                    } else if (o1.getPlayTime() < o2.getPlayTime()) {
                        return 1;
                    } else {
                        return 0;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
				return 0;
			}
		}
	  );
	}
	
	/**
	 * 把节目详情页合并后的数据进行排序
	 * @param mergeVideoAnalysisList
	 */
	public void programListSort(List<LogDayXhMediaProgramAnalysis> mergeVideoAnalysisList){
		Collections.sort(mergeVideoAnalysisList,new Comparator<LogDayXhMediaProgramAnalysis>(){
			@Override
			public int compare(LogDayXhMediaProgramAnalysis o1,LogDayXhMediaProgramAnalysis o2) {
				try {
                    if (o1.getPv() > o2.getPv()) {
                        return -1;
                    } else if (o1.getPv() < o2.getPv()) {
                        return 1;
                    } else {
                        return 0;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
				return 0;
			}
		}
	  );
	}
	
	/**
	 * 把数据写入Excel表格中并保存在服务器的指定目录中
	 * @param arg0
	 * @param arg1              
	 * @param visitListForExcel
	 * @param visitDayListForExcel
	 */
	private void export2Excel(HttpServletRequest arg0,HttpServletResponse arg1, 
			List<LogXhMediaProgramAnalysis> videoAnalysisListForExcel, List<LogDayXhMediaProgramAnalysis> videoAnalysisDayListForExcel,String from){
		try {
			if (from.equals("2")) {
				String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");
				String docsPath = arg0.getSession().getServletContext().getRealPath("docs");
				String fileName = "视频详情" + System.currentTimeMillis() + ".xlsx";
				String filePath = docsPath + FILE_SEPARATOR + fileName;
		 
				if (videoAnalysisListForExcel.size()>0) {
					// 输出流
					OutputStream os = new FileOutputStream(filePath);
					// 工作区
					XSSFWorkbook wb = new XSSFWorkbook();
					XSSFSheet sheet = wb.createSheet("视频详情");
					XSSFRow rowTitle = sheet.createRow(0);
					rowTitle.createCell(0).setCellValue("日期");
					rowTitle.createCell(1).setCellValue("小时");
					rowTitle.createCell(2).setCellValue("站点");
					rowTitle.createCell(3).setCellValue("渠道");
					rowTitle.createCell(4).setCellValue("频道");
					rowTitle.createCell(5).setCellValue("节目名称");
					rowTitle.createCell(6).setCellValue("播放次数");
					rowTitle.createCell(7).setCellValue("播放总时长");
					rowTitle.createCell(8).setCellValue("播放人数");
					rowTitle.createCell(9).setCellValue("节目总时长");
					rowTitle.createCell(10).setCellValue("播放完成度%");
					rowTitle.createCell(11).setCellValue("发布时间");
					for (int i = 0; i < videoAnalysisListForExcel.size(); i++) {
						// 创建第一个sheet
						// 生成第一行
						XSSFRow row = sheet.createRow(i+1);
						// 给这一行的第一列赋值
						row.createCell(0).setCellValue(videoAnalysisListForExcel.get(i).getDate());
						row.createCell(1).setCellValue(videoAnalysisListForExcel.get(i).getHour());
						row.createCell(2).setCellValue(videoAnalysisListForExcel.get(i).getSiteCode());
						row.createCell(3).setCellValue(videoAnalysisListForExcel.get(i).getOriginName());
						row.createCell(4).setCellValue(videoAnalysisListForExcel.get(i).getChannelName());
						row.createCell(5).setCellValue(videoAnalysisListForExcel.get(i).getChineseName());
						row.createCell(6).setCellValue(videoAnalysisListForExcel.get(i).getPlayNumber());
						row.createCell(7).setCellValue(videoAnalysisListForExcel.get(i).getPlayTime());
						row.createCell(8).setCellValue(videoAnalysisListForExcel.get(i).getPlayUserNumber());
						row.createCell(9).setCellValue(videoAnalysisListForExcel.get(i).getTimeLength());
						row.createCell(10).setCellValue(videoAnalysisListForExcel.get(i).getAlreadyPlay());
						row.createCell(11).setCellValue(videoAnalysisListForExcel.get(i).getPubDate());
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
					XSSFSheet sheet = wb.createSheet("视频详情");
					XSSFRow rowTitle = sheet.createRow(0);
					rowTitle.createCell(0).setCellValue("日期");
					rowTitle.createCell(1).setCellValue("站点");
					rowTitle.createCell(2).setCellValue("渠道");
					rowTitle.createCell(3).setCellValue("频道");
					rowTitle.createCell(4).setCellValue("节目名称");
					rowTitle.createCell(5).setCellValue("播放次数");
					rowTitle.createCell(6).setCellValue("播放总时长");
					rowTitle.createCell(7).setCellValue("播放人数");
					rowTitle.createCell(8).setCellValue("节目总时长");
					rowTitle.createCell(9).setCellValue("播放完成度%");
					rowTitle.createCell(10).setCellValue("发布时间");
					for (int i = 0; i < videoAnalysisDayListForExcel.size(); i++) {
						// 创建第一个sheet
						// 生成第一行
						XSSFRow row = sheet.createRow(i+1);
						// 给这一行的第一列赋值
						row.createCell(0).setCellValue(videoAnalysisDayListForExcel.get(i).getDate());
						row.createCell(1).setCellValue(videoAnalysisDayListForExcel.get(i).getSiteCode());
						row.createCell(2).setCellValue(videoAnalysisDayListForExcel.get(i).getOriginName());
						row.createCell(3).setCellValue(videoAnalysisDayListForExcel.get(i).getChannelName());
						row.createCell(4).setCellValue(videoAnalysisDayListForExcel.get(i).getChineseName());
						row.createCell(5).setCellValue(videoAnalysisDayListForExcel.get(i).getPlayNumber());
						row.createCell(6).setCellValue(videoAnalysisDayListForExcel.get(i).getPlayTime());
						row.createCell(7).setCellValue(videoAnalysisDayListForExcel.get(i).getPlayUserNumber());
						row.createCell(8).setCellValue(videoAnalysisDayListForExcel.get(i).getTimeLength());
						row.createCell(9).setCellValue(videoAnalysisDayListForExcel.get(i).getAlreadyPlay());
						row.createCell(10).setCellValue(videoAnalysisDayListForExcel.get(i).getPubDate());
					}
					// 写文件
					wb.write(os);
					// 关闭输出流
					os.close();
				}
			}else{
				String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");
				String docsPath = arg0.getSession().getServletContext().getRealPath("docs");
				String fileName = "节目详情" + System.currentTimeMillis() + ".xlsx";
				String filePath = docsPath + FILE_SEPARATOR + fileName;
		 
				if (videoAnalysisListForExcel.size()>0) {
					// 输出流
					OutputStream os = new FileOutputStream(filePath);
					// 工作区
					XSSFWorkbook wb = new XSSFWorkbook();
					XSSFSheet sheet = wb.createSheet("节目详情");
					XSSFRow rowTitle = sheet.createRow(0);
					rowTitle.createCell(0).setCellValue("日期");
					rowTitle.createCell(1).setCellValue("小时");
					rowTitle.createCell(2).setCellValue("站点");
					rowTitle.createCell(3).setCellValue("渠道");
					rowTitle.createCell(4).setCellValue("频道");
					rowTitle.createCell(5).setCellValue("节目名称");
					rowTitle.createCell(6).setCellValue("PV");
					rowTitle.createCell(7).setCellValue("UV");
					rowTitle.createCell(8).setCellValue("IP");
					rowTitle.createCell(9).setCellValue("发布时间");
					for (int i = 0; i < videoAnalysisListForExcel.size(); i++) {
						// 创建第一个sheet
						// 生成第一行
						XSSFRow row = sheet.createRow(i+1);
						// 给这一行的第一列赋值
						row.createCell(0).setCellValue(videoAnalysisListForExcel.get(i).getDate());
						row.createCell(1).setCellValue(videoAnalysisListForExcel.get(i).getHour());
						row.createCell(2).setCellValue(videoAnalysisListForExcel.get(i).getSiteCode());
						row.createCell(3).setCellValue(videoAnalysisListForExcel.get(i).getOriginName());
						row.createCell(4).setCellValue(videoAnalysisListForExcel.get(i).getChannelName());
						row.createCell(5).setCellValue(videoAnalysisListForExcel.get(i).getChineseName());
						row.createCell(6).setCellValue(videoAnalysisListForExcel.get(i).getPv());
						row.createCell(7).setCellValue(videoAnalysisListForExcel.get(i).getUv());
						row.createCell(8).setCellValue(videoAnalysisListForExcel.get(i).getIp());
						row.createCell(9).setCellValue(videoAnalysisListForExcel.get(i).getPubDate());
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
					XSSFSheet sheet = wb.createSheet("节目详情");
					XSSFRow rowTitle = sheet.createRow(0);
					rowTitle.createCell(0).setCellValue("日期");
					rowTitle.createCell(1).setCellValue("站点");
					rowTitle.createCell(2).setCellValue("渠道");
					rowTitle.createCell(3).setCellValue("频道");
					rowTitle.createCell(4).setCellValue("节目名称");
					rowTitle.createCell(5).setCellValue("PV");
					rowTitle.createCell(6).setCellValue("UV");
					rowTitle.createCell(7).setCellValue("IP");
					rowTitle.createCell(8).setCellValue("发布时间");
					for (int i = 0; i < videoAnalysisDayListForExcel.size(); i++) {
						// 创建第一个sheet
						// 生成第一行
						XSSFRow row = sheet.createRow(i+1);
						// 给这一行的第一列赋值
						row.createCell(0).setCellValue(videoAnalysisDayListForExcel.get(i).getDate());
						row.createCell(1).setCellValue(videoAnalysisDayListForExcel.get(i).getSiteCode());
						row.createCell(2).setCellValue(videoAnalysisDayListForExcel.get(i).getOriginName());
						row.createCell(3).setCellValue(videoAnalysisDayListForExcel.get(i).getChannelName());
						row.createCell(4).setCellValue(videoAnalysisDayListForExcel.get(i).getChineseName());
						row.createCell(5).setCellValue(videoAnalysisDayListForExcel.get(i).getPv());
						row.createCell(6).setCellValue(videoAnalysisDayListForExcel.get(i).getUv());
						row.createCell(7).setCellValue(videoAnalysisDayListForExcel.get(i).getIp());
						row.createCell(8).setCellValue(videoAnalysisDayListForExcel.get(i).getPubDate());
					}
					// 写文件
					wb.write(os);
					// 关闭输出流
					os.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
//		download(filePath,arg1);
	}
}
