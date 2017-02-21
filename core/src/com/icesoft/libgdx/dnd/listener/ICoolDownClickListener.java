package com.icesoft.libgdx.dnd.listener;

import com.badlogic.gdx.scenes.scene2d.InputEvent;

public interface ICoolDownClickListener {
	public void clicked(InputEvent event, float x, float y);
	public void longTouch(InputEvent event, float x, float y);
}
