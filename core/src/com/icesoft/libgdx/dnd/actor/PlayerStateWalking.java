package com.icesoft.libgdx.dnd.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;

public class PlayerStateWalking extends Actor{
	private static final String T = "PlayerStateWalking.class";
	private String name;
	private String photo;
	private int staminaMax;
	private int stamina;
	
	private float percent;
	
	private Texture photoImage;
	private NinePatch progressBackground;
	private NinePatch progressForeground;
	
	private BitmapFont font;
	private static final String FONT_ASCII = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ~!@#$%^&*()_+-=</>,.:;{}[]";

	public PlayerStateWalking(String name,String photo,int staminaMax,int stamina){
		this.name = name;
		this.photo = photo;
		this.staminaMax = staminaMax;
		this.stamina = stamina;
		init();
	}

	private void init() {
		percent = stamina / staminaMax;
		photoImage = new Texture(Gdx.files.internal(photo));
		progressBackground = new NinePatch(new Texture(Gdx.files.internal("images/emptybar.png")),5,5,1,1);
		progressForeground = new NinePatch(new Texture(Gdx.files.internal("images/fillbar.png")),5,5,1,1);
		font();
	}
	private void font(){
		FreeTypeFontParameter default_font_Param = new FreeTypeFontParameter();
		default_font_Param.characters = FONT_ASCII;
		default_font_Param.size = 30; 
	    default_font_Param.kerning=true;
	    default_font_Param.color = Color.BLACK;
	    FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/MSYHBD.TTF"));
	    font = generator.generateFont(default_font_Param);
	    generator.dispose();
	}
	public void draw(Batch batch, float parentAlpha) {		
		batch.draw(photoImage, this.getX(), this.getY(), this.getWidth()/2, this.getHeight(), 0, 0, photoImage.getWidth(), photoImage.getHeight(), false, false);
		font.draw(batch, "Normal", this.getX()+this.getWidth()/2, this.getY()+this.getHeight()/2, this.getWidth()/2, Align.right, false);
		progressBackground.draw(batch, this.getX() + this.getWidth()/2, this.getY(), this.getWidth()/2, 20);
		progressForeground.draw(batch, this.getX() + this.getWidth()/2, this.getY(), this.getWidth()/2 * percent, 20);		
	}
}
