package com.example.mtg_draft_sim;

public class Card {

	// private variables
	int _id;
	String _name;
	String _rarity;
	
	public Card()
	{	
	}
	
	public Card(int id, String name, String rarity)
	{
		this._id = id;
		this._name = name;
		this._rarity = rarity;
	}
	
	public Card(String name, String rarity)
	{
		this._name = name;
		this._rarity = rarity;
	}
	
	
	// get/set methods
	public String getName()
	{
		return this._name;
	}
	
	public int getID()
	{
		return this._id;
	}
	
	public String getRarity()
	{
		return this._rarity;
	}
	
	public void setID(int id)
	{
		this._id = id;
	}
	
	public void setName(String name)
	{
		this._name = name;
	}
	
	public void setRarity(String rarity)
	{
		this._rarity = rarity;
	}
	
	
}
