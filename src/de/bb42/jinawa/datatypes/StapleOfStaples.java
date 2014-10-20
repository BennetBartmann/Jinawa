package de.bb42.jinawa.datatypes;

import java.io.File;
import java.util.List;

import de.bb42.jinawa.modell.StapleFile;
/**
 * 
 * @author Bennet Bartmann
 *
 */
public class StapleOfStaples {
	private List<Staple> staples;
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
		File newDir = new File(baseDir.getAbsolutePath()+"newFolder");
		if(newDir.mkdir()){
			staples.add(new Staple(new StapleFile(newDir)));
		}
		
	}
}
