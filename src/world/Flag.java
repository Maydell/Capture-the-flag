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
	
	private int startX, startY;

	public Flag(int team, int startX, int startY) {
		setTeam(team);
		this.startX = startX;
		this.startY = startY;
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
