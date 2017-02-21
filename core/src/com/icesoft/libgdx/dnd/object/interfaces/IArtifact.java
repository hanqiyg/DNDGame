package com.icesoft.libgdx.dnd.object.interfaces;

import java.util.Map;

import com.icesoft.libgdx.dnd.DNDGame;
import com.icesoft.libgdx.dnd.skill.interfaces.ISkill;

public interface IArtifact {
	public String getName();
	public Map<IMaterial,Integer> getRequireResource();
	public Map<ITool,Integer> getRequireTool();
	public Map<ISkill,Integer> getRequireSkill();
	public void make(DNDGame game);
}
