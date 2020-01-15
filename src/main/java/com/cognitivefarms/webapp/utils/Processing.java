package com.cognitivefarms.webapp.utils;

/**
 * Contains some utils fonctions
 * 
 * @author thierry WADJI
 *
 */
public class Processing {
	
	/**
	 * Translate the value of prediction from number to letters
	 * 
	 * @param pred
	 * @return
	 */
	public static String translatePrediction(int pred) {
		
		switch (pred) {
		case 0:
			return "grow";
			
		case 1:
			return "grow_irrigated";
			
		case 2:
			return "no_grow";

		default:
			return "";
		}
		
	}

}
