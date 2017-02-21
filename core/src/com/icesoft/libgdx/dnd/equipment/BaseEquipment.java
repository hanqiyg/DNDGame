package com.icesoft.libgdx.dnd.equipment;

import java.util.ArrayList;
import java.util.List;
import com.icesoft.libgdx.dnd.basic.BaseAttribute;
import com.icesoft.libgdx.dnd.basic.BaseProperty;
import com.icesoft.libgdx.dnd.basic.BaseRequirement;
import com.icesoft.libgdx.dnd.basic.BaseSingleSkill;
import com.icesoft.libgdx.dnd.basic.enums.AttributeEnum;
import com.icesoft.libgdx.dnd.basic.enums.EquipmentLevelEnum;
import com.icesoft.libgdx.dnd.basic.enums.PropertyEnum;
import com.icesoft.libgdx.dnd.basic.enums.SkillEnum;
import com.icesoft.libgdx.dnd.equipment.interfaces.IEquipment;

public class BaseEquipment implements IEquipment{
	private String ID;
	private String name;
	private EquipmentLevelEnum level;
	private int weight;
	private int druable;
	
	private List<BaseAttribute> attributes;
	private List<BaseRequirement> requirements;
	private List<BaseProperty> properties;
	private List<BaseSingleSkill> skills;

	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public EquipmentLevelEnum getLevel() {
		return level;
	}
	public void setLevel(EquipmentLevelEnum level) {
		this.level = level;
	}
	public int getDruable() {
		return druable;
	}
	public void setDruable(int druable) {
		this.druable = druable;
	}	
	public List<BaseAttribute> getAttributes() {
		if(attributes == null){
			attributes = new ArrayList<BaseAttribute>();
		}
		return attributes;
	}
	public void setAttributes(List<BaseAttribute> attributes) {
		this.attributes = attributes;
	}
	public void addAttribute(BaseAttribute attribute) {
		getAttributes().add(attribute);
	}
	public void addAttributes(List<BaseAttribute> attributes) {
		getAttributes().addAll(attributes);
	}
	public int getAttributeByName(AttributeEnum name){
		int a = 0;
		for(BaseAttribute ba : getAttributes()){
			if(ba.getName().equals(name)){
				a = a + ba.getValue();
			}
		}
		return a;
	}
	
	public List<BaseRequirement> getRequirements() {
		if(requirements == null){
			requirements = new ArrayList<BaseRequirement>();
		}
		return requirements;
	}
	public void setRequirements(List<BaseRequirement> requirements) {
		this.requirements = requirements;
	}
	public void addRequirement(BaseRequirement requirements) {
		getRequirements().add(requirements);
	}
	public void addRequirements(List<BaseRequirement> requirements) {
		getRequirements().addAll(requirements);
	}
	public int getRequirementByName(AttributeEnum name){
		int r = 0;
		for(BaseRequirement ba : getRequirements()){
			if(ba.getName().equals(name)){
				r = r + ba.getValue();
			}
		}
		return r;
	}	
	public List<BaseProperty> getProperties() {
		if(properties == null){
			properties = new ArrayList<BaseProperty>();
		}
		return properties;
	}
	public void setProperties(List<BaseProperty> properties) {
		this.properties = properties;
	}
	public void addProperty(BaseProperty properties) {
		getProperties().add(properties);
	}
	public void addProperties(List<BaseProperty> properties) {
		getProperties().addAll(properties);
	}	
	public int getPropertyByName(PropertyEnum name){
		int p = 0;
		for(BaseProperty ba : getProperties()){
			if(ba.getName().equals(name)){
				p = p + ba.getValue();
			}
		}
		return p;
	}
	
	public List<BaseSingleSkill> getSkills() {
		if(skills == null){
			skills = new ArrayList<BaseSingleSkill>();
		}
		return skills;
	}
	public void setSkills(List<BaseSingleSkill> skills) {
		this.skills = skills;
	}
	public void addSkill(BaseSingleSkill properties) {
		getSkills().add(properties);
	}
	public void addSkills(List<BaseSingleSkill> properties) {
		getSkills().addAll(properties);
	}
	public int getSkillByName(SkillEnum name){
		int s = 0;
		for(BaseSingleSkill ba : getSkills()){
			if(ba.getName().equals(name)){
				s = s + ba.getValue();
			}
		}
		return s;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("ID");
		sb.append("["+ID+"]" + System.getProperty("line.separator"));
		sb.append("Name");
		sb.append("["+name+"]" + System.getProperty("line.separator"));
		sb.append("Druable");
		sb.append("["+druable+"]" + System.getProperty("line.separator"));

		for(BaseAttribute a : getAttributes()){
			sb.append(a.getName());
			sb.append("["+a.getValue()+"]" + System.getProperty("line.separator"));
		}
		for(BaseRequirement r : getRequirements()){
			sb.append(r.getName());
			sb.append("["+attributes.get(r.getValue())+"]" + System.getProperty("line.separator"));
		}
		for(BaseProperty p : getProperties()){
			sb.append(p.getName());
			sb.append("["+p.getValue()+"]" + System.getProperty("line.separator"));
		}
		for(BaseSingleSkill s : getSkills()){
			sb.append(s.getName());
			sb.append("["+s.getValue()+"]" + System.getProperty("line.separator"));
		}
		return sb.toString();
	}
	public int getStackLimit(){
		return 1;
	}
	public int getCount(){
		return 1;
	}
	public void setCount(int count){
		return;
	}
	public int getWeight(){
		return weight;
	}
	public void setWeight(int weight){
		this.weight = weight;
	}
}
