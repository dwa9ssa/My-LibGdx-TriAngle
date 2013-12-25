package com.example.libgdx.model;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class Image {
		
	private float x;
	private float y;
	private Texture texture;

	public Image(Texture texture) {
		this.texture = texture;
	}

	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	
	public int getHeight() {
		return texture.getHeight();
	}
	
	public int getWidth() {
		return texture.getWidth();
	}
	
	public boolean isCollision(Image image) {
		if (
			((this.getX() >= image.getX() && this.getX() <= image.getX() + image.getWidth()) 
			&& (this.getY() >= image.getY() && this.getY() <= image.getY() + image.getHeight()))
			|| 
			((image.getX() >= this.getX() && image.getX() <= this.getX() + this.getWidth()) 
			&& (image.getY() >= this.getY() && image.getY() <= this.getY() + this.getHeight()))
			)
			return true;
		
		return false;
	}
	
	public String toString () {
		return "X = " + this.getX() + "; Y = " + this.getY() + "; Height = " + this.getHeight() + "; Width = " + this.getWidth() + ";";
	}
}
