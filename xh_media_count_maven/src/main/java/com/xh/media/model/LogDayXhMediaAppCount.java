package com.xh.media.model;

import java.util.Date;
import java.util.List;

public class LogDayXhMediaAppCount {
    private Integer id;

    private String date;

    private String siteCode;

    private String originId;

    private Integer startPv;

    private Integer startUv;

    private Integer newUser;

    private Integer local;

    private Integer nonLocal;

    private Integer commentNumber;

    private Integer collectionNumber;

    private Integer shareNumber;

    private Integer ip;

    private Date createTime;
    
    private String startTime;
    
    private String endTime;
    
    private String siteName;
    
    private String originName;
    
    private List sitcodList;
    
    private List orderList;

    private List originList;
    
    public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

	public List getOriginList() {
		return originList;
	}

	public void setOriginList(List originList) {
		this.originList = originList;
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

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode == null ? null : siteCode.trim();
    }

    public String getOriginId() {
        return originId;
    }

    public void setOriginId(String originId) {
        this.originId = originId == null ? null : originId.trim();
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

    public Integer getNewUser() {
        return newUser;
    }

    public void setNewUser(Integer newUser) {
        this.newUser = newUser;
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

    public Integer getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(Integer commentNumber) {
        this.commentNumber = commentNumber;
    }

    public Integer getCollectionNumber() {
        return collectionNumber;
    }

    public void setCollectionNumber(Integer collectionNumber) {
        this.collectionNumber = collectionNumber;
    }

    public Integer getShareNumber() {
        return shareNumber;
    }

    public void setShareNumber(Integer shareNumber) {
        this.shareNumber = shareNumber;
    }

    public Integer getIp() {
        return ip;
    }

    public void setIp(Integer ip) {
        this.ip = ip;
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

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
}