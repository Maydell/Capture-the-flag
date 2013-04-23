package world;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Map {

	static Tile[][] tiles;
	Player player1, player2;
	String name;

	public Map() {
		loadMap();
	}
	
	public static void main(String[] args) {
		new Map();
	}

	public void loadMap() {
		File file = new File("maps/Map.map");
		System.out.println(file.exists());
		try {
			Scanner fileReader = new Scanner(file);
			name = fileReader.nextLine();
			fileReader.nextLine();
			tiles = new Tile[fileReader.nextInt()][fileReader.nextInt()];
			fileReader.nextLine();
			fileReader.nextLine();
			for (int x = 0; x < tiles.length; x++) {
				for (int y = 0; y < tiles[0].length; y++) {
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
						tiles[x][y] = new Tile(Tile.Type.WALL_GRASS_VERT, x, y);
						break;
					case 42:
						tiles[x][y] = new Tile(Tile.Type.WALL_GRASS_HOR, x, y);
						break;
					case 43:
						tiles[x][y] = new Tile(Tile.Type.WALL_GRASS_NW, x, y);
						break;
					case 44:
						tiles[x][y] = new Tile(Tile.Type.WALL_GRASS_NE, x, y);
						break;
					case 45:
						tiles[x][y] = new Tile(Tile.Type.WALL_GRASS_SE, x, y);
						break;
					case 46:
						tiles[x][y] = new Tile(Tile.Type.WALL_GRASS_SW, x, y);
						break;
					case 511:
						tiles[x][y] = new Tile(Tile.Type.GRASS, x, y);
						tiles[x][y].addEntity(new Flag(Player.Team.Blue));
						break;
					case 512:
						tiles[x][y] = new Tile(Tile.Type.ROCK, x, y);
						tiles[x][y].addEntity(new Flag(Player.Team.Blue));
						break;
					case 521:
						tiles[x][y] = new Tile(Tile.Type.GRASS, x, y);
						tiles[x][y].addEntity(new Flag(Player.Team.Red));
						break;
					case 522:
						tiles[x][y] = new Tile(Tile.Type.ROCK, x, y);
						tiles[x][y].addEntity(new Flag(Player.Team.Red));
						break;
					}
				}
			}
			fileReader.close();
			connectTiles();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(name + "\n\n");
		for (int x = 0; x < tiles.length; x++) {
			for(int y = 0; y < tiles[0].length; y++) {
				System.out.print(tiles[x][y] + " ");
			}
			System.out.println();
		}
	}
	
	public void connectTiles() {
		for(int x = 0; x < tiles.length; x++) {
			for (int y = 0; y < tiles[0].length; y++) {
				if(x > 0) 
					tiles[x][y].addNeighbor(tiles[x-1][y]);
				if(x < tiles.length - 1) 
					tiles[x][y].addNeighbor(tiles[x+1][y]);
				if(y > 0) 
					tiles[x][y].addNeighbor(tiles[x][y-1]);
				if (y < tiles[0].length - 1)
					tiles[x][y].addNeighbor(tiles[x][y+1]);
			}
		}
	}
}
