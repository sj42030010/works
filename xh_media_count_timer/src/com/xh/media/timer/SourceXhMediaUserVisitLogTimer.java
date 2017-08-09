package com.xh.media.timer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.xh.media.bean.IndexChannelBean;
import com.xh.media.bean.IndexProgramBean;
import com.xh.media.bean.SourceVisitBean;
import com.xh.media.dao.PublicDao;
import com.xh.media.dao.SourceXhMediaUserVisitLogDao;
import com.xh.media.util.DateUtil;
import com.xh.media.util.DbConn;
import com.xh.media.util.Key;
import com.xh.media.util.Util;

public class SourceXhMediaUserVisitLogTimer {
	private SourceXhMediaUserVisitLogDao sxmuvld;
	private PublicDao pd;
	private DbConn dbConn;
	
	public SourceXhMediaUserVisitLogTimer(){
		dbConn = new DbConn(Key.COUNT_DATABASE[0], Key.COUNT_DATABASE[1], Key.COUNT_DATABASE[2]);
		sxmuvld = new SourceXhMediaUserVisitLogDao(dbConn);
		pd = new PublicDao(dbConn);
	}
	
	public void updateIndexTableAndSourceTable()
	{
		Gson gson = new Gson();
		String[] timeArr = DateUtil.getDateValueNowMinute();
		//根据上报数据更新频道栏目索引表和节目索引表
		List<SourceVisitBean> list = new ArrayList<SourceVisitBean>();
		list = sxmuvld.getSourceVisitList(timeArr[3], timeArr[4]);
//		System.out.println("first list size===="+list.size());
		if(list.size()>0)
		{
			for(SourceVisitBean svb:list)
			{
				String siteUrl = "";
				siteUrl = sxmuvld.getSiteUrl(svb.getSiteCode());
				if(!"".equals(siteUrl))
				{
					if(!"0".equals(svb.getChannelId()))
					{
						//首先更新频道栏目索引表的数据
						String jsonStr = "";
						String channelUrl = "";
						channelUrl = siteUrl+Key.CHANNEL_INFO_PATH+"?channelId=";
						System.out.println(channelUrl+svb.getChannelId());
						jsonStr = Util.getResponseStr(channelUrl+svb.getChannelId());
						int strLength = jsonStr.length();
						if(!"".equals(jsonStr)&&strLength>28)
						{
							Map map = gson.fromJson(jsonStr, Map.class);
							String channelName = map.get("channelName").toString();
							String channelCode = map.get("channelPath").toString();
							//拆分channelCode取得父频道ID
							String[] channels = channelCode.split("_");
							String pChannelId = "";
							if(channels.length>1)
							{
								pChannelId = channels[channels.length-2];
							}
							else
							{
								pChannelId = channels[channels.length-1];
							}
							jsonStr = Util.getResponseStr(channelUrl+pChannelId);
							int strLength1 = jsonStr.length();
							if(!"".equals(jsonStr)&&strLength1>28)
							{
								Map pmap = gson.fromJson(jsonStr, Map.class);
								String pChannelName = pmap.get("channelName").toString();
								String pChannelCode = pmap.get("channelPath").toString();
								IndexChannelBean icb = new IndexChannelBean();
								icb.setChannelId(svb.getChannelId());
								icb.setChannelName(channelName);
								icb.setChannelPath(channelCode);
								icb.setOriginId(svb.getOriginId());
								icb.setParentId(pChannelId);
								icb.setSiteCode(svb.getSiteCode());
								icb.setStatus(1);
								sxmuvld.updateOrInsertChannelIndexTable(icb);
								IndexChannelBean picb = new IndexChannelBean();
								picb.setChannelId(pChannelId);
								picb.setChannelName(pChannelName);
								picb.setChannelPath(pChannelCode);
								picb.setOriginId(svb.getOriginId());
								picb.setParentId("-1");
								picb.setSiteCode(svb.getSiteCode());
								picb.setStatus(1);
								sxmuvld.updateOrInsertChannelIndexTable(picb);
							}
						}
						
						if(!"0".equals(svb.getGlobalId()))
						{
							//接下来更新节目索引表的数据
							String jsonPStr = "";
							String programUrl = ""; 
							programUrl = siteUrl+Key.PROGRAM_INFO_PATH+"?channelId="+svb.getChannelId()+"&globalId="+svb.getGlobalId();
							jsonPStr = Util.getResponseStr(programUrl);
							if(!"".equals(jsonPStr))
							{
								Map map = gson.fromJson(jsonPStr, Map.class);
								String chineseName = "";
								String timeLength = "0";
								String publishDate = "0";
								int cnindex = jsonPStr.indexOf("chineseName");
								int tlIndex = jsonPStr.indexOf("timeLength");
								int pdIndex = jsonPStr.indexOf("publishDate");
								if(cnindex>-1)
								{
									chineseName = map.get("chineseName").toString();
								}
								if(tlIndex>-1)
								{
									timeLength = map.get("timeLength").toString();
								}
								if(pdIndex>-1)
								{
									publishDate = map.get("publishDate").toString();
								}
								IndexProgramBean ipb= new IndexProgramBean();
								ipb.setChannelId(svb.getChannelId());
								ipb.setGlobalId(svb.getGlobalId());
								ipb.setChineseName(chineseName);
								ipb.setPublishDate(publishDate);
								ipb.setTimeLength(timeLength);
								sxmuvld.updateProgramIndexTable(ipb);
							}
						}
					}
				}
			}
		}
		
		//根据交互上报数据更新节目索引表,按照逻辑用户必然访问过节目详情页，所以此步骤没有必要执行，原本增加这一步是想解决交互节目记录没有添加进索引表的问题，但是接口无法获得channelid只能取到originid所以如果没有
		//索引记录还是无法补全故此目前暂不进行此步
//		List<SourceVisitBean> listIner = new ArrayList<SourceVisitBean>();
//		listIner = sxmuvld.getSourceInteractiveList(timeArr[3], timeArr[4]);
//		if(listIner.size()>0)
//		{
//			for(SourceVisitBean svb:listIner)
//			{
//				String siteUrl = "";
//				siteUrl = sxmuvld.getSiteUrl(svb.getSiteCode());
//				if(!"".equals(siteUrl))
//				{
//					if(!"0".equals(svb.getOriginId()))
//					{
//						if(!"0".equals(svb.getGlobalId()))
//						{
//							//根据global_id和site_code确定
////							//接下来更新节目索引表的数据
////							String jsonPStr = "";
////							String programUrl = ""; 
////							programUrl = siteUrl+Key.PROGRAM_INFO_PATH+"?channelId="+svb.getChannelId()+"&globalId="+svb.getGlobalId();
////							jsonPStr = Util.getResponseStr(programUrl);
////							if(!"".equals(jsonPStr))
////							{
////								Map map = gson.fromJson(jsonPStr, Map.class);
////								String chineseName = map.get("chineseName").toString();
////								String timeLength = map.get("timeLength").toString();
////								String publishDate = map.get("publishDate").toString();
////								IndexProgramBean ipb= new IndexProgramBean();
////								ipb.setChannelId(svb.getChannelId());
////								ipb.setGlobalId(svb.getGlobalId());
////								ipb.setChineseName(chineseName);
////								ipb.setPublishDate(publishDate);
////								ipb.setTimeLength(timeLength);
////								sxmuvld.updateProgramIndexTable(ipb);
////							}
//						}
//					}
//				}
//			}
//		}
		Util.closeResultSetAndConnection(dbConn);
	}
	
	public void updateIndexTableAndSourceTableHistory(String days, String hours, String minutes, String yesDate, String nowDate)
	{
		Gson gson = new Gson();
//		String[] timeArr = DateUtil.getDateValueNowMinute();
		//根据上报数据更新频道栏目索引表和节目索引表
		List<SourceVisitBean> list = new ArrayList<SourceVisitBean>();
		list = sxmuvld.getSourceVisitList(yesDate, nowDate);
		if(list.size()>0)
		{
			for(SourceVisitBean svb:list)
			{
				String siteUrl = "";
				siteUrl = sxmuvld.getSiteUrl(svb.getSiteCode());
				if(!"".equals(siteUrl))
				{
					if(!"0".equals(svb.getChannelId()))
					{
						//首先更新频道栏目索引表的数据
						String jsonStr = "";
						String jsonPCstr = "";
						String channelUrl = "";
						channelUrl = siteUrl+Key.CHANNEL_INFO_PATH+"?channelId=";
						System.out.println("子栏目===="+channelUrl+svb.getChannelId());
						jsonStr = Util.getResponseStr(channelUrl+svb.getChannelId());
						
						int strLength = jsonStr.length();
						if(!"".equals(jsonStr)&&strLength>28)
						{
							Map map = gson.fromJson(jsonStr, Map.class);
							String channelName = map.get("channelName").toString();
							String channelCode = map.get("channelPath").toString();
							//拆分channelCode取得父频道ID
							String[] channels = channelCode.split("_");
							String pChannelId = "";
							if(channels.length>1)
							{
								if(channelCode.indexOf("__")>-1)
								{
									pChannelId = channels[channels.length-3];
								}
								else
								{
								    pChannelId = channels[channels.length-2];
								}
							}
							else
							{
								pChannelId = channels[channels.length-1];
							}
							
							jsonPCstr = Util.getResponseStr(channelUrl+pChannelId);
//							System.out.println("父栏目===="+channelUrl+pChannelId);
//							System.out.println("父栏目===="+jsonPCstr);
							int strLength1 = jsonStr.length();
							if(!"".equals(jsonPCstr)&&strLength1>28)
							{
								Map pmap = gson.fromJson(jsonPCstr, Map.class);
								String pChannelName = pmap.get("channelName").toString();
								String pChannelCode = pmap.get("channelPath").toString();
								IndexChannelBean icb = new IndexChannelBean();
								icb.setChannelId(svb.getChannelId());
								icb.setChannelName(channelName);
								icb.setChannelPath(channelCode);
								icb.setOriginId(svb.getOriginId());
								icb.setParentId(pChannelId);
								icb.setSiteCode(svb.getSiteCode());
								icb.setStatus(1);
								sxmuvld.updateOrInsertChannelIndexTable(icb);
								IndexChannelBean picb = new IndexChannelBean();
								picb.setChannelId(pChannelId);
								picb.setChannelName(pChannelName);
								picb.setChannelPath(pChannelCode);
								picb.setOriginId(svb.getOriginId());
								picb.setParentId("-1");
								picb.setSiteCode(svb.getSiteCode());
								picb.setStatus(1);
								sxmuvld.updateOrInsertChannelIndexTable(picb);
							}
						}
						
						if(!"0".equals(svb.getGlobalId()))
						{
							//接下来更新节目索引表的数据
							String jsonPStr = "";
							String programUrl = ""; 
							programUrl = siteUrl+Key.PROGRAM_INFO_PATH+"?channelId="+svb.getChannelId()+"&globalId="+svb.getGlobalId();
							//System.out.println(programUrl);
							jsonPStr = Util.getResponseStr(programUrl);
							if(!"".equals(jsonPStr))
							{
//								System.out.println("节目地址=="+programUrl);
//								System.out.println("节目信息=="+jsonPStr);
								Map map = gson.fromJson(jsonPStr, Map.class);
								String chineseName = "";
								String timeLength = "0";
								String publishDate = "0";
								int cnindex = jsonPStr.indexOf("chineseName");
								int tlIndex = jsonPStr.indexOf("timeLength");
								int pdIndex = jsonPStr.indexOf("publishDate");
								if(cnindex>-1)
								{
									chineseName = map.get("chineseName").toString();
								}
								if(tlIndex>-1)
								{
									if(!"".equals(map.get("timeLength").toString()))
									{
										timeLength = map.get("timeLength").toString();
									}
								}
								if(pdIndex>-1)
								{
									if(!"".equals(map.get("publishDate").toString()))
									{
										publishDate = map.get("publishDate").toString();
									}
								}
								IndexProgramBean ipb= new IndexProgramBean();
								ipb.setChannelId(svb.getChannelId());
								ipb.setGlobalId(svb.getGlobalId());
								ipb.setChineseName(chineseName);
								ipb.setPublishDate(publishDate);
								ipb.setTimeLength(timeLength);
								sxmuvld.updateProgramIndexTable(ipb);
							}
						}
					}
				}
			}
		}
		Util.closeResultSetAndConnection(dbConn);
	}
}
