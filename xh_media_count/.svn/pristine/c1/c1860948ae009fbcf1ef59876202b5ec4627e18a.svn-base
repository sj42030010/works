package com.xh.media.dao;

import java.util.List;

import com.xh.media.model.SysUserGroup;

public interface SysUserGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUserGroup record);

    int insertSelective(SysUserGroup record);

    SysUserGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUserGroup record);

    int updateByPrimaryKey(SysUserGroup record);
    
    List<Integer> getSiteCodeByUserId(Integer userId);
    
    int getIdByUserIdGroupId(int userId, int groupId);
    
    List<SysUserGroup> getSysUserGroupByGroupId(int groupId);
    
    int updateGroupIdById(SysUserGroup record);
}