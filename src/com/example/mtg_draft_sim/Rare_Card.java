package com.example.mtg_draft_sim;

public class Rare_Card {

	// private variables
	int _id;
	String _name;
	
	public Rare_Card()
	{	
	}
	
	public Rare_Card(int id, String name)
	{
		this._id = id;
		this._name = name;
	}
	
	public Rare_Card(String name)
	{
		this._name = name;
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
	
	public void setID(int id)
	{
		this._id = id;
	}
	
	public void setName(String name)
	{
		this._name = name;
	}
	
	
}
