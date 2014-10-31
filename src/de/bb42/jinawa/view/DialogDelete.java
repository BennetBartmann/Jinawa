package de.bb42.jinawa.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import de.bb42.jinawa.R;
import de.bb42.jinawa.controller.Controller;

public class DialogDelete extends DialogFragment {
	private int position;

	public DialogDelete(int position) {
		this.position = position;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		// Use the Builder class for convenient dialog construction
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(R.string.deleteStaple);
		builder.setMessage(R.string.deleteStapleText);
		builder.setPositiveButton(R.string.Ok, new DeleteButtonListener());
		builder.setNegativeButton(R.string.Cancel, new DoNothingListener());
		// Create the AlertDialog object and return it
		return builder.create();
	}

	private class DeleteButtonListener implements
			DialogInterface.OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int which) {

			Controller.getInstance().getStapleOfStaples().delete(position);
			ViewDataHolder.getInstance().getSlideScreenStaples().upDateView();

		}
	}

}