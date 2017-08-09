package com.xh.media.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
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
import com.xh.media.model.LogDayXhMediaVisit;
import com.xh.media.model.LogXhMediaProgramAnalysis;
import com.xh.media.model.LogXhMediaVisit;
import com.xh.media.model.SysGroups;
import com.xh.media.service.LogDayXhMediaProgramAnalysisService;
import com.xh.media.service.LogXhMediaProgramAnalysisService;
@Controller
public class VideoAnalysisController extends AbstractController{
	private Gson gson;
	private ApplicationContext context;
	private LogXhMediaProgramAnalysisService logXhMediaProgramAnalysisService;
	private LogDayXhMediaProgramAnalysisService logDayXhMediaProgramAnalysisService;
	public VideoAnalysisController() {
		 gson=new Gson();
		 context=new ClassPathXmlApplicationContext("applicationContext.xml");
		 logXhMediaProgramAnalysisService = (LogXhMediaProgramAnalysisService) context.getBean("logXhMediaProgramAnalysisService");
		 logDayXhMediaProgramAnalysisService=(LogDayXhMediaProgramAnalysisService) context.getBean("logDayXhMediaProgramAnalysisService");
	}

	@RequestMapping("/video_analysis")
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0,HttpServletResponse arg1){
		try {
			int action=0;
			if(arg0.getParameter("action") != null)
				action = Integer.parseInt(arg0.getParameter("action"));
			String originId = "";
			if(arg0.getParameter("originId") != null)
				originId = arg0.getParameter("originId");
			String channelId = "";
			if(arg0.getParameter("channelId") != null)
				channelId = arg0.getParameter("channelId");
			String globalId ="";
			if (arg0.getParameter("globalId")!=null) 
			   globalId=arg0.getParameter("globalId");
			String startTime = "";
			if(arg0.getParameter("startTime")!=null)
				startTime = arg0.getParameter("startTime");
			String endTime = "";
			if(arg0.getParameter("endTime")!=null)
				endTime = arg0.getParameter("endTime");
			String siteCode="";
			if(arg0.getParameter("siteCode")!=null)
				siteCode = arg0.getParameter("siteCode");
			String partId="";
			if(arg0.getParameter("partId")!=null)
				partId = arg0.getParameter("partId");
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
				sort = "play_Number";
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
				if("".equals(originId)&&"".equals(channelId)&&"".equals(globalId)&&"".equals(startTime)&&"".equals(endTime)){
					LogDayXhMediaProgramAnalysis record=new LogDayXhMediaProgramAnalysis();
				    if (sitcodList.size()>0) {
				    	record.setSitcodList(sitcodList);
					}
				    record.setOrderList(orderList);
					videoAnalysisDayList=logDayXhMediaProgramAnalysisService.getSummaryVideoAnalysisList(record);
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
						if(!"".equals(siteCode)){
							record.setSiteCode(siteCode);
						}
						if(!"".equals(originId)){
							record.setOriginId(originId);
						}
						if(!"".equals(channelId)){
							record.setChannelId(channelId);
						}
						if(!"".equals(partId)){
							record.setPartId(partId);
						}
						record.setStartTime(startTime);
						record.setEndTime(endTime);
					    if (sitcodList.size()>0) {
							record.setSitcodList(sitcodList);
						}
					    if (!"".equals(globalId)) {
					    	record.setGlobalId(globalId);
						}
					    record.setOrderList(orderList);
					    videoAnalysisList = logXhMediaProgramAnalysisService.getVideoAnalysisListBySearchOneDay(record);
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
						if(!"".equals(siteCode)){
							recordDay.setSiteCode(siteCode);
						}
						if(!"".equals(originId)){
							recordDay.setOriginId(originId);
						}
						if(!"".equals(channelId)){
							recordDay.setChannelId(channelId);
						}
						if(!"".equals(partId)){
							recordDay.setPartId(partId);
						}
						recordDay.setStartTime(startTime);
						recordDay.setEndTime(endTime);
						recordDay.setOrderList(orderList);
						if (sitcodList.size()>0) {
		                    	recordDay.setSitcodList(sitcodList);
							}
						if (!"".equals(globalId)) {
							  recordDay.setGlobalId(globalId);
							}
						videoAnalysisDayList = logDayXhMediaProgramAnalysisService.getVideoAnalysisListBySearch(recordDay);
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
				if("".equals(originId)&&"".equals(channelId)&&"".equals(globalId)&&"".equals(startTime)&&"".equals(endTime)){
					LogDayXhMediaProgramAnalysis record=new LogDayXhMediaProgramAnalysis();
                    if (sitcodList.size()>0) {
                    	record.setSitcodList(sitcodList);
					}
					videoAnalysisDayList=logDayXhMediaProgramAnalysisService.getVideoAnalysisListForCharts(record);
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
						if(!"".equals(siteCode)){
							record.setSiteCode(siteCode);
						}
						if(!"".equals(originId)){
							record.setOriginId(originId);
						}
						if(!"".equals(channelId)){
							record.setChannelId(channelId);
						}
						if(!"".equals(partId)){
							record.setPartId(partId);
						}
						if (sitcodList.size()>0) {
		                    	record.setSitcodList(sitcodList);
							}
						if (!"".equals(globalId)) {
							record.setGlobalId(globalId);
						}
						record.setStartTime(startTime);
						record.setEndTime(endTime);
					videoAnalysisList = logXhMediaProgramAnalysisService.getVideoAnalysisListBySearchOneDayForCharts(record);
					for (LogXhMediaProgramAnalysis logXhMediaProgramAnalysis : videoAnalysisList) {
						if("0".equals(logXhMediaProgramAnalysis.getTimeLength()+"")){
							logXhMediaProgramAnalysis.setAlreadyPlay("0");
						}else{
							int temp=logXhMediaProgramAnalysis.getPlayTime()*100/logXhMediaProgramAnalysis.getTimeLength();
							logXhMediaProgramAnalysis.setAlreadyPlay(temp+"");
						}
					}
					videoAnalysisList=fillUpZero(videoAnalysisList);
					map.put("total", videoAnalysisList.size());
					map.put("rows", videoAnalysisList);
					}else{
						LogDayXhMediaProgramAnalysis recordDay = new LogDayXhMediaProgramAnalysis();
						if(!"".equals(siteCode)){
							recordDay.setSiteCode(siteCode);
						}
						if(!"".equals(originId)){
							recordDay.setOriginId(originId);
						}
						if(!"".equals(channelId)){
							recordDay.setChannelId(channelId);
						}
						if(!"".equals(partId)){
							recordDay.setPartId(partId);
						}
						if (sitcodList.size()>0) {
		                    	recordDay.setSitcodList(sitcodList);
							}
						recordDay.setStartTime(startTime);
						recordDay.setEndTime(endTime);
					videoAnalysisDayList = logDayXhMediaProgramAnalysisService.getVideoAnalysisListBySearchForCharts(recordDay);
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
				if("".equals(originId)&&"".equals(channelId)&&"".equals(startTime)&&"".equals(endTime)){
					LogDayXhMediaProgramAnalysis record=new LogDayXhMediaProgramAnalysis();
				    if (sitcodList.size()>0) {
				    	record.setSitcodList(sitcodList);
					}
					videoAnalysisDayList=logDayXhMediaProgramAnalysisService.getSummaryVideoAnalysisList(record);
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
							if(!"".equals(originId)){
								record.setSiteCode(originId);
							}
							if(!"".equals(channelId)){
								record.setChannelId(channelId);
							}
							record.setStartTime(startTime);
							record.setEndTime(endTime);
						    if (sitcodList.size()>0) {
								record.setSitcodList(sitcodList);
							}
						    videoAnalysisList = logXhMediaProgramAnalysisService.getVideoAnalysisListBySearchOneDay(record);
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
							if(!"".equals(originId)){
								recordDay.setSiteCode(originId);
							}
							if(!"".equals(channelId)){
								recordDay.setChannelId(channelId);
							}
							recordDay.setStartTime(startTime);
							recordDay.setEndTime(endTime);
							if (sitcodList.size()>0) {
			                    	recordDay.setSitcodList(sitcodList);
								}
							videoAnalysisDayList = logDayXhMediaProgramAnalysisService.getVideoAnalysisListBySearch(recordDay);
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
				export2Excel(arg0,arg1,videoAnalysisList,videoAnalysisDayList);
				break;
			case 4://获取汇总数据
				List<LogDayXhMediaProgramAnalysis> selectVideoDataSum = logDayXhMediaProgramAnalysisService.selectVideoDataSum();
				map.put("total", selectVideoDataSum.size());
				map.put("rows", selectVideoDataSum);
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

	private List<LogXhMediaProgramAnalysis> fillUpZero(List<LogXhMediaProgramAnalysis> videoAnalysisDayList) {
		List<LogXhMediaProgramAnalysis>  minutesList=new ArrayList<LogXhMediaProgramAnalysis>();
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
		for (LogXhMediaProgramAnalysis logXhMediaVisit : videoAnalysisDayList) {
			for (LogXhMediaProgramAnalysis muinutesVisit : minutesList) {
					if (logXhMediaVisit.getHour().equals(muinutesVisit.getHour().substring(0,2))&&logXhMediaVisit.getMinute().equals(muinutesVisit.getMinute())) {
						muinutesVisit.setPlayTime(logXhMediaVisit.getPlayTime());
						muinutesVisit.setPlayNumber(logXhMediaVisit.getPlayNumber());
						muinutesVisit.setPlayUserNumber(logXhMediaVisit.getPlayUserNumber());
						muinutesVisit.setTimeLength(logXhMediaVisit.getTimeLength());
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
			List<LogXhMediaProgramAnalysis> videoAnalysisListForExcel, List<LogDayXhMediaProgramAnalysis> videoAnalysisDayListForExcel){
		String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");
		String docsPath = arg0.getSession().getServletContext().getRealPath("docs");
		String fileName = "视频分析明细" + System.currentTimeMillis() + ".xlsx";
		String filePath = docsPath + FILE_SEPARATOR + fileName;
	 try {
			if (videoAnalysisListForExcel.size()>0) {
				// 输出流
				OutputStream os = new FileOutputStream(filePath);
				// 工作区
				XSSFWorkbook wb = new XSSFWorkbook();
				XSSFSheet sheet = wb.createSheet("视频分析明细");
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
				XSSFSheet sheet = wb.createSheet("视频分析明细");
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
		} catch (Exception e) {
			e.printStackTrace();
		}
//		download(filePath,arg1);
	}
	
}
