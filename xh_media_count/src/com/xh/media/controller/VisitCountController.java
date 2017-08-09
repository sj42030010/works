package com.xh.media.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
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
import wap.util.Util;

import com.google.gson.Gson;
import com.xh.media.model.LogDayXhMediaVisit;
import com.xh.media.model.LogXhMediaVisit;
import com.xh.media.model.SysGroups;
import com.xh.media.service.LogDayXhMediaVisitService;
import com.xh.media.service.LogXhMediaVisitService;
@Controller
public class VisitCountController {
	private ApplicationContext context;
	private LogXhMediaVisitService logXhMediaVisitService;
	private LogDayXhMediaVisitService logDayXhMediaVisitService;
	private Gson gson;
	
	public VisitCountController() {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		logXhMediaVisitService=(LogXhMediaVisitService) context.getBean("logXhMediaVisitService");
		logDayXhMediaVisitService=(LogDayXhMediaVisitService) context.getBean("logDayXhMediaVisitService");
	    gson=new Gson();
	}
	
	@RequestMapping("/visit")
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0,
			HttpServletResponse arg1){
		try {
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
			int action=0;
			if(arg0.getParameter("action") != null)
				action = Integer.parseInt(arg0.getParameter("action"));
			String originId = "";
			if(arg0.getParameter("originId") != null)//在action 1 2 3 中这个是站点标识 在action4 中这个是渠道ID
				originId = arg0.getParameter("originId");
			String channelId = "";
			if(arg0.getParameter("channelId") != null)//在在action 1 2 3 中这个是渠道标识 在action4 中这个是频道ID
				channelId = arg0.getParameter("channelId");
			String startTime = "";
			if(arg0.getParameter("startTime")!=null)
				startTime = arg0.getParameter("startTime");
			String endTime = "";
			if(arg0.getParameter("endTime")!=null)
				endTime = arg0.getParameter("endTime");
			String date = "";
			if(arg0.getParameter("date")!=null)
				date = arg0.getParameter("date");
			String siteCode="";
			if(arg0.getParameter("siteCode")!=null)//只在action4中是站点标识
				siteCode = arg0.getParameter("siteCode");
			String partId="";
			if(arg0.getParameter("partId")!=null)
				partId = arg0.getParameter("partId");
			String resultStr = "";
			Map map = new HashMap();
			List<SysGroups> groupList = (List<SysGroups>) arg0.getSession().getAttribute("groups");
			List sitcodList=new ArrayList();
			for (SysGroups sysGroups : groupList) {
				sitcodList.add(sysGroups.getId());
			}
			
			String[] soStr = sort.split(",");
			String[] orStr = order.split(",");
			List orderList=new ArrayList();
			for(int i=0;i<soStr.length;i++){
				if("playTime".equals(soStr[i])){
					orderList.add("play_time "+orStr[i]);
				}else if("nonLocal".equals(soStr[i])){
					orderList.add("non_local "+orStr[i]);
				}else{
					orderList.add(soStr[i]+" "+orStr[i]);
				}
			}
			switch(action){
			case 1://初次进入获取数据列表的数据，以及数据列表的搜素
				List<LogXhMediaVisit> visitList = new ArrayList<LogXhMediaVisit>();
				List<LogDayXhMediaVisit> visitDayList = new ArrayList<LogDayXhMediaVisit>();
				if("".equals(siteCode)&&"".equals(originId)&&"".equals(channelId)&&"".equals(partId)&&"".equals(startTime)&&"".equals(endTime)){
					LogDayXhMediaVisit logDayXhMediaVisit=new LogDayXhMediaVisit();
					if (sitcodList.size()>0) {
						logDayXhMediaVisit.setSitcodList(sitcodList);
					}
					
		           logDayXhMediaVisit.setOrderList(orderList);
					visitDayList = logDayXhMediaVisitService.getSumVisitList(logDayXhMediaVisit);
					for (LogDayXhMediaVisit Visit : visitDayList) {
						Visit.setNonLocal(Visit.getNonLocal()+Visit.getLocal());
					}
					map.put("total", visitDayList.size());
					map.put("rows", visitDayList);
				}else{
					LogXhMediaVisit record = new LogXhMediaVisit();
					LogDayXhMediaVisit recordDay = new LogDayXhMediaVisit();
					if(!"".equals(siteCode)){
						record.setSiteCode(siteCode);
					    recordDay.setSiteCode(siteCode);
					}
					if(!"".equals(originId)){
						record.setOriginId(originId);
					    recordDay.setOriginId(originId);
					}
					if(!"".equals(channelId)){
						record.setChannelId(channelId);
					    recordDay.setChannelId(channelId);
					}
					if(!"".equals(partId)){
						record.setPartId(partId);
					    recordDay.setPartId(partId);
					}
					if("".equals(startTime)){
						startTime = "1900-01-01";
					}	
					if("".equals(endTime)){
						Timestamp ts = new Timestamp(System.currentTimeMillis());
						String nowTime = ts+"";
						endTime = nowTime.substring(0,10);
					}
					record.setOrderList(orderList);
					record.setStartTime(startTime);
					record.setEndTime(endTime);
					if (sitcodList.size()>0) {
						record.setSitcodList(sitcodList);
					}
					recordDay.setOrderList(orderList);
					recordDay.setStartTime(startTime);
					recordDay.setEndTime(endTime);
					
					if (sitcodList.size()>0) {
						recordDay.setSitcodList(sitcodList);
					}
					visitDayList = logDayXhMediaVisitService.getVisitListBySearch(recordDay);
					for (LogDayXhMediaVisit Visit : visitDayList) {
						Visit.setNonLocal(Visit.getNonLocal()+Visit.getLocal());
					}
					map.put("total", visitDayList.size());
					map.put("rows", visitDayList);
				}
				resultStr = gson.toJson(map);
				arg1.setCharacterEncoding("UTF-8");
				arg1.getWriter().write(resultStr);
				break;
			case 2://获取折线图的数据，以及折线图的搜索
				if("".equals(originId)&&"".equals(channelId)&&"".equals(startTime)&&"".equals(endTime)){
				LogDayXhMediaVisit logDayXhMediaVisit=new LogDayXhMediaVisit();
				if (sitcodList.size()>0) {
					logDayXhMediaVisit.setSitcodList(sitcodList);
				}
				List<LogDayXhMediaVisit> visitList1 = logDayXhMediaVisitService.getSumVisitListForCharts(logDayXhMediaVisit);
				List<LogDayXhMediaVisit> chartList = new ArrayList<LogDayXhMediaVisit>();
				for (LogDayXhMediaVisit logXhMediaVisit : visitList1) {
					LogDayXhMediaVisit bean=new LogDayXhMediaVisit();
					bean.setPv(logXhMediaVisit.getPv());
					bean.setUv(logXhMediaVisit.getUv());
					bean.setIp(logXhMediaVisit.getIp());
					bean.setDate(logXhMediaVisit.getDate());
					chartList.add(bean);
				}
				map.put("total", chartList.size());
				map.put("rows", chartList);
				}else{
					LogXhMediaVisit record = new LogXhMediaVisit();
					LogDayXhMediaVisit recordDay = new LogDayXhMediaVisit();
					if(!"".equals(siteCode)){
						record.setSiteCode(siteCode);
					    recordDay.setSiteCode(siteCode);
					}
					if(!"".equals(originId)){
						record.setOriginId(originId);
					    recordDay.setOriginId(originId);
					}
					if(!"".equals(channelId)){
						record.setChannelId(channelId);
					    recordDay.setChannelId(channelId);
					}
					if(!"".equals(partId)){
						record.setPartId(partId);
					    recordDay.setPartId(partId);
					}
					if("".equals(startTime)){
						startTime = "1900-01-01";
						
					}	
					if("".equals(endTime)){
						Timestamp ts = new Timestamp(System.currentTimeMillis());
						String nowTime = ts+"";
						endTime = nowTime.substring(0,10);
					}
					record.setStartTime(startTime);
					record.setEndTime(endTime);
					if (sitcodList.size()>0) {
						record.setSitcodList(sitcodList);
					}
					recordDay.setStartTime(startTime);
					recordDay.setEndTime(endTime);
					if (sitcodList.size()>0) {
						recordDay.setSitcodList(sitcodList);
					}
					if (startTime.equals(endTime)) {
						visitList = logXhMediaVisitService.getVisitListBySearchOneDayForCharts(record);
						visitList=fillUpZero(visitList);
						map.put("total", visitList.size());
						map.put("rows", visitList);
					}else{
						visitDayList = logDayXhMediaVisitService.getVisitListBySearchForCharts(recordDay);
						map.put("total", visitDayList.size());
						map.put("rows", visitDayList);
					}
				}
				resultStr = gson.toJson(map);
				arg1.setCharacterEncoding("UTF-8");
				arg1.getWriter().write(resultStr);
				break;
			case 3://获取Excel导出的数据
				List<LogXhMediaVisit> visitListForExcel = new ArrayList<LogXhMediaVisit>();
				List<LogDayXhMediaVisit> visitDayListForExcel = new ArrayList<LogDayXhMediaVisit>();
				if("".equals(originId)&&"".equals(channelId)&&"".equals(startTime)&&"".equals(endTime)){
					LogDayXhMediaVisit logDayXhMediaVisit=new LogDayXhMediaVisit();
					if (sitcodList.size()>0) {
						logDayXhMediaVisit.setSitcodList(sitcodList);
					}
		           logDayXhMediaVisit.setOrderList(orderList);
					visitDayListForExcel = logDayXhMediaVisitService.getSumVisitList(logDayXhMediaVisit);
					for (LogDayXhMediaVisit Visit : visitDayListForExcel) {
						Visit.setNonLocal(Visit.getNonLocal()+Visit.getLocal());
					}
				}else{
					LogXhMediaVisit record = new LogXhMediaVisit();
					LogDayXhMediaVisit recordDay = new LogDayXhMediaVisit();
					if(!"".equals(siteCode)){
						record.setSiteCode(siteCode);
					    recordDay.setSiteCode(siteCode);
					}
					if(!"".equals(originId)){
						record.setOriginId(originId);
					    recordDay.setOriginId(originId);
					}
					if(!"".equals(channelId)){
						record.setChannelId(channelId);
					    recordDay.setChannelId(channelId);
					}
					if(!"".equals(partId)){
						record.setPartId(partId);
					    recordDay.setPartId(partId);
					}
					if("".equals(startTime)){
						startTime = "1900-01-01";
					}	
					if("".equals(endTime)){
						Timestamp ts = new Timestamp(System.currentTimeMillis());
						String nowTime = ts+"";
						endTime = nowTime.substring(0,10);
					}
					record.setOrderList(orderList);
					record.setStartTime(startTime);
					record.setEndTime(endTime);
					if (sitcodList.size()>0) {
						record.setSitcodList(sitcodList);
					}
					recordDay.setOrderList(orderList);
					recordDay.setStartTime(startTime);
					recordDay.setEndTime(endTime);
					
					if (sitcodList.size()>0) {
						recordDay.setSitcodList(sitcodList);
					}
					if (startTime.equals(endTime)) {
						visitListForExcel = logXhMediaVisitService.getVisitListBySearchOneDay(record);
					}else{
						visitDayListForExcel = logDayXhMediaVisitService.getVisitListBySearch(recordDay);
					}
				}
				export2Excel(arg0,arg1,visitListForExcel,visitDayListForExcel);
				break;
			case 4://详情页列表数据提取
				LogXhMediaVisit record = new LogXhMediaVisit();	
				if(!"".equals(date)){
					record.setDate(date);
				}
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
					record.setChannelId(partId);
				}
				record.setOrderList(orderList);
				
				List<LogXhMediaVisit> visitListDetail = logXhMediaVisitService.getVisitListDetail(record);
				for (LogXhMediaVisit Visit : visitListDetail) {
					Visit.setNonLocal(Visit.getNonLocal()+Visit.getLocal());
				}
				map.put("total", visitListDetail.size());
				map.put("rows", visitListDetail);
				resultStr = gson.toJson(map);
				arg1.setCharacterEncoding("UTF-8");
				arg1.getWriter().write(resultStr);
				break;
			case 5://详情页折线图
				LogXhMediaVisit recordchart = new LogXhMediaVisit();	
				if(!"".equals(date)){
					recordchart.setDate(date);
				}
				if(!"".equals(siteCode)){
					recordchart.setSiteCode(siteCode);
				}
				if(!"".equals(originId)){
					recordchart.setOriginId(originId);
				}
				if(!"".equals(channelId)){
					recordchart.setChannelId(channelId);
				}
				if(!"".equals(partId)){
					recordchart.setChannelId(partId);
				}
				List<LogXhMediaVisit> visitListChartDetail = logXhMediaVisitService.getVisitListBySearchOneDayForCharts(recordchart);
				List<LogXhMediaVisit> fillUpZero = fillUpZero(visitListChartDetail);
				
				
				map.put("total", fillUpZero.size());
				map.put("rows", fillUpZero);
				resultStr = gson.toJson(map);
				arg1.setCharacterEncoding("UTF-8");
				arg1.getWriter().write(resultStr);
				break;
			case 6://获取汇总数据
				List<LogXhMediaVisit> visitSumData = logXhMediaVisitService.selectDataSum();
				map.put("total", visitSumData.size());
				map.put("rows", visitSumData);
				resultStr = gson.toJson(map);
				arg1.setCharacterEncoding("UTF-8");
				arg1.getWriter().write(resultStr);
				break;
			default:
				break;
			}
//			resultStr = gson.toJson(map);
//			arg1.setCharacterEncoding("UTF-8");
//			arg1.getWriter().write(resultStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	/**
	 * 缺数补零
	 * @param visitList
	 */
	private List<LogXhMediaVisit> fillUpZero(List<LogXhMediaVisit> visitList) {
		List<LogXhMediaVisit>  minutesList=new ArrayList<LogXhMediaVisit>();
		for (int i = 0; i < Key.hours.length; i++) {
				LogXhMediaVisit visit1=new LogXhMediaVisit();
				visit1.setMinute("00");
				visit1.setPv(0);
				visit1.setUv(0);
				visit1.setIp(0);
				visit1.setHour(Key.hours[i]);
				minutesList.add(visit1);
				for (int j = 0; j < Key.minutes.length; j++) {
					LogXhMediaVisit visit=new LogXhMediaVisit();
					visit.setMinute(Key.minutes[j]);
					visit.setPv(0);
					visit.setUv(0);
					visit.setIp(0);
					visit.setHour(Key.hours[i]+":"+Key.minutes[j]);
					minutesList.add(visit);
				}
		}
		for (LogXhMediaVisit logXhMediaVisit : visitList) {
			for (LogXhMediaVisit muinutesVisit : minutesList) {
					if (logXhMediaVisit.getHour().equals(muinutesVisit.getHour().substring(0,2))&&logXhMediaVisit.getMinute().equals(muinutesVisit.getMinute())) {
						muinutesVisit.setPv(logXhMediaVisit.getPv());
						muinutesVisit.setUv(logXhMediaVisit.getUv());
						muinutesVisit.setIp(logXhMediaVisit.getIp());
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
			List<LogXhMediaVisit> visitListForExcel, List<LogDayXhMediaVisit> visitDayListForExcel){
		String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");
		String docsPath = arg0.getSession().getServletContext().getRealPath("docs");
		String fileName = "访问统计表" + System.currentTimeMillis() + ".xlsx";
		String filePath = docsPath + FILE_SEPARATOR + fileName;
	 try {
		
			if (visitListForExcel.size()>0) {
				// 输出流
				OutputStream os = new FileOutputStream(filePath);
				// 工作区
				XSSFWorkbook wb = new XSSFWorkbook();
				XSSFSheet sheet = wb.createSheet("访问统计表");
				XSSFRow rowTitle = sheet.createRow(0);
				rowTitle.createCell(0).setCellValue("日期");
				rowTitle.createCell(1).setCellValue("小时");
				rowTitle.createCell(2).setCellValue("站点");
				rowTitle.createCell(3).setCellValue("渠道");
				rowTitle.createCell(4).setCellValue("频道");
				rowTitle.createCell(5).setCellValue("PV");
				rowTitle.createCell(6).setCellValue("UV");
				rowTitle.createCell(7).setCellValue("IP");
				rowTitle.createCell(8).setCellValue("播放总时长");
				rowTitle.createCell(9).setCellValue("站内访问");
				rowTitle.createCell(10).setCellValue("站外访问");
				for (int i = 0; i < visitListForExcel.size(); i++) {
					// 创建第一个sheet
					// 生成第一行
					XSSFRow row = sheet.createRow(i+1);
					// 给这一行的第一列赋值
					row.createCell(0).setCellValue(visitListForExcel.get(i).getDate());
					row.createCell(1).setCellValue(visitListForExcel.get(i).getHour());
					row.createCell(2).setCellValue(visitListForExcel.get(i).getSiteCode());
					row.createCell(3).setCellValue(visitListForExcel.get(i).getOriginName());
					row.createCell(4).setCellValue(visitListForExcel.get(i).getChannelName());
					row.createCell(5).setCellValue(visitListForExcel.get(i).getPv());
					row.createCell(6).setCellValue(visitListForExcel.get(i).getUv());
					row.createCell(7).setCellValue(visitListForExcel.get(i).getIp());
					row.createCell(8).setCellValue(visitListForExcel.get(i).getPlayTime());
					row.createCell(9).setCellValue(visitListForExcel.get(i).getNonLocal());
					row.createCell(10).setCellValue(visitListForExcel.get(i).getLocal());
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
				XSSFSheet sheet = wb.createSheet("访问统计表");
				XSSFRow rowTitle = sheet.createRow(0);
				rowTitle.createCell(0).setCellValue("日期");
				rowTitle.createCell(1).setCellValue("站点");
				rowTitle.createCell(2).setCellValue("渠道");
				rowTitle.createCell(3).setCellValue("频道");
				rowTitle.createCell(4).setCellValue("PV");
				rowTitle.createCell(5).setCellValue("UV");
				rowTitle.createCell(6).setCellValue("IP");
				rowTitle.createCell(7).setCellValue("播放总时长");
				rowTitle.createCell(8).setCellValue("站内访问");
				rowTitle.createCell(9).setCellValue("站外访问");
				for (int i = 0; i < visitDayListForExcel.size(); i++) {
					// 创建第一个sheet
					// 生成第一行
					XSSFRow row = sheet.createRow(i+1);
					// 给这一行的第一列赋值
					row.createCell(0).setCellValue(visitDayListForExcel.get(i).getDate());
					row.createCell(1).setCellValue(visitDayListForExcel.get(i).getSiteCode());
					row.createCell(2).setCellValue(visitDayListForExcel.get(i).getOriginName());
					row.createCell(3).setCellValue(visitDayListForExcel.get(i).getChannelName());
					row.createCell(4).setCellValue(visitDayListForExcel.get(i).getPv());
					row.createCell(5).setCellValue(visitDayListForExcel.get(i).getUv());
					row.createCell(6).setCellValue(visitDayListForExcel.get(i).getIp());
					row.createCell(7).setCellValue(visitDayListForExcel.get(i).getPlayTime());
					row.createCell(8).setCellValue(visitDayListForExcel.get(i).getNonLocal());
					row.createCell(9).setCellValue(visitDayListForExcel.get(i).getLocal());
				}
				// 写文件
				wb.write(os);
				// 关闭输出流
				os.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Util.download(filePath,arg1);
	}

}
