package com.example.libgdx.model;

import com.badlogic.gdx.graphics.Texture;

public class Barre extends Image {

	public Barre(Texture texture) {
		super(texture);
	}

	public void move(float position) {
		System.out.println(position - (this.getHeight() / 2));
		this.setY(position - (this.getHeight() / 2));
	}
}
