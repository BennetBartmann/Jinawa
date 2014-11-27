package de.bb42.jinawa.view;

import de.bb42.jinawa.R;
import android.app.Dialog;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DialogLongClickStaples extends Dialog {
	private int position;
	private Dialog dialog;

	public DialogLongClickStaples(int position) {
		this.position = position;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		// Use the Builder class for convenient dialog construction
		dialog = new Dialog(SlideScreenStaples.getContext());
		dialog.setContentView(R.layout.longclickdialogstaple);
		dialog.setTitle(R.string.optionsStaple);

		Button deleteButton = (Button) dialog
				.findViewById(R.id.deleteStapleButton);
		deleteButton.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				DialogDelete dialogDelete = new DialogDelete(position);

				dialogDelete.show(getFragmentManager(), getTag());

				dialog.hide();

			}
		});

		Button renameButton = (Button) dialog
				.findViewById(R.id.renameStapleButton);
		renameButton.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				DialogRename dialogRename = new DialogRename(position);
				dialogRename.show(getFragmentManager(), getTag());
				dialog.hide();

			}
		});
		// now that the dialog is set up, it's time to show it

		return dialog;
	}

}