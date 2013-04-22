package world;

import java.util.ArrayList;

public class Player {

	public enum Team {
		Red, Blue
	}
	
	private Team team;
	private Spawn spawn;
	
	private ArrayList<Unit> units;
	
	public Player(Spawn spawn, Team team) {
		setSpawn(spawn);
		setTeam(team);
		// Spawn units
	}
	
	public void setSpawn(Spawn spawn) {
		this.spawn = spawn;
	}
	
	public Spawn getSpawn() {
		return spawn;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public ArrayList<Unit> getUnits() {
		return units;
	}

	public void setUnits(ArrayList<Unit> units) {
		this.units = units;
	}
}
