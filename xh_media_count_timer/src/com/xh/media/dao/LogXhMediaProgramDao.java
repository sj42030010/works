package com.xh.media.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.xh.media.bean.ProgramLogBean;
import com.xh.media.bean.ProgramLogNewBean;
import com.xh.media.bean.SourceVisitBean;
import com.xh.media.util.DbConn;

public class LogXhMediaProgramDao {
	private DbConn dbConn;
	public LogXhMediaProgramDao(DbConn dbConn){
		this.dbConn = dbConn;
	}
	
	public int findPlayTimeCount(String originId,String channelId,String globalId,String siteCode,String startTime,String endTime){
		int playtime = 0;
		try{
			String select = "SELECT SUM(play_time) AS playtime FROM source_xh_media_user_visit_log WHERE origin_id='"+originId+"' AND channle_id='"+channelId+"' AND global_id='"+globalId+"' AND site_code='"+siteCode+"' AND create_time>='"+startTime+"' AND create_time<'"+endTime+"'";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			if(rs1.next())
				playtime = rs1.getInt("playtime");
		}catch(Exception e){
			e.printStackTrace();
		}
		return playtime;
	}
	
	public int findPlayNumberCount(String originId,String channelId,String globalId,String siteCode,String startTime,String endTime){
		int playnumber = 0;
		try{
			String select = "SELECT COUNT(id) AS playnumber FROM source_xh_media_user_visit_log WHERE play_time>0 AND origin_id='"+originId+"' AND channle_id='"+channelId+"' AND global_id='"+globalId+"' AND site_code='"+siteCode+"' AND create_time>='"+startTime+"' AND create_time<'"+endTime+"'";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			if(rs1.next())
				playnumber = rs1.getInt("playnumber");
		}catch(Exception e){
			e.printStackTrace();
		}
		return playnumber;
	}
	
	public int findPlayUserNumberCount(String originId,String channelId,String globalId,String siteCode,String startTime,String endTime){
		int playusernumber = 0;
		try{
			String select = "SELECT COUNT(DISTINCT user_id) AS playusernumber FROM source_xh_media_user_visit_log WHERE play_time>0 AND origin_id='"+originId+"' AND channle_id='"+channelId+"' AND global_id='"+globalId+"' AND site_code='"+siteCode+"' AND create_time>='"+startTime+"' AND create_time<'"+endTime+"'";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			if(rs1.next())
				playusernumber = rs1.getInt("playusernumber");
		}catch(Exception e){
			e.printStackTrace();
		}
		return playusernumber;
	}
	
	public int findPvCount(String originId,String channelId,String globalId,String siteCode,String startTime,String endTime){
		int pv = 0;
		try{
			String select = "SELECT COUNT(id) AS pv FROM source_xh_media_user_visit_log WHERE origin_id='"+originId+"' AND channle_id='"+channelId+"' AND global_id='"+globalId+"' AND site_code='"+siteCode+"' AND create_time>='"+startTime+"' AND create_time<'"+endTime+"'";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			if(rs1.next())
				pv = rs1.getInt("pv");
		}catch(Exception e){
			e.printStackTrace();
		}
		return pv;
	}
	
	public int findUvCount(String originId,String channelId,String globalId,String siteCode,String startTime,String endTime){
		int uv = 0;
		try{
			String select = "SELECT COUNT(DISTINCT user_id) AS uv FROM source_xh_media_user_visit_log WHERE origin_id='"+originId+"' AND channle_id='"+channelId+"' AND global_id='"+globalId+"' AND site_code='"+siteCode+"' AND create_time>='"+startTime+"' AND create_time<'"+endTime+"'";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			if(rs1.next())
				uv = rs1.getInt("uv");
		}catch(Exception e){
			e.printStackTrace();
		}
		return uv;
	}
	
	public int findIpCount(String originId,String channelId,String globalId,String siteCode,String startTime,String endTime){
		int ip = 0;
		try{
			String select = "SELECT COUNT(DISTINCT ip) AS ip FROM source_xh_media_user_visit_log WHERE origin_id='"+originId+"' AND channle_id='"+channelId+"' AND global_id='"+globalId+"' AND site_code='"+siteCode+"' AND create_time>='"+startTime+"' AND create_time<'"+endTime+"'";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			if(rs1.next())
				ip = rs1.getInt("ip");
		}catch(Exception e){
			e.printStackTrace();
		}
		return ip;
	}
	
	public List<SourceVisitBean> getProgramList(String startTime, String endTime){
		List<SourceVisitBean> list = new ArrayList<SourceVisitBean>();
		try{
			String select = "SELECT origin_id,channle_id,global_id,site_code FROM source_xh_media_user_visit_log WHERE global_id !=0 AND create_time>='"+startTime+"' AND create_time<'"+endTime+"' GROUP BY global_id,site_code,channle_id,origin_id ORDER BY global_id DESC";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			while(rs1.next()){
				SourceVisitBean svb = new SourceVisitBean();
				svb.setOriginId(rs1.getString("origin_id"));
				svb.setChannelId(rs1.getString("channle_id"));
				svb.setGlobalId(rs1.getString("global_id"));
				svb.setSiteCode(rs1.getString("site_code"));
				list.add(svb);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	public int insertProgramLog(ProgramLogBean plb){
		int result = 1;
		try{
			StringBuffer insert = new StringBuffer("INSERT INTO log_xh_media_program_analysis (date,hour,minute,origin_id,origin_name,channel_id,channel_name,part_id,part_name,global_id,chinese_name,pub_date,type,play_time,time_length,play_number,play_user_number,source,pv,uv,ip,site_code) VALUES ('");
			insert.append(plb.getDate()).append("','").append(plb.getHour()).append("','").append(plb.getMinute()).append("','").append(plb.getOriginId()).append("','").append(plb.getOriginName()).append("','").append(plb.getChannelId()).append("','")
			.append(plb.getChannelName()).append("','").append(plb.getPartId()).append("', '").append(plb.getPartName()).append("', '").append(plb.getGlobalId()).append("','").append(plb.getChineseName()).append("','").append(plb.getPubDate()).append("','").append(plb.getType()).append("',").append(plb.getPlayTime()).append(",").append(plb.getTimeLength()).append(",")
			.append(plb.getPlayNumber()).append(",").append(plb.getPlayUserNumber()).append(",'").append(plb.getSource()).append("',").append(plb.getPv()).append(",").append(plb.getUv()).append(",").append(plb.getIp()).append(",'").append(plb.getSiteCode()).append("') ON DUPLICATE KEY UPDATE play_time=VALUES(play_time),time_length=VALUES(time_length),play_number=VALUES(play_number),play_user_number=VALUES(play_user_number),pv=VALUES(pv),uv=VALUES(uv),ip=VALUES(ip)");
//			System.out.println(insert);
			dbConn.execute(insert.toString());
		}catch(Exception e){
			e.printStackTrace();
			result = 0;
		}
		return result;
	}
	
	public int insertDayProgramLog(ProgramLogBean plb){
		int result = 1;
		try{
			StringBuffer insert = new StringBuffer("INSERT INTO log_day_xh_media_program_analysis (date,origin_id,origin_name,channel_id,channel_name,part_id,part_name,global_id,chinese_name,pub_date,type,play_time,time_length,play_number,play_user_number,source,pv,uv,ip,site_code) VALUES ('");
			insert.append(plb.getDate()).append("','").append(plb.getOriginId()).append("','").append(plb.getOriginName()).append("','").append(plb.getChannelId()).append("','")
			.append(plb.getChannelName()).append("','").append(plb.getPartId()).append("','").append(plb.getPartName()).append("','").append(plb.getGlobalId()).append("','").append(plb.getChineseName()).append("','").append(plb.getPubDate()).append("','").append(plb.getType()).append("',").append(plb.getPlayTime()).append(",").append(plb.getTimeLength()).append(",")
			.append(plb.getPlayNumber()).append(",").append(plb.getPlayUserNumber()).append(",'").append(plb.getSource()).append("',").append(plb.getPv()).append(",").append(plb.getUv()).append(",").append(plb.getIp()).append(",'").append(plb.getSiteCode()).append("') ON DUPLICATE KEY UPDATE play_time=VALUES(play_time),time_length=VALUES(time_length),play_number=VALUES(play_number),play_user_number=VALUES(play_user_number),pv=VALUES(pv),uv=VALUES(uv),ip=VALUES(ip)");
//			System.out.println(insert);
			dbConn.execute(insert.toString());
		}catch(Exception e){
			e.printStackTrace();
			result = 0;
		}
		return result;
	}
	
	public int insertProgramLogNew(ProgramLogNewBean plnb){
		int result = 1;
		try{
			StringBuffer insert = new StringBuffer("INSERT INTO log_xh_media_program_new (date,hour,minute,site_code,origin_id,channel_id,part_id,global_id,play_number,play_user_number,comment_number,collection_number,share_number,time_length,play_time,pub_date,source) VALUES ('");
			insert.append(plnb.getDate()).append("','").append(plnb.getHour()).append("','").append(plnb.getMinute()).append("','").append(plnb.getSiteCode()).append("','").append(plnb.getOriginId()).append("','").append(plnb.getChannelId()).append("','").append(plnb.getPartId()).append("', '").append(plnb.getGlobalId()).append("',").append(plnb.getPlayNumber()).append(",").append(plnb.getPlayUserNumber()).append(",").append(plnb.getCommentNumber()).append(",").append(plnb.getCollectionNumber()).append(",")
			.append(plnb.getShareNumber()).append(",").append(plnb.getTimeLength()).append(",").append(plnb.getPlayTime()).append(",'").append(plnb.getPubDate()).append("','").append(plnb.getSource()).append("') ON DUPLICATE KEY UPDATE play_time=VALUES(play_time),time_length=VALUES(time_length),play_number=VALUES(play_number),play_user_number=VALUES(play_user_number),comment_number=VALUES(comment_number),collection_number=VALUES(collection_number),share_number=VALUES(share_number)");
//			System.out.println(insert);
			dbConn.execute(insert.toString());
		}catch(Exception e){
			e.printStackTrace();
			result = 0;
		}
		return result;
	}
	
	public int insertDayProgramLogNew(ProgramLogNewBean plnb){
		int result = 1;
		try{
			StringBuffer insert = new StringBuffer("INSERT INTO log_day_xh_media_program_new (date,site_code,origin_id,channel_id,part_id,global_id,play_number,play_user_number,comment_number,collection_number,share_number,time_length,play_time,pub_date,source) VALUES ('");
			insert.append(plnb.getDate()).append("','").append(plnb.getSiteCode()).append("','").append(plnb.getOriginId()).append("','").append(plnb.getChannelId()).append("','").append(plnb.getPartId()).append("', '").append(plnb.getGlobalId()).append("',").append(plnb.getPlayNumber()).append(",").append(plnb.getPlayUserNumber()).append(",").append(plnb.getCommentNumber()).append(",").append(plnb.getCollectionNumber()).append(",")
			.append(plnb.getShareNumber()).append(",").append(plnb.getTimeLength()).append(",").append(plnb.getPlayTime()).append(",'").append(plnb.getPubDate()).append("','").append(plnb.getSource()).append("') ON DUPLICATE KEY UPDATE play_time=VALUES(play_time),time_length=VALUES(time_length),play_number=VALUES(play_number),play_user_number=VALUES(play_user_number),comment_number=VALUES(comment_number),collection_number=VALUES(collection_number),share_number=VALUES(share_number)");
//			System.out.println(insert);
			dbConn.execute(insert.toString());
		}catch(Exception e){
			e.printStackTrace();
			result = 0;
		}
		return result;
	}
	
	public int insertDayProgramLogNewSummary(ProgramLogNewBean plnb){
		int result = 1;
		try{
			StringBuffer insert = new StringBuffer("INSERT INTO log_day_xh_media_program_new_summary (date,site_code,origin_id,channel_id,part_id,global_id,play_number,play_user_number,comment_number,collection_number,share_number,time_length,play_time,pub_date,source) VALUES ('");
			insert.append(plnb.getDate()).append("','").append(plnb.getSiteCode()).append("','").append(plnb.getOriginId()).append("','").append(plnb.getChannelId()).append("','").append(plnb.getPartId()).append("', '").append(plnb.getGlobalId()).append("',").append(plnb.getPlayNumber()).append(",").append(plnb.getPlayUserNumber()).append(",").append(plnb.getCommentNumber()).append(",").append(plnb.getCollectionNumber()).append(",")
			.append(plnb.getShareNumber()).append(",").append(plnb.getTimeLength()).append(",").append(plnb.getPlayTime()).append(",'").append(plnb.getPubDate()).append("','0') ON DUPLICATE KEY UPDATE play_time=VALUES(play_time),time_length=VALUES(time_length),play_number=VALUES(play_number),play_user_number=VALUES(play_user_number),comment_number=VALUES(comment_number),collection_number=VALUES(collection_number),share_number=VALUES(share_number)");
//			System.out.println(insert);
			dbConn.execute(insert.toString());
		}catch(Exception e){
			e.printStackTrace();
			result = 0;
		}
		return result;
	}
	
	public int insertProgramLogTest(ProgramLogBean plb){
		int result = 1;
		try{
			StringBuffer insert = new StringBuffer("INSERT INTO log_xh_media_program_analysis (date,hour,minute,origin_id,origin_name,channel_id,channel_name,part_id,part_name,global_id,chinese_name,pub_date,type,play_time,time_length,play_number,play_user_number,source,pv,uv,ip,site_code) VALUES ('");
			insert.append(plb.getDate()).append("','").append(plb.getHour()).append("','").append(plb.getMinute()).append("','").append(plb.getOriginId()).append("','").append(plb.getOriginName()).append("','").append(plb.getChannelId()).append("','")
			.append(plb.getChannelName()).append("','").append(plb.getPartId()).append("','").append(plb.getPartName()).append("','").append(plb.getGlobalId()).append("','").append(plb.getChineseName()).append("','").append(plb.getPubDate()).append("','").append(plb.getType()).append("',").append(plb.getPlayTime()).append(",").append(plb.getTimeLength()).append(",")
			.append(plb.getPlayNumber()).append(",").append(plb.getPlayUserNumber()).append(",'").append(plb.getSource()).append("',").append(plb.getPv()).append(",").append(plb.getUv()).append(",").append(plb.getIp()).append(",'").append(plb.getSiteCode()).append("') ON DUPLICATE KEY UPDATE play_time=VALUES(play_time),time_length=VALUES(time_length),play_number=VALUES(play_number),play_user_number=VALUES(play_user_number),pv=VALUES(pv),uv=VALUES(uv),ip=VALUES(ip)");
//			System.out.println(insert);
//			dbConn.execute(insert.toString());
		}catch(Exception e){
			e.printStackTrace();
			result = 0;
		}
		return result;
	}
	
	//以下为处理推荐节目汇总的相关方法
	
	public List<ProgramLogBean> getAllNotCommendProgramList(String date, String hour, String minute){
		List<ProgramLogBean> list = new ArrayList<ProgramLogBean>();
		try{
			String select = "SELECT date,hour,minute,origin_id,origin_name,channel_id,channel_name,part_id,part_name,global_id,chinese_name,pub_date,type,play_time,time_length,play_number,play_user_number,source,pv,uv,ip,site_code FROM log_xh_media_program_analysis WHERE date='"+date+"' AND hour='"+hour+"' AND minute='"+minute+"' AND source<100";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			while(rs1.next()){
				ProgramLogBean plb = new ProgramLogBean();
				plb.setDate(rs1.getString("date"));
				plb.setHour(rs1.getString("hour"));
				plb.setMinute(rs1.getString("minute"));
				plb.setOriginId(rs1.getString("origin_id"));
				plb.setOriginName(rs1.getString("origin_name"));
				plb.setChannelId(rs1.getString("channel_id"));
				plb.setChannelName(rs1.getString("channel_name"));
				plb.setPartId(rs1.getString("part_id"));
				plb.setPartName(rs1.getString("part_name"));
				plb.setGlobalId(rs1.getString("global_id"));
				plb.setChineseName(rs1.getString("chinese_name"));
				plb.setPubDate(rs1.getString("pub_date"));
				plb.setType(rs1.getInt("type"));
				plb.setPlayTime(rs1.getInt("play_time"));
				plb.setTimeLength(rs1.getInt("time_length"));
				plb.setPlayNumber(rs1.getInt("play_number"));
				plb.setPlayUserNumber(rs1.getInt("play_user_number"));
				plb.setSource(rs1.getString("source"));
				plb.setPv(rs1.getInt("pv"));
				plb.setUv(rs1.getInt("uv"));
				plb.setIp(rs1.getInt("ip"));
				plb.setSiteCode(rs1.getString("site_code"));
				list.add(plb);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	public List<ProgramLogNewBean> getAllNotCommendProgramNewList(String date, String hour, String minute){
		List<ProgramLogNewBean> list = new ArrayList<ProgramLogNewBean>();
		try{
			String select = "SELECT date,hour,minute,site_code,origin_id,channel_id,part_id,global_id,play_number,play_user_number,comment_number,collection_number,share_number,time_length,play_time,pub_date,source FROM log_xh_media_program_new WHERE date='"+date+"' AND hour='"+hour+"' AND minute='"+minute+"' AND source<100";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			while(rs1.next()){
				ProgramLogNewBean plnb = new ProgramLogNewBean();
				plnb.setDate(rs1.getString("date"));
				plnb.setHour(rs1.getString("hour"));
				plnb.setMinute(rs1.getString("minute"));
				plnb.setOriginId(rs1.getString("origin_id"));
				plnb.setChannelId(rs1.getString("channel_id"));
				plnb.setPartId(rs1.getString("part_id"));
				plnb.setGlobalId(rs1.getString("global_id"));
				plnb.setPubDate(rs1.getString("pub_date"));
				plnb.setPlayTime(rs1.getInt("play_time"));
				plnb.setTimeLength(rs1.getInt("time_length"));
				plnb.setPlayNumber(rs1.getInt("play_number"));
				plnb.setPlayUserNumber(rs1.getInt("play_user_number"));
				plnb.setSource(rs1.getString("source"));
				plnb.setSiteCode(rs1.getString("site_code"));
				plnb.setCommentNumber(rs1.getInt("comment_number"));
				plnb.setCollectionNumber(rs1.getInt("collection_number"));
				plnb.setShareNumber(rs1.getInt("share_number"));
				list.add(plnb);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	public List<ProgramLogBean> getAllCommendProgramList(String date, String hour, String minute){
		List<ProgramLogBean> list = new ArrayList<ProgramLogBean>();
		try{
			String select = "SELECT date,hour,minute,origin_id,origin_name,channel_id,channel_name,part_id,part_name,global_id,chinese_name,pub_date,type,play_time,time_length,play_number,play_user_number,source,pv,uv,ip,site_code FROM log_xh_media_program_analysis WHERE date='"+date+"' AND hour='"+hour+"' AND minute='"+minute+"' AND source>100";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			while(rs1.next()){
				ProgramLogBean plb = new ProgramLogBean();
				plb.setDate(rs1.getString("date"));
				plb.setHour(rs1.getString("hour"));
				plb.setMinute(rs1.getString("minute"));
				plb.setOriginId(rs1.getString("origin_id"));
				plb.setOriginName(rs1.getString("origin_name"));
				plb.setChannelId(rs1.getString("channel_id"));
				plb.setChannelName(rs1.getString("channel_name"));
				plb.setPartId(rs1.getString("part_id"));
				plb.setPartName(rs1.getString("part_name"));
				plb.setGlobalId(rs1.getString("global_id"));
				plb.setChineseName(rs1.getString("chinese_name"));
				plb.setPubDate(rs1.getString("pub_date"));
				plb.setType(rs1.getInt("type"));
				plb.setPlayTime(rs1.getInt("play_time"));
				plb.setTimeLength(rs1.getInt("time_length"));
				plb.setPlayNumber(rs1.getInt("play_number"));
				plb.setPlayUserNumber(rs1.getInt("play_user_number"));
				plb.setSource(rs1.getString("source"));
				plb.setPv(rs1.getInt("pv"));
				plb.setUv(rs1.getInt("uv"));
				plb.setIp(rs1.getInt("ip"));
				plb.setSiteCode(rs1.getString("site_code"));
				list.add(plb);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	public List<ProgramLogNewBean> getAllCommendProgramNewList(String date, String hour, String minute){
		List<ProgramLogNewBean> list = new ArrayList<ProgramLogNewBean>();
		try{
			String select = "SELECT date,hour,minute,site_code,origin_id,channel_id,part_id,global_id,play_number,play_user_number,comment_number,collection_number,share_number,time_length,play_time,pub_date,source FROM log_xh_media_program_new WHERE date='"+date+"' AND source>100";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			while(rs1.next()){
				ProgramLogNewBean plnb = new ProgramLogNewBean();
				plnb.setDate(rs1.getString("date"));
				plnb.setHour(rs1.getString("hour"));
				plnb.setMinute(rs1.getString("minute"));
				plnb.setOriginId(rs1.getString("origin_id"));
				plnb.setChannelId(rs1.getString("channel_id"));
				plnb.setPartId(rs1.getString("part_id"));
				plnb.setGlobalId(rs1.getString("global_id"));
				plnb.setPubDate(rs1.getString("pub_date"));
				plnb.setPlayTime(rs1.getInt("play_time"));
				plnb.setTimeLength(rs1.getInt("time_length"));
				plnb.setPlayNumber(rs1.getInt("play_number"));
				plnb.setPlayUserNumber(rs1.getInt("play_user_number"));
				plnb.setSource(rs1.getString("source"));
				plnb.setSiteCode(rs1.getString("site_code"));
				plnb.setCommentNumber(rs1.getInt("comment_number"));
				plnb.setCollectionNumber(rs1.getInt("collection_number"));
				plnb.setShareNumber(rs1.getInt("share_number"));
				list.add(plnb);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	public List<ProgramLogBean> getDayAllNotCommendProgramList(String date, String hour, String minute){
		List<ProgramLogBean> list = new ArrayList<ProgramLogBean>();
		try{
			String select = "SELECT date,origin_id,origin_name,channel_id,channel_name,part_id,part_name,global_id,chinese_name,pub_date,type,play_time,time_length,play_number,play_user_number,source,pv,uv,ip,site_code FROM log_day_xh_media_program_analysis WHERE date='"+date+"' AND source<100";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			while(rs1.next()){
				ProgramLogBean plb = new ProgramLogBean();
				plb.setDate(rs1.getString("date"));
				plb.setOriginId(rs1.getString("origin_id"));
				plb.setOriginName(rs1.getString("origin_name"));
				plb.setChannelId(rs1.getString("channel_id"));
				plb.setChannelName(rs1.getString("channel_name"));
				plb.setPartId(rs1.getString("part_id"));
				plb.setPartName(rs1.getString("part_name"));
				plb.setGlobalId(rs1.getString("global_id"));
				plb.setChineseName(rs1.getString("chinese_name"));
				plb.setPubDate(rs1.getString("pub_date"));
				plb.setType(rs1.getInt("type"));
				plb.setPlayTime(rs1.getInt("play_time"));
				plb.setTimeLength(rs1.getInt("time_length"));
				plb.setPlayNumber(rs1.getInt("play_number"));
				plb.setPlayUserNumber(rs1.getInt("play_user_number"));
				plb.setSource(rs1.getString("source"));
				plb.setPv(rs1.getInt("pv"));
				plb.setUv(rs1.getInt("uv"));
				plb.setIp(rs1.getInt("ip"));
				plb.setSiteCode(rs1.getString("site_code"));
				list.add(plb);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	public List<ProgramLogNewBean> getDayAllNotCommendProgramNewList(String date, String hour, String minute){
		List<ProgramLogNewBean> list = new ArrayList<ProgramLogNewBean>();
		try{
			String select = "SELECT date,site_code,origin_id,channel_id,part_id,global_id,play_number,play_user_number,comment_number,collection_number,share_number,time_length,play_time,pub_date,source FROM log_day_xh_media_program_new WHERE date='"+date+"' AND source<100";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			while(rs1.next()){
				ProgramLogNewBean plnb = new ProgramLogNewBean();
				plnb.setDate(rs1.getString("date"));
				plnb.setOriginId(rs1.getString("origin_id"));
				plnb.setChannelId(rs1.getString("channel_id"));
				plnb.setPartId(rs1.getString("part_id"));
				plnb.setGlobalId(rs1.getString("global_id"));
				plnb.setPubDate(rs1.getString("pub_date"));
				plnb.setPlayTime(rs1.getInt("play_time"));
				plnb.setTimeLength(rs1.getInt("time_length"));
				plnb.setPlayNumber(rs1.getInt("play_number"));
				plnb.setPlayUserNumber(rs1.getInt("play_user_number"));
				plnb.setSource(rs1.getString("source"));
				plnb.setSiteCode(rs1.getString("site_code"));
				plnb.setCommentNumber(rs1.getInt("comment_number"));
				plnb.setCollectionNumber(rs1.getInt("collection_number"));
				plnb.setShareNumber(rs1.getInt("share_number"));
				list.add(plnb);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	public List<ProgramLogBean> getDayAllCommendProgramList(String date, String hour, String minute){
		List<ProgramLogBean> list = new ArrayList<ProgramLogBean>();
		try{
			String select = "SELECT date,origin_id,origin_name,channel_id,channel_name,part_id,part_name,global_id,chinese_name,pub_date,type,play_time,time_length,play_number,play_user_number,source,pv,uv,ip,site_code FROM log_day_xh_media_program_analysis WHERE date='"+date+"' AND source>100";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			while(rs1.next()){
				ProgramLogBean plb = new ProgramLogBean();
				plb.setDate(rs1.getString("date"));
				plb.setOriginId(rs1.getString("origin_id"));
				plb.setOriginName(rs1.getString("origin_name"));
				plb.setChannelId(rs1.getString("channel_id"));
				plb.setChannelName(rs1.getString("channel_name"));
				plb.setPartId(rs1.getString("part_id"));
				plb.setPartName(rs1.getString("part_name"));
				plb.setGlobalId(rs1.getString("global_id"));
				plb.setChineseName(rs1.getString("chinese_name"));
				plb.setPubDate(rs1.getString("pub_date"));
				plb.setType(rs1.getInt("type"));
				plb.setPlayTime(rs1.getInt("play_time"));
				plb.setTimeLength(rs1.getInt("time_length"));
				plb.setPlayNumber(rs1.getInt("play_number"));
				plb.setPlayUserNumber(rs1.getInt("play_user_number"));
				plb.setSource(rs1.getString("source"));
				plb.setPv(rs1.getInt("pv"));
				plb.setUv(rs1.getInt("uv"));
				plb.setIp(rs1.getInt("ip"));
				plb.setSiteCode(rs1.getString("site_code"));
				list.add(plb);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	public List<ProgramLogNewBean> getDayAllCommendProgramNewList(String date, String hour, String minute){
		List<ProgramLogNewBean> list = new ArrayList<ProgramLogNewBean>();
		try{
			String select = "SELECT date,site_code,origin_id,channel_id,part_id,global_id,play_number,play_user_number,comment_number,collection_number,share_number,time_length,play_time,pub_date,source FROM log_day_xh_media_program_new WHERE date='"+date+"' AND source>100";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			while(rs1.next()){
				ProgramLogNewBean plnb = new ProgramLogNewBean();
				plnb.setDate(rs1.getString("date"));
				plnb.setOriginId(rs1.getString("origin_id"));
				plnb.setChannelId(rs1.getString("channel_id"));
				plnb.setPartId(rs1.getString("part_id"));
				plnb.setGlobalId(rs1.getString("global_id"));
				plnb.setPubDate(rs1.getString("pub_date"));
				plnb.setPlayTime(rs1.getInt("play_time"));
				plnb.setTimeLength(rs1.getInt("time_length"));
				plnb.setPlayNumber(rs1.getInt("play_number"));
				plnb.setPlayUserNumber(rs1.getInt("play_user_number"));
				plnb.setSource(rs1.getString("source"));
				plnb.setSiteCode(rs1.getString("site_code"));
				plnb.setCommentNumber(rs1.getInt("comment_number"));
				plnb.setCollectionNumber(rs1.getInt("collection_number"));
				plnb.setShareNumber(rs1.getInt("share_number"));
				list.add(plnb);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	public int insertProgramLogSummary(ProgramLogBean plb){
		int result = 1;
		try{
			StringBuffer insert = new StringBuffer("INSERT INTO log_xh_media_program_analysis_summary (date,hour,minute,origin_id,origin_name,channel_id,channel_name,part_id,part_name,global_id,chinese_name,pub_date,type,play_time,time_length,play_number,play_user_number,source,pv,uv,ip,site_code) VALUES ('");
			insert.append(plb.getDate()).append("','").append(plb.getHour()).append("','").append(plb.getMinute()).append("','").append(plb.getOriginId()).append("','").append(plb.getOriginName()).append("','").append(plb.getChannelId()).append("','")
			.append(plb.getChannelName()).append("','").append(plb.getPartId()).append("','").append(plb.getPartName()).append("','").append(plb.getGlobalId()).append("','").append(plb.getChineseName()).append("','").append(plb.getPubDate()).append("','").append(plb.getType()).append("',").append(plb.getPlayTime()).append(",").append(plb.getTimeLength()).append(",")
			.append(plb.getPlayNumber()).append(",").append(plb.getPlayUserNumber()).append(",'0',").append(plb.getPv()).append(",").append(plb.getUv()).append(",").append(plb.getIp()).append(",'").append(plb.getSiteCode()).append("') ON DUPLICATE KEY UPDATE play_time=VALUES(play_time),time_length=VALUES(time_length),play_number=VALUES(play_number),play_user_number=VALUES(play_user_number),pv=VALUES(pv),uv=VALUES(uv),ip=VALUES(ip)");
//			System.out.println(insert);
			dbConn.execute(insert.toString());
		}catch(Exception e){
			e.printStackTrace();
			result = 0;
		}
		return result;
	}
	
	public int insertProgramLogNewSummary(ProgramLogNewBean plnb){
		int result = 1;
		try{
			StringBuffer insert = new StringBuffer("INSERT INTO log_xh_media_program_new_summary (date,hour,minute,site_code,origin_id,channel_id,part_id,global_id,play_number,play_user_number,comment_number,collection_number,share_number,time_length,play_time,pub_date,source) VALUES ('");
			insert.append(plnb.getDate()).append("','").append(plnb.getHour()).append("','").append(plnb.getMinute()).append("','").append(plnb.getSiteCode()).append("','").append(plnb.getOriginId()).append("','").append(plnb.getChannelId()).append("','").append(plnb.getPartId()).append("', '").append(plnb.getGlobalId()).append("',").append(plnb.getPlayNumber()).append(",").append(plnb.getPlayUserNumber()).append(",").append(plnb.getCommentNumber()).append(",").append(plnb.getCollectionNumber()).append(",")
			.append(plnb.getShareNumber()).append(",").append(plnb.getTimeLength()).append(",").append(plnb.getPlayTime()).append(",'").append(plnb.getPubDate()).append("','0') ON DUPLICATE KEY UPDATE play_time=VALUES(play_time),time_length=VALUES(time_length),play_number=VALUES(play_number),play_user_number=VALUES(play_user_number),comment_number=VALUES(comment_number),collection_number=VALUES(collection_number),share_number=VALUES(share_number)");
//			System.out.println(insert);
			dbConn.execute(insert.toString());
		}catch(Exception e){
			e.printStackTrace();
			result = 0;
		}
		return result;
	}
	
	public int insertDayProgramLogSummary(ProgramLogBean plb){
		int result = 1;
		try{
			StringBuffer insert = new StringBuffer("INSERT INTO log_day_xh_media_program_analysis_summary (date,origin_id,origin_name,channel_id,channel_name,part_id,part_name,global_id,chinese_name,pub_date,type,play_time,time_length,play_number,play_user_number,source,pv,uv,ip,site_code) VALUES ('");
			insert.append(plb.getDate()).append("','").append(plb.getOriginId()).append("','").append(plb.getOriginName()).append("','").append(plb.getChannelId()).append("','")
			.append(plb.getChannelName()).append("','").append(plb.getPartId()).append("','").append(plb.getPartName()).append("','").append(plb.getGlobalId()).append("','").append(plb.getChineseName()).append("','").append(plb.getPubDate()).append("','").append(plb.getType()).append("',").append(plb.getPlayTime()).append(",").append(plb.getTimeLength()).append(",")
			.append(plb.getPlayNumber()).append(",").append(plb.getPlayUserNumber()).append(",'0',").append(plb.getPv()).append(",").append(plb.getUv()).append(",").append(plb.getIp()).append(",'").append(plb.getSiteCode()).append("') ON DUPLICATE KEY UPDATE play_time=VALUES(play_time),time_length=VALUES(time_length),play_number=VALUES(play_number),play_user_number=VALUES(play_user_number),pv=VALUES(pv),uv=VALUES(uv),ip=VALUES(ip)");
//			System.out.println(insert);
			dbConn.execute(insert.toString());
		}catch(Exception e){
			e.printStackTrace();
			result = 0;
		}
		return result;
	}
	
	public int updateProgramLogSummary(ProgramLogBean plb){
		int result = 1;
		try{
			String update = "UPDATE log_xh_media_program_analysis_summary SET play_time=play_time+"+plb.getPlayTime()+",time_length=time_length+"+plb.getTimeLength()+",play_number=play_number+"+plb.getPlayNumber()+",play_user_number=play_user_number+"+plb.getPlayUserNumber()+",pv=pv+"+plb.getPv()+",uv=uv+"+plb.getUv()+",ip=ip+"+plb.getIp()+" WHERE global_id="+plb.getSource()+" AND site_code="+plb.getSiteCode()+" AND origin_id='"+plb.getOriginId()+"' AND date='"+plb.getDate()+"' AND hour='"+plb.getHour()+"' AND minute='"+plb.getMinute()+"'";
			dbConn.execute(update);
		}catch(Exception e){
			e.printStackTrace();
			result = 0;
		}
		return result;
	}
	
	public int updateProgramLogNewSummary(ProgramLogNewBean plnb){
		int result = 1;
		try{
			String update = "UPDATE log_xh_media_program_new_summary SET play_time=play_time+"+plnb.getPlayTime()+",play_number=play_number+"+plnb.getPlayNumber()+",play_user_number=play_user_number+"+plnb.getPlayUserNumber()+",comment_number=comment_number+"+plnb.getCommentNumber()+",collection_number=collection_number+"+plnb.getCollectionNumber()+",share_number=share_number+"+plnb.getShareNumber()+" WHERE global_id="+plnb.getSource()+" AND site_code="+plnb.getSiteCode()+" AND origin_id='"+plnb.getOriginId()+"' AND date='"+plnb.getDate()+"' AND hour='"+plnb.getHour()+"' AND minute='"+plnb.getMinute()+"'";
			dbConn.execute(update);
		}catch(Exception e){
			e.printStackTrace();
			result = 0;
		}
		return result;
	}
	
	public int updateDayProgramLogSummary(ProgramLogBean plb){
		int result = 1;
		try{
			String update = "UPDATE log_day_xh_media_program_analysis_summary SET play_time=play_time+"+plb.getPlayTime()+",time_length=time_length+"+plb.getTimeLength()+",play_number=play_number+"+plb.getPlayNumber()+",play_user_number=play_user_number+"+plb.getPlayUserNumber()+",pv=pv+"+plb.getPv()+",uv=uv+"+plb.getUv()+",ip=ip+"+plb.getIp()+" WHERE global_id="+plb.getSource()+" AND site_code="+plb.getSiteCode()+" AND origin_id='"+plb.getOriginId()+"' AND date='"+plb.getDate()+"'";
			dbConn.execute(update);
		}catch(Exception e){
			e.printStackTrace();
			result = 0;
		}
		return result;
	}
	
	public int updateDayProgramLogNewSummary(ProgramLogNewBean plnb){
		int result = 1;
		try{
			String update = "UPDATE log_day_xh_media_program_new_summary SET play_time=play_time+"+plnb.getPlayTime()+",play_number=play_number+"+plnb.getPlayNumber()+",play_user_number=play_user_number+"+plnb.getPlayUserNumber()+",comment_number=comment_number+"+plnb.getCommentNumber()+",collection_number=collection_number+"+plnb.getCollectionNumber()+",share_number=share_number+"+plnb.getShareNumber()+" WHERE global_id="+plnb.getSource()+" AND site_code="+plnb.getSiteCode()+" AND origin_id='"+plnb.getOriginId()+"' AND date='"+plnb.getDate()+"'";
			dbConn.execute(update);
		}catch(Exception e){
			e.printStackTrace();
			result = 0;
		}
		return result;
	}
	
	public int getIsNeedSummary(ProgramLogBean plb){
		int result = 0;
		try{
			String select = "SELECT COUNT(id) AS number FROM log_xh_media_program_analysis WHERE global_id="+plb.getSource()+" AND site_code="+plb.getSiteCode()+" AND origin_id='"+plb.getOriginId()+"' AND date='"+plb.getDate()+"' AND hour='"+plb.getHour()+"' AND minute='"+plb.getMinute()+"' AND source<100";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			if(rs1.next())
				result = rs1.getInt("number");
		}catch(Exception e){
			e.printStackTrace();
			result = -1;
		}
		return result;
	}
	
	public int getIsNeedSummaryNew(ProgramLogNewBean plnb){
		int result = 0;
		try{
			String select = "SELECT COUNT(id) AS number FROM log_xh_media_program_new WHERE global_id="+plnb.getSource()+" AND site_code="+plnb.getSiteCode()+" AND origin_id='"+plnb.getOriginId()+"' AND date='"+plnb.getDate()+"' AND hour='"+plnb.getHour()+"' AND minute='"+plnb.getMinute()+"' AND source<100";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			if(rs1.next())
				result = rs1.getInt("number");
		}catch(Exception e){
			e.printStackTrace();
			result = -1;
		}
		return result;
	}
	
	public int getDayIsNeedSummary(ProgramLogBean plb){
		int result = 0;
		try{
			String select = "SELECT COUNT(id) AS number FROM log_day_xh_media_program_analysis WHERE global_id="+plb.getSource()+" AND site_code="+plb.getSiteCode()+" AND origin_id='"+plb.getOriginId()+"' AND date='"+plb.getDate()+"' AND source<100";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			if(rs1.next())
				result = rs1.getInt("number");
		}catch(Exception e){
			e.printStackTrace();
			result = -1;
		}
		return result;
	}
	
	public int getDayIsNeedSummaryNew(ProgramLogNewBean plnb){
		int result = 0;
		try{
			String select = "SELECT COUNT(id) AS number FROM log_day_xh_media_program_new WHERE global_id="+plnb.getSource()+" AND site_code="+plnb.getSiteCode()+" AND origin_id='"+plnb.getOriginId()+"' AND date='"+plnb.getDate()+"' AND source<100";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			if(rs1.next())
				result = rs1.getInt("number");
		}catch(Exception e){
			e.printStackTrace();
			result = -1;
		}
		return result;
	}
	
	//处理历史数据的方法
	public List<ProgramLogBean> getAllNotCommendProgramList(){
		List<ProgramLogBean> list = new ArrayList<ProgramLogBean>();
		try{
			String select = "SELECT date,hour,minute,origin_id,origin_name,channel_id,channel_name,global_id,chinese_name,pub_date,type,play_time,time_length,play_number,play_user_number,source,pv,uv,ip,site_code FROM log_xh_media_program_analysis WHERE source<100";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			while(rs1.next()){
				ProgramLogBean plb = new ProgramLogBean();
				plb.setDate(rs1.getString("date"));
				plb.setHour(rs1.getString("hour"));
				plb.setMinute(rs1.getString("minute"));
				plb.setOriginId(rs1.getString("origin_id"));
				plb.setOriginName(rs1.getString("origin_name"));
				plb.setChannelId(rs1.getString("channel_id"));
				plb.setChannelName(rs1.getString("channel_name"));
				plb.setGlobalId(rs1.getString("global_id"));
				plb.setChineseName(rs1.getString("chinese_name"));
				plb.setPubDate(rs1.getString("pub_date"));
				plb.setType(rs1.getInt("type"));
				plb.setPlayTime(rs1.getInt("play_time"));
				plb.setTimeLength(rs1.getInt("time_length"));
				plb.setPlayNumber(rs1.getInt("play_number"));
				plb.setPlayUserNumber(rs1.getInt("play_user_number"));
				plb.setSource(rs1.getString("source"));
				plb.setPv(rs1.getInt("pv"));
				plb.setUv(rs1.getInt("uv"));
				plb.setIp(rs1.getInt("ip"));
				plb.setSiteCode(rs1.getString("site_code"));
				list.add(plb);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	public List<ProgramLogBean> getAllCommendProgramList(){
		List<ProgramLogBean> list = new ArrayList<ProgramLogBean>();
		try{
			String select = "SELECT date,hour,minute,origin_id,origin_name,channel_id,channel_name,global_id,chinese_name,pub_date,type,play_time,time_length,play_number,play_user_number,source,pv,uv,ip,site_code FROM log_xh_media_program_analysis WHERE source>100";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			while(rs1.next()){
				ProgramLogBean plb = new ProgramLogBean();
				plb.setDate(rs1.getString("date"));
				plb.setHour(rs1.getString("hour"));
				plb.setMinute(rs1.getString("minute"));
				plb.setOriginId(rs1.getString("origin_id"));
				plb.setOriginName(rs1.getString("origin_name"));
				plb.setChannelId(rs1.getString("channel_id"));
				plb.setChannelName(rs1.getString("channel_name"));
				plb.setGlobalId(rs1.getString("global_id"));
				plb.setChineseName(rs1.getString("chinese_name"));
				plb.setPubDate(rs1.getString("pub_date"));
				plb.setType(rs1.getInt("type"));
				plb.setPlayTime(rs1.getInt("play_time"));
				plb.setTimeLength(rs1.getInt("time_length"));
				plb.setPlayNumber(rs1.getInt("play_number"));
				plb.setPlayUserNumber(rs1.getInt("play_user_number"));
				plb.setSource(rs1.getString("source"));
				plb.setPv(rs1.getInt("pv"));
				plb.setUv(rs1.getInt("uv"));
				plb.setIp(rs1.getInt("ip"));
				plb.setSiteCode(rs1.getString("site_code"));
				list.add(plb);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	public List<ProgramLogBean> getDayAllNotCommendProgramList(){
		List<ProgramLogBean> list = new ArrayList<ProgramLogBean>();
		try{
			String select = "SELECT date,origin_id,origin_name,channel_id,channel_name,global_id,chinese_name,pub_date,type,play_time,time_length,play_number,play_user_number,source,pv,uv,ip,site_code FROM log_day_xh_media_program_analysis WHERE source<100";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			while(rs1.next()){
				ProgramLogBean plb = new ProgramLogBean();
				plb.setDate(rs1.getString("date"));
				plb.setOriginId(rs1.getString("origin_id"));
				plb.setOriginName(rs1.getString("origin_name"));
				plb.setChannelId(rs1.getString("channel_id"));
				plb.setChannelName(rs1.getString("channel_name"));
				plb.setGlobalId(rs1.getString("global_id"));
				plb.setChineseName(rs1.getString("chinese_name"));
				plb.setPubDate(rs1.getString("pub_date"));
				plb.setType(rs1.getInt("type"));
				plb.setPlayTime(rs1.getInt("play_time"));
				plb.setTimeLength(rs1.getInt("time_length"));
				plb.setPlayNumber(rs1.getInt("play_number"));
				plb.setPlayUserNumber(rs1.getInt("play_user_number"));
				plb.setSource(rs1.getString("source"));
				plb.setPv(rs1.getInt("pv"));
				plb.setUv(rs1.getInt("uv"));
				plb.setIp(rs1.getInt("ip"));
				plb.setSiteCode(rs1.getString("site_code"));
				list.add(plb);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	public List<ProgramLogBean> getDayAllCommendProgramList(){
		List<ProgramLogBean> list = new ArrayList<ProgramLogBean>();
		try{
			String select = "SELECT date,origin_id,origin_name,channel_id,channel_name,global_id,chinese_name,pub_date,type,play_time,time_length,play_number,play_user_number,source,pv,uv,ip,site_code FROM log_day_xh_media_program_analysis WHERE source>100";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			while(rs1.next()){
				ProgramLogBean plb = new ProgramLogBean();
				plb.setDate(rs1.getString("date"));
				plb.setOriginId(rs1.getString("origin_id"));
				plb.setOriginName(rs1.getString("origin_name"));
				plb.setChannelId(rs1.getString("channel_id"));
				plb.setChannelName(rs1.getString("channel_name"));
				plb.setGlobalId(rs1.getString("global_id"));
				plb.setChineseName(rs1.getString("chinese_name"));
				plb.setPubDate(rs1.getString("pub_date"));
				plb.setType(rs1.getInt("type"));
				plb.setPlayTime(rs1.getInt("play_time"));
				plb.setTimeLength(rs1.getInt("time_length"));
				plb.setPlayNumber(rs1.getInt("play_number"));
				plb.setPlayUserNumber(rs1.getInt("play_user_number"));
				plb.setSource(rs1.getString("source"));
				plb.setPv(rs1.getInt("pv"));
				plb.setUv(rs1.getInt("uv"));
				plb.setIp(rs1.getInt("ip"));
				plb.setSiteCode(rs1.getString("site_code"));
				list.add(plb);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
}
