package com.me.mygdxgame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;

public class TestImage implements ApplicationListener {
	private Mesh mesh;
	private Mesh mesh2;
	private Texture texture;
	private Texture texture2;

	@Override
	public void create() {
	    if (mesh == null) {

	        mesh2 = new Mesh(true, 3, 3, 
	                new VertexAttribute(Usage.Position, 3, "a_position"),
//	                new VertexAttribute(Usage.ColorPacked, 4, "a_color"),
	                new VertexAttribute(Usage.TextureCoordinates, 2, "a_texCoords"));

	        mesh2.setVertices(new float[] { -1f, -1f, 0, 
//	        		Color.toFloatBits(255, 0, 0, 255),
	        		0, 1,
                   1f, -1f, 0, 
//                   Color.toFloatBits(0, 255, 0, 255), 
                   1, 1,
                   0, 1f, 0, 
//                   Color.toFloatBits(0, 0, 255, 255), 
                   0.5f, 0 });
	                                       
	        mesh2.setIndices(new short[] { 0, 1, 2 });
	    	
	        mesh = new Mesh(true, 3, 3, 
	                new VertexAttribute(Usage.Position, 3, "a_position"),
//	                new VertexAttribute(Usage.ColorPacked, 4, "a_color"),
	                new VertexAttribute(Usage.TextureCoordinates, 2, "a_texCoords"));

	        mesh.setVertices(new float[] { -0.5f, -0.5f, 0, 
//	        		Color.toFloatBits(255, 0, 0, 255),
	        		0, 1,
                   0.5f, -0.5f, 0, 
//                   Color.toFloatBits(0, 255, 0, 255), 
                   1, 1,
                   0, 0.5f, 0, 
//                   Color.toFloatBits(0, 0, 255, 255), 
                   0.5f, 0 });
	                                       
	        mesh.setIndices(new short[] { 0, 1, 2 });


	        FileHandle imageFileHandle = Gdx.files.internal("data/badlogic.jpg"); 
	        texture = new Texture(imageFileHandle);
	        
	        FileHandle imageFileHandle2 = Gdx.files.internal("data/badlogicc.jpg"); 
	        texture2 = new Texture(imageFileHandle2);
	    }
	}

	@Override
	public void render() {
	    Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	    Gdx.graphics.getGL10().glEnable(GL10.GL_TEXTURE_2D);
	    texture2.bind();
	    mesh2.render(GL10.GL_TRIANGLES, 0, 3);
	    texture.bind();
	    mesh.render(GL10.GL_TRIANGLES, 0, 3);
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
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
}
