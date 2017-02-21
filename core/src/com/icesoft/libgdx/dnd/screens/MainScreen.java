package com.icesoft.libgdx.dnd.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.I18NBundle;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.icesoft.libgdx.basic.view.EquipmentViewUtils;
import com.icesoft.libgdx.basic.view.LogViewUtils;
import com.icesoft.libgdx.basic.view.MakeViewUtils;
import com.icesoft.libgdx.basic.view.ObjectViewUtils;
import com.icesoft.libgdx.dnd.DNDGame;
import com.icesoft.libgdx.dnd.actor.SystemInfoActor;
import com.icesoft.libgdx.dnd.basic.enums.EquipmentClassEnum;
import com.icesoft.libgdx.dnd.basic.enums.ObjectClassEnum;
import com.icesoft.libgdx.dnd.basic.enums.OptionsMenuItems;
import com.icesoft.libgdx.dnd.basic.utils.CharacterUtils;
import com.icesoft.libgdx.dnd.basic.utils.EquipmentUtils;
import com.icesoft.libgdx.dnd.console.executor.MyCommandExecutor;
import com.icesoft.libgdx.dnd.equipment.interfaces.IEquipment;
import com.icesoft.libgdx.dnd.object.interfaces.IMaterial;
import com.icesoft.libgdx.dnd.object.interfaces.IObject;
import com.icesoft.libgdx.dnd.object.interfaces.ISupple;
import com.icesoft.libgdx.log.LogLevel;
import com.icesoft.libgdx.test.TestView;
import com.icesoft.libgdx.utils.Assets;
import com.icesoft.libgdx.utils.LanguageManager;
import com.icesoft.libgdx.utils.WindowUtils;
import com.strongjoshua.console.Console;
import com.strongjoshua.console.GUIConsole;

public class MainScreen implements Screen,InputProcessor{
	private String T = "MainScreen.class";
	
	private Console console;
	private MyCommandExecutor cExec;
	
	private DNDGame game;
	private ScalingViewport backStageViewport = new ScalingViewport(Scaling.fill,DNDGame.SCREEN_WIDTH, DNDGame.SCREEN_HEIGHT, new OrthographicCamera());
	private Stage backStage = new Stage(backStageViewport);
	private OrthographicCamera stageCamera = new OrthographicCamera();
	private ScalingViewport stageViewport = new ScalingViewport(DNDGame.SCALING, DNDGame.SCREEN_WIDTH, DNDGame.SCREEN_HEIGHT, stageCamera);
	private Stage stage = new Stage(stageViewport);	
	
	private I18NBundle bundle;
	private Skin skin;
	private Table title;
	private Table context;
	private Table control;
	
	private Window optionWindow,exitWindow;
	private boolean pause;	
	private SystemInfoActor sysInfo;
	private Table root;
	
	public MainScreen(DNDGame game){
		this.game = game;		
		bundle = LanguageManager.getI18NBundle(game.assets.manager);
		skin = game.assets.manager.get(Assets.SKIN, Skin.class);
		
		Image background = new Image(new Texture(Gdx.files.internal("images/background.jpg")));
		background.setBounds(0f, 0f, DNDGame.SCREEN_WIDTH, DNDGame.SCREEN_HEIGHT);
		backStage.addActor(background);
		
		sysInfo = new SystemInfoActor(skin.getFont("default-font"),SystemInfoActor.Level.GRAPHIC);
		pause = false;
		root = new Table();
		console = new GUIConsole(false);
		console.setSizePercent(100, 20);
		console.setPositionPercent(0, 80);
	}
	
	@Override
	public void show() {
		Gdx.app.debug(T, "show");
		stage.clear();	
		
		title 	= new Table();
		context = new Table();
		control = new Table();
		
		root.setFillParent(true);
		root.add(title).height(100f).expandX().fillX().row();;
		root.add(context).expand().fill().row();
		root.add(control).height(220f).expandX().fillX().row();;
		//root.debug();

		title.add(sysInfo).expand().fill();
		setEquipmentMountView();
		control.add(EquipmentViewUtils.getControlView(game, this, skin, bundle)).expand().fill();
		
		stage.addActor(root);
		
		Gdx.input.setCatchBackKey(true);
		Gdx.input.setCatchMenuKey(true);
		
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(stage);
		multiplexer.addProcessor(this);
		Gdx.input.setInputProcessor(multiplexer);
		//stage.setDebugParentUnderMouse(true);

		cExec = new MyCommandExecutor(game);
		console.setCommandExecutor(cExec);
		//console.setDisplayKeyID(Input.Keys.Z);	
		console.setVisible(false);
		console.resetInputProcessing();		
	}
	
	public Stage getStage(){
		return stage;
	}
	
	public void setObjectView() {
		context.clear();
		Table t = ObjectViewUtils.getObjectsView(game,this,skin,bundle);
		context.add(t).expand().fill();
	}

	public void setObjectView(ObjectClassEnum oce, IObject o) {
		switch(oce){
		case EQUIP:{
			if(o != null && o instanceof IEquipment){
				IEquipment e = (IEquipment) o;
				EquipmentClassEnum type = EquipmentUtils.getEquipmentType(game.character, e);
				if(type != null){
					IEquipment source = EquipmentUtils.getEquippedByType(type, game.character);
					context.clear();
					context.add(EquipmentViewUtils.getEquipmentCompView(game, this, skin, bundle, type, source, e)).expand().fill();
				}
			}			
		}
			break;
		case QUEST:
			break;
		case SUPPLE:{
			if(o != null && o instanceof ISupple){
				context.clear();
				context.add(ObjectViewUtils.getSuppleObjectsView(game,this,skin,bundle,(ISupple) o)).expand().fill();
			}
		}
			break;
		case MATERIAL:{
			if(o != null && o instanceof IMaterial){
				context.clear();
				context.add(ObjectViewUtils.getToolObjectsView(game,this,skin,bundle,(IMaterial) o)).expand().fill();
			}
		}			
			break;
		default:
			break;		
		}
	
	}
	public void setMakeView() {
		context.clear();
		context.add(MakeViewUtils.getMakeView(game,this,skin,bundle)).expand().fill();		
	}
	
	public void setEquipmentMountView(){
		context.clear();
		context.add(EquipmentViewUtils.getEquipmentMountView(game,this,skin,bundle)).expand().fill();	
	}
	public void setEquipmentSelectView(EquipmentClassEnum type) {
		context.clear();
		context.add(EquipmentViewUtils.getEquippedSelectView(game, this, skin, bundle, type)).expand().fill();	
	}

	public void setEquipmentUnmountView(EquipmentClassEnum type) {
		context.clear();
		context.add(EquipmentViewUtils.getUnmountEquipmentView(game,this,skin,bundle, type)).expand().fill();	
	}
	public void setEquipmentCompareView(EquipmentClassEnum type, IEquipment s, IEquipment t) {
		context.clear();
		context.add(EquipmentViewUtils.getEquipmentCompView(game, this, skin, bundle, type, s, t)).expand().fill();	
	}
	public void setMapView() {
		context.clear();
		Label l = new Label("aa",skin);
		context.add(l).expand().fill();	
	}
	public void setQuestView() {
		context.clear();
		context.add(new Label("Quest",skin)).expand().fill();	
	}
	public void setStatusView() {
		context.clear();
		context.add(EquipmentViewUtils.getCharacterView(game, this, skin, bundle)).expand().fill();	
	}

	public void setSkillView() {
		context.clear();
		context.add(EquipmentViewUtils.getSkillView(game, this, skin, bundle)).expand().fill();	
	}	


	float stateTime = 0;
	@Override
	public void render(float delta) {		
		if(!pause){
			Gdx.gl.glClearColor(0, 0, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			
			CharacterUtils.updateExtra(game.character, game.extra);
			update();
			
			backStage.act(delta);
			backStage.getViewport().apply();
			backStage.draw();
			
			stage.act(delta);
			stage.getViewport().apply();
			stage.draw();
			console.draw();
		}
	}

	private void update() {
				
	}

	@Override
	public void resize(int width, int height) {
		Gdx.app.debug(T, "resize");	
		backStageViewport.update(width, height);
		stageViewport.update(width, height);
	}
	@Override
	public void pause() {
		Gdx.app.debug(T, "pause");
		pause = true;
	}
	@Override
	public void resume() {
		Gdx.app.debug(T, "resume");
		pause = false;
	}
	@Override
	public void hide() {
		Gdx.app.debug(T, "hide");
	}
	@Override
	public void dispose() {
		Gdx.app.debug(T, "dispose");
		console.dispose();
		backStage.dispose();
		stage.dispose();	
	}

	public void showExitWindow(){
		if(exitWindow == null){
			exitWindow = WindowUtils.getExitWindow(this, bundle, skin, DNDGame.SCREEN_WIDTH, DNDGame.SCREEN_HEIGHT);
		}
		stage.addActor(exitWindow);
	}
	public void showOptionWindow(){
		if(optionWindow == null){
			optionWindow = WindowUtils.getOptionWindow(this,  bundle, skin, DNDGame.SCREEN_WIDTH, DNDGame.SCREEN_HEIGHT);
		}
		stage.addActor(optionWindow);
	}
	public void hideExitWindow(){
		if(exitWindow != null){
			exitWindow.remove();
		}
	}
	public void hideOptionWindow(){
		if(optionWindow != null){
			optionWindow.remove();
		}
	}

	public void options(OptionsMenuItems o) {
		switch(o){
		case EXIT:	{hideOptionWindow();showExitWindow();}	break;
		case LOAD:	game.loadGame();						break;
		case NEW:	game.newGame();							break;
		case RESUME:hideOptionWindow();						break;
		case SAVE:	game.saveGame();						break;
		case DEBUG:	console.setVisible(true);			break;
		default:	hideOptionWindow();						break;		
		}
	}
	public void exitGame() {
		game.exitGame();
	}
	@Override
	public boolean keyDown(int keycode) {
		return true;
	}
	@Override
	public boolean keyUp(int keycode) {
		switch(keycode){
			case Keys.BACK:		showExitWindow();	break;
			case Keys.B:		showExitWindow();	break;
			case Keys.ESCAPE:	showExitWindow();	break;
			case Keys.M:		showOptionWindow();	break;
			case Keys.MENU:		showOptionWindow();	break;
			case Keys.T:		test();				break;
			case Keys.P:		characterInfo();	break;
		}
		return true;
	}
	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return true;
	}
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	public void characterInfo(){
		System.out.println(game.character.toString());
	}
	public void test(){
		Table t = TestView.getClassView(game, this, skin, bundle);
		context.clear();
		context.add(t).expand().fill();		
	}
	
	public void setLogView(){
		context.clear();
		context.add(LogViewUtils.getLogView(game, this, skin, bundle)).expand().fill();
	}
}
