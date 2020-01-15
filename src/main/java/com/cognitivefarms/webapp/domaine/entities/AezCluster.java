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

import com.cognitivefarms.webapp.domaine.qto.AezClusterQto;

/**
 * @author thierry WADJI
 *
 */

@Entity
@Table(name = "aez_cluster")
@SqlResultSetMapping(name="aezAtPosition", 
classes = @ConstructorResult(
		 	targetClass = AezClusterQto.class,
		 	columns = {
		 			@ColumnResult(name = "aez_text"),
		 			@ColumnResult(name = "sub_nat_unit"),
		 			@ColumnResult(name = "fs_name"),
		 			@ColumnResult(name = "lat"),
		 			@ColumnResult(name = "lng"),
		 			@ColumnResult(name = "km")
		 	}))
@NamedNativeQuery(name = "AezCluster.findAtPosition", 
				  query = "SELECT aez_text,sub_nat_unit,fs_name,lat,lng,\r\n" + 
				  		"       ST_Distance(\r\n" + 
				  		"           az.geom,\r\n" + 
				  		"           ST_Transform(\r\n" + 
				  		"               ST_GeomFromText(\r\n" + 
				  		"                   :point,\r\n" + 
				  		"                   4326),\r\n" + 
				  		"               4326)) / 1000.0 as km\r\n" + 
				  		"FROM    public.aez_cluster az\r\n" + 
				  		"WHERE   ST_DWithin(\r\n" + 
				  		"           az.geom,\r\n" + 
				  		"           ST_Transform(\r\n" + 
				  		"               ST_GeomFromText(\r\n" + 
				  		"                   :point,\r\n" + 
				  		"                   4326),\r\n" + 
				  		"               4326),\r\n" + 
				  		"           5000)\r\n" + 
				  		"ORDER BY km\r\n" + 
				  		"LIMIT   10;", 
				  resultSetMapping = "aezAtPosition")
public class AezCluster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id") 
	private Long id;
	
	@Column(name = "geom")
	private String geom;
	
	@Column(name = "aez_text")
	private String aezText;
	
	@Column(name = "cell5m")
	private int cell5m;
	
	@Column(name = "iso3")
	private String iso3;
	
	@Column(name = "sub_nat_unit")
	private String subNatUnit;
	
	@Column(name = "aez5_clas")
	private String aez5Clas;
	
	@Column(name = "fs_name")
	private String fsName;
	
	@Column(name = "lat")
	private double lat;
	
	@Column(name = "lng")
	private double lng;
	
	@Column(name = "area_cooked")
	private double areaCooked;
	
	@Column(name = "area_total")
	private double areaTotal;
	
	@Column(name = "field_11")
	private String field11;

	public AezCluster() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AezCluster(String geom, String aezText, int cell5m, String iso3, String subNatUnit, String aez5Clas,
			String fsName, double lat, double lng, double areaCooked, double areaTotal, String field11) {
		super();
		this.geom = geom;
		this.aezText = aezText;
		this.cell5m = cell5m;
		this.iso3 = iso3;
		this.subNatUnit = subNatUnit;
		this.aez5Clas = aez5Clas;
		this.fsName = fsName;
		this.lat = lat;
		this.lng = lng;
		this.areaCooked = areaCooked;
		this.areaTotal = areaTotal;
		this.field11 = field11;
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

	public String getAezText() {
		return aezText;
	}

	public void setAezText(String aezText) {
		this.aezText = aezText;
	}

	public int getCell5m() {
		return cell5m;
	}

	public void setCell5m(int cell5m) {
		this.cell5m = cell5m;
	}

	public String getIso3() {
		return iso3;
	}

	public void setIso3(String iso3) {
		this.iso3 = iso3;
	}

	public String getSubNatUnit() {
		return subNatUnit;
	}

	public void setSubNatUnit(String subNatUnit) {
		this.subNatUnit = subNatUnit;
	}

	public String getAez5Clas() {
		return aez5Clas;
	}

	public void setAez5Clas(String aez5Clas) {
		this.aez5Clas = aez5Clas;
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

	public double getAreaCooked() {
		return areaCooked;
	}

	public void setAreaCooked(double areaCooked) {
		this.areaCooked = areaCooked;
	}

	public double getAreaTotal() {
		return areaTotal;
	}

	public void setAreaTotal(double areaTotal) {
		this.areaTotal = areaTotal;
	}

	public String getField11() {
		return field11;
	}

	public void setField11(String field11) {
		this.field11 = field11;
	}
	
	

}
