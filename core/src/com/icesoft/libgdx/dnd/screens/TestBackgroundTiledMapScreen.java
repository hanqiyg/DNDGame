package com.icesoft.libgdx.dnd.screens;

import java.util.Iterator;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
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
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.icesoft.libgdx.dnd.DNDGame;
import com.icesoft.libgdx.dnd.screens.TestBackgroundTiledMapScreen2.Hero;

public class TestBackgroundTiledMapScreen implements Screen{
	private static final String T = "TestBackgroundTiledMapScreen.class";
	private OrthographicCamera tiledCamera = new OrthographicCamera();
	private OrthographicCamera stageCamera = new OrthographicCamera();
	
	private ScalingViewport stageViewport = new ScalingViewport(Scaling.fit, DNDGame.SCREEN_WIDTH, DNDGame.SCREEN_HEIGHT, stageCamera);
	private Stage stage = new Stage(stageViewport);
	
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private static final float unit = 32;
	private ShapeRenderer debugRenderer;
	
	
	private TextureRegion up;
	private TextureRegion down;
	private TextureRegion left;
	private TextureRegion right;
	private TextureRegion stateFrame;
	private Label label;
	private Label scoreBoard;
	private int score = 0;
	
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
	public TestBackgroundTiledMapScreen(String mapTMX, Vector2 position){
		this.mapTMX = mapTMX;
		this.position = position;
	}

	@Override
	public void show() {
		initHero();
        map = new TmxMapLoader().load(mapTMX);
        renderer = new OrthogonalTiledMapRenderer(map, 1 / unit);
        
        tiledCamera.setToOrtho(false, 20f, ((float)DNDGame.SCREEN_HEIGHT/(float)DNDGame.SCREEN_WIDTH) * 20f);
        tiledCamera.update();
        
        debugRenderer = new ShapeRenderer();
        
        label = new Label("",DNDGame.SKIN);
        label.setBounds(0f, 0f, 100f, 50f);
        
        scoreBoard = new Label(String.valueOf(score),DNDGame.SKIN);
        scoreBoard.setBounds(960f, 0f, 300f, 50f);

        initMobs();
        initWalls();
        
        stage.addActor(label);
        stage.addActor(scoreBoard);
        Gdx.input.setInputProcessor(stage);
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
		if (deltaTime == 0) return;

		if (deltaTime > 0.1f)
			deltaTime = 0.1f;

		Hero.stateTime += deltaTime;
		
		if (Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A) || isTouched(0, 0.25f)) {
			Hero.state = Hero.State.left;
			Hero.velocity.x = - Hero.MAX_VELOCITY;
			Hero.velocity.y = 0;
		}

		if (Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D) || isTouched(0.25f, 0.5f)) {
			Hero.state = Hero.State.right;
			Hero.velocity.x =   Hero.MAX_VELOCITY;
			Hero.velocity.y = 0;
		}
		if (Gdx.input.isKeyPressed(Keys.UP) || Gdx.input.isKeyPressed(Keys.W) || isTouched(0, 0.25f)) {
			Hero.state = Hero.State.up;
			Hero.velocity.x = 0;  
			Hero.velocity.y =   Hero.MAX_VELOCITY;;
		}

		if (Gdx.input.isKeyPressed(Keys.DOWN) || Gdx.input.isKeyPressed(Keys.S) || isTouched(0.25f, 0.5f)) {
			Hero.state = Hero.State.down;
			Hero.velocity.x = 0;  
			Hero.velocity.y = - Hero.MAX_VELOCITY;;
		}
		
		Hero.velocity.x = MathUtils.clamp(Hero.velocity.x,
				-Hero.MAX_VELOCITY, Hero.MAX_VELOCITY);
		Hero.velocity.y = MathUtils.clamp(Hero.velocity.y,
				-Hero.MAX_VELOCITY, Hero.MAX_VELOCITY);
		
/*		if (Math.abs(Hero.velocity.x) < 1 && Math.abs(Hero.velocity.y)<1) {
			Hero.velocity.x = 0;
			Hero.velocity.y = 0;
			Hero.state = Hero.State.stand;
		}*/
		Hero.velocity.scl(deltaTime);
		MOB m = isPositionMob(Hero.position.x+Hero.velocity.x,Hero.position.y +Hero.velocity.y,Hero.width,Hero.height);
		if(m!=null){
			Hero.velocity.x = 0;
			Hero.velocity.y = 0;
			Gdx.app.debug(T, "hit mob:"+m.name);
			final Label l = labelPool.obtain();
			l.setText("+" + m.score);
			Vector3 source = vec3Pool.obtain();
			source.set(Hero.position.x,Hero.position.y,0);
			Vector3 target = vec3Pool.obtain();
			target = tiledCamera.project(source, 0, 0, DNDGame.SCREEN_WIDTH, DNDGame.SCREEN_HEIGHT);

			Gdx.app.debug(T, "source: " + source.x + "," + source.y);
			Gdx.app.debug(T, "target: " + target.x + "," + target.y);
			l.setPosition(target.x,target.y);
			vec3Pool.free(source);
			vec3Pool.free(target);
			stage.addActor(l);
			MoveByAction moveAction = Actions.moveBy(0, 100, 1);
			RunnableAction runAction = Actions.run(new Runnable() {
	            @Override
	            public void run() {
	                Gdx.app.debug(T, "runnable");
	                l.remove();
	                labelPool.free(l);
	            }
	        });
			SequenceAction sequenceAction = Actions.sequence(moveAction,runAction);
			l.addAction(sequenceAction);			
			
			score = (int) (score + m.score);
			mobs.removeValue(m, true);			
		}		
		if(isPositionWall(Hero.position.x+Hero.velocity.x,Hero.position.y +Hero.velocity.y,Hero.width,Hero.height)){
			Hero.velocity.x = 0;
			Hero.velocity.y = 0;
		}			
		Hero.position.add(Hero.velocity);
		Hero.velocity.scl(1/deltaTime);
		Hero.velocity.x *= Hero.DAMPING;
		Hero.velocity.y *= Hero.DAMPING;
	}
	
	private static final String WALL_LAYER = "wall";
	private Array<Rectangle> walls = new Array<Rectangle>();
	private void initWalls(){
		Object o = map.getLayers().get(WALL_LAYER);
		if(o instanceof TiledMapTileLayer){
			TiledMapTileLayer layer = (TiledMapTileLayer) o;
			for(int x=0;x<layer.getTileWidth();x++){
				for(int y=0;y<layer.getTileHeight();y++){
					Cell c = layer.getCell(x, y);
					if(c!=null){
						walls.add(new Rectangle(x,y,1,1));
						Gdx.app.debug(T, "Add Walls [x:"+x+",y:"+y+"]");
					}
				}
			}
		}
	}
	private boolean isPositionWall(float x,float y,float w,float h){
		Rectangle r = rectPool.obtain();
		r.set(x, y, w, h);
		for(Rectangle re:walls){
			if(r.overlaps(re)){
				Gdx.app.debug(T, "Wall [x:"+re.x+",y:"+re.y+",w:"+re.width+",h:"+re.height+"]");
				Gdx.app.debug(T, "Hero [x:"+ r.x+",y:"+ r.y+",w:"+r.width+",h:" +r.height +"]");
				rectPool.free(r);
				return true;
			}
		}
		rectPool.free(r);
		return false;
	}
	
	private MOB isPositionMob(float x, float y,float w,float h) {
		Rectangle r = rectPool.obtain();
		r.set(x,y,w,h);
		Rectangle mr = rectPool.obtain();
		for(MOB m : mobs){
			mr.set(m.position.x,m.position.y,1/unit * m.t.getRegionWidth(),1/unit * m.t.getRegionHeight());
			if(r.overlaps(mr)){
				Gdx.app.debug(T, "Hero:[" + Hero.position.x + ","+Hero.position.y+","+Hero.width+","+Hero.height+"]");
				Gdx.app.debug(T, "Mobs:[" + mr.x + ","+mr.y+","+mr.width+","+mr.height+"]");
				rectPool.free(r);
				rectPool.free(mr);
				return m;
			}
		}
		rectPool.free(r);
		rectPool.free(mr);
		return null;
	}
	public Array<MOB> mobs = new Array<MOB>();
	private Json json = new Json();
	private static final String MONSTER_PROFILE_PATH = "monster/";
	public class MOB{
		String name;
		float score;
		float range;
		float width;
		float height;
		TextureRegion t;
		Vector2 position = new Vector2();
		Vector2 velocity = new Vector2();
		Vector2 born = new Vector2();
		public MOB(String name,String filename,TextureRegion t,int x,int y){			
			this.name = name;
			this.born.x = x;
			this.born.y = y;
			this.width = t.getRegionWidth() /unit;
			this.height = t.getRegionHeight() /unit;
			this.t = t;
			this.position.x = x;
			this.position.y = y;
		}
	}

	private static final String DEFAULT_MONSTER_PROFILE = "default_monster.json";
	private void initMobs(){
		MapLayer layer = map.getLayers().get("object");
		for(MapObject obj : layer.getObjects()){
			Gdx.app.debug(T, "name:"+obj.getName()+"\topacity:"+obj.getOpacity()
								+"\tcolor:"+obj.getColor()
								+"\tclass:"+obj.getClass());
			Iterator<String> it = obj.getProperties().getKeys();
			while(it.hasNext()){
				String s = it.next();
				Gdx.app.debug(T, "key:"+s+"\tObject:"+obj.getProperties().get(s));
			}
			TiledMapTile tile = map.getTileSets().getTile((Integer) obj.getProperties().get("gid"));
			TextureRegion t = tile.getTextureRegion();
			int objx = (int) (obj.getProperties().get("x", 0f, Float.class)/unit);
			int objy = (int) (obj.getProperties().get("y", 0f, Float.class)/unit);
			int w = (int) (obj.getProperties().get("width", 0f, Float.class)/unit);
			int h = (int) (obj.getProperties().get("height", 0f, Float.class)/unit);
			float score = obj.getProperties().get("score",Float.class);
			float range = obj.getProperties().get("range",Float.class);
			String name = obj.getName();
			String filename = obj.getProperties().get("filename",DEFAULT_MONSTER_PROFILE,String.class);
			mobs.add(new MOB(name,filename,t,objx,objy));
			Gdx.app.debug(T, "x:"+objx+"\ty:"+objy);
		}
	}
	private static final float MOB_SPEED = 2f;
	private Vector2 MOB_Left = new Vector2(-1,0);
	private Vector2 MOB_Right = new Vector2(1,0);
	private Vector2 MOB_Up = new Vector2(0,1);
	private Vector2 MOB_Down = new Vector2(0,-1);
	private Vector2[] directions = {MOB_Left,MOB_Right,MOB_Up,MOB_Down};
	private Random random = new Random();
	
	private void updateMobs(float deltaTime){
		for(MOB m:mobs){
			if(m!=null){
				if(m.velocity.x < MOB_SPEED && m.velocity.y <MOB_SPEED){
					Vector2 direction= vec2Pool.obtain();
					direction = directions[random.nextInt(4)];
					m.velocity.x = direction.x * MOB_SPEED;
					m.velocity.y = direction.y * MOB_SPEED;
				}
				m.velocity.x = MathUtils.clamp(m.velocity.x,
						-MOB_SPEED, MOB_SPEED);
				m.velocity.y = MathUtils.clamp(m.velocity.y,
						-MOB_SPEED, MOB_SPEED);
				m.velocity.scl(deltaTime);
				if(!isMobInBound(m)){
					m.velocity.x = 0;
					m.velocity.y = 0;
				}				
				m.position.add(m.velocity);
				m.velocity.scl(1/deltaTime);
			}
		}
	}
	
	private boolean isMobInBound(MOB m) {
		Vector2 p = vec2Pool.obtain();
		p.set(m.position);
		Gdx.app.debug(T, p.toString());
		p.add(m.velocity);
		Gdx.app.debug(T, p.toString());
		if(p.dst(m.born)<m.range && !isPositionWall(p.x, p.y, m.width, m.height)){
			vec2Pool.free(p);
			return true;
		}else{
			vec2Pool.free(p);
			return false;
		}
	}


	private void MOBrender(float delta){
		for(MOB m:mobs){
			if(m!=null){
				renderer.getBatch().begin();
				renderer.getBatch().draw(m.t, m.position.x, m.position.y,1 / unit * m.t.getRegionWidth(),1/unit * m.t.getRegionHeight());
				renderer.getBatch().end();
			}
		}
	}
	
	private boolean isTouched(float f, float g) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		label.setText("FPS:" + Gdx.graphics.getFramesPerSecond());
		tiledCamera.position.set(Hero.position.x, Hero.position.y, 0);
		tiledCamera.update();
		renderer.setView(tiledCamera);
		renderer.render();
		
        scoreBoard.setText(String.valueOf(score));
        
		heroUpdate(delta);
		heroRender(delta);
		
		updateMobs(delta);
		MOBrender(delta);
		
		stage.act();
		stage.draw();
	}
	private void renderDebug () {
		debugRenderer.setProjectionMatrix(tiledCamera.combined);
		debugRenderer.begin(ShapeType.Line);

		debugRenderer.setColor(Color.YELLOW);
		TiledMapTileLayer layer = (TiledMapTileLayer)map.getLayers().get("wall");
		for (int y = 0; y <= layer.getHeight(); y++) {
			for (int x = 0; x <= layer.getWidth(); x++) {
				Cell cell = layer.getCell(x, y);
				if (cell != null) {
					if (tiledCamera.frustum.boundsInFrustum(x + 0.5f, y + 0.5f, 0, 1, 1, 0))
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
