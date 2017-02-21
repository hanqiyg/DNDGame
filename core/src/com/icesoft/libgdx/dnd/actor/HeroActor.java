package com.icesoft.libgdx.dnd.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

public class HeroActor extends Actor {
	private static final String T = "HeroActor.class";
	private Animation walkUp;
	private Animation walkRight;
	private Animation walkDown;
	private Animation walkLeft;
	private TextureRegion standing;
	
	private TextureRegion standingUp;
	private TextureRegion standingRight;
	private TextureRegion standingDown;
	private TextureRegion standingLeft;
	
	private Array<Rectangle> tiles = new Array<Rectangle>();
	
	private float stateTime;
	static float MAX_VELOCITY = 10f;
	static float DAMPING = 0.87f;

	final Vector2 position = new Vector2();
	final Vector2 velocity = new Vector2();
	
	private Pool<Rectangle> rectPool = new Pool<Rectangle>() {
		@Override
		protected Rectangle newObject () {
			return new Rectangle();
		}
	};
	
	private enum State {
		Standing, MoveLeft,MoveRight,MoveUp,MoveDown
	}
	private State state = State.MoveLeft;
	private float unit;
	
	private int HERO = 3;
	private static final int HERO_WIDTH = 25, HERO_HEIGHT = 32;
	private TiledMap map;
	public HeroActor(float unit,TiledMap map){
		
		this.map = map;
		this.unit =unit;
		TextureRegion[][] regions = TextureRegion.split(new Texture(Gdx.files.internal("images/hero.png")), HERO_WIDTH, HERO_HEIGHT);
		System.out.println(regions.length +"x" +regions[0].length);
		walkUp = new Animation(0.15f, regions[HERO][1], regions[HERO][2], regions[HERO][3]);
		walkUp.setPlayMode(Animation.PlayMode.LOOP_PINGPONG); 
		walkRight = new Animation(0.15f, regions[HERO+1][1], regions[HERO+1][2], regions[HERO+1][3]);
		walkRight.setPlayMode(Animation.PlayMode.LOOP_PINGPONG); 
		walkDown = new Animation(0.15f, regions[HERO+2][1], regions[HERO+2][2], regions[HERO+2][3]);
		walkDown.setPlayMode(Animation.PlayMode.LOOP_PINGPONG); 
		walkLeft = new Animation(0.15f, regions[HERO+3][1], regions[HERO+3][2], regions[HERO+3][3]);
		walkLeft.setPlayMode(Animation.PlayMode.LOOP_PINGPONG); 
		
		standingUp = regions[HERO][2];
		standingRight = regions[HERO+1][2];
		standingDown = regions[HERO+2][2];
		standingLeft = regions[HERO+3][2];
		standing = standingUp;
		this.setWidth(unit);
		this.setHeight(unit);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		updateKoala(parentAlpha);
		TextureRegion frame = null;
		switch(state){
		case MoveDown:frame = walkDown.getKeyFrame(stateTime);standing = standingDown;
			break;
		case MoveLeft:frame = walkLeft.getKeyFrame(stateTime);standing = standingLeft;
			break;
		case MoveRight:frame = walkRight.getKeyFrame(stateTime);standing = standingRight;
			break;
		case MoveUp:frame = walkUp.getKeyFrame(stateTime);standing = standingUp;
			break;
		case Standing:frame = standing;
			break;
		default:frame = walkUp.getKeyFrame(stateTime);standing = standingUp;
			break;		
		}
		batch.draw(frame, (float)(this.getX()+((HERO_WIDTH-unit)/2)),(float)(this.getY()+((HERO_HEIGHT-unit)/2)));
	}
	private void updateKoala (float deltaTime) {
		if (deltaTime == 0) return;

		if (deltaTime > 0.1f)
			deltaTime = 0.1f;

		stateTime += deltaTime;



		if (Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A) || isTouched(0, 0.25f)) {
			velocity.x = -MAX_VELOCITY;
			state = State.MoveRight;
		}

		if (Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D) || isTouched(0.25f, 0.5f)) {
			velocity.x = MAX_VELOCITY;
			state = State.MoveLeft;
		}
		if (Gdx.input.isKeyPressed(Keys.UP) || Gdx.input.isKeyPressed(Keys.W) || isTouched(0, 0.25f)) {
			velocity.y = MAX_VELOCITY;
			state = State.MoveUp;
		}

		if (Gdx.input.isKeyPressed(Keys.DOWN) || Gdx.input.isKeyPressed(Keys.S) || isTouched(0.25f, 0.5f)) {
			Gdx.app.debug(T, "down");
			velocity.y = -MAX_VELOCITY;
			state = State.MoveDown;
		}

		// clamp the velocity to the maximum, x-axis only
		velocity.x = MathUtils.clamp(velocity.x,
				-MAX_VELOCITY, MAX_VELOCITY);
		velocity.y = MathUtils.clamp(velocity.y,
				-MAX_VELOCITY, MAX_VELOCITY);

		// If the velocity is < 1, set it to 0 and set state to Standing
		if (Math.abs(velocity.x) < 1 && Math.abs(velocity.y)<1) {
			velocity.x = 0;
			velocity.y = 0;
			state = State.Standing;
		}

		// multiply by delta time so we know how far we go
		// in this frame
		velocity.scl(deltaTime);

		// perform collision detection & response, on each axis, separately
		// if the koala is moving right, check the tiles to the right of it's
		// right bounding box edge, otherwise check the ones to the left
		Rectangle koalaRect = rectPool.obtain();
		koalaRect.set(position.x, position.y, unit, unit);
		int startX, startY, endX, endY;
		if (velocity.x > 0) {
			startX = endX = (int)(position.x + unit + velocity.x);
		} else {
			startX = endX = (int)(position.x + velocity.x);
		}
		if (velocity.y > 0) {
			startY = endY = (int)(position.y + unit + velocity.y);
		} else {
			startY = endY = (int)(position.y + velocity.y);
		}

		getTiles(startX, startY, endX, endY, tiles);
		koalaRect.x += velocity.x;
		koalaRect.y += velocity.y;
		for (Rectangle tile : tiles) {
			if (koalaRect.overlaps(tile)) {
				velocity.x = 0;
				velocity.y = 0;
				break;
			}
		}
		

		rectPool.free(koalaRect);

		// unscale the velocity by the inverse delta time and set
		// the latest position
		position.add(velocity);
		velocity.scl(1 / deltaTime);

		// Apply damping to the velocity on the x-axis so we don't
		// walk infinitely once a key was pressed
		velocity.x *= DAMPING;
		velocity.y *= DAMPING;
	}

	private boolean isTouched(float g, float f) {
		// TODO Auto-generated method stub
		return false;
	}
	private void getTiles (int startX, int startY, int endX, int endY, Array<Rectangle> tiles) {
		TiledMapTileLayer layer = (TiledMapTileLayer)map.getLayers().get("wall");
		rectPool.freeAll(tiles);
		tiles.clear();
		for (int y = startY; y <= endY; y++) {
			for (int x = startX; x <= endX; x++) {
				Cell cell = layer.getCell(x, y);
				if (cell != null) {
					Rectangle rect = rectPool.obtain();
					rect.set(x, y, 1, 1);
					tiles.add(rect);
				}
			}
		}
	}
}
