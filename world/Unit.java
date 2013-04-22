package world;

import graphics.Drawable;

public class Unit extends Drawable {

	public static enum Class {
		
		Scout(75, 40, 8, 8);
		
		Class(int hp, int damage, int shootRange, int moveRange) {
			this.hp = hp; this.damage = damage; this.shootRange = shootRange; this.moveRange = moveRange;
		}
		
		int hp, damage;
		int shootRange, moveRange;
	}
	
	private boolean alive;
	private Class unitClass;
	private Player.Team team;
	
	public Unit(Class unitClass, Player.Team team) {
		setUnitClass(unitClass);
		setTeam(team);
	}
	
	@Override
	public void draw() {
		
	}
	
	public int getHp() {
		return unitClass.hp;
	}
	public void setHp(int hp) {
		unitClass.hp = hp;
	}
	public int getDamage() {
		return unitClass.damage;
	}
	public void setDamage(int damage) {
		unitClass.damage = damage;
	}
	public int getShootRange() {
		return unitClass.shootRange;
	}
	public void setShootRange(int shootRange) {
		unitClass.shootRange = shootRange;
	}
	public int getMoveRange() {
		return unitClass.moveRange;
	}
	public void setMoveRange(int moveRange) {
		unitClass.moveRange = moveRange;
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

	public Player.Team getTeam() {
		return team;
	}

	public void setTeam(Player.Team team) {
		this.team = team;
	}
}
