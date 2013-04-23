package gamestates;

import graphics.Button;
import main.CTF;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Menu extends BasicGameState {

	Button quitButton;
	Button startButton;
	
	int id;

	GameContainer gc;

	public Menu(int id) {
		this.id = id;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.gc = gc;
		Image button = new Image("images/startButtonNormal.png");
		Image hover = new Image("images/startButtonHover.png");
		startButton = new Button(gc, button, hover, (gc.getWidth() - button.getWidth()) / 2, (gc.getHeight() - button.getHeight()) / 2 - 50);
		quitButton = new Button(gc, button, hover, (gc.getWidth() - button.getWidth()) / 2, (gc.getHeight() - button.getHeight()) / 2 + 50);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setColor(new Color(255, 255, 255));
		g.fillRect(0, 0, gc.getWidth(), gc.getHeight());
		startButton.render(gc, g);
		quitButton.render(gc, g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if (startButton.isClicked()) {
			sbg.enterState(CTF.GAME, new FadeOutTransition(), new FadeInTransition());
		} else if (quitButton.isClicked()) {
			gc.exit();
		}
		
	}

	@Override
	public int getID() {
		return 0;
	}

}
