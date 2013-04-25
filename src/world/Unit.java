package world;

import graphics.Drawable;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.*;

/**
 * A Unit is a object that the players can give orders to.
 * 
 * @author Mats Stichel, Isak Jagberg
 * 
 */
public class Unit extends Drawable {

	// Every Unit has a certain Class, which dictates its stats (health, damage,
	// range etc.)
	public static enum Class {

		Scout(100, 40, 8, 8, 2, 0), Soldier(140, 50, 8, 4, 3, 1), Sniper(100,
				80, 40, 2, 3, 2), Medic(100, -30, 4, 6, 3, 3);

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

	private Class unitClass;
	private int team;
	private int hp;

	public Unit(GUIContext container, Class unitClass, int team) {
		super(null, null, null, 0, 0);
		Image image = images[unitClass.id][team];
		Image hover = images[unitClass.id][team].copy();
		hover.setImageColor(.8f, .8f, .8f);
		setNormalImage(image);
		setMouseOverImage(hover);
		setUnitClass(unitClass);
		setTeam(team);
		hp = unitClass.hp;
	}

	/**
	 * Called at the beginning of the Game. Loads all the images that represent
	 * different Classes of Unit.
	 */
	public static void initUnits() {
		images = new Image[Class.values().length][2];
		try {
			images[0][0] = new Image("images/drawables/units/unit_red_scout.png");
			images[0][1] = new Image("images/drawables/units/unit_blue_scout.png");
			images[1][0] = new Image("images/drawables/units/unit_red_soldier.png");
			images[1][1] = new Image("images/drawables/units/unit_blue_soldier.png");
			images[2][0] = new Image("images/drawables/units/unit_red_sniper.png");
			images[2][1] = new Image("images/drawables/units/unit_blue_sniper.png");
			images[3][0] = new Image("images/drawables/units/unit_red_medic.png");
			images[3][1] = new Image("images/drawables/units/unit_blue_medic.png");
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
			g.setColor(Color.black);
			g.drawRect(parent.getxPos() * Tile.TILE_SIZE
					+ (Tile.TILE_SIZE - 40) / 2, parent.getyPos()
					* Tile.TILE_SIZE - (Tile.TILE_SIZE - 40) / 2, 40, 5);
			g.setColor(new Color(1 - (float) hp / unitClass.hp, (float) hp
					/ unitClass.hp, 0f));
			g.fillRect(1 + parent.getxPos() * Tile.TILE_SIZE
					+ (Tile.TILE_SIZE - 40) / 2, 1 + parent.getyPos()
					* Tile.TILE_SIZE - (Tile.TILE_SIZE - 40) / 2,
					(int) (((double) hp / unitClass.hp) * 39), 4);
		}
	}

	/**
	 * Called when getting shot by a different Unit. Reduces the hp of this Unit
	 * based on the damage of the attacking Unit.
	 * 
	 * @param damage
	 *            The damage of the attacking Unit.
	 */
	public void takeDamage(int damage) {
		hp -= damage;
		// If hp is negative, the Unit is dead. This makes sure that the Unit
		// will be added to the Spawn if he was killed.
		if (hp < 0) {
			hp = 0;
		}
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public boolean isAlive() {
		return hp > 0;
	}

	public void setAlive(boolean alive) {
		this.hp = unitClass.hp;
	}

	public Class getUnitClass() {
		return unitClass;
	}

	public void setUnitClass(Class unitClass) {
		this.unitClass = unitClass;
	}

	public int getTeam() {
		return team;
	}

	public void setTeam(int team) {
		this.team = team;
	}
	
	public int getID() {
		return unitClass.id;
	}
}
