package gamestates;

import graphics.Button;
import main.CTF;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Menu extends BasicGameState {

	Button quitButton;
	Button startButton;
	
	int id;

	GameContainer gc;

	public Menu(int id) {
		this.id = id;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		this.gc = gc;
		Image button = new Image("grass.jpg");
		startButton = new Button(gc, button, 100, 40, 100, 50);
		quitButton = new Button(gc, button, 100, 140, 100, 50);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		startButton.render(gc, g);
		quitButton.render(gc, g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if (startButton.isClicked()) {
			sbg.enterState(CTF.GAME);
		} else if (quitButton.isClicked()) {
			gc.exit();
		}
		
	}

	@Override
	public int getID() {
		return 0;
	}

}
