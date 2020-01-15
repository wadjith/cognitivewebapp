package com.cognitivefarms.webapp.domaine.dto;

public class Classification {
	
	private String crop;
	private String id;
	private String prediction;
	
	public Classification() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Classification(String crop, String id, String prediction) {
		super();
		this.crop = crop;
		this.id = id;
		this.prediction = prediction;
	}

	public String getCrop() {
		return crop;
	}

	public void setCrop(String crop) {
		this.crop = crop;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPrediction() {
		return prediction;
	}

	public void setPrediction(String prediction) {
		this.prediction = prediction;
	}
	

}
