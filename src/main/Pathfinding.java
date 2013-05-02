package main;

import java.util.ArrayList;

import world.Tile;

public class Pathfinding {
	
	private static class Square {
		public Square parent;
		public Tile tile;
		public Square(Square parent, Tile tile) {
			this.parent = parent;
			this.tile = tile;
		}
	}

	public static ArrayList<Square> opened;
	public static ArrayList<Square> closed;
	
	public static int getHeuristic(Square s1, Square s2) {
		return Math.abs(s1.tile.getxPos() - s2.tile.getxPos()) + Math.abs(s1.tile.getyPos() - s2.tile.getyPos());
	}
	
	public static ArrayList<Tile> findPath(Tile start, Tile goal) {
		opened = new ArrayList<Square>();
		closed = new ArrayList<Square>();
		ArrayList<Tile> path = new ArrayList<Tile>();
		Square first = new Square(null, start);
		Square last = new Square(null, goal);
		opened.add(first);
		while (opened.size() > 0) {
			Square shortest = findShortest(last);
			if (shortest.tile == goal) {
				path = createPath(shortest);
				break;
			}
			closed.add(shortest);
			opened.remove(shortest);
			opened.addAll(findNeighbors(shortest));
		}
		return path;
	}
	
	public static ArrayList<Tile> createPath(Square start) {
		ArrayList<Tile> path = new ArrayList<Tile>();
		while (start.parent != null) {
			path.add(start.tile);
			start = start.parent;
		}
		return path;
	}
	
	public static Square findShortest(Square goal) {
		Square shortest = opened.get(0);
		for (Square s : opened) {
			if (getHeuristic(s, goal) < getHeuristic(shortest, goal))
				shortest = s;
		}
		return shortest;
	}
	
	public static ArrayList<Square> findNeighbors(Square s) {
		ArrayList<Square> neighbors = new ArrayList<Square>();
		for (Tile t : s.tile.getNeighbors()) {
			boolean skip = false;
			for (Square sq : closed) {
				if (t == sq.tile) {
					skip = true;
					break;
				}
			}
			for (Square sq : opened) {
				if (t == sq.tile) {
					if (getPathLength(sq) > getPathLength(sq) + 1) {
						sq.parent = s;
					}
					skip = true;
					continue;
				}
			}
			if (!skip)
				neighbors.add(new Square(s, t));
		}
		return neighbors;
	}
	
	public static int getPathLength(Square s) {
		int length = 0;
		while (s.parent != null) {
			length++;
			s = s.parent;
		}
		return length;
	}
	
}
