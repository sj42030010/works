package com.xh.media.dao;

import java.util.List;

import com.xh.media.model.SysMenuList;

public interface SysMenuListMapper {
    int deleteByPrimaryKey(Short menuId);

    int insert(SysMenuList record);

    int insertSelective(SysMenuList record);

    SysMenuList selectByPrimaryKey(Short menuId);

    int updateByPrimaryKeySelective(SysMenuList record);

    int updateByPrimaryKey(SysMenuList record);
    
    List<SysMenuList> getMenuList();
    
    List<SysMenuList> getAllMenuList(String dept);
}