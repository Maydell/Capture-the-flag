package gamestates;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import world.Camera;
import world.Map;
import world.Tile;

public class Game extends BasicGameState {

	private int id;
	Map map;
	Camera c;
	
	public Game(int id) {
		this.id = id;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		System.out.println("Entered Game-state");
		map = new Map();
		c = new Camera();
		Tile.initTiles();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.pushTransform();
		{
			c.useView(g);
			map.draw(g);
		}
		g.popTransform();
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
	}

	@Override
	public int getID() {
		return id;
	}

}
