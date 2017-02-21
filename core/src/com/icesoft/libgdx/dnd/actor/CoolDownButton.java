package com.icesoft.libgdx.dnd.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class CoolDownButton extends CircularProgress {
	private static final String T = "CoolDownButton.class";
	private float coolDownPeriod = 1;
	private float time = 0f;
	private ClickListener listener;
	
	public CoolDownButton(TextureRegion region, TextureRegion background,float coolDownPeriod) {
		super(region, background);
		this.coolDownPeriod = coolDownPeriod;	
	}	
	public void setTime(float time){
		this.time = this.time + time;
		super.setPercent(this.time/coolDownPeriod);
		if(this.time>=coolDownPeriod && listener != null && !this.getListeners().contains(listener, false)){
			Gdx.app.debug(T, "Fire addListener");
			this.addListener(listener);
		}
		if(this.time<coolDownPeriod && listener != null && this.getListeners().contains(listener, false)){
			Gdx.app.debug(T, "Fire removeListener");
			this.removeListener(listener);
		}
	}
	public void cooldown(){
		Gdx.app.debug(T, "Fire CoolDown");
		this.time = 0;
		super.setPercent(this.time/coolDownPeriod);
	}
	public void setClickListener(ClickListener cl){
		this.listener = cl;
	}
}
