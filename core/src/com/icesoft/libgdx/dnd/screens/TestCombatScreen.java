package com.icesoft.libgdx.dnd.screens;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.icesoft.libgdx.dnd.DNDGame;
import com.icesoft.libgdx.dnd.actor.CoolDownButton;
import com.icesoft.libgdx.dnd.actor.LifeBar;

public class TestCombatScreen implements Screen{
	private static final String T = "TestCombatScreen.class";
	
	private Stage stage;
	LifeBar lb;
	float stateTime = 0;
	TextField tf;
	
	int full = 100,now = 100;
	Random r = new Random();
	Label hp;
	CoolDownButton cdb;
	Label attackLabel;
	
	@Override
	public void show() {
		NinePatch bg = new NinePatch(new Texture(Gdx.files.internal("images/emptybar.png")),5,5,1,1);
		NinePatch pr = new NinePatch(new Texture(Gdx.files.internal("images/fillbar.png")),5,5,1,1);
		lb = new LifeBar(pr,bg);
		lb.setBounds(150, 400f, 310, 20);
		lb.setFull(100);
		
		hp = new Label("HP:"+now+"/"+full,DNDGame.SKIN.get("very-small", LabelStyle.class));
		hp.setBounds(0, 400f, 80, 20);

/*		tf = new TextField("100", DNDGame.SKIN);
		tf.setBounds(0f, 200f, 400f, 100f);
		TextButton tb = new TextButton("OK",DNDGame.SKIN);
		Gdx.app.debug(T, "" + tb.getStyle().fontColor);
		tb.setBounds(500f, 200f, 100f, 100f);
		tb.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				int now = Integer.valueOf(tf.getText());
				lb.setNow(now);
			}			
		});*/
		
		
		TextureAtlas iconBlack = new TextureAtlas(Gdx.files.internal("atlas/IconBlack.txt"));
		TextureRegion blacked = iconBlack.findRegion("DISBTN3M3");
		
		TextureAtlas iconColor = new TextureAtlas(Gdx.files.internal("atlas/IconColor.txt"));
		TextureRegion colored = iconColor.findRegion("BTN3M3");

		cdb = new CoolDownButton(colored,blacked,3);
		cdb.setBounds(100f, 100f, 64f, 64f);
		cdb.setClickListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.debug(T, event + "["+x+","+y+"]");
				cdb.cooldown();
				int attack = r.nextInt(10);
				if(now>attack){
					now = now - attack;
					attackLabel = new Label("-" + attack,DNDGame.SKIN.get("very-small", LabelStyle.class));
					attackLabel.setBounds(250, 400f, 100, 50);
					stage.addActor(attackLabel);
					MoveByAction action1 = Actions.moveBy(0, -100, 1);
			        RunnableAction runnable = Actions.run(new Runnable() {
			            @Override
			            public void run() {
			                Gdx.app.debug(T, "runnable");
			                if(attackLabel == null){
			                	Gdx.app.debug(T, "attackLabel null");
			                }
			                attackLabel.remove();
			                attackLabel = null;
			            }
			        });
					SequenceAction sequenceAction = Actions.sequence(action1, runnable);
					attackLabel.addAction(sequenceAction);
				}else{
					now = 0;
				}
				hp.setText("HP:"+now+"/"+full);
				lb.setNow(now);
				Gdx.app.debug(T, "OnClick.");
			}			
		});
		
		
		stage = new Stage();
		//stage.addActor(tf);
		//stage.addActor(tb);
		stage.addActor(hp);
		stage.addActor(lb);
		stage.addActor(cdb);
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		cdb.setTime(Gdx.graphics.getDeltaTime());
		stage.act();
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
