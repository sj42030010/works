package com.xh.media.service;

import java.util.List;

import com.xh.media.model.SysUserGroup;

public interface SysUserGroupService {
    public int deleteByPrimaryKey(Integer id);

    public int insert(SysUserGroup record);

    public int insertSelective(SysUserGroup record);

    public SysUserGroup selectByPrimaryKey(Integer id);

    public int updateByPrimaryKeySelective(SysUserGroup record);

    public int updateByPrimaryKey(SysUserGroup record);
    
    public List<Integer> getSiteCodeByUserId(Integer userId);
    
    public int getIdByUserIdGroupId(int userId, int groupId);
    
    public  List<SysUserGroup> getSysUserGroupByGroupId(int groupId);
    
    public int updateGroupIdById(SysUserGroup record);
}
