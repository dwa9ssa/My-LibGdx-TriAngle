package com.example.libgdx.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.audio.Sound;
import com.example.libgdx.model.Balle;
import com.example.libgdx.model.Barre;
import com.example.libgdx.model.Brique;
import com.example.libgdx.model.Image;

public class CasseBrique {

	private Balle balle;
	private Barre barre;

	private Image imageGameOver;
	private Image imageBackground;
	private List<Brique> listImageBrique;

	private Sound soundGameOver;
	private Sound soundClash;

	private List<Image> listImage;
	private List<Sound> listSound;

	private float w;
	private float h;

	private float marge = 200;

	private boolean gameOverFirstUse = false;
	private boolean isGameOver = false;

	public CasseBrique(Balle balle, Barre barre, Image imageGameOver,
			Image imageBackground, List<Brique> listImageBrique, Sound soundGameOver, Sound soundClash,
			float w, float h, float marge, boolean gameOverFirstUse,
			boolean isGameOver) {
		this.balle = balle;
		this.barre = barre;
		this.imageGameOver = imageGameOver;
		this.imageBackground = imageBackground;
		this.listImageBrique = listImageBrique;
		this.soundGameOver = soundGameOver;
		this.soundClash = soundClash;
		this.w = w;
		this.h = h;
		this.marge = marge;
		this.gameOverFirstUse = gameOverFirstUse;
		this.isGameOver = isGameOver;

		barre.setX(w - barre.getWidth() - marge);
		
		this.getBalle().setX(this.getBarre().getX() - this.getBalle().getHeight());
//		this.getBalle().setY(this.getBalle().getY());

		Brique brique = new Brique("data/brique.png");
//		brique.setX(this.getW() - brique.getWidth());
		brique.setY(this.getH() - brique.getHeight());
		
		Brique brique1 = new Brique("data/brique.png");
//		brique1.setX(this.getW() - brique.getWidth());
		brique1.setY((this.getH() / 2) - brique1.getHeight());
		
		List<Brique> listBrique = new ArrayList<Brique>();
		
		listBrique.add(new Brique("data/brique.png"));

		listBrique.add(brique);
		listBrique.add(brique1);
		
		this.setListImageBrique(listBrique);
	}

	public boolean isBalleFail() {
		return ((this.getW() - (this.getBalle().getWidth() + this.getMarge()) < this
				.getBalle().getX()) && !(this.getBalle().getY()
				- (this.getBalle().getHeight() / 2) <= (this.getBarre().getY() + this
				.getBalle().getHeight()) && (this.getBarre().getY() - this
				.getBalle().getHeight()) <= this.getBalle().getY()
				- (this.getBalle().getHeight() / 2)));
	}

	public boolean isTouchTheWallW() {
		return ((this.getW() - (this.getBalle().getWidth() + this.getMarge()) < this
				.getBalle().getX()) || this.getBalle().getX() < 0);
	}

	public boolean isTouchTheWallH() {
		return (this.getH() - this.getBalle().getHeight() < this.getBalle()
				.getY() || this.getBalle().getY() < 0);
	}

	public void renderGame() {
		listImage = new ArrayList<Image>();
		listSound = new ArrayList<Sound>();
		
		listImage.add(this.getImageBackground());

		this.getImageBackground().setX(0);
		this.getImageBackground().setY(0);
		
		if (this.isGameOver() || this.isBalleFail()) {
			this.setGameOver(true);
		}

		if (this.isTouchTheWallW()) {
			this.getBalle().setxBalleCoefDeplacement(-1
					* this.getBalle().getxBalleCoefDeplacement());
			if (!this.isGameOver()) {
				listSound.add(this.getSoundClash());
			} else if (!this.isGameOverFirstUse()) {
				listSound.add(this.getSoundGameOver());
				this.setGameOverFirstUse(true);
			}
		}

		if (this.isTouchTheWallH()) {
			this.getBalle().setyBalleCoefDeplacement(-1
					* this.getBalle().getyBalleCoefDeplacement());
			if (!this.isGameOver()) {
				listSound.add(this.getSoundClash());
			} else if (!this.isGameOverFirstUse()) {
				listSound.add(this.getSoundGameOver());
				this.setGameOverFirstUse(true);
			}
		}

		listImage.add(this.getBarre()); // #17
		listImage.add(this.getBalle()); // #17
		
		if (!this.isGameOver()) {
			this.getBalle().setX(this.getBalle().getX() + this.getBalle().getxBalleCoefDeplacement());
			this.getBalle().setY(this.getBalle().getY() + this.getBalle().getyBalleCoefDeplacement());

			
			for (Brique brique : listImageBrique) {
//				System.err.println("Balle : " + this.getBalle());
				if (brique.isVisible() && !balle.isCollision(brique)) {
//					System.err.println("Brique : " + image);
					listImage.add(brique);
				} else {
					brique.setVisible(false);
				}
			}
		}
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

	public List<Brique> getListImageBrique() {
		return listImageBrique;
	}

	public void setListImageBrique(List<Brique> listImageBrique) {
		this.listImageBrique = listImageBrique;
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

	public List<Image> getListImage() {
		return listImage;
	}

	public void setListImage(List<Image> listImage) {
		this.listImage = listImage;
	}

	public List<Sound> getListSound() {
		return listSound;
	}

	public void setListSound(List<Sound> listSound) {
		this.listSound = listSound;
	}
}
