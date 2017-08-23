package com.xh.media.dao;

import java.util.List;

import com.xh.media.model.SysUsers;

public interface SysUsersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUsers record);

    int insertSelective(SysUsers record);

    SysUsers selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUsers record);

    int updateByPrimaryKeyWithBLOBs(SysUsers record);

    int updateByPrimaryKey(SysUsers record);
    
    List<String> loadUserAuthorities(String userAccount);
    
    String getPasswordByUseraccount(String userAccount);
    
    SysUsers getUserByUsername(String userAccount);
    
    SysUsers getUserByUseraccount(String userAccount);
    
    List<SysUsers> getSysUsersList(SysUsers su);
    
    List<SysUsers> getAllSysUsersList(SysUsers su);
    
    int getMaxId();
    
    List<String> getUserCanVisitUrl(String userAccount);
    
    List<String> getDeptList(int dept);
    
    int countSysUserByAccountDeptAndStatus(String userAccount, String dept);
    
    int getUserIdByAccountDeptAndStatus(String userAccount, String dept);
    
    List<Integer> getIdByDept(String dept);
    
    int updateDeptById(SysUsers su);
}