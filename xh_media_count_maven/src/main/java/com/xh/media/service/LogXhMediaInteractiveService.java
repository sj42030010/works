package com.xh.media.service;

import java.util.List;

import com.xh.media.model.LogDayXhMediaInteractive;
import com.xh.media.model.LogXhMediaInteractive;

public interface LogXhMediaInteractiveService {
	public int deleteByPrimaryKey(Integer id);

	public int insert(LogXhMediaInteractive record);

	public int insertSelective(LogXhMediaInteractive record);

	public LogXhMediaInteractive selectByPrimaryKey(Integer id);

	public List<LogXhMediaInteractive> findInteractiveList();
	 
	public int updateByPrimaryKeySelective(LogXhMediaInteractive record);

	public int updateByPrimaryKey(LogXhMediaInteractive record);
	
	public  List<LogXhMediaInteractive> findSumInteractiveList();
	
	public  List<LogXhMediaInteractive> getInteractiveBySearch(LogXhMediaInteractive record);
	
	public List<LogXhMediaInteractive> getInteractiveBySearchOneDay(LogXhMediaInteractive record);
	
	public  List<LogXhMediaInteractive>  findSumInteractiveListForChart();
    
	public List<LogXhMediaInteractive> getInteractiveBySearchForChart(LogXhMediaInteractive record);
    
	public List<LogXhMediaInteractive> getInteractiveBySearchOneDayForChart(LogXhMediaInteractive record);

    public List<LogXhMediaInteractive> getInteractiveDetail(LogXhMediaInteractive record);
    
    public List<LogXhMediaInteractive>  getInteractiveChartDetail(LogXhMediaInteractive record);
}
