package de.bb42.jinawapremium.view;

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

	public void setSlideScreenStaples(SlideScreenStaples slideScreenStaples){
		this.slideScreenStaples = slideScreenStaples;
	}

	public SlideScreenPapers getSlideScreenPapers() {
		return slideScreenPapers;
	}

	public void setSlideScreenPapers(SlideScreenPapers slideScreenPapers){
		this.slideScreenPapers = slideScreenPapers;
	}
	
}