package com.example.libgdx.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class Image {
	
	private String imagePath;
	private float x;
	private float y;
	private Pixmap pixels;
	private Texture texture;
	
	public Image(String imagePath) {
		this.setImagePath(imagePath);

		pixels = new Pixmap(Gdx.files.internal(this.getImagePath()));
		texture = new Texture(pixels);
	}

	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
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
	public Pixmap getPixels() {
		return pixels;
	}
	public void setPixels(Pixmap pixels) {
		this.pixels = pixels;
	}
	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	
	public int getHeight() {
		return pixels.getHeight();
	}
	
	public int getWidth() {
		return pixels.getWidth();
	}
	
	public boolean isCollision(Image image) {
		if ((this.getX() >= image.getX() && this.getX() <= image.getX() + image.getWidth()) && (this.getY() >= image.getY() && this.getY() <= image.getY() + image.getHeight()))
			return true;
		
		if ((image.getX() >= this.getX() && image.getX() <= this.getX() + this.getWidth()) && (image.getY() >= this.getY() && image.getY() <= this.getY() + this.getHeight()))
			return true;
		
		return false;
	}
	
	public String toString () {
		return "X = " + this.getX() + "; Y = " + this.getY() + "; Height = " + this.getHeight() + "; Width = " + this.getWidth() + ";";
	}
}
