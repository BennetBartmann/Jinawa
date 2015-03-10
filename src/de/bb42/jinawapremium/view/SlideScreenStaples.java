package de.bb42.jinawapremium.view;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Surface;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import de.bb42.jinawapremium.R;
import de.bb42.jinawapremium.controller.Controller;
import de.bb42.jinawapremium.controller.datatypes.Staple;

public class SlideScreenStaples extends Activity {

	private static Context context;
	private LinearLayout linearLayoutinScrollView;
	private Button[] buttonsStaple = new Button[200];
	private int stapleSize;
	private HorizontalScrollView scrollView;
	private Dialog dialog;
	private Controller controller = Controller.getInstance();
	private List<Staple> staples = controller.getStapleOfStaples().getStaples();
	private Intent intentSettings;
	private Timer timer = new Timer();;
	private int timeInterval = 85;
	private int delay = 0;
	private int orientation;
	private TextView actPageNumberTextField;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen_slide);
		ViewDataHolder.getInstance().setSlideScreenStaples(this);
		SlideScreenStaples.context = this;
		intentSettings = new Intent(SlideScreenStaples.getContext(),
				Settings.class);
		scrollView = (HorizontalScrollView) findViewById(R.id.horizontalScrollView1);
		scrollView.addView(getLinLayout());
		scrollView.getViewTreeObserver().addOnScrollChangedListener(
				new OnScrollChangedListener() {

					@Override
					public void onScrollChanged() {
						// only set every 5 pixel a new Timer for Performance
						if (delay == 5) {
							setupTimer();
							delay = 0;
						}
						delay++;
					}
				});
		actPageNumberTextField = (TextView) findViewById(R.id.textView1);
		actPageNumberTextField.setText(scrollView.getScrollX()
				/ getStapleWidth() + 1 + "/" + stapleSize);
	}

	public static Context getAppContext() {
		return SlideScreenStaples.getContext();
	}

	public static Context getContext() {
		return context;
	}

	// OnClick goto PaperView
	View.OnClickListener onClickGoToStaple = new View.OnClickListener() {
		public void onClick(View v) {
			Intent intentPaper = new Intent(SlideScreenStaples.context,
					SlideScreenPapers.class);
			int id = ((Button) v).getId();
			intentPaper.putExtra("positionStaple", id);
			startActivity(intentPaper);
		}
	};

	// return Staple Size
	private int getStapleSize() {
		if (staples != null) {
			return controller.getStapleOfStaples().getStaples().size() + 2;
		} else {
			return 0;
		}
	}

	// Updates View
	public void update() {
		scrollView.removeAllViews();
		scrollView.addView(getLinLayout());

	}

	// Creates the LinView to put into Scrollview
	private LinearLayout getLinLayout() {
		stapleSize = getStapleSize();
		linearLayoutinScrollView = new LinearLayout(SlideScreenStaples.this);

		for (int i = 0; i < stapleSize; i++) {
			buttonsStaple[i] = new Button(this);
			buttonsStaple[i].setId(i);
			buttonsStaple[i].setBackgroundResource(R.drawable.staple);
			if (i == stapleSize - 2) {
				buttonsStaple[i].setText(R.string.newStaple);
				buttonsStaple[i].setOnClickListener(onClickNewStaple);
			} else if (i == stapleSize - 1) {
				buttonsStaple[i].setText(R.string.Settings);
				buttonsStaple[i].setOnClickListener(onClickSettings);
			} else {
				buttonsStaple[i].setText(staples.get(i).getTitel());
				buttonsStaple[i].setGravity(Gravity.CENTER_HORIZONTAL);
				buttonsStaple[i].setOnClickListener(onClickGoToStaple);
				buttonsStaple[i].setOnLongClickListener(onLongClickStaple);

			}
			// Trying to set the Titel in die Titlebar
			int navBarHeight = getNavigationBarHeight(this, orientation);
			int screenHeight = getMetrics().heightPixels;
			int hightOnScreenInPixel = (int) ((screenHeight - navBarHeight) / 3);
			buttonsStaple[i].setPadding(0, hightOnScreenInPixel, 0, 0);

			buttonsStaple[i].setLayoutParams(new LinearLayout.LayoutParams(
					getStapleWidth(), (int) LayoutParams.MATCH_PARENT));
			linearLayoutinScrollView.addView(buttonsStaple[i]);

		}
		return linearLayoutinScrollView;
	}

	// OnClickAction Intent for Settings /goto SettingsView
	View.OnClickListener onClickSettings = new View.OnClickListener() {
		public void onClick(View v) {
			startActivity(intentSettings);
		}
	};
	// OnClickAction Intent for NewStaple /goto NewStapleView

	View.OnClickListener onClickNewStaple = new View.OnClickListener() {
		public void onClick(View v) {
			Dialogs DIaDel = new Dialogs(0, 0, SlideScreenStaples.context);
			DIaDel.getNameStaple();
		}
	};
	// OnLONGClickAction Intent for Staples /ShowDialog

	View.OnLongClickListener onLongClickStaple = new View.OnLongClickListener() {
		public boolean onLongClick(View v) {
			int id = ((Button) v).getId();

			onLongClickDialog(id);
			return true;
		}
	};

	// Cancels the old timer and set a new timer with action on run out of time
	private void setupTimer() {
		timer.cancel();
		timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						centre();
					}
				});
			}
		}, timeInterval);
		actPageNumberTextField.setText(scrollView.getScrollX()
				/ getStapleWidth() + 1 + "/" + stapleSize);
	}

	// Snaps in on edges of the Staples
	private void centre() {

		int scroll = scrollView.getScrollX() % getStapleWidth();
		if (scroll <= getStapleWidth() / 2) {
			scrollView.smoothScrollTo(scrollView.getScrollX() - scroll, 0);
		} else {
			scrollView.smoothScrollTo(scrollView.getScrollX()
					+ (getStapleWidth() - scroll), 0);

		}
	}

	// returns the width of one Staple (complete Screen for Portrait 1/3 Screen
	// for Landscape)
	private int getStapleWidth() {
		DisplayMetrics metrics = getMetrics();
		int rotation = getWindowManager().getDefaultDisplay().getRotation();
		if (rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270) {
			orientation = Configuration.ORIENTATION_LANDSCAPE;

			return metrics.widthPixels / 3;

		} else {
			orientation = Configuration.ORIENTATION_PORTRAIT;

			return metrics.widthPixels;
		}
	}

	// retruns Metrics
	private DisplayMetrics getMetrics() {
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		return metrics;
	}

	// Creates Options Dialog for delete and rename
	private void onLongClickDialog(final int id) {
		dialog = new Dialog(SlideScreenStaples.getContext());
		dialog.setContentView(R.layout.longclickdialogstaple);
		dialog.setTitle(R.string.optionsStaple);

		Button deleteButton = (Button) dialog
				.findViewById(R.id.deleteStapleButton);
		deleteButton.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				dialog.dismiss();
				Dialogs DIaDel = new Dialogs(id, 0, SlideScreenStaples.context);
				DIaDel.getDeletDialog();
			}
		});
		Button renameButton = (Button) dialog
				.findViewById(R.id.renameStapleButton);
		renameButton.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				dialog.dismiss();
				Dialogs DIaDel = new Dialogs(id, 0, SlideScreenStaples.context);
				DIaDel.getRenameDialog();

			}
		});
		dialog.show();

	}

	// Intent to start Paper
	public void startPaper(Intent intentPaper) {
		startActivity(intentPaper);
	}

	// returns NavBar Height
	private int getNavigationBarHeight(Context context, int orientation) {
		Resources resources = context.getResources();

		int id = resources
				.getIdentifier(
						orientation == Configuration.ORIENTATION_PORTRAIT ? "navigation_bar_height"
								: "navigation_bar_height_landscape", "dimen",
						"android");
		if (id > 0) {
			return resources.getDimensionPixelSize(id);
		}
		return 0;
	}
}