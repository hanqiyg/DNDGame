package com.icesoft.libgdx.utils;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.I18NBundle;
import com.icesoft.libgdx.dnd.basic.enums.OptionsMenuItems;
import com.icesoft.libgdx.dnd.screens.MainScreen;

public class WindowUtils{
	public static final float pad = 10f;
	public static Window getOptionWindow(final MainScreen home, I18NBundle bundle,Skin skin,float ScreenWidth,float ScreenHeight) {
		final Window win = new Window(bundle.get("OPTIONS"),skin);
		win.setModal(true);
		win.setMovable(false);

		float RowWidth = ScreenWidth * 0.8f;
		float RowHeight = 50f;
		for(final OptionsMenuItems o : OptionsMenuItems.values()){
			TextButton b = new TextButton(bundle.get(o.toString()),skin);
			b.addListener(new ClickListener(){
				@Override
				public void clicked(InputEvent event, float x, float y) {
					home.options(o);
				}				
			});
			win.add(b).size(RowWidth,RowHeight).pad(pad);
			win.row();
		}
		win.addListener(new InputListener(){
			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				return true;
			}
			@Override
			public boolean keyUp(InputEvent event, int keycode) {
				switch(keycode){
					case Keys.BACK:		home.hideOptionWindow();break;
					case Keys.B:		home.hideOptionWindow();break;
					case Keys.ESCAPE:	home.hideOptionWindow();break;
					default:return false;
				}
				return true;
			}			
		});
		win.pack();
		float x = ScreenWidth / 2 - win.getWidth() /2;
		float y = ScreenHeight /2 - win.getHeight() /2;
		
		win.setBounds(x, y, win.getWidth(), win.getHeight());
		return win;
	}
	public static Window getExitWindow(final MainScreen home, I18NBundle bundle,Skin skin,float ScreenWidth,float ScreenHeight) {
		final Window win = new Window(bundle.get("EXIT"),skin);
		win.setModal(true);
		win.setMovable(false);

		float RowWidth = ScreenWidth * 0.8f;
		float RowHeight = 50f;
		TextButton btn_exit = new TextButton(bundle.get("EXIT"),skin);
		btn_exit.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				home.exitGame();
			}				
		});
		TextButton btn_cancel = new TextButton(bundle.get("CANCEL"),skin);
		btn_cancel.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				win.remove();
			}				
		});
		win.add(bundle.get("ExitContext")).colspan(2);
		win.row();
		win.add(btn_exit).size(RowWidth/2 - pad,RowHeight).pad(pad);
		win.add(btn_cancel).size(RowWidth/2 - pad,RowHeight).pad(pad);
		win.row();
		win.addListener(new InputListener(){
			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				return true;
			}
			@Override
			public boolean keyUp(InputEvent event, int keycode) {
				switch(keycode){
					case Keys.BACK:		home.hideExitWindow();break;
					case Keys.B:		home.hideExitWindow();break;
					case Keys.ESCAPE:	home.hideExitWindow();break;
					default:return false;
				}
				return true;
			}			
		});
		win.pack();
		float x = ScreenWidth / 2 - win.getWidth() /2;
		float y = ScreenHeight /2 - win.getHeight() /2;
		win.setBounds(x, y, win.getWidth(), win.getHeight());

		return win;
	}
}
