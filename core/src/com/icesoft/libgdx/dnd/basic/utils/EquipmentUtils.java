package com.icesoft.libgdx.dnd.basic.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.icesoft.libgdx.dnd.basic.BaseAttribute;
import com.icesoft.libgdx.dnd.basic.BaseProperty;
import com.icesoft.libgdx.dnd.basic.BaseSingleSkill;
import com.icesoft.libgdx.dnd.basic.enums.AttributeEnum;
import com.icesoft.libgdx.dnd.basic.enums.EquipmentClassEnum;
import com.icesoft.libgdx.dnd.basic.enums.EquipmentLevelEnum;
import com.icesoft.libgdx.dnd.basic.enums.PropertyEnum;
import com.icesoft.libgdx.dnd.basic.enums.SkillEnum;
import com.icesoft.libgdx.dnd.character.GameChar;
import com.icesoft.libgdx.dnd.equipment.Arm;
import com.icesoft.libgdx.dnd.equipment.BaseEquipment;
import com.icesoft.libgdx.dnd.equipment.Body;
import com.icesoft.libgdx.dnd.equipment.Boots;
import com.icesoft.libgdx.dnd.equipment.Glove;
import com.icesoft.libgdx.dnd.equipment.Guardian;
import com.icesoft.libgdx.dnd.equipment.Head;
import com.icesoft.libgdx.dnd.equipment.LeftHand;
import com.icesoft.libgdx.dnd.equipment.Mount;
import com.icesoft.libgdx.dnd.equipment.Necklace;
import com.icesoft.libgdx.dnd.equipment.Pants;
import com.icesoft.libgdx.dnd.equipment.RightHand;
import com.icesoft.libgdx.dnd.equipment.Ring;
import com.icesoft.libgdx.dnd.equipment.interfaces.IArm;
import com.icesoft.libgdx.dnd.equipment.interfaces.IBody;
import com.icesoft.libgdx.dnd.equipment.interfaces.IBoots;
import com.icesoft.libgdx.dnd.equipment.interfaces.IEquipment;
import com.icesoft.libgdx.dnd.equipment.interfaces.IGlove;
import com.icesoft.libgdx.dnd.equipment.interfaces.IGuardian;
import com.icesoft.libgdx.dnd.equipment.interfaces.IHead;
import com.icesoft.libgdx.dnd.equipment.interfaces.ILeftHand;
import com.icesoft.libgdx.dnd.equipment.interfaces.IMount;
import com.icesoft.libgdx.dnd.equipment.interfaces.INecklace;
import com.icesoft.libgdx.dnd.equipment.interfaces.IPants;
import com.icesoft.libgdx.dnd.equipment.interfaces.IRightHand;
import com.icesoft.libgdx.dnd.equipment.interfaces.IRing;
import com.icesoft.libgdx.dnd.object.interfaces.IObject;

public class EquipmentUtils {
	@SuppressWarnings("unchecked")
	public static <T extends IEquipment> T genEquipment(String name,EquipmentLevelEnum level,EquipmentClassEnum type){
		IEquipment e;
		switch(type){
			case ARM: e = new Arm();
				break;
			case BODY:e = new Body();
				break;
			case BOOTS:e = new Boots();
				break;
			case GLOVE:e = new Glove();
				break;
			case GUARDIAN:e = new Guardian();
				break;
			case HEAD:e = new Head();
				break;
			case LEFTHAND:e = new LeftHand();
				break;
			case LEFTRING:e = new Ring();
				break;
			case MOUNT:e = new Mount();
				break;
			case NECKLACE:e = new Necklace();
				break;
			case PANTS:e = new Pants();
				break;
			case RIGHTHAND:e = new RightHand();
				break;
			case RIGHTRING:e = new Ring();
				break;
			default: e= new BaseEquipment();
				break;
		}
			e.setID(java.util.UUID.randomUUID().toString());
			e.setName(name);
			e.setCount(1);
		switch(level){
	 		case NORMAL:{
				e.setLevel(EquipmentLevelEnum.NORMAL);
				e.setWeight(50);
				e.setDruable(50);
				e.setAttributes(getRandomAttributes(0,0));
				e.setProperties(getRandomProperties(3,3));
				e.setSkills(getRandomSkills(0,3));
			}
	 			break;
			case ENHANCED:{
				e.setLevel(EquipmentLevelEnum.ENHANCED);
				e.setWeight(100);
				e.setDruable(100);
				e.setAttributes(getRandomAttributes(1,3));
				e.setProperties(getRandomProperties(3,5));
				e.setSkills(getRandomSkills(0,3));
			}
		 		break;
		 	case RARE:{
				e.setLevel(EquipmentLevelEnum.RARE);
				e.setWeight(200);
				e.setDruable(200);
				e.setAttributes(getRandomAttributes(1,5));
				e.setProperties(getRandomProperties(5,5));
				e.setSkills(getRandomSkills(0,3));
			}
		 		break;
		 	case UNIQUE:{
				e.setLevel(EquipmentLevelEnum.UNIQUE);
				e.setWeight(300);
				e.setDruable(300);
				e.setAttributes(getRandomAttributes(2,5));
				e.setProperties(getRandomProperties(7,5));
				e.setSkills(getRandomSkills(1,1));
			}
		 		break;
		 	case HEROIC:{
				e.setLevel(EquipmentLevelEnum.HEROIC);
				e.setWeight(500);
				e.setDruable(500);
				e.setAttributes(getRandomAttributes(2,7));
				e.setProperties(getRandomProperties(7,7));
				e.setSkills(getRandomSkills(2,2));
			}
		 		break;
		 	case INHERIT:{
				e.setLevel(EquipmentLevelEnum.INHERIT);
				e.setWeight(1000);
				e.setDruable(1000);
				e.setAttributes(getRandomAttributes(3,7));
				e.setProperties(getRandomProperties(9,7));
				e.setSkills(getRandomSkills(3,2));
			}
		 		break;
		 	case SUIT:{
				e.setLevel(EquipmentLevelEnum.SUIT);
				e.setWeight(1000);
				e.setDruable(1000);
				e.setAttributes(getRandomAttributes(4,7));
				e.setProperties(getRandomProperties(9,7));
				e.setSkills(getRandomSkills(3,2));
			}
		 		break;
		 	default:{
				e.setLevel(EquipmentLevelEnum.NORMAL);
				e.setWeight(50);
				e.setDruable(50);
				e.setAttributes(getRandomAttributes(0,0));
				e.setProperties(getRandomProperties(3,3));
				e.setSkills(getRandomSkills(0,3));
			}
		 		break;		
		}
		return (T) e;
	}
	private static List<BaseSingleSkill> getRandomSkills(int c, int range) {
		List<BaseSingleSkill> skills = new ArrayList<BaseSingleSkill>();
		if(c <=0 || range <=0) return skills;
		for(int i=0;i<c;i++){
			skills.add(getRandomSkill(skills,range));
		}
		return skills;
	}

	private static BaseSingleSkill getRandomSkill(List<BaseSingleSkill> skills, int range) {
		BaseSingleSkill s = null;
		do{
			s = getRandomSingleSkill(range);
		}while(skills.contains(s));
		return s;
	}
	private static BaseSingleSkill getRandomSingleSkill(int range) {
		Random random = new Random();
		SkillEnum a = SkillEnum.values()[random.nextInt(SkillEnum.values().length)];
		int v = random.nextInt(range) + 1;		
		return new BaseSingleSkill(a,v);
	}
	private static List<BaseProperty> getRandomProperties(int c, int range) {
		List<BaseProperty> properties = new ArrayList<BaseProperty>();
		if(c <=0 || range <=0) return properties;
		for(int i=0;i<c;i++){
			properties.add(getRandomProperty(properties,range));
		}
		return properties;
	}

	private static BaseProperty getRandomProperty(List<BaseProperty> properties, int range) {
		BaseProperty p = null;
		do{
			p = getRandomSingleProperty(range);
		}while(properties.contains(p));
		return p;
	}
	private static BaseProperty getRandomSingleProperty(int range) {
		Random random = new Random();
		PropertyEnum a = PropertyEnum.values()[random.nextInt(PropertyEnum.values().length)];
		int v = random.nextInt(range) + 1;		
		return new BaseProperty(a,v);
	}
	private static List<BaseAttribute> getRandomAttributes(int c,int range) {
		List<BaseAttribute> attributes = new ArrayList<BaseAttribute>();
		if(c <=0 || range <=0) return attributes;
		for(int i=0;i<c;i++){
			attributes.add(getRandomAttribute(attributes,range));
		}
		return attributes;
	}
	private static BaseAttribute getRandomSingleAttribute(int range) {
		Random random = new Random();
		AttributeEnum a = AttributeEnum.values()[random.nextInt(AttributeEnum.values().length)];
		int v = random.nextInt(range) + 1;		
		return new BaseAttribute(a,v);
	}
	private static BaseAttribute getRandomAttribute(List<BaseAttribute> attributes,int range) {
		BaseAttribute a = null;
		do{
			a = getRandomSingleAttribute(range);
		}while(attributes.contains(a));
		return a;
	}
	public static TextButtonStyle getTextButtonStyleByEquipmentLevel(IEquipment source,Skin skin){
		TextButtonStyle tbs = new TextButtonStyle();
		if(skin == null){
			return tbs;
		}
		if(source == null || source.getLevel() == null || source.getLevel().getColor() == null){
			tbs.font		= skin.getFont("default-font");
			tbs.up 			= skin.getDrawable("button01");
			tbs.down 		= skin.getDrawable("button02");
			tbs.fontColor 	= Color.WHITE;
		}else{
			tbs.font		= skin.getFont("default-font");
			tbs.up 			= skin.getDrawable("button01");
			tbs.down 		= skin.getDrawable("button02");
			tbs.fontColor 	= source.getLevel().getColor();
		}
		return tbs;
	}
	@SuppressWarnings("unchecked")
	public static <T extends IEquipment> T getEquippedByType(EquipmentClassEnum type,GameChar c){
		switch(type){
		case ARM:		return (T) c.getArm();
		case BODY:		return (T) c.getBody();
		case BOOTS:		return (T) c.getBoots();
		case GLOVE:		return (T) c.getGlove();
		case GUARDIAN:	return (T) c.getGuardian();
		case HEAD:		return (T) c.getHead();
		case LEFTHAND:	return (T) c.getLeftHand();
		case LEFTRING:	return (T) c.getLeftRing();
		case MOUNT:		return (T) c.getMount();
		case NECKLACE:	return (T) c.getNecklace();
		case PANTS:		return (T) c.getPants();
		case RIGHTHAND:	return (T) c.getRightHand();
		case RIGHTRING:	return (T) c.getRightRing();
		default:		return null;
		}		
	}
	@SuppressWarnings("unchecked")
	public static <T extends IEquipment> List<T> getEquipmentFromObjectsByType(EquipmentClassEnum type,GameChar c){
		Class<?> clazz = type.getEquipmentClass();
		List<T> list = new ArrayList<T>();
		for(IObject o : c.getObjects()){
			if(clazz.isInstance(o)){
				list.add((T)o);
			}
		}
		return list;
	}
	public static EquipmentClassEnum getEquipmentType(GameChar c,IEquipment e){
		if(e instanceof IArm)		return EquipmentClassEnum.ARM;
		if(e instanceof IBody)		return EquipmentClassEnum.BODY;		
		if(e instanceof IBoots)		return EquipmentClassEnum.BOOTS;
		if(e instanceof IGlove)		return EquipmentClassEnum.GLOVE;
		if(e instanceof IGuardian)	return EquipmentClassEnum.GUARDIAN;
		if(e instanceof IHead)		return EquipmentClassEnum.HEAD;
		if(e instanceof ILeftHand)	return EquipmentClassEnum.LEFTHAND;
		if(e instanceof IRightHand)	return EquipmentClassEnum.RIGHTHAND;
		if(e instanceof IMount)		return EquipmentClassEnum.MOUNT;
		if(e instanceof INecklace)	return EquipmentClassEnum.NECKLACE;
		if(e instanceof IPants)		return EquipmentClassEnum.PANTS;
		if(e instanceof IRing){
			if(c.getLeftRing() 	== null){return EquipmentClassEnum.LEFTRING;}
			if(c.getRightRing() == null){return EquipmentClassEnum.RIGHTRING;}		
			return EquipmentClassEnum.LEFTRING;
		}
		return null;							
	}
}
