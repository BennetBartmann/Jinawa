/**
 * 
 */
package de.bb42.jinawa.datatypes;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Bennet Bartmann
 * @version 0.1
 */
public class Staple {
	private List<Page> pages = new LinkedList<Page>();
	private StringBuffer titel = new StringBuffer("Beispieltitel");
	/**
	 * Intiallizes New Staple
	 */
	public Staple(){
		for(int i = 0; i < 3;i++){
			pages.add(new Page());
		}
	}
	/**
	 * @return the titel
	 */
	public StringBuffer getTitel(){
		return titel;
	}
	
	/**
	 * @return all Pages of the staple
	 */
	public List<Page> getPages(){
		return pages;
	}
	/**
	 * Creates a new Page
	 */
	public void createNewPage(){
		pages.add(new Page());
	}
	/**
	 * 
	 * @param titel new Titel for Staple
	 */
	public void setNewTitel(StringBuffer titel){
		this.titel = titel;
	}
}
