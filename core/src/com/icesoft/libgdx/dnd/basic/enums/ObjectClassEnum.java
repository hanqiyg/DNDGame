package com.icesoft.libgdx.dnd.basic.enums;

import com.icesoft.libgdx.dnd.equipment.interfaces.IEquipment;
import com.icesoft.libgdx.dnd.object.interfaces.IMaterial;
import com.icesoft.libgdx.dnd.object.interfaces.IObject;
import com.icesoft.libgdx.dnd.object.interfaces.IQuest;
import com.icesoft.libgdx.dnd.object.interfaces.IScroll;
import com.icesoft.libgdx.dnd.object.interfaces.ISupple;

public enum ObjectClassEnum {
	EQUIP(IEquipment.class),SUPPLE(ISupple.class),QUEST(IQuest.class),MATERIAL(IMaterial.class),SCROLL(IScroll.class);
	
	private Class<? extends IObject> clazz;
	private ObjectClassEnum(Class<? extends IObject> c){
		clazz = c;
	}
	public Class<? extends IObject> getObjectClass(){
		return clazz;
	}
}
