package com.me.mygdxgame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;

public class TestLibGdx implements ApplicationListener {

	private Mesh triangleMesh; // , squareMesh;
	// private OrthographicCamera camera;

	private float x = 0;
	private float y = 0;
	private float x1 = -0.25f;
	private float y1 = -0.25f;
	private float x2 = 0.25f;
	private float y2 = -0.25f;
	private float x3 = 0f;
	private float y3 = 0.25f;
	private float coef = 1f;
	// private float myccoefX = 1;
	// private float myccoefY = 1;
	private float w;
	private float h;
	private float coefSize = 1;

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
		triangleMesh = new Mesh(true, 3, 3, new VertexAttribute(Usage.Position,
				3, "a_position"));
		// squareMesh = new Mesh(true, 4, 4, new VertexAttribute(Usage.Position,
		// 3, "b_position"));
		triangleMesh.setVertices(new float[] { //
				(coefSize * x1) + x, (coefSize * y1) + y, coef,
				(coefSize * x2) + x, (coefSize * y2) + y, coef,
				(coefSize * x3) + x, (coefSize * y3) + y, coef });

		triangleMesh.setIndices(new short[] { 0, 1, 2 });

		triangleMesh.render(GL10.GL_TRIANGLES, 0, 3);
		// squareMesh.render(GL10.GL_TRIANGLE_STRIP, 0, 4);
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
			x = arg0 / (w / 2) - 1;
			y = -(arg1 / (h / 2) - 1);
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
			coefSize = (arg1 - arg0) / (w / 4);
			return false;
		}

	}
}
