package com.icesoft.libgdx.dnd.actor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.icesoft.libgdx.utils.ColorUtils;
import com.icesoft.libgdx.utils.MemoryUtils;

public class SystemInfoActor extends Actor {
	private BitmapFont font;
	
	private Color border = Color.BLACK;	
	private Color startColor = Color.GREEN;
	private Color endColor = Color.RED;
	
	public static enum Level{
		STANDARD,GRAPHIC,BASIC
	}
	
	private Level lv = Level.BASIC;
	
	private ShapeRenderer shapeRenderer;
	private GlyphLayout layout;
	
	public SystemInfoActor(){	
	}
	public SystemInfoActor(BitmapFont font){
		this.font = font;
	}
	public SystemInfoActor(BitmapFont font,Level lv){
		this.font = font;
		this.lv = lv;
	}
	
	public SystemInfoActor(BitmapFont font,Level lv,Color start,Color end,Color border){
		this.font = font;
		this.lv = lv;
		this.startColor = start;
		this.endColor = end;
		this.border = border;
	}
	
	public SystemInfoActor(Level lv) {
		this.lv = lv;
	}
	@Override
	public void draw(Batch batch, float parentAlpha) {
		if(font == null){
			font = new BitmapFont();
		}
		switch(lv){
			case GRAPHIC:	drawGraph(batch,parentAlpha);	break;
			case BASIC:		drawBasic(batch,parentAlpha);	break;
			case STANDARD:	drawStandard(batch,parentAlpha);break;
			default:		drawBasic(batch,parentAlpha);	break;		
		}
	}
	private void drawStandard(Batch batch, float parentAlpha) {
		String format = "#.00";
		long free 	= MemoryUtils.getFreeMemory();
		long max   	= MemoryUtils.getMaxMemory();
		long total	= MemoryUtils.getTotalMemory();
		String s = 	"Used: [ " 	+ MemoryUtils.getMemoryUnitString((total - free),format) 	+ " ] " +
					"Total: [ " + MemoryUtils.getMemoryUnitString(total,format) 			+ " ] " +
					"Max: [ " 	+ MemoryUtils.getMemoryUnitString(max,format)				+ " ]" ;
		font.draw(batch, s, this.getX(), this.getY());
	}
	public void drawGraph(Batch batch, float parentAlpha){	
		long free 	= MemoryUtils.getFreeMemory();
		long max   	= MemoryUtils.getMaxMemory();
		long total	= MemoryUtils.getTotalMemory();
		float p;
		if(total<=0||(total - free) <=0){
			p = 0;
		}
		p = (float) (total - free) / total;
		Color filled;
		if(startColor.equals(endColor)){
			filled = startColor;			
		}else{
			filled = ColorUtils.getBlenderColor(startColor,endColor,p,false);		
		}		
		batch.end();
		if(shapeRenderer == null){shapeRenderer = new ShapeRenderer();}
	    shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
	    shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
	    shapeRenderer.translate(this.getX(), this.getY(), 0);
	    
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(filled.r,filled.g,filled.b,filled.a);
        shapeRenderer.rect(0, 0, this.getWidth() * p,this.getHeight());
        shapeRenderer.end();
        
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(border.r,border.g,border.b,border.a);
        shapeRenderer.rect(0, 0, this.getWidth(), this.getHeight());
        shapeRenderer.end();
        batch.begin();
        
		String format = "#.00";
		String s = 	MemoryUtils.getMemoryUnitString((total - free),format) + " / " +
					MemoryUtils.getMemoryUnitString(total,format) + 
					" [ "  + MemoryUtils.getMemoryUnitString(max,format) + " ]";
		
    	if(layout == null){
    		layout = new GlyphLayout();
    	}
    	
    	layout.setText(font, s);
    	float x = this.getX() + this.getWidth()/2 - layout.width/2;
    	float y = this.getY() + layout.height/2 + this.getHeight()/2;
    	font.draw(batch, s, x, y);
	}

	public void drawBasic(Batch batch, float parentAlpha){
		String format = "#.0";
		long free 	= MemoryUtils.getFreeMemory();
		long total	= MemoryUtils.getTotalMemory();
		String s = 	"U: [ " + MemoryUtils.getMemoryUnitString((total - free),format) + " ]";
		font.draw(batch, s, this.getX(), this.getY());
	}
}
