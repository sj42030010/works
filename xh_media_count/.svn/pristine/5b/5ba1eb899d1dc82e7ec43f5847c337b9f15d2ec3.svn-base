package com.xh.media.service;

import java.util.List;

import com.xh.media.model.LogXhMediaUser;

public interface LogXhMediaUserService {
    public int deleteByPrimaryKey(Integer id);

    public int insert(LogXhMediaUser record);

    public int insertSelective(LogXhMediaUser record);

    public LogXhMediaUser selectByPrimaryKey(Integer id);

    public int updateByPrimaryKeySelective(LogXhMediaUser record);

    public int updateByPrimaryKey(LogXhMediaUser record);
    
    public List<LogXhMediaUser> findUserList();
    
    public List<LogXhMediaUser> findSumUserList();
    
    public List<LogXhMediaUser> getUserListBySearch(LogXhMediaUser record);
    
    public List<LogXhMediaUser> getUserListBySearchOneDay(LogXhMediaUser record);
    
    public List<LogXhMediaUser> findSumUserListForCharts();
    
    public List<LogXhMediaUser> getUserListBySearchForCharts(LogXhMediaUser record);
    
    public List<LogXhMediaUser> getUserListBySearchOneDayForCharts(LogXhMediaUser record);
    
    public List<LogXhMediaUser> getUserListBySearchOneDayForChartsPie(LogXhMediaUser record);

    public List<LogXhMediaUser>  getUserListDetail(LogXhMediaUser record);
    
    public List<LogXhMediaUser> getUserChartLineDetail(LogXhMediaUser record);
    
    public List<LogXhMediaUser> getUserChartPieDetail(LogXhMediaUser record);
    
    public List<LogXhMediaUser> selectDetailSumData(LogXhMediaUser record);
}