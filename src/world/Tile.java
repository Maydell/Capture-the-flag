package world;

import graphics.Drawable;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Tile extends Drawable {

	public static final int TILE_SIZE = 50;
	
	public static Image[] images;
	
	public enum Type {
		EMPTY(true, 0), GRASS(false, 1), ROCK(false, 2), WALL_GRASS_VERT(true), WALL_GRASS_HOR(
				true), WALL_GRASS_NW(true), WALL_GRASS_NE(true), WALL_GRASS_SE(
				true), WALL_GRASS_SW(true), WALL_ROCK_VERT(true), WALL_ROCK_HOR(
				true), WALL_ROCK_NW(true), WALL_ROCK_NE(true), WALL_ROCK_SE(
				true), WALL_ROCK_SW(true);

		boolean occupied;
		int id;

		Type(boolean occupied) {
			this.occupied = occupied;
		}
		
		Type(boolean occupied, int id) {
			this.occupied = occupied;
			this.id = id;
		}
	}

	ArrayList<Tile> neighbors;
	private Type type;
	private Drawable entity;

	public static void initTiles() {
		images = new Image[3];
		try {
			images[0] = new Image("images/tiles/empty.png");
			images[1] = new Image("images/tiles/grass.png");
			images[2] = new Image("images/tiles/rock.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
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
		this.setEntity(entity);
	}

	public boolean isOccupied() {
		return false;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.drawImage(images[type.id], xPos * TILE_SIZE, yPos * TILE_SIZE);
		g.drawRect(xPos * TILE_SIZE, yPos * TILE_SIZE, TILE_SIZE, TILE_SIZE);
	}

	// TODO
	public String toString() {
		return type.name();
	}

	public Drawable getEntity() {
		return entity;
	}

	public void setEntity(Drawable entity) {
		this.entity = entity;
	}

}
