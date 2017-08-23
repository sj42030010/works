package com.xh.media.service;

import java.util.List;

import com.xh.media.model.IndexXhMediaProgram;

public interface IndexXhMediaProgramService {

	public int deleteByPrimaryKey(Integer id);

	public int insert(IndexXhMediaProgram record);

	public int insertSelective(IndexXhMediaProgram record);

	public IndexXhMediaProgram selectByPrimaryKey(Integer id);

	public int updateByPrimaryKeySelective(IndexXhMediaProgram record);

	public int updateByPrimaryKey(IndexXhMediaProgram record);
    
	public List<IndexXhMediaProgram> getProgramByChannel(IndexXhMediaProgram record);
    
    public IndexXhMediaProgram getProgramByGlobalIdAndSiteCode(String globalId, String siteCode);
}
