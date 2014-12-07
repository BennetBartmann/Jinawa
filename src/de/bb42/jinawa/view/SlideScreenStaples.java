package de.bb42.jinawa.view;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import de.bb42.jinawa.R;
import de.bb42.jinawa.controller.Controller;
import de.bb42.jinawa.controller.datatypes.Staple;
import de.bb42.jinawa.controller.datatypes.StapleOfStaples;

public class SlideScreenStaples extends Activity {

	private static Context context;
	private String noinput = "";

	LinearLayout rl2;
	Button[] b = new Button[200];
	int stapleSize;
	HorizontalScrollView sv;
	int sum = 30;
	Dialog dialog;
	private Controller controller = Controller.getInstance();
	private List<Staple> staples = controller.getStapleOfStaples().getStaples();
	private StapleOfStaples staple = Controller.getInstance()
			.getStapleOfStaples();
	private Intent intentSettings;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen_slide);
		ViewDataHolder.getInstance().setSlideScreenStaples(this);
		SlideScreenStaples.context = this;
		intentSettings = new Intent(SlideScreenStaples.getContext(),
				Settings.class);
		sv = (HorizontalScrollView) findViewById(R.id.horizontalScrollView1);

		sv.addView(getLinLayout());
	}

	public static Context getAppContext() {
		return SlideScreenStaples.getContext();
	}

	public static Context getContext() {
		return context;
	}

	View.OnClickListener onClickGoToStaple = new View.OnClickListener() {
		public void onClick(View v) {
			Intent intentPaper = new Intent(SlideScreenStaples.context,
					SlideScreenPapers.class);
			int id = ((Button) v).getId();
			intentPaper.putExtra("positionStaple", id);
			startActivity(intentPaper);
		}
	};

	private int getStapleSize() {
		if (staples != null) {
			return controller.getStapleOfStaples().getStaples().size() + 2;
		} else {
			return 0;
		}
	}

	public void update() {
		sv.removeAllViews();
		sv.addView(getLinLayout());

	}

	private LinearLayout getLinLayout() {
		stapleSize = getStapleSize();
		rl2 = new LinearLayout(SlideScreenStaples.this);
		for (int i = 0; i < stapleSize; i++) {
			b[i] = new Button(this);
			b[i].setId(i);
			b[i].setBackgroundResource(R.drawable.staple);
			if (i == stapleSize - 2) {
				b[i].setText(R.string.newStaple);
				b[i].setOnClickListener(onClickNewStaple);
			} else if (i == stapleSize - 1) {
				b[i].setText(R.string.Settings);
				b[i].setOnClickListener(onClickSettings);

			} else {
				b[i].setText(staples.get(i).getTitel());
				b[i].setOnClickListener(onClickGoToStaple);
				b[i].setOnLongClickListener(onLongClickStaple);
			}

			b[i].setLayoutParams(new LinearLayout.LayoutParams(
					getScreenWidth(), (int) LayoutParams.MATCH_PARENT));
			rl2.addView(b[i]);
		}
		return rl2;
	}

	private void dialog() {
		final EditText input = new EditText(this);

		new AlertDialog.Builder(SlideScreenStaples.this)
				.setTitle(R.string.newStaple)
				.setMessage(R.string.inputText)
				.setView(input)
				.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						String name = input.getText().toString();
						if (!name.trim().equals(noinput)) {
							staple.createNewStaple(new StringBuffer(name));
							Intent intentPaper = new Intent(SlideScreenStaples
									.getContext(), SlideScreenPapers.class);
							intentPaper.putExtra("positionStaple", (staple
									.getStaples().size() == 0 ? 0 : staple
									.getStaples().size() - 1));
							update();
							startActivity(intentPaper);
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

	View.OnClickListener onClickSettings = new View.OnClickListener() {
		public void onClick(View v) {
			startActivity(intentSettings);
		}
	};
	View.OnClickListener onClickNewStaple = new View.OnClickListener() {
		public void onClick(View v) {
			dialog();
		}
	};
	View.OnLongClickListener onLongClickStaple = new View.OnLongClickListener() {
		public boolean onLongClick(View v) {
			int id = ((Button) v).getId();

			displayAlert(id);
			return true;
		}
	};

	private int getScreenWidth() {
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		return metrics.widthPixels;
	}

	private void displayAlert(final int id) {
		dialog = new Dialog(SlideScreenStaples.getContext());
		dialog.setContentView(R.layout.longclickdialogstaple);
		dialog.setTitle(R.string.optionsStaple);

		Button deleteButton = (Button) dialog
				.findViewById(R.id.deleteStapleButton);
		deleteButton.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				dialog.dismiss();
				DialogDelete DIaDel = new DialogDelete(id,
						SlideScreenStaples.context);
				DIaDel.getDeletDialog();
			}
		});
		Button renameButton = (Button) dialog
				.findViewById(R.id.renameStapleButton);
		renameButton.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				dialog.dismiss();
				DialogDelete DIaDel = new DialogDelete(id,
						SlideScreenStaples.context);
				DIaDel.getRenameDialog();

			}
		});
		dialog.show();

	}
}