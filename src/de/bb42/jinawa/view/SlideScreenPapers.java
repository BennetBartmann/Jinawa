package de.bb42.jinawa.view;

import de.bb42.jinawa.R;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

/**
 * SlideScreen for Staples
 * 
 * @author Johannes Becker
 * 
 */
public class SlideScreenPapers extends FragmentActivity {
	private static SlideScreenPapers instance = null;
	private static final int NUM_PAGES = 3;
	private static Context context;

	/**
	 * Get the instance of SlideScreenStaples
	 * 
	 * @return SlideScreenStaples
	 */
	public static SlideScreenPapers getInstance() {
		if (instance == null) {
			instance = new SlideScreenPapers();
		}
		return instance;
	}

	/**
	 * Get the context of SlideScreenStaples
	 * 
	 * @return Context of SlideScreenStaples
	 */
	public static Context getAppContext() {
		return SlideScreenPapers.context;
	}

	/**
	 * The pager widget, which handles animation and allows swiping horizontally
	 * to access previous and next wizard steps.
	 */
	private ViewPager mPager;

	/**
	 * provides the pages to the ViewPager
	 */
	private PagerAdapter mPagerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen_slide);
		SlideScreenPapers.context = getApplicationContext();
		// Instantiate a ViewPager and a PagerAdapter.
		mPager = (ViewPager) findViewById(R.id.pager);
		mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
		mPager.setAdapter(mPagerAdapter);
	}

	/**
	 * A pager adapter that represents ScreenSlidePageFragment objects
	 */
	private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
		public ScreenSlidePagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			return new FragmentPapers();
		}

		@Override
		public int getCount() {
			return NUM_PAGES;
		}
	}

}