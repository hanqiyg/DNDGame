package com.icesoft.libgdx.dnd.basic.utils;

import java.util.List;

import com.icesoft.libgdx.dnd.basic.BaseSingleSkill;
import com.icesoft.libgdx.dnd.basic.enums.SkillEnum;
import com.icesoft.libgdx.dnd.character.GameChar;
import com.icesoft.libgdx.dnd.skill.interfaces.ISkill;

public class SkillUtils {
	public static final int[] levels = {
			1,10,100,200,400,1000,2000,4000,10000
	};
	public static int getLevel(ISkill skill) {
		for(int i=0;i<SkillUtils.levels.length;i++){
			if(skill.getEXP() <SkillUtils.levels[i]){
				return i;
			}
		}
		return 0;
	}
	public static int getNextLevelExp(ISkill skill){
		return SkillUtils.levels[getLevel(skill)];
	}
	public static int getLevelExp(ISkill skill){
		return SkillUtils.levels[getLevel(skill)-1];
	}
	public static ISkill getByName(GameChar character, SkillEnum skillName) {
		for(ISkill s : character.getSkills()){
			if(s.getName().equals(skillName)){
				return s;
			}
		}
		return null;
	}
	public static int getMaxHP(int baseHP, List<BaseSingleSkill> skills) {
		int i = 0;
		for(BaseSingleSkill bss : skills){
			switch(bss.getName()){
			
			}
		}
		return i;
	}
	public static int getMaxMP(int baseMP, List<BaseSingleSkill> skills) {
		int i = 0;
		for(BaseSingleSkill bss : skills){
			switch(bss.getName()){
			
			}
		}
		return i;
	}
	public static int getMaxSP(int baseSP, List<BaseSingleSkill> skills) {
		int i = 0;
		for(BaseSingleSkill bss : skills){
			switch(bss.getName()){
			
			}
		}
		return i;
	}
	public static int getMaxWP(int baseWP, List<BaseSingleSkill> skills) {
		int i = 0;
		for(BaseSingleSkill bss : skills){
			switch(bss.getName()){
			
			}
		}
		return i;
	}
	public static int getMaxPhysicalDamage(int baseWP, List<BaseSingleSkill> skills) {
		// TODO Auto-generated method stub
		return 0;
	}
	public static int getMaxFireDamage(int base, List<BaseSingleSkill> skills) {
		// TODO Auto-generated method stub
		return 0;
	}
	public static int getMaxColdDamage(int base, List<BaseSingleSkill> skills) {
		// TODO Auto-generated method stub
		return 0;
	}
	public static int getMaxPoisonDamage(int base, List<BaseSingleSkill> skills) {
		// TODO Auto-generated method stub
		return 0;
	}
	public static int getMaxEnergyDamage(int base, List<BaseSingleSkill> skills) {
		// TODO Auto-generated method stub
		return 0;
	}
	public static int getMinPhysicalDamage(int base, List<BaseSingleSkill> skills) {
		// TODO Auto-generated method stub
		return 0;
	}
	public static int getMinFireDamage(int base, List<BaseSingleSkill> skills) {
		// TODO Auto-generated method stub
		return 0;
	}
	public static int getMinColdDamage(int base, List<BaseSingleSkill> skills) {
		// TODO Auto-generated method stub
		return 0;
	}
	public static int getMinPoisonDamage(int base, List<BaseSingleSkill> skills) {
		// TODO Auto-generated method stub
		return 0;
	}
	public static int getMinEnergyDamage(int base, List<BaseSingleSkill> skills) {
		// TODO Auto-generated method stub
		return 0;
	}
	public static int getPhysicalArmor(int base, List<BaseSingleSkill> skills) {
		// TODO Auto-generated method stub
		return 0;
	}
	public static int getFireResist(int base, List<BaseSingleSkill> skills) {
		// TODO Auto-generated method stub
		return 0;
	}
	public static int getColdResist(int base, List<BaseSingleSkill> skills) {
		// TODO Auto-generated method stub
		return 0;
	}
	public static int getPoisonResist(int base, List<BaseSingleSkill> skills) {
		// TODO Auto-generated method stub
		return 0;
	}
	public static int getEnergyResist(int base, List<BaseSingleSkill> skills) {
		// TODO Auto-generated method stub
		return 0;
	}
	public static int getPhysicalArmorToRange(int base, List<BaseSingleSkill> skills) {
		// TODO Auto-generated method stub
		return 0;
	}
	public static int getPhysicalArmorToMelee(int base, List<BaseSingleSkill> skills) {
		// TODO Auto-generated method stub
		return 0;
	}
	public static int getPhysicalDamageToRange(int base, List<BaseSingleSkill> skills) {
		// TODO Auto-generated method stub
		return 0;
	}
	public static int getPhysicalDamageToMelee(int base, List<BaseSingleSkill> skills) {
		// TODO Auto-generated method stub
		return 0;
	}
	public static int getRegenHP(int base, List<BaseSingleSkill> skills) {
		// TODO Auto-generated method stub
		return 0;
	}
	public static int getRegenMP(int base, List<BaseSingleSkill> skills) {
		// TODO Auto-generated method stub
		return 0;
	}
	public static int getRegenSP(int base, List<BaseSingleSkill> skills) {
		// TODO Auto-generated method stub
		return 0;
	}
	public static int getPhysicalDamageToUndead(int base, List<BaseSingleSkill> skills) {
		// TODO Auto-generated method stub
		return 0;
	}
	public static int getPhysicalDamageToDemon(int base, List<BaseSingleSkill> skills) {
		// TODO Auto-generated method stub
		return 0;
	}
	public static int getPhysicalDamageToDragon(int base, List<BaseSingleSkill> skills) {
		// TODO Auto-generated method stub
		return 0;
	}
	public static int getDodge(int base, List<BaseSingleSkill> skills) {
		// TODO Auto-generated method stub
		return 0;
	}
	public static int getCritical(int base, List<BaseSingleSkill> skills) {
		// TODO Auto-generated method stub
		return 0;
	}
	public static int getAim(int base, List<BaseSingleSkill> skills) {
		// TODO Auto-generated method stub
		return 0;
	}
	public static int getSpeed(int base, List<BaseSingleSkill> skills) {
		// TODO Auto-generated method stub
		return 0;
	}
	public static int getStunChance(int base, List<BaseSingleSkill> skills) {
		// TODO Auto-generated method stub
		return 0;
	}
	public static int getFrezenChance(int base, List<BaseSingleSkill> skills) {
		// TODO Auto-generated method stub
		return 0;
	}
	public static int getReflectPhysicalDamage(int base, List<BaseSingleSkill> skills) {
		// TODO Auto-generated method stub
		return 0;
	}
	public static int getReflectMagicDamage(int base, List<BaseSingleSkill> skills) {
		// TODO Auto-generated method stub
		return 0;
	}
	public static int getStealthHP(int base, List<BaseSingleSkill> skills) {
		// TODO Auto-generated method stub
		return 0;
	}
	public static int getStealthMP(int base, List<BaseSingleSkill> skills) {
		// TODO Auto-generated method stub
		return 0;
	}
	public static int getDecreaseMPCost(int base, List<BaseSingleSkill> skills) {
		// TODO Auto-generated method stub
		return 0;
	}
	public static int getDecreaseSPCost(int base, List<BaseSingleSkill> skills) {
		// TODO Auto-generated method stub
		return 0;
	}
	public static int getIncreaseCoin(int base, List<BaseSingleSkill> skills) {
		// TODO Auto-generated method stub
		return 0;
	}
	public static int getIncreaseEXP(int base, List<BaseSingleSkill> skills) {
		// TODO Auto-generated method stub
		return 0;
	}
	public static int getDurableDamage(int base, List<BaseSingleSkill> skills) {
		// TODO Auto-generated method stub
		return 0;
	}
}
