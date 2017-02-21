package com.icesoft.libgdx.basic.view;

import java.util.List;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.I18NBundle;
import com.icesoft.libgdx.dnd.DNDGame;
import com.icesoft.libgdx.dnd.actor.ProgressbarActor;
import com.icesoft.libgdx.dnd.basic.enums.AttributeEnum;
import com.icesoft.libgdx.dnd.basic.enums.EquipmentClassEnum;
import com.icesoft.libgdx.dnd.basic.enums.PropertyEnum;
import com.icesoft.libgdx.dnd.basic.enums.SkillEnum;
import com.icesoft.libgdx.dnd.basic.utils.CharacterUtils;
import com.icesoft.libgdx.dnd.basic.utils.EquipmentUtils;
import com.icesoft.libgdx.dnd.basic.utils.SkillUtils;
import com.icesoft.libgdx.dnd.equipment.interfaces.IEquipment;
import com.icesoft.libgdx.dnd.screens.MainScreen;
import com.icesoft.libgdx.dnd.skill.interfaces.ISkill;
import com.icesoft.libgdx.utils.Assets;

public class EquipmentViewUtils {
	public static Table getEquipmentMountView(DNDGame game, MainScreen screen,Skin skin, I18NBundle bundle){
		Table view = new Table();
		addEquippedTableRow(game,screen,skin,bundle,view,EquipmentClassEnum.HEAD,		game.character.getHead());
		addEquippedTableRow(game,screen,skin,bundle,view,EquipmentClassEnum.BODY,		game.character.getBody());
		addEquippedTableRow(game,screen,skin,bundle,view,EquipmentClassEnum.ARM,		game.character.getArm());
		addEquippedTableRow(game,screen,skin,bundle,view,EquipmentClassEnum.GLOVE,		game.character.getGlove());
		addEquippedTableRow(game,screen,skin,bundle,view,EquipmentClassEnum.NECKLACE,	game.character.getNecklace());
		addEquippedTableRow(game,screen,skin,bundle,view,EquipmentClassEnum.LEFTRING,	game.character.getLeftRing());
		addEquippedTableRow(game,screen, skin,bundle,view,EquipmentClassEnum.RIGHTRING,	game.character.getRightRing());
		addEquippedTableRow(game,screen,skin,bundle,view,EquipmentClassEnum.LEFTHAND,	game.character.getLeftHand());
		addEquippedTableRow(game,screen,skin,bundle,view,EquipmentClassEnum.RIGHTHAND,	game.character.getRightHand());
		addEquippedTableRow(game,screen,skin,bundle,view,EquipmentClassEnum.PANTS,		game.character.getPants());
		addEquippedTableRow(game,screen,skin,bundle,view,EquipmentClassEnum.BOOTS,		game.character.getBoots());
		addEquippedTableRow(game,screen,skin,bundle,view,EquipmentClassEnum.GUARDIAN,	game.character.getGuardian());
		addEquippedTableRow(game,screen,skin,bundle,view,EquipmentClassEnum.MOUNT,		game.character.getMount());
		return view;
	}
	
	public static void addEquippedTableRow(DNDGame game,final MainScreen screen,Skin skin,I18NBundle bundle,
												Table view, final EquipmentClassEnum type,IEquipment e){
		view.add(new Label(bundle.get(type.name()),skin)).uniform().pad(DNDGame.PAD);
		TextButton b = getEquipmentButton(skin, bundle, e);
		b.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				screen.setEquipmentSelectView(type);
			}		
		});
		view.add(b).expandX().fillX().pad(DNDGame.PAD);
		view.row();
	}
	public static Table getEquippedSelectView(DNDGame game, final MainScreen screen,Skin skin, I18NBundle bundle, 
												final EquipmentClassEnum type){
		final IEquipment e = EquipmentUtils.getEquippedByType(type, game.character);
		List<IEquipment> list = EquipmentUtils.getEquipmentFromObjectsByType(type, game.character);		
		Table view = new Table();
		addEquippedSelectTableRow(game, screen, skin, bundle, view, type, e);
		Table sub = new Table();
		if(list != null && list.size() > 0){			
			for(final IEquipment t : list){
				TextButton b = getEquipmentButton(skin, bundle, t);
				b.addListener(new ClickListener(){
					@Override
					public void clicked(InputEvent event, float x, float y) {
						screen.setEquipmentCompareView(type,e,t);
					}
				});				
				sub.add(b).expandX().fillX().pad(DNDGame.PAD);
				sub.row();
			}
			
		}else{
				Label l = new Label(bundle.get("NO_EQUIPMENT"),skin);
				l.setAlignment(Align.center);
				sub.add(l).expandX().fillX().pad(DNDGame.PAD);
				sub.row();
		}
		ScrollPane spanel = new ScrollPane(sub,skin);
		spanel.setForceScroll(false, true);
		view.add(spanel).colspan(2).expand().fill();
		view.pack();
		return view;		
	}
	public static void addEquippedSelectTableRow(DNDGame game,final MainScreen screen,Skin skin,I18NBundle bundle,
													Table view, final EquipmentClassEnum type,final IEquipment e){
		view.add(new Label(bundle.get(type.name()),skin)).uniform().pad(DNDGame.PAD);		
		TextButton b = getEquipmentButton(skin, bundle, e);
		if(e != null){
			b.addListener(new ClickListener(){
				@Override
				public void clicked(InputEvent event, float x, float y) {
					screen.setEquipmentUnmountView(type);
				}		
			});
		}
		view.add(b).expandX().fillX().pad(DNDGame.PAD);
		view.row();
	}
	public static TextButton getEquipmentButton(Skin skin,I18NBundle bundle,IEquipment e){		
		TextButton b = new TextButton(	e==null?bundle.get("NO_EQUIPMENT"):bundle.get(e.getName()),
										EquipmentUtils.getTextButtonStyleByEquipmentLevel(e, skin));
		return b;		
	}

	public static Table getEquipmentCompView(final DNDGame game,final MainScreen screen,Skin skin,I18NBundle bundle,final EquipmentClassEnum type,IEquipment source,final IEquipment target){
		Table view = new Table();
		//view.debug();
		view.add(new Label(bundle.get("EQUIPMENTED"),skin)).uniform().fillX().pad(DNDGame.PAD);	
		Label sourceName;
		if(source != null){	
			sourceName = new Label(bundle.get(source.getName()),skin);
			sourceName.setColor(source.getLevel().getColor());
		}else{
			sourceName = new Label(bundle.get("NODATA"),skin);
			sourceName.setColor(skin.getColor("white"));
		}		 
		view.add(sourceName).colspan(2).uniform().expandX().center().pad(DNDGame.PAD).left();
		view.row();
		view.add(new Label(bundle.get("TARGET"),skin)).uniform().fillX().pad(DNDGame.PAD);
		Label targetName;
		if(target != null){
			targetName = new Label(bundle.get(target.getName()),skin);
			targetName.setColor(target.getLevel().getColor());
		}else{
			targetName = new Label(bundle.get("NODATA"),skin);
			targetName.setColor(skin.getColor("white"));
		}
		view.add(targetName).colspan(2).uniform().expandX().center().pad(DNDGame.PAD).left();
		view.row();
		
		view.add(new Label(bundle.get("NAME"),skin)).expandX().uniform().center().pad(DNDGame.PAD).left();
		view.add(new Label(bundle.get("EQUIPMENTED"),skin)).expandX().uniform().center().pad(DNDGame.PAD);
		view.add(new Label(bundle.get("TARGET"),skin)).expandX().uniform().center().pad(DNDGame.PAD);
		view.row();
		
		Color h = Color.GOLD;
		Color l = Color.GREEN;
		Color f = Color.GREEN;
		Color nf = Color.RED;
		Color n = Color.WHITE;
		
		Table stable = new Table();
		//stable.debug();
		for(AttributeEnum a : AttributeEnum.values()){
			int s = source == null ? 0 : source.getRequirementByName(a);
			int t = target == null ? 0 : target.getRequirementByName(a);
			String sourceText,targetText;
			Color  sourceColor,targetColor;
			if(s > 0 || t > 0){
				if(source != null){
					if(CharacterUtils.isRequirementFit(game.character,source,a)){
						sourceText = String.valueOf(s);
						sourceColor = f;					
					}else{
						sourceText = String.valueOf(s);
						sourceColor = nf;	
					}
				}else{
					sourceText = bundle.get("NODATA");
					sourceColor = n;
				}
				if(target != null){
					if(CharacterUtils.isRequirementFit(game.character,target,a)){
						targetText = String.valueOf(t);
						targetColor = f;					
					}else{
						targetText = String.valueOf(t);
						targetColor = nf;	
					}
				}else{
					targetText = bundle.get("NODATA");
					targetColor = n;
				}
				Label title = new Label(bundle.get("requirement") + bundle.get(a.toString()),skin);
				stable.add(title).uniform().pad(DNDGame.PAD).left();
				Label sourceLabel = new Label(sourceText,skin);
				sourceLabel.setColor(sourceColor);
				Label targetLabel = new Label(targetText,skin);
				targetLabel.setColor(targetColor);
				stable.add(sourceLabel).expandX().uniform().center().pad(DNDGame.PAD);
				stable.add(targetLabel).expandX().uniform().center().pad(DNDGame.PAD);
				stable.row();
			}
		}
		
		for(AttributeEnum a : AttributeEnum.values()){
			int s = source == null ? 0 : source.getAttributeByName(a);
			int t = target == null ? 0 : target.getAttributeByName(a);
			String sourceText,targetText;
			Color  sourceColor,targetColor;
			if(s > 0 || t > 0){
				if(s > t){
					sourceText 	= source==null ? bundle.get("NODATA") : String.valueOf(s);
					sourceColor = source==null ? n : h;
					targetText 	= target==null ? bundle.get("NODATA") : String.valueOf(t);
					targetColor = target==null ? n : l;
				}else if(s == t){
					sourceText 	= source==null ? bundle.get("NODATA") : String.valueOf(s);
					sourceColor = source==null ? n : h;
					targetText 	= target==null ? bundle.get("NODATA") : String.valueOf(t);
					targetColor = target==null ? n : h;
				}else{
					sourceText 	= source==null ? bundle.get("NODATA") : String.valueOf(s);
					sourceColor = source==null ? n : l;
					targetText 	= target==null ? bundle.get("NODATA") : String.valueOf(t);
					targetColor = target==null ? n : h;
				}
				Label title = new Label(bundle.get("increase") + bundle.get(a.toString()),skin);
				stable.add(title).uniform().pad(DNDGame.PAD).left();
				Label sourceLabel = new Label(sourceText,skin);
				sourceLabel.setColor(sourceColor);
				Label targetLabel = new Label(targetText,skin);
				targetLabel.setColor(targetColor);
				stable.add(sourceLabel).expandX().uniform().center().pad(DNDGame.PAD);
				stable.add(targetLabel).expandX().uniform().center().pad(DNDGame.PAD);
				stable.row();
			}
		}
		for(PropertyEnum p : PropertyEnum.values()){
			int s = source == null ? 0 : source.getPropertyByName(p);
			int t = target == null ? 0 : target.getPropertyByName(p);
			String sourceText,targetText;
			Color  sourceColor,targetColor;
			if(s > 0 || t > 0){
				if(s > t){
					sourceText 	= source==null ? bundle.get("NODATA") : String.valueOf(s);
					sourceColor = source==null ? n : h;
					targetText 	= target==null ? bundle.get("NODATA") : String.valueOf(t);
					targetColor = target==null ? n : l;
				}else if(s == t){
					sourceText 	= source==null ? bundle.get("NODATA") : String.valueOf(s);
					sourceColor = source==null ? n : h;
					targetText 	= target==null ? bundle.get("NODATA") : String.valueOf(t);
					targetColor = target==null ? n : h;
				}else{
					sourceText 	= source==null ? bundle.get("NODATA") : String.valueOf(s);
					sourceColor = source==null ? n : l;
					targetText 	= target==null ? bundle.get("NODATA") : String.valueOf(t);
					targetColor = target==null ? n : h;
				}
				Label title = new Label(bundle.get(p.toString()),skin);
				stable.add(title).uniform().pad(DNDGame.PAD).left();
				Label sourceLabel = new Label(sourceText,skin);
				sourceLabel.setColor(sourceColor);
				Label targetLabel = new Label(targetText,skin);
				targetLabel.setColor(targetColor);
				stable.add(sourceLabel).expandX().uniform().center().pad(DNDGame.PAD);
				stable.add(targetLabel).expandX().uniform().center().pad(DNDGame.PAD);
				stable.row();
			}
		}
		for(SkillEnum se : SkillEnum.values()){
			int s = source == null ? 0 : source.getSkillByName(se);
			int t = target == null ? 0 : target.getSkillByName(se);
			String sourceText,targetText;
			Color  sourceColor,targetColor;
			if(s > 0 || t > 0){
				if(s > t){
					sourceText 	= source==null ? bundle.get("NODATA") : String.valueOf(s);
					sourceColor = source==null ? n : h;
					targetText 	= target==null ? bundle.get("NODATA") : String.valueOf(t);
					targetColor = target==null ? n : l;
				}else if(s == t){
					sourceText 	= source==null ? bundle.get("NODATA") : String.valueOf(s);
					sourceColor = source==null ? n : h;
					targetText 	= target==null ? bundle.get("NODATA") : String.valueOf(t);
					targetColor = target==null ? n : h;
				}else{
					sourceText 	= source==null ? bundle.get("NODATA") : String.valueOf(s);
					sourceColor = source==null ? n : l;
					targetText 	= target==null ? bundle.get("NODATA") : String.valueOf(t);
					targetColor = target==null ? n : h;
				}
				Label title = new Label(bundle.get("SKILL_TITLE") + bundle.get(se.toString()),skin);
				stable.add(title).uniform().pad(DNDGame.PAD).left();
				Label sourceLabel = new Label(sourceText,skin);
				sourceLabel.setColor(sourceColor);
				Label targetLabel = new Label(targetText,skin);
				targetLabel.setColor(targetColor);
				stable.add(sourceLabel).expandX().uniform().center().pad(DNDGame.PAD);
				stable.add(targetLabel).expandX().uniform().center().pad(DNDGame.PAD);
				stable.row();
			}
		}
		ScrollPane spanel = new ScrollPane(stable,skin);
		spanel.setForceScroll(false, true);
		view.add(spanel).colspan(3).expand().fill().pad(DNDGame.PAD);
		view.row();

		TextButton confirm = new TextButton(bundle.get("SWITCH"), skin);
		confirm.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				CharacterUtils.swapEquipment(game.character, target, type);
				screen.setEquipmentMountView();
			}
		});
		TextButton cancel = new TextButton(bundle.get("BACK"), skin);
		cancel.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				screen.setEquipmentMountView();
			}
		});
		Table control = new Table();
		control.add(confirm).expandX().fillX().pad(DNDGame.PAD);
		control.add(cancel).expandX().fillX().pad(DNDGame.PAD);
		control.row();
		control.pack();
		view.add(control).colspan(3).expandX().fillX();
		view.pack();
		return view;		
	}
	public static Table getUnmountEquipmentView(final DNDGame game,final MainScreen screen,Skin skin,I18NBundle bundle,final EquipmentClassEnum type){
		IEquipment source = EquipmentUtils.getEquippedByType(type, game.character);
		Table view = new Table();		
		view.debug();
		view.add(new Label(bundle.get("EQUIPMENTED"),skin)).expandX().uniform().left().pad(DNDGame.PAD);
		Label sourceName;
		if(source != null){	
			sourceName = new Label(bundle.get(source.getName()),skin);
			sourceName.setColor(source.getLevel().getColor());
		}else{
			sourceName = new Label(bundle.get("NODATA"),skin);
			sourceName.setColor(skin.getColor("white"));
		}		 
		view.add(sourceName).expandX().uniform().center().pad(DNDGame.PAD);
		view.row();		
		
		view.add(new Label(bundle.get("NAME"),skin)).expandX().uniform().center().pad(DNDGame.PAD).left();
		view.add(new Label(bundle.get("EQUIPMENTED"),skin)).expandX().uniform().center().pad(DNDGame.PAD);
		view.row();
		
		Color h = Color.GOLD;
		Color l = Color.GREEN;
		Color f = Color.GREEN;
		Color nf = Color.RED;
		Color n = Color.WHITE;
		
		Table stable = new Table();
		for(AttributeEnum a : AttributeEnum.values()){
			int s = source == null ? 0 : source.getRequirementByName(a);
			String sourceText;
			Color  sourceColor;
			if(s > 0){
				if(source != null){
					if(CharacterUtils.isRequirementFit(game.character,source,a)){
						sourceText = String.valueOf(s);
						sourceColor = f;					
					}else{
						sourceText = String.valueOf(s);
						sourceColor = nf;	
					}
				}else{
					sourceText = bundle.get("NODATA");
					sourceColor = n;
				}			
				Label title = new Label(bundle.get("requirement") + bundle.get(a.toString()),skin);
				stable.add(title).uniform().left().pad(DNDGame.PAD);
				Label sourceLabel = new Label(sourceText,skin);
				sourceLabel.setColor(sourceColor);
				stable.add(sourceLabel).expandX().uniform().center().pad(DNDGame.PAD);
				stable.row();
			}
		}
		
		for(AttributeEnum a : AttributeEnum.values()){
			int s = source == null ? 0 : source.getAttributeByName(a);
			String sourceText;
			Color  sourceColor;
			if(s > 0){
				sourceText 	= source==null ? bundle.get("NODATA") : String.valueOf(s);
				sourceColor = source==null ? n : h;
				Label title = new Label(bundle.get("increase") + bundle.get(a.toString()),skin);
				stable.add(title).uniform().left().pad(DNDGame.PAD);
				Label sourceLabel = new Label(sourceText,skin);
				sourceLabel.setColor(sourceColor);
				stable.add(sourceLabel).expandX().uniform().center().pad(DNDGame.PAD);
				stable.row();
			}
		}
		for(PropertyEnum p : PropertyEnum.values()){
			int s = source == null ? 0 : source.getPropertyByName(p);
			String sourceText;
			Color  sourceColor;
			if(s > 0 ){
				sourceText 	= source==null ? bundle.get("NODATA") : String.valueOf(s);
				sourceColor = source==null ? n : h;
				Label title = new Label(bundle.get(p.toString()),skin);
				stable.add(title).uniform().left().pad(DNDGame.PAD);
				Label sourceLabel = new Label(sourceText,skin);
				sourceLabel.setColor(sourceColor);
				stable.add(sourceLabel).expandX().uniform().center().pad(DNDGame.PAD);
				stable.row();
			}
		}
		for(SkillEnum se : SkillEnum.values()){
			int s = source == null ? 0 : source.getSkillByName(se);
			String sourceText;
			Color  sourceColor;
			if(s > 0){
				sourceText 	= source==null ? bundle.get("NODATA") : String.valueOf(s);
				sourceColor = source==null ? n : h;
				Label title = new Label(bundle.get("SKILL_TITLE") + bundle.get(se.toString()),skin);
				stable.add(title).uniform().left().pad(DNDGame.PAD);
				Label sourceLabel = new Label(sourceText,skin);
				sourceLabel.setColor(sourceColor);
				stable.add(sourceLabel).expandX().uniform().center().pad(DNDGame.PAD);
				stable.row();
			}
		}
		ScrollPane spanel = new ScrollPane(stable,skin);
		spanel.setForceScroll(false, true);
		view.add(spanel).colspan(2).expand().fill().pad(DNDGame.PAD);
		view.row();

		TextButton confirm = new TextButton(bundle.get("UNMOUNT"), skin);
		confirm.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				CharacterUtils.unmountEquipment(game.character, type);
				screen.setEquipmentMountView();
			}
		});
		TextButton cancel = new TextButton(bundle.get("BACK"), skin);
		cancel.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				screen.setEquipmentMountView();
			}
		});
		Table control = new Table();
		control.add(confirm).expandX().fillX().pad(DNDGame.PAD);
		control.add(cancel).expandX().fillX().pad(DNDGame.PAD);
		control.row();
		control.pack();
		view.add(control).colspan(3).expandX().fillX().pad(DNDGame.PAD);
		view.pack();
		return view;		
	}
	public static Table getSkillView(DNDGame game,final MainScreen screen,Skin skin,I18NBundle bundle){
		final String prefix = "_SHORT";
		Table view = new Table();
		Table v = new Table();
		v.top();
		for(SkillEnum s : SkillEnum.values()){
			if(s.equals(SkillEnum.ALLSKILL)){
				continue;
			}
			Table t = new Table(skin);
			t.background("translucent50");
			ISkill is = game.character.getSkillByName(s);		
			if(is == null){
				t.add(new Label(bundle.get(s.toString()),skin)).uniform().left().pad(DNDGame.PAD);
				t.add(new Label(bundle.get("UNLEARNED_SKILL"),skin)).uniform().right().pad(DNDGame.PAD);
				t.row();
				ProgressbarActor p = new ProgressbarActor();
				p.setFull(SkillUtils.levels[1]);
				p.setCurrent(0);
				p.setFont(game.assets.manager.get(Assets.SMALLFONT_20_ASCII,BitmapFont.class));
				t.add(p).height(25).expandX().fillX().left().pad(DNDGame.PAD);
				t.add(new Label(String.valueOf(p.getPercent()*100) + "%",skin)).right().pad(DNDGame.PAD);
				t.row();
				t.add(new Label(bundle.get(s.toString() + prefix),skin)).colspan(2).expandX().fillX().left();
				t.row();
			}else{
				int alevel = CharacterUtils.getSkillLevelByName(is.getName(), game.character);
				int slevel = SkillUtils.getLevel(is);
				String levelString = bundle.get("LEVEL") + slevel;
				if(alevel>0){
					levelString = bundle.get("LEVEL") + slevel + "+" + alevel;
				}
				t.add(new Label(bundle.get(is.getName().toString()),skin)).left().pad(DNDGame.PAD);
				t.add(new Label(levelString,skin)).right().pad(DNDGame.PAD);
				t.row();
				ProgressbarActor p = new ProgressbarActor();
				p.setFull(SkillUtils.getNextLevelExp(is));
				p.setCurrent(is.getEXP()-SkillUtils.getLevelExp(is));
				p.setFont(game.assets.manager.get(Assets.SMALLFONT_20_ASCII,BitmapFont.class));
				t.add(p).expandX().fillX().height(25f).left().pad(DNDGame.PAD);
				t.add(new Label(String.valueOf(p.getPercent()*100) + "%",skin)).right().pad(DNDGame.PAD);
				t.row();
				t.add(new Label(bundle.get(is.getName().toString() + prefix),skin)).colspan(2).expandX().fillX().left();
				t.row();
			}
			v.add(t).expandX().fillX().pad(DNDGame.PAD).row();;
		}
		ScrollPane spanel = new ScrollPane(v,skin);
		spanel.setForceScroll(false, true);
		spanel.setSmoothScrolling(true); 
		view.add(spanel).expand().fill().align(Align.top).pad(DNDGame.PAD);
		return view;
	}
	public static Table getCharacterView(DNDGame game,final MainScreen screen,Skin skin,I18NBundle bundle){
		Table view = new Table();
		view.add(getCharacterBasicView(game, screen, skin, bundle)).expandX().fillX().pad(DNDGame.PAD);
		view.row();
		view.add(getCharacterExtraView(game, screen, skin, bundle)).expand().fill().pad(DNDGame.PAD);
		view.row();
		return view;
	}
	private static ScrollPane getCharacterExtraView(DNDGame game, MainScreen screen, Skin skin, I18NBundle bundle) {
		long start = System.nanoTime();
		final float padRight = 30f;
		Table characterExtraView = new Table(skin);
	
		String physicalDamageString = game.extra.minPhysicalDamage
				+ bundle.get("SPLITER_TO") + game.extra.maxPhysicalDamage;
		Color physical = Color.ROYAL;
		Label PhysicalDamageTitle = new Label("",skin);
		Label PhysicalDamageContext = new Label("",skin);
		PhysicalDamageTitle.setText(bundle.get("PhysicalDamage"));
		PhysicalDamageTitle.setColor(physical);
		PhysicalDamageContext.setText(physicalDamageString);
		PhysicalDamageContext.setColor(physical);
		characterExtraView.add(PhysicalDamageTitle).left().uniform();
		characterExtraView.add(PhysicalDamageContext).right().expandX().padRight(padRight);
		characterExtraView.row();
		
		Label PhysicalDamageToRangeTitle = new Label("",skin);
		Label PhysicalDamageToRangeContext = new Label("",skin);
		String physicalDamageToRangeString = game.extra.minPhysicalDamageToRange
				+ bundle.get("SPLITER_TO") + game.extra.maxPhysicalDamageToRange;
		PhysicalDamageToRangeTitle.setText(bundle.get("PhysicalDamageToRange"));
		PhysicalDamageToRangeTitle.setColor(physical);
		PhysicalDamageToRangeContext.setText(physicalDamageToRangeString);
		PhysicalDamageToRangeContext.setColor(physical);
		characterExtraView.add(PhysicalDamageToRangeTitle).left().uniform();
		characterExtraView.add(PhysicalDamageToRangeContext).right().uniform().expandX().padRight(padRight);
		characterExtraView.row();
		
		Label PhysicalDamageToMeleeTitle = new Label("",skin);
		Label PhysicalDamageToMeleeContext = new Label("",skin);
		String physicalDamageToMeleeString = game.extra.minPhysicalDamageToMelee
				+ bundle.get("SPLITER_TO") + game.extra.maxPhysicalDamageToMelee;
		PhysicalDamageToMeleeTitle.setText(bundle.get("PhysicalDamageToMelee"));
		PhysicalDamageToMeleeTitle.setColor(physical);
		PhysicalDamageToMeleeContext.setText(physicalDamageToMeleeString);
		PhysicalDamageToMeleeContext.setColor(physical);
		characterExtraView.add(PhysicalDamageToMeleeTitle).left().uniform();
		characterExtraView.add(PhysicalDamageToMeleeContext).right().uniform().expandX().padRight(padRight);
		characterExtraView.row();
		
		Label FireDamageTitle = new Label("",skin);
		Label FireDamageContext = new Label("",skin);
		String fireDamageString = game.extra.minFireDamage
				+ bundle.get("SPLITER_TO") + game.extra.maxFireDamage;
		FireDamageTitle.setText(bundle.get("FireDamage"));
		FireDamageTitle.setColor(Color.RED);
		FireDamageContext.setText(fireDamageString);
		FireDamageContext.setColor(Color.RED);
		characterExtraView.add(FireDamageTitle).left().uniform();
		characterExtraView.add(FireDamageContext).right().uniform().expandX().padRight(padRight);
		characterExtraView.row();
		
		Label ColdDamageTitle = new Label("",skin);
		Label ColdDamageContext = new Label("",skin);
		String coldDamageString = game.extra.minColdDamage
				+ bundle.get("SPLITER_TO") + game.extra.maxColdDamage;
		ColdDamageTitle.setText(bundle.get("ColdDamage"));
		ColdDamageTitle.setColor(Color.CYAN);
		ColdDamageContext.setText(coldDamageString);
		ColdDamageContext.setColor(Color.CYAN);
		characterExtraView.add(ColdDamageTitle).left().uniform();
		characterExtraView.add(ColdDamageContext).right().uniform().expandX().padRight(padRight);
		characterExtraView.row();
		
		Label PoisonDamageTitle = new Label("",skin);
		Label PoisonDamageContext = new Label("",skin);
		String poisonDamageString = game.extra.minPoisonDamage
				+ bundle.get("SPLITER_TO") + game.extra.maxPoisonDamage;
		PoisonDamageTitle.setText(bundle.get("PoisonDamage"));
		PoisonDamageTitle.setColor(Color.GREEN);
		PoisonDamageContext.setText(poisonDamageString);
		PoisonDamageContext.setColor(Color.GREEN);
		characterExtraView.add(PoisonDamageTitle).left().uniform();
		characterExtraView.add(PoisonDamageContext).right().uniform().expandX().padRight(padRight);
		characterExtraView.row();
		
		Label EnergyDamageTitle = new Label("",skin);
		Label EnergyDamageContext = new Label("",skin);
		String energyDamageString = game.extra.minEnergyDamage
				+ bundle.get("SPLITER_TO") + game.extra.maxEnergyDamage;
		EnergyDamageTitle.setText(bundle.get("EnergyDamage"));
		EnergyDamageTitle.setColor(Color.GOLD);
		EnergyDamageContext.setText(energyDamageString);
		EnergyDamageContext.setColor(Color.GOLD);
		characterExtraView.add(EnergyDamageTitle).left().uniform();
		characterExtraView.add(EnergyDamageContext).right().uniform().expandX().padRight(padRight);
		characterExtraView.row();
		
		Label DamageToUndeadTitle = new Label("",skin);
		Label DamageToUndeadContext = new Label("",skin);
		String damageToUndeadString = game.extra.minPhysicalDamageToUndead
				+ bundle.get("SPLITER_TO") + game.extra.maxPhysicalDamageToUndead;
		DamageToUndeadTitle.setText(bundle.get("DamageToUndead"));
		DamageToUndeadContext.setText(damageToUndeadString);
		characterExtraView.add(DamageToUndeadTitle).left().uniform();
		characterExtraView.add(DamageToUndeadContext).right().uniform().expandX().padRight(padRight);
		characterExtraView.row();
		
		Label DamageToDemonTitle = new Label("",skin);
		Label DamageToDemonContext = new Label("",skin);
		String damageToDemonString = game.extra.minPhysicalDamageToDemon
				+ bundle.get("SPLITER_TO") + game.extra.maxPhysicalDamageToDemon;
		DamageToDemonTitle.setText(bundle.get("DamageToDemon"));
		DamageToDemonContext.setText(damageToDemonString);
		characterExtraView.add(DamageToDemonTitle).left().uniform();
		characterExtraView.add(DamageToDemonContext).right().uniform().expandX().padRight(padRight);
		characterExtraView.row();
		
		Label DamageToDragonTitle = new Label("",skin);
		Label DamageToDragonContext = new Label("",skin);
		String damageToDragonString = game.extra.minPhysicalDamageToDragon
				+ bundle.get("SPLITER_TO") + game.extra.maxPhysicalDamageToDragon;
		DamageToDragonTitle.setText(bundle.get("DamageToDragon"));
		DamageToDragonContext.setText(damageToDragonString);
		characterExtraView.add(DamageToDragonTitle).left().uniform();
		characterExtraView.add(DamageToDragonContext).right().uniform().expandX().padRight(padRight);
		characterExtraView.row();
		
		Label regHPTitle = new Label("",skin);
		Label regHPContext = new Label("",skin);
		regHPTitle.setText(bundle.get("REG_HP"));
		regHPContext.setText(String.valueOf(game.extra.regenHP));
		characterExtraView.add(regHPTitle).left().uniform();
		characterExtraView.add(regHPContext).right().uniform().expandX().padRight(padRight);
		characterExtraView.row();
		
		Label regMPTitle = new Label("",skin);
		Label regMPContext = new Label("",skin);
		regMPTitle.setText(bundle.get("REG_MP"));
		regMPContext.setText(String.valueOf(game.extra.regenMP));
		characterExtraView.add(regMPTitle).left().uniform();
		characterExtraView.add(regMPContext).right().uniform().expandX().padRight(padRight);
		characterExtraView.row();
		
		Label regSPTitle = new Label("",skin);
		Label regSPContext = new Label("",skin);
		regSPTitle.setText(bundle.get("REG_SP"));
		regSPContext.setText(String.valueOf(game.extra.regenSP));
		characterExtraView.add(regSPTitle).left().uniform();
		characterExtraView.add(regSPContext).right().uniform().expandX().padRight(padRight);
		characterExtraView.row();
		
		Label DODGETitle = new Label("",skin);
		Label DODGEContext = new Label("",skin);
		DODGETitle.setText(bundle.get("DODGE"));
		DODGEContext.setText(String.valueOf(game.extra.dodge));
		characterExtraView.add(DODGETitle).left().uniform();
		characterExtraView.add(DODGEContext).right().uniform().expandX().padRight(padRight);
		characterExtraView.row();
		
		Label CRITICALTitle = new Label("",skin);
		Label CRITICALContext = new Label("",skin);
		CRITICALTitle.setText(bundle.get("CRITICAL"));
		CRITICALContext.setText(String.valueOf(game.extra.critical));
		characterExtraView.add(CRITICALTitle).left().uniform();
		characterExtraView.add(CRITICALContext).right().uniform().expandX().padRight(padRight);
		characterExtraView.row();
		
		Label AIMTitle = new Label("",skin);
		Label AIMContext = new Label("",skin);
		AIMTitle.setText(bundle.get("AIM"));
		AIMContext.setText(String.valueOf(game.extra.aim));
		characterExtraView.add(AIMTitle).left().uniform();
		characterExtraView.add(AIMContext).right().uniform().expandX().padRight(padRight);
		characterExtraView.row();
		
		Label SPEEDTitle = new Label("",skin);
		Label SPEEDContext = new Label("",skin);
		SPEEDTitle.setText(bundle.get("SPEED"));
		SPEEDContext.setText(String.valueOf(game.extra.speed));
		characterExtraView.add(SPEEDTitle).left().uniform();
		characterExtraView.add(SPEEDContext).right().uniform().expandX().padRight(padRight);
		characterExtraView.row();
		
		Label DecreaseMPCostTitle = new Label("",skin);
		Label DecreaseMPCostContext = new Label("",skin);
		DecreaseMPCostTitle.setText(bundle.get("DecreaseMPCost"));
		DecreaseMPCostContext.setText(String.valueOf(game.extra.decreaseMPCost));
		characterExtraView.add(DecreaseMPCostTitle).left().uniform();
		characterExtraView.add(DecreaseMPCostContext).right().uniform().expandX().padRight(padRight);
		characterExtraView.row();
		
		Label DecreaseSPCostTitle = new Label("",skin);
		Label DecreaseSPCostContext = new Label("",skin);
		DecreaseSPCostTitle.setText(bundle.get("DecreaseSPCost"));
		DecreaseSPCostContext.setText(String.valueOf(game.extra.decreaseSPCost));
		characterExtraView.add(DecreaseSPCostTitle).left().uniform();
		characterExtraView.add(DecreaseSPCostContext).right().uniform().expandX().padRight(padRight);
		characterExtraView.row();
		
		Label PhysicalArmorTitle = new Label("",skin);
		Label PhysicalArmorContext = new Label("",skin);
		PhysicalArmorTitle.setText(bundle.get("PhysicalArmor"));
		PhysicalArmorContext.setText(String.valueOf(game.extra.physicalArmor));
		characterExtraView.add(PhysicalArmorTitle).left().uniform();
		characterExtraView.add(PhysicalArmorContext).right().uniform().expandX().padRight(padRight);
		characterExtraView.row();
		
		Label FireResistTitle = new Label("",skin);
		Label FireResistContext = new Label("",skin);
		FireResistTitle.setText(bundle.get("FireResist"));
		FireResistContext.setText(String.valueOf(game.extra.fireResist));
		characterExtraView.add(FireResistTitle).left().uniform();
		characterExtraView.add(FireResistContext).right().uniform().expandX().padRight(padRight);
		characterExtraView.row();
		
		Label ColdResistTitle = new Label("",skin);
		Label ColdResistContext = new Label("",skin);
		ColdResistTitle.setText(bundle.get("ColdResist"));
		ColdResistContext.setText(String.valueOf(game.extra.coldResist));
		characterExtraView.add(ColdResistTitle).left().uniform();
		characterExtraView.add(ColdResistContext).right().uniform().expandX().padRight(padRight);
		characterExtraView.row();
		
		Label PoisonResistTitle = new Label("",skin);
		Label PoisonResistContext = new Label("",skin);
		PoisonResistTitle.setText(bundle.get("PoisonResist"));
		PoisonResistContext.setText(String.valueOf(game.extra.poisonResist));
		characterExtraView.add(PoisonResistTitle).left().uniform();
		characterExtraView.add(PoisonResistContext).right().uniform().expandX().padRight(padRight);
		characterExtraView.row();
		
		Label EnergyResistTitle = new Label("",skin);
		Label EnergyResistContext = new Label("",skin);
		EnergyResistTitle.setText(bundle.get("EnergyResist"));
		EnergyResistContext.setText(String.valueOf(game.extra.energyResist));
		characterExtraView.add(EnergyResistTitle).left().uniform();
		characterExtraView.add(EnergyResistContext).right().uniform().expandX().padRight(padRight);
		characterExtraView.row();

		Label PhysicalArmorToRangeTitle = new Label("",skin);
		Label PhysicalArmorToRangeContext = new Label("",skin);
		PhysicalArmorToRangeTitle.setText(bundle.get("PhysicalArmorToRange"));
		PhysicalArmorToRangeContext.setText(String.valueOf(game.extra.physicalArmorToRange));
		characterExtraView.add(PhysicalArmorToRangeTitle).left().uniform();
		characterExtraView.add(PhysicalArmorToRangeContext).right().uniform().expandX().padRight(padRight);
		characterExtraView.row();
		
		Label PhysicalArmorToMeleeTitle = new Label("",skin);
		Label PhysicalArmorToMeleeContext = new Label("",skin);
		PhysicalArmorToMeleeTitle.setText(bundle.get("PhysicalArmorToMelee"));
		PhysicalArmorToMeleeContext.setText(String.valueOf(game.extra.physicalArmorToMelee));
		characterExtraView.add(PhysicalArmorToMeleeTitle).left().uniform();
		characterExtraView.add(PhysicalArmorToMeleeContext).right().uniform().expandX().padRight(padRight);
		characterExtraView.row();
		
		Label StunChanceTitle = new Label("",skin);
		Label StunChanceContext = new Label("",skin);
		StunChanceTitle.setText(bundle.get("StunChance"));
		StunChanceContext.setText(String.valueOf(game.extra.stunChance));
		characterExtraView.add(StunChanceTitle).left().uniform();
		characterExtraView.add(StunChanceContext).right().uniform().expandX().padRight(padRight);
		characterExtraView.row();
		
		Label FrezenChanceTitle = new Label("",skin);
		Label FrezenChanceContext = new Label("",skin);
		FrezenChanceTitle.setText(bundle.get("FrezenChance"));
		FrezenChanceContext.setText(String.valueOf(game.extra.frezenChance));
		characterExtraView.add(FrezenChanceTitle).left().uniform();
		characterExtraView.add(FrezenChanceContext).right().uniform().expandX().padRight(padRight);
		characterExtraView.row();
		
		Label ReflectPhysicalDamageTitle = new Label("",skin);
		Label ReflectPhysicalDamageContext = new Label("",skin);
		ReflectPhysicalDamageTitle.setText(bundle.get("ReflectPhysicalDamage"));
		ReflectPhysicalDamageContext.setText(String.valueOf(game.extra.reflectPhysicalDamage));		
		characterExtraView.add(ReflectPhysicalDamageTitle).left().uniform();
		characterExtraView.add(ReflectPhysicalDamageContext).right().uniform().expandX().padRight(padRight);
		characterExtraView.row();
		
		Label ReflectMagicDamageTitle = new Label("",skin);
		Label ReflectMagicDamageContext = new Label("",skin);
		ReflectMagicDamageTitle.setText(bundle.get("ReflectMagicDamage"));
		ReflectMagicDamageContext.setText(String.valueOf(game.extra.reflectMagicDamage));
		characterExtraView.add(ReflectMagicDamageTitle).left().uniform();
		characterExtraView.add(ReflectMagicDamageContext).right().uniform().expandX().padRight(padRight);
		characterExtraView.row();
		
		Label DurableDamageTitle = new Label("",skin);
		Label DurableDamageContext = new Label("",skin);
		DurableDamageTitle.setText(bundle.get("DurableDamage"));
		DurableDamageContext.setText(String.valueOf(game.extra.durableDamage));
		characterExtraView.add(DurableDamageTitle).left().uniform();
		characterExtraView.add(DurableDamageContext).right().uniform().expandX().padRight(padRight);
		characterExtraView.row();
		
		Label StealthHPTitle = new Label("",skin);
		Label StealthHPContext = new Label("",skin);
		StealthHPTitle.setText(bundle.get("StealthHP"));
		StealthHPContext.setText(String.valueOf(game.extra.stealthHP));
		characterExtraView.add(StealthHPTitle).left().uniform();
		characterExtraView.add(StealthHPContext).right().uniform().expandX().padRight(padRight);
		characterExtraView.row();
		
		Label StealthMPTitle = new Label("",skin);
		Label StealthMPContext = new Label("",skin);
		StealthMPTitle.setText(bundle.get("StealthMP"));
		StealthMPContext.setText(String.valueOf(game.extra.stealthMP));
		characterExtraView.add(StealthMPTitle).left().uniform();
		characterExtraView.add(StealthMPContext).right().uniform().expandX().padRight(padRight);
		characterExtraView.row();
		
		Label IncreaseCoinTitle = new Label("",skin);
		Label IncreaseCoinContext = new Label("",skin);
		IncreaseCoinTitle.setText(bundle.get("IncreaseCoin"));
		IncreaseCoinContext.setText(String.valueOf(game.extra.increaseCoin));
		characterExtraView.add(IncreaseCoinTitle).left().uniform();
		characterExtraView.add(IncreaseCoinContext).right().uniform().expandX().padRight(padRight);
		characterExtraView.row();
		
		for(SkillEnum s : SkillEnum.values()){
			int lv = CharacterUtils.getSkillByName(s, game.character);
			if(lv >0){
				characterExtraView.add(bundle.get(s.toString())).left().uniform();
				characterExtraView.add(String.valueOf(lv)).right().uniform().expandX().padRight(padRight);
				characterExtraView.row();
			}
		}	
		characterExtraView.row();
		ScrollPane spanel = new ScrollPane(characterExtraView,skin);
		spanel.setForceScroll(false, true);
		spanel.setSmoothScrolling(true); 
		long end = System.nanoTime();
		System.out.println("characterExtra:" + (end - start) + "ns");
		return spanel;
	}
	
	public static Table getCharacterBasicView(DNDGame game,final MainScreen screen,Skin skin,I18NBundle bundle){
		BitmapFont small = game.assets.manager.get(Assets.SMALLFONT_20_ASCII,BitmapFont.class);
		Table characterStatusView = new Table(skin);
		characterStatusView.background("translucent50");
		characterStatusView.add(bundle.get("NAME")).left().uniform().padRight(10f);
		characterStatusView.add(game.character.getName()).colspan(7).expandX();
		characterStatusView.row();
		
		characterStatusView.add(bundle.get("STR")).left().uniform();
		characterStatusView.add(String.valueOf(game.extra.STR)).expandX();
		
		characterStatusView.add(bundle.get("DEX")).left().uniform();
		characterStatusView.add(String.valueOf(game.extra.DEX)).expandX();
		
		characterStatusView.add(bundle.get("WIZ")).left().uniform();
		characterStatusView.add(String.valueOf(game.extra.WIZ)).expandX();
		
		characterStatusView.add(bundle.get("STA")).left().uniform();
		characterStatusView.add(String.valueOf(game.extra.STA)).expandX();
		characterStatusView.row();		
		
		
		ProgressbarActor hpbar = new ProgressbarActor();
		hpbar.setFont(small);		
		characterStatusView.add(bundle.get("HP")).left().padRight(10f);
		hpbar.setFull(game.extra.maxHP);
		hpbar.setCurrent(game.character.getHp());
		characterStatusView.add(hpbar).colspan(7).expandX().fillX().height(DNDGame.PROGRESSHEIGHT);
		characterStatusView.row();		
		
		ProgressbarActor mpbar = new ProgressbarActor();
		mpbar.setFont(small);		
		characterStatusView.add(bundle.get("MP")).left().padRight(10f);
		mpbar.setFull(game.extra.maxMP);
		mpbar.setCurrent(game.character.getMp());
		characterStatusView.add(mpbar).colspan(7).expandX().fillX().height(DNDGame.PROGRESSHEIGHT);
		characterStatusView.row();
		
		
		ProgressbarActor spbar = new ProgressbarActor();
		spbar.setFont(small);		
		characterStatusView.add(bundle.get("SP")).left().padRight(10f);
		spbar.setFull(game.extra.maxSP);
		spbar.setCurrent(game.character.getSp());
		characterStatusView.add(spbar).colspan(7).expandX().fillX().height(DNDGame.PROGRESSHEIGHT);
		characterStatusView.row();
		
		ProgressbarActor wpbar = new ProgressbarActor();
		wpbar.setFont(small);		
		characterStatusView.add(bundle.get("WP")).left().padRight(10f);
		wpbar.setFull(game.extra.maxWP);
		wpbar.setCurrent(CharacterUtils.getCurrentWP(game.character));
		characterStatusView.add(wpbar).colspan(7).expandX().fillX().height(DNDGame.PROGRESSHEIGHT);
		characterStatusView.row();		

		return characterStatusView;
	}
	public static Table getControlView(DNDGame game,final MainScreen screen,Skin skin,I18NBundle bundle){
		Table view = new Table();
		TextButton maps   	= new TextButton("Maps", skin);
		TextButton status 	= new TextButton("Status", skin);
		TextButton equips 	= new TextButton("Equips", skin);
		TextButton quests 	= new TextButton("Quests", skin);
		TextButton objects 	= new TextButton("Objects", skin);
		TextButton make 	= new TextButton("Make", skin);
		TextButton skills 	= new TextButton("Skills", skin);
		TextButton log	 	= new TextButton("Logs", skin);
		maps.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
					screen.setMapView();
			}			
		});
		quests.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
					screen.setQuestView();
			}			
		});
		objects.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
					screen.setObjectView();
			}			
		});
		
		equips.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				screen.setEquipmentMountView();
			}			
		});
		status.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				screen.setStatusView();
			}			
		});
		make.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				screen.setMakeView();
			}			
		});
		skills.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				screen.setSkillView();
			}			
		});
		log.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				screen.setLogView();
			}			
		});
		float width = 180f;
		float height = 180f;
		float pad = 5f;
		view.add(maps)		.size(width,height).pad(pad);
		view.add(status)	.size(width,height).pad(pad);
		view.add(equips)	.size(width,height).pad(pad);
		view.add(quests)	.size(width,height).pad(pad);
		view.add(objects)	.size(width,height).pad(pad);
		view.add(make)		.size(width,height).pad(pad);
		view.add(skills)	.size(width,height).pad(pad);
		view.add(log)	.size(width,height).pad(pad);
		view.row();
		view.pack();
		ScrollPane spanel = new ScrollPane(view,skin);
		spanel.setForceScroll(true, false);
		spanel.setupFadeScrollBars(0, 0);
		Table t = new Table();
		t.add(spanel).size(1080f,219f).pad(pad);
		return t;
	}

	public static TextButton getEquipmentButton(final DNDGame game, final MainScreen mainScreen, Skin skin, I18NBundle bundle,final IEquipment e){
		TextButton b = new TextButton("",skin);
		final EquipmentClassEnum type = EquipmentUtils.getEquipmentType(game.character,e);
		final IEquipment s = EquipmentUtils.getEquippedByType(type, game.character);
		b.setText(bundle.get(e.getName()));
		b.setStyle(EquipmentUtils.getTextButtonStyleByEquipmentLevel(e, skin));
		b.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				mainScreen.setEquipmentCompareView(type, s, e);
			}			
		});
		return b;		
	}
}
