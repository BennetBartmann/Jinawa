package de.bb42.jinawa.view;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import de.bb42.jinawa.R;
import de.bb42.jinawa.controller.Controller;
import de.bb42.jinawa.controller.datatypes.Page;
import de.bb42.jinawa.controller.datatypes.Staple;

public class SlideScreenPapers extends Activity {
	private static Context context;
	RelativeLayout rl1;
	LinearLayout rl2;
	HorizontalScrollView sv;
	Button[] b = new Button[200];
	private int positionStaples;
	private int paperTitleLenght = 20;
	private int pageSize;
	private Controller controller = Controller.getInstance();
	private List<Page> pages;
	private Bundle bundle;
	private Intent intentWriter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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
		rl1 = (RelativeLayout) findViewById(R.id.rl);
		sv = new HorizontalScrollView(SlideScreenPapers.this);
		rl2 = new LinearLayout(SlideScreenPapers.this);

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
				b[i].setOnLongClickListener(new OnLongClickListener() {
					public boolean onLongClick(View v) {

						return true;
					}
				});
			}
			DisplayMetrics metrics = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(metrics);
			int width = metrics.widthPixels;
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					width / 2, (int) LayoutParams.MATCH_PARENT);

			b[i].setLayoutParams(params);
			rl2.addView(b[i]);
			
		}

		sv.addView(rl2);
		rl1.addView(sv);
	}

	public static Context getAppContext() {
		return SlideScreenPapers.getContext();
	}

	public static Context getContext() {
		return context;
	}

	public void update() {
		
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
			int id = ((Button) v).getId();
			intentWriter.putExtra("positionPaper", id);
			intentWriter.putExtra("positionStaples", positionStaples);
			startActivity(intentWriter);
		}
	};
}