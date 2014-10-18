package de.bb42.jinawa.datatypes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 * @author Bennet Bartmann
 * @version 0.1
 */
public class Page {
	private StringBuffer content = new StringBuffer("Hallo ich bin ein Testtext\n Sogar mit Absatz!");
	//private Date lastModifiedDate;
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
	 * Save Page to Data System
	 */
	public void save(){
		FileWriter writer;
		try {
			File newName = new File(pageFile.getParent()+"/"+createName());
			if(pageFile.renameTo(newName)){
				pageFile = newName;
			}
			writer = new FileWriter(pageFile);
			writer.write(content.toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	private String createName() {
		String name;
		name = content.substring(0, 25);
		name.replaceAll(" ", "");
		return name+".txt";
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
		FileReader fileReader; 
		try {
			fileReader = new FileReader(pageFile);
			BufferedReader pageReader = new BufferedReader(fileReader);
			getContent(pageReader);
			pageReader.close();
			loaded = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	private void getContent(BufferedReader pageReader) throws IOException{
		String zeile = pageReader.readLine();
		 while (zeile != null) { 
			content.append(zeile);
			zeile = pageReader.readLine(); 
		}
		
	}

}
