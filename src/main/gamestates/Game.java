package main.gamestates;

import graphics.HUD;
import graphics.Map;
import main.CTF;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import world.Camera;
import world.Player;
import world.Tile;
import world.Unit;

/**
 * This gamestate contains the information relevant to the Game, including
 * things like the Map, players and camera.
 * It handles the input and distributes it to the contained elements.
 * It handles the turns.
 * 
 * @author Mats Stichel, Isak Jagberg
 * 
 */
public class Game extends BasicGameState {

	private int id;
	Map map;
	Camera c;
	Player player1, player2, active;
	HUD hud;
	Input mapInput, hudInput;

	boolean up, right, down, left;

	public Game(int id) {
		this.id = id;
	}

	/**
	 * Called when the state is entered, initializing and creating relevant
	 * information for the state.
	 */
	@Override
	public void enter(GameContainer gc, StateBasedGame sbg) {
		mapInput = new Input(0);
		hudInput = new Input(1);
		Tile.initTiles();
		Unit.initUnits();
		// Creates players
		player1 = new Player(Player.RED);
		player2 = new Player(Player.BLUE);
		// Creates map
		map = new Map(gc, mapInput, player1, player2);
		// Sets up units for players
		player1.setupTeam();
		player2.setupTeam();
		// Creates HUD
		hud = new HUD(gc, hudInput, player1, player2);
		hud.setInput(hudInput);
		// Creates camera
		c = new Camera(0.5f);
		// Set temporary position for camera
		c.setX(400);
		c.setY(200);
		// Starts turn for player 1
		active = player1;
		active.turn();
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
	}

	// TODO
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.setColor(Color.cyan);
		g.fillRect(0, 0, CTF.WIDTH, CTF.HEIGHT);
		g.pushTransform();
		{
			c.useView(g);
			map.render(gc, g);
			/*Tile mouseOver = map.getTile(Mouse.getX() + (int) c.getX()
					- CTF.WIDTH / 2, CTF.HEIGHT - Mouse.getY() + (int) c.getY()
					- CTF.HEIGHT / 2);
			if (mouseOver != null && mouseOver.getType() != Tile.Type.EMPTY) {
				g.setColor(new Color(1f, 1f, 1f, .2f));
				g.fillRect(mouseOver.getxPos() * Tile.TILE_SIZE + 1,
						mouseOver.getyPos() * Tile.TILE_SIZE + 1,
						Tile.TILE_SIZE - 1, Tile.TILE_SIZE - 1);
			}*/
		}
		g.popTransform();
		hud.render(gc, g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		moveCamera(delta);
		mapInput.setOffset(50000000, 587325398);
		if (active.done()) {
			active = (active == player1) ? player2 : player1;
			active.turn();
		}
		if(hud.isMouseOver()) {
			System.out.println("Mouse over HUD");
		}
	}

	/**
	 * Moves the camera.
	 * 
	 * @param delta
	 *            The tick time.
	 */
	public void moveCamera(int delta) {
		float speed = c.getSpeed();
		if (up && !down) {
			c.move(0, -speed * delta);
		} else if (down && !up) {
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
		if (key == Input.KEY_UP || key == Input.KEY_W)
			up = true;
		if (key == Input.KEY_RIGHT || key == Input.KEY_D)
			right = true;
		if (key == Input.KEY_DOWN || key == Input.KEY_S)
			down = true;
		if (key == Input.KEY_LEFT || key == Input.KEY_A)
			left = true;
	}

	@Override
	public void keyReleased(int key, char c) {
		if (key == Input.KEY_UP || key == Input.KEY_W)
			up = false;
		if (key == Input.KEY_RIGHT || key == Input.KEY_D)
			right = false;
		if (key == Input.KEY_DOWN || key == Input.KEY_S)
			down = false;
		if (key == Input.KEY_LEFT || key == Input.KEY_A)
			left = false;
	}

	@Override
	public int getID() {
		return id;
	}

}
