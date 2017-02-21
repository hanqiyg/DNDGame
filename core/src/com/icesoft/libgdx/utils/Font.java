package com.icesoft.libgdx.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class Font {
	private static final String FONT_FILE = "fonts/MSYH.TTF";
	private static FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(FONT_FILE));
	public static BitmapFont getFont(int size,Color c,String s){
		FreeTypeFontParameter param = new FreeTypeFontParameter();
		param.characters = s;
		param.size = size; 
		param.color = c;
		return generator.generateFont(param);
	}
}
