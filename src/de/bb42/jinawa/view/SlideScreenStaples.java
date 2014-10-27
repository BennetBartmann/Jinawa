package de.bb42.jinawa.view;

import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import de.bb42.jinawa.R;
import de.bb42.jinawa.controller.Controller;
import de.bb42.jinawa.controller.datatypes.Staple;
import de.bb42.jinawa.controller.datatypes.StapleOfStaples;

/**
 * SlideScreen for Papers
 * 
 * @author Johannes Becker
 * 
 */
public class SlideScreenStaples extends FragmentActivity {
	private static SlideScreenStaples instance = null;

	private static Context context;
	private static Controller controller = Controller.getInstance();
	private static StapleOfStaples controllerStaple = Controller.getInstance()
			.getStapleOfStaples();

	private static List<Staple> staples = controller.getStapleOfStaples()
			.getStaples();

	private static int stapleSize;

	/**
	 * Get the instance of SlideScreenStaples
	 * 
	 * @return SlideScreenStaples
	 */
	public static SlideScreenStaples getInstance() {
		if (instance == null) {
			instance = new SlideScreenStaples();
		}
		return instance;
	}

	/**
	 * Get the context of SlideScreenStaples
	 * 
	 * @return Context of SlideScreenStaples
	 */
	public static Context getAppContext() {
		return SlideScreenStaples.context;
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
		SlideScreenStaples.context = this;

		if (staples != null) {
			stapleSize = staples.size();
		}
		// Instantiate a ViewPager and a PagerAdapter.
		mPager = (ViewPager) findViewById(R.id.pager);
		mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
		Output.sendToast(this, mPagerAdapter.toString());
		mPager.setAdapter(mPagerAdapter);
	}

	/**
	 * A pager adapter that represents ScreenSlidePapersFragment objects
	 */
	private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
		/**
		 * 
		 * @param fm
		 */
		public ScreenSlidePagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {

			return new FragmentStaples(staples.get(position), position);
		}

		@Override
		public int getCount() {
			return stapleSize;
		}
	}

	public void upDateData() {
		staples = controller.getStapleOfStaples().getStaples();
		if (staples != null) {
			stapleSize = staples.size();
		} else {
			// error
		}

	}

	public void upDateView() {

	}

	public static Context getContext() {
		return context;
	}

}