package world;

import graphics.Button;
import graphics.Entity;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;

/**
 * A Tile is what the game's map is built up of. Each Tile is stored in the list
 * in the Game's Map-object.
 * 
 * @author Mats Stichel, Isak Jagberg
 * 
 */
public class Tile extends Button {

	public enum Type {
		EMPTY(true, 0), GRASS(false, 1), ROCK(false, 2), WALL_GRASS_VERT(true,
				3), WALL_GRASS_HOR(true, 4), WALL_GRASS_NW(true, 5), WALL_GRASS_NE(
				true, 6), WALL_GRASS_SE(true, 7), WALL_GRASS_SW(true, 8), WALL_ROCK_VERT(
				true, 9), WALL_ROCK_HOR(true, 10), WALL_ROCK_NW(true, 11), WALL_ROCK_NE(
				true, 12), WALL_ROCK_SE(true, 13), WALL_ROCK_SW(true, 14);

		boolean occupied;
		int id;

		Type(boolean occupied, int id) {
			this.occupied = occupied;
			this.id = id;
		}
	}

	public static final int TILE_SIZE = 50;

	public static Image[] images;

	private boolean occupied;
	private ArrayList<Tile> neighbors;
	private Type type;
	private Entity entity;
	
	private int xPos, yPos;

	public Tile(GUIContext container, Type type, int xPos, int yPos) {
		super(container, images[type.id], xPos * TILE_SIZE, yPos * TILE_SIZE);
		Image hover = images[type.id].copy();
		hover.setImageColor(.8f, .8f, .8f);
		setMouseOverImage(hover);
		this.type = type;
		setxPos(xPos);
		setyPos(yPos);
		setNeighbors(new ArrayList<Tile>());
		occupied = type.occupied;
	}

	/**
	 * Called when entering the "Game" gamestate. Loads the graphics for all the
	 * types of Tile.
	 */
	public static void initTiles() {
		images = new Image[15];
		try {
			images[0] = new Image("images/drawables/tiles/empty.png");
			images[1] = new Image("images/drawables/tiles/grass.png");
			images[2] = new Image("images/drawables/tiles/rock.png");
			images[3] = new Image(
					"images/drawables/tiles/wall/wall_grass_vert.png");
			images[4] = new Image(
					"images/drawables/tiles/wall/wall_grass_hor.png");
			images[5] = new Image(
					"images/drawables/tiles/wall/wall_grass_NW.png");
			images[6] = new Image(
					"images/drawables/tiles/wall/wall_grass_NE.png");
			images[7] = new Image(
					"images/drawables/tiles/wall/wall_grass_SE.png");
			images[8] = new Image(
					"images/drawables/tiles/wall/wall_grass_SW.png");
			images[9] = new Image(
					"images/drawables/tiles/wall/wall_rock_vert.png");
			images[10] = new Image(
					"images/drawables/tiles/wall/wall_rock_hor.png");
			images[11] = new Image(
					"images/drawables/tiles/wall/wall_rock_NW.png");
			images[12] = new Image(
					"images/drawables/tiles/wall/wall_rock_NE.png");
			images[13] = new Image(
					"images/drawables/tiles/wall/wall_rock_SE.png");
			images[14] = new Image(
					"images/drawables/tiles/wall/wall_rock_SW.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Checks if the given Tile is occupied. If not, it's added to this tile's
	 * neighbor list.
	 * 
	 * @param neighbor
	 *            The given Tile.
	 */
	public void addNeighbor(Tile neighbor) {
		if (neighbor.type.occupied)
			return;
		getNeighbors().add(neighbor);
	}

	public void removeEntity() {
		entity = null;
		occupied = false;
	}

	public boolean isOccupied() {
		return occupied;
	}

//	/**
//	 * Draws the tile, using its respective Image object.
//	 */
//	@Override
//	public void render(GUIContext gc, Graphics g) {
//		g.setColor(Color.black);
//		g.drawImage(images[type.id], xPos * TILE_SIZE, yPos * TILE_SIZE);
//		g.drawRect(xPos * TILE_SIZE, yPos * TILE_SIZE, TILE_SIZE, TILE_SIZE);
//		if (entity != null)
//			entity.draw(g);
//	}

	public Entity getEntity() {
		return entity;
	}

	public Type getType() {
		return type;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
		occupied = true;
	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public ArrayList<Tile> getNeighbors() {
		return neighbors;
	}

	public void setNeighbors(ArrayList<Tile> neighbors) {
		this.neighbors = neighbors;
	}
}
