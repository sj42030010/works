package com.xh.media.service;

import java.util.List;

import com.xh.media.dao.LogDayXhMediaProgramAnalysisMapper;
import com.xh.media.model.LogDayXhMediaProgramAnalysis;

public class LogDayXhMediaProgramAnalysisServiceImpl implements LogDayXhMediaProgramAnalysisService{
	private LogDayXhMediaProgramAnalysisMapper logDayXhMediaProgramAnalysisMapper;

	public LogDayXhMediaProgramAnalysisMapper getLogDayXhMediaProgramAnalysisMapper() {
		return logDayXhMediaProgramAnalysisMapper;
	}

	public void setLogDayXhMediaProgramAnalysisMapper(
			LogDayXhMediaProgramAnalysisMapper logDayXhMediaProgramAnalysisMapper) {
		this.logDayXhMediaProgramAnalysisMapper = logDayXhMediaProgramAnalysisMapper;
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return logDayXhMediaProgramAnalysisMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(LogDayXhMediaProgramAnalysis record) {
		return logDayXhMediaProgramAnalysisMapper.insert(record);
	}

	@Override
	public int insertSelective(LogDayXhMediaProgramAnalysis record) {
		return logDayXhMediaProgramAnalysisMapper.insertSelective(record);
	}

	@Override
	public LogDayXhMediaProgramAnalysis selectByPrimaryKey(Integer id) {
		return logDayXhMediaProgramAnalysisMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(LogDayXhMediaProgramAnalysis record) {
		return logDayXhMediaProgramAnalysisMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(LogDayXhMediaProgramAnalysis record) {
		return logDayXhMediaProgramAnalysisMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<LogDayXhMediaProgramAnalysis> getvideoanalysislist() {
		return logDayXhMediaProgramAnalysisMapper.getvideoanalysislist();
	}

	@Override
	public List<LogDayXhMediaProgramAnalysis> getVideoAnalysisListBySearch(
			LogDayXhMediaProgramAnalysis record) {
		System.out.println("sitecode="+record.getSiteCode()+"channelid"+record.getChannelId()+"globalid="+record.getGlobalId());
		return logDayXhMediaProgramAnalysisMapper.getVideoAnalysisListBySearch(record);
	}

	@Override
	public List<LogDayXhMediaProgramAnalysis> getProgramanalysislist() {
		return logDayXhMediaProgramAnalysisMapper.getProgramanalysislist();
	}

	@Override
	public List<LogDayXhMediaProgramAnalysis> getProgramAnalysisListBySearch(
			LogDayXhMediaProgramAnalysis record) {
		return logDayXhMediaProgramAnalysisMapper.getProgramAnalysisListBySearch(record);
	}

//	@Override
//	public List<LogDayXhMediaProgramAnalysis> getNativeVideoAnalysisList(LogDayXhMediaProgramAnalysis record) {
//		return logDayXhMediaProgramAnalysisMapper.getNativeVideoAnalysisList(record);
//	}
//
//	@Override
//	public List<LogDayXhMediaProgramAnalysis> getRecommendVideoAnalysisList(LogDayXhMediaProgramAnalysis record) {
//		return logDayXhMediaProgramAnalysisMapper.getRecommendVideoAnalysisList(record);
//	}

	@Override
	public List<LogDayXhMediaProgramAnalysis> getVideoAnalysisListBySearchOneDay(
			LogDayXhMediaProgramAnalysis record) {
		return logDayXhMediaProgramAnalysisMapper.getVideoAnalysisListBySearchOneDay(record);
	}

	@Override
	public List<LogDayXhMediaProgramAnalysis> getNativeProgramAnalysisList(LogDayXhMediaProgramAnalysis record) {
		return logDayXhMediaProgramAnalysisMapper.getNativeProgramAnalysisList(record);
	}

	@Override
	public List<LogDayXhMediaProgramAnalysis> getRecommendProgramAnalysisList(LogDayXhMediaProgramAnalysis record) {
		
		return logDayXhMediaProgramAnalysisMapper.getRecommendProgramAnalysisList(record);
	}

	@Override
	public List<LogDayXhMediaProgramAnalysis> getProgramAnalysisListBySearchOneDay(
			LogDayXhMediaProgramAnalysis record) {
		
		return logDayXhMediaProgramAnalysisMapper.getProgramAnalysisListBySearchOneDay(record);
	}

	@Override
	public List<LogDayXhMediaProgramAnalysis> getVideoDetail(
			LogDayXhMediaProgramAnalysis record) {
		
		return logDayXhMediaProgramAnalysisMapper.getVideoDetail(record);
	}

	@Override
	public List<LogDayXhMediaProgramAnalysis> getProgramDetail(
			LogDayXhMediaProgramAnalysis record) {
		
		return logDayXhMediaProgramAnalysisMapper.getProgramDetail(record);
	}

	@Override
	public List<LogDayXhMediaProgramAnalysis> getVideoDetailBySearch(
			LogDayXhMediaProgramAnalysis record) {
		
		return logDayXhMediaProgramAnalysisMapper.getVideoDetailBySearch(record);
	}

	@Override
	public List<LogDayXhMediaProgramAnalysis> getVideoDetailBySearchOneDay(
			LogDayXhMediaProgramAnalysis record) {
		
		return logDayXhMediaProgramAnalysisMapper.getVideoDetailBySearchOneDay(record);
	}

	@Override
	public List<LogDayXhMediaProgramAnalysis> getProgramDetailBySearch(
			LogDayXhMediaProgramAnalysis record) {
		
		return logDayXhMediaProgramAnalysisMapper.getProgramDetailBySearch(record);
	}

	@Override
	public List<LogDayXhMediaProgramAnalysis> getProgramDetailBySearchOneDay(
			LogDayXhMediaProgramAnalysis record) {
		
		return logDayXhMediaProgramAnalysisMapper.getProgramDetailBySearchOneDay(record);
	}

	@Override
	public List<LogDayXhMediaProgramAnalysis> getNativeVideoDetail(
			LogDayXhMediaProgramAnalysis record) {
		
		return logDayXhMediaProgramAnalysisMapper.getNativeVideoDetail(record);
	}

	@Override
	public List<LogDayXhMediaProgramAnalysis> getRecommendVideoDetail(
			LogDayXhMediaProgramAnalysis record) {
		
		return logDayXhMediaProgramAnalysisMapper.getRecommendVideoDetail(record);
	}

	@Override
	public List<LogDayXhMediaProgramAnalysis> getNativeProgramDetail(
			LogDayXhMediaProgramAnalysis record) {
		
		return logDayXhMediaProgramAnalysisMapper.getNativeProgramDetail(record);
	}

	@Override
	public List<LogDayXhMediaProgramAnalysis> getRecommendProgramDetail(
			LogDayXhMediaProgramAnalysis record) {
		
		return logDayXhMediaProgramAnalysisMapper.getRecommendProgramDetail(record);
	}

	@Override
	public List<LogDayXhMediaProgramAnalysis> getVideoAnalysisListForCharts(LogDayXhMediaProgramAnalysis record) {
		
		return logDayXhMediaProgramAnalysisMapper.getVideoAnalysisListForCharts(record);
	}

	@Override
	public List<LogDayXhMediaProgramAnalysis> getVideoAnalysisListBySearchForCharts(
			LogDayXhMediaProgramAnalysis record) {
		
		return logDayXhMediaProgramAnalysisMapper.getVideoAnalysisListBySearchForCharts(record);
	}

	@Override
	public List<LogDayXhMediaProgramAnalysis> getVideoAnalysisListBySearchOneDayForCharts(
			LogDayXhMediaProgramAnalysis record) {
		
		return logDayXhMediaProgramAnalysisMapper.getVideoAnalysisListBySearchOneDayForCharts(record);
	}

	@Override
	public List<LogDayXhMediaProgramAnalysis> getProgramAnalysisListForCharts(LogDayXhMediaProgramAnalysis record) {
		
		return logDayXhMediaProgramAnalysisMapper.getProgramAnalysisListForCharts(record);
	}

	@Override
	public List<LogDayXhMediaProgramAnalysis> getProgramAnalysisListBySearchForCharts(
			LogDayXhMediaProgramAnalysis record) {
		
		return logDayXhMediaProgramAnalysisMapper.getProgramAnalysisListBySearchForCharts(record);
	}

	@Override
	public List<LogDayXhMediaProgramAnalysis> getProgramAnalysisListBySearchOneDayForCharts(
			LogDayXhMediaProgramAnalysis record) {
		
		return logDayXhMediaProgramAnalysisMapper.getProgramAnalysisListBySearchOneDayForCharts(record);
	}

	@Override
	public List<LogDayXhMediaProgramAnalysis> getVideoDetailForChart(
			LogDayXhMediaProgramAnalysis record) {
		
		return logDayXhMediaProgramAnalysisMapper.getVideoDetailForChart(record);
	}

	@Override
	public List<LogDayXhMediaProgramAnalysis> getProgramDetailForChart(
			LogDayXhMediaProgramAnalysis record) {
		
		return logDayXhMediaProgramAnalysisMapper.getProgramDetailForChart(record);
	}

	@Override
	public List<LogDayXhMediaProgramAnalysis> getVideoDetailChartBySearch(
			LogDayXhMediaProgramAnalysis record) {
		
		return logDayXhMediaProgramAnalysisMapper.getVideoDetailChartBySearch(record);
	}

	@Override
	public List<LogDayXhMediaProgramAnalysis> getVideoDetailChartBySearchOneDay(
			LogDayXhMediaProgramAnalysis record) {
		
		return logDayXhMediaProgramAnalysisMapper.getVideoDetailChartBySearchOneDay(record);
	}

	@Override
	public List<LogDayXhMediaProgramAnalysis> getProgramDetailChartBySearch(
			LogDayXhMediaProgramAnalysis record) {
		
		return logDayXhMediaProgramAnalysisMapper.getProgramDetailChartBySearch(record);
	}

	@Override
	public List<LogDayXhMediaProgramAnalysis> getProgramDetailChartBySearchOneDay(
			LogDayXhMediaProgramAnalysis record) {
		
		return logDayXhMediaProgramAnalysisMapper.getProgramDetailChartBySearchOneDay(record);
	}

	@Override
	public List<LogDayXhMediaProgramAnalysis> getProgramListForChartPie(LogDayXhMediaProgramAnalysis record) {
		return logDayXhMediaProgramAnalysisMapper.getProgramListForChartPie(record);
	}

	@Override
	public List<LogDayXhMediaProgramAnalysis> getProgramListBySearchForChartPie(
			LogDayXhMediaProgramAnalysis record) {
		return logDayXhMediaProgramAnalysisMapper.getProgramListBySearchForChartPie(record);
	}

	@Override
	public List<LogDayXhMediaProgramAnalysis> getProgramDetailForChartPie(
			LogDayXhMediaProgramAnalysis record) {
		return logDayXhMediaProgramAnalysisMapper.getProgramDetailForChartPie(record);
	}

	@Override
	public List<LogDayXhMediaProgramAnalysis> getProgramDetailCharPietBySearch(
			LogDayXhMediaProgramAnalysis record) {
		return logDayXhMediaProgramAnalysisMapper.getProgramDetailCharPietBySearch(record);
	}

	@Override
	public List<LogDayXhMediaProgramAnalysis> getSummaryVideoAnalysisList(
			LogDayXhMediaProgramAnalysis record) {
		return logDayXhMediaProgramAnalysisMapper.getSummaryVideoAnalysisList(record);
	}

	@Override
	public List<LogDayXhMediaProgramAnalysis> getSummaryProgramAnalysisList(
			LogDayXhMediaProgramAnalysis record) {
		return logDayXhMediaProgramAnalysisMapper.getSummaryProgramAnalysisList(record);
	}

	@Override
	public List<LogDayXhMediaProgramAnalysis> getSummaryVideoDetail(
			LogDayXhMediaProgramAnalysis record) {
		return logDayXhMediaProgramAnalysisMapper.getSummaryVideoDetail(record);
	}

	@Override
	public List<LogDayXhMediaProgramAnalysis> getSummaryProgramDetail(
			LogDayXhMediaProgramAnalysis record) {
		return logDayXhMediaProgramAnalysisMapper.getSummaryProgramDetail(record);
	}

	@Override
	public List<LogDayXhMediaProgramAnalysis> selectVideoDataSum() {
		return logDayXhMediaProgramAnalysisMapper.selectVideoDataSum();
	}

	@Override
	public List<LogDayXhMediaProgramAnalysis> selectProgramDataSum() {
		return logDayXhMediaProgramAnalysisMapper.selectProgramDataSum();
	}
}
