package graphics;

import org.newdawn.slick.Graphics;

import world.Tile;

/**
 * A Drawable object is anything that is drawn on the screen. This includes
 * Tiles, Units, Spawns and Flags.
 * 
 * @author Mats Stichel, Isak Jagberg
 * 
 */
public abstract class Entity {

	// The tile that has this object as entity
	protected Tile parent;

	public abstract void draw(Graphics g);

	public int getxPos() {
		return parent.getxPos();
	}

	public int getyPos() {
		return parent.getyPos();
	}

	public Tile getParent() {
		return parent;
	}
	
	public void setParent(Tile parent) {
		this.parent = parent;
	}
}
