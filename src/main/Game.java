package main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame {

	public Game() {
		super("Capture the flag");
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws SlickException{
		AppGameContainer gc = new AppGameContainer(new Game());
		
		gc.setDisplayMode(800, 600, false);
		gc.start();
	}

	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		// TODO Auto-generated method stub
		
	}

}
