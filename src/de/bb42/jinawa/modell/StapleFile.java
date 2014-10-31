package de.bb42.jinawa.modell;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import de.bb42.jinawa.controller.datatypes.Page;

public class StapleFile {
	private File stapleFolder;

	public StapleFile(File file) {
		stapleFolder = file;
	}

	/**
	 * Loads the Staple
	 * 
	 * @return list of Pages
	 */
	public List<Page> loadStaple() {
		List<Page> pages = new LinkedList<Page>();
		File[] pagesFiles = stapleFolder.listFiles(new TxtFilter());
		if (pagesFiles != null) {
			pages = convertPages(pagesFiles);
		}
		return pages;
	}

	/**
	 * Renames the File
	 * 
	 * @param newName
	 *            New Name for Staple
	 * @return true == everything ok
	 */
	public boolean rename(String newName) {
		File newStapleFolder = new File(stapleFolder.getParent() + "/"
				+ newName.toString());
		if (stapleFolder.renameTo(newStapleFolder)) {
			stapleFolder = newStapleFolder;
			return true;
		}
		return false;
	}

	/**
	 * Creates a new Page File
	 * 
	 * @return the Page File
	 * @throws PaperExisted 
	 */
	public PageFile createNewPageFile() {
		try {
			File file = new File(stapleFolder.getAbsoluteFile()
					+ "/newPage.txt");
			if(file.createNewFile()){
				return new PageFile(file);
			}else{
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private List<Page> convertPages(File[] pagesFiles) {
		List<Page> pages = new LinkedList<Page>();
		for (int i = 0; i < pagesFiles.length; i++) {
			if (!pagesFiles[i].isDirectory()) {
				pages.add(createPage(pagesFiles[i]));
			}
		}
		return pages;
	}

	private Page createPage(File file) {
		Page page = new Page(new PageFile(file));
		return page;
	}

	/**
	 * Filter Class for .txt Files
	 * 
	 * @author Bennet Bartmann
	 * 
	 */
	class TxtFilter implements FileFilter {
		@Override
		public boolean accept(File file) {
			return !file.isHidden() && file.getName().endsWith(".txt");
		}
	}

	/**
	 * deletes StapleFolder from File System. Deletes Content too.
	 */
	public void delete() {
		File[] listFiles = stapleFolder.listFiles();
		for (int i = 0; i < listFiles.length; i++) {
			File file = listFiles[i];
			file.delete();
		}
		stapleFolder.delete();

	}
}
