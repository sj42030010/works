package com.xh.media.service;

import java.util.List;

import com.xh.media.model.LogXhMediaVisit;

public interface LogXhMediaVisitService {
	public int deleteByPrimaryKey(Integer id);

	public int insert(LogXhMediaVisit record);

	public int insertSelective(LogXhMediaVisit record);

	public LogXhMediaVisit selectByPrimaryKey(Integer id);
	

	public int updateByPrimaryKeySelective(LogXhMediaVisit record);

	public int updateByPrimaryKey(LogXhMediaVisit record);
	
	public List<LogXhMediaVisit> selectByLimit(Integer limit);
	
	public List<LogXhMediaVisit> getVisitList();

	public List<LogXhMediaVisit> getSumVisitList();
	
	public  List<LogXhMediaVisit> getVisitListBySearch(LogXhMediaVisit record);
	
	public  List<LogXhMediaVisit> getVisitListBySearchOneDay(LogXhMediaVisit record);
	
	public   List<LogXhMediaVisit> getSumVisitListForCharts();
    
	public List<LogXhMediaVisit> getVisitListBySearchForCharts(LogXhMediaVisit record);
    
	public List<LogXhMediaVisit> getVisitListBySearchOneDayForCharts(LogXhMediaVisit record);

    public List<LogXhMediaVisit> getVisitListDetail(LogXhMediaVisit record);
    
    public List<LogXhMediaVisit> selectDataSum();
}