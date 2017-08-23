package com.xh.media.service;

import java.util.List;

import com.xh.media.model.LogDayXhMediaProgramAnalysis;


public interface LogDayXhMediaProgramAnalysisService {
	public int deleteByPrimaryKey(Integer id);
	
	public int insert(LogDayXhMediaProgramAnalysis record);

	public int insertSelective(LogDayXhMediaProgramAnalysis record);

	public LogDayXhMediaProgramAnalysis selectByPrimaryKey(Integer id);

	public int updateByPrimaryKeySelective(LogDayXhMediaProgramAnalysis record);

	public int updateByPrimaryKey(LogDayXhMediaProgramAnalysis record);
    
	public List<LogDayXhMediaProgramAnalysis> getvideoanalysislist();

	public List<LogDayXhMediaProgramAnalysis> getVideoAnalysisListBySearch(LogDayXhMediaProgramAnalysis record);
  
	public List<LogDayXhMediaProgramAnalysis> getProgramanalysislist();
  
	public List<LogDayXhMediaProgramAnalysis> getProgramAnalysisListBySearch(LogDayXhMediaProgramAnalysis record);
  
//  List<LogDayXhMediaProgramAnalysis> getNativeVideoAnalysisList(LogDayXhMediaProgramAnalysis record);
//  
//  List<LogDayXhMediaProgramAnalysis> getRecommendVideoAnalysisList(LogDayXhMediaProgramAnalysis record);
  
	public List<LogDayXhMediaProgramAnalysis> getVideoAnalysisListBySearchOneDay(LogDayXhMediaProgramAnalysis record);
  
	public List<LogDayXhMediaProgramAnalysis> getNativeProgramAnalysisList(LogDayXhMediaProgramAnalysis record);
  
	public List<LogDayXhMediaProgramAnalysis> getRecommendProgramAnalysisList(LogDayXhMediaProgramAnalysis record);
  
	public List<LogDayXhMediaProgramAnalysis> getProgramAnalysisListBySearchOneDay(LogDayXhMediaProgramAnalysis record);
  
	public List<LogDayXhMediaProgramAnalysis> getVideoDetail(LogDayXhMediaProgramAnalysis record);
  
	public List<LogDayXhMediaProgramAnalysis> getProgramDetail(LogDayXhMediaProgramAnalysis record);
  
	public List<LogDayXhMediaProgramAnalysis> getVideoDetailBySearch(LogDayXhMediaProgramAnalysis record);
  
	public List<LogDayXhMediaProgramAnalysis> getVideoDetailBySearchOneDay(LogDayXhMediaProgramAnalysis record);
  
	public List<LogDayXhMediaProgramAnalysis> getProgramDetailBySearch(LogDayXhMediaProgramAnalysis record);
  
	public List<LogDayXhMediaProgramAnalysis> getProgramDetailBySearchOneDay(LogDayXhMediaProgramAnalysis record);
  
	public List<LogDayXhMediaProgramAnalysis> getNativeVideoDetail(LogDayXhMediaProgramAnalysis record);
  
	public List<LogDayXhMediaProgramAnalysis> getRecommendVideoDetail(LogDayXhMediaProgramAnalysis record);
  
	public List<LogDayXhMediaProgramAnalysis> getNativeProgramDetail(LogDayXhMediaProgramAnalysis record);
  
	public List<LogDayXhMediaProgramAnalysis> getRecommendProgramDetail(LogDayXhMediaProgramAnalysis record);
  
	public List<LogDayXhMediaProgramAnalysis> getVideoAnalysisListForCharts(LogDayXhMediaProgramAnalysis record);
  
	public List<LogDayXhMediaProgramAnalysis> getVideoAnalysisListBySearchForCharts(LogDayXhMediaProgramAnalysis record);
  
	public List<LogDayXhMediaProgramAnalysis> getVideoAnalysisListBySearchOneDayForCharts(LogDayXhMediaProgramAnalysis record);
  
	public List<LogDayXhMediaProgramAnalysis> getProgramAnalysisListForCharts(LogDayXhMediaProgramAnalysis record);
  
	public List<LogDayXhMediaProgramAnalysis> getProgramAnalysisListBySearchForCharts(LogDayXhMediaProgramAnalysis record);
  
	public List<LogDayXhMediaProgramAnalysis> getProgramAnalysisListBySearchOneDayForCharts(LogDayXhMediaProgramAnalysis record);
  
	public List<LogDayXhMediaProgramAnalysis> getVideoDetailForChart(LogDayXhMediaProgramAnalysis record);
  
	public List<LogDayXhMediaProgramAnalysis> getProgramDetailForChart(LogDayXhMediaProgramAnalysis record);
  
	public List<LogDayXhMediaProgramAnalysis> getVideoDetailChartBySearch(LogDayXhMediaProgramAnalysis record);
  
	public List<LogDayXhMediaProgramAnalysis> getVideoDetailChartBySearchOneDay(LogDayXhMediaProgramAnalysis record);
  
	public List<LogDayXhMediaProgramAnalysis> getProgramDetailChartBySearch(LogDayXhMediaProgramAnalysis record);
  
	public List<LogDayXhMediaProgramAnalysis> getProgramDetailChartBySearchOneDay(LogDayXhMediaProgramAnalysis record);
  
	public List<LogDayXhMediaProgramAnalysis> getProgramListForChartPie(LogDayXhMediaProgramAnalysis record);
  
	public List<LogDayXhMediaProgramAnalysis> getProgramListBySearchForChartPie(LogDayXhMediaProgramAnalysis record);
  
	public List<LogDayXhMediaProgramAnalysis> getProgramDetailForChartPie(LogDayXhMediaProgramAnalysis record);
  
	public List<LogDayXhMediaProgramAnalysis> getProgramDetailCharPietBySearch(LogDayXhMediaProgramAnalysis record);
  
	public List<LogDayXhMediaProgramAnalysis> getSummaryVideoAnalysisList(LogDayXhMediaProgramAnalysis record);
  
	public List<LogDayXhMediaProgramAnalysis>  getSummaryProgramAnalysisList(LogDayXhMediaProgramAnalysis record);
  
	public List<LogDayXhMediaProgramAnalysis> getSummaryVideoDetail(LogDayXhMediaProgramAnalysis record);
  
	public List<LogDayXhMediaProgramAnalysis> getSummaryProgramDetail(LogDayXhMediaProgramAnalysis record);
  
	public List<LogDayXhMediaProgramAnalysis> selectVideoDataSum();
  
	public List<LogDayXhMediaProgramAnalysis> selectProgramDataSum();
}