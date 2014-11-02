package de.bb42.jinawa.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.EditText;
import de.bb42.jinawa.R;
import de.bb42.jinawa.controller.Controller;
import de.bb42.jinawa.controller.datatypes.StapleOfStaples;

public class DialogInput extends DialogFragment {
	private EditText input = null;
	private String noinput = "";

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		// Use the Builder class for convenient dialog construction
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		this.input = new EditText(SlideScreenStaples.getAppContext());
		builder.setView(input);
		builder.setMessage(R.string.inputText);
		builder.setPositiveButton(R.string.Ok,
				new NewStapleNameDialogListener());
		builder.setNegativeButton(R.string.Cancel, new DoNothingListener());
		// Create the AlertDialog object and return it
		return builder.create();
	}

	private class NewStapleNameDialogListener implements
			DialogInterface.OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			String name = input.getText().toString();
			StapleOfStaples staple = Controller.getInstance()
					.getStapleOfStaples();
			SlideScreenStaples slideScreen = ViewDataHolder.getInstance()
					.getSlideScreenStaples();

			if (!name.trim().equals(noinput)) {
				staple.createNewStaple(new StringBuffer(name));
				slideScreen.upDateView();
				Intent intentPaper = new Intent(
						SlideScreenStaples.getContext(),
						SlideScreenPapers.class);
				intentPaper.putExtra("positionStaple", (staple.getStaples()
						.size() == 0 ? 0 : staple.getStaples().size() - 1));
				startActivity(intentPaper);
			}
		}
	}

}