package main;

import gamestates.Game;
import gamestates.Menu;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class CTF extends StateBasedGame {
	
	public static final int WIDTH = 800, HEIGHT = 600;
	
	public static final int MENU = 0, GAME = 1;
	BasicGameState menu;

	public CTF() {
		super("Capture the flag");
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		addState(new Menu(MENU));
		addState(new Game(GAME));
		enterState(MENU);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws SlickException{
		AppGameContainer gc = new AppGameContainer(new CTF());
		gc.setShowFPS(false);
		
		gc.setDisplayMode(WIDTH, HEIGHT, false);
		gc.start();
	}
}
