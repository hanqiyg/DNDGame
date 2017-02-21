package com.icesoft.libgdx.dnd.actor;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class PentagonActor extends Actor{
	private List<PentagonData> data;
	Vector2 circleCenter;
	int topSize;
	Vector2[] tops;
	float unit;
	BitmapFont font = new BitmapFont();
	private ShapeRenderer shapeRenderer = new ShapeRenderer();
	public PentagonActor(List<PentagonData> data){
		this.data = data;
		Gdx.app.debug(T, data.size() + "");
		font.getData().setScale(3);
	}
	GlyphLayout layout;
	@Override
	public void setBounds(float x, float y, float width, float height) {
		circleCenter = new Vector2((this.getX()/2+width/2),(this.getY()/2+width/2));
		Gdx.app.debug(T, "center:" + circleCenter.toString());
		topSize = data.size();
		tops = new Vector2[topSize];
		if(height > width){
			unit = width/2;
		}else{
			unit = height/2;
		}
		float unitLength = data.get(0).length;
		for(int i=0;i<tops.length;i++){
			float deg = 360f / topSize * i +90;
			Gdx.app.debug(T, "deg:" + deg);
			float length = unit * (data.get(i).length / unitLength);
			tops[i] = new Vector2(circleCenter.x + length * MathUtils.cosDeg(deg),circleCenter.y + length * MathUtils.sinDeg(deg));
			Gdx.app.debug(T, "i:" + i +"vector2:" + tops[i]==null?"null":tops[i].x + "," + tops[i].y); 
		}
	}

	private static final String T = "PentagonActor.class";
	@Override
	public void draw(Batch batch, float parentAlpha) {        
    	if(tops != null ){
    		
        for(int i=0;i<tops.length;i++){
        	batch.end();
    	    shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
    	    shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
    	    shapeRenderer.translate(this.getX(), this.getY(), 0);
    	    
        	shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
	        shapeRenderer.setColor(Color.GOLD);
	        if(i == 0){
	        	Gdx.app.debug(T, "line" + circleCenter==null?"null":circleCenter.toString() +"," + tops[0]==null?"null":tops[0].toString());
	        	shapeRenderer.line(circleCenter, tops[0]);
	        }else if(i == (tops.length-1)){
	        	shapeRenderer.line(circleCenter, tops[i]);
	        	shapeRenderer.line(tops[i], tops[i-1]);
	        	shapeRenderer.line(tops[i], tops[0]);
	        }else{	        	
	        	shapeRenderer.line(circleCenter, tops[i]);
	        	shapeRenderer.line(tops[i], tops[i-1]);
	        }
	        shapeRenderer.end();
	        batch.begin();
	        if(layout == null){
	    		layout = new GlyphLayout();
	    	}
	    	font.draw(batch, String.valueOf(i), tops[i].x, tops[i].y);
	        
        }
		}		
		
		
	}
	public static class PentagonData{
		public String name;
		public int length;
		public PentagonData(String name,int length){
			this.name = name;
			this.length = length;
		}
	}
}
