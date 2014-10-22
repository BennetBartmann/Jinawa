package de.bb42.jinawa.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
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

	private Page page;

	/**
	 * @param savedInstanceState
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_paper);
		Intent IntentStaples = getIntent();
		Bundle b = IntentStaples.getExtras();

		if (b != null) {
			positionPaper = (Integer) b.get("positionPaper");
			positionStaples = (Integer) b.get("positionStaples");
			page = controller.getStapleOfStaples().getStaples()
					.get(positionStaples).getPages().get(positionPaper);
			final EditText editText = (EditText) findViewById(R.id.editText);
			String text = page.getContent().toString();
			editText.setText(text);
			editText.addTextChangedListener(new TextWatcher() {
				public void afterTextChanged(Editable s) {
					page.save(new StringBuffer(editText.getText()));
				}

				public void beforeTextChanged(CharSequence s, int start,
						int count, int after) {
				}

				public void onTextChanged(CharSequence s, int start,
						int before, int count) {
				}
			});
		}
	}

	@Override
	public void onBackPressed() {
		page.save();
		super.onBackPressed();
	}
}
