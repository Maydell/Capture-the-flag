package menu;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Menu extends BasicGameState{

	MouseOverArea quitButton;
	MouseOverArea startButton;
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		Image button = new Image("grass.jpg");
		startButton = new MouseOverArea(gc, button, 100, 40, 50, 50);
//		startButton.setNormalColor(new Color(255, 0, 0));
		quitButton = new MouseOverArea(gc, button, 100, 40, 50, 150);
//		quitButton.setNormalColor(new Color(0, 255, 0));
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getID() {
		return 0;
	}

}
