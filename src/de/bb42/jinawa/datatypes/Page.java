package de.bb42.jinawa.datatypes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

/**
 * 
 * @author Bennet Bartmann
 * @version 0.1
 */
public class Page {
	private StringBuffer content = new StringBuffer("Hallo ich bin ein Testtext\n Sogar mit Absatz!");
	private Date lastModifiedDate;
	/*@todo Add need functionality*/
	private File pageFile;
	private boolean loaded = false;
	
	public Page(File pageFile){
		this.pageFile = pageFile;
	}
	/**
	 * 
	 * @return Content of the Page
	 */
	public StringBuffer getContent(){
		if (!loaded){
			loadPage();
		}
		return content;
	}


	/**
	 * 
	 * @return Date of last Modification
	 */
	public Date getLastModifiedDate(){
		if (!loaded){
			loadPage();
		}
		return lastModifiedDate;
	}
	
	private void loadPage() {
		FileReader fileReader;
		String zeile; 
		try {
			fileReader = new FileReader(pageFile);
			BufferedReader pageReader = new BufferedReader(fileReader);
			zeile = pageReader.readLine();
		    while (zeile != null) { 
		    	content.append(zeile);
		        zeile = pageReader.readLine(); 
		    } 
		    pageReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	

}
