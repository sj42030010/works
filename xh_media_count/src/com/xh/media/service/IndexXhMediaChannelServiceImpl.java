package com.xh.media.service;

import java.util.List;

import com.xh.media.dao.IndexXhMediaChannelMapper;
import com.xh.media.model.IndexXhMediaChannel;
import com.xh.media.model.IndexXhMediaOrigin;

public class IndexXhMediaChannelServiceImpl implements
		IndexXhMediaChannelService {
	private IndexXhMediaChannelMapper indexXhMediaChannelMapper;
	public IndexXhMediaChannelMapper getIndexXhMediaChannelMapper() {
		return indexXhMediaChannelMapper;
	}

	public void setIndexXhMediaChannelMapper(
			IndexXhMediaChannelMapper indexXhMediaChannelMapper) {
		this.indexXhMediaChannelMapper = indexXhMediaChannelMapper;
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return indexXhMediaChannelMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(IndexXhMediaChannel record) {
		// TODO Auto-generated method stub
		return indexXhMediaChannelMapper.insert(record);
	}

	@Override
	public int insertSelective(IndexXhMediaChannel record) {
		// TODO Auto-generated method stub
		return indexXhMediaChannelMapper.insertSelective(record);
	}

	@Override
	public IndexXhMediaChannel selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return indexXhMediaChannelMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(IndexXhMediaChannel record) {
		// TODO Auto-generated method stub
		return indexXhMediaChannelMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(IndexXhMediaChannel record) {
		// TODO Auto-generated method stub
		return indexXhMediaChannelMapper.updateByPrimaryKey(record);
	}
	
	@Override
	public List<IndexXhMediaChannel> getChannelsByOrigin(String originId){
		return indexXhMediaChannelMapper.getChannelsByOrigin(originId);
	}

	@Override
	public List<IndexXhMediaChannel> getChannels(String originId,
			String siteCode) {
		return indexXhMediaChannelMapper.getChannels(originId, siteCode);
	}

	@Override
	public List<IndexXhMediaChannel> getParts(String originId, String siteCode,
			String channelId) {
		return indexXhMediaChannelMapper.getParts(originId, siteCode, channelId);
	}
	
	@Override
	public IndexXhMediaChannel getChannelByChannelIdAndSiteCode(String channelId, String siteCode){
		return indexXhMediaChannelMapper.getChannelByChannelIdAndSiteCode(channelId, siteCode);
	}
}
