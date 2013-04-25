package graphics;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.gui.GUIContext;

import world.Tile;
import world.Unit;

/**
 * A Drawable object is anything that is drawn on the screen. This includes
 * Tiles, Units, Spawns and Flags.
 * 
 * @author Mats Stichel, Isak Jagberg
 * 
 */
public abstract class Drawable extends Button {

	public Drawable(GUIContext container, Image image, Image hover, int x, int y) {
		super(container, image, hover, x, y);
	}
	
	public Drawable(GUIContext container, Image image) {
		this(container, image, image, 0, 0);
	}

	protected int xPos, yPos;

	// The tile that has this object as entity
	protected Tile parent;

	public void moveTo(Tile target) {
		xPos = target.xPos;
		yPos = target.yPos;
		if (parent != null) {
			parent.removeEntity();
		}
		target.setEntity(this);
		parent = target;
	}

	/**
	 * Initializes some of the drawn objects.
	 */
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
