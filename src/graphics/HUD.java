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
 * The HUD class takes care of constant elements on the screen, like action bars
 * and unit lists.
 * 
 * @author Mats Stichel, Isak Jagberg
 * 
 */
public class HUD {

	Player[] players = new Player[2];
	ArrayList<UnitButton> unitButtons = new ArrayList<UnitButton>();
	ArrayList<ActionButton> actionButtons = new ArrayList<ActionButton>();
	Image actionbar;
	GUIContext container;
	Shape unitListLeft, unitListRight, actionbarShape;

	public HUD(GUIContext container, Player player1, Player player2) {
		unitListLeft = new Rectangle(0, 100, 50, 230);
		unitListRight = new Rectangle(container.getWidth() - 50, 100, 50, 230);
		try {
			actionbar = new Image("images/buttons/attacks/actionbar_base.png");
			actionbarShape = new Rectangle(
					(container.getWidth() - actionbar.getWidth()) / 2,
					container.getHeight() - actionbar.getHeight(),
					actionbar.getWidth(), actionbar.getHeight());
		} catch (SlickException e) {
			e.printStackTrace();
		}
		players[0] = player1;
		players[1] = player2;
		for (int i = 0; i < 3; i++) {
			try {
				Image[] images = new Image[2];
				images[0] = new Image("images/buttons/attacks/" + i + "_" + 0
						+ ".png");
				images[1] = new Image("images/buttons/attacks/" + i + "_" + 1
						+ ".png");
				actionButtons.add(new ActionButton(container, images,
						(int) actionbarShape.getMinX() + 8 + (103 * i),
						(int) actionbarShape.getMinY() + 3, i, "" + i));
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
		ArrayList<Unit> units1 = player1.getUnits();
		ArrayList<Unit> units2 = player2.getUnits();
		this.container = container;
		for (int i = 0; i < units1.size(); i++) {
			Unit u1 = units1.get(i);
			Unit u2 = units2.get(i);
			unitButtons.add(new UnitButton(container, 0, 100 + 60 * i, u1,
					"Unit button red " + i));
			unitButtons.add(new UnitButton(container, CTF.WIDTH - 50,
					100 + 60 * i, u2, "Unit button blue " + i));
		}
	}

	public boolean mouseOver() {
		int mouseY = container.getHeight() - Mouse.getY();
		int mouseX = Mouse.getX();
		return unitListLeft.contains(mouseX, mouseY)
				|| unitListRight.contains(mouseX, mouseY)
				/*|| actionbarShape.contains(mouseX, mouseY)*/;
	}

	public Unit selectUnit() {
		for (UnitButton b : unitButtons) {
			if (b.isClicked()) {
				return b.unit;
			}
		}
		return null;
	}

	public void draw(Graphics g) {
		g.setColor(Color.white);
		int centerX = CTF.WIDTH / 2;
		g.drawString("|", centerX, 10);
		g.drawString("" + players[0].getScore(), centerX - 20, 10);
		g.drawString("" + players[1].getScore(), centerX + 10, 10);
		g.setColor(new Color(0f, 0f, 0f, .7f));
		g.fill(unitListLeft);
		g.fill(unitListRight);
		for (Button b : unitButtons) {
			b.render(container, g);
		}
//		if (Player.selected != null
//				&& !players[Player.selected.getTeam()].isDone())
//			drawActionbar(g);
	}

	public void drawActionbar(Graphics g) {
		g.drawImage(actionbar,
				(container.getWidth() - actionbar.getWidth()) / 2,
				container.getHeight() - actionbar.getHeight());
		for (ActionButton ab : actionButtons) {
			ab.setTeam(Player.selected.getTeam());
			ab.render(container, g);
		}
	}
}