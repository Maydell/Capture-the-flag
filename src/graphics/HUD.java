package graphics;

import gamestates.Game;

import java.util.ArrayList;

import main.CTF;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
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
	GUIContext container;
	Shape unitListLeft, unitListRight;
	public static Feed feed;

	public HUD(GUIContext container, Player player1, Player player2) {
		feed = new Feed();
		unitListLeft = new Rectangle(0, 100, 50, 230);
		unitListRight = new Rectangle(container.getWidth() - 50, 100, 50, 230);
		players[0] = player1;
		players[1] = player2;
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
				|| unitListRight.contains(mouseX, mouseY);
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
		String scoreText = players[0].getScore() + "|" + players[1].getScore();
		int width = g.getFont().getWidth(scoreText);
		g.drawString(scoreText, centerX - width / 2, 10);
		String turns = Game.turns / 2 + " turns left";
		width = g.getFont().getWidth(turns);
		g.drawString(turns, centerX - width / 2, 30);
		g.setColor(new Color(0f, 0f, 0f, .7f));
		g.fill(unitListLeft);
		g.fill(unitListRight);
		for (Button b : unitButtons) {
			b.render(container, g);
		}
		feed.draw(g);
		if (Player.selected != null) {
			drawUnitInfo(g, Player.selected);
		}
	}

	public void drawUnitInfo(Graphics g, Unit u) {
		int x, y = CTF.HEIGHT - 180;
		Color teamColor;
		if (u.getTeam() == Player.RED) {
			teamColor = Color.red;
			x = 20;
		} else {
			x = CTF.WIDTH - 180;
			teamColor = Color.blue;
		}
		g.setColor(new Color(0f, 0f, 0f, .7f));
		g.fillRect(x, y, 160, 160);
		g.setColor(teamColor);
		g.drawRect(x, y, 160, 160);
		g.setColor(Color.white);
		g.drawString(u.toString(), x + 10, y + 10);
		g.drawString("HP: " + u.getHp() + "/" + u.getMaxHP(), x + 10, y + 25);
		g.drawString("Actions left: " + u.getMovement(), x + 10, y + 40);
		g.drawString("Damage: " + u.getDamage(), x + 10, y + 55);
		if (!u.isAlive()) {
			if (players[u.getTeam()].getSpawn().spawnList.containsKey(u))
				g.drawString(
						"Dead. Spawn in "
								+ (players[u.getTeam()].getSpawn().spawnList
										.get(u) + 1), x + 10, y + 70);
			else
				g.drawString(
						"Dead. Spawn in "
								+ (u.getSpawnTime() + 1), x + 10, y + 70);
		}
	}
}