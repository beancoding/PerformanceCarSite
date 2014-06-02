package com.dmcliver.performancecars.models;

public class TreeModel {
	
	private String label;
	private String level;
	
	public TreeModel(String label, String level) {
		
		this.label = label;
		this.level = level;
	}

	public TreeModel() {}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
}
