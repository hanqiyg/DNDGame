package com.icesoft.libgdx.dnd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.PropertiesUtils;
import com.badlogic.gdx.utils.Scaling;
import com.icesoft.libgdx.dnd.basic.utils.CharacterUtils;
import com.icesoft.libgdx.dnd.character.GameChar;
import com.icesoft.libgdx.dnd.character.GameCharExtra;
import com.icesoft.libgdx.dnd.logic.CreateWorld;
import com.icesoft.libgdx.dnd.screens.BlankScreen;
import com.icesoft.libgdx.dnd.screens.MainScreen;
import com.icesoft.libgdx.dnd.screens.SplashScreen;
import com.icesoft.libgdx.utils.Assets;
import com.icesoft.libgdx.utils.StringUtils;
import com.icesoft.libgdx.log.ConsoleLog;
import com.icesoft.libgdx.log.IConsoleLog;
import com.icesoft.libgdx.log.Log;
import com.icesoft.libgdx.log.LogLevel;

public class DNDGame extends Game{
	private static final String T = "DNDGame.class";
	public static final String P = "Config";
	
	public static final float PAD = 10f;
	public static final float PROGRESSHEIGHT = 25f;
	
	public static final int SCREEN_WIDTH = 1080;
	public static final int SCREEN_HEIGHT = 1920;
	public static final Scaling SCALING = Scaling.fit;

	private List<Screen> allScreen = new ArrayList<Screen>();
	public Assets assets;
	public GameChar character;	
	public String configName = "1920-1080";
	
	public static Skin SKIN;
	public String bitmapFontText;
	
	public boolean debug;
	public long debugTime = 5;
	
	public GameCharExtra extra = new GameCharExtra();
	public ConsoleLog cons;
	private BlankScreen b;
	public Log log;
	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		Gdx.app.debug(T, "create");
		test();
		cons = new ConsoleLog();
		log = new Log();
		getI18NFontFile();
		character = CreateWorld.getCharacter();
		assets = new Assets(this);		
		assets.loadBasic();
		CharacterUtils.updateExtra(character, extra);
		this.setScreen(new SplashScreen(this));
		//setScreen(new TScreen());
	}
	
	private void test() {
		IConsoleLog c = new IConsoleLog();
		c.test();
	}

	public void addLog(Stage stage,com.icesoft.libgdx.log.LogLevel level,String msg){
		System.out.println(level.getIdentifier() + msg);
		Skin skin = assets.manager.get(Assets.SKIN, Skin.class);
		if(skin != null && stage != null){
			final Label l = new Label("",skin);
			l.setColor(level.getColor());
			l.setText(level.getIdentifier() + msg);
			l.setBounds(100f, 300f, 500f, 50f);
			stage.addActor(l);
			l.addAction(Actions.sequence(Actions.fadeOut(1f),Actions.run(new Runnable(){
				@Override
				public void run() {
					l.remove();
				}
			})));
		}
		log.addEntry(msg, level);
	}
	
	public void getI18NFontFile(){
		FileHandle f = Gdx.files.internal("i18n/language_zh_CN.properties");
		ObjectMap<String,String> porperties = new ObjectMap<String,String>();
		try {
			PropertiesUtils.load(porperties, f.reader());
	        StringBuffer sb = new StringBuffer();
	        if(porperties!=null && porperties.size > 0){
	        	for(String key : porperties.keys()){
	        		String value = porperties.get(key);
	        		if(value !=null && !value.isEmpty()){
	        			sb.append(value.trim());
	        		}
	        	}
	        }
		    bitmapFontText = StringUtils.removeDuplicate(sb.toString());    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void exitGame() {
		Gdx.app.debug(T, "exitGame");
		if(b == null){
			b = new BlankScreen();
		}
		setScreen(b);
		for(Screen s : allScreen){
			s.dispose();
		}
		log.flush();
		assets.dispose();
		b.dispose();
		Gdx.app.exit();		
	}
	public void loadGame() {
		Gdx.app.debug(T, "loadGame");
	}
	public void newGame() {
		Gdx.app.debug(T, "newGame");
	}
	public void saveGame() {
		Gdx.app.debug(T, "saveGame");
	}
	
	MainScreen mainScreen;
	public void mainScreen() {
		if(mainScreen == null){
			mainScreen = new MainScreen(this);
			allScreen.add(mainScreen);
		}
		setScreen(mainScreen);
	}
}
