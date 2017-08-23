package com.xh.media.dao;

import java.util.List;

import com.xh.media.model.SysUseLog;

public interface SysUseLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUseLog record);

    int insertSelective(SysUseLog record);

    SysUseLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUseLog record);

    int updateByPrimaryKey(SysUseLog record);

    List<SysUseLog> selectAllData();
    
    List<SysUseLog> searchUserLog(SysUseLog record);
}