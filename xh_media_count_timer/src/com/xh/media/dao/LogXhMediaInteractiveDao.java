package com.xh.media.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.xh.media.bean.InteractiveLogBean;
import com.xh.media.bean.ProgramLogBean;
import com.xh.media.bean.SourceInteractiveBean;
import com.xh.media.util.DbConn;

public class LogXhMediaInteractiveDao {
	private DbConn dbConn;
	public LogXhMediaInteractiveDao(DbConn dbConn){
		this.dbConn = dbConn;
	}
	
	public int findCommentNumberCount(String globalId,String siteCode,String startTime,String endTime){
		int commentnumber = 0;
		try{
			String select = "SELECT COUNT(id) AS commentnumber FROM source_xh_media_interactive WHERE action=1 AND global_id='"+globalId+"' AND site_code='"+siteCode+"' AND create_time>='"+startTime+"' AND create_time<'"+endTime+"'";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			if(rs1.next())
				commentnumber = rs1.getInt("commentnumber");
		}catch(Exception e){
			e.printStackTrace();
		}
		return commentnumber;
	}
	
	public int findAppCommentNumberCount(String originId,String startTime,String endTime){
		int commentnumber = 0;
		try{
			String select = "SELECT COUNT(id) AS commentnumber FROM source_xh_media_interactive WHERE action=1 AND origin_id='"+originId+"' AND create_time>='"+startTime+"' AND create_time<'"+endTime+"'";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			if(rs1.next())
				commentnumber = rs1.getInt("commentnumber");
		}catch(Exception e){
			e.printStackTrace();
		}
		return commentnumber;
	}
	
	public int findSiteCommentNumberCount(String siteCode,String startTime,String endTime){
		int commentnumber = 0;
		try{
			String select = "SELECT COUNT(id) AS commentnumber FROM source_xh_media_interactive WHERE action=1 AND site_code='"+siteCode+"' AND create_time>='"+startTime+"' AND create_time<'"+endTime+"'";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			if(rs1.next())
				commentnumber = rs1.getInt("commentnumber");
		}catch(Exception e){
			e.printStackTrace();
		}
		return commentnumber;
	}
	
	public int findTopicNumberCount(String globalId,String siteCode,String startTime,String endTime){
		int topicnumber = 0;
		try{
			String select = "SELECT COUNT(id) AS topicnumber FROM source_xh_media_interactive WHERE action=1 AND parent=0 AND global_id='"+globalId+"' AND site_code='"+siteCode+"' AND create_time>='"+startTime+"' AND create_time<'"+endTime+"'";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			if(rs1.next())
				topicnumber = rs1.getInt("topicnumber");
		}catch(Exception e){
			e.printStackTrace();
		}
		return topicnumber;
	}
	
	public int findShareNumberCount(String globalId,String siteCode,String startTime,String endTime){
		int sharenumber = 0;
		try{
			String select = "SELECT COUNT(id) AS sharenumber FROM source_xh_media_interactive WHERE action=4 AND global_id='"+globalId+"' AND site_code='"+siteCode+"' AND create_time>='"+startTime+"' AND create_time<'"+endTime+"'";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			if(rs1.next())
				sharenumber = rs1.getInt("sharenumber");
		}catch(Exception e){
			e.printStackTrace();
		}
		return sharenumber;
	}
	
	public int findAppShareNumberCount(String originId,String startTime,String endTime){
		int sharenumber = 0;
		try{
			String select = "SELECT COUNT(id) AS sharenumber FROM source_xh_media_interactive WHERE action=4 AND origin_id='"+originId+"' AND create_time>='"+startTime+"' AND create_time<'"+endTime+"'";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			if(rs1.next())
				sharenumber = rs1.getInt("sharenumber");
		}catch(Exception e){
			e.printStackTrace();
		}
		return sharenumber;
	}
	
	public int findSiteShareNumberCount(String siteCode,String startTime,String endTime){
		int sharenumber = 0;
		try{
			String select = "SELECT COUNT(id) AS sharenumber FROM source_xh_media_interactive WHERE action=4 AND site_code='"+siteCode+"' AND create_time>='"+startTime+"' AND create_time<'"+endTime+"'";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			if(rs1.next())
				sharenumber = rs1.getInt("sharenumber");
		}catch(Exception e){
			e.printStackTrace();
		}
		return sharenumber;
	}
	
	public int findCollectionNumberCount(String globalId,String siteCode,String startTime,String endTime){
		int collectionnumber = 0;
		try{
			String select = "SELECT COUNT(id) AS collectionnumber FROM source_xh_media_interactive WHERE action=2 AND global_id='"+globalId+"' AND site_code='"+siteCode+"' AND create_time>='"+startTime+"' AND create_time<'"+endTime+"'";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			if(rs1.next())
				collectionnumber = rs1.getInt("collectionnumber");
		}catch(Exception e){
			e.printStackTrace();
		}
		return collectionnumber;
	}
	
	public int findAppCollectionNumberCount(String originId,String startTime,String endTime){
		int collectionnumber = 0;
		try{
			String select = "SELECT COUNT(id) AS collectionnumber FROM source_xh_media_interactive WHERE action=2 AND origin_id='"+originId+"' AND create_time>='"+startTime+"' AND create_time<'"+endTime+"'";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			if(rs1.next())
				collectionnumber = rs1.getInt("collectionnumber");
		}catch(Exception e){
			e.printStackTrace();
		}
		return collectionnumber;
	}
	
	public int findSiteCollectionNumberCount(String siteCode,String startTime,String endTime){
		int collectionnumber = 0;
		try{
			String select = "SELECT COUNT(id) AS collectionnumber FROM source_xh_media_interactive WHERE action=2 AND site_code='"+siteCode+"' AND create_time>='"+startTime+"' AND create_time<'"+endTime+"'";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			if(rs1.next())
				collectionnumber = rs1.getInt("collectionnumber");
		}catch(Exception e){
			e.printStackTrace();
		}
		return collectionnumber;
	}
	
	public List<SourceInteractiveBean> getInteractiveList(String startTime, String endTime){
		List<SourceInteractiveBean> list = new ArrayList<SourceInteractiveBean>();
		try{
			String select = "SELECT global_id,origin_id,site_code FROM source_xh_media_interactive WHERE create_time>='"+startTime+"' AND create_time<'"+endTime+"' GROUP BY global_id,site_code ORDER BY global_id DESC";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			while(rs1.next()){
				SourceInteractiveBean sib = new SourceInteractiveBean();
				sib.setGlobalId(rs1.getString("global_id"));
				sib.setSiteCode(rs1.getString("site_code"));
				sib.setOriginId(rs1.getString("origin_id"));
				list.add(sib);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	public int insertInteractiveLog(InteractiveLogBean ilb){
		int result = 1;
		try{
			StringBuffer insert = new StringBuffer("INSERT INTO log_xh_media_interactive (date,hour,minute,origin_id,origin_name,channel_id,channel_name,global_id,chinese_name,source,type,comment_number,topic_number,share_number,collection_number,site_code) VALUES ('");
			insert.append(ilb.getDate()).append("','").append(ilb.getHour()).append("','").append(ilb.getMinute()).append("','").append(ilb.getOriginId()).append("','").append(ilb.getOriginName()).append("','").append(ilb.getChannelId()).append("','")
			.append(ilb.getChannelName()).append("','").append(ilb.getGlobalId()).append("','").append(ilb.getChineseName()).append("','").append(ilb.getType()).append("','").append(ilb.getSource()).append("',").append(ilb.getCommentNumber()).append(",").append(ilb.getTopicNumber()).append(",")
			.append(ilb.getShareNumber()).append(",").append(ilb.getCollectionNumber()).append(",'").append(ilb.getSiteCode()).append("') ON DUPLICATE KEY UPDATE comment_number=VALUES(comment_number),topic_number=VALUES(topic_number),share_number=VALUES(share_number),collection_number=VALUES(collection_number)");
			dbConn.execute(insert.toString());
		}catch(Exception e){
			e.printStackTrace();
			result = 0;
		}
		return result;
	}
	
	public int insertDayInteractiveLog(InteractiveLogBean ilb){
		int result = 1;
		try{
			StringBuffer insert = new StringBuffer("INSERT INTO log_day_xh_media_interactive (date,origin_id,origin_name,channel_id,channel_name,global_id,chinese_name,source,type,comment_number,topic_number,share_number,collection_number,site_code) VALUES ('");
			insert.append(ilb.getDate()).append("','").append(ilb.getOriginId()).append("','").append(ilb.getOriginName()).append("','").append(ilb.getChannelId()).append("','")
			.append(ilb.getChannelName()).append("','").append(ilb.getGlobalId()).append("','").append(ilb.getChineseName()).append("','").append(ilb.getType()).append("','").append(ilb.getSource()).append("',").append(ilb.getCommentNumber()).append(",").append(ilb.getTopicNumber()).append(",")
			.append(ilb.getShareNumber()).append(",").append(ilb.getCollectionNumber()).append(",'").append(ilb.getSiteCode()).append("') ON DUPLICATE KEY UPDATE comment_number=VALUES(comment_number),topic_number=VALUES(topic_number),share_number=VALUES(share_number),collection_number=VALUES(collection_number)");
			dbConn.execute(insert.toString());
		}catch(Exception e){
			e.printStackTrace();
			result = 0;
		}
		return result;
	}
	
	public int insertInteractiveLogTest(InteractiveLogBean ilb){
		int result = 1;
		try{
			StringBuffer insert = new StringBuffer("INSERT INTO log_xh_media_interactive (date,hour,minute,origin_id,origin_name,channel_id,channel_name,global_id,chinese_name,source,type,comment_number,topic_number,share_number,collection_number,site_code) VALUES ('");
			insert.append(ilb.getDate()).append("','").append(ilb.getHour()).append("','").append(ilb.getMinute()).append("','").append(ilb.getOriginId()).append("','").append(ilb.getOriginName()).append("','").append(ilb.getChannelId()).append("','")
			.append(ilb.getChannelName()).append("','").append(ilb.getGlobalId()).append("','").append(ilb.getChineseName()).append("','").append(ilb.getType()).append("','").append(ilb.getSource()).append("',").append(ilb.getCommentNumber()).append(",").append(ilb.getTopicNumber()).append(",")
			.append(ilb.getShareNumber()).append(",").append(ilb.getCollectionNumber()).append(",'").append(ilb.getSiteCode()).append("') ON DUPLICATE KEY UPDATE comment_number=VALUES(comment_number),topic_number=VALUES(topic_number),share_number=VALUES(share_number),collection_number=VALUES(collection_number)");
//			System.out.println(insert);
//			dbConn.execute(insert.toString());
		}catch(Exception e){
			e.printStackTrace();
			result = 0;
		}
		return result;
	}
}
