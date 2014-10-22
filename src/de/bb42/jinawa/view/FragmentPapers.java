package de.bb42.jinawa.view;

import de.bb42.jinawa.R;
import de.bb42.jinawa.controller.datatypes.Page;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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

	private int position;
	private int positionStaples;
	private Page page;

	public FragmentPapers(Page page, int position, int positionStaples) {
		this.positionStaples = positionStaples;
		this.position = position;
		this.page = page;
	}

	Intent intentWriter = new Intent(SlideScreenPapers.getAppContext(),
			Writer.class);

	/**
	 * Create and return View
	 * 
	 * @param inflater
	 *            ?
	 * @param container
	 *            ?
	 * @param savedInstanceState
	 *            ?
	 * @return View
	 */
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.fragment_screen_slide_page, container,
				false);
		view.setId(position);
		Button mButton = (Button) view.findViewById(R.id.button1);
		mButton.setId(position);
		int length = page.getContent().length();
		if (length >= 200) {
			mButton.setText(page.getContent().subSequence(0, 200));
		} else {
			mButton.setText(page.getContent().subSequence(0, length));
		}
		mButton.setBackgroundResource(R.drawable.paper);
		mButton.setOnClickListener(this);

		return view;
	}

	/**
	 * Starts new Activity
	 * 
	 * @param v
	 *            ?
	 */
	@Override
	public void onClick(View v) {
		Log.d("MyApp", "PositionStapleThree" + v.getId());

		int id = ((Button) v).getId();
		intentWriter.putExtra("positionPaper", id);
		intentWriter.putExtra("positionStaples", positionStaples);

		startActivity(intentWriter);

	}
}