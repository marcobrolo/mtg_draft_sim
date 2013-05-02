// http://android-keas.blogspot.ca/2010/06/expandablelistview-with-dynamic-images.html
package com.example.mtg_draft_sim;
//hash map for variable string

import java.util.ArrayList;
import java.util.Random;

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

	public ArrayList<String> groupItem = new ArrayList<String>();
	public ArrayList<String> tempChild = new ArrayList<String>();
	public ArrayList<Object> childItem = new ArrayList<Object>();
	ArrayList<String> draftDeckList = new ArrayList<String>();
	ArrayList<Booster_Pack> packList = new ArrayList<Booster_Pack>();
	TestDatabaseActivity database;
	public int pack_counter = 0;		//keeps track of which pack player is at
	public int player_count = 6;
	public int pick_counter = 0;		//keeps track of picks, used to notify new booster
	public int card_per_pack_count = 15;
	int dragon_maze_card_count = 156;
	public LayoutInflater minflater;
	public Activity activity;
	protected Context _context;
	
	private class Holder
	{
		private ImageView image;
	}
	
	public NewAdapter(Context context)
	{
		//groupItem = grList;
		//this.Childtem = childItem;
		database = new TestDatabaseActivity(context);
		Log.d("making the new adapter", "part1");
		//Log.d("making the new adapter", packListVal.toString());
		//this.packList = packListVal;
		CreatePacks();
		Log.d("making the new adapter", "part2");
		Log.d("making the new adapter", this.packList.toString());
		setGroupData();
		Log.d("making the new adapter", "part3");
		this._context = context;
	}
	
	public void CreatePacks()
	{
		// create packs for players
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
	
	public void cardPick(String card_name_val)
	{
		// imitate picks for other players and current player
		Random r = new Random();
		for (int i=0; i<player_count; i++)
		{
			Booster_Pack currentBooster = this.packList.get(i);
			if (i != pack_counter)
			{
				Log.d("cardPick", "ai picking ");
				// remove ai cards
				int card_id = r.nextInt(currentBooster.getCardCount());
				try
				{	
					String card_name = currentBooster.getCardName(card_id);
					currentBooster.removeCard(card_name);
					Log.d("cardPick", "ai picked " + card_name);
				}
				catch (IndexOutOfBoundsException e)
				{
					Log.d("cardPick", "ai cannot pick card " +
							String.valueOf(card_id) + "from booster with " +
							String.valueOf(currentBooster.getCardCount()+1));
				}
			}
			else
			{
				Log.d("cardPick", "player picking ");
				// remove player's card from his booster
				currentBooster.removeCard(card_name_val);
			}
		}
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
		childItem.remove(child);
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
		tempChild = (ArrayList<String>) childItem.get(groupPosition);
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
				if (pick_counter < 45)
				{	
					// continue draft
					String cardName = groupItem.get(groupPosition);
					// when the child is clicked, this means user selects the card
					// add card into player's deck list
					draftDeckList.add(cardName);
					// remove the card then and update the pack info
					cardPick(cardName);
					// remove card from current booster
					removeGroup(groupPosition);
					removeChild(groupPosition, childPosition);
					// increase pack counter to move to next pack and pick counter
					pack_counter = (pack_counter +1 ) % (player_count - 1);
					pick_counter++;
					Log.d("pick counter tracker", String.valueOf(pick_counter));
					Log.d("pack counter tracker", String.valueOf(pack_counter));
					if (pick_counter % 15 == 0)
					{
						Log.d("open new packs", "deciding");
						if (pick_counter % 45 == 0)
						{
							Log.d("open new packs", "finished draft");
							showPickList();
						}
						else
						{
							Log.d("open new packs", "open new packs");
							// open new packs
							packList.clear();
							
							CreatePacks();
							changePack();
						}
					}
					else
					{
						// update groupItem and childItem to reflect next passed pack
						changePack();
					}
				}
				else
				{
					// draft is finished, display user's pick list
					Log.d("open new packs", "finished draft");
				}
				
				Toast.makeText(activity, tempChild.get(childPosition),
						Toast.LENGTH_SHORT).show();
			}
		});
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition)
	{
		return ((ArrayList<String>) childItem.get(groupPosition)).size();
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
	
	public void setGroupData() 
	{
		Log.d("setting group data", "part1");
		// get the current booster
		Booster_Pack currentBooster = this.packList.get(pack_counter);
		Log.d("setting group data", "part2");
		ArrayList<String> cardList = currentBooster.getCardList();
		for (int i = 0; i < cardList.size(); i++)
		{
			Log.d("setting group data", "part4");
			String card_name = cardList.get(i);
			Log.d("setting group data", "part5");
			Log.d("setting group data", card_name);
			this.groupItem.add(card_name);
			Log.d("setting group data", "part6");
			ArrayList<String> child = new ArrayList<String>();
			Log.d("setting group data", "part7");
			child.add(modifyCardName(card_name));
			Log.d("setting group data", "part8");
			this.childItem.add(child);
			Log.d("setting group data", "part9");
		}
	}
	
	public void changePack()
	{
		Log.d("changePack", "starting to change pack");
		this.groupItem.clear();
		this.childItem.clear();
		setGroupData();
		notifyDataSetChanged();
	}
	
	public void showPickList()
	{
		// adds player's picked cards into groupItem and childItem for display
		groupItem.clear();
		childItem.clear();
		for (int i = 0; i < draftDeckList.size(); i++)
		{

			String card_name = draftDeckList.get(i);
			this.groupItem.add(card_name);
			ArrayList<String> child = new ArrayList<String>();
			child.add(modifyCardName(card_name));
			this.childItem.add(child);
			notifyDataSetChanged();
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

}
