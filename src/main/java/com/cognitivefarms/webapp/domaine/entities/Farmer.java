package com.cognitivefarms.webapp.domaine.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author thierry WADJI
 *
 */
@Entity
@Table(name = "farmer")
public class Farmer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "nom", length = 100, nullable = false)
	private String nom;
	
	@Column(name = "email", length = 50, nullable = true)
	private String email;
	
	@Column(name = "password", length = 50, nullable = false)
	private String password;
	
	@Column(name = "telephone", unique = true, nullable = false)
	private int telephone;
	
	@OneToMany(mappedBy = "farmer", fetch = FetchType.LAZY)
	private List<TestSet> testSets;
	
	public Farmer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Farmer(String nom, String email, String password, int telephone) {
		super();
		this.nom = nom;
		this.email = email;
		this.password = password;
		this.telephone = telephone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getTelephone() {
		return telephone;
	}

	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}

	public List<TestSet> getTestSets() {
		return testSets;
	}

	public void setTestSets(List<TestSet> testSets) {
		this.testSets = testSets;
	}
	
	
	

}
