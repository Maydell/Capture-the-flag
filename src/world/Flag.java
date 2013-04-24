package world;

import org.newdawn.slick.Graphics;

import graphics.Drawable;

public class Flag extends Drawable{
	
	private int team;
	
	public Flag(int team){
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
