package com.icesoft.libgdx.dnd.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.icesoft.libgdx.dnd.DNDGame;
import com.icesoft.libgdx.dnd.actor.CooldownTextButton;

public class TestAltas implements Screen{
	private static final String T = "TestActor.class";
	
	private OrthographicCamera stageCamera = new OrthographicCamera();	
	private ScalingViewport stageViewport = new ScalingViewport(Scaling.fit, DNDGame.SCREEN_WIDTH, DNDGame.SCREEN_HEIGHT, stageCamera);
	private Stage stage = new Stage(stageViewport);
	
	private CooldownTextButton ctb;
	TextField xi,yi,uniti;
	Label xl,yl,unitl;
	
	float x,y,unit;
	Image item;
	@Override
	public void show() {
		xl = new Label("X:", DNDGame.SKIN);
		yl = new Label("Y:", DNDGame.SKIN);
		unitl = new Label("U:", DNDGame.SKIN);
		
		xi = new TextField("0", DNDGame.SKIN);
		yi = new TextField("0", DNDGame.SKIN);
		uniti = new TextField("0", DNDGame.SKIN);
		
		TextButton OK = new TextButton("OK",DNDGame.SKIN);
		OK.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				x = Float.valueOf(xi.getText());
				y = Float.valueOf(yi.getText());
				unit = Float.valueOf(uniti.getText());
				item.setPosition(x, y);
				item.setScale(unit/39);
			}			
		});
		Table table = new Table();
		table.add(xl).size(300,50);table.add(xi).size(200,50);table.row();
		table.add(yl).size(300,50);table.add(yi).size(200,50);table.row();
		table.add(unitl).size(300,50);table.add(uniti).size(200,50);table.row();
		table.add(OK).size(500,50).colspan(2);table.row();
		table.pack();
		table.setPosition(0, 500);
		
		stage.addActor(table);
		
		TextureAtlas t = new TextureAtlas(Gdx.files.internal("atlas/items/item01.txt"));
		TextureRegion tr = t.findRegion("1");
		Texture it = new Texture(Gdx.files.internal("atlas/items/inventoryitembase.png"));
		item = new Image(tr);
		item.setScale(1.25f);
		Image i = new Image(it);
		stage.addActor(i);
		stage.addActor(item);
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
