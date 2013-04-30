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
	String name;
	
	public Button(GUIContext container, Image image, Image hover, int x, int y, String name) {
		super(container, image, x, y);
		setMouseOverImage(hover);
		this.name = name;
	}

	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
		if (button == 0 && this.isMouseOver()) {
			System.out.println("Clicked " + name);
			clicked = true;
		}
	}

	public boolean isClicked() {
		return clicked;
	}
}
