package de.bb42.jinawa.modell;

import java.io.File;

import android.os.Environment;

/**
 * Static Class for all util Methods regarding storage
 * @author Bennet Bartmann
 * 
 */
public class Utils {
	/**
	 *  Checks if external storage is available for read and write 
	 */
	public boolean isExternalStorageWritable() {
	    String state = Environment.getExternalStorageState();
	    if (Environment.MEDIA_MOUNTED.equals(state)) {
	        return true;
	    }
	    return false;
	}
	
	/**
	 * Create a Jinawa Directory in the Documents section
	 * @return the file Pointer
	 */
	public File getAlbumStorageDir() {
	    File file = new File(Environment.getExternalStoragePublicDirectory(
	            Environment.DIRECTORY_DOCUMENTS), "Jinawa");
	    if (!file.mkdirs()) {
	    }
	    return file;
	}
}
