package de.bb42.jinawa.view;

import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Surface;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import de.bb42.jinawa.R;
import de.bb42.jinawa.controller.Controller;
import de.bb42.jinawa.controller.datatypes.Page;
import de.bb42.jinawa.controller.datatypes.Staple;

public class SlideScreenPapers extends Activity {
	private static Context context;
	LinearLayout linearLayoutinScrollView;
	HorizontalScrollView sv;
	Button[] b = new Button[200];
	private int positionStaples;
	private int paperTitleLenght = 20;
	private int pageSize;
	private Controller controller = Controller.getInstance();
	private List<Page> pages;
	private Bundle bundle;
	private Dialog dialog;
	private Intent intentWriter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen_slide);
		ViewDataHolder.getInstance().setSlideScreenPapers(this);
		SlideScreenPapers.context = this;
		intentWriter = new Intent(SlideScreenPapers.getContext(), Writer.class);
		Intent IntentStaples = getIntent();
		bundle = IntentStaples.getExtras();
		if (bundle != null) {
			positionStaples = (Integer) bundle.get("positionStaple");
			pages = controller.getStapleOfStaples().getStaples()
					.get(positionStaples).getPages();
			pageSize = pages.size() + 1;
		}
		sv = (HorizontalScrollView) findViewById(R.id.horizontalScrollView1);

		sv.addView(getLinLayout());
	}

	public static Context getAppContext() {
		return SlideScreenPapers.getContext();
	}

	public static Context getContext() {
		return context;
	}

	public void update() {
		sv.removeAllViews();
		sv.addView(getLinLayout());

	}

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
					getScreenWidth(), (int) LayoutParams.MATCH_PARENT));
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

	private void onLongClickDialog(final int positionPaper) {
		dialog = new Dialog(SlideScreenPapers.getContext());
		dialog.setContentView(R.layout.longclickdialogpaper);
		dialog.setTitle(R.string.optionsStaple);

		Button deleteButton = (Button) dialog
				.findViewById(R.id.deletePaperButton);
		deleteButton.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				dialog.dismiss();
				DialogDelete DIaDel = new DialogDelete(positionStaples,
						positionPaper, SlideScreenPapers.context);
				DIaDel.getDeletDialog2();
			}
		});

		dialog.show();

	}
}