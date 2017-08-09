package com.xh.media.service;

import java.util.List;

import com.xh.media.dao.LogXhMediaUserMapper;
import com.xh.media.model.LogXhMediaUser;

public class LogXhMediaUserServiceImpl implements LogXhMediaUserService{
	private LogXhMediaUserMapper logXhMediaUserMapper;

	public LogXhMediaUserMapper getLogXhMediaUserMapper() {
		return logXhMediaUserMapper;
	}

	public void setLogXhMediaUserMapper(LogXhMediaUserMapper logXhMediaUserMapper) {
		this.logXhMediaUserMapper = logXhMediaUserMapper;
	}
	
	public int deleteByPrimaryKey(Integer id){
		return logXhMediaUserMapper.deleteByPrimaryKey(id);
	}

    public int insert(LogXhMediaUser record){
    	return logXhMediaUserMapper.insert(record);
    }

    public int insertSelective(LogXhMediaUser record){
    	return logXhMediaUserMapper.insertSelective(record);
    }

    public LogXhMediaUser selectByPrimaryKey(Integer id){
    	return logXhMediaUserMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(LogXhMediaUser record){
    	return logXhMediaUserMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(LogXhMediaUser record){
    	return logXhMediaUserMapper.updateByPrimaryKey(record);
    }

	@Override
	public List<LogXhMediaUser> findUserList() {
		return logXhMediaUserMapper.findUserList();
	}

	@Override
	public List<LogXhMediaUser> findSumUserList() {
		return logXhMediaUserMapper.findSumUserList();
	}

	@Override
	public List<LogXhMediaUser> getUserListBySearch(LogXhMediaUser record) {
		return logXhMediaUserMapper.getUserListBySearch(record);
	}

	@Override
	public List<LogXhMediaUser> getUserListBySearchOneDay(LogXhMediaUser record) {
		return logXhMediaUserMapper.getUserListBySearchOneDay(record);
	}

	@Override
	public List<LogXhMediaUser> findSumUserListForCharts() {
		return logXhMediaUserMapper.findSumUserListForCharts();
	}

	@Override
	public List<LogXhMediaUser> getUserListBySearchForCharts(
			LogXhMediaUser record) {
		return logXhMediaUserMapper.getUserListBySearchForCharts(record);
	}

	@Override
	public List<LogXhMediaUser> getUserListBySearchOneDayForCharts(
			LogXhMediaUser record) {
		return logXhMediaUserMapper.getUserListBySearchOneDayForCharts(record);
	}

	@Override
	public List<LogXhMediaUser> getUserListBySearchOneDayForChartsPie(
			LogXhMediaUser record) {
		return logXhMediaUserMapper.getUserListBySearchOneDayForChartsPie(record);
	}

	@Override
	public List<LogXhMediaUser> getUserListDetail(LogXhMediaUser record) {
		return logXhMediaUserMapper.getUserListDetail(record);
	}

	@Override
	public List<LogXhMediaUser> getUserChartLineDetail(LogXhMediaUser record) {
		return logXhMediaUserMapper.getUserChartLineDetail(record);
	}

	@Override
	public List<LogXhMediaUser> getUserChartPieDetail(LogXhMediaUser record) {
		return logXhMediaUserMapper.getUserChartPieDetail(record);
	}

	@Override
	public List<LogXhMediaUser> selectDetailSumData(LogXhMediaUser record) {
		System.out.println("date="+record.getDate()+"sitecode="+record.getSiteCode()+"org="+record.getOriginId());
		return logXhMediaUserMapper.selectDetailSumData(record);
	}
}
