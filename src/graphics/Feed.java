package graphics;

import main.CTF;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import world.Player;

public class Feed {

	final int MAX = 10;

	FeedItem first;
	int length;

	private class FeedItem {
		FeedItem next;
		String text;
		int team;

		public FeedItem(String text, FeedItem next, int team) {
			this.text = text;
			this.next = next;
			this.team = team;
		}
	}

	public Feed() {

	}
	
	public void draw(Graphics g) {
		if (length == 0) return;
		int xOffset = 200;
		g.setColor(new Color(0f, 0f, 0f, .7f));
		g.fillRect(xOffset, CTF.HEIGHT, 400, -(20 + (length * 20)));
		FeedItem current = first;
		for (int i = 0; current != null; i++) {
			switch (current.team) {
			case Player.RED:
				g.setColor(Color.red);
				break;
			case Player.BLUE:
				g.setColor(Color.blue);
				break;
			default: 
				g.setColor(Color.white);
				break;
			}
			g.drawString(current.text, xOffset + 15, CTF.HEIGHT - 30 - (i * 20));
			current = current.next;
		}
	}

	public void add(String text, int team) {
		first = new FeedItem(text, first, team);
		length++;
		if (length == MAX) {
			removeLast();
		}
	}
	
	public void removeLast() {
		if (length == 0) return;
		FeedItem current = first;
		while (current.next.next != null) {
			current = current.next;
		}
		current.next = null;
		length--;
	}
	
	
}