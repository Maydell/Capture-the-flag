package world;

import main.CTF;

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
		g.translate(-getX() + CTF.WIDTH / 2, -getY() + CTF.HEIGHT / 2);
	}
	
	public void move(float dx, float dy) {
		x += dx;
		y += dy;
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
