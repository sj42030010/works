package com.xh.media.dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.xh.media.bean.ChannelBean;
import com.xh.media.bean.OriginBean;
import com.xh.media.bean.ProgramBean;
import com.xh.media.util.DbConn;
import com.xh.media.util.Key;

public class PublicDao {
	public DbConn dbConn;
	
	public PublicDao(DbConn dbConn){
		this.dbConn = dbConn;
	}
	
	public List<OriginBean> getOrigin(){
		List<OriginBean> list = new ArrayList<OriginBean>();
		try{
			String select = "SELECT origin_id,origin_name,site_code FROM index_xh_media_origin WHERE status=1 ORDER BY origin_id ASC";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			while(rs1.next()){
				OriginBean ob = new OriginBean();
				ob.setOriginId(rs1.getString("origin_id"));
				ob.setOriginName(rs1.getString("origin_name"));
				ob.setSiteCode(rs1.getString("site_code"));
				list.add(ob);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	public List<OriginBean> getOriginAndChannel(){
		List<OriginBean> list = new ArrayList<OriginBean>();
		try{
			String select = "SELECT origin_id,origin_name,site_code FROM index_xh_media_origin WHERE status=1 ORDER BY origin_id ASC";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			List<OriginBean> temp1 = new ArrayList<OriginBean>();
			while(rs1.next()){
				OriginBean ob = new OriginBean();
				ob.setOriginId(rs1.getString("origin_id"));
				ob.setOriginName(rs1.getString("origin_name"));
				ob.setSiteCode(rs1.getString("site_code"));
				temp1.add(ob);
			}
			for(OriginBean ob:temp1){
				String selectChannel = "SELECT channel_id,channel_name,channel_path,site_code FROM index_xh_media_channel WHERE origin_id='"+ob.getOriginId()+"' AND site_code='"+ob.getSiteCode()+"' ORDER BY channel_id ASC";
				ResultSet rs2 = dbConn.executeQuery(selectChannel, true);
				List<ChannelBean> channelList = new ArrayList<ChannelBean>();
				while(rs2.next()){
					ChannelBean cb = new ChannelBean();
					cb.setChannelId(rs2.getString("channel_id"));
					cb.setChannelName(rs2.getString("channel_name"));
					cb.setChannelPath(rs2.getString("channel_path"));
					cb.setSiteCode(rs2.getString("site_code"));
					channelList.add(cb);
				}
				ob.setChannelList(channelList);
				list.add(ob);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	public List<OriginBean> getOriginAndChannelAndProgram(){
		List<OriginBean> list = new ArrayList<OriginBean>();
		try{
			String select = "SELECT origin_id,origin_name,site_code FROM index_xh_media_origin WHERE status=1 ORDER BY origin_id ASC";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			List<OriginBean> temp1 = new ArrayList<OriginBean>();
			while(rs1.next()){
				OriginBean ob = new OriginBean();
				ob.setOriginId(rs1.getString("origin_id"));
				ob.setOriginName(rs1.getString("origin_name"));
				ob.setSiteCode(rs1.getString("site_code"));
				temp1.add(ob);
			}
			
			List<OriginBean> temp2 = new ArrayList<OriginBean>();
			for(OriginBean ob:temp1){
				String selectChannel = "SELECT channel_id,channel_name,channel_path,site_code FROM index_xh_media_channel WHERE origin_id='"+ob.getOriginId()+"' AND site_code='"+ob.getSiteCode()+"' ORDER BY channel_id ASC";
				ResultSet rs2 = dbConn.executeQuery(selectChannel, true);
				List<ChannelBean> channelList = new ArrayList<ChannelBean>();
				while(rs2.next()){
					ChannelBean cb = new ChannelBean();
					cb.setChannelId(rs2.getString("channel_id"));
					cb.setChannelName(rs2.getString("channel_name"));
					cb.setChannelPath(rs2.getString("channel_path"));
					cb.setSiteCode(rs2.getString("site_code"));
					channelList.add(cb);
				}
				ob.setChannelList(channelList);
				temp2.add(ob);
			}
			
			for(OriginBean ob:temp2){
			    OriginBean ob1 = new OriginBean();
			    ob1.setOriginId(ob.getOriginId());
			    ob1.setOriginName(ob.getOriginName());
			    List<ChannelBean> list1 = new ArrayList<ChannelBean>();
				for(ChannelBean cb:ob.getChannelList()){
					String selectProgram = "SELECT global_id,chinese_name,channel_id,time_length,type,pub_date,source,site_code FROM index_xh_media_program WHERE origin_id='"+ob.getOriginId()+"' AND channel_id='"+cb.getChannelId()+"' AND site_code='"+cb.getSiteCode()+"' ORDER BY global_id ASC";
//					System.out.println(selectProgram);
					ResultSet rs3 = dbConn.executeQuery(selectProgram, true);
					List<ProgramBean> programList = new ArrayList<ProgramBean>();
					while(rs3.next()){
						ProgramBean pb = new ProgramBean();
						pb.setChannelId(rs3.getString("channel_id"));
						pb.setChineseName(rs3.getString("chinese_name"));
						pb.setGlobalId(rs3.getString("global_id"));
						pb.setPubDate(rs3.getString("pub_date"));
						pb.setSiteCode(rs3.getString("site_code"));
						pb.setSource(rs3.getString("source"));
						pb.setTimeLength(rs3.getString("time_length"));
						pb.setType(rs3.getInt("type")+"");
						programList.add(pb);
					}
//					System.out.println("programList size is++++++"+programList.size());
					cb.setProgramList(programList);
//					System.out.println("cb.programList size is++++++"+cb.getProgramList().size());
					list1.add(cb);
				}
				ob1.setChannelList(list1);
				list.add(ob1);
			}
			Gson gson = new Gson();
			Map map = new HashMap();
			map.put("result", list);
			String str = gson.toJson(map);
//			System.out.println(str);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	public ProgramBean getProgramByGlobalId(String globalId,String siteCode){
		ProgramBean pb = new ProgramBean();
		try{
			String select = "SELECT global_id,chinese_name,channel_id,time_length,type,pub_date,source,site_code FROM index_xh_media_program WHERE global_id='"+globalId+"' AND site_code='"+siteCode+"'";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			if(rs1.next()){
				pb.setChannelId(rs1.getString("channel_id"));
				pb.setChineseName(rs1.getString("chinese_name"));
				pb.setGlobalId(rs1.getString("global_id"));
				pb.setPubDate(rs1.getString("pub_date"));
				pb.setSiteCode(rs1.getString("site_code"));
				pb.setSource(rs1.getString("source"));
				pb.setTimeLength(rs1.getString("time_length"));
				pb.setType(rs1.getInt("type")+"");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return pb;
	}
	
	public String getOriginNameByOriginId(String originId, String siteCode){
		String name = "null";
		try{
			String select = "SELECT origin_name FROM index_xh_media_origin WHERE origin_id='"+originId+"' AND site_code='"+siteCode+"'";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			if(rs1.next()){
				name = rs1.getString("origin_name");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return name;
	}
	
	public String getChannelNameByChannelId(String channelId, String siteCode){
		String name = "null";
		try{
			String select = "SELECT channel_name FROM index_xh_media_channel WHERE channel_id='"+channelId+"' AND site_code='"+siteCode+"'";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			if(rs1.next()){
				name = rs1.getString("channel_name");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return name;
	}
	
	public String getParentIdByChannelId(String channelId, String siteCode){
		String parent = "null";
		try{
			String select = "SELECT parent_id FROM index_xh_media_channel WHERE channel_id='"+channelId+"' AND site_code='"+siteCode+"'";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			if(rs1.next()){
				parent = rs1.getString("parent_id");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return parent;
	}
	
	public String getFirstVisitTime(){
		String time = "";
		try{
			String select = "SELECT create_time FROM source_xh_media_user_visit_log ORDER BY create_time asc LIMIT 1";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			if(rs1.next()){
				time = rs1.getString("create_time");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return time;
	}
	
	public static void main(String[] args){
//		ProgramBean pb = new ProgramBean();
		//System.out.println(pb.getGlobalId());
		DbConn dbConn = new DbConn(Key.COUNT_DATABASE[0], Key.COUNT_DATABASE[1], Key.COUNT_DATABASE[2]);
		try{
			String select = "SELECT * FROM index_xh_media_program WHERE id=22";
			ResultSet rs1 = dbConn.executeQuery(select, true);
			ResultSetMetaData m = null;
			m = rs1.getMetaData();
			int columns=m.getColumnCount();
			for(int i=1;i<=columns;i++){
				System.out.print(m.getColumnName(i));
			    System.out.print("\t\t");
			}
			System.out.println();
			while(rs1.next()){
				for(int i=1;i<=columns;i++){
					System.out.print(rs1.getString(i));
					System.out.print("\t\t");
			    }
			    System.out.println();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
