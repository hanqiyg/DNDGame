package com.icesoft.libgdx.utils;

import com.badlogic.gdx.graphics.Color;

public class ColorUtils {
	public static Color getBlenderColor(Color start, Color end, float percent,boolean isTransparent){
		float r,g,b,a;
		if(start.r < end.r){
			r = start.r + (end.r - start.r) * percent;
		}else{
			r = start.r - (start.r - end.r) * percent;
		}
		if(start.g < end.g){
			g = start.g + (end.g - start.g) * percent;
		}else{
			g = start.g - (start.g - end.g) * percent;
		}
		if(start.b < end.b){
			b = start.b + (end.b - start.b) * percent;
		}else{
			b = start.b - (start.b - end.b) * percent;
		}
		if(isTransparent){
			if(start.a < end.a){
				a = start.a + (end.a - start.a) * percent;
			}else{
				a = start.a - (start.a - end.a) * percent;
			}
		}else{
			a = end.a;
		}
		return new Color(r,g,b,a);
	}
}
