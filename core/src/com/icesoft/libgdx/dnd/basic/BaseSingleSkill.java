package com.icesoft.libgdx.dnd.basic;

import com.icesoft.libgdx.dnd.basic.enums.SkillEnum;

public class BaseSingleSkill {
	private SkillEnum name;
	private int value;
	
	public BaseSingleSkill(SkillEnum name,int value){
		this.name = name;
		this.value = value;
	}
	public SkillEnum getName() {
		return name;
	}
	public void setName(SkillEnum name) {
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
		if(obj != null && obj instanceof BaseSingleSkill && ((BaseSingleSkill)obj).getName().equals(getName())){
			return true;
		}
		return false;
	}
	@Override
	public int hashCode() {
		return this.getName().hashCode();
	}	
}
