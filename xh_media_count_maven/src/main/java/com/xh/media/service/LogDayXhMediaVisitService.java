package com.xh.media.service;

import java.util.List;

import com.xh.media.model.LogDayXhMediaVisit;
import com.xh.media.model.LogXhMediaVisit;

public interface LogDayXhMediaVisitService {
	public int deleteByPrimaryKey(Integer id);

	public int insert(LogDayXhMediaVisit record);

	public int insertSelective(LogDayXhMediaVisit record);

	public LogDayXhMediaVisit selectByPrimaryKey(Integer id);

	public int updateByPrimaryKeySelective(LogDayXhMediaVisit record);

	public int updateByPrimaryKey(LogDayXhMediaVisit record);
    
	public List<LogDayXhMediaVisit> selectByLimit(Integer limit);
    
	public List<LogDayXhMediaVisit> getSumVisitList(LogDayXhMediaVisit record);
    
	public List<LogDayXhMediaVisit> getVisitListBySearch(LogDayXhMediaVisit record);
    
	public List<LogDayXhMediaVisit> getSumVisitListForCharts(LogDayXhMediaVisit record);
    
	public List<LogDayXhMediaVisit> getVisitListBySearchForCharts(LogDayXhMediaVisit record);
    
	public List<LogDayXhMediaVisit> getVisitListBySearchOneDay(LogDayXhMediaVisit record);
}