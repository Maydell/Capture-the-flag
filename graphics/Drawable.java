package graphics;

public abstract class Drawable {

	protected int xPos, yPos;
	
	public void moveTo(int x, int y) {
		xPos = x; yPos = y;
	}
	
	public abstract void draw();
	
	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
}
