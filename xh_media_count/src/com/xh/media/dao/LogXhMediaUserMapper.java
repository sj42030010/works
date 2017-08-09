package com.xh.media.dao;

import java.util.List;

import com.xh.media.model.LogXhMediaUser;

public interface LogXhMediaUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LogXhMediaUser record);

    int insertSelective(LogXhMediaUser record);

    LogXhMediaUser selectByPrimaryKey(Integer id);
    
    int updateByPrimaryKeySelective(LogXhMediaUser record);

    int updateByPrimaryKey(LogXhMediaUser record);
    
    List<LogXhMediaUser> findUserList();
    
    List<LogXhMediaUser> findSumUserList();
    
    List<LogXhMediaUser> getUserListBySearch(LogXhMediaUser record);
    
    List<LogXhMediaUser> getUserListBySearchOneDay(LogXhMediaUser record);
    
    List<LogXhMediaUser> findSumUserListForCharts();
    
    List<LogXhMediaUser> getUserListBySearchForCharts(LogXhMediaUser record);
    
    List<LogXhMediaUser> getUserListBySearchOneDayForCharts(LogXhMediaUser record);
    
    List<LogXhMediaUser> getUserListBySearchOneDayForChartsPie(LogXhMediaUser record);
    
    List<LogXhMediaUser>  getUserListDetail(LogXhMediaUser record);

    List<LogXhMediaUser> getUserChartLineDetail(LogXhMediaUser record);
    
    List<LogXhMediaUser> getUserChartPieDetail(LogXhMediaUser record);
    
    List<LogXhMediaUser> selectDetailSumData(LogXhMediaUser record);
}