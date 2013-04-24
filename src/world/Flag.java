package world;

import graphics.Drawable;

import org.newdawn.slick.Graphics;

/**
 * A flag is a Drawable entity that can be picked up by units for extra points
 * in the game.
 * 
 * @author Mats Stichel, Isak Jagberg
 * 
 */
public class Flag extends Drawable {

	private int team;

	public Flag(int team) {
		this.setTeam(team);
	}

	@Override
	public void draw(Graphics g) {

	}

	public int getTeam() {
		return team;
	}

	public void setTeam(int team) {
		this.team = team;
	}

}
