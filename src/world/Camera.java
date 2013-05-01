package world;

import graphics.Entity;
import main.CTF;

import org.newdawn.slick.Graphics;

/**
 * The camera class dictates where things are drawn on the screen. It can be
 * moved in the game using the up, down, left, right or, w, s, a, d buttons.
 * 
 * @author Mats Stichel, Isak Jagberg
 * 
 */
public class Camera {

	private int x; // The camera's x-coordinate
	private int y; // The camera's y-coordinate
	private Entity target;
	private float speed; // The movement speed of the camera.

	public Camera(float speed) {
		setSpeed(speed);
	}

	/**
	 * Moves the camera to the target Drawable.
	 * 
	 * @param d
	 *            The target Drawable.
	 */
	public void target(Entity d) {
		target = d;
		// setX(d.getxPos() * Tile.TILE_SIZE + Tile.TILE_SIZE / 2);
		// setY(d.getyPos() * Tile.TILE_SIZE + Tile.TILE_SIZE / 2);
		System.out.println("Moving to " + d.getxPos() + ", " + d.getyPos());
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
	public void move(float dx, float dy, int delta) {
		if (dx != 0 || dy != 0) {
			x += dx * speed * delta;
			y += dy * speed * delta;
			target = null;
		} else if (target != null) {
			dy = (target.getyPos() * Tile.TILE_SIZE + (Tile.TILE_SIZE / 2)) - y;
			dx = (target.getxPos() * Tile.TILE_SIZE + (Tile.TILE_SIZE / 2)) - x;
			if (Math.abs(dx) <= 25 && Math.abs(dy) <= 25) {
//				setX(target.getxPos() * Tile.TILE_SIZE + (Tile.TILE_SIZE / 2));
//				setY(target.getyPos() * Tile.TILE_SIZE + (Tile.TILE_SIZE / 2));
				target = null;
			}
			if (dx != 0) {
				float k = dy / dx;
				x += delta * dx / 300;
				y += delta * k * dx / 300;
			} else {
				y += delta * dy / 300;
			}
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
}
