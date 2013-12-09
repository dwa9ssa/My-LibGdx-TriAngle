package com.example.libgdx.model;

public class Balle extends Image {

	private float xBalleCoefDeplacement = 15;
	private float yBalleCoefDeplacement = 15;
	
	
	
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

	public Balle(String imagePath) {
		super(imagePath);
	}

	public void move(float position) {
		this.setY(position - (this.getHeight() / 2));
	}
}
