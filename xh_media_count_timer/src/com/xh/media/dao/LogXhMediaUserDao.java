package com.xh.media.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.xh.media.bean.AppCountBean;
import com.xh.media.bean.SiteCountBean;
import com.xh.media.bean.SourceVisitBean;
import com.xh.media.bean.UserLogBean;
import com.xh.media.util.DbConn;

public class LogXhMediaUserDao {
	private DbConn dbConn;
	public LogXhMediaUserDao(DbConn dbConn){
		this.dbConn = dbConn;
	}
	
	public int findNewUserCount(String originId,String siteCode,String startTime,String endTime){
		int newuser = 0;
		try{
			String select = "SELECT COUNT(DISTINCT user_id) AS newuser FROM source_xh_media_user_visit_log WHERE last_visit_time=0 AND origin_id='"+originId+"' AND site_code='"+siteCode+"' AND create_time>='"+startTime+"' AND create_time<'"+endTime+"'";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			if(rs1.next())
				newuser = rs1.getInt("newuser");
		}catch(Exception e){
			e.printStackTrace();
		}
		return newuser;
	}
	
	public int findAppNewUserCount(String originId,String startTime,String endTime){
		int newuser = 0;
		try{
			String select = "SELECT COUNT(DISTINCT user_id) AS newuser FROM source_xh_media_user_visit_log WHERE last_visit_time=0 AND origin_id='"+originId+"' AND create_time>='"+startTime+"' AND create_time<'"+endTime+"'";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			if(rs1.next())
				newuser = rs1.getInt("newuser");
		}catch(Exception e){
			e.printStackTrace();
		}
		return newuser;
	}
	
	public int findSiteNewUserCount(String siteCode,String startTime,String endTime){
		int newuser = 0;
		try{
			String select = "SELECT COUNT(DISTINCT user_id) AS newuser FROM source_xh_media_user_visit_log WHERE last_visit_time=0 AND site_code='"+siteCode+"' AND create_time>='"+startTime+"' AND create_time<'"+endTime+"'";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			if(rs1.next())
				newuser = rs1.getInt("newuser");
		}catch(Exception e){
			e.printStackTrace();
		}
		return newuser;
	}
	
	public int findActiveUserCount(String originId,String siteCode,String startTime,String endTime){
		int activeuser = 0;
		try{
			String select = "SELECT COUNT(DISTINCT user_id) AS activeuser FROM source_xh_media_user_visit_log WHERE origin_id='"+originId+"' AND site_code='"+siteCode+"' AND create_time>='"+startTime+"' AND create_time<'"+endTime+"'";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			if(rs1.next())
				activeuser = rs1.getInt("activeuser");
		}catch(Exception e){
			e.printStackTrace();
		}
		return activeuser;
	}
	
	public List<SourceVisitBean> getUserList(String startTime, String endTime){
		List<SourceVisitBean> list = new ArrayList<SourceVisitBean>();
		try{
			String select = "SELECT origin_id,site_code FROM source_xh_media_user_visit_log WHERE create_time>='"+startTime+"' AND create_time<'"+endTime+"' GROUP BY origin_id,site_code ORDER BY origin_id DESC";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			while(rs1.next()){
				SourceVisitBean svb = new SourceVisitBean();
				svb.setOriginId(rs1.getString("origin_id"));
				svb.setSiteCode(rs1.getString("site_code"));
				list.add(svb);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	public List<SourceVisitBean> getAppList(String startTime, String endTime){
		List<SourceVisitBean> list = new ArrayList<SourceVisitBean>();
		try{
			String select = "SELECT origin_id FROM source_xh_media_user_visit_log WHERE create_time>='"+startTime+"' AND create_time<'"+endTime+"' GROUP BY origin_id ORDER BY origin_id DESC";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			while(rs1.next()){
				SourceVisitBean svb = new SourceVisitBean();
				svb.setOriginId(rs1.getString("origin_id"));
				svb.setSiteCode("0");
				list.add(svb);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	public List<SourceVisitBean> getSiteList(String startTime, String endTime){
		List<SourceVisitBean> list = new ArrayList<SourceVisitBean>();
		try{
			String select = "SELECT site_code FROM source_xh_media_user_visit_log WHERE create_time>='"+startTime+"' AND create_time<'"+endTime+"' GROUP BY site_code ORDER BY site_code DESC";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			while(rs1.next()){
				SourceVisitBean svb = new SourceVisitBean();
				svb.setSiteCode(rs1.getString("site_code"));
				svb.setOriginId("0");
				list.add(svb);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	public int insertAppCountLog(AppCountBean acb){
		int result = 1;
		try{
			StringBuffer insert = new StringBuffer("INSERT INTO log_xh_media_app_count (date,hour,minute,origin_id,new_user,start_pv,start_uv,local,non_local,comment_number,collection_number,share_number,ip,site_code) VALUES ('");
			insert.append(acb.getDate()).append("','").append(acb.getHour()).append("','").append(acb.getMinute()).append("','").append(acb.getOriginId())
			.append("',").append(acb.getNewUser()).append(",").append(acb.getStartPv()).append(",").append(acb.getStartUv()).append(",").append(acb.getLocal()).append(",")
			.append(acb.getNonLocal()).append(",").append(acb.getCommentNumber()).append(",").append(acb.getCollectionNumber()).append(",").append(acb.getShareNumber()).append(",").append(acb.getIp())
			.append(",'").append(acb.getSiteCode()).append("') ON DUPLICATE KEY UPDATE new_user=VALUES(new_user),start_pv=VALUES(start_pv),start_uv=VALUES(start_uv),local=VALUES(local),non_local=VALUES(non_local),comment_number=VALUES(comment_number),collection_number=VALUES(collection_number),share_number=VALUES(share_number),ip=VALUES(ip)");
			dbConn.execute(insert.toString());
		}catch(Exception e){
			e.printStackTrace();
			result = 0;
		}
		return result;
	}
	
	public int insertSiteCountLog(SiteCountBean acb){
		int result = 1;
		try{
			StringBuffer insert = new StringBuffer("INSERT INTO log_xh_media_site_count (date,hour,minute,origin_id,new_user,start_pv,start_uv,local,non_local,comment_number,collection_number,share_number,ip,site_code) VALUES ('");
			insert.append(acb.getDate()).append("','").append(acb.getHour()).append("','").append(acb.getMinute()).append("','").append(acb.getOriginId())
			.append("',").append(acb.getNewUser()).append(",").append(acb.getStartPv()).append(",").append(acb.getStartUv()).append(",").append(acb.getLocal()).append(",")
			.append(acb.getNonLocal()).append(",").append(acb.getCommentNumber()).append(",").append(acb.getCollectionNumber()).append(",").append(acb.getShareNumber()).append(",").append(acb.getIp())
			.append(",'").append(acb.getSiteCode()).append("') ON DUPLICATE KEY UPDATE new_user=VALUES(new_user),start_pv=VALUES(start_pv),start_uv=VALUES(start_uv),local=VALUES(local),non_local=VALUES(non_local),comment_number=VALUES(comment_number),collection_number=VALUES(collection_number),share_number=VALUES(share_number),ip=VALUES(ip)");
			dbConn.execute(insert.toString());
		}catch(Exception e){
			e.printStackTrace();
			result = 0;
		}
		return result;
	}
	
	public int insertDayAppCountLog(AppCountBean acb){
		int result = 1;
		try{
			StringBuffer insert = new StringBuffer("INSERT INTO log_day_xh_media_app_count (date,site_code,new_user,start_pv,start_uv,local,non_local,comment_number,collection_number,share_number,ip,origin_id) VALUES ('");
			insert.append(acb.getDate()).append("','").append(acb.getSiteCode())
			.append("',").append(acb.getNewUser()).append(",").append(acb.getStartPv()).append(",").append(acb.getStartUv()).append(",").append(acb.getLocal()).append(",")
			.append(acb.getNonLocal()).append(",").append(acb.getCommentNumber()).append(",").append(acb.getCollectionNumber()).append(",").append(acb.getShareNumber()).append(",").append(acb.getIp())
			.append(",'").append(acb.getOriginId()).append("') ON DUPLICATE KEY UPDATE new_user=VALUES(new_user),start_pv=VALUES(start_pv),start_uv=VALUES(start_uv),local=VALUES(local),non_local=VALUES(non_local),comment_number=VALUES(comment_number),collection_number=VALUES(collection_number),share_number=VALUES(share_number),ip=VALUES(ip)");
			dbConn.execute(insert.toString());
		}catch(Exception e){
			e.printStackTrace();
			result = 0;
		}
		return result;
	}
	
	public int insertDaySiteCountLog(SiteCountBean acb){
		int result = 1;
		try{
			StringBuffer insert = new StringBuffer("INSERT INTO log_day_xh_media_site_count (date,site_code,new_user,start_pv,start_uv,local,non_local,comment_number,collection_number,share_number,ip,origin_id) VALUES ('");
			insert.append(acb.getDate()).append("','").append(acb.getSiteCode())
			.append("',").append(acb.getNewUser()).append(",").append(acb.getStartPv()).append(",").append(acb.getStartUv()).append(",").append(acb.getLocal()).append(",")
			.append(acb.getNonLocal()).append(",").append(acb.getCommentNumber()).append(",").append(acb.getCollectionNumber()).append(",").append(acb.getShareNumber()).append(",").append(acb.getIp())
			.append(",'").append(acb.getOriginId()).append("') ON DUPLICATE KEY UPDATE new_user=VALUES(new_user),start_pv=VALUES(start_pv),start_uv=VALUES(start_uv),local=VALUES(local),non_local=VALUES(non_local),comment_number=VALUES(comment_number),collection_number=VALUES(collection_number),share_number=VALUES(share_number),ip=VALUES(ip)");
			dbConn.execute(insert.toString());
		}catch(Exception e){
			e.printStackTrace();
			result = 0;
		}
		return result;
	}
}
