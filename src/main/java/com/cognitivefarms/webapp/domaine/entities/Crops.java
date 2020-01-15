package com.cognitivefarms.webapp.domaine.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author thierry WADJI
 *
 */

@Entity
@Table(name = "crops")
@NamedQuery(name = "Crops.findCropsByGroupList", 
			query = "SELECT c FROM Crops c WHERE c.groupCode IN :groups")
@NamedQuery(name = "Crops.getCropPropertyFromGroupList", 
			query = "SELECT c.crop FROM Crops c WHERE c.groupCode IN :groups")
public class Crops {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id") 
	private Long id;
	
	@Column(name = "group_code") 
	private String groupCode;
	
	@Column(name = "group_label") 
	private String groupLabel;
	
	@Column(name = "crop") 
	private String crop;

	public Crops() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Crops(String groupCode, String groupLabel, String crop) {
		super();
		this.groupCode = groupCode;
		this.groupLabel = groupLabel;
		this.crop = crop;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getGroupLabel() {
		return groupLabel;
	}

	public void setGroupLabel(String groupLabel) {
		this.groupLabel = groupLabel;
	}

	public String getCrop() {
		return crop;
	}

	public void setCrop(String crop) {
		this.crop = crop;
	}
	
	
	
	

}
