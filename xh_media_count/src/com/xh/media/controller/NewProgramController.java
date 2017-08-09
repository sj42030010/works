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
import com.xh.media.model.IndexXhMediaChannel;
import com.xh.media.model.IndexXhMediaProgram;
import com.xh.media.model.LogDayXhMediaNewProgram;
import com.xh.media.model.LogXhMediaNewProgram;
import com.xh.media.model.SysGroups;
import com.xh.media.service.IndexXhMediaChannelService;
import com.xh.media.service.IndexXhMediaOriginService;
import com.xh.media.service.IndexXhMediaProgramService;
import com.xh.media.service.LogDayXhMediaNewProgramService;
import com.xh.media.service.LogXhMediaNewProgramService;
import com.xh.media.service.SysGroupsService;

@Controller
public class NewProgramController {
	
	private ApplicationContext context;
	private Gson gson;
	private LogDayXhMediaNewProgramService logDayXhMediaNewProgramService;
	private LogXhMediaNewProgramService  logXhMediaNewProgramService;
	private SysGroupsService sysGroupsService;
	private IndexXhMediaOriginService indexXhMediaOriginService;
	private IndexXhMediaChannelService indexXhMediaChannelService;
	private IndexXhMediaProgramService indexXhMediaProgramService;
	public NewProgramController() {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		gson = new Gson();
		logDayXhMediaNewProgramService=(LogDayXhMediaNewProgramService) context.getBean("logDayXhMediaNewProgramService");
		logXhMediaNewProgramService=(LogXhMediaNewProgramService) context.getBean("logXhMediaNewProgramService");
		sysGroupsService = (SysGroupsService)context.getBean("sysGroupsService");
		indexXhMediaOriginService = (IndexXhMediaOriginService)context.getBean("indexXhMediaOriginService");
		indexXhMediaChannelService = (IndexXhMediaChannelService)context.getBean("indexXhMediaChannelService");
		indexXhMediaProgramService = (IndexXhMediaProgramService)context.getBean("indexXhMediaProgramService");
	}

	/**
	 * 获取节目统计列表数据
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping("/getNewProgram")
	public void getProgramData(HttpServletRequest request,HttpServletResponse response) throws IOException{
		LogDayXhMediaNewProgram ldxmnp = new LogDayXhMediaNewProgram();
		ldxmnp = initData(request);
		
		if (request.getParameter("startTime")!=null&&!"".equals(request.getParameter("startTime"))) {
			ldxmnp.setStartTime(request.getParameter("startTime"));
		}else{
			ldxmnp.setStartTime("1900-01-01");
		}	
		if (request.getParameter("endTime")!=null&&!"".equals(request.getParameter("endTime"))) {
			ldxmnp.setEndTime(request.getParameter("endTime"));
		}else{
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			String nowTime = String.valueOf(ts);
			ldxmnp.setEndTime(nowTime.substring(0, 10));
		}
		
		List<LogDayXhMediaNewProgram> newProgramList = new ArrayList<LogDayXhMediaNewProgram>();
		
		List<SysGroups> groupList = (List<SysGroups>) request.getSession().getAttribute("groups");
		List sitecodeList = new ArrayList();
		for (SysGroups sysGroups : groupList) {
			sitecodeList.add(sysGroups.getId());
		}
		
		ldxmnp.setSitcodList(sitecodeList);
		String sort = "";
		String order = "";
		if(request.getParameter("sort")!=null){
			sort = request.getParameter("sort");
		}else{
			sort = "play_number";
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
			if("playNumber".equals(soStr[i])){
				orderList.add("play_number "+orStr[i]);
			}else if("playUserNumber".equals(soStr[i])){
				orderList.add("play_user_number "+orStr[i]);
			}else if("commentNumber".equals(soStr[i])){
				orderList.add("comment_number "+orStr[i]);
			}else if("collectionNumber".equals(soStr[i])){
				orderList.add("collection_number "+orStr[i]);
			}else if("shareNumber".equals(soStr[i])){
				orderList.add("share_number "+orStr[i]);
			}else if("timeLength".equals(soStr[i])){
				orderList.add("time_length "+orStr[i]);
			}else if("pubDate".equals(soStr[i])){
				orderList.add("pub_date "+orStr[i]);
			}else if("siteName".equals(soStr[i])){
				orderList.add("site_code "+orStr[i]);
			}else if("channelName".equals(soStr[i])){
				orderList.add("channel_id "+orStr[i]);
			}else if("partName".equals(soStr[i])){
				orderList.add("part_id "+orStr[i]);
			}else if("originName".equals(soStr[i])){
				orderList.add("origin_id "+orStr[i]);
			}else if("programName".equals(soStr[i])){
				orderList.add("global_id "+orStr[i]);
			}else{
				orderList.add(soStr[i]+" "+orStr[i]);
			}
		}
		ldxmnp.setOrderList(orderList);
		newProgramList = logDayXhMediaNewProgramService.searchNewProgramListNew(ldxmnp);
		for (LogDayXhMediaNewProgram logDayXhMediaNewProgram : newProgramList) {
			int playUserNumber = logDayXhMediaNewProgram.getPlayUserNumber();
			int timeLength = logDayXhMediaNewProgram.getTimeLength();
//			if (playUserNumber>=0&&timeLength>0) {
//				int  playTime = logDayXhMediaNewProgram.getPlayTime();
//				int average=playTime/playUserNumber;
//				logDayXhMediaNewProgram.setAlreadyPlay(Integer.toString(average/timeLength));
//			}
			int playTime = 0;
			int average = 0;
			float alreadyPlay = 0.0f;
			playTime = logDayXhMediaNewProgram.getPlayTime();
			if(playUserNumber>0)
			{
				average = playTime/playUserNumber;
			}
			if(timeLength>0)
			{
				alreadyPlay = (float)average/timeLength;
			}
			logDayXhMediaNewProgram.setAlreadyPlay(String.valueOf((int)(alreadyPlay*100)));
			String siteName = "";
			String originName = "";
			String channelName = "";
			String partName = "";
			String programName = "";
			SysGroups sg = sysGroupsService.selectByPrimaryKey(Integer.parseInt(logDayXhMediaNewProgram.getSiteCode()));
			siteName = sg.getName();
			originName = indexXhMediaOriginService.getOriginNameByOriginId(logDayXhMediaNewProgram.getOriginId());
			IndexXhMediaChannel channel = new IndexXhMediaChannel();
			channel = indexXhMediaChannelService.getChannelByChannelIdAndSiteCode(logDayXhMediaNewProgram.getChannelId(), logDayXhMediaNewProgram.getSiteCode());
			if(channel != null)
			{	
			    channelName = channel.getChannelName();
			}
			IndexXhMediaChannel part = new IndexXhMediaChannel();
			part = indexXhMediaChannelService.getChannelByChannelIdAndSiteCode(logDayXhMediaNewProgram.getPartId(), logDayXhMediaNewProgram.getSiteCode());
			if(part != null)
			{
			    partName = part.getChannelName();
			}
			IndexXhMediaProgram programi = new IndexXhMediaProgram();
			programi = indexXhMediaProgramService.getProgramByGlobalIdAndSiteCode(logDayXhMediaNewProgram.getGlobalId(), logDayXhMediaNewProgram.getSiteCode());
			if(programi != null)
			{
			    programName = programi.getChineseName();
			}		
			logDayXhMediaNewProgram.setSiteName(siteName);
			logDayXhMediaNewProgram.setOriginName(originName);
			logDayXhMediaNewProgram.setChannelName(channelName);
			logDayXhMediaNewProgram.setPartName(partName);
			logDayXhMediaNewProgram.setProgramName(programName);
		}
		System.out.println("newProgramList size()==="+newProgramList.size());
		Map map=new HashMap();
		map.put("rows", newProgramList);
		map.put("total", newProgramList.size());
		String result = gson.toJson(map);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(result);
		response.getWriter().close();
	}
	
//	/**
//	 * 检索列表数据
//	 * @param program
//	 * @param request
//	 * @param response
//	 */
//	public void searchProgram(LogDayXhMediaNewProgram program,HttpServletRequest request,HttpServletResponse response){
//		
//		List<LogDayXhMediaNewProgram> searchNewProgramList = logDayXhMediaNewProgramService.searchNewProgramList(program);
//		for (LogDayXhMediaNewProgram logDayXhMediaNewProgram : searchNewProgramList) {
//			int playUserNumber = logDayXhMediaNewProgram.getPlayUserNumber();
//			int timeLength = logDayXhMediaNewProgram.getTimeLength();
//			if (playUserNumber>=0&&timeLength>=0) {
//				int  playTime = logDayXhMediaNewProgram.getPlayTime();
//				int average=playTime/playUserNumber;
//				logDayXhMediaNewProgram.setAlreadyPlay(Integer.toString(average/timeLength));
//			}
//		}
//		Map map=new HashMap();
//		map.put("rows", searchNewProgramList);
//		map.put("total", searchNewProgramList.size());
//		String result = gson.toJson(map);
//		response.setCharacterEncoding("utf-8");
//		try {
//			response.getWriter().write(result);
//			response.getWriter().close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * 对接收到的参数做初始化
	 * @param request
	 */
	private LogDayXhMediaNewProgram initData(HttpServletRequest request) {
		LogDayXhMediaNewProgram newProgram = new LogDayXhMediaNewProgram();
		if (request.getParameter("siteCode")!=null&&!"".equals(request.getParameter("siteCode"))) {
			newProgram.setSiteCode(request.getParameter("siteCode"));
		}
		if (request.getParameter("originId")!=null&&!"".equals(request.getParameter("originId"))) {
			newProgram.setOriginId(request.getParameter("originId"));
		}		
		if (request.getParameter("channelId")!=null&&!"".equals(request.getParameter("channelId"))) {
			newProgram.setChannelId(request.getParameter("channelId"));
		}		
		if (request.getParameter("partId")!=null&&!"".equals(request.getParameter("partId"))) {
			newProgram.setPartId(request.getParameter("partId"));
		}	
		if (request.getParameter("globalId")!=null&&!"".equals(request.getParameter("globalId"))) {
			newProgram.setGlobalId(request.getParameter("globalId"));
		}
		if (request.getParameter("date")!=null&&!"".equals(request.getParameter("date"))) {
			newProgram.setDate(request.getParameter("date"));
		}
		
		
//		String startTime="";
//		if (request.getParameter("startTime")!=null) {
//			startTime=request.getParameter("startTime");
//		}	
//		if("".equals(startTime)){
//			startTime = "1900-01-01";
//			newProgram.setStartTime(startTime);
//		}
//		String endTime="";
//		if (request.getParameter("endTime")!=null) {
//			endTime=request.getParameter("endTime");
//		}
//		if("".equals(endTime)){
//			Timestamp ts = new Timestamp(System.currentTimeMillis());
//			String nowTime = ts+"";
//			endTime = nowTime.substring(0,10);
//			newProgram.setEndTime(endTime);
//		}
		return newProgram;
	}
	
	/**
	 * 对接收到的参数做初始化
	 * @param request
	 */
	private LogXhMediaNewProgram initDataNew(HttpServletRequest request) {
		LogXhMediaNewProgram newProgram = new LogXhMediaNewProgram();
		if (request.getParameter("siteCode")!=null&&!"".equals(request.getParameter("siteCode"))) {
			newProgram.setSiteCode(request.getParameter("siteCode"));
		}
		if (request.getParameter("originId")!=null&&!"".equals(request.getParameter("originId"))) {
			newProgram.setOriginId(request.getParameter("originId"));
		}		
		if (request.getParameter("channelId")!=null&&!"".equals(request.getParameter("channelId"))) {
			newProgram.setChannelId(request.getParameter("channelId"));
		}		
		if (request.getParameter("partId")!=null&&!"".equals(request.getParameter("partId"))) {
			newProgram.setPartId(request.getParameter("partId"));
		}	
		if (request.getParameter("globalId")!=null&&!"".equals(request.getParameter("globalId"))) {
			newProgram.setGlobalId(request.getParameter("globalId"));
		}
		if (request.getParameter("date")!=null&&!"".equals(request.getParameter("date"))) {
			newProgram.setDate(request.getParameter("date"));
		}
		
//		String startTime="";
//		if (request.getParameter("startTime")!=null) {
//			startTime=request.getParameter("startTime");
//		}	
//		if("".equals(startTime)){
//			startTime = "1900-01-01";
//			newProgram.setStartTime(startTime);
//		}
//		String endTime="";
//		if (request.getParameter("endTime")!=null) {
//			endTime=request.getParameter("endTime");
//		}
//		if("".equals(endTime)){
//			Timestamp ts = new Timestamp(System.currentTimeMillis());
//			String nowTime = ts+"";
//			endTime = nowTime.substring(0,10);
//			newProgram.setEndTime(endTime);
//		}
		return newProgram;
	}
	
	@RequestMapping("/getDayNewProgramDetail")
	public void getDayProgramDetail(HttpServletRequest request,HttpServletResponse response) throws IOException{
		LogXhMediaNewProgram newProgram_day = initDataNew(request);
		List<LogXhMediaNewProgram> newProgramDetail = new ArrayList<LogXhMediaNewProgram>();
//		if (request.getParameter("startTime")!=null&&!"".equals(request.getParameter("startTime"))) {
//			newProgram_day.setStartTime(request.getParameter("startTime"));
//		}else{
//			newProgram_day.setStartTime("1900-01-01");
//		}	
//		if (request.getParameter("endTime")!=null&&!"".equals(request.getParameter("endTime"))) {
//			newProgram_day.setEndTime(request.getParameter("endTime"));
//		}else{
//			Timestamp ts = new Timestamp(System.currentTimeMillis());
//			String nowTime = String.valueOf(ts);
//			newProgram_day.setEndTime(nowTime.substring(0, 10));
//		}
		String sort = "";
		String order = "";
		if(request.getParameter("sort")!=null){
			sort = request.getParameter("sort");
		}else{
			sort = "play_number";
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
			if("playNumber".equals(soStr[i])){
				orderList.add("play_number "+orStr[i]);
			}else if("playUserNumber".equals(soStr[i])){
				orderList.add("play_user_number "+orStr[i]);
			}else if("commentNumber".equals(soStr[i])){
				orderList.add("comment_number "+orStr[i]);
			}else if("collectionNumber".equals(soStr[i])){
				orderList.add("collection_number "+orStr[i]);
			}else if("shareNumber".equals(soStr[i])){
				orderList.add("share_number "+orStr[i]);
			}else if("timeLength".equals(soStr[i])){
				orderList.add("time_length "+orStr[i]);
			}else if("pubDate".equals(soStr[i])){
				orderList.add("pub_date "+orStr[i]);
			}else if("siteName".equals(soStr[i])){
				orderList.add("site_code "+orStr[i]);
			}else if("channelName".equals(soStr[i])){
				orderList.add("channel_id "+orStr[i]);
			}else if("partName".equals(soStr[i])){
				orderList.add("part_id "+orStr[i]);
			}else if("originName".equals(soStr[i])){
				orderList.add("origin_id "+orStr[i]);
			}else if("programName".equals(soStr[i])){
				orderList.add("global_id "+orStr[i]);
			}else{
				orderList.add(soStr[i]+" "+orStr[i]);
			}
		}
		newProgram_day.setOrderList(orderList);
		
		newProgramDetail = logXhMediaNewProgramService.searchNewProgramListNew(newProgram_day);
		
		for (LogXhMediaNewProgram logDayXhMediaNewProgram : newProgramDetail) {
			int playUserNumber = logDayXhMediaNewProgram.getPlayUserNumber();
			int timeLength = logDayXhMediaNewProgram.getTimeLength();
//			if (playUserNumber>=0&&timeLength>0) {
//				int  playTime = logDayXhMediaNewProgram.getPlayTime();
//				int average=playTime/playUserNumber;
//				logDayXhMediaNewProgram.setAlreadyPlay(Integer.toString(average/timeLength));
//			}
			int playTime = 0;
			int average = 0;
			float alreadyPlay = 0.0f;
			playTime = logDayXhMediaNewProgram.getPlayTime();
			if(playUserNumber>0)
			{
				average = playTime/playUserNumber;
			}
			if(timeLength>0)
			{
				alreadyPlay = (float)average/timeLength;
			}
			logDayXhMediaNewProgram.setAlreadyPlay(String.valueOf((int)(alreadyPlay*100)));
			String siteName = "";
			String originName = "";
			String channelName = "";
			String partName = "";
			String programName = "";
			SysGroups sg = sysGroupsService.selectByPrimaryKey(Integer.parseInt(logDayXhMediaNewProgram.getSiteCode()));
			siteName = sg.getName();
			originName = indexXhMediaOriginService.getOriginNameByOriginId(logDayXhMediaNewProgram.getOriginId());
			IndexXhMediaChannel channel = new IndexXhMediaChannel();
			channel = indexXhMediaChannelService.getChannelByChannelIdAndSiteCode(logDayXhMediaNewProgram.getChannelId(), logDayXhMediaNewProgram.getSiteCode());
			if(channel != null)
			{	
			    channelName = channel.getChannelName();
			}
			IndexXhMediaChannel part = new IndexXhMediaChannel();
			part = indexXhMediaChannelService.getChannelByChannelIdAndSiteCode(logDayXhMediaNewProgram.getPartId(), logDayXhMediaNewProgram.getSiteCode());
			if(part != null)
			{
			    partName = part.getChannelName();
			}
			IndexXhMediaProgram programi = new IndexXhMediaProgram();
			programi = indexXhMediaProgramService.getProgramByGlobalIdAndSiteCode(logDayXhMediaNewProgram.getGlobalId(), logDayXhMediaNewProgram.getSiteCode());
			if(programi != null)
			{
			    programName = programi.getChineseName();
			}		
			logDayXhMediaNewProgram.setSiteName(siteName);
			logDayXhMediaNewProgram.setOriginName(originName);
			logDayXhMediaNewProgram.setChannelName(channelName);
			logDayXhMediaNewProgram.setPartName(partName);
			logDayXhMediaNewProgram.setProgramName(programName);
		}
		Map map=new HashMap();
		map.put("rows", newProgramDetail);
		map.put("total", newProgramDetail.size());
		String result = gson.toJson(map);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(result);
		response.getWriter().close();
			
	}
	
	@RequestMapping("/getNewProgramDetail")
	public void getProgramDetail(HttpServletRequest request,HttpServletResponse response) throws IOException{
		LogXhMediaNewProgram newProgram = initDataNew(request);
		List<LogXhMediaNewProgram> newProgramDetail = new ArrayList<LogXhMediaNewProgram>();
		if (request.getParameter("startTime")!=null&&!"".equals(request.getParameter("startTime"))) {
			newProgram.setStartTime(request.getParameter("startTime"));
		}else{
			newProgram.setStartTime("1900-01-01");
		}	
		if (request.getParameter("endTime")!=null&&!"".equals(request.getParameter("endTime"))) {
			newProgram.setEndTime(request.getParameter("endTime"));
		}else{
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			String nowTime = String.valueOf(ts);
			newProgram.setEndTime(nowTime.substring(0, 10));
		}
		String sort = "";
		String order = "";
		if(request.getParameter("sort")!=null){
			sort = request.getParameter("sort");
		}else{
			sort = "play_number";
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
			if("playNumber".equals(soStr[i])){
				orderList.add("play_number "+orStr[i]);
			}else if("playUserNumber".equals(soStr[i])){
				orderList.add("play_user_number "+orStr[i]);
			}else if("commentNumber".equals(soStr[i])){
				orderList.add("comment_number "+orStr[i]);
			}else if("collectionNumber".equals(soStr[i])){
				orderList.add("collection_number "+orStr[i]);
			}else if("shareNumber".equals(soStr[i])){
				orderList.add("share_number "+orStr[i]);
			}else if("timeLength".equals(soStr[i])){
				orderList.add("time_length "+orStr[i]);
			}else if("pubDate".equals(soStr[i])){
				orderList.add("pub_date "+orStr[i]);
			}else if("siteName".equals(soStr[i])){
				orderList.add("site_code "+orStr[i]);
			}else if("channelName".equals(soStr[i])){
				orderList.add("channel_id "+orStr[i]);
			}else if("partName".equals(soStr[i])){
				orderList.add("part_id "+orStr[i]);
			}else if("originName".equals(soStr[i])){
				orderList.add("origin_id "+orStr[i]);
			}else if("programName".equals(soStr[i])){
				orderList.add("global_id "+orStr[i]);
			}else{
				orderList.add(soStr[i]+" "+orStr[i]);
			}
		}
		newProgram.setOrderList(orderList);
		
		newProgramDetail = logXhMediaNewProgramService.searchNewProgramListNew(newProgram);
		
		for (LogXhMediaNewProgram logDayXhMediaNewProgram : newProgramDetail) {
			int playUserNumber = logDayXhMediaNewProgram.getPlayUserNumber();
			int timeLength = logDayXhMediaNewProgram.getTimeLength();
//			if (playUserNumber>=0&&timeLength>0) {
//				int  playTime = logDayXhMediaNewProgram.getPlayTime();
//				int average=playTime/playUserNumber;
//				logDayXhMediaNewProgram.setAlreadyPlay(Integer.toString(average/timeLength));
//			}
			int playTime = 0;
			int average = 0;
			float alreadyPlay = 0.0f;
			playTime = logDayXhMediaNewProgram.getPlayTime();
			if(playUserNumber>0)
			{
				average = playTime/playUserNumber;
			}
			if(timeLength>0)
			{
				alreadyPlay = (float)average/timeLength;
			}
			logDayXhMediaNewProgram.setAlreadyPlay(String.valueOf((int)(alreadyPlay*100)));
			String siteName = "";
			String originName = "";
			String channelName = "";
			String partName = "";
			String programName = "";
			SysGroups sg = sysGroupsService.selectByPrimaryKey(Integer.parseInt(logDayXhMediaNewProgram.getSiteCode()));
			siteName = sg.getName();
			originName = indexXhMediaOriginService.getOriginNameByOriginId(logDayXhMediaNewProgram.getOriginId());
			IndexXhMediaChannel channel = new IndexXhMediaChannel();
			channel = indexXhMediaChannelService.getChannelByChannelIdAndSiteCode(logDayXhMediaNewProgram.getChannelId(), logDayXhMediaNewProgram.getSiteCode());
			if(channel != null)
			{	
			    channelName = channel.getChannelName();
			}
			IndexXhMediaChannel part = new IndexXhMediaChannel();
			part = indexXhMediaChannelService.getChannelByChannelIdAndSiteCode(logDayXhMediaNewProgram.getPartId(), logDayXhMediaNewProgram.getSiteCode());
			if(part != null)
			{
			    partName = part.getChannelName();
			}
			IndexXhMediaProgram programi = new IndexXhMediaProgram();
			programi = indexXhMediaProgramService.getProgramByGlobalIdAndSiteCode(logDayXhMediaNewProgram.getGlobalId(), logDayXhMediaNewProgram.getSiteCode());
			if(programi != null)
			{
			    programName = programi.getChineseName();
			}		
			logDayXhMediaNewProgram.setSiteName(siteName);
			logDayXhMediaNewProgram.setOriginName(originName);
			logDayXhMediaNewProgram.setChannelName(channelName);
			logDayXhMediaNewProgram.setPartName(partName);
			logDayXhMediaNewProgram.setProgramName(programName);
		}
		Map map=new HashMap();
		map.put("rows", newProgramDetail);
		map.put("total", newProgramDetail.size());
		String result = gson.toJson(map);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(result);
		response.getWriter().close();
			
	}
}
