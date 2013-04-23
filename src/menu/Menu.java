package menu;

import graphics.Button;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Menu extends BasicGameState{

	Button quitButton;
	Button startButton;
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		Image button = new Image(100, 50);
		button.setImageColor(255, 255, 255);
		startButton = new Button(gc, button, 100, 40, 100, 200);
		quitButton = new Button(gc, button, 100, 140, 50, 100);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		startButton.render(gc, g);
		quitButton.render(gc, g);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
		
	}

	@Override
	public int getID() {
		return 0;
	}

}
