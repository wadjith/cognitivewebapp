package com.cognitivefarms.webapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cognitivefarms.webapp.domaine.entities.AezCluster;
import com.cognitivefarms.webapp.domaine.qto.AezClusterQto;

/**
 * Interface JPA repository for AezCluster entity
 * 
 * @author thierry WADJI
 *
 */
public interface AezClusterRepository extends JpaRepository<AezCluster, Long> {
	
	/**
	 * @param position. It is a string like POINT(lat lng). lat is the latitude and lng is longitude
	 * @return
	 */
	@Query(name = "AezCluster.findAtPosition", nativeQuery = true)
	public List<AezClusterQto> findAtPosition(@Param("point") String point);

}
