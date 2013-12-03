package com.me.mygdxgame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.me.mygdxgame.TestLibGdx.MyGestureListener;

public class Animator implements ApplicationListener {

	private static final int FRAME_COLS = 6; // #1
	private static final int FRAME_ROWS = 5; // #2

	Animation walkAnimation; // #3
	Texture walkSheet, walkSheetCar; // #4
	TextureRegion[] walkFrames; // #5
	SpriteBatch spriteBatch; // #6
	TextureRegion currentFrame; // #7

	float stateTime; // #8

	Pixmap pixels;

	float x; // = Gdx.graphics.getWidth();
	float y; // = Gdx.graphics.getHeight();
	float xCar = x;
	float yRunner = 0;
	boolean isJump = false;

	@Override
	public void create() {

		Gdx.input
				.setInputProcessor(new GestureDetector(new MyGestureListener()));

		walkSheet = new Texture(Gdx.files.internal("data/animation_sheet.png")); // #9
		TextureRegion[][] tmp = TextureRegion.split(walkSheet,
				walkSheet.getWidth() / FRAME_COLS, walkSheet.getHeight()
						/ FRAME_ROWS); // #10
		walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				walkFrames[index++] = tmp[i][j];
			}
		}
		walkAnimation = new Animation(0.025f, walkFrames); // #11
		spriteBatch = new SpriteBatch(); // #12
		stateTime = 0f; // #13

		pixels = new Pixmap(Gdx.files.internal("data/badlogic.png"));
		walkSheetCar = new Texture(pixels);

		x = Gdx.graphics.getWidth();
		y = Gdx.graphics.getHeight();

		System.out.println(x + ", " + y);
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT); // #14
		stateTime += Gdx.graphics.getDeltaTime(); // #15
		currentFrame = walkAnimation.getKeyFrame(stateTime, true); // #16
		spriteBatch.begin();
		if (xCar <= -100) {
			xCar = x;
		}

		if (isJump == false && 0 < yRunner) {
			yRunner-=10;
		}

		spriteBatch.draw(currentFrame, 100, yRunner);
		spriteBatch.draw(walkSheetCar, xCar, -60); // #17
		spriteBatch.end();

		// if (nbrRate == 100) {
		// nbrRate = 0;
		xCar -= 10;
		// }

		// pixels.dispose();
		// walkSheetCar.draw(pixels, 200, 0);
		// walkSheetCar.
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
			isJump = true;
			if (yRunner <= y - 100) {
				yRunner+=10;
			}
			return false;
		}

		@Override
		public boolean panStop(float arg0, float arg1, int arg2, int arg3) {
			isJump = false;
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
			// tcoefSize = (arg1 - arg0) / (w / 4);
			// scoefSize = tcoefSize;
			return false;
		}

	}
}