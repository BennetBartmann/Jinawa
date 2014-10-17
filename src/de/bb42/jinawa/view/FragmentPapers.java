package de.bb42.jinawa.view;

import de.bb42.jinawa.R;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Fragment for Papers
 * 
 * @author Johannes Becker
 * 
 */
public class FragmentPapers extends Fragment implements View.OnClickListener {

	View view;

	Intent intentWriter = new Intent(SlideScreenPapers.getAppContext(),
			Writer.class);

	/**
	 * Create and return View
	 * @param inflater ?
	 * @param container ?
	 * @param savedInstanceState ?
	 * @return View
	 */
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.fragment_screen_slide_page, container,
				false);
		Button mButton = (Button) view.findViewById(R.id.button1);
		mButton.setBackgroundResource(R.drawable.paper);
		mButton.setOnClickListener(this);

		return view;
	}

	/**
	 * Starts new Activity
	 * @param v ?
	 */
	@Override
	public void onClick(View v) {
		startActivity(intentWriter);

	}
}