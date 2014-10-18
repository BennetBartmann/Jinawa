/**
 * 
 */
package de.bb42.jinawa.controller;

import de.bb42.jinawa.datatypes.StapleOfStaples;
import de.bb42.jinawa.modell.Modell;

/**
 * @author Bennet Bartmann
 * @version 0.1
 * This class is a singleton so View has easier access to it
 */
public class Controller {
	private StapleOfStaples staples;
	private static Controller instance = null;
	
	/**
	 * Prevent others from instanciating the Modell
	 */
	private Controller() {
	     Modell modell = new Modell();
	     this.staples = modell.getAllStaples();
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
	
	/**
	 * 
	 * @return Sparse Array of Staples
	 */
	public StapleOfStaples getStaples() {
		return staples;
	}
}
