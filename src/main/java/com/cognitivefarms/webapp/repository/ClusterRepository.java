package com.cognitivefarms.webapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cognitivefarms.webapp.domaine.entities.Cluster;
import com.cognitivefarms.webapp.domaine.qto.ClusterQto;

/**
 * @author thierry WADJI
 *
 */
public interface ClusterRepository extends JpaRepository<Cluster, Long> {
	
	/**
	 * @param position. It is a string like POINT(lat lng). lat is the latitude and lng is longitude
	 * @return
	 */
	@Query(name = "Cluster.findAtPosition", nativeQuery = true)
	public List<ClusterQto> findAtPosition(@Param("point") String point);

}
