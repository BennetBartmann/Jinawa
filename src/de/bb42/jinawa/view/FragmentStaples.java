package de.bb42.jinawa.view;

import de.bb42.jinawa.R;
import de.bb42.jinawa.controller.Controller;
import de.bb42.jinawa.controller.datatypes.Staple;
import de.bb42.jinawa.controller.datatypes.StapleOfStaples;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

/**
 * Fragment for Staples
 * 
 * @author Johannes Becker
 * 
 */
public class FragmentStaples extends Fragment implements View.OnClickListener {
	private View view;
	private Staple stapleData;
	private int position;
	private Dialog dialog;

	public FragmentStaples(Staple stapleData, int position) {
		this.stapleData = stapleData;
		this.position = position;

	}

	Intent intentPaper = new Intent(SlideScreenStaples.getContext(),
			SlideScreenPapers.class);

	/**
	 * Create and return View
	 * 
	 * @param inflater
	 *            ?
	 * @param container
	 *            ?
	 * @param savedInstanceState
	 *            ?
	 * @return View
	 */
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_screen_slide_page, container,
				false);
		Button mButton = (Button) view.findViewById(R.id.button1);

		mButton.setText(stapleData.getTitel());
		mButton.setId(position);
		mButton.setBackgroundResource(R.drawable.staple2);
		mButton.setOnClickListener(this);
		mButton.setOnLongClickListener(new OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				dialog = new Dialog(SlideScreenStaples.getContext());
				dialog.setContentView(R.layout.dialogdelete);
				dialog.setTitle("StaplesOptionen");
				dialog.setCancelable(true);
				// there are a lot of settings, for dialog, check them all out!
				// set up radiobutton

				Button rd1 = (Button) dialog.findViewById(R.id.rd1);
				rd1.setOnClickListener(new OnClickListener() {

					public void onClick(View arg0) {
						new AlertDialog.Builder(SlideScreenStaples.getContext())
								.setTitle("Rly delete?")
								.setMessage("Delet?")
								.setPositiveButton("Ok",
										new DialogInterface.OnClickListener() {
											public void onClick(
													DialogInterface dialog,
													int whichButton) {
												Controller.getInstance()
														.getStapleOfStaples()
														.delete(position);
												ViewDataHolder
														.getInstance()
														.getSlideScreenStaples()
														.upDateView();

											}
										})
								.setNegativeButton("Cancel",
										new DialogInterface.OnClickListener() {
											public void onClick(
													DialogInterface dialog,
													int whichButton) {
												// Do nothing.
											}
										}).show();
						dialog.dismiss();

					}
				});
				Button rd3 = (Button) dialog.findViewById(R.id.cancle);
				rd3.setOnClickListener(new OnClickListener() {

					public void onClick(View arg0) {
						dialog.dismiss();
					}
				});
				Button rd2 = (Button) dialog.findViewById(R.id.rd2);
				rd2.setOnClickListener(new OnClickListener() {
					final EditText input = new EditText(SlideScreenStaples
							.getContext());

					public void onClick(View arg0) {
						new AlertDialog.Builder(SlideScreenStaples.getContext())
								.setTitle("Update Status")
								.setMessage("Rename")
								.setView(input)
								.setPositiveButton("Ok",
										new DialogInterface.OnClickListener() {
											public void onClick(
													DialogInterface dialog,
													int whichButton) {
												Editable value = input
														.getText();
												Controller
														.getInstance()
														.getStapleOfStaples()
														.getStaples()
														.get(position)
														.setNewTitel(
																new StringBuffer(
																		value));
												ViewDataHolder
														.getInstance()
														.getSlideScreenStaples()
														.upDateView();

											}
										})
								.setNegativeButton("Cancel",
										new DialogInterface.OnClickListener() {
											public void onClick(
													DialogInterface dialog,
													int whichButton) {
												// Do nothing.
											}
										}).show();
						dialog.dismiss();

					}
				});
				// now that the dialog is set up, it's time to show it
				dialog.show();

				return true;
			}
		});
		return view;
	}

	/**
	 * Starts new Activity
	 * 
	 * @param v
	 *            ?
	 */
	@Override
	public void onClick(View v) {
		int id = ((Button) v).getId();

		intentPaper.putExtra("positionStaple", id);
		startActivity(intentPaper);

	}

}