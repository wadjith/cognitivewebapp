package com.cognitivefarms.webapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognitivefarms.webapp.domaine.entities.Farmer;
import com.cognitivefarms.webapp.domaine.entities.TestSet;

/**
 * @author thierry WADJI
 *
 */
public interface TestSetRepository extends JpaRepository<TestSet, Long> {
	
	/**
	 * @param farmer
	 * @return
	 */
	List<TestSet> findByFarmer(Farmer farmer);

}
