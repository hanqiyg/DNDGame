package com.icesoft.libgdx.dnd.basic.utils;

import java.util.ArrayList;
import java.util.List;

import com.icesoft.libgdx.dnd.basic.BaseProperty;
import com.icesoft.libgdx.dnd.basic.BaseSingleSkill;
import com.icesoft.libgdx.dnd.basic.enums.RaceEnum;
import com.icesoft.libgdx.dnd.state.IState;

public class RaceUtils {
	public static List<BaseProperty> getProperties(RaceEnum race) {
		List<BaseProperty> properties = new ArrayList<BaseProperty>();
		return properties;
	}
	public static List<BaseSingleSkill> getSkills(RaceEnum race) {
		List<BaseSingleSkill> skills = new ArrayList<BaseSingleSkill>();
		return skills;
	}
	public static List<IState> getStates(RaceEnum race) {
		List<IState> states = new ArrayList<IState>();
		return states;
	}
}
