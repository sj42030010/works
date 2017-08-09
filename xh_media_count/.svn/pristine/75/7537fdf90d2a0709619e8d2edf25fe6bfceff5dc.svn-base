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
import wap.util.Util;

import com.google.gson.Gson;
import com.xh.media.model.LogDayXhMediaInteractive;
import com.xh.media.model.LogDayXhMediaUser;
import com.xh.media.model.LogXhMediaInteractive;
import com.xh.media.model.LogXhMediaUser;
import com.xh.media.model.LogXhMediaVisit;
import com.xh.media.model.SysGroups;
import com.xh.media.service.LogDayXhMediaInteractiveService;
import com.xh.media.service.LogXhMediaInteractiveService;
@Controller
public class InteractiveController extends AbstractController {
	private ApplicationContext context;
	private Gson gson;
	private LogXhMediaInteractiveService logXhMediaInteractiveService;
    private LogDayXhMediaInteractiveService  logDayXhMediaInteractiveService;
	public InteractiveController() {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		gson = new Gson();
		logXhMediaInteractiveService = (LogXhMediaInteractiveService) context.getBean("logXhMediaInteractiveService");
		logDayXhMediaInteractiveService=(LogDayXhMediaInteractiveService) context.getBean("logDayXhMediaInteractiveService");
	}

	@RequestMapping("/interactive")
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0,
			HttpServletResponse arg1) {

		try {
			String sort = "";
			String order = "";
			if(arg0.getParameter("sort")!=null){
				sort = arg0.getParameter("sort");
			}else{
				sort = "COMMENT_NUMBER";
			}
			if(arg0.getParameter("order")!=null){
				order = arg0.getParameter("order");
			}else{
				order = "desc";
			}
			int  action = 0;
			if(arg0.getParameter("action") != null)
				action = Integer.parseInt(arg0.getParameter("action"));
			String originId = "";
			if (arg0.getParameter("originId") != null)
				originId = arg0.getParameter("originId");
			String channelId = "";
			if (arg0.getParameter("channelId") != null)
				channelId = arg0.getParameter("channelId");
			String startTime = "";
			if (arg0.getParameter("startTime") != null)
				startTime = arg0.getParameter("startTime");
			String endTime = "";
			if (arg0.getParameter("endTime") != null)
				endTime = arg0.getParameter("endTime");
			String resultStr = "";
			Map map = new HashMap();
			List<LogXhMediaInteractive> interactiveList = new ArrayList<LogXhMediaInteractive>();
			List<LogDayXhMediaInteractive> interactiveDayList = new ArrayList<LogDayXhMediaInteractive>();
			List<SysGroups> groupList = (List<SysGroups>) arg0.getSession().getAttribute("groups");
			List sitcodList=new ArrayList();
			for (SysGroups sysGroups : groupList) {
				sitcodList.add(sysGroups.getId());
			}
			
			String[] soStr = sort.split(",");
			String[] orStr = order.split(",");
			List orderList=new ArrayList();
			for(int i=0;i<soStr.length;i++){
				if("commentNumber".equals(soStr[i])){
					orderList.add("comment_Number "+orStr[i]);
				}else if("topicNumber".equals(soStr[i])){
					orderList.add("topic_Number "+orStr[i]);
				}else if ("collectionNumber".equals(soStr[i])) {
					orderList.add("collection_Number "+orStr[i]);
				}else if ("shareNumber".equals(soStr[i])) {
					orderList.add("share_Number "+orStr[i]);
				}else{
					orderList.add(soStr[i]+" "+orStr[i]);
				}
			}
			switch (action) {
			case 1:
				if ("".equals(originId) && "".equals(channelId)&& "".equals(startTime) && "".equals(endTime)) {
					LogDayXhMediaInteractive record=new LogDayXhMediaInteractive();
					if (sitcodList.size()>0) {
						record.setSitcodList(sitcodList);
					}
					record.setOrderList(orderList);
					interactiveDayList = logDayXhMediaInteractiveService.findSumInteractiveList(record);
					map.put("total", interactiveDayList.size());
					map.put("rows", interactiveDayList);
				} else {
					if ("".equals(endTime)) {
						Timestamp ts = new Timestamp(System.currentTimeMillis());
						String nowTime = ts + "";
						endTime = nowTime.substring(0, 10);
					}
					if ("".equals(startTime)) {
						startTime = "1900-01-01";
					}
//					if (startTime.equals(endTime)) {
//							LogXhMediaInteractive record = new LogXhMediaInteractive();
//							if (!"".equals(originId)){
//								record.setSiteCode(originId);
//							}
//							if (!"".equals(channelId)){
//								record.setChannelId(channelId);
//							}
//						    if (sitcodList.size()>0) {
//								record.setSitcodList(sitcodList);
//							}
//							record.setStartTime(startTime);
//							record.setEndTime(endTime);
//							record.setOrderList(orderList);
//					        interactiveList = logXhMediaInteractiveService.getInteractiveBySearchOneDay(record);	
//					        map.put("total", interactiveList.size());
//							map.put("rows", interactiveList);
//					}else{
						LogDayXhMediaInteractive recordDay = new LogDayXhMediaInteractive();
						if (!"".equals(originId)){
							recordDay.setSiteCode(originId);
						}
						if (!"".equals(channelId)){
							recordDay.setChannelId(channelId);
						}
						  if (sitcodList.size()>0) {
								recordDay.setSitcodList(sitcodList);
							}
						recordDay.setStartTime(startTime);
						recordDay.setEndTime(endTime);
						recordDay.setOrderList(orderList);
					   interactiveDayList = logDayXhMediaInteractiveService.getInteractiveBySearch(recordDay);
					   map.put("total", interactiveDayList.size());
					   map.put("rows", interactiveDayList);
//					}
					
				}
				break;
			case 2:
				if ("".equals(originId) && "".equals(channelId)&& "".equals(startTime) && "".equals(endTime)) {
					LogDayXhMediaInteractive record=new LogDayXhMediaInteractive();
					if (sitcodList.size()>0) {
						record.setSitcodList(sitcodList);
					}
					interactiveDayList = logDayXhMediaInteractiveService.findSumInteractiveListForChart(record);
					map.put("total", interactiveDayList.size());
					map.put("rows", interactiveDayList);
				} else {
					if ("".equals(startTime)) {
						startTime = "1900-01-01";
					}
					if ("".equals(endTime)) {
						Timestamp ts = new Timestamp(System.currentTimeMillis());
						String nowTime = ts + "";
						endTime = nowTime.substring(0, 10);
					}
					if (startTime.equals(endTime)) {
						LogXhMediaInteractive record = new LogXhMediaInteractive();
						if (!"".equals(originId)){
							record.setSiteCode(originId);
						}
						if (!"".equals(channelId)){
							record.setChannelId(channelId);
						}
						 if (sitcodList.size()>0) {
								record.setSitcodList(sitcodList);
							}
						record.setStartTime(startTime);
						record.setEndTime(endTime);
					interactiveList = logXhMediaInteractiveService.getInteractiveBySearchOneDayForChart(record);
					interactiveList=fillUpZero(interactiveList);
					map.put("total", interactiveList.size());
					map.put("rows", interactiveList);
					}else{
						LogDayXhMediaInteractive recordDay = new LogDayXhMediaInteractive();
						if (!"".equals(originId))
							recordDay.setSiteCode(originId);
						if (!"".equals(channelId))
							recordDay.setChannelId(channelId);
					    if (sitcodList.size()>0) {
							recordDay.setSitcodList(sitcodList);
						}
						recordDay.setStartTime(startTime);
						recordDay.setEndTime(endTime);
					interactiveDayList = logDayXhMediaInteractiveService.getInteractiveBySearchForChart(recordDay);
					map.put("total", interactiveDayList.size());
					map.put("rows", interactiveDayList);
					}
					
				}
				break;
			case 3:
				if ("".equals(originId) && "".equals(channelId)&& "".equals(startTime) && "".equals(endTime)) {
					LogDayXhMediaInteractive record=new LogDayXhMediaInteractive();
					if (sitcodList.size()>0) {
						record.setSitcodList(sitcodList);
					}
					record.setOrderList(orderList);
					interactiveDayList = logDayXhMediaInteractiveService.findSumInteractiveList(record);
					map.put("total", interactiveDayList.size());
					map.put("rows", interactiveDayList);
				} else {
					if ("".equals(endTime)) {
						Timestamp ts = new Timestamp(System.currentTimeMillis());
						String nowTime = ts + "";
						endTime = nowTime.substring(0, 10);
					}
					if ("".equals(startTime)) {
						startTime = "1900-01-01";
					}
					if (startTime.equals(endTime)) {
							LogXhMediaInteractive record = new LogXhMediaInteractive();
							if (!"".equals(originId)){
								record.setSiteCode(originId);
							}
							if (!"".equals(channelId)){
								record.setChannelId(channelId);
							}
						    if (sitcodList.size()>0) {
								record.setSitcodList(sitcodList);
							}
							record.setStartTime(startTime);
							record.setEndTime(endTime);
							record.setOrderList(orderList);
					        interactiveList = logXhMediaInteractiveService.getInteractiveBySearchOneDay(record);	
					        map.put("total", interactiveList.size());
							map.put("rows", interactiveList);
					}else{
						LogDayXhMediaInteractive recordDay = new LogDayXhMediaInteractive();
						if (!"".equals(originId)){
							recordDay.setSiteCode(originId);
						}
						if (!"".equals(channelId)){
							recordDay.setChannelId(channelId);
						}
						  if (sitcodList.size()>0) {
								recordDay.setSitcodList(sitcodList);
							}
						recordDay.setStartTime(startTime);
						recordDay.setEndTime(endTime);
						recordDay.setOrderList(orderList);
					   interactiveDayList = logDayXhMediaInteractiveService.getInteractiveBySearch(recordDay);
					   map.put("total", interactiveDayList.size());
					   map.put("rows", interactiveDayList);
					}
				}
				export2Excel(arg0,arg1,interactiveList,interactiveDayList);
				break;
			case 4:
				List<LogDayXhMediaInteractive> selectInteractiveDataSum = logDayXhMediaInteractiveService.selectInteractiveDataSum();
				   map.put("total", selectInteractiveDataSum.size());
				   map.put("rows", selectInteractiveDataSum);
				break;
			default:
				break;
			}
			if (action!=3) {
				resultStr = gson.toJson(map);
				arg1.setCharacterEncoding("UTF-8");
				arg1.getWriter().write(resultStr);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取数据列表详情
	 * @param request
	 * @param response
	 */
	@RequestMapping("/detailData")
	public void getDetailData(HttpServletRequest request,HttpServletResponse response){
		String date="";
		if (request.getParameter("date")!=null) {
			date=request.getParameter("date");
		}
		String siteCode="";
		if (request.getParameter("siteCode")!=null) {
			siteCode=request.getParameter("siteCode");
		}
		String channelId="";
		if (request.getParameter("channelId")!=null) {
			channelId=request.getParameter("channelId");
		}
		String sort = "";
		String order = "";
		if(request.getParameter("sort")!=null){
			sort = request.getParameter("sort");
		}else{
			sort = "COMMENT_NUMBER";
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
			if("commentNumber".equals(soStr[i])){
				orderList.add("comment_Number "+orStr[i]);
			}else if("topicNumber".equals(soStr[i])){
				orderList.add("topic_Number "+orStr[i]);
			}else if ("collectionNumber".equals(soStr[i])) {
				orderList.add("collection_Number "+orStr[i]);
			}else if ("shareNumber".equals(soStr[i])) {
				orderList.add("share_Number "+orStr[i]);
			}else{
				orderList.add(soStr[i]+" "+orStr[i]);
			}
		}
		LogXhMediaInteractive record=new LogXhMediaInteractive();
		if (!"".equals(date)) {
			record.setDate(date);
		}
		if (!"".equals(siteCode)) {
			record.setSiteCode(siteCode);
		}
		if (!"".equals(channelId)) {
			record.setOriginId(channelId);
		}
		record.setOrderList(orderList);
		List<LogXhMediaInteractive> interactiveDetail = logXhMediaInteractiveService.getInteractiveDetail(record);
		Map map= new HashMap();
		map.put("total", interactiveDetail.size());
		map.put("rows", interactiveDetail);
		String resultStr = gson.toJson(map);
		try {
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(resultStr);
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取折线图详情
	 * @param request
	 * @param response
	 */
	@RequestMapping("/chartDetail")
	public void getDetailChart(HttpServletRequest request,HttpServletResponse response){
		String date="";
		if (request.getParameter("date")!=null) {
			date=request.getParameter("date");
		}
		String siteCode="";
		if (request.getParameter("siteCode")!=null) {
			siteCode=request.getParameter("siteCode");
		}
		String channelId="";
		if (request.getParameter("channelId")!=null) {
			channelId=request.getParameter("channelId");
		}
		String sort = "";
		String order = "";
		if(request.getParameter("sort")!=null){
			sort = request.getParameter("sort");
		}else{
			sort = "COMMENT_NUMBER";
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
			if("commentNumber".equals(soStr[i])){
				orderList.add("comment_Number "+orStr[i]);
			}else if("topicNumber".equals(soStr[i])){
				orderList.add("topic_Number "+orStr[i]);
			}else if ("collectionNumber".equals(soStr[i])) {
				orderList.add("collection_Number "+orStr[i]);
			}else if ("shareNumber".equals(soStr[i])) {
				orderList.add("share_Number "+orStr[i]);
			}else{
				orderList.add(soStr[i]+" "+orStr[i]);
			}
		}
		LogXhMediaInteractive record=new LogXhMediaInteractive();
		if (!"".equals(date)) {
			record.setDate(date);
		}
		if (!"".equals(siteCode)) {
			record.setSiteCode(siteCode);
		}
		if (!"".equals(channelId)) {
			record.setOriginId(channelId);
		}
		record.setOrderList(orderList);
		 List<LogXhMediaInteractive> interactiveChartDetail = logXhMediaInteractiveService.getInteractiveChartDetail(record);
		 
		  List<LogXhMediaInteractive> fillUpZero = fillUpZero(interactiveChartDetail);
			Map map= new HashMap();
			map.put("total", fillUpZero.size());
			map.put("rows", fillUpZero);
			try {
				response.setCharacterEncoding("UTF-8");
				String resultStr = gson.toJson(map);
				response.getWriter().write(resultStr);
					response.getWriter().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	}
	
	private List<LogXhMediaInteractive> fillUpZero(List<LogXhMediaInteractive> interactiveList) {
		List<LogXhMediaInteractive> minutesList=new ArrayList<LogXhMediaInteractive>();
		for (int i = 0; i < Key.hours.length; i++) {
			LogXhMediaInteractive interactive1=new LogXhMediaInteractive();
			interactive1.setMinute("00");
			interactive1.setHour(Key.hours[i]);
			interactive1.setCommentNumber(0);
			interactive1.setTopicNumber(0);
			interactive1.setCollectionNumber(0);
			interactive1.setShareNumber(0);
			minutesList.add(interactive1);
			for (int j = 0; j < Key.minutes.length; j++) {
				LogXhMediaInteractive interactive=new LogXhMediaInteractive();
				interactive.setMinute(Key.minutes[j]);
				interactive.setHour(Key.hours[i]+":"+Key.minutes[j]);
				interactive.setCommentNumber(0);
				interactive.setTopicNumber(0);
				interactive.setCollectionNumber(0);
				interactive.setShareNumber(0);
				minutesList.add(interactive);
			}
		}
		for (LogXhMediaInteractive logXhMediaInteractive : interactiveList) {
			for (LogXhMediaInteractive minutesListInteractive : minutesList) {
				if (logXhMediaInteractive.getHour().equals(minutesListInteractive.getHour().substring(0,2))&&logXhMediaInteractive.getMinute().equals(minutesListInteractive.getMinute())) {
					minutesListInteractive.setCommentNumber(logXhMediaInteractive.getCommentNumber());
					minutesListInteractive.setCollectionNumber(logXhMediaInteractive.getCollectionNumber());
					minutesListInteractive.setTopicNumber(logXhMediaInteractive.getTopicNumber());
					minutesListInteractive.setShareNumber(logXhMediaInteractive.getShareNumber());
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
			List<LogXhMediaInteractive> interactiveList, List<LogDayXhMediaInteractive> interactiveDayList){
		String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");
		String docsPath = arg0.getSession().getServletContext().getRealPath("docs");
		String fileName = "交互分析" + System.currentTimeMillis() + ".xlsx";
		String filePath = docsPath + FILE_SEPARATOR + fileName;
	 try {
			if (interactiveList.size()>0) {
				// 输出流
				OutputStream os = new FileOutputStream(filePath);
				// 工作区
				XSSFWorkbook wb = new XSSFWorkbook();
				XSSFSheet sheet = wb.createSheet("交互分析");
				XSSFRow rowTitle = sheet.createRow(0);
				rowTitle.createCell(0).setCellValue("日期");
				rowTitle.createCell(1).setCellValue("小时");
				rowTitle.createCell(2).setCellValue("站点");
				rowTitle.createCell(3).setCellValue("渠道");
				rowTitle.createCell(4).setCellValue("频道");
				rowTitle.createCell(5).setCellValue("节目名称");
				rowTitle.createCell(6).setCellValue("评论");
				rowTitle.createCell(7).setCellValue("话题");
				rowTitle.createCell(8).setCellValue("收藏");
				rowTitle.createCell(9).setCellValue("分享");
				for (int i = 0; i < interactiveList.size(); i++) {
					// 创建第一个sheet
					// 生成第一行
					XSSFRow row = sheet.createRow(i+1);
					// 给这一行的第一列赋值
					row.createCell(0).setCellValue(interactiveList.get(i).getDate());
					row.createCell(1).setCellValue(interactiveList.get(i).getHour());
					row.createCell(2).setCellValue(interactiveList.get(i).getSiteCode());
					row.createCell(3).setCellValue(interactiveList.get(i).getOriginName());
					row.createCell(4).setCellValue(interactiveList.get(i).getChannelName());
					row.createCell(5).setCellValue(interactiveList.get(i).getChineseName());
					row.createCell(6).setCellValue(interactiveList.get(i).getCommentNumber());
					row.createCell(7).setCellValue(interactiveList.get(i).getTopicNumber());
					row.createCell(8).setCellValue(interactiveList.get(i).getCollectionNumber());
					row.createCell(9).setCellValue(interactiveList.get(i).getShareNumber());
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
				XSSFSheet sheet = wb.createSheet("交互分析");
				XSSFRow rowTitle = sheet.createRow(0);
				rowTitle.createCell(0).setCellValue("日期");
				rowTitle.createCell(1).setCellValue("站点");
				rowTitle.createCell(2).setCellValue("渠道");
				rowTitle.createCell(3).setCellValue("频道");
				rowTitle.createCell(4).setCellValue("节目名称");
				rowTitle.createCell(5).setCellValue("评论");
				rowTitle.createCell(6).setCellValue("话题");
				rowTitle.createCell(7).setCellValue("收藏");
				rowTitle.createCell(8).setCellValue("分享");
				for (int i = 0; i < interactiveDayList.size(); i++) {
					// 创建第一个sheet
					// 生成第一行
					XSSFRow row = sheet.createRow(i+1);
					// 给这一行的第一列赋值
					row.createCell(0).setCellValue(interactiveDayList.get(i).getDate());
					row.createCell(1).setCellValue(interactiveDayList.get(i).getSiteCode());
					row.createCell(2).setCellValue(interactiveDayList.get(i).getOriginName());
					row.createCell(3).setCellValue(interactiveDayList.get(i).getChannelName());
					row.createCell(4).setCellValue(interactiveDayList.get(i).getChineseName());
					row.createCell(5).setCellValue(interactiveDayList.get(i).getCommentNumber());
					row.createCell(6).setCellValue(interactiveDayList.get(i).getTopicNumber());
					row.createCell(7).setCellValue(interactiveDayList.get(i).getCollectionNumber());
					row.createCell(8).setCellValue(interactiveDayList.get(i).getShareNumber());
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
