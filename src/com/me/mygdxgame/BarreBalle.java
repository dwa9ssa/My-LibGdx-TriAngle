package com.me.mygdxgame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.me.mygdxgame.TestLibGdx.MyGestureListener;

public class BarreBalle implements ApplicationListener {

	Pixmap pixelsBarre, pixelsBalle, pixelsGameOver; // , pixelsBalle4;
	Texture textureBarre, textureBalle, textureGameOver; // , textureBalle4;

	Texture background;

	// TextureRegion[] balleFrames; // #5
	// TextureRegion currentFrame; // #7

	// private static final int FRAME_COLS = 2; // #1
	// private static final int FRAME_ROWS = 2; // #2

	SpriteBatch spriteBatch; // #6

	boolean isGameOver = false;

	float w; // = Gdx.graphics.getWidth();
	float h; // = Gdx.graphics.getHeight();

	float xBarre = 0;
	float yBarre = 0;

	float xBalle = 0;
	float yBalle = 0;

	float xBalleCoefDeplacement = 15;
	float yBalleCoefDeplacement = 15;

	float marge = 200;

	boolean gameOverFirstUse = false;
	Sound soundGameOver;
	Sound soundClash;
//    CharSequence str = "Hello World!";
//	BitmapFont font;

//	SpriteBatch spriteFont;
//	Matrix4 mx4Font;
	
	@Override
	public void create() {

		Gdx.input
				.setInputProcessor(new GestureDetector(new MyGestureListener()));

		spriteBatch = new SpriteBatch(); // #12

		soundClash = Gdx.audio.newSound(Gdx.files.internal("data/clash.mp3"));
		soundGameOver = Gdx.audio.newSound(Gdx.files.internal("data/gameover.mp3"));
		
		background = new Texture("data/back.png");
//		background.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);

		pixelsBarre = new Pixmap(Gdx.files.internal("data/barre.png"));
		textureBarre = new Texture(pixelsBarre);

		pixelsBalle = new Pixmap(Gdx.files.internal("data/balle.png"));
		textureBalle = new Texture(pixelsBalle);

		pixelsGameOver = new Pixmap(Gdx.files.internal("data/gameover.png"));
		textureGameOver = new Texture(pixelsGameOver);
		
	    spriteBatch = new SpriteBatch();
	    
//	    spriteFont = new SpriteBatch();
//	    font = new BitmapFont();
//	    mx4Font = new Matrix4();

		// pixelsBalle4 = new Pixmap(Gdx.files.internal("data/balleAnnim.png"));
		// textureBalle4 = new Texture(pixelsBalle4);
		//
		// TextureRegion[][] tmp = TextureRegion.split(textureBalle4,
		// textureBalle4.getWidth() / FRAME_COLS, textureBalle4.getHeight()
		// / FRAME_ROWS); // #10
		//
		// balleFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		// int index = 0;
		// for (int i = 0; i < FRAME_ROWS; i++) {
		// for (int j = 0; j < FRAME_COLS; j++) {
		// balleFrames[index++] = tmp[i][j];
		// }
		// }

		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();

		System.out.println(w + " " + h);

		xBarre = w - 8 - marge;

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	float stateTime;
	int tilesW, tilesH;

	@Override
	public void render() {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT); // #14
		// TODO Auto-generated method stub

		// System.out.println(yBalle + " " + yBarre);

		spriteBatch.begin();
		spriteBatch.draw(background, 0, 0);

		spriteBatch.draw(background, 0, 0, background.getWidth() * tilesW,
				background.getHeight() * tilesH, 0, tilesH, tilesW, 0);
		
		if (isGameOver
				|| ((w - (64 + marge) < xBalle) && !(yBalle - 32 <= (yBarre + 64) && (yBarre - 64) <= yBalle - 32))) {
			isGameOver = true;
			spriteBatch.draw(textureGameOver, 0, 0); // #17
		} 
//		else {

			if ((w - (64 + marge) < xBalle) || xBalle < 0) {
				xBalleCoefDeplacement = -1 * xBalleCoefDeplacement;
				if (!isGameOver) {soundClash.play();}
				else if (!gameOverFirstUse) {soundGameOver.play(); gameOverFirstUse = true;}
//				if ((w - (64 + marge)) < xBalle) {
//					System.out.println(yBalle + " " + (yBarre + 64) + " " + (yBarre - 64));
//				}
			}

			if (h - 64 < yBalle || yBalle < 0) {
				yBalleCoefDeplacement = -1 * yBalleCoefDeplacement;
				if (!isGameOver) {soundClash.play();}
				else if (!gameOverFirstUse) {soundGameOver.play(); gameOverFirstUse = true;}
			}
			

			if (!isGameOver) {
				xBalle += xBalleCoefDeplacement;
				yBalle += yBalleCoefDeplacement;
			}

			spriteBatch.draw(textureBarre, xBarre, yBarre); // #17
			spriteBatch.draw(textureBalle, xBalle, yBalle); // #17

			// stateTime += Gdx.graphics.getDeltaTime();
			// currentFrame = balleAnimation.getKeyFrame(6.4F, true); // #16
			// currentFrame = balleFrames[1];
			// spriteBatch.draw(currentFrame, 0, 0); // #17
//		}

//		font.draw(spriteBatch, str, 10, 10);
		spriteBatch.end();
		
//		mx4Font.setToRotation(new Vector3(200, 200, 0), 180);
//		spriteFont.setTransformMatrix(mx4Font);
//		spriteFont.begin();
////		font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
//		font.draw(spriteFont, "The quick brown fox jumped over the lazy dog", 10, 10);
//		spriteFont.end();
	}

	@Override
	public void resize(int width, int height) {
		tilesW = width / background.getWidth() + 1;
		tilesH = height / background.getHeight() + 1;
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
			// x = arg0 / (w / 2) - 1;
			if (!isGameOver)
			yBarre = h - arg1 - 64;// -(arg1 / (h / 2) - 1);
			// sx = -tx;
			// sy = -ty;
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
			// tcoefSize = (arg1 - arg0) / (w / 4);
			// scoefSize = tcoefSize;
			return false;
		}

	}
}
