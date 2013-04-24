package world;

import graphics.Drawable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.newdawn.slick.Graphics;

public class Spawn extends Drawable {

	private HashMap<Unit, Integer> spawnList = new HashMap<Unit, Integer>();
	
	public Spawn(int x, int y, Tile parent) {
		setxPos(x);
		setyPos(y);
		this.parent = parent;
	}
	
	public Unit createUnit(Unit.Class unitClass, int team) {
		Unit unit = new Unit(unitClass, team);
		spawn(unit);
		return unit;
	}
	
	public void spawn(Unit unit) {
		System.out.println("Spawned player!");
		for (Tile neighbor : parent.neighbors) {
			if (!neighbor.isOccupied()) {
				unit.moveTo(neighbor);
				unit.setAlive(true);
			}
		}
	}
	
	public void spawn() {
		Iterator<Entry<Unit, Integer>> i = spawnList.entrySet().iterator();
		while (i.hasNext()) {
			Entry<Unit, Integer> item = i.next();
			int spawnTime = item.getValue();
			if (spawnTime <= 1) {
				spawn(item.getKey());
				i.remove();
			}
		}
	}
	
	public void add(Unit unit) {
		spawnList.put(unit, unit.getUnitClass().spawnTime);
	}
	
	@Override
	public void draw(Graphics g) {
		
	}
	
}
