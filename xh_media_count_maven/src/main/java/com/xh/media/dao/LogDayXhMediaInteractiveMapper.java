package com.xh.media.dao;

import java.util.List;

import com.xh.media.model.LogDayXhMediaInteractive;

public interface LogDayXhMediaInteractiveMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LogDayXhMediaInteractive record);

    int insertSelective(LogDayXhMediaInteractive record);

    LogDayXhMediaInteractive selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LogDayXhMediaInteractive record);

    int updateByPrimaryKey(LogDayXhMediaInteractive record);
    
    List<LogDayXhMediaInteractive> findInteractiveList();
    
    List<LogDayXhMediaInteractive> findSumInteractiveList(LogDayXhMediaInteractive record);
    
    List<LogDayXhMediaInteractive> getInteractiveBySearch(LogDayXhMediaInteractive record);
    
    List<LogDayXhMediaInteractive> getInteractiveBySearchOneDay(LogDayXhMediaInteractive record);
    
    List<LogDayXhMediaInteractive>  findSumInteractiveListForChart(LogDayXhMediaInteractive record);
    
    List<LogDayXhMediaInteractive> getInteractiveBySearchForChart(LogDayXhMediaInteractive record);
    
    List<LogDayXhMediaInteractive> getInteractiveBySearchOneDayForChart(LogDayXhMediaInteractive record);
    
    List<LogDayXhMediaInteractive> selectInteractiveDataSum();
}