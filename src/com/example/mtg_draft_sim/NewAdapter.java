// http://android-keas.blogspot.ca/2010/06/expandablelistview-with-dynamic-images.html
package com.example.mtg_draft_sim;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageView;



@SuppressWarnings("unchecked")
public class NewAdapter extends BaseExpandableListAdapter
{

	public ArrayList<String> groupItem, tempChild;
	public ArrayList<Object> Childtem = new ArrayList<Object>();
	public LayoutInflater minflater;
	public Activity activity;
	
	private class Holder
	{
		private ImageView image;
	}
	
	public NewAdapter(ArrayList<String> grList, ArrayList<Object> childItem)
	{
		groupItem = grList;
		this.Childtem = childItem;
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
	
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent)
	{
		View view= getGenericGroupView(groupPosition ,convertView);
		return view;
	}           
	
	public View getGenericGroupView(int groupPosition, View convertView)
	{
		// Layout parameters for the ExpandableListView
        View view=null;
        try{


        AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 90);
        view=convertView;
        Holder holder=new Holder(); 
        if(convertView==null)
        {
            //LayoutInflater inflator=(LayoutInflater) ExpandableList1.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //view=inflator.inflate(R.layout.child_row, null);
        	view = minflater.inflate(R.layout.child_row, null);
            view.setLayoutParams(lp);

            view.setPadding(80, 5, 5, 5);
            holder.image=(ImageView)view.findViewById(R.id.childImage);

            view.setTag(holder);
        }
        else
        {
            holder=(Holder) view.getTag();
        }
            holder.image.setImageResource(R.drawable.minus);


        }catch (Exception e) {

            e.printStackTrace();
            // TODO: handle exception
        }

        return view;
    }
	

/*
	@Override
	public View getChildView(int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent)
	{
		tempChild = (ArrayList<String>) Childtem.get(groupPosition);
		TextView text = null;
		if (convertView == null)
		{
			convertView = minflater.inflate(R.layout.child_row, null);
		}
		text = (TextView) convertView.findViewById(R.id.textView1);
		text.setText(tempChild.get(childPosition));
		convertView.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				Toast.makeText(activity, tempChild.get(childPosition),
						Toast.LENGTH_SHORT).show();
			}
		});
		return convertView;
	}
*/
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
		return false;
	}

}
