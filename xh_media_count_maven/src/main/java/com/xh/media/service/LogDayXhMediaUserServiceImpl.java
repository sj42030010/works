package com.xh.media.service;

import java.util.List;

import com.xh.media.dao.LogDayXhMediaUserMapper;
import com.xh.media.dao.LogXhMediaUserMapper;
import com.xh.media.model.LogDayXhMediaUser;
import com.xh.media.model.LogXhMediaUser;

public class LogDayXhMediaUserServiceImpl implements LogDayXhMediaUserService{
	private LogDayXhMediaUserMapper logDayXhMediaUserMapper;

	public LogDayXhMediaUserMapper getLogDayXhMediaUserMapper() {
		return logDayXhMediaUserMapper;
	}

	public void setLogDayXhMediaUserMapper(
			LogDayXhMediaUserMapper logDayXhMediaUserMapper) {
		this.logDayXhMediaUserMapper = logDayXhMediaUserMapper;
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return logDayXhMediaUserMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(LogDayXhMediaUser record) {
		return logDayXhMediaUserMapper.insert(record);
	}

	@Override
	public int insertSelective(LogDayXhMediaUser record) {
		return logDayXhMediaUserMapper.insertSelective(record);
	}

	@Override
	public LogDayXhMediaUser selectByPrimaryKey(Integer id) {
		return logDayXhMediaUserMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(LogDayXhMediaUser record) {
		return logDayXhMediaUserMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(LogDayXhMediaUser record) {
		return logDayXhMediaUserMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<LogDayXhMediaUser> findUserList() {
		return logDayXhMediaUserMapper.findUserList();
	}

	@Override
	public List<LogDayXhMediaUser> findSumUserList(LogDayXhMediaUser record) {
		return logDayXhMediaUserMapper.findSumUserList(record);
	}

	@Override
	public List<LogDayXhMediaUser> getUserListBySearch(LogDayXhMediaUser record) {
		return logDayXhMediaUserMapper.getUserListBySearch(record);
	}

	@Override
	public List<LogDayXhMediaUser> getUserListBySearchOneDay(
			LogDayXhMediaUser record) {
		return logDayXhMediaUserMapper.getUserListBySearchOneDay(record);
	}

	@Override
	public List<LogDayXhMediaUser> findSumUserListForCharts(LogDayXhMediaUser record) {
		return logDayXhMediaUserMapper.findSumUserListForCharts(record);
	}

	@Override
	public List<LogDayXhMediaUser> getUserListBySearchForCharts(
			LogDayXhMediaUser record) {
		return logDayXhMediaUserMapper.getUserListBySearchForCharts(record);
	}

	@Override
	public List<LogDayXhMediaUser> getUserListBySearchOneDayForCharts(
			LogDayXhMediaUser record) {
		return logDayXhMediaUserMapper.getUserListBySearchOneDayForCharts(record);
	}

	@Override
	public List<LogDayXhMediaUser> findUserDayListForChartsPie(LogDayXhMediaUser record) {
		return logDayXhMediaUserMapper.findUserDayListForChartsPie(record);
	}

	@Override
	public List<LogDayXhMediaUser> getUserDayListBySearchForChartsPie(
			LogDayXhMediaUser record) {
		return logDayXhMediaUserMapper.getUserDayListBySearchForChartsPie(record);
	}

	@Override
	public List<LogDayXhMediaUser> selectUserDataSum() {
		return logDayXhMediaUserMapper.selectUserDataSum();
	}

}
