package com.me.mygdxgame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.example.libgdx.game.CasseBrique;
import com.example.libgdx.model.Balle;
import com.example.libgdx.model.Barre;
import com.example.libgdx.model.Image;

public class BarreBalle implements ApplicationListener {
	// Image imageGameOver, imageBackground;
	// Barre barre;
	// Balle balle;
	//
	SpriteBatch spriteBatch; // #6

	CasseBrique casseBrique;

	//
	// boolean isGameOver = false;
	//
	// float w;
	// float h;
	//
	// float marge = 200;
	//
	// boolean gameOverFirstUse = false;
	// Sound soundGameOver;
	// Sound soundClash;

	@Override
	public void create() {

		Gdx.input
				.setInputProcessor(new GestureDetector(new MyGestureListener()));

		spriteBatch = new SpriteBatch(); // #12

//		soundClash = Gdx.audio.newSound(Gdx.files.internal("data/clash.mp3"));
//		soundGameOver = Gdx.audio.newSound(Gdx.files.internal("data/gameover.mp3"));
//
//		imageBackground = new Image("data/back.png");
//		barre = new Barre("data/barre.png");
//		balle = new Balle("data/balle.png");
//		imageGameOver = new Image("data/gameover.png");
//
//		spriteBatch = new SpriteBatch();
//
//		w = Gdx.graphics.getWidth();
//		h = Gdx.graphics.getHeight();
//
//		System.out.println(w + " " + h);
//		System.out.println("balle.getHeight() = " + balle.getHeight() + "; balle.getWidth() = " + balle.getWidth());
//		System.out.println("barre.getHeight() = " + barre.getHeight() + "; barre.getWidth() = " + barre.getWidth());
//		System.out.println(" (balle.getHeight() / 2) = " + (balle.getHeight() / 2));
//		barre.setX(w - barre.getWidth() - marge);
		

		casseBrique = new CasseBrique(new Balle("data/balle.png"), new Barre("data/barre.png"), new Image("data/gameover.png"), new Image("data/back.png"), Gdx.audio.newSound(Gdx.files.internal("data/gameover.mp3")), Gdx.audio.newSound(Gdx.files.internal("data/clash.mp3")), Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 200, false, false);
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
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

		spriteBatch.begin();
		spriteBatch.draw(casseBrique.getImageBackground().getTexture(), 0, 0);

		if (casseBrique.isGameOver()
				|| ((casseBrique.getW() - (casseBrique.getBalle().getWidth() + casseBrique.getMarge()) < casseBrique.getBalle().getX()) && !(casseBrique.getBalle()
						.getY() - (casseBrique.getBalle().getHeight() / 2) <= (casseBrique.getBarre().getY() + casseBrique.getBalle()
						.getHeight()) && (casseBrique.getBarre().getY() - casseBrique.getBalle().getHeight()) <= casseBrique.getBalle()
						.getY() - (casseBrique.getBalle().getHeight() / 2)))) {
			casseBrique.setGameOver(true);
			spriteBatch.draw(casseBrique.getImageBackground().getTexture(), 0, 0); // #17
		}

		if ((casseBrique.getW() - (casseBrique.getBalle().getWidth() + casseBrique.getMarge()) < casseBrique.getBalle().getX()) || casseBrique.getBalle().getX() < 0) {
			casseBrique.getBalle().setxBalleCoefDeplacement(-1
					* casseBrique.getBalle().getxBalleCoefDeplacement());
			if (!casseBrique.isGameOver()) {
				casseBrique.getSoundClash().play();
			} else if (!casseBrique.isGameOverFirstUse()) {
				casseBrique.getSoundGameOver().play();
				casseBrique.setGameOverFirstUse(true);
			}
		}

		if (casseBrique.getH() - casseBrique.getBalle().getHeight() < casseBrique.getBalle().getY() || casseBrique.getBalle().getY() < 0) {
			casseBrique.getBalle().setyBalleCoefDeplacement(-1
					* casseBrique.getBalle().getyBalleCoefDeplacement());
			if (!casseBrique.isGameOver()) {
				casseBrique.getSoundClash().play();
			} else if (!casseBrique.isGameOverFirstUse()) {
				casseBrique.getSoundGameOver().play();
				casseBrique.setGameOverFirstUse(true);
			}
		}

		if (!casseBrique.isGameOver()) {
			casseBrique.getBalle().setX(casseBrique.getBalle().getX() + casseBrique.getBalle().getxBalleCoefDeplacement());
			casseBrique.getBalle().setY(casseBrique.getBalle().getY() + casseBrique.getBalle().getyBalleCoefDeplacement());
		}

		spriteBatch.draw(casseBrique.getBarre().getTexture(), casseBrique.getBarre().getX(), casseBrique.getBarre().getY()); // #17
		spriteBatch.draw(casseBrique.getBalle().getTexture(), casseBrique.getBalle().getX(), casseBrique.getBalle().getY()); // #17

		spriteBatch.end();
	}

	@Override
	public void resize(int width, int height) {
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
			if (!casseBrique.isGameOver()) {
				casseBrique.getBarre().move(casseBrique.getH() - arg1);
			}

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
