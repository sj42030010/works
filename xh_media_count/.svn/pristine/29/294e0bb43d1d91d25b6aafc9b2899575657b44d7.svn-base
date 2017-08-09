package com.xh.media.controller;

import java.io.IOException;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.google.gson.Gson;
import com.xh.media.bean.CountBean;
import com.xh.media.model.IndexXhMediaChannel;
import com.xh.media.model.IndexXhMediaOrigin;
import com.xh.media.service.IndexXhMediaChannelService;
import com.xh.media.service.IndexXhMediaOriginService;
@Controller
public class ChannelController extends AbstractController {
	private IndexXhMediaChannelService indexXhMediaChannelService;
	private IndexXhMediaOriginService indexXhMediaOriginService;
	private ApplicationContext context;
	private Gson gson;
	
	public ChannelController(){
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		indexXhMediaChannelService = (IndexXhMediaChannelService)context.getBean("indexXhMediaChannelService");
		indexXhMediaOriginService = (IndexXhMediaOriginService)context.getBean("indexXhMediaOriginService");
		gson = new Gson();
	}
	@RequestMapping("/channel")
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		int action = Integer.parseInt(arg0.getParameter("action"));
		String originId = arg0.getParameter("originId");
		switch(action){
			case 1://获取频道列表
				List<IndexXhMediaChannel> channelList = indexXhMediaChannelService.getChannelsByOrigin(originId);
				String resultStr = gson.toJson(channelList);
				arg1.setCharacterEncoding("UTF-8");
				arg1.getWriter().write(resultStr);
				arg1.getWriter().flush();
				arg1.getWriter().close();
				break;
			case 2://获取渠道列表
				List<IndexXhMediaOrigin> originList = indexXhMediaOriginService.getAllOriginBySiteCode(originId);
				
				String resultStr1 = gson.toJson(originList);
				arg1.setCharacterEncoding("UTF-8");
				arg1.getWriter().write(resultStr1);
				arg1.getWriter().flush();
				arg1.getWriter().close();
				break;
			case 3://获取折线图数据
				/*List<String> legend = new ArrayList<String>(Arrays.asList(new String[] { "折线图"}));
				List<String> axis = new ArrayList<String>(
		                Arrays.asList(new String[] {"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23"}));
				List<Series> series = new ArrayList<Series>();
				series.add(new Series("折线图", "line", new ArrayList<Integer>(Arrays.asList(0,0,0,0,0,0,0,0,3,8,7,8,8,23,9,17,6,2,2,12,0,3,0))));
				Echarts echarts = new Echarts(legend, axis, series);
				arg1.setContentType("text/html;charset=utf-8");
				PrintWriter out;
		        try {
		            out = arg1.getWriter();
		            Gson gson = new Gson();
		            String str = gson.toJson(echarts);
		            System.out.println("str:"+str);
		            out.write(str);
		            out.flush();
		            out.close();
		        } catch (Exception e) {
		            e.printStackTrace();
		        }*/
//				String[] hours = {"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23"};
				String[] hours = {"0","00:15","00:30","00:45","1","01:15","01:30","01:45","2","02:15","02:30","02:45","3","03:15","03:30","03:45","4","04:15","04:30","04:45","5","05:15","05:30","05:45"};
				int[] pv = {0,0,0,0,0,0,0,0,3,8,7,8,8,23,9,17,6,2,2,12,0,3,0,0};
				int[] uv = {0,0,0,0,0,0,0,0,3,8,7,5,7,21,9,16,6,1,1,5,0,3,0};
				int[] ip = {0,0,0,0,0,0,0,0,2,4,5,4,1,9,6,11,4,1,1,5,0,1,0};
//				Map map = new HashMap();
//				map.put("hours", hours);
//				map.put("pv", pv);
//				map.put("uv", uv);
//				map.put("ip", ip);
//				String resultStr2 = gson.toJson(map);
//				arg1.setCharacterEncoding("UTF-8");
//				arg1.getWriter().write(resultStr2);
				List<CountBean> list = new ArrayList<CountBean>();
				for(int i=0;i<hours.length;i++){
					CountBean cb = new CountBean();
					cb.setName(hours[i]);
					cb.setNum(pv[i]);
					list.add(cb);
				}
				Map map = new HashMap();
				String[] legend = {"pv","uv","ip"};
				map.put("legend", legend);
				map.put("hour", hours);
				map.put("pv", pv);
				map.put("uv", uv);
				map.put("ip", ip);
				String resultStr2 = gson.toJson(map);
//				ObjectMapper mapper = new ObjectMapper();    //提供java-json相互转换功能的类
//		        
//		        String json = mapper.writeValueAsString(list);    //将list中的对象转换为Json格式的数组
				arg1.setCharacterEncoding("utf-8");
				arg1.getWriter().write(resultStr2);
				arg1.getWriter().flush();
				arg1.getWriter().close();
				break;
		}
		
		return null;
	}
	
	
	@RequestMapping("/getChannel")
	public void getChannel(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String originId = request.getParameter("originId");
		String siteCode= request.getParameter("siteCode");
		List<IndexXhMediaChannel> channelList = indexXhMediaChannelService.getChannels(originId,siteCode);
		String resultStr = gson.toJson(channelList);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(resultStr);
		response.getWriter().flush();
		response.getWriter().close();
	}
	
	@RequestMapping("/getParts")
	public void getParts(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String siteCode= request.getParameter("siteCode");
		String originId = request.getParameter("originId");
		String channelId= request.getParameter("channelId");
		List<IndexXhMediaChannel> parts = indexXhMediaChannelService.getParts(siteCode,originId, channelId);
		
		String resultStr = gson.toJson(parts);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(resultStr);
		response.getWriter().flush();
		response.getWriter().close();
		
		
		
	}
	
	public static void main(String[] args){
		String[] cate = {"a","b","c"};
		int[] data = {1,2,3};
		Map map = new HashMap();
		map.put("categories", cate);
		map.put("data", data);
		Gson gson = new Gson();
		String str = gson.toJson(map);
		System.out.println(str);
	}

}
