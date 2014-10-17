package de.bb42.jinawa.view;

import de.bb42.jinawa.R;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentStaples extends Fragment implements View.OnClickListener {
	View view;

	Intent intentPaper = new Intent(SlideScreenStaples.getAppContext(),
			SlideScreenPapers.class);

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.fragment_screen_slide_page, container,
				false);

		Button mButton = (Button) view.findViewById(R.id.button1);
		mButton.setBackgroundResource(R.drawable.staple);
		mButton.setOnClickListener(this);

		return view;
	}

	@Override
	public void onClick(View v) {

		startActivity(intentPaper);
	}
}