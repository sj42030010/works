package com.xh.media.service;

import java.util.List;

import com.xh.media.model.LogDayXhMediaInteractive;
import com.xh.media.model.LogDayXhMediaProgramAnalysis;
public interface LogDayXhMediaInteractiveService {
	public int deleteByPrimaryKey(Integer id);

	public int insert(LogDayXhMediaInteractive record);

	public int insertSelective(LogDayXhMediaInteractive record);

	public LogDayXhMediaInteractive selectByPrimaryKey(Integer id);

	public int updateByPrimaryKeySelective(LogDayXhMediaInteractive record);

	public int updateByPrimaryKey(LogDayXhMediaInteractive record);
    
	public List<LogDayXhMediaInteractive> findInteractiveList();
    
	public List<LogDayXhMediaInteractive> findSumInteractiveList(LogDayXhMediaInteractive record);
    
	public List<LogDayXhMediaInteractive> getInteractiveBySearch(LogDayXhMediaInteractive record);
    
	public List<LogDayXhMediaInteractive> getInteractiveBySearchOneDay(LogDayXhMediaInteractive record);
    
	public List<LogDayXhMediaInteractive>  findSumInteractiveListForChart(LogDayXhMediaInteractive record);
    
	public List<LogDayXhMediaInteractive> getInteractiveBySearchForChart(LogDayXhMediaInteractive record);
    
	public List<LogDayXhMediaInteractive> getInteractiveBySearchOneDayForChart(LogDayXhMediaInteractive record);
    
	public List<LogDayXhMediaInteractive> selectInteractiveDataSum();

}
