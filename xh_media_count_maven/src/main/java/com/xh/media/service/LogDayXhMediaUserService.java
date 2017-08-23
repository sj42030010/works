package com.xh.media.service;

import java.util.List;

import com.xh.media.model.LogDayXhMediaUser;

public interface LogDayXhMediaUserService {
    public int deleteByPrimaryKey(Integer id);

    public int insert(LogDayXhMediaUser record);

    public int insertSelective(LogDayXhMediaUser record);

    public LogDayXhMediaUser selectByPrimaryKey(Integer id);

    public int updateByPrimaryKeySelective(LogDayXhMediaUser record);

    public int updateByPrimaryKey(LogDayXhMediaUser record);
    
    public List<LogDayXhMediaUser> findUserList();
    
    public List<LogDayXhMediaUser> findSumUserList(LogDayXhMediaUser record);
    
    public List<LogDayXhMediaUser> getUserListBySearch(LogDayXhMediaUser record);
    
    public List<LogDayXhMediaUser> getUserListBySearchOneDay(LogDayXhMediaUser record);
    
    public List<LogDayXhMediaUser> findSumUserListForCharts(LogDayXhMediaUser record);
    
    public List<LogDayXhMediaUser> getUserListBySearchForCharts(LogDayXhMediaUser record);
    
    public List<LogDayXhMediaUser> getUserListBySearchOneDayForCharts(LogDayXhMediaUser record);
    
    public List<LogDayXhMediaUser> findUserDayListForChartsPie(LogDayXhMediaUser record);
    
    public List<LogDayXhMediaUser> getUserDayListBySearchForChartsPie(LogDayXhMediaUser record);

    public  List<LogDayXhMediaUser> selectUserDataSum();
}