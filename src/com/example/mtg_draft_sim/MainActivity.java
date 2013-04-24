package com.example.mtg_draft_sim;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button menuButton = (Button)findViewById(R.id.MenuButton);
		menuButton.setOnClickListener(startListener);
	}
	
	private OnClickListener startListener = new OnClickListener()
	{
        public void onClick(View v)
        {     
        	Toast.makeText(MainActivity.this, "The Start button was clicked.", Toast.LENGTH_LONG).show();
        	Intent startNewActivityOpen = new Intent(MainActivity.this, Draft_Activity.class);
        	startActivityForResult(startNewActivityOpen, 0);
        }
    };
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
