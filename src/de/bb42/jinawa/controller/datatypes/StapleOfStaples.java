package de.bb42.jinawa.controller.datatypes;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import de.bb42.jinawa.modell.StapleFile;
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
	 * @param baseDir
	 */
	public StapleOfStaples(File baseDir){
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
	public void createNewStaple(){
		File newDir = new File(baseDir.getAbsolutePath()+"/newFolder");
		if(newDir.mkdir()){
			Staple staple = new Staple(new StapleFile(newDir));
			staples.add(staple);
			staple.setTitel(new StringBuffer("NewFolder"));
		}
		
	}
	
	/**
	 * Create new Staple with given Name
	 * @param Name Name for String
	 */
	public void createNewStaple(StringBuffer Name){
		File newDir = new File(baseDir.getAbsolutePath()+"/"+Name.toString());
		if(newDir.mkdir()){
			Staple newStaple = new Staple(new StapleFile(newDir));
			staples.add(newStaple);
			newStaple.setTitel(Name);
		}
	}
}
