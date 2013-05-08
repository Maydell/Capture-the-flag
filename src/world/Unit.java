package world;

import graphics.Entity;

import java.util.ArrayList;

import main.Pathfinding;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

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

		Scout(100, 40, 8, 40000, 2, 0), Soldier(140, 50, 8, 4, 3, 1), Sniper(
				100, 80, 40, 2, 3, 2), Medic(100, -30, 4, 6, 3, 3);

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
		if (target != this) {
			System.out.println("Attacked " + target + ".");
			target.takeDamage(getUnitClass().damage);
			return true;
		}
//		System.out.println("A Unit can't attack himself.");
		dropFlag();
		return false;
	}

	// TODO
	private void jump(Tile target) {
		// TODO
		if (!target.isOccupied()) {
			System.out.println("Moving " + this + ".");
			parent.removeUnit();
			target.setUnit(this);
			if (flag != null) {
				if (target.getFlag() != null
						&& target.getFlag().getTeam() == team) {
					System.out.println(this + " captured the flag.");
					gamestates.Game.active.increaseScore(10);
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
			System.out.println("Can't move to an occupied tile.");
	}

	// TODO
	public void moveTo(Tile target) {
		ArrayList<Tile> path = Pathfinding.findPath(parent, target);
		if (path.size() <= movement) {
			jump(target);
			movement -= path.size();
		} else {
			System.out.println("That Tile is too far away.");
		}
	}

	// TODO
	public void takeFlag(Flag flag) {
		this.flag = flag;
		flag.getParent().removeFlag();
	}

	// TODO
	public void dropFlag() {
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
}
