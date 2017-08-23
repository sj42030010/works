package com.xh.media.service;

import java.util.List;

import com.xh.media.model.LogDayXhMediaSiteCount;

public interface LogDayXhMediaSiteCountService {

	public int deleteByPrimaryKey(Integer id);

    public int insert(LogDayXhMediaSiteCount record);

    public int insertSelective(LogDayXhMediaSiteCount record);

    public LogDayXhMediaSiteCount selectByPrimaryKey(Integer id);

    public int updateByPrimaryKeySelective(LogDayXhMediaSiteCount record);

    public int updateByPrimaryKey(LogDayXhMediaSiteCount record);
    
    public List<LogDayXhMediaSiteCount> getSiteData();
    
    public List<LogDayXhMediaSiteCount> getSiteDataByOriginId(String originId);
    
    public List<LogDayXhMediaSiteCount> getSiteDataNew(LogDayXhMediaSiteCount ldxmsc);
    
    public List<LogDayXhMediaSiteCount> searchDaySiteDataDetail(LogDayXhMediaSiteCount ldxmsc);
}
