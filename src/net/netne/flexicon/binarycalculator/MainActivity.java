package net.netne.flexicon.binarycalculator;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
@SuppressWarnings("rawtypes")

public class MainActivity extends ListActivity {
	final String classes[] = { "Converter", "Calculator" };
	final static String EXTRA_MESSAGE = "net.netne.flexicon.binarycalculator.MESSAGE";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Make the view be a list using an Adapter
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, classes));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		String choiceClass = classes[position] + "Activity";
		
		// Attempt to start an Activity
		try{
			Class ourClass = Class.forName("net.netne.flexicon.binarycalculator." + choiceClass);
			Intent intent = new Intent(this, ourClass);
			intent.putExtra(EXTRA_MESSAGE, choiceClass);
			startActivity(intent);
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
