package com.xh.media.bean;

import java.io.Serializable;
import java.util.List;

public class OriginBean implements Serializable{
	private String originId;
	private String originName;
	private String siteCode;
	private List<ChannelBean> channelList;
	
	public String getSiteCode() {
		return siteCode;
	}
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}
	public List<ChannelBean> getChannelList() {
		return channelList;
	}
	public void setChannelList(List<ChannelBean> channelList) {
		this.channelList = channelList;
	}
	public String getOriginId() {
		return originId;
	}
	public void setOriginId(String originId) {
		this.originId = originId;
	}
	public String getOriginName() {
		return originName;
	}
	public void setOriginName(String originName) {
		this.originName = originName;
	}
}
