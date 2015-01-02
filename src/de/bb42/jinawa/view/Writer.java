package de.bb42.jinawa.view;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import de.bb42.jinawa.R;
import de.bb42.jinawa.controller.Controller;
import de.bb42.jinawa.controller.datatypes.Page;

/**
 * Writer Activity
 * 
 * @author Johannes Becker
 * 
 */
public class Writer extends Activity {
	private static Controller controller = Controller.getInstance();
	private int positionPaper;
	private int positionStaples;
	private String noinput = "";
	List<String> info = new ArrayList<String>();
	TextView textView;
	private int count = 0;
	private Page page;

	/**
	 * @param savedInstanceState
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.writer);
		Intent IntentStaples = getIntent();
		Bundle b = IntentStaples.getExtras();
		if (b != null) {
			positionPaper = (Integer) b.get("positionPaper");
			positionStaples = (Integer) b.get("positionStaples");
			page = controller.getStapleOfStaples().getStaples()
					.get(positionStaples).getPages().get(positionPaper);

		} else {
			// error
		}
		final EditText editText = (EditText) findViewById(R.id.editText);
		String text = page.getContent().toString();
		editText.setText(text);
		editText.addTextChangedListener(new ContentChangedListener(editText));
		textView = (TextView) findViewById(R.id.infoBar);
		updateInfo();
	}

	public void toggleInfo(View v) {
		count++;
		if (count == info.size()) {
			count = 0;
		}

		textView.setText(info.get(count));

	}

	public void updateInfo() {
		info.clear();
		info.add(getString(R.string.countChar) + "" + page.countCharacters());
		info.add(getString(R.string.countCharWithoutWhitespaces) + ""
				+ page.countCharactersWithOutWhitespace());
		textView.setText(info.get(count));

	}

	public void onBackPressed() {
		if (page.getContent().toString().trim().equals(noinput)) {
			Controller.getInstance().getStapleOfStaples().getStaples()
					.get(positionStaples).deletePaper(positionPaper);
			ViewDataHolder.getInstance().getSlideScreenPapers().update();

		} else {
			page.getContent().toString().trim();
			page.save();
			ViewDataHolder.getInstance().getSlideScreenPapers().update();
		}

		super.onBackPressed();
	}

	private class ContentChangedListener implements TextWatcher {
		final EditText editText;

		ContentChangedListener(EditText editText) {
			this.editText = editText;
		}

		@Override
		public void afterTextChanged(Editable s) {

			page.save(new StringBuffer(editText.getText()));
			updateInfo();

		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// Does Nothing

		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// Does Nothing

		}

	}
}
