package world;

import graphics.Drawable;

public class Tile extends Drawable {

	public enum Type {
		EMPTY(true), GRASS(false), ROCK(false), WALL_GRASS_VERT(true), WALL_GRASS_HOR(true), WALL_GRASS_NW(true), WALL_GRASS_NE(true), WALL_GRASS_SE(true), WALL_GRASS_SW(true), WALL_ROCK_VERT(true), WALL_ROCK_HOR(true), WALL_ROCK_NW(true), WALL_ROCK_NE(true), WALL_ROCK_SE(true), WALL_ROCK_SW(true);
		
		boolean occupied;
		
		Type(boolean occupied) {
			this.occupied = occupied;
		}
	}

	private Type type;
	private Drawable entity;

	public Tile(Type type, int xPos, int yPos) {
		this.type = type;
		setxPos(xPos);
		setyPos(yPos);
	}

	public void addEntity(Drawable entity) {
		this.entity = entity;
	}

<<<<<<< HEAD
//	public boolean isOccupied() {
//		if(type.occupied || (entity != null && entity.InstanceOf(Flag)));
//	}
=======
	public boolean isOccupied() {
		return false;
	} 
>>>>>>> Blorg

	@Override
	public void draw() {

	}

	// TODO
	public String toString() {
		return type.name();
	}

}
