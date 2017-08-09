package com.xh.media.dao;

import java.util.List;

import com.xh.media.model.IndexXhMediaChannel;

public interface IndexXhMediaChannelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(IndexXhMediaChannel record);

    int insertSelective(IndexXhMediaChannel record);

    IndexXhMediaChannel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IndexXhMediaChannel record);

    int updateByPrimaryKey(IndexXhMediaChannel record);
    
    List<IndexXhMediaChannel> getChannelsByOrigin(String originId);
    
    List<IndexXhMediaChannel> getChannels(String originId,String siteCode);
    
    List<IndexXhMediaChannel>  getParts(String originId,String siteCode,String channelId);
    
    IndexXhMediaChannel getChannelByChannelIdAndSiteCode(String channelId, String siteCode);
}