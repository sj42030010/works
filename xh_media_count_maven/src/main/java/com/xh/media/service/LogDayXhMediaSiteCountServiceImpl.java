package com.xh.media.service;

import java.util.List;

import com.xh.media.dao.LogDayXhMediaSiteCountMapper;
import com.xh.media.model.LogDayXhMediaSiteCount;

public class LogDayXhMediaSiteCountServiceImpl implements
		LogDayXhMediaSiteCountService {

	private LogDayXhMediaSiteCountMapper logDayXhMediaSiteCountMapper;
	
	public LogDayXhMediaSiteCountMapper getLogDayXhMediaSiteCountMapper() {
		return logDayXhMediaSiteCountMapper;
	}

	public void setLogDayXhMediaSiteCountMapper(
			LogDayXhMediaSiteCountMapper logDayXhMediaSiteCountMapper) {
		this.logDayXhMediaSiteCountMapper = logDayXhMediaSiteCountMapper;
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return logDayXhMediaSiteCountMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(LogDayXhMediaSiteCount record) {
		return logDayXhMediaSiteCountMapper.insert(record);
	}

	@Override
	public int insertSelective(LogDayXhMediaSiteCount record) {
		return logDayXhMediaSiteCountMapper.insertSelective(record);
	}

	@Override
	public LogDayXhMediaSiteCount selectByPrimaryKey(Integer id) {
		return logDayXhMediaSiteCountMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(LogDayXhMediaSiteCount record) {
		return logDayXhMediaSiteCountMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(LogDayXhMediaSiteCount record) {
		return logDayXhMediaSiteCountMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<LogDayXhMediaSiteCount> getSiteData() {
		return logDayXhMediaSiteCountMapper.getSiteData();
	}

	@Override
	public List<LogDayXhMediaSiteCount> getSiteDataByOriginId(String originId) {
		return logDayXhMediaSiteCountMapper.getSiteDataByOriginId(originId);
	}
	
	@Override
	public List<LogDayXhMediaSiteCount> getSiteDataNew(LogDayXhMediaSiteCount ldxmac){
		return logDayXhMediaSiteCountMapper.getSiteDataNew(ldxmac);
	}
	
	@Override
	public List<LogDayXhMediaSiteCount> searchDaySiteDataDetail(LogDayXhMediaSiteCount ldxmac)
	{
		return logDayXhMediaSiteCountMapper.searchDaySiteDataDetail(ldxmac);
	}

}
