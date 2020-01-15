package com.cognitivefarms.webapp.domaine.entities;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import com.cognitivefarms.webapp.domaine.qto.CropatPosition;

/**
 * @author thierry WADJI
 *
 */
@Entity
@Table(name = "cropcompilbyregion")
@SqlResultSetMapping(name="cropsByRegion", 
					 classes = @ConstructorResult(
							 	targetClass = CropatPosition.class,
							 	columns = {
							 			@ColumnResult(name = "Crop"),
							 			@ColumnResult(name = "ADM2_NAME_ALT"),
							 			@ColumnResult(name = "km")
							 	}))

@NamedNativeQuery(name = "CropbyRegion.findCropbyRegion",
				  query = "SELECT \"Crop\",\r\n" + 
				  		"       \"ADM2_NAME_ALT\",\r\n" + 
				  		"       ST_Distance(\r\n" + 
				  		"           b.geom,\r\n" + 
				  		"           ST_Transform(\r\n" + 
				  		"               ST_GeomFromText(\r\n" + 
				  		"                   :point,\r\n" + 
				  		"                   4326),\r\n" + 
				  		"               4326)) / 1000.0 as km\r\n" + 
				  		"FROM    public.\"cropcompilbyregion\" b\r\n" + 
				  		"WHERE   ST_DWithin(\r\n" + 
				  		"           b.geom,\r\n" + 
				  		"           ST_Transform(\r\n" + 
				  		"               ST_GeomFromText(\r\n" + 
				  		"                   :point,\r\n" + 
				  		"                   4326),\r\n" + 
				  		"               4326),\r\n" + 
				  		"           5000)\r\n" + 
				  		"ORDER BY km\r\n" + 
				  		"LIMIT   10;",
				  		resultSetMapping = "cropsByRegion")
public class CropbyRegion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id") 
	private Long id;
	
	@Column(name = "geom")
	private String geom;
	
	@Column(name = "CELL5M")
	private int cell;
	
	@Column(name = "ISO3")
	private String iso3;
	
	@Column(name = "ADM0_NAME")
	private String adm0;
	
	@Column(name = "ADM1_NAME_ALT")
	private String adm1;
	
	@Column(name = "ADM2_NAME_ALT")
	private String adm2;
	
	@Column(name = "x")
	private double x;
	
	@Column(name = "y")
	private double y;
	
	@Column(name = "volume")
	private int volume;
	
	@Column(name = "Crop")
	private String crop;
	
	public CropbyRegion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CropbyRegion(int cell, String iso3, String adm0, String adm1, String adm2, double x, double y, int volume,
			String crop) {
		super();
		this.cell = cell;
		this.iso3 = iso3;
		this.adm0 = adm0;
		this.adm1 = adm1;
		this.adm2 = adm2;
		this.x = x;
		this.y = y;
		this.volume = volume;
		this.crop = crop;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGeom() {
		return geom;
	}

	public void setGeom(String geom) {
		this.geom = geom;
	}

	public int getCell() {
		return cell;
	}

	public void setCell(int cell) {
		this.cell = cell;
	}

	public String getIso3() {
		return iso3;
	}

	public void setIso3(String iso3) {
		this.iso3 = iso3;
	}

	public String getAdm0() {
		return adm0;
	}

	public void setAdm0(String adm0) {
		this.adm0 = adm0;
	}

	public String getAdm1() {
		return adm1;
	}

	public void setAdm1(String adm1) {
		this.adm1 = adm1;
	}

	public String getAdm2() {
		return adm2;
	}

	public void setAdm2(String adm2) {
		this.adm2 = adm2;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public String getCrop() {
		return crop;
	}

	public void setCrop(String crop) {
		this.crop = crop;
	}
	
	

}
