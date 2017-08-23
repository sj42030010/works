package com.xh.media.dao;

import java.util.List;

import com.xh.media.model.LogXhMediaVisit;

public interface LogXhMediaVisitMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LogXhMediaVisit record);

    int insertSelective(LogXhMediaVisit record);

    LogXhMediaVisit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LogXhMediaVisit record);

    int updateByPrimaryKey(LogXhMediaVisit record);
    
    List<LogXhMediaVisit> selectByLimit(Integer limit);
    
    List<LogXhMediaVisit> getVisitList();
    
    List<LogXhMediaVisit> getSumVisitList();
    
    List<LogXhMediaVisit> getVisitListBySearch(LogXhMediaVisit record);
    
    List<LogXhMediaVisit> getVisitListBySearchOneDay(LogXhMediaVisit record);
    
    List<LogXhMediaVisit> getSumVisitListForCharts();
    
    List<LogXhMediaVisit> getVisitListBySearchForCharts(LogXhMediaVisit record);
    
    List<LogXhMediaVisit> getVisitListBySearchOneDayForCharts(LogXhMediaVisit record);

    List<LogXhMediaVisit> getVisitListDetail(LogXhMediaVisit record);
    
    List<LogXhMediaVisit> selectDataSum();
}