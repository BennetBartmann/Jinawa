package de.bb42.jinawa.view;

import java.util.List;

import de.bb42.jinawa.R;
import de.bb42.jinawa.controller.Controller;
import de.bb42.jinawa.controller.datatypes.Page;
import de.bb42.jinawa.controller.datatypes.Staple;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class SlideScreenPaper extends Activity {
	private static Context context;

	RelativeLayout rl1;
	LinearLayout rl2;
	HorizontalScrollView sv;
	Button[] b = new Button[200];
	private int positionStaples;
	private int paperTitleLenght = 20;
	private int stapleSize;
	int sum = 30;
	private Controller controller = Controller.getInstance();
	private List<Page> pages;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Intent IntentStaples = getIntent();
		Bundle bundle = IntentStaples.getExtras();
		if (bundle != null) {
			positionStaples = (Integer) bundle.get("positionStaple");
			pages = controller.getStapleOfStaples().getStaples()
					.get(positionStaples).getPages();
			stapleSize = pages.size() + 1;
		}

		rl1 = (RelativeLayout) findViewById(R.id.rl);
		sv = new HorizontalScrollView(SlideScreenPaper.this);
		rl2 = new LinearLayout(SlideScreenPaper.this);

		for (int i = 1; i <= stapleSize; i++) {
			b[i] = new Button(this);
			b[i].setId(i);
			b[i].setBackgroundResource(R.drawable.staple);
			if (i == stapleSize) {
				b[i].setText(R.string.newPaper);
			} else {
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
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					(int) LayoutParams.WRAP_CONTENT,
					(int) LayoutParams.MATCH_PARENT);
			params.height = 600;
			params.width = 300;

			b[i].setLayoutParams(params);
			rl2.addView(b[i]);
			sum = sum + 300;
		}

		sv.addView(rl2);
		rl1.addView(sv);
	}

	public static Context getAppContext() {
		return SlideScreenPaper.getContext();
	}

	public static Context getContext() {
		return context;
	}

}