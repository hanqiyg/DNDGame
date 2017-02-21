package com.icesoft.libgdx.utils;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class Utils {
	public static void displayBounds(Actor a){
		System.out.println("X:" + a.getX() + "Y:" + a.getY() + "W:" + a.getWidth() + "H:" + a.getHeight());
	}
}
