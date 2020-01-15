package com.cognitivefarms.webapp.domaine.qto;


public class CropatPosition {
	
	private String crop;
	private String adm2;
	private double km;
	
	public CropatPosition(String crop, String adm2, double km) {
		super();
		this.crop = crop;
		this.adm2 = adm2;
		this.km = km;
	}

	public String getCrop() {
		return crop;
	}

	public String getAdm2() {
		return adm2;
	}

	public double getKm() {
		return km;
	}
	
	
	
	
	
	

}
