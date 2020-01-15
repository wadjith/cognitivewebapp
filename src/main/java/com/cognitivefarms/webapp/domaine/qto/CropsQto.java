package com.cognitivefarms.webapp.domaine.qto;

public class CropsQto {
	
	private String group;
	private String crop;
	
	public CropsQto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CropsQto(String group, String crop) {
		super();
		this.group = group;
		this.crop = crop;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getCrop() {
		return crop;
	}
	public void setCrop(String crop) {
		this.crop = crop;
	}
	
	

}
