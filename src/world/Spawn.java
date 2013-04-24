package world;

import graphics.Drawable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Spawn extends Drawable {

	private HashMap<Unit, Integer> spawnList = new HashMap<Unit, Integer>();
	private Animation animation;
	
	public Spawn(int x, int y, Tile parent, int team) {
		setxPos(x);
		setyPos(y);
		this.parent = parent;
		Image[] images = loadImages(team);
		animation = new Animation(images, 500);
		animation.start();
	}
	
	public Image[] loadImages(int team) {
		Image[] images = new Image[5];
		String teamString = (team == 0) ? "Red" : "Blue";
		for(int i = 0; i < images.length; i++) {
			try {
				images[i] = new Image("images/Tiles/Spawn/" + teamString + "/" + i + ".png");
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
		return images;
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
		animation.draw(xPos * Tile.TILE_SIZE, yPos * Tile.TILE_SIZE);
	}
	
}
