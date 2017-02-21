package com.icesoft.libgdx.basic.view;

import java.util.List;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.I18NBundle;
import com.icesoft.libgdx.dnd.DNDGame;
import com.icesoft.libgdx.dnd.screens.MainScreen;

public class LogViewUtils {
	public static Table getLogView(final DNDGame game, final MainScreen mainScreen, Skin skin, I18NBundle bundle) {
		Table view = new Table();
		Table logTable = new Table();
		logTable.bottom();
		List<String> lines = game.cons.getLines();
		for(String l : lines){
			Label label = new Label(l,skin);
			logTable.add(label).expandX().fillX();
			logTable.row();
		}
		ScrollPane logPanel = new ScrollPane(logTable,skin);
		logPanel.layout();
		logPanel.setScrollPercentY(1);
		view.add(logPanel).expand().fill().align(Align.top).padTop(DNDGame.PAD).padBottom(DNDGame.PAD);		
		return view;
	}
}
