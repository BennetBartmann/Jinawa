package de.bb42.jinawapremium.view;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import de.bb42.jinawapremium.R;

public class Settings extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.settings);
		TextView tv = (TextView) findViewById(R.id.settingsAbout);
		tv.setText(Html.fromHtml(getString(R.string.about)));
		tv.setMovementMethod(LinkMovementMethod.getInstance());

	}
}
