package com.me.mygdxgame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;

public class TestLibGdx implements ApplicationListener {

	private Mesh triangleMesh; 
	private Mesh squareMesh;
	// private OrthographicCamera camera;
//	private Texture texture;
	private Texture texture2;

	private float w;
	private float h;
	
	private float tx = -0.5f;
	private float ty = 0;
	private float tx1 = -0.25f;
	private float ty1 = -0.25f;
	private float tx2 = 0.25f;
	private float ty2 = -0.25f;
	private float tx3 = 0f;
	private float ty3 = 0.25f;
	private float tcoef = 1f;
	private float tcoefSize = 1;
	
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

	@Override
	public void create() {
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();

		// camera = new OrthographicCamera(1, h/w);

		Gdx.input
				.setInputProcessor(new GestureDetector(new MyGestureListener()));

		// squareMesh.setVertices(new float[] { 0.5f, -1.0f, -6.0f,// point BL
		// 2.5f, -1.0f, -6.0f,// point BR
		// 0.5f, 1.0f, -6.0f, // point TL
		// 2.5f, 1.0f, -6.0f // point TR
		// });
		// squareMesh.setIndices(new short[] { 0, 1, 2, 3 });

		// if (mesh == null) {
		// mesh = new Mesh(true, 3, 3,
		// new VertexAttribute(Usage.Position, 3, "a_position"));
		//
		// mesh.setVertices(new float[] { -1f, -1f, 0,
		// 1f, -1f, 0,
		// -1f, 1f, 0 });
		// mesh.setIndices(new short[] { 0, 1, 2 });
		// }
		//
		// if (mesh1 == null) {
		// mesh1 = new Mesh(true, 3, 3,
		// new VertexAttribute(Usage.Position, 3, "a_position"));
		//
		// mesh1.setVertices(new float[] { -1f, 1f, 0,
		// 1f, -1f, 0,
		// 1f, 1f, 0 });
		// mesh1.setIndices(new short[] { 0, 1, 2 });
		// }
	}

	@Override
	public void dispose() {
	}

	@Override
	public void pause() {
	}

	private boolean drawGLScene() {
		squareMesh = new Mesh(true, 4, 4, 
				new VertexAttribute(Usage.Position, 3, "b_position")
//		,
//				new VertexAttribute(Usage.ColorPacked, 4, "a_color")
		);

		squareMesh.setVertices(new float[] { 
				(tcoefSize * sx1) + sx, (tcoefSize * sy1) + sy, scoef, //Color.toFloatBits(255, 0, 0, 255), //0, 1,// point BL
				(tcoefSize * sx2) + sx, (tcoefSize * sy2) + sy, scoef, //Color.toFloatBits(0, 255, 0, 255), //1, 1,// point BR
				(tcoefSize * sx3) + sx, (tcoefSize * sy3) + sy, scoef, //Color.toFloatBits(0, 0, 255, 255), //0, 0, // point TL
				(tcoefSize * sx4) + sx, (tcoefSize * sy4) + sy, scoef, //Color.toFloatBits(255, 255, 255, 255), //1, 0 // point TR
				});
		
		squareMesh.setIndices(new short[] { 0, 1, 2, 3 });

//        FileHandle imageFileHandle = Gdx.files.internal("data/badlogic.jpg"); 
//        texture = new Texture(imageFileHandle);
//	    texture.bind();
        
		squareMesh.render(GL10.GL_TRIANGLE_STRIP, 0, 4);

		triangleMesh = new Mesh(true, 3, 3, 
				new VertexAttribute(Usage.Position, 3, "a_position"), 
				new VertexAttribute(Usage.ColorPacked, 4, "a_color"),
                new VertexAttribute(Usage.TextureCoordinates, 2, "a_texCoords"));

		triangleMesh.setVertices(new float[] { //
				(tcoefSize * tx1) + tx, (tcoefSize * ty1) + ty, tcoef, Color.toFloatBits(255, 0, 0, 255), 0, 1,
				(tcoefSize * tx2) + tx, (tcoefSize * ty2) + ty, tcoef, Color.toFloatBits(0, 255, 0, 255), 1, 1,
				(tcoefSize * tx3) + tx, (tcoefSize * ty3) + ty, tcoef, Color.toFloatBits(0, 0, 255, 255), 0.5f, 0 });

		triangleMesh.setIndices(new short[] { 0, 1, 2 });

        FileHandle imageFileHandle2 = Gdx.files.internal("data/badlogicc.jpg"); 
        texture2 = new Texture(imageFileHandle2);
	    texture2.bind();
	    
		triangleMesh.render(GL10.GL_TRIANGLES, 0, 3);

	    Gdx.graphics.getGL10().glEnable(GL10.GL_TEXTURE_2D);
	    texture2.bind();
	    
		return true;
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		drawGLScene();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void resume() {
	}

	class MyGestureListener implements GestureListener {

		@Override
		public boolean fling(float arg0, float arg1, int arg2) {
			return false;
		}

		@Override
		public boolean longPress(float arg0, float arg1) {
			return false;
		}

		@Override
		public boolean pan(float arg0, float arg1, float arg2, float arg3) {
			tx = arg0 / (w / 2) - 1;
			ty = -(arg1 / (h / 2) - 1);
			sx = -tx;
			sy = -ty;
			return false;
		}

		@Override
		public boolean panStop(float arg0, float arg1, int arg2, int arg3) {
			return false;
		}

		@Override
		public boolean pinch(Vector2 arg0, Vector2 arg1, Vector2 arg2,
				Vector2 arg3) {
			return false;
		}

		@Override
		public boolean tap(float arg0, float arg1, int arg2, int arg3) {
			return false;
		}

		@Override
		public boolean touchDown(float arg0, float arg1, int arg2, int arg3) {
			return false;
		}

		@Override
		public boolean zoom(float arg0, float arg1) {
			tcoefSize = (arg1 - arg0) / (w / 4);
			scoefSize = tcoefSize;
			return false;
		}

	}
}
