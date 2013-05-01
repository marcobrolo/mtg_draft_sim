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
		//white
		database.addCard(new Card("Boros Mastiff", "Common"));
		database.addCard(new Card("Haazda Snare Squad", "Common"));
		database.addCard(new Card("Lyev Decree", "Common"));
		database.addCard(new Card("Maze Sentinel", "Common"));
		database.addCard(new Card("Renounce the Guilds", "Rare"));
		database.addCard(new Card("Riot Control", "Common"));
		database.addCard(new Card("Scion of Vitu-Ghazi", "Common"));
		database.addCard(new Card("Steeple Roc", "Common"));
		database.addCard(new Card("Sunspire Gatekeepers", "Common"));
		database.addCard(new Card("Wake the Reflections", "Common"));
		
		// blue
		database.addCard(new Card("Aetherling", "Common"));
		database.addCard(new Card("Hidden Strings", "Common"));
		database.addCard(new Card("Maze Glider", "Common"));
		database.addCard(new Card("Mindstatic", "Common"));
		database.addCard(new Card("Murmuring Phantasm", "Common"));
		database.addCard(new Card("Opal Lake Gatekeepers", "Common"));
		database.addCard(new Card("Runner's Bane", "Common"));
		database.addCard(new Card("Trait Doctoring", "Common"));
		database.addCard(new Card("Uncovered Clues", "Common"));
		database.addCard(new Card("Wind Drake", "Common"));
		
		// black
		database.addCard(new Card("Bane Alley Blackguard", "Common"));
		database.addCard(new Card("Blood Scrivener", "Common"));
		database.addCard(new Card("Crypt Incursion", "Common"));
		database.addCard(new Card("Fatal Fumes", "Common"));
		database.addCard(new Card("Hired Torturer", "Common"));
		database.addCard(new Card("Maze Abomination", "Common"));
		database.addCard(new Card("Pontiff of Blight", "Common"));
		database.addCard(new Card("Rakdos Drake", "Common"));
		database.addCard(new Card("Sinister Possession", "Common"));
		database.addCard(new Card("Ubul Sar Gatekeepers", "Common"));
		
		// red
		database.addCard(new Card("Awe for the Guilds", "Common"));
		database.addCard(new Card("Clear a Path", "Common"));
		database.addCard(new Card("Maze Rusher", "Common"));
		database.addCard(new Card("Possibility Storm", "Common"));
		database.addCard(new Card("Punish the Enemy", "Common"));
		database.addCard(new Card("Pyrewild Shaman", "Common"));
		database.addCard(new Card("Riot Piker", "Common"));
		database.addCard(new Card("Rubblebelt Maaka", "Common"));
		database.addCard(new Card("Smelt-Ward Gatekeepers", "Common"));
		database.addCard(new Card("Weapon Surge", "Common"));
		
		// green
		database.addCard(new Card("Battering Krasis", "Common"));
		database.addCard(new Card("Kraul Warrior", "Common"));
		database.addCard(new Card("Maze Behemoth", "Common"));
		database.addCard(new Card("Mending Touch", "Common"));
		database.addCard(new Card("Mutant's Prey", "Common"));
		database.addCard(new Card("Phytoburst", "Common"));
		database.addCard(new Card("Renegade Krasis", "Common"));
		database.addCard(new Card("Saruli Gatekeepers", "Common"));
		database.addCard(new Card("Skylasher", "Common"));
		database.addCard(new Card("Thrashing Mossdog", "Common"));
		
		// multicolor
		database.addCard(new Card("Advent of the Wurm", "Common"));
		database.addCard(new Card("AliveWell", "Common"));
		database.addCard(new Card("ArmedDangerous", "Common"));
		database.addCard(new Card("Armored Wolf-Rider", "Common"));
		database.addCard(new Card("Ascended Lawmage", "Common"));
		database.addCard(new Card("BeckCall", "Common"));
		database.addCard(new Card("Beetleform Mage", "Common"));
		database.addCard(new Card("Blast of Genius", "Common"));
		database.addCard(new Card("Blaze Commando", "Common"));
		database.addCard(new Card("Blood Baron of Vizkopa", "Common"));
		database.addCard(new Card("Boros Battleshaper", "Common"));
		database.addCard(new Card("BreakingEntering", "Common"));
		database.addCard(new Card("Bred for the Hunt", "Common"));
		database.addCard(new Card("Bronzebeak Moa", "Common"));
		database.addCard(new Card("Carnage Gladiator", "Common"));
		database.addCard(new Card("CatchRelease", "Common"));
		database.addCard(new Card("Council of the Absolute", "Common"));
		database.addCard(new Card("Deadbridge Chant", "Common"));
		database.addCard(new Card("Debt to the Deathless", "Common"));
		database.addCard(new Card("Deputy of Acquittals", "Common"));
		database.addCard(new Card("DownDirty", "Common"));
		database.addCard(new Card("Emmara Tandris", "Common"));
		database.addCard(new Card("Exava, Rakdos Blood Witch", "Common"));
		database.addCard(new Card("FarAway", "Common"));
		database.addCard(new Card("Feral Animist", "Common"));
		database.addCard(new Card("FleshBlood", "Common"));
		database.addCard(new Card("Fluxcharger", "Common"));
		database.addCard(new Card("Gaze of Granite", "Common"));
		database.addCard(new Card("GiveTake", "Common"));
		database.addCard(new Card("Gleam of Battle", "Common"));
		database.addCard(new Card("Goblin Test Pilot", "Common"));
		database.addCard(new Card("Gruul War Chant", "Common"));
		database.addCard(new Card("Haunter of Nightveil", "Common"));
		database.addCard(new Card("Jelenn Sphinx", "Common"));
		database.addCard(new Card("Korozda Gorgon", "Common"));
		database.addCard(new Card("Krasis Incubation", "Common"));
		database.addCard(new Card("Lavinia of the Tenth", "Common"));
		database.addCard(new Card("Legion's Initiative", "Common"));
		database.addCard(new Card("Master of Cruelties", "Common"));
		database.addCard(new Card("Maw of the Obzedat", "Common"));
		database.addCard(new Card("Melek, Izzet Paragon", "Common"));
		database.addCard(new Card("Mirko Vosk, Mind Drinker", "Common"));
		database.addCard(new Card("Morgue Burst", "Common"));
		database.addCard(new Card("Nivix Cyclops", "Common"));
		database.addCard(new Card("Notion Thief", "Common"));
		database.addCard(new Card("Obzedat's Aid", "Common"));
		database.addCard(new Card("Pilfered Plans", "Common"));
		database.addCard(new Card("Plasm Capture", "Common"));
		database.addCard(new Card("ProfitLoss", "Common"));
		database.addCard(new Card("Progenitor Mimic", "Common"));
		database.addCard(new Card("ProtectServe", "Common"));
		database.addCard(new Card("Putrefy", "Common"));
		database.addCard(new Card("Ral Zarek", "Common"));
		database.addCard(new Card("ReadyWilling", "Common"));
		database.addCard(new Card("Reap Intellect", "Common"));
		database.addCard(new Card("Render Silent", "Common"));
		database.addCard(new Card("Restore the Peace", "Common"));
		database.addCard(new Card("Rot Farm Skeleton", "Common"));
		database.addCard(new Card("Ruric Thar, the Unbowed", "Common"));
		database.addCard(new Card("Savageborn Hydra", "Common"));
		database.addCard(new Card("Sin Collector", "Common"));
		database.addCard(new Card("Sire of Insanity", "Common"));
		database.addCard(new Card("Species Gorger", "Common"));
		database.addCard(new Card("Spiked Jester", "Common"));
		database.addCard(new Card("Tajic, Blade of the Legion", "Common"));
		database.addCard(new Card("Teysa, Envoy of Ghosts", "Common"));
		database.addCard(new Card("Tithe Drinker", "Common"));
		database.addCard(new Card("ToilTrouble", "Common"));
		database.addCard(new Card("Trostani's Summoner", "Common"));
		database.addCard(new Card("TurnBurn", "Common"));
		database.addCard(new Card("Unflinching Courage", "Common"));
		database.addCard(new Card("Varolz, the Scar-Striped", "Common"));
		database.addCard(new Card("Viashino Firstblade", "Common"));
		database.addCard(new Card("Voice of Resurgence", "Common"));
		database.addCard(new Card("Vorel of the Hull Clade", "Common"));
		database.addCard(new Card("Warleader's Helix", "Common"));
		database.addCard(new Card("Warped Physique", "Common"));
		database.addCard(new Card("WearTear", "Common"));
		database.addCard(new Card("Woodlot Crawler", "Common"));
		database.addCard(new Card("Zhur-Taa Ancient", "Common"));
		database.addCard(new Card("Zhur-Taa Druid", "Common"));
		
		// artifact
		database.addCard(new Card("Azorius Cluestone", "Common"));
		database.addCard(new Card("Boros Cluestone", "Common"));
		database.addCard(new Card("Dimir Cluestone", "Common"));
		database.addCard(new Card("Golgari Cluestone", "Common"));
		database.addCard(new Card("Gruul Cluestone", "Common"));
		database.addCard(new Card("Izzet Cluestone", "Common"));
		database.addCard(new Card("Orzhov Cluestone", "Common"));
		database.addCard(new Card("Rakdos Cluestone", "Common"));
		database.addCard(new Card("Selesnya Cluestone", "Common"));
		database.addCard(new Card("Simic Cluestone", "Common"));
		
		//lands
		database.addCard(new Card("Azorius Guildgate", "Common"));
		database.addCard(new Card("Boros Guildgate", "Common"));
		database.addCard(new Card("Dimir Guildgate", "Common"));
		database.addCard(new Card("Golgari Guildgate", "Common"));
		database.addCard(new Card("Gruul Guildgate", "Common"));
		database.addCard(new Card("Izzet Guildgate", "Common"));
		database.addCard(new Card("Orzhov Guildgate", "Common"));
		database.addCard(new Card("Rakdos Guildgate", "Common"));
		database.addCard(new Card("Selesnya Guildgate", "Common"));
		database.addCard(new Card("Simic Guildgate", "Common"));
		database.addCard(new Card("Maze's End", "Common"));
		
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
