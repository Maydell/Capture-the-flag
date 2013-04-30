package graphics;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
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
	private Shape area;

	public Button(GUIContext container, Image image, Image hover, int x, int y) {
		super(container, image, x, y);
		setMouseOverImage(hover);
		area = new Rectangle(getX(), getY(), getWidth(), getHeight());
	}
	
	public Button(GUIContext container, Image image, int x,int y) {
		super(container, image, x, y);
		area = new Rectangle(getX(), getY(), getWidth(), getHeight());
	}

	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
		if (button == 0 && this.isMouseOver()) {
			clicked = true;
			System.out.println("Button clicked");
		}
	}
	
	@Override
	public void setInput(Input input) {
		this.input = input;
	}
	
	@Override
	public boolean isMouseOver() {
		System.out.println("asd");
		return area.contains(input.getAbsoluteMouseX(), input.getAbsoluteMouseY());
	}

	public boolean isClicked() {
		return clicked;
	}
}
