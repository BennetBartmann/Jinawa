package de.bb42.jinawa.view;

import de.bb42.jinawa.R;
import de.bb42.jinawa.controller.datatypes.Staple;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class SlideScreenStaples extends Activity implements OnClickListener {
	private static Context context;

	RelativeLayout rl1;
	LinearLayout rl2;
	HorizontalScrollView sv;
	Button[] b;
	int sum = 30;
	Intent intentPaper = new Intent();
	private Staple stapleData;
	private int position;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		rl1 = (RelativeLayout) findViewById(R.id.rl);
		sv = new HorizontalScrollView(SlideScreenStaples.this);
		rl2 = new LinearLayout(SlideScreenStaples.this);
		b = new Button[20];

		for (int i = 1; i < 15; i++) {
			b[i] = new Button(this);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					(int) LayoutParams.WRAP_CONTENT,
					(int) LayoutParams.MATCH_PARENT);
			params.height = 600;
			params.width = 300;
			b[i].setText(stapleData.getTitel());
			b[i].setId(i);
			b[i].setBackgroundResource(R.drawable.staple);
			b[i].setOnClickListener(this);
			b[i].setOnLongClickListener(new OnLongClickListener() {
				public boolean onLongClick(View v) {
					DialogLongClickStaples dialogLongClick = new DialogLongClickStaples(
							i);
					dialogLongClick.show(getFragmentManager(), "buik");

					return true;
				}
			});
			b[i].setText("Button " + i);
			b[i].setLayoutParams(params);
			rl2.addView(b[i]);
			sum = sum + 300;
		}

		sv.addView(rl2);
		rl1.addView(sv);
	}

	public static Context getAppContext() {
		return SlideScreenStaples.getContext();
	}

	public static Context getContext() {
		return context;
	}

	@Override
	public void onClick(View v) {
		int id = ((Button) v).getId();
		intentPaper.putExtra("positionStaple", id);
		startActivity(intentPaper);

	}
}