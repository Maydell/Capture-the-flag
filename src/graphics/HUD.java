package graphics;

import java.util.ArrayList;

import main.CTF;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.GUIContext;

import world.Player;
import world.Unit;

/**
 * The HUD class takes care of constant elements on the screen, like action bars and unit lists.
 * @author Mats Stichel, Isak Jagberg
 *
 */
public class HUD {

	Player player1, player2;
	ArrayList<Button> unitButtons = new ArrayList<Button>();
	Image actionbar;
	GUIContext container;
	Shape unitListLeft, unitListRight, actionbarShape;
	
	public HUD(GUIContext container, Player player1, Player player2) {
		unitListLeft = new Rectangle(0, 100, 50, 230);
		unitListRight = new Rectangle(container.getWidth() - 50, 100, 50, 230);
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
			unitButtons.add(new Button(container, image1, image1Hover, 0, 100 + 60 * i, "bdsf"));
			unitButtons.add(new Button(container, image2, image2Hover, CTF.WIDTH - image2.getWidth(), 100 + 60 * i, "bsd"));
		}
		try {
			actionbar = new Image("images/buttons/attacks/actionbar_base.png");
			actionbarShape = new Rectangle((container.getWidth() - actionbar.getWidth()) / 2, container.getHeight() - actionbar.getHeight(), actionbar.getWidth(), actionbar.getHeight());
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public boolean mouseOver() {
		int mouseY = container.getHeight() - Mouse.getY();
		int mouseX = Mouse.getX();
		return unitListLeft.contains(mouseX, mouseY) || unitListRight.contains(mouseX, mouseY) || actionbarShape.contains(mouseX, mouseY);
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.white);
		int centerX = CTF.WIDTH / 2;
		g.drawString("|", centerX, 10);
		g.drawString("" + player1.getScore(), centerX - 20, 10);
		g.drawString("" + player2.getScore(), centerX + 10, 10);
		g.setColor(new Color(0f, 0f, 0f, .7f));
		g.fill(unitListLeft);
		g.fill(unitListRight);
		for(Button b : unitButtons) {
			b.render(container, g);
		}
		g.drawImage(actionbar, (container.getWidth() - actionbar.getWidth()) / 2, container.getHeight() - actionbar.getHeight());
	}
}