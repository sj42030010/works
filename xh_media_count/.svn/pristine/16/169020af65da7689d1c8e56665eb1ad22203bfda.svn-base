package com.xh.media.service;

import java.util.List;

import com.xh.media.dao.SysGroupsMapper;
import com.xh.media.model.SysGroups;

public class SysGroupsServiceImpl implements SysGroupsService {
	private SysGroupsMapper sysGroupsMapper;
	public SysGroupsMapper getSysGroupsMapper() {
		return sysGroupsMapper;
	}

	public void setSysGroupsMapper(SysGroupsMapper sysGroupsMapper) {
		this.sysGroupsMapper = sysGroupsMapper;
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return sysGroupsMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(SysGroups record) {
		// TODO Auto-generated method stub
		return sysGroupsMapper.insert(record);
	}

	@Override
	public int insertSelective(SysGroups record) {
		// TODO Auto-generated method stub
		return sysGroupsMapper.insertSelective(record);
	}

	@Override
	public SysGroups selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return sysGroupsMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SysGroups record) {
		// TODO Auto-generated method stub
		return sysGroupsMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SysGroups record) {
		// TODO Auto-generated method stub
		return sysGroupsMapper.updateByPrimaryKey(record);
	}
	
	@Override
	public List<SysGroups> findGroupsByUserId(int userId){
		return sysGroupsMapper.findGroupsByUserId(userId);
	}
	
	@Override
	public List<SysGroups> findGroupsByDept(int dept){
		return sysGroupsMapper.findGroupsByDept(dept);
	}
	
	@Override
	public List<SysGroups> findAllGroups(){
		return sysGroupsMapper.findAllGroups();
	}

	@Override
	public List<SysGroups> findGroupsByName(SysGroups record) {
		return sysGroupsMapper.findGroupsByName(record);
	}

	@Override
	public int updateByName(SysGroups record) {
		return sysGroupsMapper.updateByName(record);
	}

	@Override
	public int updateById(SysGroups record) {
		return sysGroupsMapper.updateById(record);
	}

	@Override
	public int updateByOldId(SysGroups record) {
		return sysGroupsMapper.updateByOldId(record);
	}
}
