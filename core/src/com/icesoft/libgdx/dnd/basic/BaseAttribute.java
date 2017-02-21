package com.icesoft.libgdx.dnd.basic;

import com.icesoft.libgdx.dnd.basic.enums.AttributeEnum;

public class BaseAttribute{
	private AttributeEnum name;
	private int value;
	
	public BaseAttribute(AttributeEnum name,int value){
		this.name = name;
		this.value = value;
	}
	public AttributeEnum getName() {
		return name;
	}
	public void setName(AttributeEnum name) {
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
	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof BaseAttribute && ((BaseAttribute) obj).getName().equals(getName())){
			return true;
		}
		return false;
	}
	@Override
	public int hashCode() {
		return this.getName().hashCode();
	}	
}
