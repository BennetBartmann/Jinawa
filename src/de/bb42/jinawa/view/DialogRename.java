package de.bb42.jinawa.view;

import de.bb42.jinawa.R;
import de.bb42.jinawa.controller.Controller;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.EditText;

public class DialogRename extends DialogFragment {
	private int position;
	private EditText input = null;
	private String noinput = "";

	public DialogRename(int position) {
		this.position = position;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		// Use the Builder class for convenient dialog construction
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		//this.input = new EditText(SlideScreenStaples.getAppContext());
		builder.setView(input);
		builder.setMessage(R.string.renameStaple);
		builder.setPositiveButton(R.string.Ok,
				new RenameButtonListener());
		builder.setNegativeButton(R.string.Cancel, new DoNothingListener());
		// Create the AlertDialog object and return it
		return builder.create();
	}

	private class RenameButtonListener implements
			DialogInterface.OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			String name = input.getText().toString();
			if (!name.trim().equals(noinput)) {
				Controller.getInstance().getStapleOfStaples().getStaples()
						.get(position).setNewTitel(new StringBuffer(name));
				

			}
		}
	}

}