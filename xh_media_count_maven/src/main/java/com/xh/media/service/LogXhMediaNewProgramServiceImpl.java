package com.xh.media.service;

import java.util.List;

import com.xh.media.dao.LogXhMediaNewProgramMapper;
import com.xh.media.model.LogXhMediaNewProgram;

public class LogXhMediaNewProgramServiceImpl implements LogXhMediaNewProgramService{

	private LogXhMediaNewProgramMapper logXhMediaNewProgramMapper;
	
	public LogXhMediaNewProgramMapper getLogXhMediaNewProgramMapper() {
		return logXhMediaNewProgramMapper;
	}

	public void setLogXhMediaNewProgramMapper(
			LogXhMediaNewProgramMapper logXhMediaNewProgramMapper) {
		this.logXhMediaNewProgramMapper = logXhMediaNewProgramMapper;
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return logXhMediaNewProgramMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(LogXhMediaNewProgram record) {
		return logXhMediaNewProgramMapper.insert(record);
	}

	@Override
	public int insertSelective(LogXhMediaNewProgram record) {
		return logXhMediaNewProgramMapper.insertSelective(record);
	}

	@Override
	public LogXhMediaNewProgram selectByPrimaryKey(Integer id) {
		return logXhMediaNewProgramMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(LogXhMediaNewProgram record) {
		return logXhMediaNewProgramMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(LogXhMediaNewProgram record) {
		return logXhMediaNewProgramMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<LogXhMediaNewProgram> getNewProgramDetail(
			LogXhMediaNewProgram record) {
		return logXhMediaNewProgramMapper.getNewProgramDetail(record);
	}

	@Override
	public List<LogXhMediaNewProgram> searchNewProgramDetail(
			LogXhMediaNewProgram record) {
		return logXhMediaNewProgramMapper.searchNewProgramDetail(record);
	}
	
	@Override
	public List<LogXhMediaNewProgram> searchNewProgramListNew(LogXhMediaNewProgram record){
		return logXhMediaNewProgramMapper.searchNewProgramListNew(record);
	}

}
