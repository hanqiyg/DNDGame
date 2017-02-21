package com.icesoft.libgdx.dnd.basic;

import com.icesoft.libgdx.dnd.basic.enums.PropertyEnum;

public class BaseProperty {
	private PropertyEnum name;
	private int value;
	
	public BaseProperty(PropertyEnum name,int value){
		this.name = name;
		this.value = value;
	}
	public PropertyEnum getName() {
		return name;
	}
	public void setName(PropertyEnum name) {
		this.name = name;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	@Override
	public String toString() {		
		return "{Attribute} Name: [ " +name.toString() + " ] " + "Value: [ " + value + " ]";
	}
}
