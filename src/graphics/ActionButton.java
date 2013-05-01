package graphics;

import org.newdawn.slick.Image;
import org.newdawn.slick.gui.GUIContext;

public class ActionButton extends Button {

	Image[] images;
	Image[] hovers;

	public ActionButton(GUIContext container, Image[] images, int x, int y,
			String name) {
		super(container, images[0], x, y, name);
		this.images = images;
		loadHovers();
	}
	
	public void loadHovers() {
		hovers = new Image[images.length];
		for(int i = 0; i < images.length; i++) {
			hovers[i] = images[i].copy();
			hovers[i].setImageColor(.8f, .8f, .8f);
		}
	}

	public void setTeam(int team) {
		setNormalImage(images[team]);
		setMouseOverImage(hovers[team]);
	}

}
