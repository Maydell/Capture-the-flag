package gamestates;

import graphics.Drawable;
import main.CTF;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import world.Camera;
import world.Map;
import world.Player;
import world.Tile;
import graphics.HUD;

/**
 * This gamestate contains the information relevant to the Game, including
 * things like the Map, players and camera.
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
		Drawable.init();
		player1 = new Player(Player.RED);
		player2 = new Player(Player.BLUE);
		map = new Map(player1, player2);
		player1.setupTeam();
		player2.setupTeam();
		hud = new HUD(gc, player1, player2);
		c = new Camera(0.5f);
		c.setX(400);
		c.setY(200);
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
			map.draw(g);
			Tile mouseOver = map.getTile(Mouse.getX() + (int) c.getX()
					- CTF.WIDTH / 2, CTF.HEIGHT - Mouse.getY() + (int) c.getY()
					- CTF.HEIGHT / 2);
			if (mouseOver != null && mouseOver.getType() != Tile.Type.EMPTY) {
				// System.out.println("x: " + mouseOver.getxPos() + ", y: " + mouseOver.getyPos() + "\nMouse x: " + Mouse.getX() + ", y: " + Mouse.getY());
				g.setColor(new Color(1f, 1f, 1f, .2f));
				g.fillRect(mouseOver.getxPos() * Tile.TILE_SIZE + 1,
						mouseOver.getyPos() * Tile.TILE_SIZE + 1,
						Tile.TILE_SIZE - 1, Tile.TILE_SIZE - 1);
			}
		}
		g.popTransform();
		hud.draw(g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		moveCamera(delta);
		if (active.done()) {
			active = (active == player1) ? player2 : player1;
			active.turn();
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
