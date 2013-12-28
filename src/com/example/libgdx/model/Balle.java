package com.example.libgdx.model;

import com.badlogic.gdx.graphics.Texture;

public class Balle extends Image {

	private float xBalleCoefDeplacement;
	private float yBalleCoefDeplacement;
	
	public boolean isTouchTheWallW(float w) {
		if (this.getX() < 0) {
			this.setX(0);
			return true;
		}

		if (w - this.getWidth() < this.getX()) {
			this.setX(w - this.getWidth());
			return true;
		}
		
		return false;
	}

	public boolean isTouchTheWallH(float h) {
		if (this.getY() < 0) {
			this.setY(0);
			return true;
		}
		
		if (h - this.getHeight() < this.getY()) {
			this.setY(h - this.getHeight());
			return true;
		}
		
		return false;
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

	public boolean isBalleFail(float w) {
		if (this.getX() + this.getHeight() >= w) {
			return true;
		}
		 
		return false;
	}
	
	
	public boolean isBalleHitBarre(Barre barre, float xBalleCoefDeplacementTemp, float yBalleCoefDeplacementTemp) {
		if (this.isCollision(barre)) {
			this.setxBalleCoefDeplacement(-1
					* this.getxBalleCoefDeplacement());
			
			float vitesse = barre.getVitesse();
			
//			if (vitesse > this.getyBalleCoefDeplacement() / 2) {
//				vitesse = yBalleCoefDeplacementTemp / 2;
//			}
			
			this.setyBalleCoefDeplacement(this.getyBalleCoefDeplacement() / 2 + (vitesse + yBalleCoefDeplacementTemp) / 2);
			this.setX(barre.getX()-this.getWidth());
			
			return true;
		}
		
		return false;
	}
	

	
//	public boolean isCollisionUp(Brique image) {
//		if (
//				
//			((this.getY() >= image.getY() && this.getY() <= image.getY() + image.getHeight() / 2) 
//			|| (image.getY() >= this.getY() && image.getY() <= this.getY() + this.getHeight() / 2)) 
//			
//			&& ((this.getX() + this.getWidth() / 2 >= image.getX()) 
//			&& (this.getX() + this.getWidth() / 2 <= image.getX() + image.getWidth() / 2))
//				
//				
//				
//				) {
//			System.out.println("balle brique collision Right");
//			return true;
//		}
//		
//		return false;
//	}
//	
//	public boolean isCollisionDown(Brique image) {
//		if (
//			((this.getY() >= image.getY() && this.getY() <= image.getY() + image.getHeight() / 2) 
//			|| (image.getY() >= this.getY() && image.getY() <= this.getY() + this.getHeight() / 2))
//			
//			
//			&& (
//					(image.getX() + image.getWidth() / 2 >= this.getX()) 
//					&& 
//					(image.getX() + image.getWidth() / 2 <= this.getX() + this.getWidth() / 2)
//			)
//				
//				
//				) {
//			System.out.println("balle brique collision Down");
//			return true;
//		}
//		
//		return false;
//	}
//	
//	public boolean isCollisionLeft(Brique image) {
//		if (((this.getX() >= image.getX() && this.getX() <= image.getX() + image.getWidth() / 2) 
//			|| (image.getX() >= this.getX() && image.getX() <= this.getX() + this.getWidth() / 2)) 
//			&& ((image.getY() + image.getHeight() / 2 >= this.getY()) 
//				&& (image.getY() + image.getHeight() / 2 <= this.getY() + this.getHeight() / 2))) {
//			System.out.println("balle brique collision Down");
//			return true;
//		}
//		
//		return false;
//	}
//	
//	public boolean isCollisionRight(Brique image) {
//		if (((this.getX() >= image.getX() && this.getX() <= image.getX() + image.getWidth() / 2) 
//			|| (image.getX() >= this.getX() && image.getX() <= this.getX() + this.getWidth() / 2)) 
//			&& (
//					(this.getY() + this.getHeight() / 2 >= image.getY()) 
//				&& (this.getY() + this.getHeight() / 2 <= image.getY() + image.getHeight() / 2))) {
//			System.out.println("balle brique collision Right");
//			return true;
//		}
//		
//		return false;
//	}

	public Balle(Texture texture) {
		super(texture);
	}

	public void move(float position) {
		this.setY(position - (this.getHeight() / 2));
	}
}
