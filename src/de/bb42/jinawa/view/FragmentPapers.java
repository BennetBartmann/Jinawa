package de.bb42.jinawa.view;

import de.bb42.jinawa.R;
import de.bb42.jinawa.controller.datatypes.Page;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnLongClickListener;
import android.widget.Button;

/**
 * Fragment for Papers
 * 
 * @author Johannes Becker
 * 
 */
public class FragmentPapers extends Fragment implements View.OnClickListener {

	private ViewGroup view;

	private int position;
	private int positionStaple;
	private Page page;
	private Intent intentWriter = new Intent(SlideScreenPapers.getContext(),
			Writer.class);
	private int paperTitleLenght = 20;

	public FragmentPapers(Page page, int position, int positionStaple) {
		this.positionStaple = positionStaple;
		this.position = position;
		this.page = page;
	}



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
		view.setId(position);
		Button mButton = (Button) view.findViewById(R.id.button1);
		mButton.setId(position);
		setButtonText(mButton);
		mButton.setBackgroundResource(R.drawable.paper);
		mButton.setOnClickListener(this);
		mButton.setOnLongClickListener(new OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				DialogLongClickPaper dialogLongClick = new DialogLongClickPaper(positionStaple,
						position);
				dialogLongClick.show(getFragmentManager(), getTag());

				return true;
			}
		});
		return view;
	}

	private void setButtonText(Button Paper) {
			setButtonTextContent(Paper);
	}


	private void setButtonTextContent(Button Paper) {
		int length = page.getContent().length();
		if (length >= paperTitleLenght) {
			Paper.setText(page.getContent().subSequence(0, paperTitleLenght));
		}else if (length == 0){
			Paper.setText(R.string.emptyPaper);
		}
		
		else {
			Paper.setText(page.getContent().subSequence(0, length));
		}
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
		intentWriter.putExtra("positionPaper", id);
		intentWriter.putExtra("positionStaples", positionStaple);
		startActivity(intentWriter);
	}

}