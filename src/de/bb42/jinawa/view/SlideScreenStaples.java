package de.bb42.jinawa.view;

import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.OrientationEventListener;
import android.view.Surface;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.Toast;
import de.bb42.jinawa.R;
import de.bb42.jinawa.controller.Controller;
import de.bb42.jinawa.controller.datatypes.Staple;

public class SlideScreenStaples extends Activity {

	protected static final int THRESHOLD = 15;
	private static Context context;
	private LinearLayout linearLayoutinScrollView;
	private Button[] buttonsStaple = new Button[200];
	private int stapleSize;
	private HorizontalScrollView scrollView;
	private Dialog dialog;
	private int divTimes = 1;
	private Controller controller = Controller.getInstance();
	private List<Staple> staples = controller.getStapleOfStaples().getStaples();
	private Intent intentSettings;

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
		scrollView.removeAllViews();
		scrollView.addView(getLinLayout());

	}

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
				buttonsStaple[i].setOnClickListener(onClickGoToStaple);
				buttonsStaple[i].setOnLongClickListener(onLongClickStaple);
			}

			buttonsStaple[i].setLayoutParams(new LinearLayout.LayoutParams(
					getScreenWidth() / divTimes,
					(int) LayoutParams.MATCH_PARENT));
			linearLayoutinScrollView.addView(buttonsStaple[i]);
		}
		return linearLayoutinScrollView;
	}

	View.OnClickListener onClickSettings = new View.OnClickListener() {
		public void onClick(View v) {
			startActivity(intentSettings);
		}
	};
	View.OnClickListener onClickNewStaple = new View.OnClickListener() {
		public void onClick(View v) {
			DialogDelete DIaDel = new DialogDelete(0,
					SlideScreenStaples.context);
			DIaDel.getNameStaple();
		}
	};
	View.OnLongClickListener onLongClickStaple = new View.OnLongClickListener() {
		public boolean onLongClick(View v) {
			int id = ((Button) v).getId();

			onLongClickDialog(id);
			return true;
		}
	};

	private int getScreenWidth() {
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		if (getWindowManager().getDefaultDisplay().getRotation() == Surface.ROTATION_90
				|| getWindowManager().getDefaultDisplay().getRotation() == Surface.ROTATION_270) {
			return metrics.widthPixels / 3;

		} else {

			return metrics.widthPixels;
		}
	}

	private void onLongClickDialog(final int id) {
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

	public void startPaper(Intent intentPaper) {
		startActivity(intentPaper);
	}

}