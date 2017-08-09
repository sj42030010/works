package com.xh.media.dao;

import java.util.List;

import com.xh.media.model.SysGroups;

public interface SysGroupsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysGroups record);

    int insertSelective(SysGroups record);

    SysGroups selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysGroups record);

    int updateByPrimaryKey(SysGroups record);
    
    List<SysGroups> findGroupsByUserId(int userId);
    
    List<SysGroups> findGroupsByDept(int dept);
    
    List<SysGroups> findAllGroups();
    
    List<SysGroups> findGroupsByName(SysGroups record);
    
    int updateByName(SysGroups record);
    
    int updateById(SysGroups record);
    
    int updateByOldId(SysGroups record);
}