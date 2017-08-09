package com.xh.media.dao;

import java.util.List;

import com.xh.media.model.LogXhMediaSiteCount;

public interface LogXhMediaSiteCountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LogXhMediaSiteCount record);

    int insertSelective(LogXhMediaSiteCount record);

    LogXhMediaSiteCount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LogXhMediaSiteCount record);

    int updateByPrimaryKey(LogXhMediaSiteCount record);
    
    List<LogXhMediaSiteCount> getSiteDataDetail(String originId);
    
    List<LogXhMediaSiteCount> searchSiteDataDetail(LogXhMediaSiteCount record);
}