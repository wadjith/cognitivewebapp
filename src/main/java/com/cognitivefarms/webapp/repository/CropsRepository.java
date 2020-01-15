package com.cognitivefarms.webapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cognitivefarms.webapp.domaine.entities.Crops;

public interface CropsRepository extends JpaRepository<Crops, Long> {
	
	/**
	 * Find crops belonging to a group by the group code
	 * 
	 * @param groupCode
	 * @return
	 */
	public List<Crops> findByGroupCode(String groupCode);
	
	
	/**
	 * Find crops belonging to a list of group codes
	 * 
	 * @param groupCodes List of group code
	 * @return
	 */
	@Query(name = "Crops.findCropsByGroupList")
	public List<Crops> findCropByGroupList(@Param("groups") List<String> groupCodes);
	
	/**
	 * Get crop property of Crops belonging to a list of group codes
	 * 
	 * @param groupCodes List of group code
	 * @return
	 */
	@Query(name = "Crops.getCropPropertyFromGroupList")
	public List<String> getCropPropertyFromGroupList(@Param("groups") List<String> groupCodes);

}
