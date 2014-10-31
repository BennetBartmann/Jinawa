package de.bb42.jinawa.view;

import android.content.Context;
import android.content.Intent;
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
 * SlideScreen for Staples
 * 
 * @author Johannes Becker
 * 
 */
public class SlideScreenPapers extends FragmentActivity {
	private static Context context;
	private Controller controller = Controller.getInstance();
	private int positionStaples;
	private Staple staple;
	private int stapleSize;
	private FragmentManager fmanager;

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
		return SlideScreenPapers.context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_screen_slide);
		Intent IntentStaples = getIntent();
		Bundle b = IntentStaples.getExtras();
		if (b != null) {
			positionStaples = (Integer) b.get("positionStaple");
			staple = controller.getStapleOfStaples().getStaples()
					.get(positionStaples);
			stapleSize = staple.getPages().size() + 1;
		}

		SlideScreenPapers.context = this;
		// Instantiate a ViewPager and a PagerAdapter.
		mPager = (ViewPager) findViewById(R.id.pager);
		mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
		mPager.setAdapter(mPagerAdapter);
		ViewDataHolder.getInstance().setSlideScreenPapers(this);
	}

	/**
	 * A pager adapter that represents ScreenSlidePageFragment objects
	 */
	private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
		public ScreenSlidePagerAdapter(FragmentManager fm) {
			super(fm);
			fmanager = fm;
		}

		@Override
		public Fragment getItem(int position) {

			if (position == stapleSize - 1) {
				FragmentNewPaper fragment = new FragmentNewPaper(positionStaples);
				return fragment;

			} else {
				FragmentPapers fragment = new FragmentPapers(staple.getPages()
						.get(position), position, positionStaples);
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
			if (object instanceof FragmentNewPaper) {
				FragmentNewPaper fragment = (FragmentNewPaper) object;
				if (fmanager.getFragments().contains(fragment)) {
					return POSITION_NONE;
				} else {
					return POSITION_UNCHANGED;
				}

			}
			if (object instanceof FragmentPapers) {
				FragmentPapers fragment = (FragmentPapers) object;
				if (fmanager.getFragments().contains(fragment)) {
					return POSITION_NONE;
				} else {
					return POSITION_UNCHANGED;
				}

			}

			return -1;
		}
	}

	public void upDateData() {
		staple = controller.getStapleOfStaples().getStaples()
				.get(positionStaples);
		if (staple != null) {
			stapleSize = staple.getPages().size() + 1;
		} else {
			// error
		}

	}

	public void upDateView() {
		upDateData();
		mPager.getAdapter().notifyDataSetChanged();
	}

	@Override
	public void onBackPressed() {
		ViewDataHolder.getInstance().getSlideScreenStaples().upDateView();
		super.onBackPressed();

	}
}