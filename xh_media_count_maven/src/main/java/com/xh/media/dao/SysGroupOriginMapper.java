package com.xh.media.dao;

import java.util.List;

import com.xh.media.model.SysGroupOrigin;

public interface SysGroupOriginMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysGroupOrigin record);

    int insertSelective(SysGroupOrigin record);

    SysGroupOrigin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysGroupOrigin record);

    int updateByPrimaryKey(SysGroupOrigin record);
    
    List<SysGroupOrigin> findOriginListBySiteCode(String siteCode);
    
    SysGroupOrigin findOriginByOriginId(String originId);
}