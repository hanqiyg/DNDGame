package com.icesoft.libgdx.dnd.actor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.icesoft.libgdx.utils.ColorUtils;

public class ProgressbarActor extends Actor {
	private static final String T = "ProgressbarActor.class";
	private Color filled = Color.GREEN;
	private Color border = Color.BLACK;
	private int full;
	private int current;
	private float percent;
	
	private Color startColor = Color.RED;
	private Color endColor = Color.GREEN;
	
	private boolean revese = false;
	
	private BitmapFont font;
	private GlyphLayout layout;
	float stateTime = 0;
	
	private ShapeRenderer shapeRenderer = new ShapeRenderer();
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.end();
	    shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
	    shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
	    shapeRenderer.translate(this.getX(), this.getY(), 0);	    

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(filled.r,filled.g,filled.b,filled.a);
        shapeRenderer.rect(0, 0, this.getWidth() * percent,this.getHeight());
        shapeRenderer.end();
        
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(border.r,border.g,border.b,border.a);
        shapeRenderer.rect(0, 0, this.getWidth(), this.getHeight());
        shapeRenderer.end();
        batch.begin();
        if(font != null){
        	drawFont(batch,parentAlpha);
        }
	}

	public void drawFont(Batch batch, float parentAlpha){
    	if(layout == null){
    		layout = new GlyphLayout();
    	}
    	String str = String.valueOf(current) + " / " + String.valueOf(full);
    	layout.setText(font, str);
    	float x = this.getX() + this.getWidth()/2 - layout.width/2;
    	float y = this.getY() + layout.height/2 + this.getHeight()/2;
    	font.draw(batch, str, x, y);
	}
	public void setRevese(boolean revese){
		this.revese = revese;
	}

	public void setFont(BitmapFont font){
		this.font = font;
	}

	public void setCurrent(int c){
		if(c>=0){
			this.current = c;
		}else{
			this.current = 0;
		}
		setPercent();
		setColor();
	}
	public int getCurrent(){
		return this.current;
	}
	public void setFull(int f){
		if(f>0){
			this.full = f;
		}else{
			this.full = 0;
		}
		setPercent();
		setColor();
	}
	public int getFull(){
		return this.full;
	}

	private void setPercent() {
		if(this.current<=0||this.full<=0){
			percent = 0;
			return;
		}
		if(this.current > this.full){
			percent = 1;
			return;
		}
		percent = (float) this.current / (float) this.full;
		return;
	}
	
	public float getPercent(){
		return percent;
	}	

	private void setColor(){
		if(startColor.equals(endColor)){
			filled = startColor;
			return;
		}
		if(revese){
			filled = ColorUtils.getBlenderColor(endColor,startColor,percent,false);
		}else{
			filled = ColorUtils.getBlenderColor(startColor,endColor,percent,false);
		}
	}
	
	public Color getStartColor() {
		return startColor;
	}

	public void setStartColor(Color startColor) {
		this.startColor = startColor;
	}
	
	public Color getEndColor() {
		return endColor;
	}

	public void setEndColor(Color endColor) {
		this.endColor = endColor;
	}
}
