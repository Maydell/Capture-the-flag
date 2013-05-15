package world;

import graphics.Entity;
import graphics.HUD;
import java.util.ArrayList;
import main.Pathfinding;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Line;

/**
 * A Unit is a object that the players can give orders to.
 * 
 * @author Mats Stichel, Isak Jagberg
 * 
 */
public class Unit extends Entity {

	// Every Unit has a certain Class, which dictates its stats (health, damage,
	// range etc.)
	public static enum Class {
		//    hp - damage - shootRange - moveRange - spawnTime - id
		Scout(85, 20, 8, 8, 2, 0), Soldier(120, 40, 8, 6, 3, 1), Sniper(
				75, 60, 40, 3, 3, 2), Medic(100, -30, 14, 6, 3, 3);

		int hp, damage;
		int shootRange, moveRange;
		int spawnTime;
		int id;

		Class(int hp, int damage, int shootRange, int moveRange, int spawnTime,
				int id) {
			this.hp = hp;
			this.damage = damage;
			this.shootRange = shootRange;
			this.moveRange = moveRange;
			this.spawnTime = spawnTime;
			this.id = id;
		}
	}

	public static Image[][] images;

	private boolean alive;
	private Class unitClass;
	private int team;
	private int hp;
	private int movement;
	private Flag flag;

	public Unit(Class unitClass, int team) {
		setUnitClass(unitClass);
		setTeam(team);
		movement = unitClass.moveRange;
		hp = unitClass.hp;
	}

	/**
	 * Called at the beginning of the Game. Loads all the images that represent
	 * different Classes of Unit.
	 */
	public static void initUnits() {
		images = new Image[Class.values().length][2];
		try {
			images[0][0] = new Image(
					"images/drawables/units/unit_red_scout.png");
			images[0][1] = new Image(
					"images/drawables/units/unit_blue_scout.png");
			images[1][0] = new Image(
					"images/drawables/units/unit_red_soldier.png");
			images[1][1] = new Image(
					"images/drawables/units/unit_blue_soldier.png");
			images[2][0] = new Image(
					"images/drawables/units/unit_red_sniper.png");
			images[2][1] = new Image(
					"images/drawables/units/unit_blue_sniper.png");
			images[3][0] = new Image(
					"images/drawables/units/unit_red_medic.png");
			images[3][1] = new Image(
					"images/drawables/units/unit_blue_medic.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Draws this Unit at the location of its parent Tile.
	 */
	@Override
	public void draw(Graphics g) {
		if (parent != null) {
			g.drawImage(images[unitClass.id][team], parent.getxPos()
					* Tile.TILE_SIZE, parent.getyPos() * Tile.TILE_SIZE);
			drawHealth(g);
			if (flag != null) {
				flag.draw(g);
			}
		}
	}

	// TODO
	public void drawHealth(Graphics g) {
		g.setColor(Color.black);
		g.drawRect(parent.getxPos() * Tile.TILE_SIZE + (Tile.TILE_SIZE - 40)
				/ 2, 5 + parent.getyPos() * Tile.TILE_SIZE, 40, 5);
		g.setColor(new Color(1 - (float) hp / unitClass.hp, (float) hp
				/ unitClass.hp, 0f));
		g.fillRect(1 + parent.getxPos() * Tile.TILE_SIZE
				+ (Tile.TILE_SIZE - 40) / 2, 6 + parent.getyPos()
				* Tile.TILE_SIZE, (int) (((double) hp / unitClass.hp) * 39), 4);
	}

	/**
	 * Called when getting damaged by a different Unit. Reduces the hp of this
	 * Unit based on the damage of the attacking Unit.
	 * 
	 * @param damage
	 *            The damage of the attacking Unit.
	 */
	public void takeDamage(int damage) {
		if (!alive)
			return;
		hp -= damage;
		// If hp is negative, the Unit is dead. This makes sure that the Unit
		// will be added to the Spawn if he was killed.
		if (hp <= 0) {
			hp = 0;
			alive = false;
		} else if (hp > getUnitClass().hp) {
			hp = getUnitClass().hp;
		}
	}

	// TODO
	public boolean attack(Unit target) {
		if (movement < unitClass.moveRange / 2) {
			HUD.feed.add(this + " has no more actions.", -1);
			return false;
		}
		if (!target.isAlive()) {
			HUD.feed.add("Target is dead.", -1);
			return false;
		}
		if (target != this) {
			Line path = new Line((Tile.TILE_SIZE / 2) + getxPos()
					* Tile.TILE_SIZE, (Tile.TILE_SIZE / 2) + getyPos()
					* Tile.TILE_SIZE, (Tile.TILE_SIZE / 2) + target.getxPos()
					* Tile.TILE_SIZE, (Tile.TILE_SIZE / 2) + target.getyPos()
					* Tile.TILE_SIZE);

			float dx;
			float dy;

			if (Math.abs(path.getDX()) > Math.abs(path.getDY())) {
				dx = (path.getDX() >= 0) ? 1 : -1;
				dy = dx * path.getDY() / path.getDX();
			} else {
				dy = (path.getDY() >= 0) ? 1 : -1;
				dx = dy * path.getDX() / path.getDY();
			}
			float x = (Tile.TILE_SIZE / 2) + getxPos() * Tile.TILE_SIZE;
			float y = (Tile.TILE_SIZE / 2) + getyPos() * Tile.TILE_SIZE;

			Tile tile = Map.getTile((int) x, (int) y);
			while (tile.getUnit() != target) {
				x += dx;
				y += dy;
				tile = Map.getTile((int) x, (int) y);
				if (tile.isOccupied() && tile.getUnit() == null) {
					HUD.feed.add("Target not visible!", -1);
					return false;
				}
			}
			movement = 0;
			int distance = (int) Math.sqrt(path.getDX() * path.getDX()
					+ path.getDY() * path.getDY())
					/ Tile.TILE_SIZE;
			float chance = 1 - (distance / unitClass.shootRange);
			System.out.println("Distance: " + distance + " ShootRange: " + unitClass.shootRange);
			if (Math.random() < chance) {
				int damage = (int) ((0.8 + Math.random() * 0.4) * unitClass.damage
						* (1.0 - ((double) distance / (2 * unitClass.shootRange))));
				target.takeDamage(damage);
				if (unitClass == Unit.Class.Medic)
					HUD.feed.add("Healed " + target + " for " + -damage + " hp.", -1);
				else 
					HUD.feed.add("Attacked " + target + " for " + damage
						+ " hp.", -1);
				return !target.isAlive();
			}
			HUD.feed.add("Missed target.", -1);
			return false;
		}
		dropFlag();
		return false;
	}

	// TODO
	private void jump(Tile target) {
		// TODO
		if (!target.isOccupied()) {
			parent.removeUnit();
			target.setUnit(this);
			if (flag != null) {
				if (target.getFlag() != null
						&& target.getFlag().getTeam() == team) {
					HUD.feed.add(this + " captured the flag.", -1);
					gamestates.Game.active.increaseScore(40);
					flag.reset();
					flag = null;
				} else {
					flag.setParent(target);
				}
			}
			parent = target;
			if (parent.getFlag() != null && flag == null) {
				if (parent.getFlag().getTeam() == getTeam()) {
					parent.getFlag().reset();
				} else {
					takeFlag(parent.getFlag());
					parent.removeFlag();
				}
			}
		} else
			HUD.feed.add("Can't move to an occupied tile.", -1);
	}

	// TODO
	public void moveTo(Tile target) {
		ArrayList<Tile> path = Pathfinding.findPath(parent, target);
		if (path.size() <= movement) {
			jump(target);
			movement -= path.size();
		} else {
			HUD.feed.add("That Tile is too far away.", -1);
		}
	}

	// TODO
	public void takeFlag(Flag flag) {
		this.flag = flag;
		flag.getParent().removeFlag();
	}

	// TODO
	public void dropFlag() {
		if (flag == null) return;
		parent.setFlag(flag);
		flag = null;
	}

	// TODO
	public int getHp() {
		return hp;
	}

	// TODO
	public void setHp(int hp) {
		this.hp = hp;
	}

	// TODO
	public boolean isAlive() {
		return alive;
	}

	// TODO
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	// TODO
	public Class getUnitClass() {
		return unitClass;
	}

	// TODO
	public void setUnitClass(Class unitClass) {
		this.unitClass = unitClass;
	}

	// TODO
	public int getID() {
		return unitClass.id;
	}

	// TODO
	public int getTeam() {
		return team;
	}

	// TODO
	public void setTeam(int team) {
		this.team = team;
	}

	// TODO
	public void setParent(Tile parent) {
		this.parent = parent;
	}

	// TODO
	public int getMovement() {
		return movement;
	}

	// TODO
	public void setMovement(int movement) {
		this.movement = movement;
	}

	public int getMoveRange() {
		return unitClass.moveRange;
	}
	
	public int getMaxHP() {
		return unitClass.hp;
	}

	// TODO
	public String toString() {
		String rep = "";
		if (team == 0)
			rep += "Red ";
		else
			rep += "Blue ";
		rep += unitClass;
		return rep;
	}

	public boolean hasFlag() {
		return flag != null;
	}

	public int getDamage() {
		return unitClass.damage;
	}
	
	public int getSpawnTime() {
		return unitClass.spawnTime;
	}
}
