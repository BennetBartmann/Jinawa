package de.jinawa.datatypes;

import java.util.Date;

/**
 * 
 * @author Bennet Bartmann
 * @version 0.1
 */
public class Page {
	private StringBuffer content = new StringBuffer("Hallo ich bin ein Testtext\n Sogar mit Absatz!");
	private Date lastModifiedDate;
	
	public Page(){
		
	}
	/**
	 * 
	 * @return Content of the Page
	 */
	public StringBuffer getContent(){
		return content;
	}
	/**
	 * 
	 * @return Only the Heading of the Text
	 */
	public StringBuffer getHeading(){
		return new StringBuffer("Hallo ich bin ein Testtext");
	}
	/**
	 * 
	 * @return only the Body of the Text
	 */
	public StringBuffer getBody(){
		return new StringBuffer ("Sogar mit Absatz");
	}
	/**
	 * 
	 * @return Date of last Modification
	 */
	public Date getLastModifiedDate(){
		return lastModifiedDate;
	}

}
