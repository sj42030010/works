package com.xh.media.service;

import java.util.List;

import com.xh.media.dao.LogDayXhMediaNewProgramMapper;
import com.xh.media.model.LogDayXhMediaNewProgram;

public class LogDayXhMediaNewProgramServiceImpl implements
		LogDayXhMediaNewProgramService {

	private LogDayXhMediaNewProgramMapper logDayXhMediaNewProgramMapper;
	
	public LogDayXhMediaNewProgramMapper getLogDayXhMediaNewProgramMapper() {
		return logDayXhMediaNewProgramMapper;
	}

	public void setLogDayXhMediaNewProgramMapper(
			LogDayXhMediaNewProgramMapper logDayXhMediaNewProgramMapper) {
		this.logDayXhMediaNewProgramMapper = logDayXhMediaNewProgramMapper;
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return logDayXhMediaNewProgramMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(LogDayXhMediaNewProgram record) {
		return logDayXhMediaNewProgramMapper.insert(record);
	}

	@Override
	public int insertSelective(LogDayXhMediaNewProgram record) {
		return logDayXhMediaNewProgramMapper.insertSelective(record);
	}

	@Override
	public LogDayXhMediaNewProgram selectByPrimaryKey(Integer id) {
		return logDayXhMediaNewProgramMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(LogDayXhMediaNewProgram record) {
		return logDayXhMediaNewProgramMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(LogDayXhMediaNewProgram record) {
		return logDayXhMediaNewProgramMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<LogDayXhMediaNewProgram> getNewProgramList() {
		return logDayXhMediaNewProgramMapper.getNewProgramList();
	}

	@Override
	public List<LogDayXhMediaNewProgram> searchNewProgramList(
			LogDayXhMediaNewProgram record) {
		return logDayXhMediaNewProgramMapper.searchNewProgramList(record);
	}
	
	@Override
	public List<LogDayXhMediaNewProgram> searchNewProgramListNew(LogDayXhMediaNewProgram record){
		return logDayXhMediaNewProgramMapper.searchNewProgramListNew(record);
	}
	
	@Override
	public List<LogDayXhMediaNewProgram> searchDayNewProgramListNew(LogDayXhMediaNewProgram record){
		return logDayXhMediaNewProgramMapper.searchDayNewProgramListNew(record);
	}
}
