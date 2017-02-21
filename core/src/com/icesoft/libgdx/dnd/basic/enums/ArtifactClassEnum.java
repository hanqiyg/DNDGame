package com.icesoft.libgdx.dnd.basic.enums;

import com.icesoft.libgdx.dnd.equipment.interfaces.IEquipment;
import com.icesoft.libgdx.dnd.object.interfaces.IObject;
import com.icesoft.libgdx.dnd.object.interfaces.IScroll;
import com.icesoft.libgdx.dnd.object.interfaces.ISupple;

public enum ArtifactClassEnum {
	EQUIP(IEquipment.class),SUPPLE(ISupple.class),SCROLL(IScroll.class);
	
	private Class<? extends IObject> clazz;
	private ArtifactClassEnum(Class<? extends IObject> c){
		clazz = c;
	}
	public Class<? extends IObject> getObjectClass(){
		return clazz;
	}
}
