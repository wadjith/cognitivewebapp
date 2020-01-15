package com.cognitivefarms.webapp.service;



import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import weka.classifiers.AbstractClassifier;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.Logistic;
import weka.classifiers.functions.SMO;
import weka.classifiers.lazy.IBk;
import weka.classifiers.meta.AdaBoostM1;
import weka.classifiers.meta.CVParameterSelection;
import weka.classifiers.meta.LogitBoost;
import weka.classifiers.misc.InputMappedClassifier;
import weka.classifiers.rules.OneR;
import weka.classifiers.trees.DecisionStump;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;
import weka.classifiers.trees.LMT;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.experiment.InstanceQuery;
import weka.filters.Filter;
import weka.filters.supervised.attribute.NominalToBinary;
import weka.filters.unsupervised.attribute.Add;

import com.cognitivefarms.webapp.domaine.dto.Classification;
import com.cognitivefarms.webapp.utils.Processing;
import com.cognitivefarms.webapp.utils.wekatools.InstancesManipulation;
import com.cognitivefarms.webapp.utils.wekatools.MatchDataSets;
import com.cognitivefarms.webapp.utils.wekatools.NormalizeNumericFeatures;

/**
 * Predicting the survival probability of titanic passengers. 
 * The data set is provided by kaggle. 
 * Try different classification methods on the data. 
 * @author carrillo
 *
 */
public class Titanic
{ 
	private AbstractClassifier classifier;
	
	private InputMappedClassifier imc; 
	private Instances trainingData, testData;
	private ArrayList<String> testIds;
	
	
	/**
	 * A constructor that is using instance initialisation
	 * @author thierry WADJI
	 * @throws Exception
	 */
	public Titanic() throws Exception 
	{
		preprocessData( trainingData, testData );
	}
	
	public Titanic( final Instances trainingSet, final Instances testSet ) throws Exception 
	{
		preprocessData( trainingSet, testSet );
	}
	
	/*
	 * Pre-process the training and test data. 
	 * 
	 * 1. Load the data. 
	 * 2. Make nominal features nominal.
	 * 3. Match training and test data (Add missing features and missing labels in nominal features). 
	 * 4. Define class feature ("Survived") in data.  
	 * 5. Keep only useful features identified during data exploration. 
	 * 6. Set Training and Test data instance variables. 
	 */
	public void preprocessData( final Instances trainingSet, final Instances testSet ) throws Exception 
	{
		System.out.println( "Pre-processing data."); 
		// Load training and test data 
		setTrainingData( new DataSource( trainingSet ).getDataSet() );
		setTestData( new DataSource( testSet ).getDataSet() );
				
		// Add survived feature (with empty values) to test data to make it compatible and keep the id field 
		keepPassengerIdOfTestData();
		
		// Make nominal features nominal 
		defineNominalClasses();
		
		// Match test and training data  
		MatchDataSets matchDataSets = new MatchDataSets( getTrainingData(), getTestData() );
		matchDataSets.run(); 
		setTrainingData( matchDataSets.getInstance1() );
		setTestData( matchDataSets.getInstance2() ); 
		
		// Set class attribute to feature "Survived" 
		InstancesManipulation.setClassAttribute( getTrainingData(), "predictor" ); 
		
		// Normalize features to range between 0 and 1. 
		normalizeNumericalFeatures();		
		
		// Remove unwanted features
		removeFeatures();  
		/*
		// Convert nominal features with k-values into k features with binary values. 
		nominalToBinary();
		*/
		System.out.println( "Pre-processing data. Done.");
	}
	
	/*
	 * Prepare the test data for processing 
	 * 1. Add the survived attribute to test data.
	 * 2. Keep the passengerId field for identification of prediction.  
	 */
	@SuppressWarnings("unused")
	private void keepPassengerIdOfTestData() throws Exception 
	{
		//Keep PassengerId
		final int n = InstancesManipulation.getFeatureIndex( getTestData(), "userid" );
		ArrayList<String> ids = new ArrayList<String>(); 
		for( int m = 0; m < getTestData().numInstances(); m++ )
		{
			ids.add( getTestData().instance( m ).toString( n ) ); 
		}
		
		setTestIds( ids );	
	}
	
	/*
	 * Define which features are nominal features 
	 */
	private void defineNominalClasses() throws Exception
	{
		final String[] nominalClasses = new String[] {
				"crop","weather","ph","climate","predictor", "userid"
		};
		setTrainingData( InstancesManipulation.makeNominal( getTrainingData(), nominalClasses ) );
		setTestData( InstancesManipulation.makeNominal( getTestData(), nominalClasses ) );
	}
	
	/*
	 * Remove unwanted features. 
	 */
	@SuppressWarnings("unused")
	private void removeFeatures() throws Exception
	{
		//Remove features
		final String[] classesToRemove = new String[] {
				"userid" //Nominal Features
				//"TicketId", //Nominal Features
				 //Numerical Features
		};
		
		for( String feature : classesToRemove )
		{
			setTrainingData( InstancesManipulation.removeAttribute( getTrainingData(), feature ) ); 
			//setTestData( InstancesManipulation.removeAttribute( getTrainingData(), feature ) );
		}  
	}
	
	/*
	 * Scales numerical features between 0 and 1.
	 * 1. Separate class feature from rest
	 * 2. Train normalization on training data.
	 * 3. Scale training data. 
	 * 4. Join class feature with scaled training data.
	 *     
	 * 5. Scale test data    
	 */
	private void normalizeNumericalFeatures() throws Exception
	{
		final NormalizeNumericFeatures norm = new NormalizeNumericFeatures(); 
		norm.setInputFormat( getTrainingData() );
		
		setTrainingData( Filter.useFilter( getTrainingData(), norm ) ) ;
		setTestData( Filter.useFilter( getTestData(), norm ) );
	}
	
	@SuppressWarnings("unused")
	private void nominalToBinary() throws Exception
	{
		NominalToBinary nominal2Binary = new NominalToBinary(); 
		nominal2Binary.setInputFormat( getTrainingData() );
		
		setTrainingData( Filter.useFilter( getTrainingData(), nominal2Binary ) );
		setTestData( Filter.useFilter( getTestData(), nominal2Binary ) );
	}
	
	/*
	 * Train classifier. 
	 * 1. Train classifier
	 * 2. Train InputMappedClassifier to match attribute labels across training and test data. 
	 * 
	 * by WTT: This method may be inherited from IPredictionService interface as the algorithm prediction may changed
	 */
	public void train() throws Exception
	{ 
		System.out.println( "Training model.");
	 
		//Evaluation evalSMO = new Evaluation( getTrainingData() ); 
		//evalSMO.crossValidateModel( new SMO(), getTrainingData(), 10, new Random( 1000 ) );
		//System.out.println( "SMO error: " +  evalSMO.errorRate() );
		//System.out.println( "SMO kappa: " +  evalSMO.kappa() ); 
		
		RandomForest base = new RandomForest();
		//LMT base = new LMT();
	    
	    Evaluation evaluation = new Evaluation(getTrainingData());
	    //evaluation.evaluateModel(base, getTestData();
	    //evaluation.evaluateModel(base, getTrainingData(), null);
	    evaluation.crossValidateModel(base, getTrainingData(), 2, new Random(1));
	    System.out.println(evaluation.toSummaryString());
		
		// Train classifier (Random Forest) 
		final RandomForest rf = new RandomForest();
		//final LMT rf = new LMT();
		rf.buildClassifier( getTrainingData() );
		setClassifier( rf );
		
		//Map
		final InputMappedClassifier imc = new InputMappedClassifier(); 
		imc.buildClassifier( getTrainingData() );
		setInputMappedClassifier( imc );
		
		System.out.println( "Training model. Done.");
	}
	
	/*
	 * Classify Instances
	 * 1. Open Output stream 
	 * 2. Classify Instances and write to file. 
	 * 3. Close Output stream. 
	 * 
	 * by WTT: This method may be inherited from IPredictionService interface in order to be used by another service
	 */
	public List<Classification> classify() throws Exception
	{		
		System.out.println( "Predict outcome.");
		Instance currentInstance;
		List<Classification> classList = new ArrayList<Classification>();
		//StringBuilder str = new StringBuilder("CLASSIFY RESPONSE: ");
		
		for( int m = 0; m < getTestData().numInstances(); m++  ) 
		{
			String id = getTestIds().get( m );  
			currentInstance = getInputMappedClassifier().constructMappedInstance( getTestData().get( m ) );
			 
			//String crop = currentInstance.attribute(m).value(0); 
			double pred = getClassifier().classifyInstance( currentInstance );
			//String crop = currentInstance.attribute(0).value((int) pred) ;
			
			classList.add(new Classification(currentInstance.stringValue(0).toString(), id, Processing.translatePrediction((int) pred)));
			//str.append(currentInstance.stringValue(0).toString() + ", " + id + "," + (int) pred);
//			System.out.println(currentInstance.stringValue(0).toString() + ", " + id + "," + (int) pred ); 
			System.out.println("Current Instance" + m + ": " + currentInstance.toString());
		}
		
		System.out.println( "Predict outcome. Done.");
		
		//return str.toString();
		return classList;
	}
	
	private void setClassifier( final AbstractClassifier classifier ) { this.classifier = classifier; }
	public AbstractClassifier getClassifier() { return this.classifier; }
	
	private void setInputMappedClassifier( final InputMappedClassifier imc ) { this.imc = imc; }
	public InputMappedClassifier getInputMappedClassifier() { return this.imc; }
	
	private void setTrainingData( final Instances trainingData ) { this.trainingData = trainingData; } 
	public Instances getTrainingData() { return this.trainingData; }
	
	private void setTestData( final Instances testData ) { this.testData = testData; }
	public Instances getTestData() { return this.testData; }
	
	private void setTestIds( final ArrayList<String> testIds ) { this.testIds = testIds; }
	public ArrayList<String> getTestIds() { return this.testIds; } 
	
	/*
	 * Predict survival. 
	 * 1. Define input data (training and test data). 
	 * 2. Define output data (predictions) 
	 * 3. Load and pre-process data
	 * 4. Train classifier
	 * 5. Classify 
	 */
	
	/*
	 * public static void main(String[] args) throws Exception { final long time =
	 * System.currentTimeMillis();
	 * 
	 * //Specify data //final String trainingSet =
	 * "resources/titanic/trainClean.csv"; //final String testSet =
	 * "resources/titanic/testClean.csv"; final String predictOut =
	 * "E:\\models\\predict.csv";
	 * 
	 * 
	 * //Trainset data from SQL Postgres InstanceQuery query = new InstanceQuery();
	 * query.setDatabaseURL("jdbc:postgresql://localhost:5432/Cognitivefarms");
	 * query.setUsername("epie"); query.setPassword("ernestine"); query.
	 * setQuery("SELECT crop, weather, ph, climate, predictor FROM trainset order by crop, predictor asc"
	 * ); Instances trainingSet = query.retrieveInstances();
	 * 
	 * //Testset data from SQL Postgres
	 * 
	 * InstanceQuery Test = new InstanceQuery();
	 * Test.setDatabaseURL("jdbc:postgresql://localhost:5432/Cognitivefarms");
	 * Test.setUsername("epie"); Test.setPassword("ernestine"); Test.
	 * setQuery("SELECT crop, weather, ph, climate, userid, predictor from cropscatalog order by crop, predictor asc"
	 * ); // Read // table Instances testSet = Test.retrieveInstances(); //
	 * testSet.setClassIndex(testSet.numAttributes() -1);
	 * 
	 * Titanic tt = new Titanic( trainingSet, testSet ); tt.train(); tt.classify(
	 * predictOut );
	 * 
	 * System.out.println( "Done. [" + (System.currentTimeMillis() - time)/1000 +
	 * " s]" ); }
	 */

}
