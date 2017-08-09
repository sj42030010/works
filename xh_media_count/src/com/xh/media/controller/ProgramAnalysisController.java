package com.xh.media.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
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

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.xh.media.model.LogDayXhMediaProgramAnalysis;
import com.xh.media.model.LogXhMediaProgramAnalysis;
import com.xh.media.model.LogXhMediaVisit;
import com.xh.media.model.SysGroups;
import com.xh.media.service.LogDayXhMediaProgramAnalysisService;
import com.xh.media.service.LogXhMediaProgramAnalysisService;
@Controller
public class ProgramAnalysisController extends AbstractController{
	private Gson gson;
	private ApplicationContext context;
	private LogXhMediaProgramAnalysisService logXhMediaProgramAnalysisService;
	private LogDayXhMediaProgramAnalysisService logDayXhMediaProgramAnalysisService;
	public ProgramAnalysisController() {
		 gson=new Gson();
		 context=new ClassPathXmlApplicationContext("applicationContext.xml");
		 logXhMediaProgramAnalysisService = (LogXhMediaProgramAnalysisService) context.getBean("logXhMediaProgramAnalysisService");
		 logDayXhMediaProgramAnalysisService=(LogDayXhMediaProgramAnalysisService) context.getBean("logDayXhMediaProgramAnalysisService");
	}

	@RequestMapping("/program_analysis")
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
				sort = "pv";
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
				if("pubDate".equals(soStr[i])){
					orderList.add("pub_Date "+orStr[i]);
				}else{
					orderList.add(soStr[i]+" "+orStr[i]);
				}
			}
			
			switch (action) {
			case 1:
				if("".equals(originId)&&"".equals(channelId)&&"".equals(startTime)&&"".equals(endTime)){
					LogDayXhMediaProgramAnalysis record=new LogDayXhMediaProgramAnalysis();
                    if (sitcodList.size()>0) {
                    	record.setSitcodList(sitcodList);
					}
                    record.setOrderList(orderList);
					videoAnalysisDayList=logDayXhMediaProgramAnalysisService.getSummaryProgramAnalysisList(record);
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
//					if (startTime.equals(endTime)) {
//						LogXhMediaProgramAnalysis record = new LogXhMediaProgramAnalysis();
//						if(!"".equals(originId)){
//							record.setSiteCode(originId);
//						}
//						if(!"".equals(channelId)){
//							record.setChannelId(channelId);
//						}
//						record.setStartTime(startTime);
//						record.setEndTime(endTime);	
//						 if (sitcodList.size()>0) {
//		                    	record.setSitcodList(sitcodList);
//							}
//						if (!"".equals(globalId)) {
//						    	record.setGlobalId(globalId);
//							}
//						record.setOrderList(orderList);
//					    videoAnalysisList = logXhMediaProgramAnalysisService.getProgramAnalysisListBySearchOneDay(record);
//					    map.put("total", videoAnalysisList.size());
//						map.put("rows", videoAnalysisList);
//					}else{
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
						if (!"".equals(globalId)) {
						    	recordDay.setGlobalId(globalId);
							}
						recordDay.setStartTime(startTime);
						recordDay.setEndTime(endTime);
						recordDay.setOrderList(orderList);
					   videoAnalysisDayList = logDayXhMediaProgramAnalysisService.getProgramAnalysisListBySearch(recordDay);
					   map.put("total", videoAnalysisDayList.size());
					   map.put("rows", videoAnalysisDayList);
					}
//				}	
				break;
			case 2:
				if("".equals(originId)&&"".equals(channelId)&&"".equals(globalId)&&"".equals(startTime)&&"".equals(endTime)){
					LogDayXhMediaProgramAnalysis record=new LogDayXhMediaProgramAnalysis();
					if (sitcodList.size()>0) {
						record.setSitcodList(sitcodList);
					}
					videoAnalysisDayList=logDayXhMediaProgramAnalysisService.getProgramAnalysisListForCharts(record);
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
						videoAnalysisList = logXhMediaProgramAnalysisService.getProgramAnalysisListBySearchOneDayForCharts(record);
						videoAnalysisList=fillUpZero(videoAnalysisList);
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
						if (!"".equals(globalId)) {
								recordDay.setGlobalId(globalId);
							}
					    videoAnalysisDayList = logDayXhMediaProgramAnalysisService.getProgramAnalysisListBySearchForCharts(recordDay);
					    map.put("total", videoAnalysisDayList.size());
						map.put("rows", videoAnalysisDayList);
					}
				}
				break;
			case 3:
				if("".equals(originId)&&"".equals(channelId)&&"".equals(globalId)&&"".equals(startTime)&&"".equals(endTime)){
					LogDayXhMediaProgramAnalysis record=new LogDayXhMediaProgramAnalysis();
					if (sitcodList.size()>0) {
						record.setSitcodList(sitcodList);
					}
					videoAnalysisDayList=logDayXhMediaProgramAnalysisService.getProgramListForChartPie(record);
					List<LogDayXhMediaProgramAnalysis> videoAnalysisDayList1=new ArrayList<LogDayXhMediaProgramAnalysis>();
					int pv=0;
					int uv=0;
					for (int i = 0; i < videoAnalysisDayList.size(); i++) {
						if (i<10) {
							videoAnalysisDayList1.add(videoAnalysisDayList.get(i));
						}else{
							pv+=videoAnalysisDayList.get(i).getPv();
							uv+=videoAnalysisDayList.get(i).getUv();
						}
					}
					if (pv>0) {
						record.setChannelName("其他");
						record.setPv(pv);
						record.setUv(uv);
						videoAnalysisDayList1.add(record);
					}
					map.put("total", videoAnalysisDayList1.size());
					map.put("rows", videoAnalysisDayList1);	
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
						 if (sitcodList.size()>0) {
		                    	record.setSitcodList(sitcodList);
							}
						if (!"".equals(globalId)) {
								record.setGlobalId(globalId);
							}
						record.setStartTime(startTime);
						record.setEndTime(endTime);
						videoAnalysisList = logXhMediaProgramAnalysisService.getProgramAnalysisListBySearchOneDayForChartsPie(record);
						List<LogXhMediaProgramAnalysis> videoAnalysisDayList1=new ArrayList<LogXhMediaProgramAnalysis>();
						int pv=0;
						int uv=0;
						for (int i = 0; i < videoAnalysisList.size(); i++) {
							if (i<10) {
								videoAnalysisDayList1.add(videoAnalysisList.get(i));
							}else{
								pv+=videoAnalysisList.get(i).getPv();
								uv+=videoAnalysisList.get(i).getUv();
							}
						}
						if (pv>0) {
							record.setChannelName("其他");
							record.setPv(pv);
							record.setUv(uv);
							videoAnalysisDayList1.add(record);
						}
						if (videoAnalysisList.size()>0) {
							if (videoAnalysisDayList1.get(0)==null) {
								map.put("rows", null);
							}else{
								map.put("rows", videoAnalysisDayList1);
							}
						}
						map.put("total", videoAnalysisDayList1.size());
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
					    if (!"".equals(globalId)) {
								recordDay.setGlobalId(globalId);
							}
						videoAnalysisDayList = logDayXhMediaProgramAnalysisService.getProgramListBySearchForChartPie(recordDay);
						List<LogDayXhMediaProgramAnalysis> videoAnalysisDayList1=new ArrayList<LogDayXhMediaProgramAnalysis>();
						int pv=0;
						int uv=0;
						for (int i = 0; i < videoAnalysisDayList.size(); i++) {
							if (i<10) {
								videoAnalysisDayList1.add(videoAnalysisDayList.get(i));
							}else{
								pv+=videoAnalysisDayList.get(i).getPv();
								uv+=videoAnalysisDayList.get(i).getUv();
							}
						}
						if (pv>0) {
							recordDay.setChannelName("其他");
							recordDay.setPv(pv);
							recordDay.setUv(uv);
							videoAnalysisDayList1.add(recordDay);
						}
						if (videoAnalysisDayList1.size()>0) {
							if (videoAnalysisDayList1.get(0)==null) {
								map.put("rows", null);
							}else{
								map.put("rows", videoAnalysisDayList1);
							}
						}
						map.put("total", videoAnalysisDayList1.size());
					}
				}
				break;
			case 4:
				if("".equals(originId)&&"".equals(channelId)&&"".equals(startTime)&&"".equals(endTime)){
					LogDayXhMediaProgramAnalysis record=new LogDayXhMediaProgramAnalysis();
                    if (sitcodList.size()>0) {
                    	record.setSitcodList(sitcodList);
					}
					videoAnalysisDayList=logDayXhMediaProgramAnalysisService.getSummaryProgramAnalysisList(record);
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
					    videoAnalysisList = logXhMediaProgramAnalysisService.getProgramAnalysisListBySearchOneDay(record);
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
						 if (sitcodList.size()>0) {
		                    	recordDay.setSitcodList(sitcodList);
							}
						recordDay.setStartTime(startTime);
						recordDay.setEndTime(endTime);
					   videoAnalysisDayList = logDayXhMediaProgramAnalysisService.getProgramAnalysisListBySearch(recordDay);
					   map.put("total", videoAnalysisDayList.size());
					   map.put("rows", videoAnalysisDayList);
					}
					
				}	
				export2Excel(arg0,arg1,videoAnalysisList,videoAnalysisDayList);
				break;
			case 5:
				List<LogDayXhMediaProgramAnalysis> selectProgramDataSum = logDayXhMediaProgramAnalysisService.selectProgramDataSum();
				   map.put("total", selectProgramDataSum.size());
				   map.put("rows", selectProgramDataSum);
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

	private List<LogXhMediaProgramAnalysis> fillUpZero(List<LogXhMediaProgramAnalysis> videoAnalysisList) {
		List<LogXhMediaProgramAnalysis>  minutesList=new ArrayList<LogXhMediaProgramAnalysis>();
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
		return minutesList;
	}

//	/**
//	 * 获取合并后的视频分析列表
//	 * @return
//	 */
//	private List<LogDayXhMediaProgramAnalysis> getVideoAnalysisList(LogDayXhMediaProgramAnalysis record) {
//		List<LogDayXhMediaProgramAnalysis> mergeVideoAnalysisList = new ArrayList<LogDayXhMediaProgramAnalysis>();//合并后的数据集合
//		List<LogDayXhMediaProgramAnalysis> nativeVideoAnalysisList = logDayXhMediaProgramAnalysisService.getNativeProgramAnalysisList(record);
//		
//		List<LogDayXhMediaProgramAnalysis> recommendVideoAnalysisList = logDayXhMediaProgramAnalysisService.getRecommendProgramAnalysisList(record);
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
//		listSort(mergeVideoAnalysisList);
//		return mergeVideoAnalysisList;
//	}
	
	/**
	 * 把合并后的数据进行排序
	 * @param mergeVideoAnalysisList
	 */
	public void listSort(List<LogDayXhMediaProgramAnalysis> mergeVideoAnalysisList){
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
			List<LogXhMediaProgramAnalysis> videoAnalysisListForExcel, List<LogDayXhMediaProgramAnalysis> videoAnalysisDayListForExcel){
		String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");
		String docsPath = arg0.getSession().getServletContext().getRealPath("docs");
		String fileName = "节目分析明细" + System.currentTimeMillis() + ".xlsx";
		String filePath = docsPath + FILE_SEPARATOR + fileName;
	 try {
			if (videoAnalysisListForExcel.size()>0) {
				// 输出流
				OutputStream os = new FileOutputStream(filePath);
				// 工作区
				XSSFWorkbook wb = new XSSFWorkbook();
				XSSFSheet sheet = wb.createSheet("节目分析明细");
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
				XSSFSheet sheet = wb.createSheet("节目分析明细");
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
