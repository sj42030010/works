package com.xh.media.dao;

import java.util.List;

import com.xh.media.model.LogXhMediaAppCount;

public interface LogXhMediaAppCountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LogXhMediaAppCount record);

    int insertSelective(LogXhMediaAppCount record);

    LogXhMediaAppCount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LogXhMediaAppCount record);

    int updateByPrimaryKey(LogXhMediaAppCount record);
    
    List<LogXhMediaAppCount> getAppDataDetail(String originId);
    
    List<LogXhMediaAppCount> searchAppDataDetail(LogXhMediaAppCount record);
}