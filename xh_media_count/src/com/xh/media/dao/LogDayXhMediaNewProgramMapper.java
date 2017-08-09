package com.xh.media.dao;

import java.util.List;

import com.xh.media.model.LogDayXhMediaNewProgram;

public interface LogDayXhMediaNewProgramMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LogDayXhMediaNewProgram record);

    int insertSelective(LogDayXhMediaNewProgram record);

    LogDayXhMediaNewProgram selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LogDayXhMediaNewProgram record);

    int updateByPrimaryKey(LogDayXhMediaNewProgram record);
    
    List<LogDayXhMediaNewProgram> getNewProgramList();
    
    List<LogDayXhMediaNewProgram> searchNewProgramList(LogDayXhMediaNewProgram record);
    
    List<LogDayXhMediaNewProgram> searchNewProgramListNew(LogDayXhMediaNewProgram record);
    
    List<LogDayXhMediaNewProgram> searchDayNewProgramListNew(LogDayXhMediaNewProgram record);
}