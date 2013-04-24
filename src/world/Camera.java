package world;

import main.CTF;

import org.newdawn.slick.Graphics;

import graphics.Drawable;

/**
 * The camera class dictates where things are drawn on the screen. It can be
 * moved in the game using the up, down, left, right or, w, s, a, d buttons.
 * 
 * @author Mats Stichel, Isak Jagberg
 * 
 */
public class Camera {

	private float x; // The camera's x-coordinate
	private float y; // The camera's y-coordinate

	/**
	 * Moves the camera to the target Drawable.
	 * 
	 * @param d
	 *            The target Drawable.
	 */
	public void target(Drawable d) {
		setX(d.getxPos());
		setY(d.getyPos());
	}

	public void useView(Graphics g) {
		g.translate(-getX() + CTF.WIDTH / 2, -getY() + CTF.HEIGHT / 2);
	}

	/**
	 * Changes the camera's coordinates.
	 * 
	 * @param dx
	 *            The change in x-coordinate.
	 * @param dy
	 *            The change in y-coordinate.
	 */
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
