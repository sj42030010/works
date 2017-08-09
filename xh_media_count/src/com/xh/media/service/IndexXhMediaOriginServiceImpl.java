package com.xh.media.service;

import java.util.List;

import com.xh.media.dao.IndexXhMediaOriginMapper;
import com.xh.media.model.IndexXhMediaOrigin;

public class IndexXhMediaOriginServiceImpl implements IndexXhMediaOriginService {
	private IndexXhMediaOriginMapper indexXhMediaOriginMapper;
	public IndexXhMediaOriginMapper getIndexXhMediaOriginMapper() {
		return indexXhMediaOriginMapper;
	}

	public void setIndexXhMediaOriginMapper(
			IndexXhMediaOriginMapper indexXhMediaOriginMapper) {
		this.indexXhMediaOriginMapper = indexXhMediaOriginMapper;
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return indexXhMediaOriginMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(IndexXhMediaOrigin record) {
		// TODO Auto-generated method stub
		return indexXhMediaOriginMapper.insert(record);
	}

	@Override
	public int insertSelective(IndexXhMediaOrigin record) {
		// TODO Auto-generated method stub
		return indexXhMediaOriginMapper.insertSelective(record);
	}

	@Override
	public IndexXhMediaOrigin selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return indexXhMediaOriginMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(IndexXhMediaOrigin record) {
		// TODO Auto-generated method stub
		return indexXhMediaOriginMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(IndexXhMediaOrigin record) {
		// TODO Auto-generated method stub
		return indexXhMediaOriginMapper.updateByPrimaryKey(record);
	}
	
	@Override
	public List<IndexXhMediaOrigin> getAllOrigin(){
		return indexXhMediaOriginMapper.getAllOrigin();
	}
	
	@Override
	public List<IndexXhMediaOrigin> getAllOriginBySiteCode(String siteCode){
		return indexXhMediaOriginMapper.getAllOriginBySiteCode(siteCode);
	}
	
	@Override
	public String getOriginNameByOriginId(String originId){
		return indexXhMediaOriginMapper.getOriginNameByOriginId(originId);
	}
}
