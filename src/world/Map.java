package world;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Map {

	Tile[][] tiles;
	Player player1, player2;
	String name;

	public Map() {
		loadMap();
	}
	
	public static void main(String[] args) {
		new Map();
	}

	public void loadMap() {
		File file = new File("res/Map.map");
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
						tiles[x][y] = new Tile(Tile.Type.EMPTY);
						break;
					case 1:
						tiles[x][y] = new Tile(Tile.Type.GRASS);
						break;
					case 2:
						tiles[x][y] = new Tile(Tile.Type.ROCK);
						break;
					case 31:
						tiles[x][y] = new Tile(Tile.Type.WALL_GRASS_VERT);
						break;
					case 32:
						tiles[x][y] = new Tile(Tile.Type.WALL_GRASS_HOR);
						break;
					case 33:
						tiles[x][y] = new Tile(Tile.Type.WALL_GRASS_NW);
						break;
					case 34:
						tiles[x][y] = new Tile(Tile.Type.WALL_GRASS_NE);
						break;
					case 35:
						tiles[x][y] = new Tile(Tile.Type.WALL_GRASS_SE);
						break;
					case 36:
						tiles[x][y] = new Tile(Tile.Type.WALL_GRASS_SW);
						break;
					case 41:
						tiles[x][y] = new Tile(Tile.Type.WALL_GRASS_VERT);
						break;
					case 42:
						tiles[x][y] = new Tile(Tile.Type.WALL_GRASS_HOR);
						break;
					case 43:
						tiles[x][y] = new Tile(Tile.Type.WALL_GRASS_NW);
						break;
					case 44:
						tiles[x][y] = new Tile(Tile.Type.WALL_GRASS_NE);
						break;
					case 45:
						tiles[x][y] = new Tile(Tile.Type.WALL_GRASS_SE);
						break;
					case 46:
						tiles[x][y] = new Tile(Tile.Type.WALL_GRASS_SW);
						break;
					case 511:
						tiles[x][y] = new Tile(Tile.Type.GRASS);
						tiles[x][y].addEntity(new Flag(Player.Team.Blue));
						break;
					case 512:
						tiles[x][y] = new Tile(Tile.Type.ROCK);
						tiles[x][y].addEntity(new Flag(Player.Team.Blue));
						break;
					case 521:
						tiles[x][y] = new Tile(Tile.Type.GRASS);
						tiles[x][y].addEntity(new Flag(Player.Team.Red));
						break;
					case 522:
						tiles[x][y] = new Tile(Tile.Type.ROCK);
						tiles[x][y].addEntity(new Flag(Player.Team.Red));
						break;
					}
				}
			}

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
}
