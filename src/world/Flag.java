package world;

import graphics.Entity;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * A flag is a Drawable entity that can be picked up by units for extra points
 * in the game.
 * 
 * @author Mats Stichel, Isak Jagberg
 * 
 */
public class Flag extends Entity {

	private int team;
	private Tile startParent;
	private Image image;

	public Flag(Tile parent, int team) {
		this.parent = startParent = parent;
		this.setTeam(team);
		try {
			image = new Image("images/drawables/flag/flag_" + team + ".png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(image, getxPos() * Tile.TILE_SIZE, getyPos() * Tile.TILE_SIZE);
	}

	public int getTeam() {
		return team;
	}

	public void setTeam(int team) {
		this.team = team;
	}
	
	public void reset() {
		parent.removeFlag();
		startParent.setFlag(this);
	}

	public void setParent(Tile parent) {
		this.parent = parent;
	}

}
