package com.xh.media.service;

import java.util.List;

import com.xh.media.model.CountByHour;

public interface CountByHourService {
	public int deleteByPrimaryKey(Integer id);

	public int insert(CountByHour record);

	public int insertSelective(CountByHour record);

	public CountByHour selectByPrimaryKey(Integer id);

	public int updateByPrimaryKeySelective(CountByHour record);

	public int updateByPrimaryKey(CountByHour record);
    
	public List<CountByHour>getCountByHourList(CountByHour record);
    
	public List<CountByHour>getSortList();
    
	public List<CountByHour>getProgramNameList(CountByHour record);
    
	public List<CountByHour>searchProgram(CountByHour record);
    
	public List<CountByHour> selectCountByHourDataSum();
}
