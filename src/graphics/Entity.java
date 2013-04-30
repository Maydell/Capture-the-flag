package graphics;

import org.newdawn.slick.Graphics;

import world.Tile;

/**
 * An Entity object is anything that can be located in a Tile on the map.
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
}
