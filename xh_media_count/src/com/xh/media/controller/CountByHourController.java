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
import com.xh.media.model.CountByHour;
import com.xh.media.service.CountByHourService;

@Controller
public class CountByHourController {
	private ApplicationContext applicationContext;
	private CountByHourService countByHourService;
	private Gson gson;
	
	public CountByHourController() {
		applicationContext= new ClassPathXmlApplicationContext("applicationContext.xml");
		countByHourService=(CountByHourService) applicationContext.getBean("countByHourService");
		gson=new Gson();
	}
	@RequestMapping("/countByHourList")
    public void getCountByHourList(HttpServletRequest request,HttpServletResponse response) throws IOException{
    	Map map= new HashMap();
    	CountByHour record=new CountByHour();
    	String sort = "";
		String order = "";
		if(request.getParameter("sort")!=null){
			sort = request.getParameter("sort");
		}else{
			sort = "date";
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
			if("orderNumber".equals(soStr[i])){
				orderList.add("order_Number "+orStr[i]);
			}else if("completeNumber".equals(soStr[i])){
				orderList.add("complete_Number "+orStr[i]);
			}else{
				orderList.add(soStr[i]+" "+orStr[i]);
			}
		}
		record.setOrderList(orderList);
    	List<CountByHour> countByHourList = countByHourService.getCountByHourList(record);
    	map.put("total", countByHourList.size());
    	map.put("rows", countByHourList);
    	String resultStr = gson.toJson(map);
    	response.setCharacterEncoding("utf-8");
    	response.getWriter().write(resultStr);
    	response.getWriter().close();
    }
	
	@RequestMapping("/sortList")
	public void getSortList(HttpServletRequest request ,HttpServletResponse response) throws IOException{
		List<CountByHour> sortList = countByHourService.getSortList();
    	String resultStr = gson.toJson(sortList);
    	response.setCharacterEncoding("utf-8");
    	response.getWriter().write(resultStr);
    	response.getWriter().close();
	}
	
	@RequestMapping("/programName")
	public void getProgramNameList(HttpServletRequest request ,HttpServletResponse response) throws IOException{
		String sort="";
		if (request.getParameter("sort")!=null) {
			sort=request.getParameter("sort");
			sort = new String(sort.getBytes("ISO-8859-1"), "utf8");
		}
		CountByHour record=new CountByHour();
			record.setSort(sort);
		List<CountByHour> programNameList = countByHourService.getProgramNameList(record);
    	String resultStr = gson.toJson(programNameList);
    	response.setCharacterEncoding("utf-8");
    	response.getWriter().write(resultStr);
    	response.getWriter().close();
	}
	@RequestMapping("/searchCountByHourList")
	public void  searchProgram(HttpServletRequest request ,HttpServletResponse response)throws IOException{
		CountByHour record=new CountByHour();
    	String sort = "";
		String order = "";
		if(request.getParameter("sort")!=null){
			sort = request.getParameter("sort");
		}else{
			sort = "date";
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
			if("orderNumber".equals(soStr[i])){
				orderList.add("order_Number "+orStr[i]);
			}else if("completeNumber".equals(soStr[i])){
				orderList.add("complete_Number "+orStr[i]);
			}else{
				orderList.add(soStr[i]+" "+orStr[i]);
			}
		}
		
		String startTime="";
		if (request.getParameter("startTime")!=null) {
			startTime=request.getParameter("startTime");
		}
		String endTime="";
		if (request.getParameter("endTime")!=null) {
			endTime=request.getParameter("endTime");
		}
		String sortProgram="";
		if (request.getParameter("sortProgram")!=null) {
			sortProgram=request.getParameter("sortProgram");
			sortProgram = new String(sortProgram.getBytes("ISO-8859-1"), "utf8");
		}
		String globalId="";
		if (request.getParameter("globalId")!=null) {
			globalId=request.getParameter("globalId");
		}
		if ("".equals(startTime)&&"".equals(endTime)&&"".equals(sortProgram)&&"".equals(globalId)) {
			 getCountByHourList(request,response);
		       }else{
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
					if (!"".equals(sortProgram)) {
						record.setSort(sortProgram);
					}
					if (!"".equals(globalId)) {
						record.setGlobalId(globalId);
					}
					record.setOrderList(orderList);
					List<CountByHour> searchProgram = countByHourService.searchProgram(record);
					Map map=new HashMap();
					map.put("total", searchProgram.size());
					map.put("rows", searchProgram);
					String resultStr = gson.toJson(map);
			    	response.setCharacterEncoding("utf-8");
			    	response.getWriter().write(resultStr);
			    	response.getWriter().close();
				}
	  }
	
	@RequestMapping("/countByHourDataSum")
	public void dataSum(HttpServletRequest request ,HttpServletResponse response)throws IOException{
		List<CountByHour> selectCountByHourDataSum = countByHourService.selectCountByHourDataSum();
		Map map=new HashMap();
		map.put("total", selectCountByHourDataSum.size());
		map.put("rows", selectCountByHourDataSum);
		String resultStr = gson.toJson(map);
    	response.setCharacterEncoding("utf-8");
    	response.getWriter().write(resultStr);
    	response.getWriter().close();
	}
}
