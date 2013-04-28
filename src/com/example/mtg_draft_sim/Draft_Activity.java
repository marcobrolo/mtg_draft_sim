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
	 @Override
	 public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  ExpandableListView expandbleLis = getExpandableListView();
	  expandbleLis.setDividerHeight(2);
	  expandbleLis.setGroupIndicator(null);
	  expandbleLis.setClickable(true);

	  setGroupData();
	  setChildGroupData();

	  NewAdapter mNewAdapter = new NewAdapter(groupItem, childItem);
	  mNewAdapter
	    .setInflater(
	      (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE),
	      this);
	  getExpandableListView().setAdapter(mNewAdapter);
	  expandbleLis.setOnChildClickListener(this);
	 }

	 public void setGroupData() {
	  groupItem.add("TechNology");
	  groupItem.add("Mobile");
	  groupItem.add("Manufacturer");
	  groupItem.add("Extras");
	 }

	 ArrayList<String> groupItem = new ArrayList<String>();
	 ArrayList<Object> childItem = new ArrayList<Object>();

	 public void setChildGroupData() {
	  /**
	   * Add Data For TecthNology
	   */
	  ArrayList<String> child = new ArrayList<String>();
	  child.add("Java");
	  child.add("Drupal");
	  child.add(".Net Framework");
	  child.add("PHP");
	  childItem.add(child);

	  /**
	   * Add Data For Mobile
	   */
	  child = new ArrayList<String>();
	  child.add("Android");
	  child.add("Window Mobile");
	  child.add("iPHone");
	  child.add("Blackberry");
	  childItem.add(child);
	  /**
	   * Add Data For Manufacture
	   */
	  child = new ArrayList<String>();
	  child.add("HTC");
	  child.add("Apple");
	  child.add("Samsung");
	  child.add("Nokia");
	  childItem.add(child);
	  /**
	   * Add Data For Extras
	   */
	  child = new ArrayList<String>();
	  child.add("Contact Us");
	  child.add("About Us");
	  child.add("Location");
	  child.add("Root Cause");
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