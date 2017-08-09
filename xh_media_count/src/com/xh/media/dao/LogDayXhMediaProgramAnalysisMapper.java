package com.xh.media.dao;

import java.util.List;

import com.xh.media.model.LogDayXhMediaProgramAnalysis;

public interface LogDayXhMediaProgramAnalysisMapper {
	    int deleteByPrimaryKey(Integer id);
	
	    int insert(LogDayXhMediaProgramAnalysis record);
	
	    int insertSelective(LogDayXhMediaProgramAnalysis record);
	
	    LogDayXhMediaProgramAnalysis selectByPrimaryKey(Integer id);
	
	    int updateByPrimaryKeySelective(LogDayXhMediaProgramAnalysis record);
	
	    int updateByPrimaryKey(LogDayXhMediaProgramAnalysis record);
	    
	    List<LogDayXhMediaProgramAnalysis> getvideoanalysislist();
    
	  List<LogDayXhMediaProgramAnalysis> getVideoAnalysisListBySearch(LogDayXhMediaProgramAnalysis record);
	  
	  List<LogDayXhMediaProgramAnalysis> getProgramanalysislist();
	  
	  List<LogDayXhMediaProgramAnalysis> getProgramAnalysisListBySearch(LogDayXhMediaProgramAnalysis record);
	  
//	  List<LogDayXhMediaProgramAnalysis> getNativeVideoAnalysisList(LogDayXhMediaProgramAnalysis record);
//	  
//	  List<LogDayXhMediaProgramAnalysis> getRecommendVideoAnalysisList(LogDayXhMediaProgramAnalysis record);
	  
	  List<LogDayXhMediaProgramAnalysis> getVideoAnalysisListBySearchOneDay(LogDayXhMediaProgramAnalysis record);
	  
	  List<LogDayXhMediaProgramAnalysis> getNativeProgramAnalysisList(LogDayXhMediaProgramAnalysis record);
	  
	  List<LogDayXhMediaProgramAnalysis> getRecommendProgramAnalysisList(LogDayXhMediaProgramAnalysis record);
	  
	  List<LogDayXhMediaProgramAnalysis> getProgramAnalysisListBySearchOneDay(LogDayXhMediaProgramAnalysis record);
	  
	  List<LogDayXhMediaProgramAnalysis> getVideoDetail(LogDayXhMediaProgramAnalysis record);
	  
	  List<LogDayXhMediaProgramAnalysis> getProgramDetail(LogDayXhMediaProgramAnalysis record);
	  
	  List<LogDayXhMediaProgramAnalysis> getVideoDetailBySearch(LogDayXhMediaProgramAnalysis record);
	  
	  List<LogDayXhMediaProgramAnalysis> getVideoDetailBySearchOneDay(LogDayXhMediaProgramAnalysis record);
	  
	  List<LogDayXhMediaProgramAnalysis> getProgramDetailBySearch(LogDayXhMediaProgramAnalysis record);
	  
	  List<LogDayXhMediaProgramAnalysis> getProgramDetailBySearchOneDay(LogDayXhMediaProgramAnalysis record);
	  
	  List<LogDayXhMediaProgramAnalysis> getNativeVideoDetail(LogDayXhMediaProgramAnalysis record);
	  
	  List<LogDayXhMediaProgramAnalysis> getRecommendVideoDetail(LogDayXhMediaProgramAnalysis record);
	  
	  List<LogDayXhMediaProgramAnalysis> getNativeProgramDetail(LogDayXhMediaProgramAnalysis record);
	  
	  List<LogDayXhMediaProgramAnalysis> getRecommendProgramDetail(LogDayXhMediaProgramAnalysis record);
	  
	  List<LogDayXhMediaProgramAnalysis> getVideoAnalysisListForCharts(LogDayXhMediaProgramAnalysis record);
	  
	  List<LogDayXhMediaProgramAnalysis> getVideoAnalysisListBySearchForCharts(LogDayXhMediaProgramAnalysis record);
	  
	  List<LogDayXhMediaProgramAnalysis> getVideoAnalysisListBySearchOneDayForCharts(LogDayXhMediaProgramAnalysis record);
	  
	  List<LogDayXhMediaProgramAnalysis> getProgramAnalysisListForCharts(LogDayXhMediaProgramAnalysis record);
	  
	  List<LogDayXhMediaProgramAnalysis> getProgramAnalysisListBySearchForCharts(LogDayXhMediaProgramAnalysis record);
	  
	  List<LogDayXhMediaProgramAnalysis> getProgramAnalysisListBySearchOneDayForCharts(LogDayXhMediaProgramAnalysis record);
	  
	  List<LogDayXhMediaProgramAnalysis> getVideoDetailForChart(LogDayXhMediaProgramAnalysis record);
	  
	  List<LogDayXhMediaProgramAnalysis> getProgramDetailForChart(LogDayXhMediaProgramAnalysis record);
	  
	  List<LogDayXhMediaProgramAnalysis> getVideoDetailChartBySearch(LogDayXhMediaProgramAnalysis record);
	  
	  List<LogDayXhMediaProgramAnalysis> getVideoDetailChartBySearchOneDay(LogDayXhMediaProgramAnalysis record);
	  
	  List<LogDayXhMediaProgramAnalysis> getProgramDetailChartBySearch(LogDayXhMediaProgramAnalysis record);
	  
	  List<LogDayXhMediaProgramAnalysis> getProgramDetailChartBySearchOneDay(LogDayXhMediaProgramAnalysis record);
	  
	  List<LogDayXhMediaProgramAnalysis> getProgramListForChartPie(LogDayXhMediaProgramAnalysis record);
	  
	  List<LogDayXhMediaProgramAnalysis> getProgramListBySearchForChartPie(LogDayXhMediaProgramAnalysis record);
	  
	  List<LogDayXhMediaProgramAnalysis> getProgramDetailForChartPie(LogDayXhMediaProgramAnalysis record);
	  
	  List<LogDayXhMediaProgramAnalysis> getProgramDetailCharPietBySearch(LogDayXhMediaProgramAnalysis record);
	  
	  List<LogDayXhMediaProgramAnalysis> getSummaryVideoAnalysisList(LogDayXhMediaProgramAnalysis record);
	  
	  List<LogDayXhMediaProgramAnalysis>  getSummaryProgramAnalysisList(LogDayXhMediaProgramAnalysis record);
	  
	  List<LogDayXhMediaProgramAnalysis> getSummaryVideoDetail(LogDayXhMediaProgramAnalysis record);

	  List<LogDayXhMediaProgramAnalysis> getSummaryProgramDetail(LogDayXhMediaProgramAnalysis record);
	  
	  List<LogDayXhMediaProgramAnalysis> selectVideoDataSum();
	  
	  List<LogDayXhMediaProgramAnalysis> selectProgramDataSum();
}