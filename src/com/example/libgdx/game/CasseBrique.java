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

	private boolean gameOverFirstUse = false;
	private boolean isGameOver = false;

	private int margeBrique = 10;
	private int nbrCols;
	private int nbrBrique = 90;

	public CasseBrique(Sound soundGameOver, Sound soundClash,
			float w, float h, float marge, boolean gameOverFirstUse,
			boolean isGameOver) {
		this.soundGameOver = soundGameOver;
		this.soundClash = soundClash;
		this.w = w;
		this.h = h;
		this.marge = marge;
		this.gameOverFirstUse = gameOverFirstUse;
		this.isGameOver = isGameOver;

		barre.setX(w - barre.getWidth() - marge);
		
		this.getBalle().setX(this.getBarre().getX() - 2 * this.getBalle().getHeight());
//		this.getBalle().setY(this.getBalle().getY());

		
		
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
		
		
//		Brique brique = new Brique("data/brique.png");
////		brique.setX(this.getW() - brique.getWidth());
//		brique.setY(this.getH() - brique.getHeight());
//		
//		Brique brique1 = new Brique("data/brique.png");
////		brique1.setX(this.getW() - brique.getWidth());
//		brique1.setY((this.getH() / 2) - brique1.getHeight());
//		
//		List<Brique> listBrique = new ArrayList<Brique>();
//		
//		listBrique.add(new Brique("data/brique.png"));
//
//		listBrique.add(brique);
//		listBrique.add(brique1);
//		
//		this.setListImageBrique(listBrique);
	}

	public boolean isBalleFail() {
		
//		System.out.println("W : " + this.getW() + " H : " + this.getH()); 
//		
//		System.out.println("WB : " + this.getBalle().getWidth() + "    HB : " + this.getBalle().getHeight());
//		
//		System.out.println("WB : " + this.getBalle().getX() + "    HB : " + this.getBalle().getY());
//		
//		System.out.println("WR : " + this.getBarre().getWidth() + "    HR : " + this.getBarre().getHeight());
//		
//		System.out.println("WR : " + this.getBarre().getX() + "    HR : " + this.getBarre().getY());
		
		
//		(this.getBalle().getWidth() + this.getMarge()) < this
//				.getBalle().getX()) && !(this.getBalle().getY()
//				- (this.getBalle().getHeight() / 2) <= (this.getBarre().getY() + this
//				.getBalle().getHeight()) && (this.getBarre().getY() - this
//				.getBalle().getHeight()) <= this.getBalle().getY()
//				- (this.getBalle().getHeight() / 2))
		

				
				if (this.getBalle().getX() >= this.getW()) {
					return true;
				}
				 
				return false;
				

//				return (
//				
////				
//				(
//						this.getW() - 
//						(this.getBalle().getWidth() + this.getMarge()) 
//						< 
//						this.getBalle().getX()
//				) 
//				&& 
//				this.getBalle().isCollision(this.getBarre())
////				(
////					this.getBalle().getY() - (this.getBalle().getHeight() / 2)
////					<= 
////					(this.getBarre().getY() + this.getBalle().getHeight()) 
////					&& 
////					(this.getBarre().getY() - this.getBalle().getHeight())
////					<= 
////					this.getBalle().getY() - (this.getBalle().getHeight() / 2)
////				)
//				
//				);
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

		for (Brique brique : listImageBrique) {
//			System.err.println("Balle : " + this.getBalle());
			if (brique.isVisible()) {
				boolean isCollision = balle.isCollision(brique);
				if (isCollision) {
					System.out.println("Collision");
					this.getBalle().setxBalleCoefDeplacement(-1
							* this.getBalle().getxBalleCoefDeplacement());
//					this.getBalle().setyBalleCoefDeplacement(-1
//							* this.getBalle().getyBalleCoefDeplacement());
					brique.setVisible(false);
				} else {
					listImage.add(brique);
				}
			}
////				System.err.println("Brique : " + image);
//				listImage.add(brique);
//			} else if (brique.isVisible() && isCollision) {
////				System.err.println("Brique : " + image);
////				listImage.add(brique);
//				this.getBalle().setxBalleCoefDeplacement(-1
//						* this.getBalle().getxBalleCoefDeplacement());
//				this.getBalle().setyBalleCoefDeplacement(-1
//						* this.getBalle().getyBalleCoefDeplacement());
//			} else {
//				brique.setVisible(false);
//			}
		}

		listImage.add(this.getBarre()); // #17
		listImage.add(this.getBalle()); // #17
		
		if (!this.isGameOver()) {
			this.getBalle().setX(this.getBalle().getX() + this.getBalle().getxBalleCoefDeplacement());
			this.getBalle().setY(this.getBalle().getY() + this.getBalle().getyBalleCoefDeplacement());

			
//			for (Brique brique : listImageBrique) {
////				System.err.println("Balle : " + this.getBalle());
//				if (brique.isVisible() && !balle.isCollision(brique)) {
////					System.err.println("Brique : " + image);
//					listImage.add(brique);
//				} else {
//					brique.setVisible(false);
//				}
//			}
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
