package com.xh.media.bean;

import java.util.ArrayList;
import java.util.List;

public class Series {
	private String legend;
	private String type;
	private ArrayList<Integer> data;
	
	public Series(String legend,String type,ArrayList<Integer> data){
		this.legend = legend;
		this.type = type;
		this.data = data;
	}
//	public String getLegend() {
//		return legend;
//	}
//	public void setLegend(String legend) {
//		this.legend = legend;
//	}
//	public String getType() {
//		return type;
//	}
//	public void setType(String type) {
//		this.type = type;
//	}
//	public ArrayList<Integer> getData() {
//		return data;
//	}
//	public void setData(ArrayList<Integer> data) {
//		this.data = data;
//	}
	
}
