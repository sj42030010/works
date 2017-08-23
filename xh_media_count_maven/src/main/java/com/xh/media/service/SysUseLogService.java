package com.xh.media.service;

import com.xh.media.model.SysUseLog;

public interface SysUseLogService {
	public int deleteByPrimaryKey(Integer id);

	public int insert(SysUseLog record);

	public int insertSelective(SysUseLog record);

	public SysUseLog selectByPrimaryKey(Integer id);

	public int updateByPrimaryKeySelective(SysUseLog record);

	public int updateByPrimaryKey(SysUseLog record);
}
