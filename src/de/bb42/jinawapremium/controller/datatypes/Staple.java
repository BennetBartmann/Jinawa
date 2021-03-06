/**
 * 
 */
package de.bb42.jinawapremium.controller.datatypes;

import java.util.LinkedList;
import java.util.List;

import de.bb42.jinawapremium.modell.PageFile;
import de.bb42.jinawapremium.modell.PaperExisted;
import de.bb42.jinawapremium.modell.StapleFile;

/**
 * @author Bennet Bartmann
 * @version 0.1
 */
public class Staple {
	private List<Page> pages = new LinkedList<Page>();
	private StringBuffer titel = new StringBuffer("Beispieltitel");
	private StapleFile stapleFolder;
	boolean loaded = false;

	/**
	 * Intiallizes New Staple
	 */
	public Staple(StapleFile stapleFolder) {
		this.stapleFolder = stapleFolder;
	}

	/**
	 * @return the titel
	 */
	public StringBuffer getTitel() {
		return titel;
	}

	/**
	 * @return all Pages of the staple
	 */
	public List<Page> getPages() {
		if (!loaded) {
			loadStaple();
		}
		return pages;
	}

	/**
	 * Creates a new Page
	 * @throws PaperExisted 
	 */
	public void createNewPage(){
		if (!loaded) {
			loadStaple();
		}
		Page p;
		PageFile pf;
		pf = stapleFolder.createNewPageFile();
		if (pf != null){
			p = new Page(pf);
			pages.add(p);
		}
	}

	/**
	 * Sets a new/changed Titel for the Staple, this operation is reflected into
	 * the file System.
	 * 
	 * @param titel
	 *            new Titel for Staple
	 */
	public void setNewTitel(StringBuffer titel) {
		if (stapleFolder.rename(titel.toString())) {
			this.titel = titel;
		}

	}

	/**
	 * Setter for Titel, no Reflection into file System
	 * 
	 * @param titel
	 *            titel to set.
	 */
	public void setTitel(StringBuffer titel) {
		this.titel = titel;
	}

	private void loadStaple() {
		pages = stapleFolder.loadStaple();
		if (pages == null) {
			pages = new LinkedList<Page>();
		}
		if (pages != null && pages.size() == 0) {
		}
		loaded = true;
	}

	/**
	 * Deletes the Paper
	 * 
	 * @param location
	 *            Location of Paper to Delete
	 */
	public void deletePaper(int location) {
		pages.get(location).delete();
		pages.remove(location);
	}

	/**
	 * deletes Staple from file System
	 */
	void delete() {
		stapleFolder.delete();
	}
}
