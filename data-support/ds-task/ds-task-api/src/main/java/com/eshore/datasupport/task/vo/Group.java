package com.eshore.datasupport.task.vo;

import java.util.List;

public class Group {
	private String zdname;//重复字段名
	private List<Integer> repeats;//重复元素下标集合
	public String getZdname() {
		return zdname;
	}
	public void setZdname(String zdname) {
		this.zdname = zdname;
	}
	public List<Integer> getRepeats() {
		return repeats;
	}
	public void setRepeats(List<Integer> repeats) {
		this.repeats = repeats;
	}

	
}
