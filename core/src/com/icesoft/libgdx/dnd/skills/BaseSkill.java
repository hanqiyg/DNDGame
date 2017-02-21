package com.icesoft.libgdx.dnd.skills;

import com.icesoft.libgdx.dnd.basic.enums.SkillEnum;
import com.icesoft.libgdx.dnd.skill.interfaces.ISkill;

public class BaseSkill implements ISkill{	
	private String ID;
	private SkillEnum name;
	private int EXP;
	private String description;
	
	public BaseSkill(SkillEnum name,int EXP){
		this.name = name;
		this.EXP = EXP;
	}
	
	@Override
	public String getID() {
		return ID;
	}
	@Override
	public void setID(String id) {
		this.ID = id;		
	}
	@Override
	public SkillEnum getName() {
		return name;
	}
	@Override
	public void setName(SkillEnum name) {
		this.name = name;
	}
	@Override
	public int getEXP() {
		return EXP;
	}
	@Override
	public void setEXP(int exp) {
		this.EXP = exp;
	}
	public void setDescription(String description){
		this.description = description;
	}
	@Override
	public String getDescription() {
		return description;
	}
}
