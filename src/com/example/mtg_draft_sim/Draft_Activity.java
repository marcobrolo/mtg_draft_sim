// list view tutorial http://www.vogella.com/articles/AndroidListView/article.html
//http://stackoverflow.com/questions/13977040/how-right-use-preloaded-database-in-android


package com.example.mtg_draft_sim;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Draft_Activity extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.draft_main);
	    final ArrayList<String> list2 = new ArrayList<String>();

		//database
		TestDatabaseActivity database = new TestDatabaseActivity(this);
		Log.d("Insert: ", "Inserting .. ");
		database.addCard(new Card("Garruk", "Common"));
		database.addCard(new Card("liliana", "Common"));
		
		//reading contact
		Log.d("Reading: ", "Reading all contacts..");
		List<Card> cards = database.getAllCards();
		for (Card card: cards)
		{
			String log= "ID: " + card.getID()+ ",Name: " + card.getName();
			Log.d("Name: ", log);
			String card_name = card.getName();
			list2.add(card_name);
		}
		
		final ListView listview = (ListView) findViewById(R.id.listview);
	    String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
	        "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
	        "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
	        "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
	        "Android", "iPhone", "WindowsMobile" };
	    
	    final ArrayList<String> list = new ArrayList<String>();
	    for (int i = 0; i < values.length; ++i)
	    {
	    	list.add(values[i]);
	    }
	    
	    final StableArrayAdapter adapter = new StableArrayAdapter(this,
	            android.R.layout.simple_list_item_1, list2);
	        listview.setAdapter(adapter);
	        
	        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

	            @Override
	            public void onItemClick(AdapterView<?> parent, final View view,
	                int position, long id)
	            {
	            	final String item = (String) parent.getItemAtPosition(position);
        			list2.remove(item);
        			adapter.notifyDataSetChanged();
	            }
	          });
	}
	
	private class StableArrayAdapter extends ArrayAdapter<String>
	{

	    HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

	    public StableArrayAdapter(Context context, int textViewResourceId,
	        List<String> objects)
	    {
	    	super(context, textViewResourceId, objects);
	    	for (int i = 0; i < objects.size(); ++i)
	    	{
	    		mIdMap.put(objects.get(i), i);
	    	}
	    }
	    
	    @Override
	    public long getItemId(int position)
	    {
	      String item = getItem(position);
	      return mIdMap.get(item);
	    }

	    @Override
	    public boolean hasStableIds()
	    {
	    	return true;
	    }
	}
}
