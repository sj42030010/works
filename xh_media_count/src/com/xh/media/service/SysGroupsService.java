package com.xh.media.service;

import java.util.List;

import com.xh.media.model.SysGroups;

public interface SysGroupsService {
	public int deleteByPrimaryKey(Integer id);

	public int insert(SysGroups record);

	public int insertSelective(SysGroups record);

	public SysGroups selectByPrimaryKey(Integer id);

	public int updateByPrimaryKeySelective(SysGroups record);

	public int updateByPrimaryKey(SysGroups record);
	
	public List<SysGroups> findGroupsByUserId(int userId);
	
	public List<SysGroups> findGroupsByDept(int dept);
	
	public List<SysGroups> findAllGroups();
	
	public List<SysGroups> findGroupsByName(SysGroups record);
	
	public  int updateByName(SysGroups record);
	
    public int updateById(SysGroups record);
    
    public int updateByOldId(SysGroups record);
}
