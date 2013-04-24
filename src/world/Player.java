package world;

import java.util.ArrayList;

public class Player {

	public static final int RED = 0, BLUE = 1;
	
	private int team;
	private Spawn spawn;
	private boolean done = false;
	
	private ArrayList<Unit> units = new ArrayList<Unit>();
	
	public Player(int team) {
		setTeam(team);
	}
	
	public void turn() {
		done = false;
		spawn.spawn();
	}
	
	public boolean done() {
		return done;
	}

	public void setupTeam() {
		Unit unit1 = new Unit(Unit.Class.Scout, team), unit2 =  new Unit(Unit.Class.Soldier, team), 
				unit3 = new Unit(Unit.Class.Sniper, team), unit4 = new Unit(Unit.Class.Medic, team);
		units.add(unit1);
		units.add(unit2);
		units.add(unit3);
		units.add(unit4);
		spawn.spawn(unit1);
		spawn.spawn(unit2);
		spawn.spawn(unit3);
		spawn.spawn(unit4);
	}
	
	public void setSpawn(Spawn spawn) {
		this.spawn = spawn;
	}
	
	public Spawn getSpawn() {
		return spawn;
	}

	public int getTeam() {
		return team;
	}

	public void setTeam(int team) {
		this.team = team;
	}

	public ArrayList<Unit> getUnits() {
		return units;
	}

	public void setUnits(ArrayList<Unit> units) {
		this.units = units;
	}
}
