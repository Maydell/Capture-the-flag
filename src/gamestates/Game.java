package gamestates;

import graphics.HUD;
import java.util.ArrayList;
import main.CTF;
import main.Pathfinding;
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
import world.Unit;

/**
 * This gamestate contains the information relevant to the Game, including
 * things like the Map, players and camera. It handles the input and distributes
 * it to the contained elements. It handles the turns.
 * 
 * @author Mats Stichel, Isak Jagberg
 * 
 */
public class Game extends BasicGameState {

	private int id;
	Map map;
	Camera c;
	Player player1, player2;
	public static Player active;
	HUD hud;
	public static int turns = 40;

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
		Tile.initTiles();
		Unit.initUnits();
		// Creates players
		player1 = new Player(Player.RED);
		player2 = new Player(Player.BLUE);
		// Creates map
		map = new Map(player1, player2);
		// Sets up units for players
		player1.setupTeam();
		player2.setupTeam();
		// Creates HUD
		hud = new HUD(gc, player1, player2);
		// Creates camera
		c = new Camera(0.5f);
		// Set temporary position for camera
		c.setX(400);
		c.setY(200);
		// Starts turn for player 1
		if (Math.random() > 0.5)
			active = player1;
		else
			active = player2;
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
		Tile mouseOver = Map.getTile(Mouse.getX() + c.getX() - CTF.WIDTH
				/ 2, CTF.HEIGHT - Mouse.getY() + c.getY() - CTF.HEIGHT / 2);
		g.setColor(Color.black);
		g.fillRect(0, 0, CTF.WIDTH, CTF.HEIGHT);
		g.pushTransform();
		{
			c.useView(g);
			map.draw(g);
			if (mouseOver != null && mouseOver.getType() != Tile.Type.EMPTY
					&& !hud.mouseOver()) {
				g.setColor(new Color(1f, 1f, 1f, .2f));
				mouseOver.highlight(g);
				if (Player.selected != null) {
					ArrayList<Tile> path = Pathfinding.findPath(
							Player.selected.getParent(), mouseOver);

					if (path.size() > Player.selected.getMovement())
						g.setColor(new Color(1f, .2f, .2f, .5f));
					else if (2 * (path.size() - Player.selected.getMovement())
							+ Player.selected.getMoveRange() <= 0)
						g.setColor(new Color(.2f, 1f, .2f, .5f));

					for (Tile t : path) {
						t.highlight(g);
					}
				}
			}
		}
		g.popTransform();
		hud.draw(g);
		if (mouseOver != null && mouseOver.getUnit() != null && (Player.selected == null || mouseOver.getUnit().getTeam() != Player.selected.getTeam())) {
			hud.drawUnitInfo(g, mouseOver.getUnit());
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {

		Unit selected = hud.selectUnit();
		if (selected != null) {
			c.target(selected);
		}
		moveCamera(delta);
	}

	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
		if (!hud.mouseOver()) {
			x += c.getX() - CTF.WIDTH / 2;
			y += c.getY() - CTF.HEIGHT / 2;
			Tile clicked = Map.getTile(x, y);
			if (clicked != null) {
				if (button == 0) {
					Unit u = clicked.getUnit();
					if (u != null && u.isAlive()
							&& u.getTeam() == active.getTeam())
						Player.selected = u;
					else
						Player.selected = null;
				} else if (button == 1 && Player.selected != null) {
					if (clicked.getUnit() != null) {
						if (Player.selected.attack(clicked.getUnit()) && clicked.getUnit().getTeam() != Player.selected.getTeam())
							active.increaseScore(10);
					} else
						Player.selected.moveTo(clicked);
				}
			}
		}
	}

	/**
	 * Moves the camera.
	 * 
	 * @param delta
	 *            The tick time.
	 */
	public void moveCamera(int delta) {
		if (up && !down) {
			c.move(0, -1, delta);
		} else if (down && !up) {
			c.move(0, 1, delta);
		}

		if (right && !left) {
			c.move(1, 0, delta);
		} else if (left && !right) {
			c.move(-1, 0, delta);
		}
		c.move(0, 0, delta);
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

		if (key == Input.KEY_SPACE) {
			active.finishTurn();
			turns--;
			if (turns == 0) {
				if (player1.getScore() > player2.getScore()) {
					HUD.feed.add("Player 1 won the game.", -1);
				} else if (player2.getScore() > player1.getScore()) {
					HUD.feed.add("Player 2 won the game.", -1);
				} else {
					HUD.feed.add("It's a tie.", -1);
				}
			}
			active = (active == player1) ? player2 : player1;
			Player.selected = null;
			active.turn();
		}
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
