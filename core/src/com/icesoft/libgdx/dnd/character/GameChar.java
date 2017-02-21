package com.icesoft.libgdx.dnd.character;

import java.util.ArrayList;
import java.util.List;
import com.icesoft.libgdx.dnd.basic.enums.RaceEnum;
import com.icesoft.libgdx.dnd.basic.enums.SkillEnum;
import com.icesoft.libgdx.dnd.equipment.interfaces.IArm;
import com.icesoft.libgdx.dnd.equipment.interfaces.IBody;
import com.icesoft.libgdx.dnd.equipment.interfaces.IBoots;
import com.icesoft.libgdx.dnd.equipment.interfaces.IGlove;
import com.icesoft.libgdx.dnd.equipment.interfaces.IGuardian;
import com.icesoft.libgdx.dnd.equipment.interfaces.IHead;
import com.icesoft.libgdx.dnd.equipment.interfaces.ILeftHand;
import com.icesoft.libgdx.dnd.equipment.interfaces.IMount;
import com.icesoft.libgdx.dnd.equipment.interfaces.INecklace;
import com.icesoft.libgdx.dnd.equipment.interfaces.IPants;
import com.icesoft.libgdx.dnd.equipment.interfaces.IRightHand;
import com.icesoft.libgdx.dnd.equipment.interfaces.IRing;
import com.icesoft.libgdx.dnd.interfaces.Location;
import com.icesoft.libgdx.dnd.object.interfaces.IObject;
import com.icesoft.libgdx.dnd.skill.interfaces.ISkill;
import com.icesoft.libgdx.dnd.state.IState;

public class GameChar {
	private String id;
	private String photo;
	private String name;
	private RaceEnum race;
	
	private int str;
	private int dex;
	private int wiz;
	private int sta;
	
	private int hp;
	private int mp;
	private int sp;
	
	private IHead head;
	private IBody body;
	private IArm  arm;
	private IGlove glove;
	private INecklace necklace;
	private IRing leftRing;
	private IRing rightRing;
	private ILeftHand leftHand;
	private IRightHand rightHand;
	private IPants pants;
	private IBoots boots;
	private IGuardian guardian;
	private IMount mount;

	private List<IObject> objects = new ArrayList<IObject>();
	private List<IObject> banks = new ArrayList<IObject>();
	private List<ISkill> skills  = new ArrayList<ISkill>();
	private List<IState> states = new ArrayList<IState>();
	
	private Location location;
	
	private long version;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		updateVersion();
		this.id = id;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		updateVersion();
		this.photo = photo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		updateVersion();
		this.name = name;
	}
	public RaceEnum getRace() {
		return race;
	}
	public void setRace(RaceEnum race) {
		updateVersion();
		this.race = race;
	}
	public int getStr() {
		return str;
	}
	public void setStr(int str) {
		updateVersion();
		this.str = str;
	}
	public int getDex() {
		return dex;
	}
	public void setDex(int dex) {
		updateVersion();
		this.dex = dex;
	}
	public int getWiz() {
		return wiz;
	}
	public void setWiz(int wiz) {
		updateVersion();
		this.wiz = wiz;
	}
	public int getSta() {
		return sta;
	}
	public void setSta(int sta) {
		updateVersion();
		this.sta = sta;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		updateVersion();
		this.hp = hp;
	}
	public int getMp() {
		return mp;
	}
	public void setMp(int mp) {
		updateVersion();
		this.mp = mp;
	}
	public int getSp() {
		return sp;
	}
	public void setSp(int sp) {
		updateVersion();
		this.sp = sp;
	}
	public IHead getHead() {
		return head;
	}
	public void setHead(IHead head) {
		updateVersion();
		this.head = head;
	}
	public IBody getBody() {
		return body;
	}
	public void setBody(IBody body) {
		updateVersion();
		this.body = body;
	}
	public IArm getArm() {
		return arm;
	}
	public void setArm(IArm arm) {
		updateVersion();
		this.arm = arm;
	}
	public IGlove getGlove() {
		return glove;
	}
	public void setGlove(IGlove glove) {
		updateVersion();
		this.glove = glove;
	}
	public INecklace getNecklace() {
		return necklace;
	}
	public void setNecklace(INecklace necklace) {
		updateVersion();
		this.necklace = necklace;
	}
	public IRing getLeftRing() {
		return leftRing;
	}
	public void setLeftRing(IRing leftRing) {
		updateVersion();
		this.leftRing = leftRing;
	}
	public IRing getRightRing() {
		return rightRing;
	}
	public void setRightRing(IRing rightRing) {
		updateVersion();
		this.rightRing = rightRing;
	}
	public ILeftHand getLeftHand() {
		return leftHand;
	}
	public void setLeftHand(ILeftHand leftHand) {
		updateVersion();
		this.leftHand = leftHand;
	}
	public IRightHand getRightHand() {
		return rightHand;
	}
	public void setRightHand(IRightHand rightHand) {
		updateVersion();
		this.rightHand = rightHand;
	}
	public IPants getPants() {
		return pants;
	}
	public void setPants(IPants pants) {
		updateVersion();
		this.pants = pants;
	}
	public IBoots getBoots() {
		return boots;
	}
	public void setBoots(IBoots boots) {
		updateVersion();
		this.boots = boots;
	}
	public IGuardian getGuardian() {
		return guardian;
	}
	public void setGuardian(IGuardian guardian) {
		updateVersion();
		this.guardian = guardian;
	}
	public IMount getMount() {
		return mount;
	}
	public void setMount(IMount mount) {
		updateVersion();
		this.mount = mount;
	}
	public ISkill getSkillByName(SkillEnum s){
		for(ISkill is : getSkills()){
			if(is.getName().equals(s)){
				return is;
			}
		}
		return null;
	}
	public List<ISkill> getSkills() {
		return skills;
	}
	public void setSkills(List<ISkill> skills) {
		updateVersion();
		this.skills = skills;
	}
	public void addSkill(ISkill skill){
		updateVersion();
		if(skill != null){
			getSkills().add(skill);
		}
	}
	public void addSkills(List<ISkill> skills){
		for(ISkill s : skills){
			addSkill(s);
		}
	}
	public void removeSkill(ISkill skill){
		updateVersion();
		if(skill != null && getSkills().contains(skill)){
			getSkills().remove(skill);
		}
	}
	public void removeSkills(List<ISkill> skills){
		for(ISkill s : skills){
			removeSkill(s);
		}
	}	
	public List<IObject> getObjects() {
		return objects;
	}
	public void setObjects(List<IObject> objects) {
		updateVersion();
		this.objects = objects;
	}
	public void addObject(IObject object){
		updateVersion();
		if(object != null){
			getObjects().add(object);
		}
	}
	public void addObjects(List<IObject> objects){
		for(IObject o : objects){
			addObject(o);
		}
	}
	public void removeObject(IObject object){
		updateVersion();
		if(object != null && getObjects().contains(object)){
			getObjects().remove(object);
		}
	}
	public void removeObjects(List<IObject> objects){
		for(IObject o : objects){
			removeObject(o);
		}
	}
	public List<IObject> getBanks() {
		return banks;
	}
	public void setBanks(List<IObject> banks) {
		updateVersion();
		this.banks = banks;
	}
	public void addBank(IObject object){
		updateVersion();
		if(object != null){
			getBanks().add(object);
		}		
	}
	public void addBanks(List<IObject> objects){
		for(IObject o : objects){
			if(o != null){
				addBank(o);
			}
		}		
	}
	public void removeBank(IObject object){
		updateVersion();
		if(object != null && getBanks().contains(object)){
			getBanks().remove(object);
		}
	}
	public void removeBanks(List<IObject> objects){
		for(IObject o : objects){
			removeBank(o);
		}
	}	
	public List<IState> getStates() {
		return states;
	}
	public void setStates(List<IState> states) {
		updateVersion();
		this.states = states;
	}
	public void addStates(List<IState> states) {
		for(IState s : states){
			addState(s);
		}
	}
	public void addState(IState state) {
		updateVersion();
		if(state != null){
			getStates().add(state);
		}
	}
	public void removeState(IState state) {
		updateVersion();
		if(state != null && getStates().contains(state)){
			getStates().add(state);
		}
	}
	public void removeStates(List<IState> states) {
		for(IState s : states){
			removeState(s);
		}		
	}	
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		updateVersion();
		this.location = location;
	}
	public long getVersion() {
		return version;
	}
	public void setVersion(long version) {
		this.version = version;
	}
	public void updateVersion(){
		this.version = System.currentTimeMillis();
	}
	@Override
	public String toString() {
		String R = "\n\r";
		String FORMAT = "%10s %-5s\r\n";
		String FORMAT2 = "%-5s\r\n";
		StringBuffer sb = new StringBuffer();
		sb.append(String.format(FORMAT2, "======================================"));
		sb.append(String.format(FORMAT, "Version:",getVersion()));
		sb.append(String.format(FORMAT, "Name:",getName()));
		sb.append(String.format(FORMAT, "STR:",getStr()));
		sb.append(String.format(FORMAT, "DEX:",getDex()));
		sb.append(String.format(FORMAT, "WIZ:",getWiz()));
		sb.append(String.format(FORMAT, "STA:",getSta()));
		sb.append(String.format(FORMAT, "HP:",getHp()));
		sb.append(String.format(FORMAT, "MP:",getMp()));
		sb.append(String.format(FORMAT, "SP:",getSp()));
		
		sb.append(String.format(FORMAT2, "[Equipped]"));
		sb.append(String.format(FORMAT, "Head:"		,(getHead()==null		?"null":getHead().getName())));
		sb.append(String.format(FORMAT, "Body:"		,(getBody()==null		?"null":getBody().getName())));
		sb.append(String.format(FORMAT, "Arm:"  	, (getArm()==null		?"null":getArm().getName())));		
		sb.append(String.format(FORMAT, "Glove:" 	, (getGlove()==null		?"null":getGlove().getName())));		
		sb.append(String.format(FORMAT, "Necklace:" , (getNecklace()==null	?"null":getNecklace().getName())));	
		sb.append(String.format(FORMAT, "LeftRing:" , (getLeftRing()==null	?"null":getLeftRing().getName())));	
		sb.append(String.format(FORMAT, "RightRing:", (getRightRing()==null	?"null":getRightRing().getName())));	
		sb.append(String.format(FORMAT, "Pants:" 	, (getPants()==null		?"null":getPants().getName())));		
		sb.append(String.format(FORMAT, "Boots:" 	, (getBoots()==null		?"null":getBoots().getName())));		
		sb.append(String.format(FORMAT, "Guardian:" , (getGuardian()==null	?"null":getGuardian().getName())));	
		sb.append(String.format(FORMAT, "Mount:" 	, (getMount()==null		?"null":getMount().getName())));		
		sb.append(String.format(FORMAT, "Head:" 	, (getHead()==null		?"null":getHead().getName())));	
		sb.append(String.format(FORMAT2, "[Equipped]"));
		
		sb.append(String.format(FORMAT2, "[Object]"));
		for(IObject o : objects){
			sb.append(String.format(FORMAT, o.getName() + ":", o.getCount()));
		}
		sb.append(String.format(FORMAT2, "[Object]"));
		
		sb.append(String.format(FORMAT2, "[Bank  ]"));
		for(IObject o : banks){
			sb.append(String.format(FORMAT, o.getName() + ":", o.getCount()));
		}
		sb.append(String.format(FORMAT2, "[Bank  ]"));
		
		sb.append(String.format(FORMAT2, "[Skill ]"));
		for(ISkill o : skills){
			sb.append(String.format(FORMAT, o.getName() + ":", o.getEXP()));
		}
		sb.append(String.format(FORMAT2, "[Skill ]"));
		
		sb.append(String.format(FORMAT2, "[State ]"));
		for(IState o : states){
			sb.append(String.format(FORMAT, o.getName() , o.getValue()));
		}
		sb.append(String.format(FORMAT2, "[State ]"));
		sb.append(String.format(FORMAT2, "======================================"));
		return sb.toString();
	}	
}
