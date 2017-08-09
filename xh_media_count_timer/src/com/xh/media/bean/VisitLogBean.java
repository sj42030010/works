package com.xh.media.bean;

public class VisitLogBean {
	private String date;
	private String hour;
	private String minute;
	private String originId;
	private String originName;
	private String channelId;
	private String channelName;
	private String partId;
	private String partName;
	private int startPv;
	private int startUv;
	private int pv;
	private int uv;
	private int ip;
	private int playTime;
	private int local;
	private int nonLocal;
	private String siteCode;
	
	public String getPartId() {
		return partId;
	}
	public void setPartId(String partId) {
		this.partId = partId;
	}
	public String getPartName() {
		return partName;
	}
	public void setPartName(String partName) {
		this.partName = partName;
	}
	public int getStartPv() {
		return startPv;
	}
	public void setStartPv(int startPv) {
		this.startPv = startPv;
	}
	public int getStartUv() {
		return startUv;
	}
	public void setStartUv(int startUv) {
		this.startUv = startUv;
	}
	public String getMinute() {
		return minute;
	}
	public void setMinute(String minute) {
		this.minute = minute;
	}
	public String getSiteCode() {
		return siteCode;
	}
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
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
	public int getPv() {
		return pv;
	}
	public void setPv(int pv) {
		this.pv = pv;
	}
	public int getUv() {
		return uv;
	}
	public void setUv(int uv) {
		this.uv = uv;
	}
	public int getIp() {
		return ip;
	}
	public void setIp(int ip) {
		this.ip = ip;
	}
	public int getPlayTime() {
		return playTime;
	}
	public void setPlayTime(int playTime) {
		this.playTime = playTime;
	}
	public int getLocal() {
		return local;
	}
	public void setLocal(int local) {
		this.local = local;
	}
	public int getNonLocal() {
		return nonLocal;
	}
	public void setNonLocal(int nonLocal) {
		this.nonLocal = nonLocal;
	}
}
