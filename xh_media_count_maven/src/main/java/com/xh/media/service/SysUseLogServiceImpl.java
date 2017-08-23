package com.xh.media.service;

import com.xh.media.dao.SysUseLogMapper;
import com.xh.media.model.SysUseLog;

public class SysUseLogServiceImpl implements SysUseLogService {
	private SysUseLogMapper sysUseLogMapper;
	public SysUseLogMapper getSysUseLogMapper() {
		return sysUseLogMapper;
	}

	public void setSysUseLogMapper(SysUseLogMapper sysUseLogMapper) {
		this.sysUseLogMapper = sysUseLogMapper;
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return sysUseLogMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(SysUseLog record) {
		// TODO Auto-generated method stub
		return sysUseLogMapper.insert(record);
	}

	@Override
	public int insertSelective(SysUseLog record) {
		// TODO Auto-generated method stub
		return sysUseLogMapper.insertSelective(record);
	}

	@Override
	public SysUseLog selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return sysUseLogMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SysUseLog record) {
		// TODO Auto-generated method stub
		return sysUseLogMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SysUseLog record) {
		// TODO Auto-generated method stub
		return sysUseLogMapper.updateByPrimaryKey(record);
	}

}
