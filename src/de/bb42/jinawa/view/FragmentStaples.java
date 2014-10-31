package de.bb42.jinawa.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import de.bb42.jinawa.R;
import de.bb42.jinawa.controller.datatypes.Staple;

/**
 * Fragment for Staples
 * 
 * @author Johannes Becker
 * 
 */
public class FragmentStaples extends Fragment implements View.OnClickListener {
	private ViewGroup view;
	private Staple stapleData;
	private int position;

	public FragmentStaples(Staple stapleData, int position) {
		this.stapleData = stapleData;
		this.position = position;

	}

	Intent intentPaper = new Intent(SlideScreenStaples.getContext(),
			SlideScreenPapers.class);
	private String title;

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
		view = (ViewGroup) inflater.inflate(
				R.layout.fragment_screen_slide_page, container, false);
		Button mButton = (Button) view.findViewById(R.id.button1);

		mButton.setText(stapleData.getTitel());
		mButton.setId(position);
		mButton.setBackgroundResource(R.drawable.staple2);
		mButton.setOnClickListener(this);
		mButton.setOnLongClickListener(new OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				DialogLongClickStaples dialogLongClick = new DialogLongClickStaples(
						position);
				dialogLongClick.show(getFragmentManager(), getTag());

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
		intentPaper.putExtra("positionStaple", id);
		startActivity(intentPaper);

	}
	public void setTitle(String title) {
		this.title = title;

	}

	public String getTitle() {
		return title;

	}

}