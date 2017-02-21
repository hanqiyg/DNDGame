package com.icesoft.libgdx.dnd.state;

import com.icesoft.libgdx.dnd.basic.enums.StateEnum;

public interface IState {
	public long getLastDate();
	public StateEnum getName();
	public int getValue();	
}
