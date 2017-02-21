package com.icesoft.libgdx.dnd.basic.enums;

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

public enum EquipmentClassEnum{
	HEAD(IHead.class),BODY(IBody.class),ARM(IArm.class),GLOVE(IGlove.class),NECKLACE(INecklace.class),LEFTRING(IRing.class),RIGHTRING(IRing.class),
	LEFTHAND(ILeftHand.class),RIGHTHAND(IRightHand.class),PANTS(IPants.class),BOOTS(IBoots.class),GUARDIAN(IGuardian.class),MOUNT(IMount.class);
	private Class<? extends IEquipment> clazz;
	private EquipmentClassEnum(Class<? extends IEquipment> c){
		clazz = c;
	}
	public Class<? extends IEquipment> getEquipmentClass(){
		return clazz;
	}
}
