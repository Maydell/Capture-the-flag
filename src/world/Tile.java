package world;

import java.util.ArrayList;

import graphics.Drawable;

public class Tile extends Drawable {

	public enum Type {
		EMPTY(true), GRASS(false), ROCK(false), WALL_GRASS_VERT(true), WALL_GRASS_HOR(
				true), WALL_GRASS_NW(true), WALL_GRASS_NE(true), WALL_GRASS_SE(
				true), WALL_GRASS_SW(true), WALL_ROCK_VERT(true), WALL_ROCK_HOR(
				true), WALL_ROCK_NW(true), WALL_ROCK_NE(true), WALL_ROCK_SE(
				true), WALL_ROCK_SW(true);

		boolean occupied;

		Type(boolean occupied) {
			this.occupied = occupied;
		}
	}

	ArrayList<Tile> neighbors;
	private Type type;
	private Drawable entity;

	public Tile(Type type, int xPos, int yPos) {
		this.type = type;
		setxPos(xPos);
		setyPos(yPos);
		neighbors = new ArrayList<Tile>();
	}

	public void addNeighbor(Tile neighbor) {
		if (neighbor.type.occupied)
			return;
		neighbors.add(neighbor);
	}

	public void addEntity(Drawable entity) {
		this.entity = entity;
	}

	public boolean isOccupied() {
		return false;
	}

	@Override
	public void draw() {

	}

	// TODO
	public String toString() {
		return type.name();
	}

}
