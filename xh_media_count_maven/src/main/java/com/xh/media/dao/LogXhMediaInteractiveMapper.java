package com.xh.media.dao;

import java.util.List;

import com.xh.media.model.LogXhMediaInteractive;

public interface LogXhMediaInteractiveMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LogXhMediaInteractive record);

    int insertSelective(LogXhMediaInteractive record);

    LogXhMediaInteractive selectByPrimaryKey(Integer id);
    
    int updateByPrimaryKeySelective(LogXhMediaInteractive record);

    int updateByPrimaryKey(LogXhMediaInteractive record);
    
    List<LogXhMediaInteractive> findInteractiveList();
    
    List<LogXhMediaInteractive> findSumInteractiveList();
    
    List<LogXhMediaInteractive> getInteractiveBySearch(LogXhMediaInteractive record);
    
    List<LogXhMediaInteractive> getInteractiveBySearchOneDay(LogXhMediaInteractive record);
    
    List<LogXhMediaInteractive>  findSumInteractiveListForChart();
    
    List<LogXhMediaInteractive> getInteractiveBySearchForChart(LogXhMediaInteractive record);
    
    List<LogXhMediaInteractive> getInteractiveBySearchOneDayForChart(LogXhMediaInteractive record);
    
    List<LogXhMediaInteractive> getInteractiveDetail(LogXhMediaInteractive record);
    
    List<LogXhMediaInteractive>  getInteractiveChartDetail(LogXhMediaInteractive record);
}