package com.icesoft.libgdx.dnd.screens;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.icesoft.libgdx.dnd.DNDGame;
import com.icesoft.libgdx.dnd.actor.CooldownTextButton;
import com.icesoft.libgdx.dnd.actor.PentagonActor;
import com.icesoft.libgdx.dnd.actor.PentagonActor.PentagonData;

public class TestPentagon implements Screen{
	private static final String T = "TestActor.class";
	
	private OrthographicCamera stageCamera = new OrthographicCamera();	
	private ScalingViewport stageViewport = new ScalingViewport(Scaling.fit, DNDGame.SCREEN_WIDTH, DNDGame.SCREEN_HEIGHT, stageCamera);
	private Stage stage = new Stage(stageViewport);
	
	private CooldownTextButton ctb;
	TextField xi,yi,uniti;
	Label xl,yl,unitl;
	
	public TestPentagon(DNDGame game){

	}	
	
	float x,y,unit;
	Image item;
	@Override
	public void show() {
		PentagonData s1 = new PentagonData("1", 100);
		PentagonData s2 = new PentagonData("2", 50);
		PentagonData s3 = new PentagonData("3", 30);
		PentagonData s4 = new PentagonData("4", 70);
		PentagonData s5 = new PentagonData("5", 80);
		List<PentagonData> list = new ArrayList<PentagonData>();
		list.add(s1);list.add(s2);list.add(s3);list.add(s4);list.add(s5);list.add(s4);list.add(s4);
		PentagonActor a = new PentagonActor(list);
		a.setBounds(0, 0, 800, 800);
		
		stage.addActor(a);
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
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
