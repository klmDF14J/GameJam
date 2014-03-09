package hsim.gui;

import hsim.resource.Images;
import hsim.util.GameInfo;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class GuiPatient extends Gui {

	public Image gui_texture;
	
	public GuiPatient(String texture_name) {
		super(texture_name);
		
		gui_texture = Images.getImage("gui/" + texture_name);
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(gui_texture, GameInfo.resolution.getWidth() / 2, GameInfo.resolution.getHeight() / 2);
		System.out.println("Rendering");
	}
}
