package com.icesoft.libgdx.basic.view;

import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.I18NBundle;
import com.icesoft.libgdx.dnd.DNDGame;
import com.icesoft.libgdx.dnd.basic.enums.ObjectClassEnum;
import com.icesoft.libgdx.dnd.object.interfaces.IMaterial;
import com.icesoft.libgdx.dnd.object.interfaces.IObject;
import com.icesoft.libgdx.dnd.object.interfaces.ISupple;
import com.icesoft.libgdx.dnd.screens.MainScreen;
import com.icesoft.libgdx.log.LogLevel;

public class ObjectViewUtils {
	public static Actor getSuppleObjectsView(final DNDGame game, final MainScreen mainScreen, Skin skin, I18NBundle bundle, final ISupple s) {
		Table view = new Table();
		Label name = new Label(s.getName(),skin);
		name.setAlignment(Align.center);
		name.setColor(getUsableLevel(s));
		Label usage = new Label(s.getUsage(),skin);
		view.add(name).expandX().fillX();
		view.row();
		view.add(usage).expand().fill();
		view.row();
		TextButton confirm 	= new TextButton(s.getCommandName(),skin);
		TextButton cancel 	= new TextButton("Cancel",skin);
		confirm.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				s.use(game);
				game.addLog(mainScreen.getStage(), LogLevel.INFO, "Use " + s.getName());
				mainScreen.setObjectView();
			}
		});
		cancel.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				mainScreen.setObjectView();
			}
		});
		Table control = new Table();
		control.add(confirm).expandX().fillX().pad(DNDGame.PAD);
		control.add(cancel)	.expandX().fillX().pad(DNDGame.PAD);
		control.row();
		view.add(control).expandX().fillX();
		view.row();
		return view;
	}
	
	public static Table getObjectsView(final DNDGame game, final MainScreen mainScreen, final Skin skin, final I18NBundle bundle) {
		TextButtonStyle tbs = new TextButtonStyle();
		tbs.font		= skin.getFont("default-font");
		tbs.up 			= skin.getDrawable("button01");
		tbs.down 		= skin.getDrawable("button02");
		tbs.checked		= skin.getDrawable("button02");
		tbs.fontColor 	= Color.WHITE;
		
		ButtonGroup<TextButton> bg = new ButtonGroup<TextButton>();
		bg.setMaxCheckCount(1);
		bg.setMinCheckCount(1);
		bg.setUncheckLast(true);
		
		Table view = new Table();
		final Table objTable = new Table();
		Table typeTable = new Table();
		ScrollPane sp_types = new ScrollPane(typeTable,skin);
		sp_types.setForceScroll(true, false);
		sp_types.setupFadeScrollBars(0, 0);
		ScrollPane sp_objects = new ScrollPane(objTable,skin);
		boolean first = true;
		for(final ObjectClassEnum oce : ObjectClassEnum.values()){
			List<IObject> l = new ArrayList<IObject>();
			for(IObject o : game.character.getObjects()){
				if(oce.getObjectClass().isInstance(o)){
					l.add(o);
				}
			}
			if(first){
				changeTypedObjects(game, mainScreen, skin, bundle, objTable,l, oce);
			}
			first = false;
			String title = bundle.get(oce.name()) + "\n(" + l.size() + ")"; 
			final TextButton b = new TextButton(title,tbs);
			b.setUserObject(l);
			b.addListener(new ClickListener(){
				@SuppressWarnings("unchecked")
				@Override
				public void clicked(InputEvent event, float x, float y) {
					changeTypedObjects(game, mainScreen, skin, bundle, objTable, (List<IObject>) b.getUserObject(), oce);					
				}				
			});
			bg.add(b);
			typeTable.add(b).uniform().fillX().padLeft(DNDGame.PAD);
		}
		view.add(sp_types).expandX().fillX().padTop(DNDGame.PAD);
		view.row();	
		view.add(sp_objects).expand().fill().padTop(DNDGame.PAD).padBottom(DNDGame.PAD);
		view.row();
		return view;
	}
	
	public static void changeTypedObjects(DNDGame game, final MainScreen mainScreen, final Skin skin, final I18NBundle bundle,Table objTable,List<IObject> objects,final ObjectClassEnum key) {
		objTable.clear();
		for(IObject o : objects){
			String title = bundle.get(o.getName()) + (o.getCount()>1?"(" + String.valueOf(o.getCount()) + ")":"");
			final TextButton objButton = new TextButton(title,skin);
			objButton.setUserObject(o);
			objButton.addListener(new ClickListener(){
				@Override
				public void clicked(InputEvent event, float x, float y) {
					mainScreen.setObjectView(key,(IObject)objButton.getUserObject());
				}							
			});
			objTable.add(objButton).expandX().fillX().pad(DNDGame.PAD);
			objTable.row();
		}
	}	

	private static Color getUsableLevel(ISupple e) {
		return Color.ROYAL;
	}

	public static Actor getToolObjectsView(DNDGame game, MainScreen mainScreen, Skin skin, I18NBundle bundle,
			IMaterial o) {
		// TODO Auto-generated method stub
		return null;
	}
}
