package com.xh.media.dao;

import java.util.List;

import com.xh.media.model.LogDayXhMediaVisit;

public interface LogDayXhMediaVisitMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LogDayXhMediaVisit record);

    int insertSelective(LogDayXhMediaVisit record);

    LogDayXhMediaVisit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LogDayXhMediaVisit record);

    int updateByPrimaryKey(LogDayXhMediaVisit record);
    
    List<LogDayXhMediaVisit> selectByLimit(Integer limit);
    
    List<LogDayXhMediaVisit> getSumVisitList(LogDayXhMediaVisit record);
    
    List<LogDayXhMediaVisit> getVisitListBySearch(LogDayXhMediaVisit record);
    
    List<LogDayXhMediaVisit> getSumVisitListForCharts(LogDayXhMediaVisit record);
    
    List<LogDayXhMediaVisit> getVisitListBySearchForCharts(LogDayXhMediaVisit record);
    
    List<LogDayXhMediaVisit> getVisitListBySearchOneDay(LogDayXhMediaVisit record);
    
}