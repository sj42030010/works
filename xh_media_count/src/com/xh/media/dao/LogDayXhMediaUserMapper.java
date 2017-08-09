package com.xh.media.dao;

import java.util.List;

import com.xh.media.model.LogDayXhMediaUser;

public interface LogDayXhMediaUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LogDayXhMediaUser record);

    int insertSelective(LogDayXhMediaUser record);

    LogDayXhMediaUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LogDayXhMediaUser record);

    int updateByPrimaryKey(LogDayXhMediaUser record);
    
    List<LogDayXhMediaUser> findUserList();
    
    List<LogDayXhMediaUser> findSumUserList(LogDayXhMediaUser record);
    
    List<LogDayXhMediaUser> getUserListBySearch(LogDayXhMediaUser record);
    
    List<LogDayXhMediaUser> getUserListBySearchOneDay(LogDayXhMediaUser record);
    
    List<LogDayXhMediaUser> findSumUserListForCharts(LogDayXhMediaUser record);
    
    List<LogDayXhMediaUser> getUserListBySearchForCharts(LogDayXhMediaUser record);
    
    List<LogDayXhMediaUser> getUserListBySearchOneDayForCharts(LogDayXhMediaUser record);
    
    List<LogDayXhMediaUser> findUserDayListForChartsPie(LogDayXhMediaUser record);
    
    List<LogDayXhMediaUser> getUserDayListBySearchForChartsPie(LogDayXhMediaUser record);
    
    List<LogDayXhMediaUser> selectUserDataSum();
}