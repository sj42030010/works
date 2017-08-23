package com.xh.media.service;

import java.util.List;

import com.xh.media.dao.LogXhMediaInteractiveMapper;
import com.xh.media.model.LogDayXhMediaInteractive;
import com.xh.media.model.LogXhMediaInteractive;

public class LogXhMediaInteractiveServiceImpl implements LogXhMediaInteractiveService{
	private LogXhMediaInteractiveMapper logXhMediaInteractiveMapper;
	
	public LogXhMediaInteractiveMapper getLogXhMediaInteractiveMapper() {
		return logXhMediaInteractiveMapper;
	}

	public void setLogXhMediaInteractiveMapper(
			LogXhMediaInteractiveMapper logXhMediaInteractiveMapper) {
		this.logXhMediaInteractiveMapper = logXhMediaInteractiveMapper;
	}

	public int deleteByPrimaryKey(Integer id){
		return logXhMediaInteractiveMapper.deleteByPrimaryKey(id);
	}

	public int insert(LogXhMediaInteractive record){
		return logXhMediaInteractiveMapper.insert(record);
	}

	public int insertSelective(LogXhMediaInteractive record){
		return logXhMediaInteractiveMapper.insertSelective(record);
	}

	public LogXhMediaInteractive selectByPrimaryKey(Integer id){
		return logXhMediaInteractiveMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(LogXhMediaInteractive record){
		return logXhMediaInteractiveMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(LogXhMediaInteractive record){
		return logXhMediaInteractiveMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<LogXhMediaInteractive> findInteractiveList() {
		return logXhMediaInteractiveMapper.findInteractiveList();
	}

	@Override
	public List<LogXhMediaInteractive> findSumInteractiveList() {
		return logXhMediaInteractiveMapper.findSumInteractiveList();
	}

	@Override
	public List<LogXhMediaInteractive> getInteractiveBySearch(
			LogXhMediaInteractive record) {
		// TODO Auto-generated method stub
		return logXhMediaInteractiveMapper.getInteractiveBySearch(record);
	}

	@Override
	public List<LogXhMediaInteractive> getInteractiveBySearchOneDay(
			LogXhMediaInteractive record) {
		// TODO Auto-generated method stub
		return logXhMediaInteractiveMapper.getInteractiveBySearchOneDay(record);
	}

	@Override
	public List<LogXhMediaInteractive> findSumInteractiveListForChart() {
		// TODO Auto-generated method stub
		return logXhMediaInteractiveMapper.findSumInteractiveListForChart();
	}

	@Override
	public List<LogXhMediaInteractive> getInteractiveBySearchForChart(
			LogXhMediaInteractive record) {
		// TODO Auto-generated method stub
		return logXhMediaInteractiveMapper.getInteractiveBySearchForChart(record);
	}

	@Override
	public List<LogXhMediaInteractive> getInteractiveBySearchOneDayForChart(
			LogXhMediaInteractive record) {
		// TODO Auto-generated method stub
		return logXhMediaInteractiveMapper.getInteractiveBySearchOneDayForChart(record);
	}

	@Override
	public List<LogXhMediaInteractive> getInteractiveDetail(
			LogXhMediaInteractive record) {
		// TODO Auto-generated method stub
		return logXhMediaInteractiveMapper.getInteractiveDetail(record);
	}

	@Override
	public List<LogXhMediaInteractive> getInteractiveChartDetail(
			LogXhMediaInteractive record) {
		// TODO Auto-generated method stub
		return logXhMediaInteractiveMapper.getInteractiveChartDetail(record);
	}

}
