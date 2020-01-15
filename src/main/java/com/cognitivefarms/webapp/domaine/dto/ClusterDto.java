package com.cognitivefarms.webapp.domaine.dto;

import java.util.List;

public class ClusterDto {
	
	private String subNatUnit;
	private List<String> groupOfCultures;
	
	public ClusterDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ClusterDto(String subNatUnit, List<String> groupOfCultures) {
		super();
		this.subNatUnit = subNatUnit;
		this.groupOfCultures = groupOfCultures;
	}
	
	public String getSubNatUnit() {
		return subNatUnit;
	}
	public void setSubNatUnit(String subNatUnit) {
		this.subNatUnit = subNatUnit;
	}
	public List<String> getGroupOfCultures() {
		return groupOfCultures;
	}
	public void setGroupOfCultures(List<String> groupOfCultures) {
		this.groupOfCultures = groupOfCultures;
	}
	
	

}
