package com.cognitivefarms.webapp.domaine.dto;

/**
 * Contain information about climate and weather at a position
 * 
 * @author thierry WADJI
 *
 */
public class AezClusterDto {
	
	public String subNatUnit;
	public String climate;
	public String weather;
	
	public AezClusterDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AezClusterDto(String subNatUnit, String climate, String weather) {
		super();
		this.subNatUnit = subNatUnit;
		this.climate = climate;
		this.weather = weather;
	}

	public String getSubNatUnit() {
		return subNatUnit;
	}

	public void setSubNatUnit(String subNatUnit) {
		this.subNatUnit = subNatUnit;
	}

	public String getClimate() {
		return climate;
	}

	public void setClimate(String climate) {
		this.climate = climate;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}
	

}
