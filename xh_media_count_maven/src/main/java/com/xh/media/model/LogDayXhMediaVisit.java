package com.xh.media.model;

import java.util.Date;
import java.util.List;

public class LogDayXhMediaVisit {
    private Integer id;

    private String date;

    private String originId;

    private String originName;

    private String channelId;

    private String channelName;

    private Integer pv;

    private Integer uv;

    private Integer ip;

    private Integer playTime;

    private Integer local;

    private Integer nonLocal;

    private String siteCode;

    private Date createTime;
    
    private String startTime;
    
    private String endTime;
    
    private List sitcodList;
    
    private List orderList;
    
    private String siteName;
    
    private String partId;

    private String partName;

    private Integer startPv;

    private Integer startUv;

    public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }

    public String getOriginId() {
        return originId;
    }

    public void setOriginId(String originId) {
        this.originId = originId == null ? null : originId.trim();
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName == null ? null : originName.trim();
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId == null ? null : channelId.trim();
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName == null ? null : channelName.trim();
    }

    public Integer getPv() {
        return pv;
    }

    public void setPv(Integer pv) {
        this.pv = pv;
    }

    public Integer getUv() {
        return uv;
    }

    public void setUv(Integer uv) {
        this.uv = uv;
    }

    public Integer getIp() {
        return ip;
    }

    public void setIp(Integer ip) {
        this.ip = ip;
    }

    public Integer getPlayTime() {
        return playTime;
    }

    public void setPlayTime(Integer playTime) {
        this.playTime = playTime;
    }

    public Integer getLocal() {
        return local;
    }

    public void setLocal(Integer local) {
        this.local = local;
    }

    public Integer getNonLocal() {
        return nonLocal;
    }

    public void setNonLocal(Integer nonLocal) {
        this.nonLocal = nonLocal;
    }

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode == null ? null : siteCode.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public List getSitcodList() {
		return sitcodList;
	}

	public void setSitcodList(List sitcodList) {
		this.sitcodList = sitcodList;
	}

	public List getOrderList() {
		return orderList;
	}

	public void setOrderList(List orderList) {
		this.orderList = orderList;
	}

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

	public Integer getStartPv() {
		return startPv;
	}

	public void setStartPv(Integer startPv) {
		this.startPv = startPv;
	}

	public Integer getStartUv() {
		return startUv;
	}

	public void setStartUv(Integer startUv) {
		this.startUv = startUv;
	}

}