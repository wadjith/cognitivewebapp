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

import com.cognitivefarms.webapp.domaine.qto.ClusterQto;

/**
 * @author thierry WADJI
 *
 */

@Entity
@Table(name = "clusters")
@SqlResultSetMapping(name="clusterAtPosition", 
classes = @ConstructorResult(
		 	targetClass = ClusterQto.class,
		 	columns = {
		 			@ColumnResult(name = "sub_nat_unit"),
		 			@ColumnResult(name = "cultures"),
		 			@ColumnResult(name = "km")
		 	}))
@NamedNativeQuery(name = "Cluster.findAtPosition", 
				  query = "SELECT sub_nat_unit,cultures,\r\n" + 
				  		"       ST_Distance(\r\n" + 
				  		"           cl.geom,\r\n" + 
				  		"           ST_Transform(\r\n" + 
				  		"               ST_GeomFromText(\r\n" + 
				  		"                   :point,\r\n" + 
				  		"                   4326),\r\n" + 
				  		"               4326)) / 1000.0 as km\r\n" + 
				  		"FROM    public.clusters cl\r\n" + 
				  		"WHERE   ST_DWithin(\r\n" + 
				  		"           cl.geom,\r\n" + 
				  		"           ST_Transform(\r\n" + 
				  		"               ST_GeomFromText(\r\n" + 
				  		"                   :point,\r\n" + 
				  		"                   4326),\r\n" + 
				  		"               4326),\r\n" + 
				  		"           5000)\r\n" + 
				  		"ORDER BY km\r\n" + 
				  		"LIMIT   10;", 
				  resultSetMapping = "clusterAtPosition")
public class Cluster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id") 
	private Long id;
	
	@Column(name = "geom")
	private String geom;
	
	@Column(name = "id_1")
	private int id_1;
	
	@Column(name = "gridcode")
	private int gridCode;
	
	@Column(name = "sub_nat_unit")
	private String subNatUnit;
	
	@Column(name = "gridcode_1")
	private double gridCode1;
	
	@Column(name = "subnatun_1")
	private String subNatUn;
	
	@Column(name = "cultures")
	private String cultures;

	public Cluster() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cluster(String geom, int id_1, int gridCode, String subNatUnit, double gridCode1, String subNatUn,
			String cultures) {
		super();
		this.geom = geom;
		this.id_1 = id_1;
		this.gridCode = gridCode;
		this.subNatUnit = subNatUnit;
		this.gridCode1 = gridCode1;
		this.subNatUn = subNatUn;
		this.cultures = cultures;
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

	public int getId_1() {
		return id_1;
	}

	public void setId_1(int id_1) {
		this.id_1 = id_1;
	}

	public int getGridCode() {
		return gridCode;
	}

	public void setGridCode(int gridCode) {
		this.gridCode = gridCode;
	}

	public String getSubNatUnit() {
		return subNatUnit;
	}

	public void setSubNatUnit(String subNatUnit) {
		this.subNatUnit = subNatUnit;
	}

	public double getGridCode1() {
		return gridCode1;
	}

	public void setGridCode1(double gridCode1) {
		this.gridCode1 = gridCode1;
	}

	public String getSubNatUn() {
		return subNatUn;
	}

	public void setSubNatUn(String subNatUn) {
		this.subNatUn = subNatUn;
	}

	public String getCultures() {
		return cultures;
	}

	public void setCultures(String cultures) {
		this.cultures = cultures;
	}
	
	

}
