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

/**
 * This gamestate contains the information relevant to the main menu.
 * 
 * @author Mats Stichel, Isak Jagberg
 * 
 */
public class Menu extends BasicGameState {

	Button startButton, quitButton;
	Image logo;

	int id;

	GameContainer gc;

	public Menu(int id) {
		this.id = id;
	}

	// TODO
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		this.gc = gc;
		Image normalStart = new Image("images/buttons/startButtonNormal.png");
		Image hoverStart = new Image("images/buttons/startButtonHover.png");
		Image normalQuit = new Image("images/buttons/quitButtonNormal.png");
		Image hoverQuit = new Image("images/buttons/quitButtonHover.png");
		startButton = new Button(gc, normalStart, hoverStart,
				(gc.getWidth() - normalStart.getWidth()) / 2,
				(gc.getHeight() - normalStart.getHeight()) / 2 - 50);
		quitButton = new Button(gc, normalQuit, hoverQuit,
				(gc.getWidth() - normalQuit.getWidth()) / 2,
				(gc.getHeight() - normalQuit.getHeight()) / 2 + 50);
		logo = new Image("res/images/logo.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.setColor(new Color(255, 255, 255));
		g.fillRect(0, 0, gc.getWidth(), gc.getHeight());
		startButton.render(gc, g);
		quitButton.render(gc, g);
		g.drawImage(logo, (gc.getWidth() - logo.getWidth()) / 2, 50);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		if (startButton.isClicked()) {
			sbg.enterState(CTF.GAME, new FadeOutTransition(),
					new FadeInTransition());
		} else if (quitButton.isClicked()) {
			gc.exit();
		}

	}

	@Override
	public int getID() {
		return 0;
	}

}
