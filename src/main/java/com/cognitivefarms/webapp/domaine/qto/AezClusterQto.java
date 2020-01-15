package com.cognitivefarms.webapp.domaine.qto;

/**
 * This class is a result from query of entity AezCluster
 * 
 * @author thierry WADJI
 *
 */
public class AezClusterQto {
	
	private String aezText;
	private String subNatUnit;
	private String fsName;
	private double lat;
	private double lng;
	private double km;
	
	public AezClusterQto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AezClusterQto(String aezText, String subNatUnit, String fsName, double lat, double lng, double km) {
		super();
		this.aezText = aezText;
		this.subNatUnit = subNatUnit;
		this.fsName = fsName;
		this.lat = lat;
		this.lng = lng;
		this.km = km;
	}

	public String getAezText() {
		return aezText;
	}

	public void setAezText(String aezText) {
		this.aezText = aezText;
	}

	public String getSubNatUnit() {
		return subNatUnit;
	}

	public void setSubNatUnit(String subNatUnit) {
		this.subNatUnit = subNatUnit;
	}

	public String getFsName() {
		return fsName;
	}

	public void setFsName(String fsName) {
		this.fsName = fsName;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public double getKm() {
		return km;
	}

	public void setKm(double km) {
		this.km = km;
	}
	
	

}
