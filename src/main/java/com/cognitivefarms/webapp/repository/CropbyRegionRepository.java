package com.cognitivefarms.webapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cognitivefarms.webapp.domaine.entities.CropbyRegion;
import com.cognitivefarms.webapp.domaine.qto.CropatPosition;

/**
 * @author thierry WADJI
 *
 */
public interface CropbyRegionRepository extends JpaRepository<CropbyRegion, Long> {
	
		
	/**
	 * @param point. It is a string like POINT(lat lng)
	 * @return
	 */
	@Query(name = "CropbyRegion.findCropbyRegion", nativeQuery = true)
	public List<CropatPosition> listCropAtPosition(@Param("point") String point);

}
