package com.xh.media.service;

import java.util.List;

import com.xh.media.model.LogDayXhMediaAppCount;

public interface LogDayXhMediaAppCountService {

	public int deleteByPrimaryKey(Integer id);

    public int insert(LogDayXhMediaAppCount record);

    public int insertSelective(LogDayXhMediaAppCount record);

    public LogDayXhMediaAppCount selectByPrimaryKey(Integer id);

    public int updateByPrimaryKeySelective(LogDayXhMediaAppCount record);

    public int updateByPrimaryKey(LogDayXhMediaAppCount record);
    
    public List<LogDayXhMediaAppCount> getAppData();
    
    public List<LogDayXhMediaAppCount> getAppDataByOriginId(String originId);
    
    public List<LogDayXhMediaAppCount> getAppDataNew(LogDayXhMediaAppCount ldxmac);
    
    public List<LogDayXhMediaAppCount> searchDayAppDataDetail(LogDayXhMediaAppCount ldxmac);
}
