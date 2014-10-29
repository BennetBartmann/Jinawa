package de.bb42.jinawa.view;

import de.bb42.jinawa.R;
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
public class FragmentNewStaple extends Fragment implements View.OnClickListener {

	private View view;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.fragment_screen_slide_page, container,
				false);

		Button mButton = (Button) view.findViewById(R.id.button1);
		mButton.setText(R.string.newStaple);
		mButton.setBackgroundResource(R.drawable.staple2);
		mButton.setOnClickListener(this);

		return view;
	}

	@Override
	public void onClick(View arg0) {
		DialogInput dialog = new DialogInput();
		dialog.show(getChildFragmentManager(), getTag());
	}

}