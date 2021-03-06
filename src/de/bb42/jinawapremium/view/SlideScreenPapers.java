package de.bb42.jinawapremium.view;

import java.io.File;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import de.bb42.jinawapremium.R;
import de.bb42.jinawapremium.controller.Controller;
import de.bb42.jinawapremium.controller.datatypes.Page;
import de.bb42.jinawapremium.controller.datatypes.Staple;

public class SlideScreenPapers extends Activity {
	private static Context context;
	LinearLayout linearLayoutinScrollView;
	HorizontalScrollView scrollView;
	Button[] b = new Button[200];
	private int positionStaples;
	private int paperTitleLenght = 20;
	private int pageSize;
	private Controller controller = Controller.getInstance();
	private List<Page> pages;
	private Bundle bundle;
	private Dialog dialog;
	private Timer t;
	private int delay = 0;
	private int timeInterval = 85;
	private TextView actPageNumberTextField;
	private int orientation;

	private Intent intentWriter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen_slide);
		ViewDataHolder.getInstance().setSlideScreenPapers(this);
		SlideScreenPapers.context = this;
		actPageNumberTextField = (TextView) findViewById(R.id.textView1);
		t = new Timer();
		intentWriter = new Intent(SlideScreenPapers.getContext(), Writer.class);
		Intent IntentStaples = getIntent();
		bundle = IntentStaples.getExtras();
		if (bundle != null) {
			positionStaples = (Integer) bundle.get("positionStaple");
			pages = controller.getStapleOfStaples().getStaples()
					.get(positionStaples).getPages();
			pageSize = pages.size() + 1;
		}
		scrollView = (HorizontalScrollView) findViewById(R.id.horizontalScrollView1);
		scrollView.addView(getLinLayout());
		actPagenumber();

		scrollView.getViewTreeObserver().addOnScrollChangedListener(
				new OnScrollChangedListener() {

					@Override
					public void onScrollChanged() {
						if (delay == 10) {
							setupTimer();
							delay = 0;
						}
						delay++;
					}
				});

	}

	public void actPagenumber() {
		actPageNumberTextField.setText(scrollView.getScrollX()
				/ getStapleWidth() + 1 + "/" + pageSize);
	}

	public static Context getAppContext() {
		return SlideScreenPapers.getContext();
	}

	public static Context getContext() {
		return context;
	}

	public void update() {
		scrollView.removeAllViews();
		scrollView.addView(getLinLayout());

	}

	// returns the width of one Staple (complete Screen for Portrait 1/3 Screen
	// for Landscape)
	private int getStapleWidth() {
		DisplayMetrics metrics = getMetrics();
		int defaultOrientation = getDeviceDefaultOrientation();
		int rotation = getWindowManager().getDefaultDisplay().getRotation();
		if (rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270) {
			orientation = (defaultOrientation == Configuration.ORIENTATION_LANDSCAPE ? Configuration.ORIENTATION_PORTRAIT
					: Configuration.ORIENTATION_LANDSCAPE);
		} else {
			orientation = defaultOrientation;
		}
		if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
			return metrics.widthPixels / 3;
		}
		return metrics.widthPixels;
	}

	public int getDeviceDefaultOrientation() {

		WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

		Configuration config = getResources().getConfiguration();

		int rotation = windowManager.getDefaultDisplay().getRotation();

		if (((rotation == Surface.ROTATION_0 || rotation == Surface.ROTATION_180) && config.orientation == Configuration.ORIENTATION_LANDSCAPE)
				|| ((rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270) && config.orientation == Configuration.ORIENTATION_PORTRAIT)) {
			return Configuration.ORIENTATION_LANDSCAPE;
		} else {
			return Configuration.ORIENTATION_PORTRAIT;
		}
	}// retruns Metrics

	private DisplayMetrics getMetrics() {
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		return metrics;
	}

	private int getStapleSize() {
		List<Page> pages = controller.getStapleOfStaples().getStaples()
				.get(positionStaples).getPages();

		if (pages != null) {
			return pages.size() + 1;
		} else {
			return 0;
		}
	}

	private LinearLayout getLinLayout() {
		pageSize = getStapleSize();
		linearLayoutinScrollView = new LinearLayout(SlideScreenPapers.this);
		for (int i = 0; i < pageSize; i++) {
			b[i] = new Button(this);
			b[i].setId(i);
			Drawable d = getResources().getDrawable(R.drawable.paper);

			d.setBounds(20, 20, 20, 20);

			b[i].setBackgroundResource(R.drawable.paper);

			if (i == pageSize - 1) {
				b[i].setText(R.string.newPaper);
				b[i].setOnClickListener(onClickNewPaper);
			} else {
				b[i].setOnClickListener(onClickGoToPaper);
				Page page = pages.get(i);
				int length = page.getContent().length();
				if (length >= paperTitleLenght) {
					b[i].setText(page.getContent().subSequence(0,
							paperTitleLenght));
				} else if (length == 0) {
					b[i].setText(R.string.emptyPaper);
				} else {
					b[i].setText(page.getContent().subSequence(0, length));
				}
				b[i].setOnLongClickListener(onLongClickPaper);

			}
			b[i].setLayoutParams(new LinearLayout.LayoutParams(
					getStapleWidth(), (int) LayoutParams.MATCH_PARENT));
			linearLayoutinScrollView.addView(b[i]);
		}
		return linearLayoutinScrollView;
	}

	View.OnClickListener onClickNewPaper = new View.OnClickListener() {
		public void onClick(View v) {
			Staple latestStaple = Controller.getInstance().getStapleOfStaples()
					.getStaples().get(positionStaples);
			latestStaple.createNewPage();

			Intent intentWriter = new Intent(context, Writer.class);
			intentWriter.putExtra("positionPaper", (latestStaple.getPages()
					.size() < 1 ? 0 : latestStaple.getPages().size() - 1));
			intentWriter.putExtra("positionStaples", positionStaples);
			startActivity(intentWriter);

		}
	};
	View.OnClickListener onClickGoToPaper = new View.OnClickListener() {
		public void onClick(View v) {
			int positionPaper = ((Button) v).getId();
			intentWriter.putExtra("positionPaper", positionPaper);
			intentWriter.putExtra("positionStaples", positionStaples);
			startActivity(intentWriter);
		}
	};
	View.OnLongClickListener onLongClickPaper = new View.OnLongClickListener() {
		public boolean onLongClick(View v) {
			int positionPaper = ((Button) v).getId();

			onLongClickDialog(positionPaper);
			return true;
		}
	};

	private void setupTimer() {
		t.cancel();
		t = new Timer();
		t.schedule(new TimerTask() {

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
				/ getStapleWidth() + 1 + "/" + pageSize);
	}

	private void centre() {

		int scroll = scrollView.getScrollX() % getStapleWidth();
		if (scroll <= getStapleWidth() / 2) {
			scrollView.smoothScrollTo(scrollView.getScrollX() - scroll, 0);
		} else {
			scrollView.smoothScrollTo(scrollView.getScrollX()
					+ (getStapleWidth() - scroll), 0);

		}
		actPagenumber();

	}

	private void onLongClickDialog(final int positionPaper) {
		dialog = new Dialog(SlideScreenPapers.getContext());
		dialog.setContentView(R.layout.longclickdialogpaper);
		dialog.setTitle(R.string.optionsPaper);

		Button deleteButton = (Button) dialog
				.findViewById(R.id.deletePaperButton);
		deleteButton.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				dialog.dismiss();
				Dialogs DIaDel = new Dialogs(positionStaples, positionPaper,
						SlideScreenPapers.context);
				DIaDel.getDeleteDialogPaper();
			}
		});
		Button shareButton = (Button) dialog.findViewById(R.id.shareButton);
		shareButton.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				dialog.dismiss();
				shareFile(positionPaper);
			}

		});
		Button shareTextButton = (Button) dialog
				.findViewById(R.id.shareTextButton);
		shareTextButton.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				dialog.dismiss();
				shareText(positionPaper);
			}

		});

		dialog.show();

	}

	private void shareFile(int positionPaper) {
		File f = new File(controller.getStapleOfStaples().getStaples()
				.get(positionStaples).getPages().get(positionPaper)
				.getFileAdress());
		Uri u = Uri.fromFile(f);
		String ext = MimeTypeMap.getFileExtensionFromUrl(u.toString());
		String mime = null;
		if (ext != null) {
			mime = MimeTypeMap.getSingleton().getMimeTypeFromExtension(ext);
		}
		Intent i = new Intent();
		i.setAction(Intent.ACTION_SEND);
		i.putExtra(Intent.EXTRA_STREAM, u);
		i.setType(mime != null ? mime : "text/plain");
		startActivity(Intent.createChooser(i, "Share Text"));

	}

	private void shareText(int positionPaper) {

		Intent sendIntent = new Intent();
		sendIntent.setAction(Intent.ACTION_SEND);
		sendIntent.putExtra(Intent.EXTRA_TEXT, controller.getStapleOfStaples()
				.getStaples().get(positionStaples).getPages()
				.get(positionPaper).getContent().toString());
		sendIntent.setType("text/plain");
		startActivity(sendIntent);

	}
}