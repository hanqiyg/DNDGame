package com.icesoft.libgdx.dnd.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.icesoft.libgdx.dnd.basic.BaseAttribute;
import com.icesoft.libgdx.dnd.basic.BaseProperty;
import com.icesoft.libgdx.dnd.basic.BaseRequirement;
import com.icesoft.libgdx.dnd.basic.BaseSingleSkill;
import com.icesoft.libgdx.dnd.basic.enums.AttributeEnum;
import com.icesoft.libgdx.dnd.basic.enums.EquipmentClassEnum;
import com.icesoft.libgdx.dnd.basic.enums.EquipmentLevelEnum;
import com.icesoft.libgdx.dnd.basic.enums.PropertyEnum;
import com.icesoft.libgdx.dnd.basic.enums.SkillEnum;
import com.icesoft.libgdx.dnd.basic.utils.EquipmentUtils;
import com.icesoft.libgdx.dnd.character.GameChar;
import com.icesoft.libgdx.dnd.equipment.Arm;
import com.icesoft.libgdx.dnd.equipment.Head;
import com.icesoft.libgdx.dnd.object.Bread;
import com.icesoft.libgdx.dnd.skill.interfaces.ISkill;
import com.icesoft.libgdx.dnd.skills.BaseSkill;

public class CreateWorld {
	public static GameChar getCharacter(){		
		GameChar c = new GameChar();
		c.setStr(100);
		c.setDex(50);
		c.setWiz(200);
		c.setSta(200);
		c.setHp(200);
		c.setMp(200);
		c.setSp(200);
		c.setName("character");
		c.setArm(getArm());
		c.addObject(getHead());
		c.addObject(getArm2());
		c.setSkills(getSkills());
		c.addObject(new Bread("bread", 500, 30, 10, 99,"eat","Eat"));
		c.addObject(EquipmentUtils.genEquipment("arm1",EquipmentLevelEnum.NORMAL, 	EquipmentClassEnum.ARM));
		c.addObject(EquipmentUtils.genEquipment("arm2",EquipmentLevelEnum.ENHANCED, EquipmentClassEnum.ARM));
		c.addObject(EquipmentUtils.genEquipment("arm3",EquipmentLevelEnum.RARE, 	EquipmentClassEnum.ARM));
		c.addObject(EquipmentUtils.genEquipment("arm4",EquipmentLevelEnum.UNIQUE, 	EquipmentClassEnum.ARM));
		c.addObject(EquipmentUtils.genEquipment("arm5",EquipmentLevelEnum.HEROIC, 	EquipmentClassEnum.ARM));
		c.addObject(EquipmentUtils.genEquipment("arm6",EquipmentLevelEnum.INHERIT, 	EquipmentClassEnum.ARM));
		c.addObject(EquipmentUtils.genEquipment("arm7",EquipmentLevelEnum.SUIT, 	EquipmentClassEnum.ARM));
		return c;
	}
	static Random random = new Random();
	public static Arm getArm(){
		Arm arm = new Arm();		
		arm.setName("LEGENDARM");
		arm.setLevel(EquipmentLevelEnum.HEROIC);
//		System.out.println("default"+arm.getName());
//		String gbk = new String(arm.getName().getBytes(),Charset.forName("GBK"));
//		System.out.println("gbk" + gbk);
//		String utf = new String(arm.getName().getBytes(),DNDGame.CHARSET);
//		System.out.println("utf-8" + utf);
//		String gb18030 = new String(arm.getName().getBytes(),Charset.forName("GB18030"));
//		System.out.println("gb18030" + gb18030);
//		String ISO8859 = new String(arm.getName().getBytes(),Charset.forName("ISO8859-1"));
//		System.out.println("ISO8859-1" + ISO8859);
//		String utf16 = new String(arm.getName().getBytes(),Charset.forName("UTF-16"));
//		System.out.println("utf16" + utf16);

		for(AttributeEnum a : AttributeEnum.values()){			
			arm.addAttribute(new BaseAttribute(a,random.nextInt(5)));
			arm.addRequirement(new BaseRequirement(a,random.nextInt(5)));
		}
		for(PropertyEnum p : PropertyEnum.values()){
			arm.addProperty(new BaseProperty(p,random.nextInt(5)));
		}
		for(SkillEnum s : SkillEnum.values()){
			arm.addSkill(new BaseSingleSkill(s,random.nextInt(2)));
		}
		arm.setWeight(200);
		arm.setDruable(100);
		return arm;
	}
	public static Map<String,Integer> getSingleSkill(){
		Map<String,Integer> skills = new HashMap<String,Integer>();
		skills.put("DODGE", 1);
		return skills;
	}
	public static Arm getArm2(){
		Arm arm = new Arm();
		arm.setName("Arm2");
		arm.setLevel(EquipmentLevelEnum.NORMAL);
		for(AttributeEnum a : AttributeEnum.values()){			
			arm.addAttribute(new BaseAttribute(a,random.nextInt(5)));
			arm.addRequirement(new BaseRequirement(a,random.nextInt(5)));
		}
		for(PropertyEnum p : PropertyEnum.values()){
			arm.addProperty(new BaseProperty(p,random.nextInt(5)));
		}
		for(SkillEnum s : SkillEnum.values()){
			arm.addSkill(new BaseSingleSkill(s,random.nextInt(2)));
		}
		arm.setWeight(200);
		arm.setDruable(100);
		return arm;
	}
	public static Head getHead(){		
		Head head = new Head();
		head.setName("Head1");
		head.setLevel(EquipmentLevelEnum.NORMAL);
		for(AttributeEnum a : AttributeEnum.values()){			
			head.addAttribute(new BaseAttribute(a,random.nextInt(5)));
			head.addRequirement(new BaseRequirement(a,random.nextInt(5)));
		}
		for(PropertyEnum p : PropertyEnum.values()){
			head.addProperty(new BaseProperty(p,random.nextInt(5)));
		}
		for(SkillEnum s : SkillEnum.values()){
			head.addSkill(new BaseSingleSkill(s,random.nextInt(2)));
		}
		head.setWeight(200);
		head.setDruable(100);
		return head;
	}
	public static Head getHead2(){		
		Head head = new Head();
		head.setName("Head2");
		head.setLevel(EquipmentLevelEnum.NORMAL);
		head.setWeight(50);
		return head;
	}
	public static List<ISkill> getSkills(){
		List<ISkill> skills = new ArrayList<ISkill>();
		skills.add(new BaseSkill(SkillEnum.DODGE,1350));
		skills.add(new BaseSkill(SkillEnum.PARRY,1250));
		return skills;
	}
}
