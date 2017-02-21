package com.icesoft.libgdx.utils;

import java.util.Locale;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.I18NBundleLoader;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.assets.loaders.SkinLoader.SkinParameter;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader.FreeTypeFontLoaderParameter;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.I18NBundle;
import com.badlogic.gdx.utils.ObjectMap;
import com.icesoft.libgdx.dnd.DNDGame;

public class Assets {
	private static final String T = "Assets.class";
	public AssetManager manager = new AssetManager();
	public AssetManager views = new AssetManager();
	private static final String FONT_ASCII = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ~!@#$%^&*()_+-=</>,.:;{}[]";	
	public static final String DEFAULT_40_CHINESE = "40CHINESE.TTF";
	public static final String SMALLFONT_20_ASCII = "20ASCII.TTF";
	public static final String FONT_FILENAME = "fonts/MSYHBD.TTF";
	public static final String ATLAS = "skins/uiskin.atlas";
	public static final String SKIN = "skins/uiskin.json";
	
	public DNDGame game;
	
	public Assets(DNDGame game){
		this.game = game;
	}

	public void loadBasic(){
		//bitmapfont
		FileHandleResolver resolver = new InternalFileHandleResolver();
		manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
		manager.setLoader(BitmapFont.class, ".TTF", new FreetypeFontLoader(resolver));
		FreeTypeFontLoaderParameter defaultFont = new FreeTypeFontLoaderParameter();
		defaultFont.fontFileName = FONT_FILENAME;
		defaultFont.fontParameters.size = 40;
		defaultFont.fontParameters.characters = game.bitmapFontText + FONT_ASCII;
		manager.load(DEFAULT_40_CHINESE, BitmapFont.class, defaultFont);
		
		FreeTypeFontLoaderParameter smallFontASCII = new FreeTypeFontLoaderParameter();
		smallFontASCII.fontFileName = FONT_FILENAME;
		smallFontASCII.fontParameters.size = 20;
		smallFontASCII.fontParameters.characters = FONT_ASCII;
		manager.load(SMALLFONT_20_ASCII, BitmapFont.class, smallFontASCII);
	
		manager.finishLoadingAsset(DEFAULT_40_CHINESE);
		BitmapFont font = manager.get(DEFAULT_40_CHINESE,BitmapFont.class);
		Gdx.app.debug(T, "Font:" + font.getRegions().size);
		
		//skin
		
		ObjectMap<String,Object> resources = new ObjectMap<String,Object>();
		resources.put("default-font", font);
		SkinParameter skinParam = new SkinParameter(ATLAS,resources);
		manager.setLoader(Skin.class, new SkinLoader(resolver));
		manager.load(SKIN,Skin.class,skinParam);	
		
		//i18n		
		manager.setLoader(I18NBundle.class, new I18NBundleLoader(resolver));
		manager.load(LanguageManager.en_GB,I18NBundle.class, new I18NBundleLoader.I18NBundleParameter(Locale.UK));
		manager.load(LanguageManager.zh_CN,I18NBundle.class, new I18NBundleLoader.I18NBundleParameter(Locale.CHINA));
	}
	public BitmapFont getFont(String fontName,int size, String fontText){
		System.out.println(!manager.containsAsset(fontName));
		if(!manager.containsAsset(fontName)){
			FileHandleResolver resolver = new InternalFileHandleResolver();
			manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
			manager.setLoader(BitmapFont.class, ".TTF", new FreetypeFontLoader(resolver));
			
			FreeTypeFontLoaderParameter parameter = new FreeTypeFontLoaderParameter();
			parameter.fontFileName = FONT_FILENAME;
			parameter.fontParameters.size = size;
			parameter.fontParameters.characters = fontText;
			manager.load(fontName, BitmapFont.class, parameter);
		}
		manager.finishLoadingAsset(fontName);
		return manager.get(fontName,BitmapFont.class);
	}
	public void dispose(){
		manager.get(SKIN,Skin.class).remove("default-font", BitmapFont.class);
		manager.dispose();
		views.dispose();
	}
}
