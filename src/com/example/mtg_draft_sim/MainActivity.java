package com.example.mtg_draft_sim;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// delete db
		this.deleteDatabase("mtg.db");
		
		Button menuButton = (Button)findViewById(R.id.MenuButton);
		menuButton.setOnClickListener(startListener);
		
		Button databaseButton = (Button)findViewById(R.id.DatabaseButton);
		databaseButton.setOnClickListener(startListener);
	}
	
	private OnClickListener startListener = new OnClickListener()
	{
        public void onClick(View v)
        {     
        	switch(v.getId())
        	{
        	case R.id.MenuButton:
	        	Toast.makeText(MainActivity.this, "The Start button was clicked.", Toast.LENGTH_LONG).show();
	        	Intent startNewActivityOpen = new Intent(MainActivity.this, Draft_Activity.class);
	        	startActivityForResult(startNewActivityOpen, 0);
	        	break;
        	case R.id.DatabaseButton:
        		Toast.makeText(MainActivity.this, "Resetting Database.", Toast.LENGTH_LONG).show();
        		fillDatabase();
        		break;
        	}
        }
    };
    
    private void fillDatabase()
    {
    	// insert db with cards
    	TestDatabaseActivity database = new TestDatabaseActivity(this);
		Log.d("Insert: ", "Inserting .. ");
		database.addCard(new Card("Boros Mastiff", "Common"));
		database.addCard(new Card("Haazda Snare Squad", "Common"));
		database.addCard(new Card("Lyev Decree", "Common"));
		database.addCard(new Card("Maze Sentinel", "Common"));
		database.addCard(new Card("Renounce the Guilds", "Rare"));
		database.addCard(new Card("Riot Control", "Common"));
		database.addCard(new Card("Scion of Vitu-Ghazi", "Common"));
		
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
