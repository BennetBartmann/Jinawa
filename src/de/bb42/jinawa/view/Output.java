package de.bb42.jinawa.view;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class Output {

	public static void sendToast(Context context, CharSequence text) {

		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
		Log.v("Output", text.toString());
	}
}
