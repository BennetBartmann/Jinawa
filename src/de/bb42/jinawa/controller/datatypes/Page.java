package de.bb42.jinawa.controller.datatypes;


import de.bb42.jinawa.modell.PageFile;

/**
 * 
 * @author Bennet Bartmann
 * @version 0.1
 */
public class Page {
	private StringBuffer content = new StringBuffer("Hallo ich bin ein Testtext\n Sogar mit Absatz!");
	//private Date lastModifiedDate;
	/*@todo Add need functionality*/
	private PageFile pageFile;
	private boolean loaded = false;
	
	public Page(PageFile pageFile){
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
	 * Save Page to Data System
	 */
	public void save(){
		pageFile.save(content.toString());
	}
	
	/**
	 * Save Page to Data System
	 * @param content Content to save
	 */
	public void save(StringBuffer content){
		this.content = content;
		pageFile.save(content.toString());
	}
	
	
	/*
	 * 
	 * @return Date of last Modification
	 *
	public Date getLastModifiedDate(){
		if (!loaded){
			loadPage();
		}
		return lastModifiedDate;
	}*/
	private void loadPage() {
		content = pageFile.loadPage();
		loaded = true;
	}

}
