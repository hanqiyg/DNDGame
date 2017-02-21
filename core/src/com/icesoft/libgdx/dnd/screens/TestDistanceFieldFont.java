package com.icesoft.libgdx.dnd.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.icesoft.libgdx.dnd.DNDGame;

public class TestDistanceFieldFont implements Screen{
	private static final String T = "TestDistanceFieldFont.class";
	
	private OrthographicCamera stageCamera = new OrthographicCamera();	
	private ScalingViewport stageViewport = new ScalingViewport(Scaling.fit, DNDGame.SCREEN_WIDTH, DNDGame.SCREEN_HEIGHT, stageCamera);
	private Stage stage = new Stage(stageViewport);
	
	private SpriteBatch batch;
	private BitmapFont font;
	private BitmapFont genFont;
	private ShaderProgram fontShader;
	private String str = "ABCD123";
	private float scale = 0.125f;
	@Override
	public void show() {
		Texture texture = new Texture(Gdx.files.internal("fonts/DistanceFieldFont128.png"), true); 
		texture.setFilter(TextureFilter.MipMapLinearLinear, TextureFilter.Linear); 
		
		font = new BitmapFont(Gdx.files.internal("fonts/DistanceFieldFont128.fnt"), new TextureRegion(texture), false);
		
		fontShader = new ShaderProgram(Gdx.files.internal("shader/font.vert"), Gdx.files.internal("shader/font.frag"));
		if (!fontShader.isCompiled()) {
		    Gdx.app.error("fontShader", "compilation failed:\n" + fontShader.getLog());
		}
		batch = new SpriteBatch();
		
		FreeTypeFontParameter default_font_Param = new FreeTypeFontParameter();
		default_font_Param.characters = "G@" + String.valueOf(128*scale) + str;
		default_font_Param.size = 128; 
	    default_font_Param.kerning=true;
	    default_font_Param.color = Color.WHITE;
	    FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/MSYHBD.TTF"));
	    genFont = generator.generateFont(default_font_Param);
	    genFont.getData().setScale(0.25f);
	    generator.dispose();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		font.getData().setScale(scale);
		font.draw(batch, "N@"+(128*scale)+ "=" + str,100,100);
		
		batch.setShader(fontShader);
		fontShader.setUniformf("smoothing", 0.25f / (4 * scale));
		font.draw(batch, "S@"+(128*scale)+ "=" + str, 100,200);
		batch.setShader(null);
		genFont.draw(batch,"G@" +(128*scale)+ "=" + str, 100,300);
		batch.end();	
		
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