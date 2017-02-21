package com.icesoft.libgdx.dnd.basic.utils;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.utils.Json;
import com.icesoft.libgdx.dnd.basic.BaseProperty;
import com.icesoft.libgdx.dnd.basic.BaseRequirement;
import com.icesoft.libgdx.dnd.basic.BaseSingleSkill;
import com.icesoft.libgdx.dnd.basic.enums.AttributeEnum;
import com.icesoft.libgdx.dnd.basic.enums.EquipmentClassEnum;
import com.icesoft.libgdx.dnd.basic.enums.SkillEnum;
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
import com.icesoft.libgdx.dnd.state.IState;
import com.icesoft.libgdx.dnd.character.GameChar;
import com.icesoft.libgdx.dnd.character.GameCharExtra;

public class CharacterUtils {
	public static String print(GameChar c){
		return new Json().prettyPrint(c);
	}
	private static final int HP_STR = 10;
	private static final int HP_DEX= 10;
	private static final int HP_WIZ = 10;
	private static final int HP_STA = 10;
	public static int getMaxHP(int str,int dex,int wiz,int sta,List<BaseProperty> properties, List<BaseSingleSkill> skills,List<IState> states){
		int base = 	str * HP_STR + dex * HP_DEX + wiz * HP_WIZ + sta * HP_STA;
		int extra = base;
		extra += PropertyUtils.getMaxHP(base,properties);
		extra += SkillUtils.getMaxHP(base,skills);
		extra += StateUtils.getMaxHP(base,states);
		return extra;		
	}
	private static final int MP_STR = 10;
	private static final int MP_DEX= 10;
	private static final int MP_WIZ = 10;
	private static final int MP_STA = 10;
	public static int getMaxMP(int str,int dex,int wiz,int sta,List<BaseProperty> properties, List<BaseSingleSkill> skills,List<IState> states){
		int base = 	str * MP_STR + dex * MP_DEX + wiz * MP_WIZ + sta * MP_STA;
		int extra = base;
		extra += PropertyUtils.getMaxMP(base,properties);
		extra += SkillUtils.getMaxMP(base,skills);
		extra += StateUtils.getMaxMP(base,states);
		return extra;		
	}
	private static final int SP_STR = 10;
	private static final int SP_DEX= 10;
	private static final int SP_WIZ = 10;
	private static final int SP_STA = 10;
	public static int getMaxSP(int str,int dex,int wiz,int sta,List<BaseProperty> properties, List<BaseSingleSkill> skills,List<IState> states){			
		int base = 	str * SP_STR + dex * SP_DEX + wiz * SP_WIZ + sta * SP_STA;
		int extra = base;
		extra += PropertyUtils.getMaxSP(base,properties);
		extra += SkillUtils.getMaxSP(base,skills);
		extra += StateUtils.getMaxSP(base,states);
		return extra;		
	}
	private static final int WP_STR = 10;
	private static final int WP_DEX= 10;
	private static final int WP_WIZ = 10;
	private static final int WP_STA = 10;
	public static int getMaxWP(int str,int dex,int wiz,int sta,List<BaseProperty> properties, List<BaseSingleSkill> skills,List<IState> states){		
		int base = 	str * WP_STR + dex * WP_DEX + wiz * WP_WIZ + sta * WP_STA;
		int extra = base;
		extra += PropertyUtils.getMaxWP(base,properties);
		extra += SkillUtils.getMaxWP(base,skills);
		extra += StateUtils.getMaxWP(base,states);
		return extra;		
	}
	private static final int MAX_PHYSICAL_DAMAGE_STR = 10;
	private static final int MAX_PHYSICAL_DAMAGE_DEX= 10;
	private static final int MAX_PHYSICAL_DAMAGE_WIZ = 10;
	private static final int MAX_PHYSICAL_DAMAGE_STA = 10;
	public static int getMaxPhysicalDamage( int str, int dex, int wiz, int sta,List<BaseProperty> properties, List<BaseSingleSkill> skills, List<IState> states){
		int base = 	str * MAX_PHYSICAL_DAMAGE_STR + 
					dex * MAX_PHYSICAL_DAMAGE_DEX + 
					wiz * MAX_PHYSICAL_DAMAGE_WIZ + 
					sta * MAX_PHYSICAL_DAMAGE_STA;
		int extra = base;
		extra += PropertyUtils.getMaxPhysicalDamage(base,properties);
		extra += SkillUtils.getMaxPhysicalDamage(base,skills);
		extra += StateUtils.getMaxPhysicalDamage(base,states);
		return extra;		
	}
	private static final int MAX_FIRE_DAMAGE_STR = 10;
	private static final int MAX_FIRE_DAMAGE_DEX= 10;
	private static final int MAX_FIRE_DAMAGE_WIZ = 10;
	private static final int MAX_FIRE_DAMAGE_STA = 10;
	public static int getMaxFireDamage( int str, int dex, int wiz, int sta,List<BaseProperty> properties, List<BaseSingleSkill> skills, List<IState> states){
		int base =	str * MAX_FIRE_DAMAGE_STR + 
					dex * MAX_FIRE_DAMAGE_DEX + 
					wiz * MAX_FIRE_DAMAGE_WIZ + 
					sta * MAX_FIRE_DAMAGE_STA;
		int extra = base;
		extra += PropertyUtils.getMaxFireDamage(base,properties);
		extra += SkillUtils.getMaxFireDamage(base,skills);
		extra += StateUtils.getMaxFireDamage(base,states);
		return extra;		
	}
	private static final int MAX_COLD_DAMAGE_STR = 10;
	private static final int MAX_COLD_DAMAGE_DEX= 10;
	private static final int MAX_COLD_DAMAGE_WIZ = 10;
	private static final int MAX_COLD_DAMAGE_STA = 10;
	public static int getMaxColdDamage( int str, int dex, int wiz, int sta,List<BaseProperty> properties, List<BaseSingleSkill> skills, List<IState> states){
		int base =	str * MAX_COLD_DAMAGE_STR + 
					dex * MAX_COLD_DAMAGE_DEX + 
					wiz * MAX_COLD_DAMAGE_WIZ + 
					sta * MAX_COLD_DAMAGE_STA;
		int extra = base;
		extra += PropertyUtils.getMaxColdDamage(base,properties);
		extra += SkillUtils.getMaxColdDamage(base,skills);
		extra += StateUtils.getMaxColdDamage(base,states);
		return extra;		
	}
	private static final int MAX_POISON_DAMAGE_STR = 10;
	private static final int MAX_POISON_DAMAGE_DEX= 10;
	private static final int MAX_POISON_DAMAGE_WIZ = 10;
	private static final int MAX_POISON_DAMAGE_STA = 10;
	public static int getMaxPoisonDamage( int str, int dex, int wiz, int sta,List<BaseProperty> properties, List<BaseSingleSkill> skills, List<IState> states){
		int base = 	str * MAX_POISON_DAMAGE_STR + 
					dex * MAX_POISON_DAMAGE_DEX + 
					wiz * MAX_POISON_DAMAGE_WIZ + 
					sta * MAX_POISON_DAMAGE_STA;
		int extra = base;
		extra += PropertyUtils.getMaxPoisonDamage(base,properties);
		extra += SkillUtils.getMaxPoisonDamage(base,skills);
		extra += StateUtils.getMaxPoisonDamage(base,states);
		return extra;		
	}	
	private static final int MAX_ENERGY_DAMAGEDAMAGE_STR = 10;
	private static final int MAX_ENERGY_DAMAGEDAMAGE_DEX= 10;
	private static final int MAX_ENERGY_DAMAGEDAMAGE_WIZ = 10;
	private static final int MAX_ENERGY_DAMAGEDAMAGE_STA = 10;
	public static int getMaxEnergyDamage( int str, int dex, int wiz, int sta,List<BaseProperty> properties, List<BaseSingleSkill> skills, List<IState> states){
		int base = 	 str * MAX_ENERGY_DAMAGEDAMAGE_STR + 
					  dex * MAX_ENERGY_DAMAGEDAMAGE_DEX + 
					  wiz * MAX_ENERGY_DAMAGEDAMAGE_WIZ + 
					  sta * MAX_ENERGY_DAMAGEDAMAGE_STA;
		int extra = base;
		extra += PropertyUtils.getMaxEnergyDamage(base,properties);
		extra += SkillUtils.getMaxEnergyDamage(base,skills);
		extra += StateUtils.getMaxEnergyDamage(base,states);
		return extra;		
	}
	private static final int MIN_PHYSICAL_DAMAGE_STR = 10;
	private static final int MIN_PHYSICAL_DAMAGE_DEX= 10;
	private static final int MIN_PHYSICAL_DAMAGE_WIZ = 10;
	private static final int MIN_PHYSICAL_DAMAGE_STA = 10;
	public static int getMinPhysicalDamage( int str, int dex, int wiz, int sta,List<BaseProperty> properties, List<BaseSingleSkill> skills, List<IState> states){
		int base = 	  str * MIN_PHYSICAL_DAMAGE_STR + 
					  dex * MIN_PHYSICAL_DAMAGE_DEX + 
					  wiz * MIN_PHYSICAL_DAMAGE_WIZ + 
					  sta * MIN_PHYSICAL_DAMAGE_STA;
		int extra = base;
		extra += PropertyUtils.getMinPhysicalDamage(base,properties);
		extra += SkillUtils.getMinPhysicalDamage(base,skills);
		extra += StateUtils.getMinPhysicalDamage(base,states);
		return extra;		
	}
	private static final int MIN_FIRE_DAMAGE_STR = 10;
	private static final int MIN_FIRE_DAMAGE_DEX= 10;
	private static final int MIN_FIRE_DAMAGE_WIZ = 10;
	private static final int MIN_FIRE_DAMAGE_STA = 10;
	public static int getMinFireDamage( int str, int dex, int wiz, int sta,List<BaseProperty> properties, List<BaseSingleSkill> skills, List<IState> states){
		int base = 	  str * MIN_FIRE_DAMAGE_STR + 
					  dex * MIN_FIRE_DAMAGE_DEX + 
					  wiz * MIN_FIRE_DAMAGE_WIZ + 
					  sta * MIN_FIRE_DAMAGE_STA;
		int extra = base;
		extra += PropertyUtils.getMinFireDamage(base,properties);
		extra += SkillUtils.getMinFireDamage(base,skills);
		extra += StateUtils.getMinFireDamage(base,states);
		return extra;		
	}
	private static final int MIN_COLD_DAMAGE_STR = 10;
	private static final int MIN_COLD_DAMAGE_DEX= 10;
	private static final int MIN_COLD_DAMAGE_WIZ = 10;
	private static final int MIN_COLD_DAMAGE_STA = 10;
	public static int getMinColdDamage( int str, int dex, int wiz, int sta,List<BaseProperty> properties, List<BaseSingleSkill> skills, List<IState> states){
		int base = 	  str * MIN_COLD_DAMAGE_STR + 
					  dex * MIN_COLD_DAMAGE_DEX + 
					  wiz * MIN_COLD_DAMAGE_WIZ + 
					  sta * MIN_COLD_DAMAGE_STA;
		int extra = base;
		extra += PropertyUtils.getMinColdDamage(base,properties);
		extra += SkillUtils.getMinColdDamage(base,skills);
		extra += StateUtils.getMinColdDamage(base,states);
		return extra;		
	}
	private static final int MIN_POISON_DAMAGE_STR = 10;
	private static final int MIN_POISON_DAMAGE_DEX= 10;
	private static final int MIN_POISON_DAMAGE_WIZ = 10;
	private static final int MIN_POISON_DAMAGE_STA = 10;
	public static int getMinPoisonDamage( int str, int dex, int wiz, int sta,List<BaseProperty> properties, List<BaseSingleSkill> skills, List<IState> states){
		int base = 	  str * MIN_POISON_DAMAGE_STR + 
					  dex * MIN_POISON_DAMAGE_DEX + 
					  wiz * MIN_POISON_DAMAGE_WIZ + 
					  sta * MIN_POISON_DAMAGE_STA;
		int extra = base;
		extra += PropertyUtils.getMinPoisonDamage(base,properties);
		extra += SkillUtils.getMinPoisonDamage(base,skills);
		extra += StateUtils.getMinPoisonDamage(base,states);
		return extra;		
	}	
	private static final int MIN_ENERGY_DAMAGEDAMAGE_STR = 10;
	private static final int MIN_ENERGY_DAMAGEDAMAGE_DEX= 10;
	private static final int MIN_ENERGY_DAMAGEDAMAGE_WIZ = 10;
	private static final int MIN_ENERGY_DAMAGEDAMAGE_STA = 10;
	public static int getMinEnergyDamage( int str, int dex, int wiz, int sta,List<BaseProperty> properties, List<BaseSingleSkill> skills, List<IState> states){
		int base = 	  str * MIN_ENERGY_DAMAGEDAMAGE_STR + 
					  dex * MIN_ENERGY_DAMAGEDAMAGE_DEX + 
					  wiz * MIN_ENERGY_DAMAGEDAMAGE_WIZ + 
					  sta * MIN_ENERGY_DAMAGEDAMAGE_STA;
		int extra = base;
		extra += PropertyUtils.getMinEnergyDamage(base,properties);
		extra += SkillUtils.getMinEnergyDamage(base,skills);
		extra += StateUtils.getMinEnergyDamage(base,states);
		return extra;		
	}
	
	private static final int PHYSICAL_ARMOR_STR = 10;
	private static final int PHYSICAL_ARMOR_DEX= 10;
	private static final int PHYSICAL_ARMOR_WIZ = 10;
	private static final int PHYSICAL_ARMOR_STA = 10;
	public static int getPhysicalArmor( int str, int dex, int wiz, int sta,List<BaseProperty> properties, List<BaseSingleSkill> skills, List<IState> states){
		int base = 	  str * PHYSICAL_ARMOR_STR + 
					  dex * PHYSICAL_ARMOR_DEX + 
					  wiz * PHYSICAL_ARMOR_WIZ + 
					  sta * PHYSICAL_ARMOR_STA;
		int extra = base;
		extra += PropertyUtils.getPhysicalArmor(base,properties);
		extra += SkillUtils.getPhysicalArmor(base,skills);
		extra += StateUtils.getPhysicalArmor(base,states);
		return extra;		
	}
	private static final int FIRE_RESIST_STR = 10;
	private static final int FIRE_RESIST_DEX= 10;
	private static final int FIRE_RESIST_WIZ = 10;
	private static final int FIRE_RESIST_STA = 10;
	public static int getFireResist( int str, int dex, int wiz, int sta,List<BaseProperty> properties, List<BaseSingleSkill> skills, List<IState> states){
		int base = 	  str * FIRE_RESIST_STR + 
					  dex * FIRE_RESIST_DEX + 
					  wiz * FIRE_RESIST_WIZ + 
					  sta * FIRE_RESIST_STA;
		int extra = base;
		extra += PropertyUtils.getFireResist(base,properties);
		extra += SkillUtils.getFireResist(base,skills);
		extra += StateUtils.getFireResist(base,states);
		return extra;		
	}
	private static final int COLD_RESIST_STR = 10;
	private static final int COLD_RESIST_DEX= 10;
	private static final int COLD_RESIST_WIZ = 10;
	private static final int COLD_RESIST_STA = 10;
	public static int getColdResist( int str, int dex, int wiz, int sta,List<BaseProperty> properties, List<BaseSingleSkill> skills, List<IState> states){
		int base = 	  str * COLD_RESIST_STR + 
					  dex * COLD_RESIST_DEX + 
					  wiz * COLD_RESIST_WIZ + 
					  sta * COLD_RESIST_STA;
		int extra = base;
		extra += PropertyUtils.getColdResist(base,properties);
		extra += SkillUtils.getColdResist(base,skills);
		extra += StateUtils.getColdResist(base,states);
		return extra;		
	}
	private static final int POISON_RESIST_STR = 10;
	private static final int POISON_RESIST_DEX= 10;
	private static final int POISON_RESIST_WIZ = 10;
	private static final int POISON_RESIST_STA = 10;
	public static int getPoisonResist( int str, int dex, int wiz, int sta,List<BaseProperty> properties, List<BaseSingleSkill> skills, List<IState> states){
		int base = 	  str * POISON_RESIST_STR + 
					  dex * POISON_RESIST_DEX + 
					  wiz * POISON_RESIST_WIZ + 
					  sta * POISON_RESIST_STA;
		int extra = base;
		extra += PropertyUtils.getPoisonResist(base,properties);
		extra += SkillUtils.getPoisonResist(base,skills);
		extra += StateUtils.getPoisonResist(base,states);
		return extra;		
	}	
	private static final int ENERGY_RESIST_STR = 10;
	private static final int ENERGY_RESIST_DEX= 10;
	private static final int ENERGY_RESIST_WIZ = 10;
	private static final int ENERGY_RESIST_STA = 10;
	public static int getEnergyResist( int str, int dex, int wiz, int sta,List<BaseProperty> properties, List<BaseSingleSkill> skills, List<IState> states){
		int base = 	  str * ENERGY_RESIST_STR + 
					  dex * ENERGY_RESIST_DEX + 
					  wiz * ENERGY_RESIST_WIZ + 
					  sta * ENERGY_RESIST_STA;
		int extra = base;
		extra += PropertyUtils.getEnergyResist(base,properties);
		extra += SkillUtils.getEnergyResist(base,skills);
		extra += StateUtils.getEnergyResist(base,states);
		return extra;		
	}
	private static final int PHYSICAL_ARMOR_TO_RANGE_STR = 10;
	private static final int PHYSICAL_ARMOR_TO_RANGE_DEX= 10;
	private static final int PHYSICAL_ARMOR_TO_RANGE_WIZ = 10;
	private static final int PHYSICAL_ARMOR_TO_RANGE_STA = 10;
	public static int getPhysicalArmorToRange(int physicalArmor, int str, int dex, int wiz, int sta,List<BaseProperty> properties, List<BaseSingleSkill> skills, List<IState> states){
		int base = physicalArmor;
		base =	base + 	str * PHYSICAL_ARMOR_TO_RANGE_STR + 
						dex * PHYSICAL_ARMOR_TO_RANGE_DEX + 
						wiz * PHYSICAL_ARMOR_TO_RANGE_WIZ + 
						sta * PHYSICAL_ARMOR_TO_RANGE_STA;
		int extra = base;
		extra += PropertyUtils.getPhysicalArmorToRange(base,properties);
		extra += SkillUtils.getPhysicalArmorToRange(base,skills);
		extra += StateUtils.getPhysicalArmorToRange(base,states);
		return extra;
	}
	private static final int PHYSICAL_ARMOR_TO_MELEE_STR = 10;
	private static final int PHYSICAL_ARMOR_TO_MELEE_DEX= 10;
	private static final int PHYSICAL_ARMOR_TO_MELEE_WIZ = 10;
	private static final int PHYSICAL_ARMOR_TO_MELEE_STA = 10;
	public static int getPhysicalArmorToMelee(int physicalArmor, int str, int dex, int wiz, int sta,List<BaseProperty> properties, List<BaseSingleSkill> skills, List<IState> states){
		int base = physicalArmor;
		base =	base + 	str * PHYSICAL_ARMOR_TO_MELEE_STR + 
						dex * PHYSICAL_ARMOR_TO_MELEE_DEX + 
						wiz * PHYSICAL_ARMOR_TO_MELEE_WIZ + 
						sta * PHYSICAL_ARMOR_TO_MELEE_STA;
		int extra = base;
		extra += PropertyUtils.getPhysicalArmorToMelee(base,properties);
		extra += SkillUtils.getPhysicalArmorToMelee(base,skills);
		extra += StateUtils.getPhysicalArmorToMelee(base,states);
		return extra;
	}
	private static final int MAX_PHYSICAL_DAMAGE_TO_RANGE_STR = 10;
	private static final int MAX_PHYSICAL_DAMAGE_TO_RANGE_DEX= 10;
	private static final int MAX_PHYSICAL_DAMAGE_TO_RANGE_WIZ = 10;
	private static final int MAX_PHYSICAL_DAMAGE_TO_RANGE_STA = 10;
	public static int getMaxPhysicalDamageToRange(int maxPhysicalDamage, int str, int dex, int wiz, int sta,List<BaseProperty> properties, List<BaseSingleSkill> skills, List<IState> states){
		int base = maxPhysicalDamage;
		base =	base + 	str * MAX_PHYSICAL_DAMAGE_TO_RANGE_STR + 
						dex * MAX_PHYSICAL_DAMAGE_TO_RANGE_DEX + 
						wiz * MAX_PHYSICAL_DAMAGE_TO_RANGE_WIZ + 
						sta * MAX_PHYSICAL_DAMAGE_TO_RANGE_STA;
		int extra = base;
		extra += PropertyUtils.getPhysicalDamageToRange(base,properties);
		extra += SkillUtils.getPhysicalDamageToRange(base,skills);
		extra += StateUtils.getPhysicalDamageToRange(base,states);
		return extra;
	}
	private static final int MIN_PHYSICAL_DAMAGE_TO_RANGE_STR = 10;
	private static final int MIN_PHYSICAL_DAMAGE_TO_RANGE_DEX= 10;
	private static final int MIN_PHYSICAL_DAMAGE_TO_RANGE_WIZ = 10;
	private static final int MIN_PHYSICAL_DAMAGE_TO_RANGE_STA = 10;
	public static int getMinPhysicalDamageToRange(int minxPhysicalDamage, int str, int dex, int wiz, int sta,List<BaseProperty> properties, List<BaseSingleSkill> skills, List<IState> states){
		int base = minxPhysicalDamage;
		base =	base + 	str * MIN_PHYSICAL_DAMAGE_TO_RANGE_STR + 
						dex * MIN_PHYSICAL_DAMAGE_TO_RANGE_DEX + 
						wiz * MIN_PHYSICAL_DAMAGE_TO_RANGE_WIZ + 
						sta * MIN_PHYSICAL_DAMAGE_TO_RANGE_STA;
		int extra = base;
		extra += PropertyUtils.getPhysicalDamageToRange(base,properties);
		extra += SkillUtils.getPhysicalDamageToRange(base,skills);
		extra += StateUtils.getPhysicalDamageToRange(base,states);
		return extra;
	}
	private static final int MAX_PHYSICAL_DAMAGE_TO_MELEE_STR = 10;
	private static final int MAX_PHYSICAL_DAMAGE_TO_MELEE_DEX= 10;
	private static final int MAX_PHYSICAL_DAMAGE_TO_MELEE_WIZ = 10;
	private static final int MAX_PHYSICAL_DAMAGE_TO_MELEE_STA = 10;
	public static int getMaxPhysicalDamageToMelee(int maxPhysicalDamage, int str, int dex, int wiz, int sta,List<BaseProperty> properties, List<BaseSingleSkill> skills, List<IState> states){
		int base = maxPhysicalDamage;
		base =	base + 	str * MAX_PHYSICAL_DAMAGE_TO_MELEE_STR + 
						dex * MAX_PHYSICAL_DAMAGE_TO_MELEE_DEX + 
						wiz * MAX_PHYSICAL_DAMAGE_TO_MELEE_WIZ + 
						sta * MAX_PHYSICAL_DAMAGE_TO_MELEE_STA;
		int extra = base;
		extra += PropertyUtils.getPhysicalDamageToMelee(base,properties);
		extra += SkillUtils.getPhysicalDamageToMelee(base,skills);
		extra += StateUtils.getPhysicalDamageToMelee(base,states);
		return extra;
	}
	private static final int MIN_PHYSICAL_DAMAGE_TO_MELEE_STR = 10;
	private static final int MIN_PHYSICAL_DAMAGE_TO_MELEE_DEX= 10;
	private static final int MIN_PHYSICAL_DAMAGE_TO_MELEE_WIZ = 10;
	private static final int MIN_PHYSICAL_DAMAGE_TO_MELEE_STA = 10;
	public static int getMinPhysicalDamageToMelee(int minPhysicalDamage, int str, int dex, int wiz, int sta,List<BaseProperty> properties, List<BaseSingleSkill> skills, List<IState> states){
		int base = minPhysicalDamage;
		base =	base + 	str * MIN_PHYSICAL_DAMAGE_TO_MELEE_STR + 
						dex * MIN_PHYSICAL_DAMAGE_TO_MELEE_DEX + 
						wiz * MIN_PHYSICAL_DAMAGE_TO_MELEE_WIZ + 
						sta * MIN_PHYSICAL_DAMAGE_TO_MELEE_STA;
		int extra = base;
		extra += PropertyUtils.getPhysicalDamageToMelee(base,properties);
		extra += SkillUtils.getPhysicalDamageToMelee(base,skills);
		extra += StateUtils.getPhysicalDamageToMelee(base,states);
		return extra;
	}
	private static final int MAX_PHYSICAL_DAMAGE_TO_UNDEAD_STR = 10;
	private static final int MAX_PHYSICAL_DAMAGE_TO_UNDEAD_DEX= 10;
	private static final int MAX_PHYSICAL_DAMAGE_TO_UNDEAD_WIZ = 10;
	private static final int MAX_PHYSICAL_DAMAGE_TO_UNDEAD_STA = 10;
	public static int getMaxPhysicalDamageToUndead(int maxPhysicalDamage, int str, int dex, int wiz, int sta,List<BaseProperty> properties, List<BaseSingleSkill> skills, List<IState> states){
		int base = maxPhysicalDamage;
		base =	base + 	str * MAX_PHYSICAL_DAMAGE_TO_UNDEAD_STR + 
						dex * MAX_PHYSICAL_DAMAGE_TO_UNDEAD_DEX + 
						wiz * MAX_PHYSICAL_DAMAGE_TO_UNDEAD_WIZ + 
						sta * MAX_PHYSICAL_DAMAGE_TO_UNDEAD_STA;
		int extra = base;
		extra += PropertyUtils.getPhysicalDamageToUndead(base,properties);
		extra += SkillUtils.getPhysicalDamageToUndead(base,skills);
		extra += StateUtils.getPhysicalDamageToUndead(base,states);
		return extra;
	}
	private static final int MIN_PHYSICAL_DAMAGE_TO_UNDEAD_STR = 10;
	private static final int MIN_PHYSICAL_DAMAGE_TO_UNDEAD_DEX= 10;
	private static final int MIN_PHYSICAL_DAMAGE_TO_UNDEAD_WIZ = 10;
	private static final int MIN_PHYSICAL_DAMAGE_TO_UNDEAD_STA = 10;
	public static int getMinPhysicalDamageToUndead(int minPhysicalDamage, int str, int dex, int wiz, int sta,List<BaseProperty> properties, List<BaseSingleSkill> skills, List<IState> states){
		int base = minPhysicalDamage;
		base =	base + 	str * MIN_PHYSICAL_DAMAGE_TO_UNDEAD_STR + 
						dex * MIN_PHYSICAL_DAMAGE_TO_UNDEAD_DEX + 
						wiz * MIN_PHYSICAL_DAMAGE_TO_UNDEAD_WIZ + 
						sta * MIN_PHYSICAL_DAMAGE_TO_UNDEAD_STA;
		int extra = base;
		extra += PropertyUtils.getPhysicalDamageToUndead(base,properties);
		extra += SkillUtils.getPhysicalDamageToUndead(base,skills);
		extra += StateUtils.getPhysicalDamageToUndead(base,states);
		return extra;
	}
	private static final int MAX_PHYSICAL_DAMAGE_TO_DEMON_STR = 10;
	private static final int MAX_PHYSICAL_DAMAGE_TO_DEMON_DEX= 10;
	private static final int MAX_PHYSICAL_DAMAGE_TO_DEMON_WIZ = 10;
	private static final int MAX_PHYSICAL_DAMAGE_TO_DEMON_STA = 10;
	public static int getMaxPhysicalDamageToDemon(int maxPhysicalDamage, int str, int dex, int wiz, int sta,List<BaseProperty> properties, List<BaseSingleSkill> skills, List<IState> states){
		int base = maxPhysicalDamage;
		base =	base + 	str * MAX_PHYSICAL_DAMAGE_TO_DEMON_STR + 
						dex * MAX_PHYSICAL_DAMAGE_TO_DEMON_DEX + 
						wiz * MAX_PHYSICAL_DAMAGE_TO_DEMON_WIZ + 
						sta * MAX_PHYSICAL_DAMAGE_TO_DEMON_STA;
		int extra = base;
		extra += PropertyUtils.getPhysicalDamageToDemon(base,properties);
		extra += SkillUtils.getPhysicalDamageToDemon(base,skills);
		extra += StateUtils.getPhysicalDamageToDemon(base,states);
		return extra;
	}
	private static final int MIN_PHYSICAL_DAMAGE_TO_DEMON_STR = 10;
	private static final int MIN_PHYSICAL_DAMAGE_TO_DEMON_DEX= 10;
	private static final int MIN_PHYSICAL_DAMAGE_TO_DEMON_WIZ = 10;
	private static final int MIN_PHYSICAL_DAMAGE_TO_DEMON_STA = 10;
	public static int getMinPhysicalDamageToDemon(int minPhysicalDamage, int str, int dex, int wiz, int sta,List<BaseProperty> properties, List<BaseSingleSkill> skills, List<IState> states){
		int base = minPhysicalDamage;
		base =	base + 	str * MIN_PHYSICAL_DAMAGE_TO_DEMON_STR + 
						dex * MIN_PHYSICAL_DAMAGE_TO_DEMON_DEX + 
						wiz * MIN_PHYSICAL_DAMAGE_TO_DEMON_WIZ + 
						sta * MIN_PHYSICAL_DAMAGE_TO_DEMON_STA;
		int extra = base;
		extra += PropertyUtils.getPhysicalDamageToDemon(base,properties);
		extra += SkillUtils.getPhysicalDamageToDemon(base,skills);
		extra += StateUtils.getPhysicalDamageToDemon(base,states);
		return extra;
	}
	private static final int MAX_PHYSICAL_DAMAGE_TO_DRAGON_STR = 10;
	private static final int MAX_PHYSICAL_DAMAGE_TO_DRAGON_DEX= 10;
	private static final int MAX_PHYSICAL_DAMAGE_TO_DRAGON_WIZ = 10;
	private static final int MAX_PHYSICAL_DAMAGE_TO_DRAGON_STA = 10;
	public static int getMaxPhysicalDamageToDragon(int maxPhysicalDamage, int str, int dex, int wiz, int sta,List<BaseProperty> properties, List<BaseSingleSkill> skills, List<IState> states){
		int base = maxPhysicalDamage;
		base =	base + 	str * MAX_PHYSICAL_DAMAGE_TO_DRAGON_STR + 
						dex * MAX_PHYSICAL_DAMAGE_TO_DRAGON_DEX + 
						wiz * MAX_PHYSICAL_DAMAGE_TO_DRAGON_WIZ + 
						sta * MAX_PHYSICAL_DAMAGE_TO_DRAGON_STA;
		int extra = base;
		extra += PropertyUtils.getPhysicalDamageToDragon(base,properties);
		extra += SkillUtils.getPhysicalDamageToDragon(base,skills);
		extra += StateUtils.getPhysicalDamageToDragon(base,states);
		return extra;
	}
	private static final int MIN_PHYSICAL_DAMAGE_TO_DRAGON_STR = 10;
	private static final int MIN_PHYSICAL_DAMAGE_TO_DRAGON_DEX= 10;
	private static final int MIN_PHYSICAL_DAMAGE_TO_DRAGON_WIZ = 10;
	private static final int MIN_PHYSICAL_DAMAGE_TO_DRAGON_STA = 10;
	public static int getMinPhysicalDamageToDragon(int minPhysicalDamage, int str, int dex, int wiz, int sta,List<BaseProperty> properties, List<BaseSingleSkill> skills, List<IState> states){
		int base = minPhysicalDamage;
		base =	base + 	str * MIN_PHYSICAL_DAMAGE_TO_DRAGON_STR + 
						dex * MIN_PHYSICAL_DAMAGE_TO_DRAGON_DEX + 
						wiz * MIN_PHYSICAL_DAMAGE_TO_DRAGON_WIZ + 
						sta * MIN_PHYSICAL_DAMAGE_TO_DRAGON_STA;
		int extra = base;
		extra += PropertyUtils.getPhysicalDamageToDragon(base,properties);
		extra += SkillUtils.getPhysicalDamageToDragon(base,skills);
		extra += StateUtils.getPhysicalDamageToDragon(base,states);
		return extra;
	}
	private static final int REGEN_HP_STR = 1;
	private static final int REGEN_HP_DEX= 1;
	private static final int REGEN_HP_WIZ = 1;
	private static final int REGEN_HP_STA = 2;
	public static int getRegenHP( int str, int dex, int wiz, int sta,List<BaseProperty> properties, List<BaseSingleSkill> skills, List<IState> states){
		int base = 	str * REGEN_HP_STR + 
					dex * REGEN_HP_DEX + 
					wiz * REGEN_HP_WIZ + 
					sta * REGEN_HP_STA;
		int extra = base;
		extra += PropertyUtils.getRegenHP(base,properties);
		extra += SkillUtils.getRegenHP(base,skills);
		extra += StateUtils.getRegenHP(base,states);
		return extra;
	}
	private static final int REGEN_MP_STR = 1;
	private static final int REGEN_MP_DEX= 1;
	private static final int REGEN_MP_WIZ = 3;
	private static final int REGEN_MP_STA = 1;
	public static int getRegenMP( int str, int dex, int wiz, int sta,List<BaseProperty> properties, List<BaseSingleSkill> skills, List<IState> states){
		int base = 	str * REGEN_MP_STR + 
					dex * REGEN_MP_DEX + 
					wiz * REGEN_MP_WIZ + 
					sta * REGEN_MP_STA;
		int extra = base;
		extra += PropertyUtils.getRegenMP(base,properties);
		extra += SkillUtils.getRegenMP(base,skills);
		extra += StateUtils.getRegenMP(base,states);
		return extra;
	}
	private static final int REGEN_SP_STR = 2;
	private static final int REGEN_SP_DEX= 1;
	private static final int REGEN_SP_WIZ = 1;
	private static final int REGEN_SP_STA = 1;
	public static int getRegenSP( int str, int dex, int wiz, int sta,List<BaseProperty> properties, List<BaseSingleSkill> skills, List<IState> states){
		int base = 	str * REGEN_SP_STR + 
					dex * REGEN_SP_DEX + 
					wiz * REGEN_SP_WIZ + 
					sta * REGEN_SP_STA;
		int extra = base;
		extra += PropertyUtils.getRegenSP(base,properties);
		extra += SkillUtils.getRegenSP(base,skills);
		extra += StateUtils.getRegenSP(base,states);
		return extra;
	}
	public static void addHP(GameChar c,int max, int add){
		int hp = c.getHp() + add;
		if(hp > max){
			c.setHp(max);
		}else{
			c.setHp(hp);
		}
	}
	private static final int AIM_STR = 10;
	private static final int AIM_DEX= 10;
	private static final int AIM_WIZ = 10;
	private static final int AIM_STA = 10;
	public static int getAim( int str, int dex, int wiz, int sta,List<BaseProperty> properties, List<BaseSingleSkill> skills, List<IState> states){
		int base = 	str * AIM_STR + dex * AIM_DEX + wiz * AIM_WIZ + sta * AIM_STA;
		int extra = base;
		extra += PropertyUtils.getAim(base,properties);
		extra += SkillUtils.getAim(base,skills);
		extra += StateUtils.getAim(base,states);
		return extra;
	}
	private static final int DODGE_STR = 10;
	private static final int DODGE_DEX= 10;
	private static final int DODGE_WIZ = 10;
	private static final int DODGE_STA = 10;
	public static int getDodge( int str, int dex, int wiz, int sta, List<BaseProperty> properties, List<BaseSingleSkill> skills, List<IState> states){
		int base = 	str * DODGE_STR + 
					dex * DODGE_DEX + 
					wiz * DODGE_WIZ + 
					sta * DODGE_STA;
		int extra = base;
		extra += PropertyUtils.getDodge(base,properties);
		extra += SkillUtils.getDodge(base,skills);
		extra += StateUtils.getDodge(base,states);
		return extra;
	}
	private static final int CRITICAL_STR = 10;
	private static final int CRITICAL_DEX= 10;
	private static final int CRITICAL_WIZ = 10;
	private static final int CRITICAL_STA = 10;
	public static int getCritical( int str, int dex, int wiz, int sta, List<BaseProperty> properties, List<BaseSingleSkill> skills, List<IState> states){
		int base = 	str * CRITICAL_STR + 
					dex * CRITICAL_DEX + 
					wiz * CRITICAL_WIZ + 
					sta * CRITICAL_STA;
		int extra = base;
		extra += PropertyUtils.getCritical(base,properties);
		extra += SkillUtils.getCritical(base,skills);
		extra += StateUtils.getCritical(base,states);
		return extra;
	}
	private static final int SPEED_STR = 10;
	private static final int SPEED_DEX= 10;
	private static final int SPEED_WIZ = 10;
	private static final int SPEED_STA = 10;
	public static int getSpeed( int str, int dex, int wiz, int sta, List<BaseProperty> properties, List<BaseSingleSkill> skills, List<IState> states){
		int base = 	str * SPEED_STR + 
					dex * SPEED_DEX + 
					wiz * SPEED_WIZ + 
					sta * SPEED_STA;
		int extra = base;
		extra += PropertyUtils.getSpeed(base,properties);
		extra += SkillUtils.getSpeed(base,skills);
		extra += StateUtils.getSpeed(base,states);
		return extra;
	}
	private static final int STUN_STR = 10;
	private static final int STUN_DEX= 10;
	private static final int STUN_WIZ = 10;
	private static final int STUN_STA = 10;
	public static int getStunChance( int str, int dex, int wiz, int sta, List<BaseProperty> properties, List<BaseSingleSkill> skills, List<IState> states){
		int base = 	str * STUN_STR + 
					dex * STUN_DEX + 
					wiz * STUN_WIZ + 
					sta * STUN_STA;
		int extra = base;
		extra += PropertyUtils.getStunChance(base,properties);
		extra += SkillUtils.getStunChance(base,skills);
		extra += StateUtils.getStunChance(base,states);
		return extra;
	}
	private static final int FREZEN_STR = 10;
	private static final int FREZEN_DEX= 10;
	private static final int FREZEN_WIZ = 10;
	private static final int FREZEN_STA = 10;
	public static int getFrezenChance( int str, int dex, int wiz, int sta, List<BaseProperty> properties, List<BaseSingleSkill> skills, List<IState> states){
		int base = 	str * FREZEN_STR + 
					dex * FREZEN_DEX + 
					wiz * FREZEN_WIZ + 
					sta * FREZEN_STA;
		int extra = base;
		extra += PropertyUtils.getFrezenChance(base,properties);
		extra += SkillUtils.getFrezenChance(base,skills);
		extra += StateUtils.getFrezenChance(base,states);
		return extra;
	}
	private static final int REFLECT_PHYSICAL_STR = 10;
	private static final int REFLECT_PHYSICAL_DEX= 10;
	private static final int REFLECT_PHYSICAL_WIZ = 10;
	private static final int REFLECT_PHYSICAL_STA = 10;
	public static int getReflectPhysicalDamage( int str, int dex, int wiz, int sta, List<BaseProperty> properties, List<BaseSingleSkill> skills, List<IState> states){
		int base = 	str * REFLECT_PHYSICAL_STR + 
					dex * REFLECT_PHYSICAL_DEX + 
					wiz * REFLECT_PHYSICAL_WIZ + 
					sta * REFLECT_PHYSICAL_STA;
		int extra = base;
		extra += PropertyUtils.getReflectPhysicalDamage(base,properties);
		extra += SkillUtils.getReflectPhysicalDamage(base,skills);
		extra += StateUtils.getReflectPhysicalDamage(base,states);
		return extra;
	}
	private static final int REFLECT_MAGIC_STR = 10;
	private static final int REFLECT_MAGIC_DEX= 10;
	private static final int REFLECT_MAGIC_WIZ = 10;
	private static final int REFLECT_MAGIC_STA = 10;
	public static int getReflectMagicDamage( int str, int dex, int wiz, int sta, List<BaseProperty> properties, List<BaseSingleSkill> skills, List<IState> states){
		int base = 	str * REFLECT_MAGIC_STR + 
					dex * REFLECT_MAGIC_DEX + 
					wiz * REFLECT_MAGIC_WIZ + 
					sta * REFLECT_MAGIC_STA;
		int extra = base;
		extra += PropertyUtils.getReflectMagicDamage(base,properties);
		extra += SkillUtils.getReflectMagicDamage(base,skills);
		extra += StateUtils.getReflectMagicDamage(base,states);
		return extra;
	}
	private static final int STEALTH_HP_STR = 10;
	private static final int STEALTH_HP_DEX= 10;
	private static final int STEALTH_HP_WIZ = 10;
	private static final int STEALTH_HP_STA = 10;
	public static int getStealthHP(int str, int dex, int wiz, int sta, List<BaseProperty> properties, List<BaseSingleSkill> skills, List<IState> states){
		int base = 	str * STEALTH_HP_STR + 
					dex * STEALTH_HP_DEX + 
					wiz * STEALTH_HP_WIZ + 
					sta * STEALTH_HP_STA;
		int extra = base;
		extra += PropertyUtils.getStealthHP(base,properties);
		extra += SkillUtils.getStealthHP(base,skills);
		extra += StateUtils.getStealthHP(base,states);
		return extra;
	}
	private static final int STEALTH_MP_STR = 10;
	private static final int STEALTH_MP_DEX= 10;
	private static final int STEALTH_MP_WIZ = 10;
	private static final int STEALTH_MP_STA = 10;
	public static int getStealthMP( int str, int dex, int wiz, int sta, List<BaseProperty> properties, List<BaseSingleSkill> skills, List<IState> states){
		int base = 	str * STEALTH_MP_STR + 
					dex * STEALTH_MP_DEX + 
					wiz * STEALTH_MP_WIZ + 
					sta * STEALTH_MP_STA;
		int extra = base;
		extra += PropertyUtils.getStealthMP(base,properties);
		extra += SkillUtils.getStealthMP(base,skills);
		extra += StateUtils.getStealthMP(base,states);
		return extra;
	}
	private static final int DECREASE_MP_STR = 10;
	private static final int DECREASE_MP_DEX= 10;
	private static final int DECREASE_MP_WIZ = 10;
	private static final int DECREASE_MP_STA = 10;
	public static int getDecreaseMPCost( int str, int dex, int wiz, int sta, List<BaseProperty> properties, List<BaseSingleSkill> skills, List<IState> states){
		int base = 	str * DECREASE_MP_STR + 
					dex * DECREASE_MP_DEX + 
					wiz * DECREASE_MP_WIZ + 
					sta * DECREASE_MP_STA;
		int extra = base;
		extra += PropertyUtils.getDecreaseMPCost(base,properties);
		extra += SkillUtils.getDecreaseMPCost(base,skills);
		extra += StateUtils.getDecreaseMPCost(base,states);
		return extra;
	}
	private static final int DECREASE_SP_STR = 10;
	private static final int DECREASE_SP_DEX= 10;
	private static final int DECREASE_SP_WIZ = 10;
	private static final int DECREASE_SP_STA = 10;
	public static int getDecreaseSPCost( int str, int dex, int wiz, int sta, List<BaseProperty> properties, List<BaseSingleSkill> skills, List<IState> states){
		int base = 	str * DECREASE_SP_STR + 
					dex * DECREASE_SP_DEX + 
					wiz * DECREASE_SP_WIZ + 
					sta * DECREASE_SP_STA;
		int extra = base;
		extra += PropertyUtils.getDecreaseSPCost(base,properties);
		extra += SkillUtils.getDecreaseSPCost(base,skills);
		extra += StateUtils.getDecreaseSPCost(base,states);
		return extra;
	}
	private static final int INCREASE_COIN_STR = 10;
	private static final int INCREASE_COIN_DEX= 10;
	private static final int INCREASE_COIN_WIZ = 10;
	private static final int INCREASE_COIN_STA = 10;
	public static int getIncreaseCoin( int str, int dex, int wiz, int sta, List<BaseProperty> properties, List<BaseSingleSkill> skills, List<IState> states){
		int base = 	str * INCREASE_COIN_STR + 
					dex * INCREASE_COIN_DEX + 
					wiz * INCREASE_COIN_WIZ + 
					sta * INCREASE_COIN_STA;
		int extra = base;
		extra += PropertyUtils.getIncreaseCoin(base,properties);
		extra += SkillUtils.getIncreaseCoin(base,skills);
		extra += StateUtils.getIncreaseCoin(base,states);
		return extra;
	}
	private static final int INCREASE_EXP_STR = 10;
	private static final int INCREASE_EXP_DEX= 10;
	private static final int INCREASE_EXP_WIZ = 10;
	private static final int INCREASE_EXP_STA = 10;
	public static int getIncreaseEXP( int str, int dex, int wiz, int sta, List<BaseProperty> properties, List<BaseSingleSkill> skills, List<IState> states){
		int base = 	str * INCREASE_EXP_STR + 
					dex * INCREASE_EXP_DEX + 
					wiz * INCREASE_EXP_WIZ + 
					sta * INCREASE_EXP_STA;
		int extra = base;
		extra += PropertyUtils.getIncreaseEXP(base,properties);
		extra += SkillUtils.getIncreaseEXP(base,skills);
		extra += StateUtils.getIncreaseEXP(base,states);
		return extra;
	}
	private final static int DURABLE_DAMAGE_STR = 10;
	private final static int DURABLE_DAMAGE_DEX= 10;
	private static final int DURABLE_DAMAGE_WIZ = 10;
	private static final int DURABLE_DAMAGE_STA = 10;
	public static int getDurableDamage( int str, int dex, int wiz, int sta, List<BaseProperty> properties, List<BaseSingleSkill> skills, List<IState> states){
		int base = 	str * DURABLE_DAMAGE_STR + 
					dex * DURABLE_DAMAGE_DEX + 
					wiz * DURABLE_DAMAGE_WIZ + 
					sta * DURABLE_DAMAGE_STA;
		int extra = base;
		extra += PropertyUtils.getDurableDamage(base,properties);
		extra += SkillUtils.getDurableDamage(base,skills);
		extra += StateUtils.getDurableDamage(base,states);
		return extra;
	}
	public static int getExtraAttribute(AttributeEnum a,GameChar c){
		int av = 0;
		for(IEquipment e : getEquipments(c)){
			av = av + e.getAttributeByName(a);
		}
		return av;
	}
	
	public static int getBasicAttribute(AttributeEnum a,GameChar c){
		switch(a){
		case ALLATTRIBUTE:return 0;
		case DEX:return c.getDex();
		case STA:return c.getSta();
		case STR:return c.getStr();
		case WIZ:return c.getWiz();
		default: return 0;
		}
	}
	public static int getAttribute(AttributeEnum a,GameChar c){
		return getExtraAttribute(a,c) + getBasicAttribute(a,c) + StateUtils.getAttributeByName(a,getStates(c));
	}
	public static List<BaseProperty> getProperties(GameChar c){
		List<BaseProperty> list = new ArrayList<BaseProperty>();
		for(IEquipment e : getEquipments(c)){
			list.addAll(e.getProperties());
		}
		list.addAll(RaceUtils.getProperties(c.getRace()));
		return list;
	}
	public static List<BaseSingleSkill> getSkills(GameChar c){
		List<BaseSingleSkill> list = new ArrayList<BaseSingleSkill>();
		for(IEquipment e : getEquipments(c)){
			list.addAll(e.getSkills());
		}
		list.addAll(RaceUtils.getSkills(c.getRace()));
		return list;
	}
	public static int getSkillByName(SkillEnum s,GameChar c){
		int i = 0;
		for(BaseSingleSkill bss : getSkills(c)){
			if(bss.getName().equals(s)){
				i = i + bss.getValue();
			}
		}
		return i;
	}

	public static List<IState> getStates(GameChar c) {
		List<IState> list = new ArrayList<IState>();
		list.addAll(c.getStates());
		list.addAll(RaceUtils.getStates(c.getRace()));
		return list;
	}
	public static int getSkillLevelByName(SkillEnum s,GameChar c){
		int lv = 0;
		for(BaseSingleSkill bss : getSkills(c)){
			System.out.println("find:" + s.toString() + "target:" + bss.getName() + "level" + bss.getValue());
			if(bss.getName().equals(s) || bss.getName().equals(SkillEnum.ALLSKILL)){
				lv = lv + bss.getValue();
			}
		}
		return lv;
	}


	public static List<IEquipment> getEquipments(GameChar c){
		List<IEquipment> list = new ArrayList<IEquipment>();
		if(c.getHead() != null && isDruable(c.getHead())){
			list.add(c.getHead());
		}
		if(c.getBody() != null && isDruable(c.getBody())){
			list.add(c.getBody());
		}
		if(c.getArm() != null && isDruable(c.getArm())){
			list.add(c.getArm());
		}
		if(c.getGlove() != null && isDruable(c.getGlove())){
			list.add(c.getGlove());
		}
		if(c.getNecklace() != null && isDruable(c.getNecklace())){
			list.add(c.getNecklace());
		}
		if(c.getLeftRing() != null && isDruable(c.getLeftRing())){
			list.add(c.getLeftRing());
		}
		if(c.getRightRing() != null && isDruable(c.getRightRing())){
			list.add(c.getRightRing());
		}
		if(c.getLeftHand() != null && isDruable(c.getLeftHand())){
			list.add(c.getLeftHand());
		}
		if(c.getRightHand() != null && isDruable(c.getRightHand())){
			list.add(c.getRightHand());
		}
		if(c.getPants() != null && isDruable(c.getPants())){
			list.add(c.getPants());
		}
		if(c.getBoots() != null && isDruable(c.getBoots())){
			list.add(c.getBoots());
		}
		if(c.getGuardian() != null && isDruable(c.getGuardian())){
			list.add(c.getGuardian());
		}
		if(c.getMount() != null && isDruable(c.getMount())){
			list.add(c.getMount());
		}
		return list;
	}
	public static boolean isEquipmentUsable(GameChar c,IEquipment e){
		return (isDruable(e) && isRequirementFit(c,e));
	}
	public static boolean isDruable(IEquipment e){
		if(e == null){ 
			return false;
		}else{
			return (e.getDruable() > 0);
		}
	}
	public static boolean isRequirementFit(GameChar c,IEquipment e,AttributeEnum a){
		int r = e.getRequirementByName(a);
		if(a.equals(AttributeEnum.ALLATTRIBUTE)){
			if( r > getAttribute(AttributeEnum.DEX,c) || 
				r > getAttribute(AttributeEnum.STR,c) || 
				r > getAttribute(AttributeEnum.STA,c) || 
				r > getAttribute(AttributeEnum.WIZ,c)) {
				return false;
			}else{
				return true;
			}
		}else{
			return r < getAttribute(a,c);
		}
	}
	
	public static boolean isRequirementFit(GameChar c,IEquipment e){
		List<BaseRequirement> rs = e.getRequirements();
		for(BaseRequirement r : rs){
			if(!isRequirementFit(c,e,r.getName())){
				return false;
			}
		}
		return true;
	}
	public static int getCurrentWP(GameChar c) {
		int wp = 0;
		for(IEquipment e : getAllEquipments(c)){
			wp += e.getWeight();
		}
		for(IObject o : c.getObjects()){
			wp += o.getWeight();
		}
		return wp;
	}
	public static List<IEquipment> getAllEquipments(GameChar c){
		List<IEquipment> list = new ArrayList<IEquipment>();
		if(c.getHead() != null){
			list.add(c.getHead());
		}
		if(c.getBody() != null){
			list.add(c.getBody());
		}
		if(c.getArm() != null){
			list.add(c.getArm());
		}
		if(c.getGlove() != null){
			list.add(c.getGlove());
		}
		if(c.getNecklace() != null){
			list.add(c.getNecklace());
		}
		if(c.getLeftRing() != null){
			list.add(c.getLeftRing());
		}
		if(c.getRightRing() != null){
			list.add(c.getRightRing());
		}
		if(c.getLeftHand() != null){
			list.add(c.getLeftHand());
		}
		if(c.getRightHand() != null){
			list.add(c.getRightHand());
		}
		if(c.getPants() != null){
			list.add(c.getPants());
		}
		if(c.getBoots() != null){
			list.add(c.getBoots());
		}
		if(c.getGuardian() != null){
			list.add(c.getGuardian());
		}
		if(c.getMount() != null){
			list.add(c.getMount());
		}
		return list;
	}
	
	public static void swapEquipment(GameChar c,IEquipment e,EquipmentClassEnum t) {
		unmountEquipment(c,t);mountEquipment(c, e, t);
	}
	public static void mountEquipment(GameChar c,IEquipment e,EquipmentClassEnum t) {
		switch(t){
		case ARM:		{if(e instanceof IArm 		&& c.getObjects().contains(e)){c.setArm((IArm)e);				c.removeObject(e);}}break;
		case BODY:		{if(e instanceof IBody 		&& c.getObjects().contains(e)){c.setBody((IBody)e);				c.removeObject(e);}}break;
		case BOOTS:		{if(e instanceof IBoots 	&& c.getObjects().contains(e)){c.setBoots((IBoots)e);			c.removeObject(e);}}break;
		case GLOVE:		{if(e instanceof IGlove 	&& c.getObjects().contains(e)){c.setGlove((IGlove)e);			c.removeObject(e);}}break;
		case GUARDIAN:	{if(e instanceof IGuardian 	&& c.getObjects().contains(e)){c.setGuardian((IGuardian)e);		c.removeObject(e);}}break;
		case HEAD:		{if(e instanceof IHead 		&& c.getObjects().contains(e)){c.setHead((IHead)e);				c.removeObject(e);}}break;
		case LEFTHAND:	{if(e instanceof ILeftHand 	&& c.getObjects().contains(e)){c.setLeftHand((ILeftHand)e);		c.removeObject(e);}}break;
		case LEFTRING:	{if(e instanceof IRing 		&& c.getObjects().contains(e)){c.setLeftRing((IRing)e);			c.removeObject(e);}}break;
		case MOUNT:		{if(e instanceof IMount 	&& c.getObjects().contains(e)){c.setMount((IMount)e);			c.removeObject(e);}}break;
		case NECKLACE:	{if(e instanceof INecklace 	&& c.getObjects().contains(e)){c.setNecklace((INecklace)e);		c.removeObject(e);}}break;
		case PANTS:		{if(e instanceof IPants 	&& c.getObjects().contains(e)){c.setPants((IPants)e);			c.removeObject(e);}}break;
		case RIGHTHAND:	{if(e instanceof IRightHand && c.getObjects().contains(e)){c.setRightHand((IRightHand)e);	c.removeObject(e);}}break;
		case RIGHTRING:	{if(e instanceof IRing 		&& c.getObjects().contains(e)){c.setRightRing((IRing)e);		c.removeObject(e);}}break;
		default:break;		
		}
	}
	
	public static void unmountEquipment(GameChar c, EquipmentClassEnum t) {
		switch(t){
		case ARM:		{if(c.getArm()		!=null){c.addObject(c.getArm());}		c.setArm(null);			}break;
		case BODY:		{if(c.getBody()		!=null){c.addObject(c.getBody());}		c.setBody(null);		}break;
		case BOOTS:		{if(c.getBoots()	!=null){c.addObject(c.getBoots());}		c.setBoots(null);		}break;
		case GLOVE:		{if(c.getGlove()	!=null){c.addObject(c.getGlove());}		c.setGlove(null);		}break;
		case GUARDIAN:	{if(c.getGuardian()	!=null){c.addObject(c.getGuardian());}	c.setGuardian(null);	}break;
		case HEAD:		{if(c.getHead()		!=null){c.addObject(c.getHead());}		c.setHead(null);		}break;
		case LEFTHAND:	{if(c.getLeftHand()	!=null){c.addObject(c.getLeftHand());}	c.setLeftHand(null);	}break;
		case LEFTRING:	{if(c.getLeftRing()	!=null){c.addObject(c.getLeftRing());}	c.setLeftRing(null);	}break;
		case MOUNT:		{if(c.getMount()	!=null){c.addObject(c.getMount());}		c.setMount(null);		}break;
		case NECKLACE:	{if(c.getNecklace()	!=null){c.addObject(c.getNecklace());}	c.setNecklace(null);	}break;
		case PANTS:		{if(c.getPants()	!=null){c.addObject(c.getPants());}		c.setPants(null);		}break;
		case RIGHTHAND:	{if(c.getRightHand()!=null){c.addObject(c.getRightHand());}	c.setRightHand(null);	}break;
		case RIGHTRING:	{if(c.getRightRing()!=null){c.addObject(c.getRightRing());}	c.setRightRing(null);	}break;
		default:break;		
		}
	}
	public static void getUpdateExtra(GameChar c, GameCharExtra e) {
		e.version = c.getVersion();
		int str = getAttribute(AttributeEnum.STR,c);
		int dex = getAttribute(AttributeEnum.DEX,c);
		int wiz = getAttribute(AttributeEnum.WIZ,c);
		int sta = getAttribute(AttributeEnum.STA,c);
		
		List<BaseProperty> properties = getProperties(c);
		List<BaseSingleSkill> skills  = getSkills(c);
		List<IState> states			  = getStates(c);
		
		int maxPhysicalDamage = getMaxPhysicalDamage(str, dex, wiz, sta, properties, skills, states);
		int minPhysicalDamage = getMinPhysicalDamage(str, dex, wiz, sta, properties, skills, states);
		
		e.STR = str;
		e.DEX = dex;
		e.WIZ = wiz;
		e.STA = sta;
		
		e.maxHP = getMaxHP(str, dex, wiz, sta, properties, skills, states);
		e.maxMP = getMaxMP(str, dex, wiz, sta, properties, skills, states);
		e.maxSP = getMaxSP(str, dex, wiz, sta, properties, skills, states);
		e.maxWP = getMaxWP(str, dex, wiz, sta, properties, skills, states);
		
		e.maxPhysicalDamage = maxPhysicalDamage;
		e.minPhysicalDamage = minPhysicalDamage;
		e.maxFireDamage = getMaxFireDamage(str, dex, wiz, sta, properties, skills, states);
		e.minFireDamage = getMinFireDamage(str, dex, wiz, sta, properties, skills, states);
		e.maxColdDamage = getMaxColdDamage(str, dex, wiz, sta, properties, skills, states);
		e.minColdDamage = getMinColdDamage(str, dex, wiz, sta, properties, skills, states);
		e.maxPoisonDamage = getMaxPoisonDamage(str, dex, wiz, sta, properties, skills, states);
		e.minPoisonDamage = getMinPoisonDamage(str, dex, wiz, sta, properties, skills, states);
		e.maxEnergyDamage = getMaxEnergyDamage(str, dex, wiz, sta, properties, skills, states);
		e.minEnergyDamage = getMinEnergyDamage(str, dex, wiz, sta, properties, skills, states);
		
		e.maxPhysicalDamageToRange = getMaxPhysicalDamageToRange(maxPhysicalDamage,str, dex, wiz, sta, properties, skills, states);
		e.minPhysicalDamageToRange = getMinPhysicalDamageToRange(minPhysicalDamage,str, dex, wiz, sta, properties, skills, states);
		e.maxPhysicalDamageToMelee = getMaxPhysicalDamageToMelee(maxPhysicalDamage,str, dex, wiz, sta, properties, skills, states);
		e.minPhysicalDamageToMelee = getMinPhysicalDamageToMelee(minPhysicalDamage,str, dex, wiz, sta, properties, skills, states);
		e.maxPhysicalDamageToUndead = getMaxPhysicalDamageToUndead(maxPhysicalDamage,str, dex, wiz, sta, properties, skills, states);
		e.minPhysicalDamageToUndead = getMinPhysicalDamageToUndead(minPhysicalDamage,str, dex, wiz, sta, properties, skills, states);
		e.maxPhysicalDamageToDemon = getMaxPhysicalDamageToDemon(maxPhysicalDamage,str, dex, wiz, sta, properties, skills, states);
		e.minPhysicalDamageToDemon = getMinPhysicalDamageToDemon(minPhysicalDamage,str, dex, wiz, sta, properties, skills, states);
		e.maxPhysicalDamageToDragon = getMaxPhysicalDamageToDragon(maxPhysicalDamage,str, dex, wiz, sta, properties, skills, states);
		e.minPhysicalDamageToDragon = getMinPhysicalDamageToDragon(minPhysicalDamage,str, dex, wiz, sta, properties, skills, states);
		
		int physicalArmor = getPhysicalArmor(str, dex, wiz, sta, properties, skills, states);
		e.physicalArmor = physicalArmor;
		e.fireResist = getFireResist(str, dex, wiz, sta, properties, skills, states);
		e.coldResist = getColdResist(str, dex, wiz, sta, properties, skills, states);
		e.poisonResist = getPoisonResist(str, dex, wiz, sta, properties, skills, states);
		e.energyResist = getEnergyResist(str, dex, wiz, sta, properties, skills, states);
		e.physicalArmorToRange = getPhysicalArmorToRange(physicalArmor,str, dex, wiz, sta, properties, skills, states);
		e.physicalArmorToMelee = getPhysicalArmorToMelee(physicalArmor,str, dex, wiz, sta, properties, skills, states);

		e.regenHP = getRegenHP(str, dex, wiz, sta, properties, skills, states);
		e.regenMP = getRegenMP(str, dex, wiz, sta, properties, skills, states);
		e.regenSP = getRegenSP(str, dex, wiz, sta, properties, skills, states);

		e.aim = getAim(str, dex, wiz, sta, properties, skills, states);
		e.dodge = getDodge(str, dex, wiz, sta, properties, skills, states);
		e.critical = getCritical(str, dex, wiz, sta, properties, skills, states);
		e.speed = getSpeed(str, dex, wiz, sta, properties, skills, states);
		
		e.stunChance = getStunChance(str, dex, wiz, sta, properties, skills, states);
		e.frezenChance = getFrezenChance(str, dex, wiz, sta, properties, skills, states);
		e.reflectPhysicalDamage = getReflectPhysicalDamage(str, dex, wiz, sta, properties, skills, states);
		e.reflectMagicDamage = getReflectMagicDamage(str, dex, wiz, sta, properties, skills, states);
		e.stealthHP = getStealthHP(str, dex, wiz, sta, properties, skills, states);
		e.stealthMP = getStealthMP(str, dex, wiz, sta, properties, skills, states);
		
		e.decreaseMPCost = getDecreaseMPCost(str, dex, wiz, sta, properties, skills, states);
		e.decreaseSPCost = getDecreaseSPCost(str, dex, wiz, sta, properties, skills, states);
		
		e.increaseCoin = getIncreaseCoin(str, dex, wiz, sta, properties, skills, states);
		e.increaseEXP = getIncreaseEXP(str, dex, wiz, sta, properties, skills, states);
		e.durableDamage = getDurableDamage(str, dex, wiz, sta, properties, skills, states);
	}
	public static boolean updateExtra(GameChar c, GameCharExtra e) {
		//long start = System.nanoTime();
		if(e.version < c.getVersion()){
			System.out.println("updateExtra@" + c.getVersion());
			getUpdateExtra(c,e);
			//long end = System.nanoTime();
			//System.out.println("update:" + (end - start) +"ns");
			return true;
		}else{
			//long end = System.nanoTime();
			//System.out.println("update:" + (end - start) +"ns");
			return false;
		}
	}
	public static void update(GameChar c,GameCharExtra e) {
		reGenHP(c,e);
		reGenMP(c,e);
		reGenSP(c,e);
	}
	private static void reGenSP(GameChar c, GameCharExtra e) {
		if(e.regenHP > 0 && e.maxHP > c.getHp()){
			int r = c.getHp() + e.regenHP;
			if(r < e.maxHP){
				c.setHp(r);
			}else{
				c.setHp(e.maxHP);
			}
		}
	}
	private static void reGenMP(GameChar c, GameCharExtra e) {
		if(e.regenMP > 0 && e.maxMP > c.getMp()){
			int r = c.getMp() + e.regenMP;
			if(r < e.maxMP){
				c.setMp(r);
			}else{
				c.setMp(e.maxMP);
			}
		}
	}
	private static void reGenHP(GameChar c, GameCharExtra e) {
		if(e.regenSP > 0 && e.maxSP > c.getSp()){
			int r = c.getSp() + e.regenSP;
			if(r < e.maxSP){
				c.setSp(r);
			}else{
				c.setSp(e.maxSP);
			}
		}
	}
}
