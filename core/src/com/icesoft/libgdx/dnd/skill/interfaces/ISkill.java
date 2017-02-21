package com.icesoft.libgdx.dnd.skill.interfaces;

import com.icesoft.libgdx.dnd.basic.enums.SkillEnum;

public interface ISkill {
	public String getID();
	public void setID(String id);
	public SkillEnum getName();
	public void setName(SkillEnum name);
	public int getEXP();
	public void setEXP(int exp);
	public String getDescription();
}
