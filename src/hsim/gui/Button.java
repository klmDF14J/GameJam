package hsim.gui;

import org.newdawn.slick.geom.Rectangle;

public class Button {
	
	private int x;
	private int y;
	private int width;
	private int height;
	
	public Button(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public boolean onClicked(Rectangle bounds_mouse) {
		if(isClicked(bounds_mouse, new Rectangle(x, y, width, height))) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean isClicked(Rectangle bounds_mouse, Rectangle bounds_button) {
		if(bounds_mouse.intersects(bounds_button)) {
			return true;
		}
		else {
			return false;
		}
	}
}
