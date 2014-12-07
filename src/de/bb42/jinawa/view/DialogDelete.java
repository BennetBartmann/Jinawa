package de.bb42.jinawa.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;
import de.bb42.jinawa.R;
import de.bb42.jinawa.controller.Controller;

public class DialogDelete {
	private int position;
	private Context context;
	private String noinput = "";
	private EditText input;

	public DialogDelete(int position, Context context) {
		this.position = position;
		this.context = context;
	}

	public void getDeletDialog() {

		// Use the Builder class for convenient dialog construction
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(R.string.deleteStaple);
		builder.setMessage(R.string.deleteStapleText);
		builder.setPositiveButton(R.string.Ok, new DeleteButtonListener());
		builder.setNegativeButton(R.string.Cancel, new DoNothingListener());
		// Create the AlertDialog object and return it
		Dialog dia = builder.create();
		dia.show();
	}

	public class DeleteButtonListener implements
			DialogInterface.OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int which) {

			Controller.getInstance().getStapleOfStaples().delete(position);
			ViewDataHolder.getInstance().getSlideScreenStaples().update();
		}
	}

	public void getRenameDialog() {
		input = new EditText(context);

		// Use the Builder class for convenient dialog construction
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		// this.input = new EditText(SlideScreenStaples.getAppContext());
		builder.setView(input);
		builder.setMessage(R.string.renameStaple);
		builder.setPositiveButton(R.string.Ok, new RenameButtonListener());
		builder.setNegativeButton(R.string.Cancel, new DoNothingListener());
		// Create the AlertDialog object and return it
		Dialog dia = builder.create();
		dia.show();
	}

	private class RenameButtonListener implements
			DialogInterface.OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			String name = input.getText().toString();
			if (!name.trim().equals(noinput)) {
				Controller.getInstance().getStapleOfStaples().getStaples()
						.get(position).setNewTitel(new StringBuffer(name));
				ViewDataHolder.getInstance().getSlideScreenStaples().update();

			}
		}
	}

}