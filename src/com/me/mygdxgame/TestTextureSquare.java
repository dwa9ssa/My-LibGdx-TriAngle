package com.me.mygdxgame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TestTextureSquare implements ApplicationListener {

	
	Texture textureMesh;
	Mesh squareMesh;
	Texture textureSpriteBatch;
	SpriteBatch spriteBatch;
	OrthographicCamera camera;

	
	private float sx = 0.5f;
	private float sy = 0;
	private float sx1 = -0.25f;
	private float sy1 = -0.25f;
	private float sx2 = 0.25f;
	private float sy2 = -0.25f;
	private float sx3 = -0.25f;
	private float sy3 = 0.25f;
	private float sx4 = 0.25f;
	private float sy4 = 0.25f;
	private float scoef = 1f;
	private float scoefSize = 1;
	private float tcoefSize = 1;
	
	
	@Override
	public void create() {
		textureMesh = new Texture(Gdx.files.internal("data/badlogic.png"));
		
		textureSpriteBatch = new Texture(
				Gdx.files.internal("data/badlogicc.png"));

		squareMesh = new Mesh(true, 4, 4, new VertexAttribute(Usage.Position,
				3, "a_position"), new VertexAttribute(Usage.TextureCoordinates,
				2, "a_texCoords"));

//		squareMesh.setVertices(new float[] {0,
//				0,
//				0,
//				0,
//				1, // lower left
//				0 + 0, 0,
//				0,
//				1,
//				1, // lower right
//				0, 0 + 0, 0,
//				0,
//				0, // upper left
//				0 + 0, 0 + 0,
//				0, 1, 0 }); // upper right
//		
//		
//
//		squareMesh = new Mesh(true, 4, 4, 
//				new VertexAttribute(Usage.Position, 3, "b_position"),
//				new VertexAttribute(Usage.ColorPacked, 4, "a_color"));

		squareMesh.setVertices(new float[] { 
				(tcoefSize * sx1) + sx, (tcoefSize * sy1) + sy, scoef, Color.toFloatBits(255, 0, 0, 255), 0, 1,// point BL
				(tcoefSize * sx2) + sx, (tcoefSize * sy2) + sy, scoef, Color.toFloatBits(0, 255, 0, 255), 1, 1,// point BR
				(tcoefSize * sx3) + sx, (tcoefSize * sy3) + sy, scoef, Color.toFloatBits(0, 0, 255, 255), 0, 0, // point TL
				(tcoefSize * sx4) + sx, (tcoefSize * sy4) + sy, scoef, Color.toFloatBits(255, 255, 255, 255), 1, 0 // point TR
				});
		
		squareMesh.setIndices(new short[] { 0, 1, 2, 3 });

//		squareMesh.setIndices(new short[] { 0, 1, 2, 3 });

		spriteBatch = new SpriteBatch();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {
	    GLCommon gl = Gdx.gl;

	    camera.update();
	    camera.apply(Gdx.gl10);
	    spriteBatch.setProjectionMatrix(camera.combined);

	    gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
	    gl.glEnable(GL10.GL_DEPTH_TEST);

	    gl.glEnable(GL10.GL_TEXTURE_2D);
	    textureMesh.bind();
	    squareMesh.render(GL10.GL_TRIANGLE_STRIP, 0, 4);

	    spriteBatch.begin();
	    spriteBatch.draw(textureSpriteBatch, -10, 0);
	    spriteBatch.end();  
	}

	@Override
	public void resize(int width, int height) {
//        float aspectRatio = (float) width / (float) height;
//        camera = new OrthographicCamera(cameraViewHeight * aspectRatio, cameraViewHeight);
	}

	@Override
	public void resume() {

	}

}
