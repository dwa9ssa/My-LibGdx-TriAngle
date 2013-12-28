package com.example.libgdx.model;

public class Collision {

	private boolean isCollision;
	private CollisionType collisionTypeV;
	private CollisionType collisionTypeH;
	
	public boolean isCollision() {
		return isCollision;
	}

	public void setCollision(boolean isCollision) {
		this.isCollision = isCollision;
	}

	public CollisionType getCollisionTypeV() {
		return collisionTypeV;
	}

	public void setCollisionTypeV(CollisionType collisionTypeV) {
		this.collisionTypeV = collisionTypeV;
	}

	public CollisionType getCollisionTypeH() {
		return collisionTypeH;
	}

	public void setCollisionTypeH(CollisionType collisionTypeH) {
		this.collisionTypeH = collisionTypeH;
	}

	public Collision (Image image1, Image image2) {
		if (((image1.getY() >= image2.getY() && image1.getY() <= image2.getY() + image2.getHeight()) 
				|| (image2.getY() >= image1.getY() && image2.getY() <= image1.getY() + image1.getHeight())) 
				&& ((image1.getX() <= image2.getX()) 
					&& (image1.getX() + image1.getWidth() >= image2.getX()))) {
			
			this.setCollision(true);
			
			if ((image2.getX() - image1.getX()) >= (image1.getX() + image1.getWidth() - image2.getX())) {
				this.setCollisionTypeV(CollisionType.Up);
			} else {
				this.setCollisionTypeV(CollisionType.Down);
			}
		}
		

		if (((image1.getX() >= image2.getX() && image1.getX() <= image2.getX() + image2.getWidth()) 
			|| (image2.getX() >= image1.getX() && image2.getX() <= image1.getX() + image1.getWidth())) 
			&& ((image2.getY() <= image1.getY()) 
				&& (image2.getY() + image2.getHeight() >= image1.getY()))) {
			
			this.setCollision(true);
			
			if ((image1.getY() - image2.getY()) >= (image2.getY() + image2.getHeight() - image1.getY())) {
				this.setCollisionTypeH(CollisionType.Left);
			} else {
				this.setCollisionTypeH(CollisionType.Right);
			}
		}
	}
}
