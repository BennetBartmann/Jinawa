/**
 * 
 */
package de.bb42.jinawa.datatypes;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Bennet Bartmann
 * @version 0.1
 */
public class Staple {
	private List<Page> pages = new LinkedList<Page>();
	private StringBuffer titel = new StringBuffer("Beispieltitel");
	private File stapleFolder;
	boolean loaded = false;
	/**
	 * Intiallizes New Staple
	 */
	public Staple(File stapleFolder){
		this.stapleFolder = stapleFolder;
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
		if (!loaded){
			loadStaple();
		}
		return pages;
	}

	/**
	 * Creates a new Page
	 */
	public void createNewPage(){
		try { 
			File file = new File(stapleFolder.getAbsoluteFile()+"/newPage.txt");
            file.createNewFile(); 
            pages.add(new Page(file));
        } catch (IOException e) { 
            e.printStackTrace(); 
        } 
		
	}
	/**
	 * Sets a new/changed Titel for the Staple, this operation is reflected into the file System.
	 * @param titel new Titel for Staple
	 */
	public void setNewTitel(StringBuffer titel){
		File newStapleFolder = new File(stapleFolder.getParent()+"/"+titel.toString());
		if(stapleFolder.renameTo(newStapleFolder)){
			stapleFolder = newStapleFolder;
			this.titel = titel;
		}
		
	}
	/**
	 * Setter for Titel, no Reflection into file System
	 * @param titel titel to set.
	 */
	public void setTitel(StringBuffer titel){
		this.titel = titel;	
	}
	private void loadStaple() {
		File[] pages = stapleFolder.listFiles(new TxtFilter());
		if (pages == null){
			loaded = true;
		}else{
			pages = convertPages(pages);
			loaded = true;
		}
		
	}
	
	private File[] convertPages(File[] pages) {
		for (int i = 0; i < pages.length; i++){
			if(!pages[i].isDirectory()){
				this.pages.add(createPage(pages[i]));
			}
		}
		return null;
	}
	private Page createPage(File file) {
		Page page = new Page(file);
		return page;
	}
	/**
	 * Filter Class for .txt Files
	 * @author Bennet Bartmann
	 *
	 */
	class TxtFilter implements FileFilter {
		    @Override
		    public boolean accept(File file) {
		      return !file.isHidden() && file.getName().endsWith(".txt");
		    }
	}
}
