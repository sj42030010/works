package com.xh.media.service;

import java.util.List;

import com.xh.media.model.SysGroupOrigin;

public interface SysGroupOriginService {
	public int deleteByPrimaryKey(Integer id);

	public int insert(SysGroupOrigin record);

	public int insertSelective(SysGroupOrigin record);

	public SysGroupOrigin selectByPrimaryKey(Integer id);

	public int updateByPrimaryKeySelective(SysGroupOrigin record);

	public int updateByPrimaryKey(SysGroupOrigin record);
	
	public List<SysGroupOrigin> findOriginListBySiteCode(String siteCode);
	
	public SysGroupOrigin findOriginByOriginId(String originId);
}
