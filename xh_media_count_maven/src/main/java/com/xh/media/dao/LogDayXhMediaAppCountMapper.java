package com.xh.media.dao;

import java.util.List;

import com.xh.media.model.LogDayXhMediaAppCount;

public interface LogDayXhMediaAppCountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LogDayXhMediaAppCount record);

    int insertSelective(LogDayXhMediaAppCount record);

    LogDayXhMediaAppCount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LogDayXhMediaAppCount record);

    int updateByPrimaryKey(LogDayXhMediaAppCount record);
     
    List<LogDayXhMediaAppCount> getAppData();

    List<LogDayXhMediaAppCount> getAppDataByOriginId(String originId);
    
    List<LogDayXhMediaAppCount> getAppDataNew(LogDayXhMediaAppCount ldxmac);
    
    List<LogDayXhMediaAppCount> searchDayAppDataDetail(LogDayXhMediaAppCount ldxmac);
}