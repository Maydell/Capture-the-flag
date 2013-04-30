package graphics;

import java.util.ArrayList;

import main.CTF;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.GUIContext;

import world.Player;
import world.Unit;

/**
 * The HUD class takes care of constant elements on the screen, like action bars and unit lists.
 * @author Mats Stichel, Isak Jagberg
 *
 */
public class HUD extends AbstractComponent {

	Player player1, player2;
	ArrayList<Button> unitButtons = new ArrayList<Button>();
	GUIContext container;
	
	public HUD(GUIContext container, Input input, Player player1, Player player2) {
		super(container);
		setInput(input);
		this.player1 = player1;
		this.player2 = player2;
		ArrayList<Unit> units1 = player1.getUnits();
		ArrayList<Unit> units2 = player2.getUnits();
		this.container = container;
		for (int i = 0; i < units1.size(); i++) {
			Unit u1 = units1.get(i);
			Unit u2 = units2.get(i);
			Image image1 = Unit.images[u1.getID()][u1.getTeam()];
			Image image1Hover = image1.copy();
			image1Hover.setImageColor(.8f, .8f, .8f);
			Image image2 = Unit.images[u2.getID()][u2.getTeam()];
			Image image2Hover = image2.copy();
			image2Hover.setImageColor(.8f, .8f, .8f);
			unitButtons.add(new Button(container, image1, image1Hover, 0, 100 + 60 * i));
			unitButtons.add(new Button(container, image2, image2Hover, CTF.WIDTH - image2.getWidth(), 100 + 60 * i));
		}
	}
	
	@Override
	public void setInput(Input input) {
		this.input = input;
		for (Button b : unitButtons) {
			b.setInput(input);
		}
	}
	
	public boolean isMouseOver() {
		for (Button b : unitButtons) {
			if (b.isMouseOver()) return true;
		}
		return false;
	}
	
	@Override
	public void render(GUIContext gc, Graphics g) throws SlickException {
		g.setColor(Color.white);
		int centerX = gc.getWidth() / 2;
		g.drawString("|", centerX, 10);
		g.drawString("" + player1.getScore(), centerX - 20, 10);
		g.drawString("" + player2.getScore(), centerX + 10, 10);
		for(Button b : unitButtons) {
			b.render(container, g);
		}
	}
	
	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
		for (Button b : unitButtons) {
			if(b.isClicked()) {
				input.clearMousePressedRecord();
			}
		}
	}

	@Override
	public int getHeight() {
		return 0;
	}

	@Override
	public int getWidth() {
		return 0;
	}

	@Override
	public int getX() {
		return 0;
	}

	@Override
	public int getY() {
		return 0;
	}

	@Override
	public void setLocation(int arg0, int arg1) {
		
	}
}