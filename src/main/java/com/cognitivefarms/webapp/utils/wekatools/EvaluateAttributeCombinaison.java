package com.cognitivefarms.webapp.utils.wekatools;

import org.paukov.combinatorics.ICombinatoricsVector;

import weka.classifiers.Evaluation;

/**
 * Holds an attribute combination together with a classifier evaluation. 
 * @author carrillo
 *
 */
public class EvaluateAttributeCombinaison 
{
	private ICombinatoricsVector<Integer> attributeIndex; 
	private Evaluation evaluation; 
	
	public EvaluateAttributeCombinaison( final ICombinatoricsVector<Integer> attributeIndex, final Evaluation evaluation )
	{
		setAttributeIndex( attributeIndex );
		setEvaluation( evaluation );
	}
	
	public String toString() 
	{
		return getAttributeIndex() + "\tError: " + getEvaluation().errorRate();  
	}
	
	private void setAttributeIndex( final ICombinatoricsVector<Integer> attributeIndex ) { this.attributeIndex = attributeIndex; }
	public ICombinatoricsVector<Integer> getAttributeIndex() { return this.attributeIndex; }
	
	private void setEvaluation( final Evaluation evaluation ) { this.evaluation = evaluation; }
	public Evaluation getEvaluation() { return this.evaluation; }
	
}
