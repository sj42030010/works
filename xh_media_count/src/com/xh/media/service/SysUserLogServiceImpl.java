package com.xh.media.service;

import java.util.List;

import com.xh.media.dao.SysUseLogMapper;
import com.xh.media.model.SysUseLog;

public class SysUserLogServiceImpl implements SysUserLogService {
	private SysUseLogMapper sysUseLogMapper;
	

	public void setSysUseLogMapper(SysUseLogMapper sysUseLogMapper) {
		this.sysUseLogMapper = sysUseLogMapper;
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return sysUseLogMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(SysUseLog record) {
		return sysUseLogMapper.insert(record);
	}

	@Override
	public int insertSelective(SysUseLog record) {
		return sysUseLogMapper.insertSelective(record);
	}

	@Override
	public SysUseLog selectByPrimaryKey(Integer id) {
		return sysUseLogMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SysUseLog record) {
		return sysUseLogMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SysUseLog record) {
		return sysUseLogMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<SysUseLog> selectAllData() {
		return sysUseLogMapper.selectAllData();
	}

	@Override
	public List<SysUseLog> searchUserLog(SysUseLog record) {
		return sysUseLogMapper.searchUserLog(record);
	}

}
