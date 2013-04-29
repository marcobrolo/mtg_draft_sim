// list view tutorial http://www.vogella.com/articles/AndroidListView/article.html
//http://stackoverflow.com/questions/13977040/how-right-use-preloaded-database-in-android

// expandable list view tutorial
// http://androidtrainningcenter.blogspot.in/2012/07/android-expandable-listview-simple.html
// child image tutorial
// http://stackoverflow.com/questions/7790822/how-can-i-show-image-in-childgroup-in-expandablelistview

package com.example.mtg_draft_sim;

import java.util.ArrayList;

import android.app.ExpandableListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;



public class Draft_Activity extends ExpandableListActivity
{	
    ArrayList<String> groupItem = new ArrayList<String>();
	ArrayList<Object> childItem = new ArrayList<Object>();
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		ExpandableListView expandbleLis = getExpandableListView();
		expandbleLis.setDividerHeight(2);
		expandbleLis.setGroupIndicator(null);
		expandbleLis.setClickable(true);
		  
		setGroupData();		// create group data
		setChildGroupData();	// create child data
		  
		NewAdapter mNewAdapter = new NewAdapter(groupItem, childItem, Draft_Activity.this);
		mNewAdapter.setInflater(
		    (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE),
		    this);
		getExpandableListView().setAdapter(mNewAdapter);
		expandbleLis.setOnChildClickListener(this);
	}

	public void setGroupData() 
	{
		groupItem.add("Lyev Decree");
		groupItem.add("Riot Control");
	}

	public void setChildGroupData()
	{
		/**
		  * Add Data For TecthNology
		  */
		ArrayList<String> child = new ArrayList<String>();
		child.add("lyevdecree");
		childItem.add(child);
	
		/**
		  * Add Data For Mobile
		  */
		child = new ArrayList<String>();
		child.add("riotcontrol");
		childItem.add(child);

	}

	@Override
	public boolean onChildClick(ExpandableListView parent, View v,
			int groupPosition, int childPosition, long id)
	{
		Toast.makeText(Draft_Activity.this, "Clicked On Child", Toast.LENGTH_SHORT).show();
		return true;
	}
	
}