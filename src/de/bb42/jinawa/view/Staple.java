package de.bb42.jinawa.view;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import de.bb42.jinawa.R;

public class Staple extends Activity {

	GridView gridView;
	GridViewCustomAdapter gridViewCustomeAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final Intent intent = new Intent(this, Paper.class);

		gridView = (GridView) findViewById(R.id.gridViewCustom);
		// Create the Custom Adapter Object
		final List<String> list = new ArrayList<String>();
		gridViewCustomeAdapter = new GridViewCustomAdapter(this, list,
				R.drawable.paper);
		// Set the Adapter to GridView
		gridView.setAdapter(gridViewCustomeAdapter);

		list.add("Neues Papier");
		gridViewCustomeAdapter.notifyDataSetChanged();

		// Handling touch/click Event on GridView Item
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int position,
					long arg3) {

				if (gridViewCustomeAdapter.getItemId(position) == 0) {
					list.add("Papier");
					gridViewCustomeAdapter.notifyDataSetChanged();

				} else {
					startActivity(intent);

				}
			}
		});

	}

}
