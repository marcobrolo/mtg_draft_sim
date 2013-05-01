package com.example.mtg_draft_sim;

import java.util.ArrayList;
import java.util.Random;
import android.content.Context;

import android.util.Log;

public class Booster_Pack
{
	//ArrayList<Card> cardList = new ArrayList<Card>();
	ArrayList<String> cardList = new ArrayList<String>();
	int card_count = 15;
	
	public Booster_Pack()
	{	
	}
	
	public void addCard(String card_val)
	{
		this.cardList.add(card_val);
	}
	
	public void removeCard(String card_val)
	{
		this.cardList.remove(card_val);
	}
	
	public int getCardCount()
	{
		return this.card_count;
	}
	
	public void decreaseCardCount()
	{
		this.card_count--;
	}
	
	public ArrayList<String> getCardList()
	{
		return this.cardList;
	}
}
