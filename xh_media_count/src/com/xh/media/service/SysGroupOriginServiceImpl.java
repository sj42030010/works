package com.xh.media.service;

import java.util.List;

import com.xh.media.dao.SysGroupOriginMapper;
import com.xh.media.model.SysGroupOrigin;

public class SysGroupOriginServiceImpl implements SysGroupOriginService {
	private SysGroupOriginMapper sysGroupOriginMapper;
	public SysGroupOriginMapper getSysGroupOriginMapper() {
		return sysGroupOriginMapper;
	}

	public void setSysGroupOriginMapper(SysGroupOriginMapper sysGroupOriginMapper) {
		this.sysGroupOriginMapper = sysGroupOriginMapper;
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return sysGroupOriginMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(SysGroupOrigin record) {
		// TODO Auto-generated method stub
		return sysGroupOriginMapper.insert(record);
	}

	@Override
	public int insertSelective(SysGroupOrigin record) {
		// TODO Auto-generated method stub
		return sysGroupOriginMapper.insertSelective(record);
	}

	@Override
	public SysGroupOrigin selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return sysGroupOriginMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SysGroupOrigin record) {
		// TODO Auto-generated method stub
		return sysGroupOriginMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SysGroupOrigin record) {
		// TODO Auto-generated method stub
		return sysGroupOriginMapper.updateByPrimaryKey(record);
	}
	
	@Override
	public List<SysGroupOrigin> findOriginListBySiteCode(String siteCode){
		return sysGroupOriginMapper.findOriginListBySiteCode(siteCode);
	}
	
	@Override
	public SysGroupOrigin findOriginByOriginId(String originId){
		return sysGroupOriginMapper.findOriginByOriginId(originId);
	}
}
