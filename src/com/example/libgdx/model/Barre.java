package com.example.libgdx.model;

import com.badlogic.gdx.graphics.Texture;

public class Barre extends Image {

	private float oldY = 0;

	public Barre(Texture texture) {
		super(texture);
	}

	public void move(float position) {
		this.setOldY(this.getY());
		this.setY(position - (this.getHeight() / 2));
	}
	
	public float getOldY() {
		return oldY;
	}
	
	public float getVitesse() {
		return 
//				Math.abs(
						this.getY() - this.getOldY()
//						)
						;
	}

	public void setOldY(float oldY) {
		this.oldY = oldY;
	}
}
