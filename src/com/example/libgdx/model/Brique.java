package com.example.libgdx.model;

public class Brique extends Image {

	private boolean isVisible = true;
	
	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public Brique(String imagePath) {
		super(imagePath);
	}
}
