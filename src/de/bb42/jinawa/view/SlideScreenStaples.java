package de.bb42.jinawa.view;

import java.util.ArrayList;
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

/**
 * SlideScreen for Papers
 * 
 * @author Johannes Becker
 * 
 */
public class SlideScreenStaples extends FragmentActivity {
	private static Context context;
	private Controller controller = Controller.getInstance();
	private List<Staple> staples = controller.getStapleOfStaples().getStaples();

	private int stapleSize;
	/**
	 * The pager widget, which handles animation and allows swiping horizontally
	 * to access previous and next wizard steps.
	 */
	private ViewPager mPager;

	/**
	 * provides the pages to the ViewPager
	 */
	private PagerAdapter mPagerAdapter;

	/**
	 * Get the context of SlideScreenStaples
	 * 
	 * @return Context of SlideScreenStaples
	 */
	public static Context getAppContext() {
		return SlideScreenStaples.getContext();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen_slide);
		SlideScreenStaples.context = this;

		if (staples != null) {
			stapleSize = staples.size() + 1;
		}
		// Instantiate a ViewPager and a PagerAdapter.
		mPager = (ViewPager) findViewById(R.id.pager);
		mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
		mPager.setAdapter(mPagerAdapter);
		ViewDataHolder.getInstance().setSlideScreenStaples(this);
	}

	public void upDateData() {
		staples = controller.getStapleOfStaples().getStaples();
		if (staples != null) {
			stapleSize = staples.size();
			stapleSize++;
		} else {
			// error
		}

	}

	public void upDateView() {
		upDateData();
		mPager.getAdapter().notifyDataSetChanged();
		mPager.destroyDrawingCache();
	}

	public static Context getContext() {
		return context;
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
			if (position + 1 == stapleSize) {
				return new FragmentSettings();

			} else {
				return new FragmentStaples(staples.get(position), position);
			}
		}

		@Override
		public int getCount() {
			return stapleSize;
		}

	}

}