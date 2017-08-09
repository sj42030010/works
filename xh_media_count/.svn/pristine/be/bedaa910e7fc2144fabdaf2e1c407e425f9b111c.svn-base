package com.xh.media.service;

import java.util.List;

import com.xh.media.dao.IndexXhMediaProgramMapper;
import com.xh.media.model.IndexXhMediaProgram;

public class IndexXhMediaProgramServiceImpl implements IndexXhMediaProgramService{

	private IndexXhMediaProgramMapper indexXhMediaProgramMapper;
	
	
	public IndexXhMediaProgramMapper getIndexXhMediaProgramMapper() {
		return indexXhMediaProgramMapper;
	}

	public void setIndexXhMediaProgramMapper(
			IndexXhMediaProgramMapper indexXhMediaProgramMapper) {
		this.indexXhMediaProgramMapper = indexXhMediaProgramMapper;
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return indexXhMediaProgramMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(IndexXhMediaProgram record) {
		return indexXhMediaProgramMapper.insert(record);
	}

	@Override
	public int insertSelective(IndexXhMediaProgram record) {
		return indexXhMediaProgramMapper.insertSelective(record);
	}

	@Override
	public IndexXhMediaProgram selectByPrimaryKey(Integer id) {
		return indexXhMediaProgramMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(IndexXhMediaProgram record) {
		return indexXhMediaProgramMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(IndexXhMediaProgram record) {
		
		return indexXhMediaProgramMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<IndexXhMediaProgram> getProgramByChannel(
			IndexXhMediaProgram record) {
		System.out.println("channel_id"+record.getChannelId());
		System.out.println("site_code"+record.getSiteCode());
		List<IndexXhMediaProgram> programByChannel = indexXhMediaProgramMapper.getProgramByChannel(record);
		for (IndexXhMediaProgram indexXhMediaProgram : programByChannel) {
			System.out.println(indexXhMediaProgram.getChineseName());
		}
		return indexXhMediaProgramMapper.getProgramByChannel(record);
	}
	
	@Override
	public IndexXhMediaProgram getProgramByGlobalIdAndSiteCode(String globalId, String siteCode){
		return indexXhMediaProgramMapper.getProgramByGlobalIdAndSiteCode(globalId, siteCode);
	}
}
