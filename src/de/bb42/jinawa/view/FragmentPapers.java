package de.bb42.jinawa.view;

import de.bb42.jinawa.R;
import de.bb42.jinawa.controller.Controller;
import de.bb42.jinawa.controller.datatypes.Page;
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

	private View view;

	private int position;
	private int positionStaple;
	private Page page;

	public FragmentPapers(Page page, int position, int positionStaple) {
		this.positionStaple = positionStaple;
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

		int id = ((Button) v).getId();
		if (id == 0) {
			Controller.getInstance().getStapleOfStaples().getStaples()
					.get(positionStaple).createNewPage();
//			SlideScreenPapers.getInstance().upDateView();
		} else {
			intentWriter.putExtra("positionPaper", id);
			intentWriter.putExtra("positionStaples", positionStaple);

			startActivity(intentWriter);
		}
	}
}