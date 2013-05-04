package world;

import java.util.ArrayList;

/**
 * Player represents a human player. It takes care of the units owned by the
 * player and the Spawn related to the player's team.
 * 
 * @author Mats Stichel, Isak Jagberg
 * 
 */
public class Player {

	public static final int RED = 0, BLUE = 1;

	private int team;
	private Spawn spawn;
	private boolean done = true;
	public static Unit selected;

	private int score;

	private ArrayList<Unit> units = new ArrayList<Unit>();

	public Player(int team) {
		setTeam(team);
		score = 0;
	}

	/**
	 * In one turn, a player gets to assign his orders to his units.
	 */
	public void turn() {
		System.out.println(this + "'s turn.");
		done = false; // Done is set to false to show that the player is not
						// done with his turn.

		// Add any dead unit to the spawnlist (if he is not already in the
		// spawnlist).
		for (Unit u : units) {
			if (!u.isAlive() && !spawn.spawnList.containsKey(u)) {
				spawn.add(u);
			} else {
				u.setMovement(u.getUnitClass().moveRange);
			}
		}

		spawn.spawn(); // The units in the player's spawn-list are moved one
		// step closer to spawning.
	}

	public void finishTurn() {
		System.out.println("Turn over for " + this);
		done = true;
	}

	public boolean isDone() {
		return done;
	}

	/**
	 * Called at the beginning of the game. Sets up the player's team.
	 */
	public void setupTeam() {
		// Creates four units, owned by the player.
		Unit unit1 = new Unit(Unit.Class.Scout, team), unit2 = new Unit(
				Unit.Class.Soldier, team), unit3 = new Unit(Unit.Class.Sniper,
				team), unit4 = new Unit(Unit.Class.Medic, team);

		// Adds the four units to the list.
		units.add(unit1);
		units.add(unit2);
		units.add(unit3);
		units.add(unit4);

		// Spawns all four units.
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

	public int getScore() {
		return score;
	}
	
	public void increaseScore(int score) {
		this.score += score;
	}

	public ArrayList<Unit> getUnits() {
		return units;
	}

	public void setUnits(ArrayList<Unit> units) {
		this.units = units;
	}

	@Override
	public String toString() {
		return (team == Player.RED) ? "Red" : "Blue";
	}
}
