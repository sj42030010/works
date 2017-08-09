package com.xh.media.bean;

import java.io.Serializable;
import java.util.List;

public class ChannelBean implements Serializable{
	private String channelId;
	private String channelName;
	private String channelPath;
	private String siteCode;
	private List<ProgramBean> programList;
	
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getChannelPath() {
		return channelPath;
	}
	public void setChannelPath(String channelPath) {
		this.channelPath = channelPath;
	}
	public String getSiteCode() {
		return siteCode;
	}
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}
	public List<ProgramBean> getProgramList() {
		return programList;
	}
	public void setProgramList(List<ProgramBean> programList) {
		this.programList = programList;
	}
}
