package com.cognitivefarms.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import weka.core.Instances;
import weka.experiment.InstanceQuery;

/**
 * This class is used to create the training dataset and testset
 * 
 * @author thierry WADJI
 *
 */
@Component
public class DataSetFactory {
	
	private final Instances trainingData;
	private final String dbUrl; 
	private final String username;
	private final String password;
	private final String trainSetQuery;
	private final String testSetQuery;

	@Autowired
	public DataSetFactory(@Value("${spring.datasource.url}") String dbUrl, 
							@Value("${spring.datasource.username}") String username,
							@Value("${spring.datasource.password}") String password, 
							@Value("${spring.queries.trainset}") String trainQuery,
							@Value("${spring.queries.testset}") String testQuery) throws Exception {
		super();
		this.dbUrl = dbUrl;
		this.username = username;
		this.password = password;
		this.trainSetQuery = trainQuery;
		this.testSetQuery = testQuery;
		
		System.out.println("Trainset Query = "+ trainSetQuery + " Testset Query = "+testSetQuery);
		
		//Create an instance of Trainset data from SQL Postgres
		InstanceQuery query = new InstanceQuery();
        query.setDatabaseURL(dbUrl);
        query.setUsername(username);
        query.setPassword(password);
        //query.setQuery("SELECT crop, weather, ph, climate, predictor FROM trainset order by crop asc, predictor asc");
        query.setQuery(trainSetQuery);
        this.trainingData = query.retrieveInstances();
	}

	public Instances getTrainingData() {
		return trainingData;
	}

		
	/**
	 * 
	 * Create the testset object
	 * 
	 * @param position. 
	 * @return
	 * @throws Exception
	 */
	public Instances makeTestSet(String point) throws Exception {
		
		//Testset data from SQL Postgres
        InstanceQuery Test = new InstanceQuery();
        Test.setDatabaseURL(dbUrl);
        Test.setUsername(username);
        Test.setPassword(password);
        /*String query = "SELECT crop, weather, ph, climate, userid, predictor from testset"
        			 + " where point like '" + point + "' order by crop asc"; */
        String query = String.format(testSetQuery, point);
        Test.setQuery(query); // Read
                                                                            // table
        return Test.retrieveInstances();
		
	}
	
}
