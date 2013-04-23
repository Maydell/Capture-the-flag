package world;

import graphics.Drawable;

public class Flag extends Drawable{
	
	private Player.Team team;
	
	public Flag(Player.Team team){
		this.setTeam(team);
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}

	public Player.Team getTeam() {
		return team;
	}

	public void setTeam(Player.Team team) {
		this.team = team;
	}
	
}
