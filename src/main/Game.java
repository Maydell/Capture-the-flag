package main;

import menu.Menu;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame {
	
	BasicGameState menu;

	public Game() {
		super("Capture the flag");
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		menu = new Menu();
		addState(menu);
		enterState(menu.getID());
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws SlickException{
		AppGameContainer gc = new AppGameContainer(new Game());
		
		gc.setDisplayMode(800, 600, false);
		gc.start();
	}
}
