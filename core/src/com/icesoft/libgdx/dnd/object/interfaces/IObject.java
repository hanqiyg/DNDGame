package com.icesoft.libgdx.dnd.object.interfaces;

public interface IObject {
	public String getID();
	public String getName();
	public int getStackLimit();
	public int getCount();
	public void setCount(int count);
	public int getWeight();
	public void setWeight(int weight);
}
