package com.xh.media.service;

import java.util.List;

import com.xh.media.dao.LogDayXhMediaAppCountMapper;
import com.xh.media.model.LogDayXhMediaAppCount;

public class LogDayXhMediaAppCountServiceImpl implements
		LogDayXhMediaAppCountService {

	private LogDayXhMediaAppCountMapper logDayXhMediaAppCountMapper;
	
	public LogDayXhMediaAppCountMapper getLogDayXhMediaAppCountMapper() {
		return logDayXhMediaAppCountMapper;
	}

	public void setLogDayXhMediaAppCountMapper(
			LogDayXhMediaAppCountMapper logDayXhMediaAppCountMapper) {
		this.logDayXhMediaAppCountMapper = logDayXhMediaAppCountMapper;
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return logDayXhMediaAppCountMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(LogDayXhMediaAppCount record) {
		return logDayXhMediaAppCountMapper.insert(record);
	}

	@Override
	public int insertSelective(LogDayXhMediaAppCount record) {
		return logDayXhMediaAppCountMapper.insertSelective(record);
	}

	@Override
	public LogDayXhMediaAppCount selectByPrimaryKey(Integer id) {
		return logDayXhMediaAppCountMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(LogDayXhMediaAppCount record) {
		return logDayXhMediaAppCountMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(LogDayXhMediaAppCount record) {
		return logDayXhMediaAppCountMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<LogDayXhMediaAppCount> getAppData() {
		return logDayXhMediaAppCountMapper.getAppData();
	}

	@Override
	public List<LogDayXhMediaAppCount> getAppDataByOriginId(String originId) {
		return logDayXhMediaAppCountMapper.getAppDataByOriginId(originId);
	}
	
	@Override
	public List<LogDayXhMediaAppCount> getAppDataNew(LogDayXhMediaAppCount ldxmac){
		return logDayXhMediaAppCountMapper.getAppDataNew(ldxmac);
	}
	
	@Override
	public List<LogDayXhMediaAppCount> searchDayAppDataDetail(LogDayXhMediaAppCount ldxmac)
	{
		return logDayXhMediaAppCountMapper.searchDayAppDataDetail(ldxmac);
	}

}
