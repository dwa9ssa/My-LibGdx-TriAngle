package com.example.libgdx.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.example.libgdx.model.Balle;
import com.example.libgdx.model.Barre;
import com.example.libgdx.model.Brique;
import com.example.libgdx.model.Image;

public class CasseBrique {

	private Texture balleTexture = new Texture("data/balle_32.png");
	private Texture barreTexture = new Texture("data/barre.png");
	private Texture briqueTexture = new Texture("data/brique.png");
	private Texture imageGameOverTexture = new Texture("data/gameover.png");
	private Texture imageBackgroundTexture = new Texture("data/back.png");
	
	private Balle balle = new Balle(balleTexture);
	private Barre barre = new Barre(barreTexture);
	private Image imageGameOver = new Image(imageGameOverTexture);
	private Image imageBackground = new Image(imageBackgroundTexture);
	
	private List<Brique> listImageBrique = new ArrayList<Brique>();

	private Sound soundGameOver;
	private Sound soundClash;

	private List<Image> listImage;
	private List<Sound> listSound;

	private float w;
	private float h;

	private float marge = 200;

	private boolean gameOverFirstUse = true;
	private boolean isGameOver = false;

	private int margeBrique = 10;
	private int nbrCols;
	private int nbrBrique = 90;
	
	private float xBalleCoefDeplacementTemp = 5;
	private float yBalleCoefDeplacementTemp = 5;

	public CasseBrique(Sound soundGameOver, Sound soundClash,
			float w, float h, float marge,
			boolean isGameOver) {
		this.soundGameOver = soundGameOver;
		this.soundClash = soundClash;
		this.w = w;
		this.h = h;
		this.marge = marge;
		this.isGameOver = isGameOver;

		barre.setX(w - barre.getWidth() - marge);
		
		this.getBalle().setX(this.getBarre().getX() - 2 * this.getBalle().getHeight());

		this.getBalle().setxBalleCoefDeplacement(xBalleCoefDeplacementTemp);
		this.getBalle().setyBalleCoefDeplacement(yBalleCoefDeplacementTemp);
		
		nbrCols = (int) h / (briqueTexture.getHeight() + margeBrique);
		
		int currentCols = 0;
		int reinitY = 1;
		
		for (int i = 0; i < nbrBrique; i++) {
			
			if ((i % nbrCols) == 0) {
				currentCols++;
				reinitY = 0;
			}
			
			Brique brique = new Brique(briqueTexture);
			brique.setY(margeBrique + ((i % nbrCols) * (brique.getHeight() + margeBrique)));
			brique.setX(currentCols * (brique.getHeight() + margeBrique));

			reinitY = 1;
			listImageBrique.add(brique);
		}

		this.getImageBackground().setX(0);
		this.getImageBackground().setY(0);
	}

	public boolean isBalleFail() {
		if (this.getBalle().getX() + this.getBalle().getHeight() >= this.getW()) {
			return true;
		}
		 
		return false;
	}

	public boolean isTouchTheWallW() {
		return ((this.getW() - (this.getBalle().getWidth()) < this.getBalle()
				.getX()) || this.getBalle().getX() < 0);
	}

	public boolean isTouchTheWallH() {
		return (this.getH() - this.getBalle().getHeight() < this.getBalle()
				.getY() || this.getBalle().getY() < 0);
	}
	
	public boolean isBalleHitBarre() {
//		if (this.getW() - (this.getBalle().getWidth() + this.getMarge()) < this.getBalle().getX()) {
			if (this.getBalle().isCollision(this.getBarre())) {
				return true;
			}
//		}
		
		return false;
	}

	public void renderGame() {
		listImage = new ArrayList<Image>();
		listSound = new ArrayList<Sound>();
		
		listImage.add(this.getImageBackground());
		
		if (this.isGameOver() || this.isBalleFail()) {
			this.setGameOver(true);
		}
		
		if (this.isBalleHitBarre()) {
			this.getBalle().setxBalleCoefDeplacement(-1
					* this.getBalle().getxBalleCoefDeplacement());

			System.out.println("Vitesse = " + this.getBarre().getVitesse());
			System.out.println("befor yBalleCoefDeplacement = " + this.getBalle().getyBalleCoefDeplacement());
			float vitesse = this.getBarre().getVitesse();
			if (vitesse > this.getBalle().getyBalleCoefDeplacement() / 2) {
				vitesse = yBalleCoefDeplacementTemp / 2;
			}
			this.getBalle().setyBalleCoefDeplacement(vitesse
					+ yBalleCoefDeplacementTemp);
			System.out.println("after yBalleCoefDeplacement = " + this.getBalle().getyBalleCoefDeplacement());
			
			if (!this.isGameOver()) {
				listSound.add(this.getSoundClash());
			}
		}

		if (this.isTouchTheWallW()) {
			this.getBalle().setxBalleCoefDeplacement(-1
					* this.getBalle().getxBalleCoefDeplacement());
			if (!this.isGameOver()) {
				listSound.add(this.getSoundClash());
			}
		}

		if (this.isTouchTheWallH()) {
			this.getBalle().setyBalleCoefDeplacement(-1
					* this.getBalle().getyBalleCoefDeplacement());
			if (!this.isGameOver()) {
				listSound.add(this.getSoundClash());
			}
		}

		boolean isStillBalle = false;
		boolean isHitBrique = false;
		for (Brique brique : listImageBrique) {
			if (brique.isVisible()) {
				isStillBalle = true;
				boolean isCollision = balle.isCollision(brique);
				if (isCollision) {
					this.getBalle().setxBalleCoefDeplacement(-1
							* this.getBalle().getxBalleCoefDeplacement());
					brique.setVisible(false);
					isHitBrique = true;
				} else {
					listImage.add(brique);
				}
			}
		}
		
		if (isHitBrique) {
			listSound.add(this.getSoundClash());
		}
		
		if (!isStillBalle) {
			this.setGameOver(true);
		}

		listImage.add(this.getBarre()); // #17
		listImage.add(this.getBalle()); // #17
		
		if (!this.isGameOver() && isStillBalle) {
			this.getBalle().setX(this.getBalle().getX() + this.getBalle().getxBalleCoefDeplacement());
			this.getBalle().setY(this.getBalle().getY() + this.getBalle().getyBalleCoefDeplacement());
		} else {
			if (this.isGameOverFirstUse()) {
				listSound.add(this.getSoundGameOver());
				this.setGameOverFirstUse(false);
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

	public float getEffectiveMarge() {
		float diffCoefDep = this.getW() / this.getBalle().getxBalleCoefDeplacement();
		float diffMarge = this.getW() / marge;
		
		float maxDiff = diffCoefDep;
		if (maxDiff > diffMarge) {
			maxDiff = diffMarge;
		}
		
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
