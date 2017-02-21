package com.icesoft.libgdx.dnd.equipment.interfaces;

import java.util.List;
import com.icesoft.libgdx.dnd.basic.BaseAttribute;
import com.icesoft.libgdx.dnd.basic.BaseProperty;
import com.icesoft.libgdx.dnd.basic.BaseRequirement;
import com.icesoft.libgdx.dnd.basic.BaseSingleSkill;
import com.icesoft.libgdx.dnd.basic.enums.AttributeEnum;
import com.icesoft.libgdx.dnd.basic.enums.EquipmentLevelEnum;
import com.icesoft.libgdx.dnd.basic.enums.PropertyEnum;
import com.icesoft.libgdx.dnd.basic.enums.SkillEnum;
import com.icesoft.libgdx.dnd.object.interfaces.IObject;

public interface IEquipment extends IObject{
	public String getID();
	public void setID(String iD);
	public String getName();
	public void setName(String name);
	public EquipmentLevelEnum getLevel();
	public void setLevel(EquipmentLevelEnum level);
	public int getDruable();
	public void setDruable(int druable);
	public List<BaseAttribute> getAttributes();
	public void setAttributes(List<BaseAttribute> attributes);
	public void addAttribute(BaseAttribute attribute);
	public void addAttributes(List<BaseAttribute> attributes);	
	public int getAttributeByName(AttributeEnum name);
	
	public List<BaseRequirement> getRequirements();
	public void setRequirements(List<BaseRequirement> requirements);
	public void addRequirement(BaseRequirement requirements);
	public void addRequirements(List<BaseRequirement> requirements);
	public int getRequirementByName(AttributeEnum name);
		
	public List<BaseProperty> getProperties();
	public void setProperties(List<BaseProperty> properties);
	public void addProperty(BaseProperty properties);
	public void addProperties(List<BaseProperty> properties);
	public int getPropertyByName(PropertyEnum name);
	
	public List<BaseSingleSkill> getSkills();
	public void setSkills(List<BaseSingleSkill> skills);
	public void addSkill(BaseSingleSkill properties);
	public void addSkills(List<BaseSingleSkill> properties);
	public int getSkillByName(SkillEnum name);
}
