package com.cognitivefarms.webapp.domaine.dto;

/**
 * @author thierry WADJI
 *
 */
public class GpsPoint {
	
	private double lat;
	private double lng;
	
	public GpsPoint(double lat, double lng) {
		super();
		this.lat = lat;
		this.lng = lng;
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

	@Override
	public String toString() {
		return lat + "" + lng;
	}
	
	
	
	

}
