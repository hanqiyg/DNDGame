package com.icesoft.libgdx.utils;

import java.util.Locale;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.utils.I18NBundle;

public class LanguageManager {
	public static final String en_GB = "i18n/language_en_GB";
	public static final String zh_CN = "i18n/language_zh_CN";
	
    public static I18NBundle getI18NBundle(AssetManager manager){
    	String currentLanguage =  Locale.getDefault().toString();    	
    	I18NBundle bundle;
    	I18NBundle.setExceptionOnMissingKey(false);
    	if(currentLanguage.equals(en_GB)){    		
    		manager.finishLoadingAsset(en_GB);
    		bundle = manager.get(en_GB,I18NBundle.class);
    	}else
    	if(currentLanguage.equals(zh_CN)){
    		manager.finishLoadingAsset(zh_CN);
    		bundle =manager.get(zh_CN,I18NBundle.class);
    	}else{
    		manager.finishLoadingAsset(zh_CN);
    		bundle = manager.get(zh_CN,I18NBundle.class);;
    	}    	
    	return bundle;
    }
}
