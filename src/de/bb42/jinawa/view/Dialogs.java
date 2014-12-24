package de.bb42.jinawa.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.EditText;
import de.bb42.jinawa.R;
import de.bb42.jinawa.controller.Controller;
import de.bb42.jinawa.controller.datatypes.StapleOfStaples;

public class Dialogs extends Activity {
	private int positionPaper;
	private int positionStaple;

	private Context context;
	private String noinput = "";
	private EditText input;
	private StapleOfStaples staple = Controller.getInstance()
			.getStapleOfStaples();

	public Dialogs(int positionStaple, int positionPaper, Context context) {
		this.positionPaper = positionPaper;
		this.positionStaple = positionStaple;

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

			Controller.getInstance().getStapleOfStaples()
					.delete(positionStaple);
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
						.get(positionStaple)
						.setNewTitel(new StringBuffer(name));
				ViewDataHolder.getInstance().getSlideScreenStaples().update();

			}
		}
	}

	public void getNameStaple() {
		input = new EditText(context);

		new AlertDialog.Builder(context)
				.setTitle(R.string.newStaple)
				.setMessage(R.string.inputText)
				.setView(input)
				.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						String name = input.getText().toString();
						if (!name.trim().equals(noinput)) {
							staple.createNewStaple(new StringBuffer(name));
							Intent intentPaper = new Intent(context,
									SlideScreenPapers.class);
							intentPaper.putExtra("positionStaple", (staple
									.getStaples().size() == 0 ? 0 : staple
									.getStaples().size() - 1));
							ViewDataHolder.getInstance()
									.getSlideScreenStaples().update();
							ViewDataHolder.getInstance()
									.getSlideScreenStaples()
									.startPaper(intentPaper);
						} else {

							// Fehlermeldung
						}
					}
				})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								// Do nothing.
							}
						}).show();
	}

	public void getDeleteDialogStaple() {

		// Use the Builder class for convenient dialog construction
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(R.string.deleteStaple);
		builder.setMessage(R.string.deleteStapleText);
		builder.setPositiveButton(R.string.Ok, new DeleteButtonListenerStaple());
		builder.setNegativeButton(R.string.Cancel, new DoNothingListener());
		// Create the AlertDialog object and return it
		Dialog dia = builder.create();
		dia.show();
	}

	public class DeleteButtonListenerStaple implements
			DialogInterface.OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int which) {

			Controller.getInstance().getStapleOfStaples().getStaples()
					.get(positionStaple).deletePaper(positionPaper);
			ViewDataHolder.getInstance().getSlideScreenPapers().update();
		}
	}

	public class DoNothingListener implements DialogInterface.OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			// Does literally nothing.
		}

	}
}