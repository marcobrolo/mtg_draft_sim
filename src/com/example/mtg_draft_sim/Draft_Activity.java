// list view tutorial http://www.vogella.com/articles/AndroidListView/article.html
//http://stackoverflow.com/questions/13977040/how-right-use-preloaded-database-in-android

// expandable list view tutorial
// http://androidtrainningcenter.blogspot.in/2012/07/android-expandable-listview-simple.html
// child image tutorial
// http://stackoverflow.com/questions/7790822/how-can-i-show-image-in-childgroup-in-expandablelistview
// exp list delete group item and refresh
//http://stackoverflow.com/questions/4366132/delete-group-in-expandable-list
package com.example.mtg_draft_sim;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.ExpandableListActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;



public class Draft_Activity extends ExpandableListActivity
{	
    ArrayList<String> groupItem = new ArrayList<String>();
	ArrayList<Object> childItem = new ArrayList<Object>();
	ArrayList<Booster_Pack> packList = new ArrayList<Booster_Pack>();
	
	NewAdapter mNewAdapter;
	
	int pack_counter = 1;
	int player_count = 6;
	int card_per_pack_count = 15;
	int dragon_maze_card_count = 156;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		//super.onCreate(savedInstanceState);
		//ExpandableListView expandbleLis = getExpandableListView();
		//expandbleLis.setDividerHeight(2);
		//expandbleLis.setGroupIndicator(null);
		//expandbleLis.setClickable(true);
		
		CreatePacks();
		setGroupData();		// create group data
		viewPack(savedInstanceState);
		 
		//NewAdapter mNewAdapter = new NewAdapter(groupItem, childItem, Draft_Activity.this);
		//mNewAdapter.setInflater(
		//    (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE),
		//    this);
		//getExpandableListView().setAdapter(mNewAdapter);
		//expandbleLis.setOnChildClickListener(this);
	}
	
	public void viewPack(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		ExpandableListView expandbleLis = getExpandableListView();
		expandbleLis.setDividerHeight(2);
		expandbleLis.setGroupIndicator(null);
		expandbleLis.setClickable(true);
		if(mNewAdapter == null)
		{
			mNewAdapter = new NewAdapter(packList, Draft_Activity.this);
		}
		mNewAdapter.setInflater(
		    (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE),
		    this);
		getExpandableListView().setAdapter(mNewAdapter);
		expandbleLis.setOnChildClickListener(this);
	}
	
	public void CreatePacks()
	{
		// create packs for players
		TestDatabaseActivity database = new TestDatabaseActivity(this);
		int database_card_count = database.getCardCount();
		Log.d("card limit", String.valueOf(database_card_count));
		Random r = new Random();
		for (int i=0; i<player_count; i++)
		{
			Booster_Pack newBooster = new Booster_Pack();
			for(int j=0; j<card_per_pack_count; j++)
			{
				int card_id = r.nextInt(dragon_maze_card_count +1) +1;
				Log.d("random returned", String.valueOf(card_id));
				if ((card_id <=0) || (card_id >= 150))
				{
					Log.d("random returned", String.valueOf(card_id));
					card_id = 1;
				}
				//String card_name = database.getCard(r.nextInt(dragon_maze_card_count +1) +1).getName();
				String card_name = database.getCard(card_id).getName();

				newBooster.addCard(card_name);
			}
			packList.add(newBooster);
		}
	}
	
	public String modifyCardName(String card_name_val)
	{
		// method to remove non alpha characters from string
		return card_name_val.
				replaceAll("\\s","").
				replaceAll("-","").
				replaceAll(",","").
				replaceAll("'","").
				toLowerCase();
	}
	
	public void setGroupData() 
	{
		// get the current booster
		Booster_Pack currentBooster = packList.get(pack_counter);
		ArrayList<String> cardList = currentBooster.getCardList();
		
		
		for (int i = 0; i < cardList.size(); i++)
		{
			String card_name = cardList.get(i);
			groupItem.add(card_name);
			ArrayList<String> child = new ArrayList<String>();
			child.add(modifyCardName(card_name));
			childItem.add(child);
		}
	}
	
	/*
	public void setGroupData() 
	{
		// read database and create cards
		TestDatabaseActivity database = new TestDatabaseActivity(this);
		Log.d("Reading: ", "Reading all contacts..");
		List<Card> cards = database.getAllCards();
		for (Card card: cards)
		{
			String log= "ID: " + card.getID()+ ",Name: " + card.getName();
			Log.d("Name: ", log);
			String card_name = card.getName();
			groupItem.add(card_name);
			ArrayList<String> child = new ArrayList<String>();
			child.add(card_name.
					replaceAll("\\s","").
					replaceAll("-","").
					replaceAll(",","").
					replaceAll("'","").
					toLowerCase());
			childItem.add(child);
		}
		//groupItem.add("Lyev Decree");
		//groupItem.add("Riot Control");
	}*/

	public void removeCardFromBooster(String cardNameVal)
	{
		Booster_Pack currentBooster = packList.get(pack_counter);
		currentBooster.removeCard(cardNameVal);
		// increment pack counter after card selection
		pack_counter = (pack_counter+1) % player_count;

	}
	
	@Override
	public boolean onChildClick(ExpandableListView parent, View v,
			int groupPosition, int childPosition, long id)
	{
		//removeCardFromBooster(groupItem.get(groupPosition));
		Toast.makeText(Draft_Activity.this, "Clicked On Child", Toast.LENGTH_SHORT).show();
		
		return true;
	}
	
}