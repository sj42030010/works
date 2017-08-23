package com.xh.media.service;

import java.util.List;

import com.xh.media.dao.LogXhMediaAppCountMapper;
import com.xh.media.model.LogXhMediaAppCount;

public class LogXhMediaAppCountServiceImpl implements LogXhMediaAppCountService {

	private LogXhMediaAppCountMapper logXhMediaAppCountMapper;
	
	public LogXhMediaAppCountMapper getLogXhMediaAppCountMapper() {
		return logXhMediaAppCountMapper;
	}

	public void setLogXhMediaAppCountMapper(
			LogXhMediaAppCountMapper logXhMediaAppCountMapper) {
		this.logXhMediaAppCountMapper = logXhMediaAppCountMapper;
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return logXhMediaAppCountMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(LogXhMediaAppCount record) {
		return logXhMediaAppCountMapper.insert(record);
	}

	@Override
	public int insertSelective(LogXhMediaAppCount record) {
		return logXhMediaAppCountMapper.insertSelective(record);
	}

	@Override
	public LogXhMediaAppCount selectByPrimaryKey(Integer id) {
		return logXhMediaAppCountMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(LogXhMediaAppCount record) {
		return logXhMediaAppCountMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(LogXhMediaAppCount record) {
		return logXhMediaAppCountMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<LogXhMediaAppCount> getAppDataDetail(String originId) {
		return logXhMediaAppCountMapper.getAppDataDetail(originId);
	}

	@Override
	public List<LogXhMediaAppCount> searchAppDataDetail(
			LogXhMediaAppCount record) {
		System.out.println(record.getOriginId()+"::"+record.getStartTime()+"::"+record.getEndTime());
		return logXhMediaAppCountMapper.searchAppDataDetail(record);
	}

}
