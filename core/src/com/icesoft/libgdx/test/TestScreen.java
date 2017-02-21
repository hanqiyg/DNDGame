package com.icesoft.libgdx.test;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.icesoft.libgdx.dnd.DNDGame;
import com.icesoft.libgdx.dnd.actor.SystemInfoActor;
import com.icesoft.libgdx.utils.MemoryUtils;

public class TestScreen implements Screen {
	private static final String T = "TestScreen.class";
	private DNDGame game;
	private BitmapFont font;
	
	private Texture tex,tex2,tex3,tex4,tex5,tex6,tex7,tex8;
	private Label btnUseMemory;
	private Label btnClearMemory;
	
	private OrthographicCamera stageCamera = new OrthographicCamera();	
	private ScalingViewport stageViewport = new ScalingViewport(Scaling.fit, 540f, 960f, stageCamera);
	private Stage stage = new Stage(stageViewport);
	
	public TestScreen(DNDGame game){
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		this.game = game;
		font = new BitmapFont();
		//Gdx.app.debug(T, "TestScreen" + MemoryUtils.getMemoryUsageString());
		Gdx.app.debug(T, "Used:" + MemoryUtils.getMemoryUnitString(MemoryUtils.getUsedMemory()));
	}
	
	@Override
	public void show() {
		LabelStyle tbs = new LabelStyle();
		tbs.font = font;
		tbs.fontColor = Color.WHITE;
			
		btnUseMemory = new Label("openFontGen", tbs);
		btnClearMemory = new Label("closeFontGen", tbs);
		
		btnUseMemory.setBounds(100f, 200f, 100f, 50f);
		btnClearMemory.setBounds(300f, 200f, 100f, 50f);
		
		btnUseMemory.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.debug(T, "openFontGen start" + MemoryUtils.getMemoryUsageString());
				long usageBegin = MemoryUtils.getFreeMemory();
				userMemory();
				long usageEnd   = MemoryUtils.getFreeMemory();
				Gdx.app.debug(T, "genAsciiFont" + " Used Memory:[" + MemoryUtils.getMemoryUnitString(usageBegin - usageEnd) +"]");
				Gdx.app.debug(T, "openFontGen end" + MemoryUtils.getMemoryUsageString());
				Gdx.app.debug(T, "Used:" + MemoryUtils.getMemoryUnitString(MemoryUtils.getUsedMemory()));
			}
		});
		
		btnClearMemory.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.debug(T, "closeFontGen start" + MemoryUtils.getMemoryUsageString());
				long usageBegin = MemoryUtils.getFreeMemory();
				clearMemory();
				long usageEnd   = MemoryUtils.getFreeMemory();
				Gdx.app.debug(T, "closeFontGen" + " Free Memory:[" + MemoryUtils.getMemoryUnitString(usageBegin - usageEnd) +"]");
				Gdx.app.debug(T, "closeFontGen end" + MemoryUtils.getMemoryUsageString());
				Gdx.app.debug(T, "Used:" + MemoryUtils.getMemoryUnitString(MemoryUtils.getUsedMemory()));
			}
		});

		SystemInfoActor info = new SystemInfoActor();
		info.setBounds(0, 50, 200, 50);
		
		SystemInfoActor info2 = new SystemInfoActor(SystemInfoActor.Level.STANDARD);
		info2.setBounds(0, 100, 200, 50);
		
		SystemInfoActor mem = new SystemInfoActor(new BitmapFont(),SystemInfoActor.Level.GRAPHIC);
		mem.setBounds(0, 150, 200, 20);
		
		stage.addActor(btnUseMemory);
		stage.addActor(btnClearMemory);
		
		stage.addActor(info);
		stage.addActor(info2);
		stage.addActor(mem);
		
		Gdx.input.setInputProcessor(stage);
	}
	String image = "images/img25.jpg";
	public void userMemory(){
		tex = new Texture(Gdx.files.internal(image));
		tex2 = new Texture(Gdx.files.internal(image));
		tex3 = new Texture(Gdx.files.internal(image));
		tex4 = new Texture(Gdx.files.internal(image));
		tex5 = new Texture(Gdx.files.internal(image));
		tex6 = new Texture(Gdx.files.internal(image));
		tex7 = new Texture(Gdx.files.internal(image));
		tex8 = new Texture(Gdx.files.internal(image));
	}
	public void clearMemory(){
		tex.dispose();
		tex2.dispose();
		tex3.dispose();
		tex4.dispose();
		tex5.dispose();
		tex6.dispose();
		tex7.dispose();
		tex8.dispose();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}
}
