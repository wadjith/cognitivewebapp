/**
 * 
 */
package com.cognitivefarms.webapp.service;

import weka.core.Instances;

/**
 * Provides services for prediction through AI Algorithm
 * 
 * @author thierry WADJI
 *
 */
public interface IPredictionService {
	
	
	/**
	 * 
	 * Create an instance object of TestSet
	 * 
	 * @param position
	 * @return
	 * @throws Exception
	 */
	public Instances makeTestSet(String position) throws Exception;
	
	/**
	 * Used a particular algorithm to train the Artificial Intelligence (AI)
	 * @throws Exception
	 */
	public void train() throws Exception;
	
	/**
	 * Used to classify after train
	 * @throws Exception
	 */
	public void classify() throws Exception;

}
