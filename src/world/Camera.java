package world;

import org.newdawn.slick.Graphics;

import graphics.Drawable;

public class Camera {
	
	private float x;
	private float y;

	public void target(Drawable d) {
		setX(d.getxPos());
		setY(d.getyPos());
	}

	public void useView(Graphics g) {
		g.translate(-getX(), -getY());
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
}
