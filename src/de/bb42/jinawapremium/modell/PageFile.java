package de.bb42.jinawapremium.modell;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PageFile {
	private File file;

	public PageFile(File file) {
		this.file = file;
	}

	public void save() {

	}

	/**
	 * Save Page to Data System
	 * 
	 * @param content
	 *            Content to be saved
	 */
	public void save(String content) {
		FileWriter writer;
		try {
			File newName = new File(file.getParent() + "/"
					+ createName(content));
			if (file.renameTo(newName)) {
				file = newName;
			}
			writer = new FileWriter(file);
			writer.write(content.toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String createName(String content) {
		String name;
		name = content.substring(0,
				(content.length() > 25 ? 25 : content.length()));
		name = name.replaceAll("[^a-zA-Z]", "");
		return name + ".txt";
	}

	/**
	 * loads Content of the Page
	 * 
	 * @return
	 */
	public StringBuffer loadPage() {
		StringBuffer ret = new StringBuffer("");
		FileReader fileReader;
		try {
			fileReader = new FileReader(file);
			BufferedReader pageReader = new BufferedReader(fileReader);
			ret = getContent(pageReader);
			pageReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}

	private StringBuffer getContent(BufferedReader pageReader)
			throws IOException {
		StringBuffer content = new StringBuffer("");
		String zeile = pageReader.readLine();
		while (zeile != null) {
			content.append(zeile+"\n");
			zeile = pageReader.readLine();
		}
		return content;

	}

	/**
	 * Deletes the Page from File system
	 */
	public void delete() {
		file.delete();
	}
	
	public String getFileAdress(){
		return file.getAbsolutePath();
	}
}
