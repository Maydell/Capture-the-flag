package world;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.newdawn.slick.Graphics;

/**
 * The map reads and translates .map-files to in-game graphics. It also contains
 * a 2d-array of all the tiles in the map.
 * 
 * @author Mats Stichel, Isak Jagberg
 * 
 */
public class Map {

	static Tile[][] tiles;
	Player player1, player2;
	String name;

	public Map(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
		loadMap();
	}

	/**
	 * Reads a .map-file using a Scanner. Read the README.txt file for more
	 * information.
	 */
	public void loadMap() {
		Spawn spawn1, spawn2;
		File file = new File("res/maps/Map.map");
		try {
			Scanner fileReader = new Scanner(file);
			name = fileReader.nextLine();
			fileReader.nextLine();
			tiles = new Tile[fileReader.nextInt()][fileReader.nextInt()];
			fileReader.nextLine();
			fileReader.nextLine();
			for (int y = 0; y < tiles[0].length; y++) {
				for (int x = 0; x < tiles.length; x++) {
					switch (fileReader.nextInt()) {
					case 0:
						tiles[x][y] = new Tile(Tile.Type.EMPTY, x, y);
						break;
					case 1:
						tiles[x][y] = new Tile(Tile.Type.GRASS, x, y);
						break;
					case 2:
						tiles[x][y] = new Tile(Tile.Type.ROCK, x, y);
						break;
					case 31:
						tiles[x][y] = new Tile(Tile.Type.WALL_GRASS_VERT, x, y);
						break;
					case 32:
						tiles[x][y] = new Tile(Tile.Type.WALL_GRASS_HOR, x, y);
						break;
					case 33:
						tiles[x][y] = new Tile(Tile.Type.WALL_GRASS_NW, x, y);
						break;
					case 34:
						tiles[x][y] = new Tile(Tile.Type.WALL_GRASS_NE, x, y);
						break;
					case 35:
						tiles[x][y] = new Tile(Tile.Type.WALL_GRASS_SE, x, y);
						break;
					case 36:
						tiles[x][y] = new Tile(Tile.Type.WALL_GRASS_SW, x, y);
						break;
					case 41:
						tiles[x][y] = new Tile(Tile.Type.WALL_ROCK_VERT, x, y);
						break;
					case 42:
						tiles[x][y] = new Tile(Tile.Type.WALL_ROCK_HOR, x, y);
						break;
					case 43:
						tiles[x][y] = new Tile(Tile.Type.WALL_ROCK_NW, x, y);
						break;
					case 44:
						tiles[x][y] = new Tile(Tile.Type.WALL_ROCK_NE, x, y);
						break;
					case 45:
						tiles[x][y] = new Tile(Tile.Type.WALL_ROCK_SE, x, y);
						break;
					case 46:
						tiles[x][y] = new Tile(Tile.Type.WALL_ROCK_SW, x, y);
						break;
					case 511:
						tiles[x][y] = new Tile(Tile.Type.GRASS, x, y);
						tiles[x][y].setFlag(new Flag(tiles[x][y], Player.BLUE));
						break;
					case 512:
						tiles[x][y] = new Tile(Tile.Type.ROCK, x, y);
						tiles[x][y].setFlag(new Flag(tiles[x][y], Player.BLUE));
						break;
					case 521:
						tiles[x][y] = new Tile(Tile.Type.GRASS, x, y);
						tiles[x][y].setFlag(new Flag(tiles[x][y], Player.RED));
						break;
					case 522:
						tiles[x][y] = new Tile(Tile.Type.ROCK, x, y);
						tiles[x][y].setFlag(new Flag(tiles[x][y], Player.RED));
						break;
					case 611:
						tiles[x][y] = new Tile(Tile.Type.GRASS, x, y);
						spawn1 = new Spawn(tiles[x][y], Player.RED);
						tiles[x][y].setSpawn(spawn1);
						player1.setSpawn(spawn1);
						break;
					case 612:
						tiles[x][y] = new Tile(Tile.Type.ROCK, x, y);
						spawn1 = new Spawn(tiles[x][y], Player.RED);
						tiles[x][y].setSpawn(spawn1);
						player1.setSpawn(spawn1);
						break;
					case 621:
						tiles[x][y] = new Tile(Tile.Type.GRASS, x, y);
						spawn2 = new Spawn(tiles[x][y], Player.BLUE);
						tiles[x][y].setSpawn(spawn2);
						player2.setSpawn(spawn2);
						break;
					case 622:
						tiles[x][y] = new Tile(Tile.Type.ROCK, x, y);
						spawn2 = new Spawn(tiles[x][y], Player.BLUE);
						tiles[x][y].setSpawn(spawn2);
						player2.setSpawn(spawn2);
						break;
					}
				}
			}
			fileReader.close();
			connectTiles();
			checkMap();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the Tile at the given x, y-coordinates. Divides by the size of
	 * the Tile so that the index is correct. Returns null if given negative
	 * coordinates.
	 * 
	 * @param x
	 *            The x-coordinate of the wanted Tile.
	 * @param y
	 *            The y-coordinate of the wanted Tile.
	 * @return The tile at (x, y).
	 */
	public Tile getTile(int x, int y) {
		if (x < 0 || x >= tiles.length * Tile.TILE_SIZE || y < 0
				|| y >= tiles[0].length * Tile.TILE_SIZE)
			return null;
		x /= Tile.TILE_SIZE;
		y /= Tile.TILE_SIZE;
		return tiles[x][y];
	}

	/**
	 * checkMap() checks so that the .map-file that was loaded was written
	 * correctly.
	 */
	public void checkMap() {
		if (player1.getSpawn() == null || player2.getSpawn() == null) {
			// The map must have exactly 1 spawn per player.
			System.err.println("Not enough spawns.");
			System.exit(0);
		}

		if (player1.getSpawn().getParent().neighbors.size() != 4
				|| player2.getSpawn().getParent().neighbors.size() != 4) {
			// The four neighbors to the Spawn objects have to be unoccupied.
			System.err.println("Neighbors to spawn can't be occupied.");
			System.exit(0);
		}
	}

	/**
	 * Iterates through Tile[][] tiles and connects the current Tile to any
	 * unoccupied Tile 1 step to the right, left, up or down.
	 */
	public void connectTiles() {
		for (int x = 0; x < tiles.length; x++) {
			for (int y = 0; y < tiles[0].length; y++) {
				if (x > 0)
					tiles[x][y].addNeighbor(tiles[x - 1][y]);
				if (x < tiles.length - 1)
					tiles[x][y].addNeighbor(tiles[x + 1][y]);
				if (y > 0)
					tiles[x][y].addNeighbor(tiles[x][y - 1]);
				if (y < tiles[0].length - 1)
					tiles[x][y].addNeighbor(tiles[x][y + 1]);
			}
		}
	}

	/**
	 * Draws the Tiles in the map.
	 * 
	 * @param g
	 *            The given Graphics object.
	 */
	public void draw(Graphics g) {
		for (int x = 0; x < tiles.length; x++) {
			for (int y = 0; y < tiles[0].length; y++) {
				if (tiles[x][y].getType() != Tile.Type.EMPTY) //Don't draw empty tiles.
					tiles[x][y].draw(g);
			}
		}
	}
}
