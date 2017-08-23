package com.xh.media.service;

import java.util.List;

import com.xh.media.model.SysMenuList;

public interface SysMenuListService {
	public int deleteByPrimaryKey(Short menuId);

    public int insert(SysMenuList record);

    public int insertSelective(SysMenuList record);

    public SysMenuList selectByPrimaryKey(Short menuId);

    public int updateByPrimaryKeySelective(SysMenuList record);

    public int updateByPrimaryKey(SysMenuList record);
    
    public List<SysMenuList> getMenuList();
    
    public List<SysMenuList> getAllMenuList(String dept);
}
