package com.cognitivefarms.webapp.domaine.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author thierry WADJI
 *
 */
@Entity
@Table(name = "testset")
public class TestSet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "crop", length = 50, nullable = false)
	private String crop;
	
	@Column(name = "weather", length = 50, nullable = false)
	private String weather;
	
	@Column(name = "ph", length = 20, nullable = false)
	private String ph;
	
	@Column(name = "climate", length = 15, nullable = false)
	private String climate;
	
	@Column(name = "point", length = 20, nullable = false)
	private String point;
	
	@Column(name = "predictor", length = 15, nullable = true)
	private String predictor; 
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid")
	private Farmer farmer;

	public TestSet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TestSet(String crop, String weather, String ph, 
					String climate, String point, String predictor) {
		super();
		this.crop = crop;
		this.weather = weather;
		this.ph = ph;
		this.climate = climate;
		this.point = point;
		this.predictor = predictor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCrop() {
		return crop;
	}

	public void setCrop(String crop) {
		this.crop = crop;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getPh() {
		return ph;
	}

	public void setPh(String ph) {
		this.ph = ph;
	}

	public String getClimate() {
		return climate;
	}

	public void setClimate(String climate) {
		this.climate = climate;
	}
	
	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public String getPredictor() {
		return predictor;
	}

	public void setPredictor(String predictor) {
		this.predictor = predictor;
	}

	public Farmer getFarmer() {
		return farmer;
	}

	public void setFarmer(Farmer farmer) {
		this.farmer = farmer;
	}

	
	
	

}
