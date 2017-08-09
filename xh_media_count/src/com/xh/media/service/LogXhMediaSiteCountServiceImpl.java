package com.xh.media.service;

import java.util.List;


import com.xh.media.dao.LogXhMediaSiteCountMapper;
import com.xh.media.model.LogXhMediaSiteCount;

public class LogXhMediaSiteCountServiceImpl implements LogXhMediaSiteCountService {

	private LogXhMediaSiteCountMapper logXhMediaSiteCountMapper;
	
	public LogXhMediaSiteCountMapper getLogXhMediaSiteCountMapper() {
		return logXhMediaSiteCountMapper;
	}

	public void setLogXhMediaSiteCountMapper(
			LogXhMediaSiteCountMapper logXhMediaSiteCountMapper) {
		this.logXhMediaSiteCountMapper = logXhMediaSiteCountMapper;
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return logXhMediaSiteCountMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(LogXhMediaSiteCount record) {
		return logXhMediaSiteCountMapper.insert(record);
	}

	@Override
	public int insertSelective(LogXhMediaSiteCount record) {
		return logXhMediaSiteCountMapper.insertSelective(record);
	}

	@Override
	public LogXhMediaSiteCount selectByPrimaryKey(Integer id) {
		return logXhMediaSiteCountMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(LogXhMediaSiteCount record) {
		return logXhMediaSiteCountMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(LogXhMediaSiteCount record) {
		return logXhMediaSiteCountMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<LogXhMediaSiteCount> getSiteDataDetail(String originId) {
		return logXhMediaSiteCountMapper.getSiteDataDetail(originId);
	}

	@Override
	public List<LogXhMediaSiteCount> searchSiteDataDetail(
			LogXhMediaSiteCount record) {
		System.out.println(record.getOriginId()+"::"+record.getStartTime()+"::"+record.getEndTime());
		return logXhMediaSiteCountMapper.searchSiteDataDetail(record);
	}

}
