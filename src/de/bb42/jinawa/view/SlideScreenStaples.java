package de.bb42.jinawa.view;

import java.util.List;

import de.bb42.jinawa.R;
import de.bb42.jinawa.controller.Controller;
import de.bb42.jinawa.controller.datatypes.Staple;
import de.bb42.jinawa.controller.datatypes.StapleOfStaples;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class SlideScreenStaples extends Activity implements OnClickListener {

	private static Context context;
	RelativeLayout rl1;
	private String noinput = "";

	LinearLayout rl2;
	HorizontalScrollView sv;
	Button[] b = new Button[200];
	int stapleSize;
	int sum = 30;
	private Controller controller = Controller.getInstance();
	private List<Staple> staples = controller.getStapleOfStaples().getStaples();
	private StapleOfStaples staple = Controller.getInstance()
			.getStapleOfStaples();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		rl1 = (RelativeLayout) findViewById(R.id.rl);
		sv = new HorizontalScrollView(SlideScreenStaples.this);
		rl2 = new LinearLayout(SlideScreenStaples.this);
		if (staples != null) {
			stapleSize = staples.size() + 2;
		}
		for (int i = 1; i <= stapleSize; i++) {
			b[i] = new Button(this);
			b[i].setId(i);
			b[i].setBackgroundResource(R.drawable.staple);
			if (i == stapleSize - 1) {
				b[i].setText(R.string.newStaple);
				b[i].setOnClickListener(new OnClickListener() {
					public void onClick(View arg0) {
						dialog();
					}
				});
			} else if (i == stapleSize) {
				b[i].setText(R.string.Settings);
			} else {
				b[i].setText(staples.get(i).getTitel());
				b[i].setOnClickListener(this);
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
		return SlideScreenStaples.getContext();
	}

	public static Context getContext() {
		return context;
	}

	@Override
	public void onClick(View v) {
		Intent intentPaper = new Intent(this, SlideScreenPaper.class);
		int id = ((Button) v).getId();
		intentPaper.putExtra("positionStaple", id);
		startActivity(intentPaper);
	}

	private void dialog() {
		final EditText input = new EditText(this);

		new AlertDialog.Builder(SlideScreenStaples.this)
				.setTitle("Update Status")
				.setMessage("bzu")
				.setView(input)
				.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						String name = input.getText().toString();
						if (!name.trim().equals(noinput)) {
							staple.createNewStaple(new StringBuffer(name));
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
}