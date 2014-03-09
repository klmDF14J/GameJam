package hsim.gui;

import hsim.handler.GuiHandler;
import hsim.resource.Images;
import hsim.util.KeyInfo;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Gui {
	
	public Gui(String texture_name) {

	}
	
	public void render(Graphics g) {
		
	}

	public void onKeyPressed(int key, char c) {
		if(key == KeyInfo.escape) {
			GuiHandler.currentGui = null;
		}
	}

	public void onMousePressed(int button, int x, int y) {
		
	}

}
