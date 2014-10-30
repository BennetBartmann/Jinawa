package de.bb42.jinawa.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import de.bb42.jinawa.R;
import de.bb42.jinawa.controller.Controller;
import de.bb42.jinawa.controller.datatypes.Staple;

/**
 * Fragment for Papers
 * 
 * @author Johannes Becker
 * 
 */
public class FragmentNewPaper extends Fragment implements View.OnClickListener {
	private int positionStaple;
	Intent intentWriter = new Intent(SlideScreenPapers.getAppContext(),
			Writer.class);
	private View view;

	public FragmentNewPaper(int positionStaple) {
		this.positionStaple = positionStaple;

	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.fragment_screen_slide_page, container,
				false);

		Button mButton = (Button) view.findViewById(R.id.button1);
		mButton.setText(R.string.newPaper);
		mButton.setBackgroundResource(R.drawable.paper);
		mButton.setOnClickListener(this);

		return view;
	}

	@Override
	public void onClick(View arg0) {

		Staple latestStaple = Controller.getInstance().getStapleOfStaples()
				.getStaples().get(positionStaple);
		latestStaple.createNewPage();
		ViewDataHolder.getInstance().getSlideScreenPapers().upDateView();
		intentWriter.putExtra("positionPaper",
				latestStaple.getPages().size() - 2);
		intentWriter.putExtra("positionStaples", positionStaple);
		startActivity(intentWriter);

	}

}