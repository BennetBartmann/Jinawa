package de.bb42.jinawa.view;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import de.bb42.jinawa.R;

public class GridViewCustomAdapter extends ArrayAdapter<Object> {
	Context context;
	List<String> names;
	int res;
	int i = 0;

	public GridViewCustomAdapter(Context context, List<String> names, int res) {
		super(context, 0);
		this.context = context;
		this.names = names;
		this.res = res;
	}

	public int getCount() {
		return names.size();
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(R.layout.grid_row, parent, false);

			TextView textViewTitle = (TextView) row.findViewById(R.id.textView);
			ImageView imageViewIte = (ImageView) row
					.findViewById(R.id.imageView);
			Log.d("MyApp", "" + names);
			textViewTitle.setText(names.get(names.size() - 1));

			imageViewIte.setImageResource(res);

		}
		return row;
	}
}