package com.xh.media.service;

import java.util.List;

import com.xh.media.dao.LogDayXhMediaInteractiveMapper;
import com.xh.media.model.LogDayXhMediaInteractive;
import com.xh.media.model.LogDayXhMediaProgramAnalysis;

public class LogDayXhMediaInteractiveServiceImpl implements LogDayXhMediaInteractiveService{
	private LogDayXhMediaInteractiveMapper logDayXhMediaInteractiveMapper;

	public LogDayXhMediaInteractiveMapper getLogDayXhMediaInteractiveMapper() {
		return logDayXhMediaInteractiveMapper;
	}

	public void setLogDayXhMediaInteractiveMapper(
			LogDayXhMediaInteractiveMapper logDayXhMediaInteractiveMapper) {
		this.logDayXhMediaInteractiveMapper = logDayXhMediaInteractiveMapper;
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return logDayXhMediaInteractiveMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(LogDayXhMediaInteractive record) {
		return logDayXhMediaInteractiveMapper.insert(record);
	}

	@Override
	public int insertSelective(LogDayXhMediaInteractive record) {
		return logDayXhMediaInteractiveMapper.insertSelective(record);
	}

	@Override
	public LogDayXhMediaInteractive selectByPrimaryKey(Integer id) {
		return logDayXhMediaInteractiveMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(LogDayXhMediaInteractive record) {
		return logDayXhMediaInteractiveMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(LogDayXhMediaInteractive record) {
		return logDayXhMediaInteractiveMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<LogDayXhMediaInteractive> findInteractiveList() {
		return logDayXhMediaInteractiveMapper.findInteractiveList();
	}

	@Override
	public List<LogDayXhMediaInteractive> findSumInteractiveList(LogDayXhMediaInteractive record) {
		return logDayXhMediaInteractiveMapper.findSumInteractiveList(record);
	}

	@Override
	public List<LogDayXhMediaInteractive> getInteractiveBySearch(
			LogDayXhMediaInteractive record) {
		return logDayXhMediaInteractiveMapper.getInteractiveBySearch(record);
	}

	@Override
	public List<LogDayXhMediaInteractive> getInteractiveBySearchOneDay(
			LogDayXhMediaInteractive record) {
		return logDayXhMediaInteractiveMapper.getInteractiveBySearchOneDay(record);
	}

	@Override
	public List<LogDayXhMediaInteractive> findSumInteractiveListForChart(LogDayXhMediaInteractive record) {
		return logDayXhMediaInteractiveMapper.findSumInteractiveListForChart(record);
	}

	@Override
	public List<LogDayXhMediaInteractive> getInteractiveBySearchForChart(
			LogDayXhMediaInteractive record) {
		return logDayXhMediaInteractiveMapper.getInteractiveBySearchForChart(record);
	}

	@Override
	public List<LogDayXhMediaInteractive> getInteractiveBySearchOneDayForChart(
			LogDayXhMediaInteractive record) {
		return logDayXhMediaInteractiveMapper.getInteractiveBySearchOneDayForChart(record);
	}

	@Override
	public List<LogDayXhMediaInteractive> selectInteractiveDataSum() {
		// TODO Auto-generated method stub
		return logDayXhMediaInteractiveMapper.selectInteractiveDataSum();
	}

}
