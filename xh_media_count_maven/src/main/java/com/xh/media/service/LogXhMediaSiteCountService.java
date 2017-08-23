package com.xh.media.service;

import java.util.List;

import com.xh.media.model.LogXhMediaSiteCount;

public interface LogXhMediaSiteCountService {

	public int deleteByPrimaryKey(Integer id);

	public int insert(LogXhMediaSiteCount record);

	public int insertSelective(LogXhMediaSiteCount record);

	public LogXhMediaSiteCount selectByPrimaryKey(Integer id);

	public int updateByPrimaryKeySelective(LogXhMediaSiteCount record);

	public int updateByPrimaryKey(LogXhMediaSiteCount record);
    
	public List<LogXhMediaSiteCount> getSiteDataDetail(String originId);
    
	public List<LogXhMediaSiteCount> searchSiteDataDetail(LogXhMediaSiteCount record);
}
