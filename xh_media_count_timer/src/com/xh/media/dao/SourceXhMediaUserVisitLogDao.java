package com.xh.media.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.xh.media.bean.IndexChannelBean;
import com.xh.media.bean.IndexProgramBean;
import com.xh.media.bean.SourceVisitBean;
import com.xh.media.util.DbConn;

public class SourceXhMediaUserVisitLogDao {
	private DbConn dbConn;
	public SourceXhMediaUserVisitLogDao(DbConn dbConn){
		this.dbConn = dbConn;
	}
	
	public List<SourceVisitBean> getSourceVisitList(String start, String end){
		List<SourceVisitBean> list = new ArrayList<SourceVisitBean>();
		try
		{
			String select = "SELECT origin_id,channle_id,global_id,site_code FROM source_xh_media_user_visit_log WHERE create_time>='"+start+"' AND create_time<'"+end+"'";
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
	
	public String getSiteUrl(String siteCode){
		String siteUrl = "";
		try
		{
			String select = "SELECT url FROM index_xh_media_site_url WHERE site_code="+siteCode;
			ResultSet rs1 = dbConn.executeQuery(select, true);
			if(rs1.next())
			{
				siteUrl = rs1.getString("url");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return siteUrl;
	}
	
	public List<SourceVisitBean> getSourceInteractiveList(String start, String end){
		List<SourceVisitBean> list = new ArrayList<SourceVisitBean>();
		try
		{
			String select = "SELECT orgin_id,global_id,site_code FROM source_xh_media_interactive WHERE create_time>='"+start+"' AND create_time<'"+end+"'";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			while(rs1.next()){
				SourceVisitBean svb = new SourceVisitBean();
				svb.setOriginId(rs1.getString("origin_id"));
				svb.setGlobalId(rs1.getString("global_id"));
				svb.setSiteCode(rs1.getString("site_code"));
				list.add(svb);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	public int updateProgramIndexTable(IndexProgramBean ipb){
		int result = 1;
		try
		{
			StringBuffer update = new StringBuffer("UPDATE index_xh_media_program SET chinese_name='");
			update.append(ipb.getChineseName()).append("', ");
			update.append("time_length=").append(ipb.getTimeLength()).append(", ");
			update.append("pub_date=").append(ipb.getPublishDate()).append(" WHERE channel_id=").append(ipb.getChannelId()).append(" AND global_id=").append(ipb.getGlobalId());
			dbConn.execute(update.toString());
		}catch(Exception e){
			e.printStackTrace();
			result = 0;
		}
		return result;
	}
	
	public int updateOrInsertChannelIndexTable(IndexChannelBean icb){
		int result = 1;
		try
		{
			StringBuffer insert = new StringBuffer("INSERT INTO index_xh_media_channel (channel_id, channel_name, channel_path, origin_id, parent_id, site_code, status) VALUES (");
			insert.append(icb.getChannelId()).append(", '").append(icb.getChannelName()).append("', '").append(icb.getChannelPath()).append("', '").append(icb.getOriginId())
			.append("', ").append(icb.getParentId()).append(", ").append(icb.getSiteCode()).append(", ").append(icb.getStatus()).append(") ON DUPLICATE KEY UPDATE channel_name=VALUES(channel_name),channel_path=VALUES(channel_path),parent_id=VALUES(parent_id)");
//			System.out.println(insert);
			dbConn.execute(insert.toString());
		}catch(Exception e){
			e.printStackTrace();
			result = 0;
		}
		return result;
	}
}
