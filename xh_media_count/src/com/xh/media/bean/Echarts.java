package com.xh.media.bean;

import java.util.List;

public class Echarts {
	private List<String> legend;
	private List<String> axis;
	private List<Series> series;
	
	public Echarts(List<String> legend, List<String> axis, List<Series> series){
		this.legend =legend;
		this.axis = axis;
		this.series = series;
	}
}
