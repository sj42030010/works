package com.xh.media.service;

import java.util.List;

import com.xh.media.model.SysUsers;

public interface SysUsersService {
	public int deleteByPrimaryKey(Integer id);

	public int insert(SysUsers record);

	public int insertSelective(SysUsers record);

	public SysUsers selectByPrimaryKey(Integer id);

	public int updateByPrimaryKeySelective(SysUsers record);

	//public int updateByPrimaryKeyWithBLOBs(SysUsers record);

	public int updateByPrimaryKey(SysUsers record);
    
	public List<String> loadUserAuthorities(String userAccount);
    
	public String getPasswordByUseraccount(String userAccount);
    
	public SysUsers getUserByUsername(String userAccount);
	
	public SysUsers getUserByUseraccount(String userAccount);
	
	public List<SysUsers> getSysUsersList(SysUsers su);
	
	public List<SysUsers> getAllSysUsersList(SysUsers su);
	
	public int getMaxId();
	
	public List<String> getUserCanVisitUrl(String userAccount);
	
	public List<String> getDeptList(int dept);
	
	public int countSysUserByAccountDeptAndStatus(String userAccount, String dept);
	
	public int getUserIdByAccountDeptAndStatus(String userAccount, String dept);
	
    public List<Integer> getIdByDept(String dept);
    
    public int updateDeptById(SysUsers su);
}
