package de.bb42.jinawa.view;

import de.bb42.jinawa.R;
import de.bb42.jinawa.controller.datatypes.Staple;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Fragment for Staples
 * 
 * @author Johannes Becker
 * 
 */
public class FragmentStaples extends Fragment implements View.OnClickListener {
	private View view;
	private Staple stapleData;
	private int position;

	public FragmentStaples(Staple stapleData, int position) {
		this.stapleData = stapleData;
		this.position = position;

	}

	Intent intentPaper = new Intent(SlideScreenStaples.getContext(),
			SlideScreenPapers.class);

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
		Button mButton = (Button) view.findViewById(R.id.button1);

		mButton.setText(stapleData.getTitel());
		mButton.setId(position);
		mButton.setBackgroundResource(R.drawable.staple2);
		mButton.setOnClickListener(this);
		mButton.setOnLongClickListener(new OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				InputDialog dialog = new InputDialog();
				dialog.show(getChildFragmentManager(), getTag());

				return true;
			}
		});
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
			InputDialog dialog = new InputDialog();
			dialog.show(getChildFragmentManager(), getTag());
		} else {
			intentPaper.putExtra("positionStaple", id);
			startActivity(intentPaper);
		}

	}

}