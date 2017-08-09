package com.xh.media.service;

import java.util.List;

import com.xh.media.dao.LogDayXhMediaVisitMapper;
import com.xh.media.model.LogDayXhMediaVisit;

public class LogDayXhMediaVisitServiceImpl implements LogDayXhMediaVisitService{
	private LogDayXhMediaVisitMapper logDayXhMediaVisitMapper;

	
	public LogDayXhMediaVisitMapper getLogDayXhMediaVisitMapper() {
		return logDayXhMediaVisitMapper;
	}

	public void setLogDayXhMediaVisitMapper(
			LogDayXhMediaVisitMapper logDayXhMediaVisitMapper) {
		this.logDayXhMediaVisitMapper = logDayXhMediaVisitMapper;
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return logDayXhMediaVisitMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(LogDayXhMediaVisit record) {
		return logDayXhMediaVisitMapper.insert(record);
	}

	@Override
	public int insertSelective(LogDayXhMediaVisit record) {
		return logDayXhMediaVisitMapper.insertSelective(record);
	}

	@Override
	public LogDayXhMediaVisit selectByPrimaryKey(Integer id) {
		return logDayXhMediaVisitMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(LogDayXhMediaVisit record) {
		return logDayXhMediaVisitMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(LogDayXhMediaVisit record) {
		return logDayXhMediaVisitMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<LogDayXhMediaVisit> selectByLimit(Integer limit) {
		return logDayXhMediaVisitMapper.selectByLimit(limit);
	}

	@Override
	public List<LogDayXhMediaVisit> getSumVisitList(LogDayXhMediaVisit record) {
		return logDayXhMediaVisitMapper.getSumVisitList(record);
	}

	@Override
	public List<LogDayXhMediaVisit> getVisitListBySearch(
			LogDayXhMediaVisit record) {
		return logDayXhMediaVisitMapper.getVisitListBySearch(record);
	}

	@Override
	public List<LogDayXhMediaVisit> getSumVisitListForCharts(LogDayXhMediaVisit record) {
		return logDayXhMediaVisitMapper.getSumVisitListForCharts(record);
	}

	@Override
	public List<LogDayXhMediaVisit> getVisitListBySearchForCharts(
			LogDayXhMediaVisit record) {
		return logDayXhMediaVisitMapper.getVisitListBySearchForCharts(record);
	}

	@Override
	public List<LogDayXhMediaVisit> getVisitListBySearchOneDay(
			LogDayXhMediaVisit record) {
		return logDayXhMediaVisitMapper.getVisitListBySearchOneDay(record);
	}



}
