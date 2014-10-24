package de.bb42.jinawa.view;

import de.bb42.jinawa.R;
import de.bb42.jinawa.controller.Controller;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.EditText;

public class InputDialog extends DialogFragment {
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		// Use the Builder class for convenient dialog construction
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		final EditText input = new EditText(SlideScreenStaples.getAppContext());
		builder.setView(input);
		builder.setMessage(R.string.inputText)
				.setPositiveButton(R.string.Ok,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								String text = input.getText().toString();
								if (!text.equals("")) {
									Controller
											.getInstance()
											.getStapleOfStaples()
											.createNewStaple(
													new StringBuffer(text));
									//SlideScreenStaples.getInstance()			.upDateData();

									SlideScreenStaples.getInstance()							.upDateView();
								}
							}
						})
				.setNegativeButton(R.string.Cancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// User cancelled the dialog
							}
						});
		// Create the AlertDialog object and return it
		return builder.create();
	}
}