package com.cognitivefarms.webapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognitivefarms.webapp.domaine.entities.Farmer;

/**
 * @author thierry WADJI
 *
 */
public interface FarmerRepository extends JpaRepository<Farmer, Long> {
	
	public Farmer findByTelephone(int phoneNumber);
	
	public Farmer findByEmail(String email);

}
