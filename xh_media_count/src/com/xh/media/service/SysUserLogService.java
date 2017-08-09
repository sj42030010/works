package com.xh.media.service;

import java.util.List;

import com.xh.media.model.SysUseLog;

public interface SysUserLogService {

    int deleteByPrimaryKey(Integer id);

    int insert(SysUseLog record);

    int insertSelective(SysUseLog record);

    SysUseLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUseLog record);

    int updateByPrimaryKey(SysUseLog record);

    List<SysUseLog> selectAllData();
    
    List<SysUseLog> searchUserLog(SysUseLog record);
}
