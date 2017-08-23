package com.xh.media.service;

import java.util.List;

import com.xh.media.dao.SysUsersMapper;
import com.xh.media.model.SysUsers;

public class SysUsersServiceImpl implements SysUsersService {
	private SysUsersMapper sysUsersMapper;
	public SysUsersMapper getSysUsersMapper() {
		return sysUsersMapper;
	}

	public void setSysUsersMapper(SysUsersMapper sysUsersMapper) {
		this.sysUsersMapper = sysUsersMapper;
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return sysUsersMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(SysUsers record) {
		// TODO Auto-generated method stub
		return sysUsersMapper.insert(record);
	}

	@Override
	public int insertSelective(SysUsers record) {
		// TODO Auto-generated method stub
		return sysUsersMapper.insertSelective(record);
	}

	@Override
	public SysUsers selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return sysUsersMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SysUsers record) {
		// TODO Auto-generated method stub
		return sysUsersMapper.updateByPrimaryKeySelective(record);
	}

	//@Override
	//public int updateByPrimaryKeyWithBLOBs(SysUsers record) {
		// TODO Auto-generated method stub
	//	return sysUsersMapper.updateByPrimaryKeyWithBLOBs(record);
	//}

	@Override
	public int updateByPrimaryKey(SysUsers record) {
		// TODO Auto-generated method stub
		return sysUsersMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<String> loadUserAuthorities(String userAccount) {
		// TODO Auto-generated method stub
		return sysUsersMapper.loadUserAuthorities(userAccount);
	}

	@Override
	public String getPasswordByUseraccount(String userAccount) {
		// TODO Auto-generated method stub
		return sysUsersMapper.getPasswordByUseraccount(userAccount);
	}

	@Override
	public SysUsers getUserByUsername(String userAccount) {
		// TODO Auto-generated method stub
		return sysUsersMapper.getUserByUsername(userAccount);
	}
	
	public SysUsers getUserByUseraccount(String userAccount){
		return sysUsersMapper.getUserByUseraccount(userAccount);
	}

	@Override
	public List getSysUsersList(SysUsers su) {
		// TODO Auto-generated method stub
		return sysUsersMapper.getSysUsersList(su);
	}
	
	@Override
	public List getAllSysUsersList(SysUsers su) {
		// TODO Auto-generated method stub
		return sysUsersMapper.getAllSysUsersList(su);
	}

	@Override
	public int getMaxId() {
		// TODO Auto-generated method stub
		return sysUsersMapper.getMaxId();
	}

	@Override
	public List<String> getUserCanVisitUrl(String userAccount){
		return sysUsersMapper.getUserCanVisitUrl(userAccount);
	}
	
	@Override
	public List<String> getDeptList(int dept){
		return sysUsersMapper.getDeptList(dept);
	}
	
	@Override
	public int countSysUserByAccountDeptAndStatus(String userAccount, String dept){
		return sysUsersMapper.countSysUserByAccountDeptAndStatus(userAccount, dept);
	}
	
	@Override
	public int getUserIdByAccountDeptAndStatus(String userAccount, String dept){
		return sysUsersMapper.getUserIdByAccountDeptAndStatus(userAccount, dept);
	}

	@Override
	public List<Integer> getIdByDept(String dept) {
		return sysUsersMapper.getIdByDept(dept);
	}

	@Override
	public int updateDeptById(SysUsers su) {
		return sysUsersMapper.updateDeptById(su);
	}
}
