package main;

import gamestates.Game;
import gamestates.Menu;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * CTF holds the gamestates and decides which to show. 
 * It also initializes the GameContainer.
 * 
 * @author Mats Stichel, Isak Jagberg
 * 
 */
public class CTF extends StateBasedGame {

	// Used to set width and height of the window playing the game.
	public static final int WIDTH = 800, HEIGHT = 600;

	public static final int MENU = 0, GAME = 1;

	/**
	 * Creates a new StateBasedGame.
	 */
	public CTF() {
		super("Capture the flag");
	}

	/**
	 * Creates the States used for the Game.
	 */
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		addState(new Menu(MENU));
		addState(new Game(GAME));
		enterState(MENU);
	}

	/**
	 * The main method of the Game. Creates an AppGameContainer containing the
	 * game.
	 */
	public static void main(String[] args) throws SlickException {
		AppGameContainer gc = new AppGameContainer(new CTF());
		gc.setShowFPS(false);
		gc.setVSync(true);

		gc.setDisplayMode(WIDTH, HEIGHT, false);
		gc.start();
	}
}
