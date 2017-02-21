package com.icesoft.libgdx.dnd.screens;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.icesoft.libgdx.dnd.DNDGame;

public class TestBackgroundTiledMapScreen2 implements Screen{
	private static final String T = "TestBackgroundTiledMapScreen.class";
	private OrthographicCamera camera;
	private ScalingViewport fullHD = new ScalingViewport(Scaling.fit, DNDGame.SCREEN_WIDTH, DNDGame.SCREEN_HEIGHT, new OrthographicCamera());
	private Stage stage = new Stage(fullHD);
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private Viewport port;
    private static final float unit = 32;
	private ShapeRenderer debugRenderer;
	
	
	private TextureRegion up;
	private TextureRegion down;
	private TextureRegion left;
	private TextureRegion right;
	private TextureRegion stateFrame;
	private Label label;
	
	public Pool<Rectangle> rectPool = new Pool<Rectangle>() {
		@Override
		protected Rectangle newObject () {
			return new Rectangle();
		}
	};
	
	public static class Hero{
		public static enum State {
			up,down,left,right
		}
		public static State state = State.up;
		public static Vector2 position = new Vector2();
		public static float width=0,height=0;
	}
	

	
	private static final String MAP_LEVEL1 = "maps/" + "level1.tmx";
	@Override
	public void show() {
		initHero();
        map = new TmxMapLoader().load(MAP_LEVEL1);
        renderer = new OrthogonalTiledMapRenderer(map, 1 / unit);
        camera = new OrthographicCamera();
        port = new ScalingViewport(Scaling.fit,DNDGame.SCREEN_WIDTH, DNDGame.SCREEN_WIDTH,camera);
        
        camera.setToOrtho(false, 20f, ((float)DNDGame.SCREEN_HEIGHT/(float)DNDGame.SCREEN_WIDTH) * 20f);
        camera.update();
        debugRenderer = new ShapeRenderer();
        label = new Label("",DNDGame.SKIN);
        label.setBounds(0f, 0f, 100f, 50f);
        initMobs();
        stage.addActor(label);
        Gdx.input.setInputProcessor(stage);
	}

	
	public void initHero(){
		TextureAtlas ta = new TextureAtlas("atlas/hero.txt");
		up = ta.findRegion("up");
		down = ta.findRegion("down");
		left = ta.findRegion("left");
		right = ta.findRegion("right");
		stateFrame = up;
		
		Hero.position.set(25,20);
		Hero.height = 1/ unit * stateFrame.getRegionHeight();
		Hero.width = 1/unit * stateFrame.getRegionWidth();
	}
	
	public void heroRender(){
		switch(Hero.state){
		case down:	stateFrame = down;
			break;
		case left:	stateFrame = left;
			break;
		case right:	stateFrame = right;
			break;
		case up:  	stateFrame = up;
			break;
		default:	stateFrame = up;
			break;		
		}
		Batch batch = renderer.getBatch();
		batch.begin();
		batch.draw(stateFrame, Hero.position.x, Hero.position.y, Hero.width, Hero.height);
		batch.end();		
	}
	public void heroUpdate(){
		if (Gdx.input.isKeyJustPressed(Keys.LEFT) || Gdx.input.isKeyJustPressed(Keys.A) || isTouched(0, 0.25f)) {
			Hero.state = Hero.State.left;
			if(isPositionWalkable(Hero.position.x - 1,Hero.position.y)){
				if(isPositionMob(Hero.position.x - 1,Hero.position.y)){
					Gdx.app.debug(T, "Mobs");
				}
				Hero.position.x = Hero.position.x - 1;				
			}
			Gdx.app.debug(T, "heroUpdateLeft" + "positon:" + Hero.position.x+","+Hero.position.y);
		}

		if (Gdx.input.isKeyJustPressed(Keys.RIGHT) || Gdx.input.isKeyJustPressed(Keys.D) || isTouched(0.25f, 0.5f)) {
			Hero.state = Hero.State.right;
			if(isPositionWalkable(Hero.position.x + 1,Hero.position.y)){
				if(isPositionMob(Hero.position.x - 1,Hero.position.y)){
					Gdx.app.debug(T, "Mobs");
				}
				Hero.position.x = Hero.position.x + 1;
			}
			Gdx.app.debug(T, "heroUpdateRight" + "positon:" + Hero.position.x+","+Hero.position.y);
		}
		if (Gdx.input.isKeyJustPressed(Keys.UP) || Gdx.input.isKeyJustPressed(Keys.W) || isTouched(0, 0.25f)) {
			Hero.state = Hero.State.up;
			if(isPositionWalkable(Hero.position.x,Hero.position.y + 1)){
				if(isPositionMob(Hero.position.x - 1,Hero.position.y)){
					Gdx.app.debug(T, "Mobs");
				}
				Hero.position.y = Hero.position.y + 1;
			}
			Gdx.app.debug(T, "heroUpdateUp" + "positon:" + Hero.position.x+","+Hero.position.y);
		}

		if (Gdx.input.isKeyJustPressed(Keys.DOWN) || Gdx.input.isKeyJustPressed(Keys.S) || isTouched(0.25f, 0.5f)) {
			Hero.state = Hero.State.down;
			if(isPositionWalkable(Hero.position.x,Hero.position.y - 1)){
				if(isPositionMob(Hero.position.x - 1,Hero.position.y)){
					Gdx.app.debug(T, "Mobs");
				}
				Hero.position.y = Hero.position.y - 1;
			}
			Gdx.app.debug(T, "heroUpdateDown" + "positon:" + Hero.position.x+","+Hero.position.y);
		}
	}
	private boolean isPositionMob(float x, float y) {
		Rectangle r = rectPool.obtain();
		r.set(x,y,Hero.width,Hero.height);
		Gdx.app.debug(T, "rect_mobs:" + rect_mobs.size);
		for(Rectangle re : rect_mobs){
			if(r.overlaps(re)){
				Gdx.app.debug(T, "Hero:[" + Hero.position.x + ","+Hero.position.y+","+Hero.width+","+Hero.height+"]");
				Gdx.app.debug(T, "Mobs:[" + re.x + ","+re.y+","+re.width+","+re.height+"]");
				return true;
			}
		}
		rectPool.free(r);
		return false;
	}
	Array<MOB> mobs = new Array<MOB>();
	public class MOB{
		TextureRegion t;
		int x,y;
		public MOB(TextureRegion t,int x,int y){
			this.t = t;
			this.x = x;
			this.y = y;
		}
	}
	private void initMobs(){
		MapLayer layer = map.getLayers().get("object");
		for(MapObject obj : layer.getObjects()){
			Gdx.app.debug(T, "name:"+obj.getName()+"\topacity:"+obj.getOpacity()
								+"\tcolor:"+obj.getColor()
								+"\tclass:"+obj.getClass());
			Iterator<String> it =obj.getProperties().getKeys();
			while(it.hasNext()){
				String key = it.next();
				Object value = obj.getProperties().get(key);
				Gdx.app.debug(T, "key:"+key+"\tvalue:"+value.toString());
			}
			TiledMapTile tile = map.getTileSets().getTile((Integer) obj.getProperties().get("gid"));
			TextureRegion t = tile.getTextureRegion();
			int objx = (int) (obj.getProperties().get("x", 0f, Float.class)/unit);
			int objy = (int) (obj.getProperties().get("y", 0f, Float.class)/unit);
			mobs.add(new MOB(t,objx,objy));
			Gdx.app.debug(T, "x:"+objx+"\ty:"+objy);
		}
	}
	private Array<Rectangle> rect_mobs = new Array<Rectangle>();
	private void updateMobs(){
		rectPool.freeAll(rect_mobs);
		rect_mobs.clear();
		for(MOB m : mobs){
			Rectangle r = rectPool.obtain();
			r.set(m.x, m.y,1 / unit * m.t.getRegionWidth(),1/unit * m.t.getRegionHeight());
			//Gdx.app.debug(T, "AddMobs:[" + r.x + ","+r.y+","+r.width+","+r.height+"]");
			rect_mobs.add(r);
		}
	}
	private void MOBrender(){
		for(MOB m:mobs){
			if(m!=null){
				renderer.getBatch().begin();
				renderer.getBatch().draw(m.t, m.x, m.y,1 / unit * m.t.getRegionWidth(),1/unit * m.t.getRegionHeight());
				renderer.getBatch().end();
			}
		}
	}
	
	private void checkMessage(float x,float y) {
		MapLayer layer = map.getLayers().get("object");
		for(MapObject obj : layer.getObjects()){
			Gdx.app.debug(T, "name:"+obj.getName()+"\topacity:"+obj.getOpacity()
								+"\tcolor:"+obj.getColor()
								+"\tclass:"+obj.getClass());
			Iterator<String> it =obj.getProperties().getKeys();
			while(it.hasNext()){
				String key = it.next();
				Object value = obj.getProperties().get(key);
				Gdx.app.debug(T, "key:"+key+"\tvalue:"+value.toString());
			}		
		}
	}
	private boolean isTouched(float f, float g) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean isPositionWalkable(float x, float y) {
		TiledMapTileLayer layer = (TiledMapTileLayer)map.getLayers().get("wall");
		Cell cell = layer.getCell((int)x, (int)y);
		if (cell != null) {
			return false;
		}else{
			return true;
		}	
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		label.setText("FPS:" + Gdx.graphics.getFramesPerSecond());
		camera.position.set(Hero.position.x, Hero.position.y, 0);
		camera.update();
		renderer.setView(camera);
		renderer.render();
		
		heroUpdate();
		heroRender();
		
		updateMobs();
		MOBrender();
		
		stage.act();
		stage.draw();
	}
	private void renderDebug () {
		debugRenderer.setProjectionMatrix(camera.combined);
		debugRenderer.begin(ShapeType.Line);

		debugRenderer.setColor(Color.YELLOW);
		TiledMapTileLayer layer = (TiledMapTileLayer)map.getLayers().get("wall");
		for (int y = 0; y <= layer.getHeight(); y++) {
			for (int x = 0; x <= layer.getWidth(); x++) {
				Cell cell = layer.getCell(x, y);
				if (cell != null) {
					if (camera.frustum.boundsInFrustum(x + 0.5f, y + 0.5f, 0, 1, 1, 0))
						debugRenderer.rect(x, y, 1, 1);
				}
			}
		}
		debugRenderer.end();
	}

	@Override
	public void resize(int width, int height) {

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
