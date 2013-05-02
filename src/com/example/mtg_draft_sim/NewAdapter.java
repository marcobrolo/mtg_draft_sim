// http://android-keas.blogspot.ca/2010/06/expandablelistview-with-dynamic-images.html
package com.example.mtg_draft_sim;
//hash map for variable string

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


@SuppressWarnings("unchecked")
public class NewAdapter extends BaseExpandableListAdapter
{

	public ArrayList<String> groupItem, tempChild;
	public ArrayList<Object> Childtem = new ArrayList<Object>();
	ArrayList<String> draftDeckList = new ArrayList<String>();
	ArrayList<Booster_Pack> packList = new ArrayList<Booster_Pack>();
	public LayoutInflater minflater;
	public Activity activity;
	protected Context _context;
	
	private class Holder
	{
		private ImageView image;
	}
	
	public NewAdapter(ArrayList<String> grList, ArrayList<Object> childItem, Context context)
	{
		groupItem = grList;
		this.Childtem = childItem;
		this._context = context;
	}
	
	public void removeGroup(int group)
	{
		Log.v("Adapter", "Removing group"+group);
		groupItem.remove(group);
        notifyDataSetChanged();
	}
	
	public void removeChild(int group, int child)
	{
		Log.v("Adapter", "Removing child "+child+" in group "+group);
		Childtem.remove(child);
        notifyDataSetChanged();
	}
	
	public void setInflater(LayoutInflater mInflater, Activity act)
	{
		this.minflater = mInflater;
		activity = act;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition)
	{
		return null;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition)
	{
		return 0;
	}

	@Override
	public View getChildView(final int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent)
	{
		tempChild = (ArrayList<String>) Childtem.get(groupPosition);
		TextView text = null;
		ImageView image = null;
		
		if (convertView == null)
		{
			convertView = minflater.inflate(R.layout.child_row, null);
		}
		text = (TextView) convertView.findViewById(R.id.textView1);
		String card_name = tempChild.get(childPosition);
		text.setText(card_name);
		int resID = _context.getResources().getIdentifier(card_name, "drawable", _context.getPackageName());
		image = (ImageView) convertView.findViewById(R.id.childImage);
		image.setImageResource(resID);
		convertView.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				// when the child is clicked, this means user selects the card
				// remove the card then and update the pack info
				removeGroup(groupPosition);
				removeChild(groupPosition, childPosition);
				
				Toast.makeText(activity, tempChild.get(childPosition),
						Toast.LENGTH_SHORT).show();
			}
		});
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition)
	{
		return ((ArrayList<String>) Childtem.get(groupPosition)).size();
	}

	@Override
	public Object getGroup(int groupPosition)
	{
		return null;
	}

	@Override
	public int getGroupCount()
	{
		return groupItem.size();
	}

	@Override
	public void onGroupCollapsed(int groupPosition)
	{
		super.onGroupCollapsed(groupPosition);
	}

	@Override
	public void onGroupExpanded(int groupPosition)
	{
		super.onGroupExpanded(groupPosition);
	}

	@Override
	public long getGroupId(int groupPosition)
	{
		return 0;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent)
	{
		if (convertView == null)
		{
			convertView = minflater.inflate(R.layout.group_row, null);
		}
		((CheckedTextView) convertView).setText(groupItem.get(groupPosition));
		((CheckedTextView) convertView).setChecked(isExpanded);
		return convertView;
	}
	
	@Override
	public boolean hasStableIds()
	{
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition)
	{
		return true;
	}
	
	

}
