package com.xh.media.service;

import java.util.List;

import com.xh.media.model.LogDayXhMediaNewProgram;

public interface LogDayXhMediaNewProgramService {

	public int deleteByPrimaryKey(Integer id);

    public int insert(LogDayXhMediaNewProgram record);

    public int insertSelective(LogDayXhMediaNewProgram record);

    public LogDayXhMediaNewProgram selectByPrimaryKey(Integer id);

    public int updateByPrimaryKeySelective(LogDayXhMediaNewProgram record);

    public int updateByPrimaryKey(LogDayXhMediaNewProgram record);
    
    public List<LogDayXhMediaNewProgram> getNewProgramList();
    
    public List<LogDayXhMediaNewProgram> searchNewProgramList(LogDayXhMediaNewProgram record);
    
    public List<LogDayXhMediaNewProgram> searchNewProgramListNew(LogDayXhMediaNewProgram record);
    
    public List<LogDayXhMediaNewProgram> searchDayNewProgramListNew(LogDayXhMediaNewProgram record);
}
