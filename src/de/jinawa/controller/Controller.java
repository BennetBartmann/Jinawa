/**
 * 
 */
package de.jinawa.controller;

import android.util.SparseArray;

import de.jinawa.datatypes.Staple;

/**
 * @author Bennet Bartmann
 * @version 0.1
 * This class is a singleton so View has easier access to it
 */
public class Controller {
	private SparseArray<Staple> staples= new SparseArray<Staple>();
	private static Controller instance = null;
	/**
	 * Prevent others from instanciating the Modell
	 */
	private Controller() {
	     // Exists only to defeat instantiation.
	}
	/**
	 * 
	 * @return the instance of the Controller
	 */
	public static Controller getInstance() {
	    if(instance == null) {
	    	instance = new Controller();
	     }
	     return instance;
	}
	
	public SparseArray<Staple> getStaples() {
		return staples;
	}
	
	public Staple getStaple(int i){
		return staples.get(i);
	}
	
}
