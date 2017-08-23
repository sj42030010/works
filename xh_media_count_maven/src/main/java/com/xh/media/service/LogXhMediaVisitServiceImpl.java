package com.xh.media.service;

import java.util.List;

import com.xh.media.dao.LogXhMediaVisitMapper;
import com.xh.media.model.LogXhMediaVisit;

public class LogXhMediaVisitServiceImpl implements LogXhMediaVisitService{
	private LogXhMediaVisitMapper logXhMediaVisitMapper;

	public LogXhMediaVisitMapper getLogXhMediaVisitMapper() {
		return logXhMediaVisitMapper;
	}

	public void setLogXhMediaVisitMapper(LogXhMediaVisitMapper logXhMediaVisitMapper) {
		this.logXhMediaVisitMapper = logXhMediaVisitMapper;
	}
	
	public int deleteByPrimaryKey(Integer id){
		return logXhMediaVisitMapper.deleteByPrimaryKey(id);
	}

	public int insert(LogXhMediaVisit record){
		return logXhMediaVisitMapper.insert(record);
	}

	public int insertSelective(LogXhMediaVisit record){
		return logXhMediaVisitMapper.insertSelective(record);
	}

	public LogXhMediaVisit selectByPrimaryKey(Integer id){
		return logXhMediaVisitMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(LogXhMediaVisit record){
		return logXhMediaVisitMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(LogXhMediaVisit record){
		return logXhMediaVisitMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<LogXhMediaVisit> selectByLimit(Integer limit) {
		// TODO Auto-generated method stub
		return logXhMediaVisitMapper.selectByLimit(limit);
	}

	@Override
	public List<LogXhMediaVisit> getVisitList() {
		// TODO Auto-generated method stub
		return logXhMediaVisitMapper.getVisitList();
	}

	@Override
	public List<LogXhMediaVisit> getSumVisitList() {
		// TODO Auto-generated method stub
		return logXhMediaVisitMapper.getSumVisitList();
	}

	@Override
	public List<LogXhMediaVisit> getVisitListBySearch(LogXhMediaVisit record){
		// TODO Auto-generated method stub
		return logXhMediaVisitMapper.getVisitListBySearch(record);
	}

	@Override
	public List<LogXhMediaVisit> getVisitListBySearchOneDay(
			LogXhMediaVisit record) {
		// TODO Auto-generated method stub
		return logXhMediaVisitMapper.getVisitListBySearchOneDay(record);
	}

	@Override
	public List<LogXhMediaVisit> getSumVisitListForCharts() {
		// TODO Auto-generated method stub
		return logXhMediaVisitMapper.getSumVisitListForCharts();
	}

	@Override
	public List<LogXhMediaVisit> getVisitListBySearchForCharts(
			LogXhMediaVisit record) {
		// TODO Auto-generated method stub
		return logXhMediaVisitMapper.getVisitListBySearchForCharts(record);
	}

	@Override
	public List<LogXhMediaVisit> getVisitListBySearchOneDayForCharts(
			LogXhMediaVisit record) {
		// TODO Auto-generated method stub
		return logXhMediaVisitMapper.getVisitListBySearchOneDayForCharts(record);
	}

	@Override
	public List<LogXhMediaVisit> getVisitListDetail(LogXhMediaVisit record) {
		return logXhMediaVisitMapper.getVisitListDetail(record);
	}

	public List<LogXhMediaVisit> selectDataSum(){
		return logXhMediaVisitMapper.selectDataSum();
		
	}
}
