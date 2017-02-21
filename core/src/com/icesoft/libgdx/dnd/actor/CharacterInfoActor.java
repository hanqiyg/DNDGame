package com.icesoft.libgdx.dnd.actor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.icesoft.libgdx.dnd.character.GameChar;

public class CharacterInfoActor extends Actor {
	private GameChar character;
	private BitmapFont font;
	
	private String HP_String = "HP:";
	private String MP_String = "MP:";
	
	private Texture photo;
	
	private static final float PHOTO_WIDTH = 200f;
	private static final float PHOTO_HEIGHT = 200f;
	private static final float STATE_WIDTH = 400f;
	private static final float STATE_HEIGHT = 50f;
	private static final float SPACING = 10f;
	
	private static final Color filledHP = Color.RED;
	private static final Color filledMP = Color.BLUE;
	private static final Color border = Color.BLACK;
	private static final Color background = Color.GRAY;
	
	private ShapeRenderer shapeRenderer;
	private GlyphLayout layout;
	private float fontX;
	private float fontY;
	
/*	public CharacterInfoActor(Character c,BitmapFont font){
		this.character = c;
		this.font = font;
		photo = new Texture(Gdx.files.internal(c.getPhoto()));

		shapeRenderer = new ShapeRenderer();
		layout = new GlyphLayout();
	}
	
	public void update(Character c){
		this.character = c;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.end();
		
	    shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
	    shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
	    shapeRenderer.translate(this.getX(), this.getY(), 0);
	    
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(background.r,background.g,background.b,background.a);
        shapeRenderer.rect(0, 0, this.getWidth(),this.getHeight());
        shapeRenderer.end();
        
	    shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
	    shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
	    shapeRenderer.translate(this.getX()+PHOTO_WIDTH + SPACING, this.getY()+STATE_HEIGHT*3 + SPACING, 0);
	    
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(filledHP.r,filledHP.g,filledHP.b,filledHP.a);
        shapeRenderer.rect(0, 0, STATE_WIDTH * ((float)character.getHP()/(float)character.getMaxHP()) - SPACING * 2, STATE_HEIGHT - SPACING * 2);
        shapeRenderer.end();
        
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(border.r,border.g,border.b,border.a);
        shapeRenderer.rect(0, 0, STATE_WIDTH - SPACING * 2, STATE_HEIGHT - SPACING * 2);
        shapeRenderer.end();
        
	    shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
	    shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
	    shapeRenderer.translate(this.getX()+PHOTO_WIDTH + SPACING, this.getY()+STATE_HEIGHT*1 + SPACING, 0);
	    
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(filledMP.r,filledMP.g,filledMP.b,filledMP.a);
        shapeRenderer.rect(0, 0, STATE_WIDTH * ((float)character.getMP()/(float)character.getMaxMP()) - SPACING * 2, STATE_HEIGHT - SPACING * 2);
        shapeRenderer.end();
        
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(border.r,border.g,border.b,border.a);
        shapeRenderer.rect(0, 0, STATE_WIDTH - SPACING * 2, STATE_HEIGHT - SPACING * 2);
        shapeRenderer.end();
        
        batch.begin();
        
		if(photo != null){
			batch.draw(photo, this.getX(), this.getY()+STATE_HEIGHT, PHOTO_WIDTH, PHOTO_HEIGHT, 0, 0,
					photo.getWidth(), photo.getHeight(), false, false);
		}
		if(font != null){
			String hp = HP_String + character.getHP() + " / " + character.getMaxHP();	
	        layout.setText(font, hp);
	        fontX = this.getX() + PHOTO_WIDTH + SPACING;
	        fontY = this.getY() + STATE_HEIGHT * 4  + (STATE_HEIGHT- SPACING + layout.height)/2;        
			font.draw(batch, layout,fontX,fontY);
			
			String mp = MP_String + character.getMP() + " / " + character.getMaxMP();
	        layout.setText(font, mp);
	        fontX = this.getX() + PHOTO_WIDTH + SPACING;
	        fontY = this.getY() + STATE_HEIGHT * 2  + (STATE_HEIGHT - SPACING + layout.height)/2; 
	        font.draw(batch, layout,fontX,fontY);
			
			String name = character.getName();
	        layout.setText(font, name);
	        fontX = this.getX() + (PHOTO_WIDTH - layout.width)/2;
	        fontY = this.getY() +(STATE_HEIGHT + layout.height)/2;
			font.draw(batch, layout,fontX,fontY);
			
			if(character.getStates() == null || character.getStates().size()<=0){
				String nostate = "empty";
		        layout.setText(font, nostate);
		        fontX = this.getX() + PHOTO_WIDTH + SPACING;
		        fontY = this.getY() +(STATE_HEIGHT + layout.height)/2;
				font.draw(batch, layout,fontX,fontY);
			}else{
				//other
			}
		}
	}*/
}
