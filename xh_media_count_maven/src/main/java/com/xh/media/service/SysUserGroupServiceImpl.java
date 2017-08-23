package com.xh.media.service;

import java.util.List;

import com.xh.media.dao.SysUserGroupMapper;
import com.xh.media.model.SysUserGroup;

public class SysUserGroupServiceImpl implements SysUserGroupService {
	private SysUserGroupMapper sysUserGroupMapper;
	public SysUserGroupMapper getSysUserGroupMapper() {
		return sysUserGroupMapper;
	}

	public void setSysUserGroupMapper(SysUserGroupMapper sysUserGroupMapper) {
		this.sysUserGroupMapper = sysUserGroupMapper;
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return sysUserGroupMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(SysUserGroup record) {
		// TODO Auto-generated method stub
		return sysUserGroupMapper.insert(record);
	}

	@Override
	public int insertSelective(SysUserGroup record) {
		// TODO Auto-generated method stub
		return sysUserGroupMapper.insertSelective(record);
	}

	@Override
	public SysUserGroup selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		System.out.println("id="+id+"\n"+"data="+sysUserGroupMapper.selectByPrimaryKey(id));
		return sysUserGroupMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SysUserGroup record) {
		// TODO Auto-generated method stub
		return sysUserGroupMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SysUserGroup record) {
		// TODO Auto-generated method stub
		return sysUserGroupMapper.updateByPrimaryKey(record);
	}
	
	@Override
	public List<Integer> getSiteCodeByUserId(Integer userId){
		return sysUserGroupMapper.getSiteCodeByUserId(userId);
	}
	
	@Override
	public int getIdByUserIdGroupId(int userId, int groupId){
		return sysUserGroupMapper.getIdByUserIdGroupId(userId, groupId);
	}

	@Override
	public List<SysUserGroup> getSysUserGroupByGroupId(int groupId) {
		return sysUserGroupMapper.getSysUserGroupByGroupId(groupId);
	}

	@Override
	public int updateGroupIdById(SysUserGroup record) {
		
		return sysUserGroupMapper.updateGroupIdById(record);
	}

}
