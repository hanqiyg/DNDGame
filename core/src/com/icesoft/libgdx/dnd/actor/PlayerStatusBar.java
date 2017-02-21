package com.icesoft.libgdx.dnd.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Array;
import com.icesoft.libgdx.dnd.DNDGame;

public class PlayerStatusBar extends Group {
	private static final String T = "PlayerStatusBar.class";
	private String name;
	private String photo;
	private int maxHP;
	private int HP;
	private int maxMP;
	private int MP;
	
	private Label nameLabel;
	private Label HPLabel;
	private Label MPLabel;
	private Image photoImage;
	private LifeBar HPBar;
	private LifeBar MPBar;
	private Array<Image> statusImages;
		
	public PlayerStatusBar(String name,String photo,int maxHP,int HP,int maxMP,int MP){
		this.name = name;
		this.photo = photo;
		this.maxHP = maxHP;
		this.HP = HP;
		this.maxMP = maxMP;
		this.MP = MP;
		init();
	}
	public void init(){
		nameLabel = new Label(name, DNDGame.SKIN);
		nameLabel.setBounds(200f, 100f, 200f, 100f);
		this.addActor(nameLabel);
		
		photoImage = new Image(new Texture(Gdx.files.internal(photo)));
		photoImage.setBounds(0f, 0f, 200f, 200f);
		this.addActor(photoImage);
		
		NinePatch bg = new NinePatch(new Texture(Gdx.files.internal("images/emptybar.png")),5,5,1,1);
		NinePatch pr = new NinePatch(new Texture(Gdx.files.internal("images/fillbar.png")),5,5,1,1);
		HPBar = new LifeBar(pr, bg);
		HPBar.setFull(maxHP);
		HPBar.setNow(HP);
		HPBar.setBounds(200f, 50f, 400f, 20f);
		this.addActor(HPBar);	
		HPLabel = new Label("HP:"+HP+"/"+maxHP,DNDGame.SKIN.get("very-small", LabelStyle.class));
		HPLabel.setBounds(200f, 75f, 400f, 25f);
		this.addActor(HPLabel);
		
		MPBar = new LifeBar(pr, bg);
		MPBar.setFull(maxMP);
		MPBar.setNow(MP);
		MPBar.setBounds(200f, 0f, 400f, 20f);
		this.addActor(MPBar);		
		MPLabel = new Label("MP:"+MP+"/"+maxMP,DNDGame.SKIN.get("very-small", LabelStyle.class));
		MPLabel.setBounds(200f, 25f, 400f, 25f);
		this.addActor(MPLabel);
		Gdx.app.debug(T, "size:"+this.getChildren().size);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		nameLabel.setText(this.name);
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
		photoImage = new Image(new Texture(Gdx.files.internal(this.photo)));
	}

	public int getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
		HPLabel.setText("HP:"+this.HP+"/"+this.maxHP);
	}

	public int getHP() {
		return HP;
	}

	public void setHP(int hP) {
		this.HP = hP;
		HPLabel.setText("HP:"+this.HP+"/"+this.maxHP);
	}

	public int getMaxMP() {
		return maxMP;
	}

	public void setMaxMP(int maxMP) {
		this.maxMP = maxMP;
		MPLabel.setText("MP:"+this.MP+"/"+this.maxMP);
	}

	public int getMP() {
		return MP;
	}

	public void setMP(int mP) {
		this.MP = mP;
		MPLabel.setText("MP:"+this.MP+"/"+this.maxMP);
	}	
}
