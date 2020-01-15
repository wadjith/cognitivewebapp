package com.cognitivefarms.webapp.domaine.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author thierry WADJI
 *
 */

@Entity
@Table(name = "myraster")
public class Raster {
	
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name = "rid") 
	  private Long id;
	  
	  @Column(name = "rast", nullable = false) 
	  private String rast;
	  
	  @Column(name = "filename", nullable = false) 
	  private String filename;

	public Raster() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Raster(String rast, String filename) {
		super();
		this.rast = rast;
		this.filename = filename;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRast() {
		return rast;
	}

	public void setRast(String rast) {
		this.rast = rast;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	  
	  
	 

}
