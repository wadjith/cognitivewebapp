package com.cognitivefarms.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cognitivefarms.webapp.domaine.entities.Raster;

/**
 * @author thierry WADJI
 *
 */
public interface RasterRepository extends JpaRepository<Raster, Long> {
	
	/**
	 * Find the soil PH at a location 
	 * 
	 * @param latitude
	 * @param longitude
	 * @return
	 */
	@Query(value = "SELECT ST_Value(rast,(ST_SetSRID(ST_Point( :lat, :lng),42106))) "
			+ "FROM  public.myraster WHERE st_intersects(rast, (ST_SetSRID(ST_Point(:lat, :lng),42106)))", 
			nativeQuery = true)
	public double getPh(@Param("lat") double latitude, @Param("lng") double longitude);
	
	

}
