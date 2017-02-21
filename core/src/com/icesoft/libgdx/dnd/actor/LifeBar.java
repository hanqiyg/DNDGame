package com.icesoft.libgdx.dnd.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class LifeBar extends Actor {
	private static final String T = "LifeBar.class";
	private NinePatch progress;
	private NinePatch background;
	
	private int full = 100;
	private int now = 100;
	private float percent = 1f;
	
	Image iprogress;
	Image ibackground;
	public LifeBar(NinePatch region, NinePatch background){
		Gdx.app.debug(T, "create");
		this.progress = region;
		this.background = background;		
	}
	
	public void draw(Batch batch, float parentAlpha) {
		background.draw(batch, this.getX(), this.getY(), this.getWidth(), this.getHeight());
		progress.draw(batch, this.getX(), this.getY(), this.getWidth() * percent, this.getHeight());
		
	}
	
	public void setNow(int now){
		Gdx.app.debug(T, "setNow: " + now);
		this.now = now;
		percent = (float)this.now / (float)this.full;
		if(percent<=0.03){
			percent = 0.03f;
		}else if(percent>=1){
			percent = 1;
		}
		Gdx.app.debug(T, "Percent: " +this.now + "/" + this.full + " = " + percent);
	}
	public void setFull(int full){
		this.full = full;
	}
}
