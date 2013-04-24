package world;

import graphics.Drawable;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Unit extends Drawable {

	public static enum Class {

		Scout(100, 40, 8, 8, 2, 0), Soldier(140, 50, 8, 4, 3, 1), Sniper(100, 80, 40, 2, 3, 2), Medic(100, -30, 4, 6, 3, 3);
		
		int hp, damage;
		int shootRange, moveRange;
		int spawnTime;
		int id;
		
		Class(int hp, int damage, int shootRange, int moveRange, int spawnTime, int id) {
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

	public Unit(Class unitClass, int team) {
		setUnitClass(unitClass);
		setTeam(team);
		hp = unitClass.hp;
	}
	
	public static void initUnits() {
		images = new Image[Class.values().length][2];
		try {
			images[0][0] = new Image("images/units/unit_red_scout.png");
			images[0][1] = new Image("images/units/unit_blue_scout.png");
			images[1][0] = new Image("images/units/unit_red_soldier.png");
			images[1][1] = new Image("images/units/unit_blue_soldier.png");
			images[2][0] = new Image("images/units/unit_red_sniper.png");
			images[2][1] = new Image("images/units/unit_blue_sniper.png");
			images[3][0] = new Image("images/units/unit_red_medic.png");
			images[3][1] = new Image("images/units/unit_blue_medic.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void draw(Graphics g) {
		if (parent != null) {
			g.drawImage(images[unitClass.id][team], parent.getxPos() * Tile.TILE_SIZE, parent.getyPos() * Tile.TILE_SIZE);
			g.setColor(Color.black);
			g.drawRect(parent.getxPos() * Tile.TILE_SIZE + (Tile.TILE_SIZE - 40) / 2, parent.getyPos() * Tile.TILE_SIZE - (Tile.TILE_SIZE - 40) / 2, 40, 5);
			g.setColor(new Color(1 - (float) hp / unitClass.hp, (float) hp / unitClass.hp, 0f));
			g.fillRect(1 + parent.getxPos() * Tile.TILE_SIZE + (Tile.TILE_SIZE - 40) / 2, 1 + parent.getyPos() * Tile.TILE_SIZE - (Tile.TILE_SIZE - 40) / 2, (int) (((double) hp / unitClass.hp) * 39), 4);
		}
	}
	
	public void takeDamage(int damage) {
		hp -= damage;
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
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
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
}
