package com.cognitivefarms.webapp.service;

import java.util.List;
import java.util.Map;

import com.cognitivefarms.webapp.domaine.dto.AezClusterDto;
import com.cognitivefarms.webapp.domaine.dto.ClusterDto;
import com.cognitivefarms.webapp.domaine.entities.Crops;
import com.cognitivefarms.webapp.domaine.entities.Farmer;
import com.cognitivefarms.webapp.domaine.entities.TestSet;
import com.cognitivefarms.webapp.domaine.qto.AezClusterQto;
import com.cognitivefarms.webapp.domaine.qto.ClusterQto;

/**
 * Expose cognition services to public 
 * 
 * @author thierry WADJI
 *
 */
public interface ICognitiveService {
	
	/**
	 * Compute the ph of soil at a position lat, lng
	 * 
	 * @param lat
	 * @param lng
	 * @return a double wich is ph value
	 */
	public double getPH(double lat, double lng);
	
	/**
	 * The character matching a numerical value of PH 
	 * 
	 * @param ph
	 * @return
	 */
	public String getPH(double ph);
	
	/**
	 * Compute the weather and climate at a position
	 * 
	 * @param lat : latitude
	 * @param lng : longitude
	 * @return a Map with keys climate and weather
	 */
	public AezClusterQto getWeather(double lat, double lng);
	
	/**
	 * Get the best group of cultures or crops at a position
	 * 
	 * @param lat
	 * @param lng
	 * @return
	 */
	public ClusterQto getCluster(double lat, double lng);
	
	/**
	 * List of best culture or crops at a position
	 * 
	 * @param lat
	 * @param lng
	 * @return
	 */
	public List<Crops> getCrops(double lat, double lng);
	
	/**
	 * List of culture within a cluster or group
	 * 
	 * @param group : the group of culture
	 * @return a list of crops
	 */
	public List<Crops> getCrops(String group);
	
	
	/**
	 * List crops belonging to a collection of group
	 * 
	 * @param groupList
	 * @return
	 */
	public List<Crops> getCrops(List<String> groupList);
	
	/**
	 * List crop property of crops belonging to a collection of group
	 * 
	 * @param groupList
	 * @return
	 */
	public List<String> getCropPropertyofCrops(List<String> groupList);
	
	/**
	 * Persist a test set in database
	 * 
	 * @param test
	 * @return
	 */
	public boolean saveTestSet(TestSet test);
	
	/**
	 * Persist a list of test set in DB
	 * 
	 * @param tests
	 * @return
	 */
	public boolean saveAllTestSet(List<TestSet> tests);
	
	public Farmer saveFarmer(Farmer farmer);
	
	public Farmer searchFarmer(Long id);
	
	public Farmer searchByEmail(String email);
	
	public Farmer searchByTelephone(int phoneNumber);
	
	

}
