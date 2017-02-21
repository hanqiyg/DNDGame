package com.icesoft.libgdx.dnd.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class CircularProgress
    extends Actor {

    // 位置   - (x0, y0)
    public static final int POSITION_COMPONENTS = 2;
    // 颜色   - (r, g, b, a)
    public static final int COLOR_COMPONENTS = 4;
    // 纹理坐标    - (x1,y1)
    public static final int TEXCOORD_COMPONENTS = 2;
    // 定义�?�?10组数�?  位置 + 颜色 + 纹理坐标
    private static final int MAX_VERTICES = 10;
    // COMPONENTS
    private static final int TOTAL_COMPONENTS = 5; 
    
    private static final int MAX_INDICES  =  0;
  
    protected int idx = 0;
    
    private Vector2 last;
    private Mesh mesh;
    private TextureRegion region;
    private TextureRegion background;
    private ShaderProgram shader;
    private float percent;
    /**
     *  顶点数据,组合为（位置坐标 + 颜色 + 纹理坐标 �?,颜色以float类型表示
     * x0, y0, color,x1,y1
     * x0, y0, color,x1,y1
     * x0, y0, color,x1,y1
     * .....
     */
    private float[] vertices;
    
    

    public CircularProgress(TextureRegion region,TextureRegion background) {
        this.region = region;
        this.background = background;

        /**
         *  使用默认的shader
         *  �?般不指定ShaderProgram，我们使用SpriteBatch的时候都是使用默认的shader
         */
        this.shader = SpriteBatch.createDefaultShader();
        
        // 设置顶点数据类型为数组类�?
        Mesh.VertexDataType vertexDataType = Mesh.VertexDataType.VertexArray;
        
        /**
         * 只需要处�? ：顶点坐标�?�颜色�?�纹理坐�? 
         */
        VertexAttribute[] vertexAttributes = new VertexAttribute[3];
        vertexAttributes[0] = new VertexAttribute(Usage.Position, POSITION_COMPONENTS, "a_position");
        vertexAttributes[1] = new VertexAttribute(Usage.ColorPacked, COLOR_COMPONENTS, "a_color");
        vertexAttributes[2] = new VertexAttribute(Usage.TextureCoordinates, TEXCOORD_COMPONENTS, "a_texCoord0");
        
        this.mesh = new Mesh(vertexDataType, false, MAX_VERTICES, MAX_INDICES, vertexAttributes);
        float width = region.getRegionWidth();
        float height = region.getRegionHeight();
        
        /**
         * 初始化顶点数�?
         */
        vertices = new float[MAX_VERTICES  * TOTAL_COMPONENTS];

        int idx = 0; 
        vertices[(idx++)] = 0;
        vertices[(idx++)] = 0;

        idx = idx + 3; 
        vertices[(idx++)] = 0;
        vertices[(idx++)] = (height / 2.0f);

        idx = idx + 3;
        vertices[(idx++)] = (width / 2.0f);
        vertices[(idx++)] = (height / 2.0f);

        idx = idx + 3;
        vertices[(idx++)] = (width / 2.0f);
        vertices[(idx++)] = 0;

        idx = idx + 3;
        vertices[(idx++)] = (width / 2.0f);
        vertices[(idx++)] = (-height / 2.0f);

        idx = idx + 3;
        vertices[(idx++)] = 0;
        vertices[(idx++)] = (-height / 2.0f);

        idx = idx + 3; 
        vertices[(idx++)] = (-width / 2.0f);
        vertices[(idx++)] = (-height / 2.0f);

        idx = idx + 3; 
        vertices[(idx++)] = (-width / 2.0f);
        vertices[(idx++)] = 0;

        idx = idx + 3; 
        vertices[(idx++)] = (-width / 2.0f);
        vertices[(idx++)] = (height / 2.0f);
       
        /**
         * 初始话顶点位�? �? 纹理坐标
         */
        for (int i = 0; i < 9; i++) {
            vertices[i * 5 + 0] = (vertices[i * 5] + (width / 2.0f));
            vertices[i * 5 + 1] = (vertices[i * 5 + 1] + (height / 2.0f));
            Vector2 uv = getUV(region, vertices[i * 5], vertices[i * 5 + 1]);
            vertices[i * 5 + 3] = uv.x;
            vertices[i * 5 + 4] = uv.y;
            
        }
        
        
    }
    
 

    /**
     * 计算纹理坐标
     * @author whs  
     * @date 2015-11-25 下午4:24:03
     * @param region
     * @param x
     * @param y
     * @return
     * @Description:
     */
    private Vector2 getUV(TextureRegion region, float x, float y) {
        float u = region.getU();
        float v = region.getV();
        float u2 = region.getU2();
        float v2 = region.getV2();
        return new Vector2(u + x / region.getRegionWidth() * (u2 - u), v2 - y / region.getRegionHeight() * (v2 - v));
    }

    public void draw(Batch batch, float parentAlpha) {
    	batch.end();
    	batch.begin();
    	batch.draw(background, this.getX(), this.getY());       
        batch.end();
        batch.begin();
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glDepthMask(false);
        int n = 3 + (int) (8F * this.percent);
        if (n > 10) {
            n = 10;
        }
        float vertices[] = new float[n * 5];
        float x = getX();
        float y = getY();
        float fColor = getColor().toFloatBits();
        int idx = 0;;

        for (int j = 0; j < n - 1; j++) {
            vertices[0 + j * 5] = x + this.vertices[j * 5];
            vertices[1 + j * 5] = y + this.vertices[1 + j * 5];
            vertices[2 + j * 5] = fColor;
            vertices[3 + j * 5] = this.vertices[3 + j * 5];
            vertices[4 + j * 5] = this.vertices[4 + j * 5];
            idx += 5;
        }
        if (this.last != null) {
            vertices[idx + 0] = x + this.last.x;
            vertices[idx + 1] = y + this.last.y;
            vertices[idx + 2] = fColor;
            Vector2 uv = getUV(this.region, this.last.x, this.last.y);
            vertices[idx + 3] = uv.x;
            vertices[idx + 4] = uv.y;

        }
        this.region.getTexture().bind();
        this.mesh.setVertices(vertices, 0, vertices.length);
        this.mesh.render(this.shader, GL20.GL_TRIANGLE_FAN, 0, n);
        Gdx.gl.glDepthMask(true);

    }

    public void setPercent(float percent) {
        if (percent < 0.0F) {
            percent = 0.0F;
        } else if (percent >= 1.0F) {
            percent = 1.0F;
        }
        int i = 1;
        int k;
        this.percent =  percent;
        int n =  (int) (8F * this.percent);
        float pp = ( this.percent - 0.125F * n) / 0.125F;
        if (n + 1 > 8) {
            k = i;
        } else {
            k = n + 1;
        }
        if (k + 1 <= 8) {
            i = k + 1;
        }
        last = new Vector2(vertices[k * 5] + pp * (vertices[i * 5] - vertices[k * 5]), vertices[1 + k * 5] + pp
            * (vertices[1 + i * 5] - vertices[1 + k * 5]));
    }
    public float getPercent(){
    	return percent;
    }
}

