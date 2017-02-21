package com.icesoft.libgdx.dnd.actor;

import java.util.Date;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.icesoft.libgdx.dnd.listener.CoolDownClickListener;

public class CooldownTextButton extends Actor{
	private static final Color DEFAULT_BORDER_COLOR = Color.BLACK;
	private static final Color DEFAULT_FILLED_COLOR = Color.GREEN;
	
	private float stateTime = 0f;
	private float cooldownTime = 0f;
	private String text;
	private float percents;
	private BitmapFont font;
	private GlyphLayout layout;
	private Color border;
	private Color filled;
	
	private CoolDownClickListener listener;	
	private ShapeRenderer shapeRenderer;
	private long touchTime = 0;
		
	public CooldownTextButton(String text,BitmapFont font,float cooldownTime,Color border,Color filled){
		this.text = text;
		this.font = font;
		this.cooldownTime = cooldownTime;
		if(border == null) border = DEFAULT_BORDER_COLOR;
		this.border = border;
		if(filled == null) filled = DEFAULT_FILLED_COLOR;
		this.filled = filled;
		layout = new GlyphLayout();	
		shapeRenderer = new ShapeRenderer();
		this.addListener(new ClickListener(){			
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				touchTime = (new Date()).getTime();
				return super.touchDown(event, x, y, pointer, button);
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				if((new Date()).getTime() - touchTime > 1000){
					listener.longTouch(event, x, y);
				}else if(percents >=1){
					listener.clicked(event, x, y);
					reset();
				}				
			}		
		});
	}
	
	public void setCooldownTime(float cooldownTime){
		this.cooldownTime = cooldownTime;		
	}
	
	public void update(float t){
		this.stateTime += t;
		float p = stateTime / cooldownTime;
		percents = MathUtils.clamp(p, 0, 1);

	}	
	
	public void reset(){		
		this.stateTime = 0;	
	}
	
	public boolean isCool(){
		return percents>=1;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		update(parentAlpha);
		batch.end();
	    shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
	    shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
	    shapeRenderer.translate(this.getX(), this.getY(), 0);
	    
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(filled.r,filled.g,filled.b,filled.a);
        shapeRenderer.rect(0, 0, this.getWidth() * percents, this.getHeight());
        shapeRenderer.end();
        
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(border.r,border.g,border.b,border.a);
        shapeRenderer.rect(0, 0, this.getWidth(), this.getHeight());
        shapeRenderer.end();

        if(font!=null){
	        layout.setText(font, text);
	        float fontX = this.getX() + (this.getWidth() - layout.width)/2;
	        float fontY = this.getY() + (this.getHeight() + layout.height)/2;
	        
	        batch.begin();
	        if(layout.width > this.getWidth()){
	        	font.draw(batch, text, this.getX(), fontY, 0, text.length(), this.getWidth(), Align.center, true, "");
	        }else{		        
		        font.draw(batch, layout, fontX,fontY);
	        }
	        batch.end();
        }
        batch.begin();
	}

	public CoolDownClickListener getListener() {
		return listener;
	}

	public void setListener(CoolDownClickListener listener) {
		this.listener = listener;
	}	
}
