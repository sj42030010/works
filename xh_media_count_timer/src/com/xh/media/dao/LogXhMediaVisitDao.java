package com.xh.media.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.xh.media.bean.SourceVisitBean;
import com.xh.media.bean.VisitLogBean;
import com.xh.media.util.DbConn;

public class LogXhMediaVisitDao {
	private DbConn dbConn;
	public LogXhMediaVisitDao(DbConn dbConn){
		this.dbConn = dbConn;
	}
	
	public int findStartPvCount(String originId,String channelId,String siteCode,String startTime,String endTime){
		int startpv = 0;
		try{
			String select = "SELECT COUNT(id) AS startpv FROM source_xh_media_user_visit_log WHERE origin_id='"+originId+"' AND channle_id='"+channelId+"' AND site_code='"+siteCode+"' AND visit_type=3 AND create_time>='"+startTime+"' AND create_time<'"+endTime+"'";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			if(rs1.next())
				startpv = rs1.getInt("startpv");
		}catch(Exception e){
			e.printStackTrace();
		}
		return startpv;
	}
	
	public int findAppStartPvCount(String originId,String startTime,String endTime){
		int startpv = 0;
		try{
			String select = "SELECT COUNT(id) AS startpv FROM source_xh_media_user_visit_log WHERE origin_id='"+originId+"' AND visit_type=3 AND create_time>='"+startTime+"' AND create_time<'"+endTime+"'";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			if(rs1.next())
				startpv = rs1.getInt("startpv");
		}catch(Exception e){
			e.printStackTrace();
		}
		return startpv;
	}
	
	public int findSiteStartPvCount(String siteCode,String startTime,String endTime){
		int startpv = 0;
		try{
			String select = "SELECT COUNT(id) AS startpv FROM source_xh_media_user_visit_log WHERE site_code='"+siteCode+"' AND visit_type=3 AND create_time>='"+startTime+"' AND create_time<'"+endTime+"'";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			if(rs1.next())
				startpv = rs1.getInt("startpv");
		}catch(Exception e){
			e.printStackTrace();
		}
		return startpv;
	}
	
	public int findStartUvCount(String originId,String channelId,String siteCode,String startTime,String endTime){
		int startuv = 0;
		try{
			String select = "SELECT COUNT(DISTINCT user_id) AS startuv FROM source_xh_media_user_visit_log WHERE origin_id='"+originId+"' AND channle_id='"+channelId+"' AND site_code='"+siteCode+"' AND visit_type=3 AND create_time>='"+startTime+"' AND create_time<'"+endTime+"'";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			if(rs1.next())
				startuv = rs1.getInt("startuv");
		}catch(Exception e){
			e.printStackTrace();
		}
		return startuv;
	}
	
	public int findAppStartUvCount(String originId,String startTime,String endTime){
		int startuv = 0;
		try{
			String select = "SELECT COUNT(DISTINCT user_id) AS startuv FROM source_xh_media_user_visit_log WHERE origin_id='"+originId+"' AND visit_type=3 AND create_time>='"+startTime+"' AND create_time<'"+endTime+"'";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			if(rs1.next())
				startuv = rs1.getInt("startuv");
		}catch(Exception e){
			e.printStackTrace();
		}
		return startuv;
	}
	
	public int findSiteStartUvCount(String siteCode,String startTime,String endTime){
		int startuv = 0;
		try{
			String select = "SELECT COUNT(DISTINCT user_id) AS startuv FROM source_xh_media_user_visit_log WHERE site_code='"+siteCode+"' AND visit_type=3 AND create_time>='"+startTime+"' AND create_time<'"+endTime+"'";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			if(rs1.next())
				startuv = rs1.getInt("startuv");
		}catch(Exception e){
			e.printStackTrace();
		}
		return startuv;
	}
	
	public int findPvCount(String originId,String channelId,String siteCode,String startTime,String endTime){
		int pv = 0;
		try{
			String select = "SELECT COUNT(id) AS pv FROM source_xh_media_user_visit_log WHERE origin_id='"+originId+"' AND channle_id='"+channelId+"' AND site_code='"+siteCode+"' AND create_time>='"+startTime+"' AND create_time<'"+endTime+"'";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			if(rs1.next())
				pv = rs1.getInt("pv");
		}catch(Exception e){
			e.printStackTrace();
		}
		return pv;
	}
	
	public int findUvCount(String originId,String channelId,String siteCode,String startTime,String endTime){
		int uv = 0;
		try{
			String select = "SELECT COUNT(DISTINCT user_id) AS uv FROM source_xh_media_user_visit_log WHERE origin_id='"+originId+"' AND channle_id='"+channelId+"' AND site_code='"+siteCode+"' AND create_time>='"+startTime+"' AND create_time<'"+endTime+"'";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			if(rs1.next())
				uv = rs1.getInt("uv");
		}catch(Exception e){
			e.printStackTrace();
		}
		return uv;
	}
	
	public int findIpCount(String originId,String channelId,String siteCode,String startTime,String endTime){
		int ip = 0;
		try{
			String select = "SELECT COUNT(DISTINCT ip) AS ip FROM source_xh_media_user_visit_log WHERE origin_id='"+originId+"' AND channle_id='"+channelId+"' AND site_code='"+siteCode+"' AND create_time>='"+startTime+"' AND create_time<'"+endTime+"'";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			if(rs1.next())
				ip = rs1.getInt("ip");
		}catch(Exception e){
			e.printStackTrace();
		}
		return ip;
	}
	
	public int findAppIpCount(String originId,String startTime,String endTime){
		int ip = 0;
		try{
			String select = "SELECT COUNT(DISTINCT ip) AS ip FROM source_xh_media_user_visit_log WHERE origin_id='"+originId+"' AND create_time>='"+startTime+"' AND create_time<'"+endTime+"'";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			if(rs1.next())
				ip = rs1.getInt("ip");
		}catch(Exception e){
			e.printStackTrace();
		}
		return ip;
	}
	
	public int findSiteIpCount(String siteCode,String startTime,String endTime){
		int ip = 0;
		try{
			String select = "SELECT COUNT(DISTINCT ip) AS ip FROM source_xh_media_user_visit_log WHERE site_code='"+siteCode+"' AND create_time>='"+startTime+"' AND create_time<'"+endTime+"'";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			if(rs1.next())
				ip = rs1.getInt("ip");
		}catch(Exception e){
			e.printStackTrace();
		}
		return ip;
	}
	
	public int findPlayTimeCount(String originId,String channelId,String siteCode,String startTime,String endTime){
		int playtime = 0;
		try{
			String select = "SELECT SUM(play_time) AS playtime FROM source_xh_media_user_visit_log WHERE origin_id='"+originId+"' AND channle_id='"+channelId+"' AND site_code='"+siteCode+"' AND create_time>='"+startTime+"' AND create_time<'"+endTime+"'";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			if(rs1.next())
				playtime = rs1.getInt("playtime");
		}catch(Exception e){
			e.printStackTrace();
		}
		return playtime;
	}
	
	public int findLocalCount(String originId,String channelId,String siteCode,String startTime,String endTime){
		int local = 0;
		try{
			String select = "SELECT COUNT(id) AS local FROM source_xh_media_user_visit_log WHERE ref_id=0 AND origin_id='"+originId+"' AND channle_id='"+channelId+"' AND site_code='"+siteCode+"' AND create_time>='"+startTime+"' AND create_time<'"+endTime+"'";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			if(rs1.next())
				local = rs1.getInt("local");
		}catch(Exception e){
			e.printStackTrace();
		}
		return local;
	}
	
	public int findAppLocalCount(String originId,String startTime,String endTime){
		int local = 0;
		try{
			String select = "SELECT COUNT(id) AS local FROM source_xh_media_user_visit_log WHERE ref_id=0 AND origin_id='"+originId+"' AND create_time>='"+startTime+"' AND create_time<'"+endTime+"'";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			if(rs1.next())
				local = rs1.getInt("local");
		}catch(Exception e){
			e.printStackTrace();
		}
		return local;
	}
	
	public int findSiteLocalCount(String siteCode,String startTime,String endTime){
		int local = 0;
		try{
			String select = "SELECT COUNT(id) AS local FROM source_xh_media_user_visit_log WHERE ref_id=0 AND site_code='"+siteCode+"' AND create_time>='"+startTime+"' AND create_time<'"+endTime+"'";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			if(rs1.next())
				local = rs1.getInt("local");
		}catch(Exception e){
			e.printStackTrace();
		}
		return local;
	}
	
	public int findNonLocalCount(String originId,String channelId,String siteCode,String startTime,String endTime){
		int nonlocal = 0;
		try{
			String select = "SELECT COUNT(id) AS nonlocal FROM source_xh_media_user_visit_log WHERE ref_id!=0 AND origin_id='"+originId+"' AND channle_id='"+channelId+"' AND site_code='"+siteCode+"' AND create_time>='"+startTime+"' AND create_time<'"+endTime+"'";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			if(rs1.next())
				nonlocal = rs1.getInt("nonlocal");
		}catch(Exception e){
			e.printStackTrace();
		}
		return nonlocal;
	}
	
	public int findAppNonLocalCount(String originId,String startTime,String endTime){
		int nonlocal = 0;
		try{
			String select = "SELECT COUNT(id) AS nonlocal FROM source_xh_media_user_visit_log WHERE ref_id!=0 AND origin_id='"+originId+"' AND create_time>='"+startTime+"' AND create_time<'"+endTime+"'";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			if(rs1.next())
				nonlocal = rs1.getInt("nonlocal");
		}catch(Exception e){
			e.printStackTrace();
		}
		return nonlocal;
	}
	
	public int findSiteNonLocalCount(String siteCode,String startTime,String endTime){
		int nonlocal = 0;
		try{
			String select = "SELECT COUNT(id) AS nonlocal FROM source_xh_media_user_visit_log WHERE ref_id!=0 AND site_code='"+siteCode+"' AND create_time>='"+startTime+"' AND create_time<'"+endTime+"'";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			if(rs1.next())
				nonlocal = rs1.getInt("nonlocal");
		}catch(Exception e){
			e.printStackTrace();
		}
		return nonlocal;
	}
	
	public List<SourceVisitBean> getVisitList(String startTime, String endTime){
		List<SourceVisitBean> list = new ArrayList<SourceVisitBean>();
		try{
			String select = "SELECT origin_id,channle_id,site_code FROM source_xh_media_user_visit_log WHERE create_time>='"+startTime+"' AND create_time<'"+endTime+"' GROUP BY channle_id,site_code,origin_id ORDER BY channle_id DESC";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			while(rs1.next()){
				SourceVisitBean svb = new SourceVisitBean();
				svb.setChannelId(rs1.getString("channle_id"));
				svb.setOriginId(rs1.getString("origin_id"));
				svb.setSiteCode(rs1.getString("site_code"));
				list.add(svb);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	public int insertVisitLog(VisitLogBean vlb){
		int result = 1;
		try{
			StringBuffer insert = new StringBuffer("INSERT INTO log_xh_media_visit (date,hour,minute,origin_id,origin_name,channel_id,channel_name,part_id,part_name,start_pv,start_uv,pv,uv,ip,play_time,local,non_local,site_code) VALUES ('");
			insert.append(vlb.getDate()).append("','").append(vlb.getHour()).append("','").append(vlb.getMinute()).append("','").append(vlb.getOriginId()).append("','").append(vlb.getOriginName()).append("','").append(vlb.getChannelId()).append("','")
			.append(vlb.getChannelName()).append("', '").append(vlb.getPartId()).append("', '").append(vlb.getPartName()).append("',").append(vlb.getStartPv()).append(", ").append(vlb.getStartUv()).append(", ").append(vlb.getPv()).append(",").append(vlb.getUv()).append(",").append(vlb.getIp()).append(",").append(vlb.getPlayTime()).append(",").append(vlb.getLocal()).append(",")
			.append(vlb.getNonLocal()).append(",'").append(vlb.getSiteCode()).append("') ON DUPLICATE KEY UPDATE start_pv=VALUES(start_pv),start_uv=VALUES(start_uv),pv=VALUES(pv),uv=VALUES(uv),ip=VALUES(ip),play_time=VALUES(play_time),local=VALUES(local),non_local=VALUES(non_local)");
			dbConn.execute(insert.toString());
		}catch(Exception e){
			e.printStackTrace();
			result = 0;
		}
		return result;
	}
	
	public int insertDayVisitLog(VisitLogBean vlb){
		int result = 1;
		try{
			StringBuffer insert = new StringBuffer("INSERT INTO log_day_xh_media_visit (date,origin_id,origin_name,channel_id,channel_name,part_id,part_name,start_pv,start_uv,pv,uv,ip,play_time,local,non_local,site_code) VALUES ('");
			insert.append(vlb.getDate()).append("','").append(vlb.getOriginId()).append("','").append(vlb.getOriginName()).append("','").append(vlb.getChannelId()).append("','")
			.append(vlb.getChannelName()).append("', '").append(vlb.getPartId()).append("', '").append(vlb.getPartName()).append("',").append(vlb.getStartPv()).append(", ").append(vlb.getStartUv()).append(", ").append(vlb.getPv()).append(",").append(vlb.getUv()).append(",").append(vlb.getIp()).append(",").append(vlb.getPlayTime()).append(",").append(vlb.getLocal()).append(",")
			.append(vlb.getNonLocal()).append(",'").append(vlb.getSiteCode()).append("') ON DUPLICATE KEY UPDATE start_pv=VALUES(start_pv),start_uv=VALUES(start_uv),pv=VALUES(pv),uv=VALUES(uv),ip=VALUES(ip),play_time=VALUES(play_time),local=VALUES(local),non_local=VALUES(non_local)");
//			System.out.println(insert);
			dbConn.execute(insert.toString());
		}catch(Exception e){
			e.printStackTrace();
			result = 0;
		}
		return result;
	}
	
	public int insertVisitLogTest(VisitLogBean vlb){
		int result = 1;
		try{
			StringBuffer insert = new StringBuffer("INSERT INTO log_xh_media_visit (date,hour,minute,origin_id,origin_name,channel_id,channel_name,pv,uv,ip,play_time,local,non_local,site_code) VALUES ('");
			insert.append(vlb.getDate()).append("','").append(vlb.getHour()).append("','").append(vlb.getMinute()).append("','").append(vlb.getOriginId()).append("','").append(vlb.getOriginName()).append("','").append(vlb.getChannelId()).append("','")
			.append(vlb.getChannelName()).append("',").append(vlb.getPv()).append(",").append(vlb.getUv()).append(",").append(vlb.getIp()).append(",").append(vlb.getPlayTime()).append(",").append(vlb.getLocal()).append(",")
			.append(vlb.getNonLocal()).append(",'").append(vlb.getSiteCode()).append("') ON DUPLICATE KEY UPDATE pv=VALUES(pv),uv=VALUES(uv),ip=VALUES(ip),play_time=VALUES(play_time),local=VALUES(local),non_local=VALUES(non_local)");
//			System.out.println(insert);
//			dbConn.execute(insert.toString());
		}catch(Exception e){
			e.printStackTrace();
			result = 0;
		}
		return result;
	}
}
