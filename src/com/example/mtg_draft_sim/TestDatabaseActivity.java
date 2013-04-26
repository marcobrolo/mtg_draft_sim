// database tutorials
// http://www.vogella.com/articles/AndroidSQLite/article.html
// http://www.androidhive.info/2011/11/android-sqlite-database-tutorial/

package com.example.mtg_draft_sim;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TestDatabaseActivity extends SQLiteOpenHelper
{	
	public TestDatabaseActivity(Context context)
	{
	    super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	public static final String TABLE_NAME = "rare_card";
	public static final String KEY_ID = "_id";
	public static final String KEY_CARD_NAME = "name";
	
	private static final String DATABASE_NAME = "mtg.db";
	private static final int DATABASE_VERSION = 1;
	
	
	// Database creation sql statement
	private static final String DATABASE_CREATE = "create table "
			+ TABLE_NAME + "(" + KEY_ID
			+ " integer primary key autoincrement, " + KEY_CARD_NAME
			+ " text not null);";
	
	
	public void onCreate(SQLiteDatabase database)
	{
		database.execSQL(DATABASE_CREATE);
	}
	
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion)
	{
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(database);
		
	}
	
	public void addCard(Rare_Card card)
	{
		SQLiteDatabase database = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(KEY_CARD_NAME, card.getName());
		
		database.insert(TABLE_NAME, null, values);
		database.close();
	}
	
	public Rare_Card getCard(int id)
	{
		SQLiteDatabase database = this.getReadableDatabase();
		Cursor cursor = database.query(TABLE_NAME, new String[] { KEY_ID, KEY_CARD_NAME }, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		
		if (cursor != null)
		{
			cursor.moveToFirst();
		}
		
		Rare_Card card = new Rare_Card(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1));
		return card;
	}	
	
	public List<Rare_Card> getAllCards()
	{
		List<Rare_Card> cardList = new ArrayList<Rare_Card>();
		String selectQuery = "SELECT * FROM " + TABLE_NAME;
		
		SQLiteDatabase database = this.getWritableDatabase();
		Cursor cursor = database.rawQuery(selectQuery, null);
		
		if (cursor.moveToFirst())
		{
			do
			{
				Rare_Card card = new Rare_Card();
				card.setID(Integer.parseInt(cursor.getString(0)));
				card.setName(cursor.getString(1));
				cardList.add(card);
			}
			while (cursor.moveToNext());
		}
		return cardList;
	}
	
	public int getCardCount()
	{
		String countQuery = "SELECT * FROM " + TABLE_NAME;
		SQLiteDatabase database = this.getReadableDatabase();
		Cursor cursor = database.rawQuery(countQuery, null);
		cursor.close();
		return cursor.getCount();
	}
	
	public int updateCard(Rare_Card card)
	{
		SQLiteDatabase database = this.getReadableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(KEY_CARD_NAME, card.getName());
		
		return database.update(TABLE_NAME, values, KEY_ID + " = ?",
				new String[] { String.valueOf(card.getID()) });
	}
	
	public void deleteCard(Rare_Card card)
	{
		SQLiteDatabase database = this.getWritableDatabase();
		database.delete(TABLE_NAME, KEY_ID + " = ?", 
				new String[] { String.valueOf(card.getID()) });
		database.close();
	}
	
	
}
