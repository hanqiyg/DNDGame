package com.icesoft.libgdx.dnd.object;

import com.icesoft.libgdx.dnd.DNDGame;
import com.icesoft.libgdx.dnd.object.interfaces.IObject;
import com.icesoft.libgdx.dnd.object.interfaces.ISupple;

public class Bread implements IObject,ISupple{
	private String id;
	private String name;
	private int addHP;;
	private int count;
	private int limit;
	private int unitWeight;
	private String usage;
	private String commandName;
	public Bread(String name,int addHP,int count,int unitWeight,int limit,String usage,String commandName){
		this.id = java.util.UUID.randomUUID().toString();
		this.name = name;
		this.addHP = addHP;
		this.count = count;
		this.unitWeight = unitWeight;
		this.limit = limit;
		this.usage = usage;
		this.commandName = commandName;
	}
	@Override
	public void use(DNDGame game) {		
		if(this.getCount() > 0 && game.character.getHp()<game.extra.maxHP){
			int hp = game.character.getHp() + addHP;
			if(hp < game.extra.maxHP){
				game.character.setHp(hp);
			}else{
				game.character.setHp(game.extra.maxHP);
			}
			setCount(getCount() - 1);
		}
	}

	@Override
	public String getID() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getStackLimit() {
		return limit;
	}

	@Override
	public int getCount() {
		return count;
	}

	@Override
	public void setCount(int count) {
		this.count = count;		
	}

	@Override
	public int getWeight() {
		return unitWeight * count;
	}

	@Override
	public void setWeight(int weight) {
		this.unitWeight = weight;
	}
	@Override
	public String getUsage() {
		return usage;
	}
	@Override
	public String getCommandName() {
		return commandName;
	}
}
