package com.example.libgdx.game;

import com.badlogic.gdx.audio.Sound;
import com.example.libgdx.model.Balle;
import com.example.libgdx.model.Barre;
import com.example.libgdx.model.Image;

public class CasseBrique {

	private Balle balle;
	private Barre barre;

	private Image imageGameOver;
	private Image imageBackground;

	private Sound soundGameOver;
	private Sound soundClash;

	private float w;
	private float h;

	private float marge = 200;

	private boolean gameOverFirstUse = false;
	private boolean isGameOver = false;

	public CasseBrique(Balle balle, Barre barre,
	Image imageGameOver, Image imageBackground,
	Sound soundGameOver, Sound soundClash,
	float w, float h,
	float marge,
	boolean gameOverFirstUse, boolean isGameOver) {
		this.balle = balle;
		this.barre = barre;
		this.imageGameOver = imageGameOver;
		this.imageBackground = imageBackground;
		this.soundGameOver = soundGameOver;
		this.soundClash = soundClash;
		this.w = w;
		this.h = h;
		this.marge = marge;
		this.gameOverFirstUse = gameOverFirstUse;
		this.isGameOver = isGameOver;
		
		barre.setX(w - barre.getWidth() - marge);
	}

	public Balle getBalle() {
		return balle;
	}

	public void setBalle(Balle balle) {
		this.balle = balle;
	}

	public Barre getBarre() {
		return barre;
	}

	public void setBarre(Barre barre) {
		this.barre = barre;
	}

	public Image getImageGameOver() {
		return imageGameOver;
	}

	public void setImageGameOver(Image imageGameOver) {
		this.imageGameOver = imageGameOver;
	}

	public Image getImageBackground() {
		return imageBackground;
	}

	public void setImageBackground(Image imageBackground) {
		this.imageBackground = imageBackground;
	}

	public Sound getSoundGameOver() {
		return soundGameOver;
	}

	public void setSoundGameOver(Sound soundGameOver) {
		this.soundGameOver = soundGameOver;
	}

	public Sound getSoundClash() {
		return soundClash;
	}

	public void setSoundClash(Sound soundClash) {
		this.soundClash = soundClash;
	}

	public float getW() {
		return w;
	}

	public void setW(float w) {
		this.w = w;
	}

	public float getH() {
		return h;
	}

	public void setH(float h) {
		this.h = h;
	}

	public float getMarge() {
		return marge;
	}

	public void setMarge(float marge) {
		this.marge = marge;
	}

	public boolean isGameOverFirstUse() {
		return gameOverFirstUse;
	}

	public void setGameOverFirstUse(boolean gameOverFirstUse) {
		this.gameOverFirstUse = gameOverFirstUse;
	}

	public boolean isGameOver() {
		return isGameOver;
	}

	public void setGameOver(boolean isGameOver) {
		this.isGameOver = isGameOver;
	}
}
