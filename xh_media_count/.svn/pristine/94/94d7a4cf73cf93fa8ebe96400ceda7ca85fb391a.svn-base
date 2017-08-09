package com.xh.media.dao;

import java.util.List;

import com.xh.media.model.CountByHour;

public interface CountByHourMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CountByHour record);

    int insertSelective(CountByHour record);

    CountByHour selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CountByHour record);

    int updateByPrimaryKey(CountByHour record);
    
    List<CountByHour>getCountByHourList(CountByHour record);
    
    List<CountByHour>getSortList();
    
    List<CountByHour>getProgramNameList(CountByHour record);
    
    List<CountByHour>searchProgram(CountByHour record);
    
    List<CountByHour> selectCountByHourDataSum();
}