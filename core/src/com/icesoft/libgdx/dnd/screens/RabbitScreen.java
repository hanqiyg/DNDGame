package com.icesoft.libgdx.dnd.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.icesoft.libgdx.dnd.DNDGame;

public class RabbitScreen implements Screen,GestureListener{
	private static final String T = "RabbitScreen.class";
	private OrthographicCamera tiledCamera = new OrthographicCamera();
	private OrthographicCamera stageCamera = new OrthographicCamera();
	
	private ScalingViewport stageViewport = new ScalingViewport(Scaling.fit, DNDGame.SCREEN_WIDTH, DNDGame.SCREEN_HEIGHT, stageCamera);
	private Stage stage = new Stage(stageViewport);
	
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private static final float unit = 32;
	
	
	private TextureRegion up;
	private TextureRegion down;
	private TextureRegion left;
	private TextureRegion right;
	private TextureRegion stateFrame;
	private Label label;
	private Label scoreBoard;
	
	public Pool<Rectangle> rectPool = new Pool<Rectangle>() {
		@Override
		protected Rectangle newObject () {
			return new Rectangle();
		}
	};
	
	public Pool<Vector3> vec3Pool = new Pool<Vector3>() {
		@Override
		protected Vector3 newObject () {
			return new Vector3();
		}
	};
	public Pool<Vector2> vec2Pool = new Pool<Vector2>() {
		@Override
		protected Vector2 newObject () {
			return new Vector2();
		}
	};
	
	public Pool<Label> labelPool = new Pool<Label>() {
		@Override
		protected Label newObject () {
			return new Label("",DNDGame.SKIN);
		}
	};
	
	public static class Hero{
		public static enum State {
			stand,up,down,left,right
		}
		public static State state = State.up;
		public static Vector2 position = new Vector2();
		public static Vector2 velocity = new Vector2();
		public static float width=0f,height=0f;
		public static float stateTime;
		public static final float MAX_VELOCITY = 10f;
		public static final float DAMPING = 0.87f;
	}
	
	private String mapTMX;
	private Vector2 position;
	public RabbitScreen(String mapTMX, Vector2 position){
		this.mapTMX = mapTMX;
		this.position = position;
	}

	@Override
	public void show() {

        map = new TmxMapLoader().load(mapTMX);
        renderer = new OrthogonalTiledMapRenderer(map, 1 / unit);
        
        tiledCamera.setToOrtho(false, 20f, ((float)DNDGame.SCREEN_HEIGHT/(float)DNDGame.SCREEN_WIDTH) * 20f);
        tiledCamera.update();        
		initHero();
		initCarrit();
		initFinish();
        Gdx.input.setInputProcessor(new GestureDetector(this));
	}

	
	public void initHero(){
		TextureAtlas ta = new TextureAtlas("atlas/hero.txt");
		up = ta.findRegion("up");
		down = ta.findRegion("down");
		left = ta.findRegion("left");
		right = ta.findRegion("right");
		stateFrame = up;
		
		Hero.position.set(position);
		Hero.height = 1/ unit * stateFrame.getRegionHeight();
		Hero.width = 1/unit * stateFrame.getRegionWidth();
	}
	
	public void heroRender(float deltaTime){
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
	
	public void heroUpdate(float deltaTime){
		
	}
	private void initCarrit(){
		Object o = map.getLayers().get("carrit");
		if(o instanceof TiledMapTileLayer){
			TiledMapTileLayer layer = (TiledMapTileLayer) o;
			for(int x=0;x<layer.getTileWidth();x++){
				for(int y=0;y<layer.getTileHeight();y++){
					Cell c = layer.getCell(x, y);
					if(c!=null){
						carrit.add(new Rectangle(x,y,1,1));
						Gdx.app.debug(T, "Add Carrit [x:"+x+",y:"+y+"]");
					}
				}
			}
		}
	}
	private void initFinish(){
		Object o = map.getLayers().get("finish");
		if(o instanceof TiledMapTileLayer){
			TiledMapTileLayer layer = (TiledMapTileLayer) o;
			for(int x=0;x<layer.getTileWidth();x++){
				for(int y=0;y<layer.getTileHeight();y++){
					Cell c = layer.getCell(x, y);
					if(c!=null){
						finish = new Rectangle(x,y,1,1);
						Gdx.app.debug(T, "Add Finish [x:"+x+",y:"+y+"]");
					}
				}
			}
		}
	}


	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//tiledCamera.position.set(Hero.position.x, Hero.position.y, 0);
		tiledCamera.update();
		renderer.setView(tiledCamera);
		renderer.render();
		
		updateLine();
        
		heroUpdate(delta);
		heroRender(delta);
		drawLast();
		
		stage.act();
		stage.draw();
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
	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		Gdx.app.debug(T, "touchDown");
		Rectangle h = rectPool.obtain();
		Vector3 v = transTiled(x,y);
		h.set(Hero.position.x, Hero.position.y, Hero.width, Hero.height);
		Gdx.app.debug(T, "x:"+h.x+",y:"+h.y+",w:"+h.width+",h:"+h.height);
		Gdx.app.debug(T, "x:"+v.x+",y:"+v.y);
		if(h.contains(new Vector2(v.x,v.y))){
			path.clear();
			path.add(h);
			rectPool.free(h);
			Gdx.app.debug(T, "start add" + v.x + "," + v.y);
			return true;
		}
		return false;
	}
	
	public Vector3 transTiled(float x,float y){
		return tiledCamera.unproject(new Vector3(x,y,0));
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		// TODO Auto-generated method stub
		return false;
	}
	Array<Rectangle> carrit = new Array<Rectangle>();
	Array<Rectangle> path = new Array<Rectangle>();
	Rectangle finish;
	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		Gdx.app.debug(T, "pan");
		Vector3 v = transTiled(x+deltaX,y+deltaY);
		this.x = v.x;
		this.y = v.y;
		if(path.size>0){
			for(Rectangle r:path){
				if(!r.contains(new Vector2(this.x,this.y))){
					for(Rectangle c:carrit){
						if(c.contains(new Vector2(this.x,this.y))){
							path.add(c);
							Gdx.app.debug(T, "path" + path.size + "add" + this.x + "," + this.y);
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	private float x=0,y=0;
	private void drawLast() {
		if(path!=null && path.size>0){
			rederer.setProjectionMatrix(tiledCamera.combined);
			rederer.begin(ShapeType.Line);
			rederer.setColor(Color.YELLOW);
			rederer.line(getCenterOfRectangle(path.get(path.size-1)), new Vector2(x,y));
			rederer.end();
		}
	}
	ShapeRenderer rederer = new ShapeRenderer();
	private void updateLine() {		
		rederer.setProjectionMatrix(tiledCamera.combined);
		rederer.begin(ShapeType.Line);
		rederer.setColor(Color.YELLOW);
		if(path!=null &&path.size>1){
			Vector2 last = getCenterOfRectangle(path.get(0));
			for(int i=1;i<path.size;i++){
				if(last!=null){
					rederer.line(last, getCenterOfRectangle(path.get(i)));
					last = getCenterOfRectangle(path.get(i));
				}
			}
		}
		rederer.end();
	}
	private Vector2 getCenterOfRectangle(Rectangle r){
		return new Vector2(r.x + r.width/2,r.y+r.height/2);
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		if(finish.contains(new Vector2(x,y))){
			Gdx.input.setInputProcessor(null);
			run();
			return true;
		}
		return false;
	}

	private void run() {
		
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void pinchStop() {
		// TODO Auto-generated method stub
		
	}
}
