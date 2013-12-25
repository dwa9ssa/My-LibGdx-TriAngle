package com.example.libgdx.model;

import com.badlogic.gdx.graphics.Texture;

public class Balle extends Image {

	private float xBalleCoefDeplacement;
	private float yBalleCoefDeplacement;
	
	
	
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

	public Balle(Texture texture) {
		super(texture);
	}

	public void move(float position) {
		this.setY(position - (this.getHeight() / 2));
	}
}
