package gamestates;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
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
	
	boolean up, right, down, left;
	
	public Game(int id) {
		this.id = id;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		System.out.println("Entered Game-state");
		map = new Map();
		c = new Camera();
		c.setX(400);
		c.setY(200);
		Tile.initTiles();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.pushTransform();
		{
			c.useView(g);
			map.draw(g);
			
			// Temp to show camera location
			g.fillOval(c.getX() - 5, c.getY() - 5, 10, 10);
		}
		g.popTransform();
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		int speed = 1;
		if (up && !down) {
			c.move(0, -speed * delta);
		} else if(down && !up) {
			c.move(0, speed * delta);
		}
		
		if (right && !left) {
			c.move(speed * delta, 0);
		} else if (left && !right) {
			c.move(-speed * delta, 0);
		}
		
	}
	
	@Override
	public void keyPressed(int key, char c) {
		if (key == Input.KEY_UP || key == Input.KEY_W) up = true;
		if (key == Input.KEY_RIGHT || key == Input.KEY_D) right = true;
		if (key == Input.KEY_DOWN || key == Input.KEY_S) down = true;
		if (key == Input.KEY_LEFT || key == Input.KEY_A) left = true;
	}
	
	@Override
	public void keyReleased(int key, char c) {
		if (key == Input.KEY_UP || key == Input.KEY_W) up = false;
		if (key == Input.KEY_RIGHT || key == Input.KEY_D) right = false;
		if (key == Input.KEY_DOWN || key == Input.KEY_S) down = false;
		if (key == Input.KEY_LEFT || key == Input.KEY_A) left = false;
	}

	@Override
	public int getID() {
		return id;
	}

}
