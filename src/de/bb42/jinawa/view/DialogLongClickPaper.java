package de.bb42.jinawa.view;

import de.bb42.jinawa.R;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DialogLongClickPaper extends DialogFragment {
	private int positionPaper;
	private int positionStaple;

	private Dialog dialog;

	public DialogLongClickPaper(int positionStaple, int positionPaper) {
		this.positionPaper = positionPaper;
		this.positionStaple = positionStaple;

	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		// Use the Builder class for convenient dialog construction
		dialog = new Dialog(SlideScreenPapers.getContext());
		dialog.setContentView(R.layout.longclickdialogpaper);
		dialog.setTitle(R.string.optionsPaper);

		Button deleteButton = (Button) dialog
				.findViewById(R.id.deleteStapleButton);
		deleteButton.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				DialogDeletePaper dialogDelete = new DialogDeletePaper(positionStaple,
						positionPaper);

				dialogDelete.show(getFragmentManager(), getTag());

				dialog.hide();

			}
		});

		
		// now that the dialog is set up, it's time to show it

		return dialog;
	}
}