package com.example.libgdx.model;

import com.badlogic.gdx.graphics.Texture;

public class Balle extends Image {

	private float xBalleCoefDeplacement;
	private float yBalleCoefDeplacement;
	
	public boolean isTouchTheWallW(float w) {
		return ((w - (this.getWidth()) < this.getX()) || this.getX() < 0);
	}

	public boolean isTouchTheWallH(float h) {
		return (h - this.getHeight() < this.getY() || this.getY() < 0);
	}
	
	public float getxBalleCoefDeplacement() {
		return xBalleCoefDeplacement;
	}

	public void setxBalleCoefDeplacement(float xBalleCoefDeplacement) {
		this.xBalleCoefDeplacement = xBalleCoefDeplacement;
	}

	public float getyBalleCoefDeplacement() {
		return yBalleCoefDeplacement;
	}

	public void setyBalleCoefDeplacement(float yBalleCoefDeplacement) {
		this.yBalleCoefDeplacement = yBalleCoefDeplacement;
	}
	
	public boolean isBalleHitBarre(Barre barre, float xBalleCoefDeplacementTemp, float yBalleCoefDeplacementTemp) {
		if (this.isCollision(barre)) {
			this.setxBalleCoefDeplacement(-1
					* this.getxBalleCoefDeplacement());
			
			float vitesse = barre.getVitesse();
			
			if (vitesse > this.getyBalleCoefDeplacement() / 2) {
				vitesse = yBalleCoefDeplacementTemp / 2;
			}
			
			this.setyBalleCoefDeplacement(vitesse
					+ yBalleCoefDeplacementTemp);
			this.setX(barre.getX()-this.getWidth());
			
			return true;
		}
		
		return false;
	}

	public Balle(Texture texture) {
		super(texture);
	}

	public void move(float position) {
		this.setY(position - (this.getHeight() / 2));
	}
}
