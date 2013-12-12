package com.me.mygdxgame;

import java.util.ArrayList;
import java.util.List;

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
import com.example.libgdx.model.Brique;
import com.example.libgdx.model.Image;

public class BarreBalle implements ApplicationListener {
	
	SpriteBatch spriteBatch; // #6

	CasseBrique casseBrique;

	@Override
	public void create() {
		Gdx.input
				.setInputProcessor(new GestureDetector(new MyGestureListener()));

		spriteBatch = new SpriteBatch(); // #12

		
		
		casseBrique = new CasseBrique(new Balle("data/balle.png"), new Barre("data/barre.png"), new Image("data/gameover.png"), new Image("data/back.png"), null, Gdx.audio.newSound(Gdx.files.internal("data/gameover.mp3")), Gdx.audio.newSound(Gdx.files.internal("data/clash.mp3")), Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 200, false, false);

		casseBrique.getBalle().setX(casseBrique.getBarre().getX() - casseBrique.getBalle().getHeight());
//		casseBrique.getBalle().setY(casseBrique.getBalle().getY());

		Brique brique = new Brique("data/brique.png");
//		brique.setX(casseBrique.getW() - brique.getWidth());
		brique.setY(casseBrique.getH() - brique.getHeight());
		
		Brique brique1 = new Brique("data/brique.png");
//		brique1.setX(casseBrique.getW() - brique.getWidth());
		brique1.setY((casseBrique.getH() / 2) - brique1.getHeight());
		
		List<Brique> listBrique = new ArrayList<Brique>();
		
		listBrique.add(new Brique("data/brique.png"));

		listBrique.add(brique);
		listBrique.add(brique1);
		
		casseBrique.setListImageBrique(listBrique);
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
		
		casseBrique.renderGame();

		for (Sound sound : casseBrique.getListSound()) {
			sound.play();
		}
		
		for (Image image : casseBrique.getListImage()) {
			spriteBatch.draw(image.getTexture(), image.getX(), image.getY());
		}

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
