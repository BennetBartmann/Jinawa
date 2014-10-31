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
import android.util.Log;
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
	private FragmentManager fmanager;
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
			stapleSize = staples.size() + 2;
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
			stapleSize = staples.size() + 2;
		} else {
			// error
		}

	}

	public void upDateView() {
		upDateData();
		mPager.getAdapter().notifyDataSetChanged();
		mPager.invalidate();
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
			fmanager = fm;

		}

		@Override
		public Fragment getItem(int position) {
			if (position == stapleSize - 2) {
				FragmentNewStaple fragment = new FragmentNewStaple();
				return fragment;

			} else if (position == stapleSize - 1) {
				FragmentSettings fragment = new FragmentSettings(position);
				return fragment;

			} else {
				FragmentStaples fragment = new FragmentStaples(
						staples.get(position), position);
				return fragment;
			}

		}

		@Override
		public int getCount() {
			return stapleSize;
		}

		@Override
		public int getItemPosition(Object object) {

			Log.v(STORAGE_SERVICE, object.getClass() + "");
			if (object instanceof FragmentSettings) {
				FragmentSettings fragment = (FragmentSettings) object;
				if (fmanager.getFragments().contains(fragment)) {
					return POSITION_NONE;
				} else {
					return POSITION_UNCHANGED;
				}

			}
			if (object instanceof FragmentNewStaple) {
				FragmentNewStaple fragment = (FragmentNewStaple) object;
				if (fmanager.getFragments().contains(fragment)) {
					return POSITION_NONE;
				} else {
					return POSITION_UNCHANGED;
				}

			}
			if (object instanceof FragmentStaples) {

				FragmentStaples fragment = (FragmentStaples) object;
				if (fmanager.getFragments().contains(fragment)) {
					return POSITION_NONE;
				} else {
					return POSITION_UNCHANGED;
				}

			}
			return -1;
		}
	}
}