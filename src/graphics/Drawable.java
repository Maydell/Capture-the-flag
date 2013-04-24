package graphics;

import org.newdawn.slick.Graphics;

import world.Tile;
import world.Unit;

public abstract class Drawable {

	protected int xPos, yPos;
	
	// The tile that has this object as entity
	protected Tile parent;
	
	public void moveTo(Tile target) {
		xPos = target.xPos; yPos = target.yPos;
		if (parent != null) {
			parent.removeEntity();
		}
		target.setEntity(this);
		parent = target;
	}
	
	public static void init() {
		Tile.initTiles();
		Unit.initUnits();
	}
	
	public abstract void draw(Graphics g);
	
	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
	
	public Tile getParent() {
		return parent;
	}
}
