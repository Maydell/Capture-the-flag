package world;

import graphics.Drawable;

import org.newdawn.slick.Graphics;

public class Spawn extends Drawable {

	public Spawn(int x, int y) {
		setxPos(x);
		setyPos(y);
	}
	
	public Unit createUnit(Unit.Class unitClass, Player.Team team) {
		Unit unit = new Unit(unitClass, team);
		spawn(unit);
		return unit;
	}
	
	public void spawn(Unit unit) {
		unit.setAlive(true);
		
	}

	@Override
	public void draw(Graphics g) {
		
	}
	
}
