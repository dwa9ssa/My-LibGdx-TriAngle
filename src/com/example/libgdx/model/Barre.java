package com.example.libgdx.model;

public class Barre extends Image {

	public Barre(String imagePath) {
		super(imagePath);
	}

	public void move(float position) {
		this.setY(position - (this.getHeight() / 2));
	}
}
