package com.xh.media.service;

import java.util.List;

import com.xh.media.model.LogXhMediaAppCount;

public interface LogXhMediaAppCountService {

	public int deleteByPrimaryKey(Integer id);

	public int insert(LogXhMediaAppCount record);

	public int insertSelective(LogXhMediaAppCount record);

	public LogXhMediaAppCount selectByPrimaryKey(Integer id);

	public int updateByPrimaryKeySelective(LogXhMediaAppCount record);

	public int updateByPrimaryKey(LogXhMediaAppCount record);
    
	public List<LogXhMediaAppCount> getAppDataDetail(String originId);
    
	public List<LogXhMediaAppCount> searchAppDataDetail(LogXhMediaAppCount record);
}
