package de.bb42.jinawapremium.controller.datatypes;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import de.bb42.jinawapremium.modell.StapleFile;

/**
 * 
 * @author Bennet Bartmann
 * 
 */
public class StapleOfStaples {
	private List<Staple> staples = new LinkedList<Staple>();
	private File baseDir;

	/**
	 * Constructor for StapleOfStaples
	 * 
	 * @param baseDir
	 */
	public StapleOfStaples(File baseDir) {
		this.baseDir = baseDir;
	}

	/**
	 * 
	 * @return the Staples
	 */
	public List<Staple> getStaples() {
		return staples;
	}

	/**
	 * Create new Staple
	 */
	public void createNewStaple() {
		File newDir = new File(baseDir.getAbsolutePath() + "/newFolder");
		if (newDir.mkdir()) {
			Staple staple = new Staple(new StapleFile(newDir));
			staples.add(staple);
			staple.setTitel(new StringBuffer("NewFolder"));
		}

	}

	public void delete(int number) {
		staples.get(number).delete();
		staples.remove(number);
	}

	/**
	 * Create new Staple with given Name
	 * 
	 * @param Name
	 *            Name for String
	 */
	public void createNewStaple(StringBuffer Name) {
		File newDir = new File(baseDir.getAbsolutePath() + "/"
				+ Name.toString());
		if (newDir.mkdir()) {
			Staple newStaple = new Staple(new StapleFile(newDir));
			staples.add(newStaple);
			newStaple.setTitel(Name);
		}
	}
	/**
	 * Do Never use this in a productiv version! 
	 * This is for Testing Purpose only, deletes ALL Staples!!
	 */
	public void deleteAll(){
		for(Staple s: staples){
			s.delete();
		}
		System.exit(1);
	}

}
