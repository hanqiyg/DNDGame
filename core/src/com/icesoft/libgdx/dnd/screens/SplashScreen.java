package com.icesoft.libgdx.dnd.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.icesoft.libgdx.dnd.DNDGame;

public class SplashScreen implements Screen{
	private static final float SPLASH_TIME = 0.5f;
	private DNDGame game;
	private Animation splashAnimation;
	private float stateTime;
	private SpriteBatch batch;
	private ShapeRenderer shapeRenderer;
	private Color filled = Color.YELLOW;
	private Color border = Color.BLACK;
	
	public SplashScreen(DNDGame game){
		this.game = game;
		TextureAtlas splash = new TextureAtlas(Gdx.files.internal("atlas/splash.atlas"));
		splashAnimation = new Animation(0.15f, splash.getRegions());
		splashAnimation.setPlayMode(Animation.PlayMode.LOOP);
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
	}
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT); 
		stateTime += Gdx.graphics.getDeltaTime();
		if((!(game.assets.manager.update()))|| (!(game.assets.views.update())) || (stateTime <= SPLASH_TIME)){
			drawLogo();
		}else{
			//game.testScreen();
			game.mainScreen();
		}
	}
	public void drawLogo(){
		TextureRegion frame = splashAnimation.getKeyFrame(stateTime);
		float width = Gdx.graphics.getWidth() * 0.8f;
		float height = frame.getRegionHeight() * frame.getRegionWidth() / width;
		batch.begin();
		batch.draw(frame, Gdx.graphics.getWidth() /2 - width /2, Gdx.graphics.getHeight()/2 - height /2, width, height);
		batch.end();
		
		float proWidth = Gdx.graphics.getWidth() * 0.6f;
		float proHeight = proWidth * 0.05f;
		float x = Gdx.graphics.getWidth() /2 - proWidth /2;
		float y = Gdx.graphics.getHeight()/2 - height /2 - proHeight - height * 0.1f;
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(filled.r,filled.g,filled.b,filled.a);
        shapeRenderer.rect(x, y, proWidth * (game.assets.manager.getProgress() + game.assets.views.getProgress())/2,proHeight);
        shapeRenderer.end();
        
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(border.r,border.g,border.b,border.a);
        shapeRenderer.rect(x, y, proWidth, proHeight);
        shapeRenderer.end();
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
		batch.dispose();		
	}

}
