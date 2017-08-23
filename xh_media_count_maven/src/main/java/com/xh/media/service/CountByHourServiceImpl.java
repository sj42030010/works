package com.xh.media.service;

import java.util.List;

import com.xh.media.dao.CountByHourMapper;
import com.xh.media.model.CountByHour;

public class CountByHourServiceImpl implements CountByHourService {
	
	private CountByHourMapper countByHourMapper;

	public void setCountByHourMapper(CountByHourMapper countByHourMapper) {
		this.countByHourMapper = countByHourMapper;
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return countByHourMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(CountByHour record) {
		return countByHourMapper.insert(record);
	}

	@Override
	public int insertSelective(CountByHour record) {
		return countByHourMapper.insertSelective(record);
	}

	@Override
	public CountByHour selectByPrimaryKey(Integer id) {
		return countByHourMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(CountByHour record) {
		return countByHourMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(CountByHour record) {
		return countByHourMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<CountByHour> getCountByHourList(CountByHour record) {
		return countByHourMapper.getCountByHourList(record);
	}

	@Override
	public List<CountByHour> getSortList() {
		return countByHourMapper.getSortList();
	}

	@Override
	public List<CountByHour> getProgramNameList(CountByHour record) {
		return countByHourMapper.getProgramNameList(record);
	}

	@Override
	public List<CountByHour> searchProgram(CountByHour record) {
		System.out.println(record.getStartTime()+"\n"+record.getEndTime()+"\n"+record.getSort()+"\n"+record.getGlobalId());
		return countByHourMapper.searchProgram(record);
	}

	@Override
	public List<CountByHour> selectCountByHourDataSum() {
		return countByHourMapper.selectCountByHourDataSum();
	}
}
