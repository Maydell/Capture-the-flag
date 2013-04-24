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
		EMPTY(true, 0), GRASS(false, 1), ROCK(false, 2), WALL_GRASS_VERT(true, 3), WALL_GRASS_HOR(
				true, 4), WALL_GRASS_NW(true, 5), WALL_GRASS_NE(true, 6), WALL_GRASS_SE(
				true, 7), WALL_GRASS_SW(true, 8), WALL_ROCK_VERT(true, 9), WALL_ROCK_HOR(
				true, 10), WALL_ROCK_NW(true, 11), WALL_ROCK_NE(true, 12), WALL_ROCK_SE(
				true, 13), WALL_ROCK_SW(true, 14);

		boolean occupied;
		int id;
		
		Type(boolean occupied, int id) {
			this.occupied = occupied;
			this.id = id;
		}
	}

	private boolean occupied;
	ArrayList<Tile> neighbors;
	private Type type;
	private Drawable entity;

	public static void initTiles() {
		images = new Image[15];
		try {
			images[0] = new Image("images/tiles/empty.png");
			images[1] = new Image("images/tiles/grass.png");
			images[2] = new Image("images/tiles/rock.png");
			images[3] = new Image("images/tiles/Wall/wall_grass_vert.png");
			images[4] = new Image("images/tiles/Wall/wall_grass_hor.png");
			images[5] = new Image("images/tiles/Wall/wall_grass_NW.png");
			images[6] = new Image("images/tiles/Wall/wall_grass_NE.png");
			images[7] = new Image("images/tiles/Wall/wall_grass_SE.png");
			images[8] = new Image("images/tiles/Wall/wall_grass_SW.png");
			images[9] = new Image("images/tiles/Wall/wall_rock_vert.png");
			images[10] = new Image("images/tiles/Wall/wall_rock_hor.png");
			images[11] = new Image("images/tiles/Wall/wall_rock_NW.png");
			images[12] = new Image("images/tiles/Wall/wall_rock_NE.png");
			images[13] = new Image("images/tiles/Wall/wall_rock_SE.png");
			images[14] = new Image("images/tiles/Wall/wall_rock_SW.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public Tile(Type type, int xPos, int yPos) {
		this.type = type;
		setxPos(xPos);
		setyPos(yPos);
		neighbors = new ArrayList<Tile>();
		occupied = type.occupied;
	}

	public void addNeighbor(Tile neighbor) {
		if (neighbor.type.occupied)
			return;
		neighbors.add(neighbor);
	}
	
	public void removeEntity() {
		entity = null;
		occupied = false;
	}

	public boolean isOccupied() {
		return occupied;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.drawImage(images[type.id], xPos * TILE_SIZE, yPos * TILE_SIZE);
		g.drawRect(xPos * TILE_SIZE, yPos * TILE_SIZE, TILE_SIZE, TILE_SIZE);
		if (entity != null) entity.draw(g);
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
		occupied = true;
	}
}
