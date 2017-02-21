package com.icesoft.libgdx.dnd.object.interfaces;

import com.icesoft.libgdx.dnd.DNDGame;

public interface ISupple extends IObject{
	public void use(DNDGame game);
	public String getUsage();
	public String getCommandName();
}
