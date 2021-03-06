package world;

import graphics.Entity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * A Spawn-object takes care of spawning units.
 * 
 * @author Mats Stichel, Isak Jagberg
 * 
 */
public class Spawn extends Entity {

	// The spawnList stores any dead unit together with its spawn time. At the
	// start of the player's turn, the spawn time is reduced by 1, until it
	// reaches 0 (at which point the unit is spawned).
	public HashMap<Unit, Integer> spawnList = new HashMap<Unit, Integer>();
	private Animation animation;

	public Spawn(Tile parent, int team) {
		this.parent = parent;
		Image[] images = loadImages(team);
		animation = new Animation(images, 200);
		animation.setPingPong(true);
		animation.start();
	}

	/**
	 * Loads the 5 images that are used in the animation for the drawable spawn
	 * entity.
	 * 
	 * @param team
	 *            The team that owns the spawn object.
	 * @return An array containing the 5 images.
	 */
	public Image[] loadImages(int team) {
		Image[] images = new Image[5];
		String teamString = (team == 0) ? "Red" : "Blue";
		for (int i = 0; i < images.length; i++) {
			try {
				images[i] = new Image("images/drawables/spawn/" + teamString
						+ "/" + i + ".png");
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
		return images;
	}

	/**
	 * Takes in a dead unit, makes a new version of him, and spawns that.
	 * 
	 * @param unitClass
	 *            The class of the dead unit.
	 * @param team
	 *            The team of the dead unit.
	 * @return A new version of the given unit.
	 */
	public Unit createUnit(Unit.Class unitClass, int team) {
		Unit unit = new Unit(unitClass, team);
		spawn(unit);
		return unit;
	}

	/**
	 * Spawns a unit.
	 * 
	 * @param unit
	 *            The unit that will spawn.
	 */
	public void spawn(Unit unit) {
		// System.out.println("Spawned player!");

		// Iterate through the neigbors, looking for a Tile that is not occupied
		// and then puts the given unit on that Tile.
		for (Tile neighbor : parent.neighbors) {
			if (!neighbor.isOccupied()) {
//				unit.getParent().removeUnit();
				unit.setParent(neighbor);
				neighbor.setUnit(unit);
				unit.setAlive(true);
				unit.setHp(unit.getUnitClass().hp);
				unit.setMovement(unit.getUnitClass().moveRange);
				break;
			}
		}
	}

	/**
	 * Goes through the spawnlist spawning any unit that has 0 or 1 turns left
	 * (allowing future units to have a respawn time of 0). Subtracts 1 from the
	 * rest of the units in the list.
	 */
	public void spawn() {
		Iterator<Entry<Unit, Integer>> i = spawnList.entrySet().iterator();
		while (i.hasNext()) {
			Entry<Unit, Integer> item = i.next();
			int spawnTime = item.getValue();
			if (spawnTime <= 0) {
				item.getKey().getParent().removeUnit();
				spawn(item.getKey());
				i.remove();
			} else {
				spawnTime--;
				spawnList.put(item.getKey(), spawnTime);
			}
		}
	}

	/**
	 * When a unit dies, it is added to the spawnlist together with its spawn
	 * time.
	 * 
	 * @param unit
	 *            The dead unit.
	 */
	public void add(Unit unit) {
		spawnList.put(unit, unit.getUnitClass().spawnTime);
	}

	/**
	 * Draws the animated spawn entity.
	 */
	@Override
	public void draw(Graphics g) {
		animation.draw(getxPos() * Tile.TILE_SIZE, getyPos() * Tile.TILE_SIZE);
	}
	
}
