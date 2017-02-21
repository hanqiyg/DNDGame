package com.icesoft.libgdx.dnd.basic.enums;

import com.badlogic.gdx.graphics.Color;

public enum EquipmentLevelEnum {
	NORMAL(Color.WHITE),ENHANCED(Color.BLUE),RARE(Color.PURPLE),UNIQUE(Color.PINK),HEROIC(Color.ORANGE),
	SUIT(Color.GREEN),INHERIT(Color.BROWN);	
	
	private Color color;
	private EquipmentLevelEnum(Color color){
		this.color = color;
	}
	public Color getColor() {
		return color;
	}
}
