package de.bb42.jinawa.view;

/**
 *Singleton for View Data
 * @author Bennet Bartmann
 *
 */
public class ViewDataHolder {
	private static ViewDataHolder vdh = null;
	private SlideScreenStaples slideScreenStaples = null;
	private SlideScreenPapers slideScreenPapers = null;
	/**
	 * get Instance
	 * @return the Instance
	 */
	public static ViewDataHolder getInstance(){
		if (vdh == null){
			vdh = new ViewDataHolder();
		}
		return vdh;
	}
	
	private ViewDataHolder(){
		
	}

	public SlideScreenStaples getSlideScreenStaples() {
		return slideScreenStaples;
	}

	public void setSlideScreenStaples(SlideScreenStaples slideScreenStaples) throws Exception {
		if (slideScreenStaples != null){
			throw (new Exception());
		}
		this.slideScreenStaples = slideScreenStaples;
	}

	public SlideScreenPapers getSlideScreenPapers() {
		return slideScreenPapers;
	}

	public void setSlideScreenPapers(SlideScreenPapers slideScreenPapers) throws Exception {
		if (slideScreenStaples != null){
			throw (new Exception());
		}
		this.slideScreenPapers = slideScreenPapers;
	}
	
}
