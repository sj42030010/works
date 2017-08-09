package com.xh.media.service;

import java.util.List;

import com.xh.media.dao.SysMenuListMapper;
import com.xh.media.model.SysMenuList;

public class SysMenuListServiceImpl implements SysMenuListService {
	private SysMenuListMapper sysMenuListMapper;
	public SysMenuListMapper getSysMenuListMapper() {
		return sysMenuListMapper;
	}

	public void setSysMenuListMapper(SysMenuListMapper sysMenuListMapper) {
		this.sysMenuListMapper = sysMenuListMapper;
	}

	@Override
	public int deleteByPrimaryKey(Short menuId) {
		// TODO Auto-generated method stub
		return sysMenuListMapper.deleteByPrimaryKey(menuId);
	}

	@Override
	public int insert(SysMenuList record) {
		// TODO Auto-generated method stub
		return sysMenuListMapper.insert(record);
	}

	@Override
	public int insertSelective(SysMenuList record) {
		// TODO Auto-generated method stub
		return sysMenuListMapper.insertSelective(record);
	}

	@Override
	public SysMenuList selectByPrimaryKey(Short menuId) {
		// TODO Auto-generated method stub
		return sysMenuListMapper.selectByPrimaryKey(menuId);
	}

	@Override
	public int updateByPrimaryKeySelective(SysMenuList record) {
		// TODO Auto-generated method stub
		return sysMenuListMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SysMenuList record) {
		// TODO Auto-generated method stub
		return sysMenuListMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<SysMenuList> getMenuList() {
		// TODO Auto-generated method stub
		return sysMenuListMapper.getMenuList();
	}
	
	@Override
	public List<SysMenuList> getAllMenuList(String dept){
		return sysMenuListMapper.getAllMenuList(dept);
	}
}
