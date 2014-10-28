package de.bb42.jinawa.modell;

import java.io.File;

import de.bb42.jinawa.controller.datatypes.Staple;
import de.bb42.jinawa.controller.datatypes.StapleOfStaples;

/**
 * 
 * @author Bennet Bartmann
 * @version 0.1
 */
public class Modell {
	private File baseDir;

	/**
	 * Constructor just because you could need it.
	 */
	public Modell() {
		if (Utils.isExternalStorageWritable()) {
			baseDir = Utils.getFoldersStorageDir();
		} else {
			// @todo some error code here
		}
	}

	/**
	 * returns all staples
	 * 
	 * @return all Staples
	 */
	public StapleOfStaples getAllStaples() {
		File[] stapleFiles = baseDir.listFiles();
		if (stapleFiles != null) {
			return convertStaple(stapleFiles);
		} else {
			StapleOfStaples staples = new StapleOfStaples(
					Utils.getFoldersStorageDir());
			staples.createNewStaple();
			return staples;
		}
	}

	private StapleOfStaples convertStaple(File[] stapleFiles) {
		StapleOfStaples staples = new StapleOfStaples(baseDir);
		for (int i = 0; i < stapleFiles.length; i++) {
			if (stapleFiles[i].isDirectory()) {
				staples.getStaples().add(createStaple(stapleFiles[i]));
			}
		}
		if (staples.getStaples().size() == 0){
			staples.createNewStaple(new StringBuffer("New Staple"));
		}
		return staples;
	}

	private Staple createStaple(File file) {
		Staple staple = new Staple(new StapleFile(file));
		staple.setTitel(new StringBuffer(file.getName()));
		return staple;
	}

}
