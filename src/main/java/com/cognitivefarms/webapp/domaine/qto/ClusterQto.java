package com.cognitivefarms.webapp.domaine.qto;

public class ClusterQto {
	
	private String subNatUnit;
	private String cultures;
	private double km;
	
	public ClusterQto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ClusterQto(String subNatUnit, String cultures, double km) {
		super();
		this.subNatUnit = subNatUnit;
		this.cultures = cultures;
		this.km = km;
	}
	public String getSubNatUnit() {
		return subNatUnit;
	}
	public void setSubNatUnit(String subNatUnit) {
		this.subNatUnit = subNatUnit;
	}
	public String getCultures() {
		return cultures;
	}
	public void setCultures(String cultures) {
		this.cultures = cultures;
	}
	public double getKm() {
		return km;
	}
	public void setKm(double km) {
		this.km = km;
	} 
	
	

}
