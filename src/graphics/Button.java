package graphics;

import org.newdawn.slick.Image;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;

public class Button extends MouseOverArea {

	private boolean clicked;
	
	public Button(GUIContext container, Image image, int x, int y, int width, int height) {
		super(container, image, x, y, width, height);
	}

	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
		if (button == 0 && this.isMouseOver()) {
			clicked = true;
		}
	}
	
	public boolean isClicked() {
		return clicked;
	}
}
