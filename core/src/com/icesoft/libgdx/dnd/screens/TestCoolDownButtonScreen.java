package com.icesoft.libgdx.dnd.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.icesoft.libgdx.dnd.actor.CoolDownButton;

public class TestCoolDownButtonScreen implements Screen{
	private static final String T = "TestCoolDownButtonScreen.class";
	
	private TextureRegion blacked;
	private TextureRegion colored;
	
	private Stage stage;
	private CoolDownButton cdb;
	
	@Override
	public void show() {
		TextureAtlas iconBlack = new TextureAtlas(Gdx.files.internal("atlas/IconBlack.txt"));
		blacked = iconBlack.findRegion("DISBTN3M3");
		
		TextureAtlas iconColor = new TextureAtlas(Gdx.files.internal("atlas/IconColor.txt"));
		colored = iconColor.findRegion("BTN3M3");

		cdb = new CoolDownButton(colored,blacked,3);
		cdb.setBounds(100f, 100f, 64f, 64f);
		cdb.setClickListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.debug(T, event + "["+x+","+y+"]");
				cdb.cooldown();
				Gdx.app.debug(T, "OnClick.");
			}			
		});

		stage = new Stage();
		
		stage.addActor(cdb);
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		cdb.setTime(Gdx.graphics.getDeltaTime());
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
