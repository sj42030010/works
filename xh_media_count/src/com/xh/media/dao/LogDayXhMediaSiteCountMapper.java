package com.xh.media.dao;

import java.util.List;

import com.xh.media.model.LogDayXhMediaSiteCount;

public interface LogDayXhMediaSiteCountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LogDayXhMediaSiteCount record);

    int insertSelective(LogDayXhMediaSiteCount record);

    LogDayXhMediaSiteCount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LogDayXhMediaSiteCount record);

    int updateByPrimaryKey(LogDayXhMediaSiteCount record);
     
    List<LogDayXhMediaSiteCount> getSiteData();

    List<LogDayXhMediaSiteCount> getSiteDataByOriginId(String originId);
    
    List<LogDayXhMediaSiteCount> getSiteDataNew(LogDayXhMediaSiteCount ldxmsc);
    
    List<LogDayXhMediaSiteCount> searchDaySiteDataDetail(LogDayXhMediaSiteCount ldxmsc);
}