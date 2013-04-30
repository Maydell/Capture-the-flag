package graphics;

import org.newdawn.slick.Image;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;

/**
 * Buttons are objects that can be clicked and interacted with to make something
 * happen.
 * 
 * @author Mats Stichel, Isak Jagberg
 * 
 */
public class Button extends MouseOverArea {

	private boolean clicked;

	public Button(GUIContext container, Image image, Image hover, int x, int y) {
		super(container, image, x, y);
		setMouseOverImage(hover);
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
