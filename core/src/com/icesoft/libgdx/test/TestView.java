package com.icesoft.libgdx.test;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.I18NBundle;
import com.icesoft.libgdx.dnd.DNDGame;
import com.icesoft.libgdx.dnd.basic.enums.EquipmentClassEnum;
import com.icesoft.libgdx.dnd.basic.utils.EquipmentUtils;
import com.icesoft.libgdx.dnd.equipment.interfaces.IEquipment;
import com.icesoft.libgdx.dnd.screens.MainScreen;

public class TestView {
	public static Table getClassView(final DNDGame game, MainScreen mainScreen, final Skin skin, I18NBundle bundle) {
		final Table view = new Table();
		
		TextButton btn_head = new TextButton("Head", skin);
		TextButton btn_arm  = new TextButton("Arm", skin);
		view.debug();
		
		final Table sub = new Table();
		
		final ScrollPane scroll = new ScrollPane(sub,skin);
		scroll.setFadeScrollBars(false);
		scroll.setScrollbarsOnTop(false);
		scroll.setOverscroll(false, false);
		
		btn_head.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				sub.clear();
				for(IEquipment e : EquipmentUtils.getEquipmentFromObjectsByType(EquipmentClassEnum.HEAD, game.character)){
					System.out.println(e.getName());
					sub.add(new TextButton(e.getName(),skin)).expandX().fill().pad(10f).row();
					sub.row();
				}
			}			
		});
		btn_arm.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				sub.clear();
				for(IEquipment e : EquipmentUtils.getEquipmentFromObjectsByType(EquipmentClassEnum.ARM, game.character)){
					System.out.println(e.getName());	
					sub.add(new TextButton(e.getName(),skin)).expandX().fill().pad(10f).row();
					sub.row();
				}
			}		
		});	

		scroll.debug();
		view.add(scroll).fill().expand().colspan(2).row();
		
		view.add(btn_head)	.pad(10f).expandX().fill();
		view.add(btn_arm)	.pad(10f).expandX().fill();
		view.row();
		
		view.pack();
		return view;
	}

}
