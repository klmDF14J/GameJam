package hsim.gui;

import hsim.resource.Images;
import hsim.util.GameInfo;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class GuiShop extends Gui {

	
	private Image gui_texture;

	public GuiShop(String texture_name) {
		super(texture_name);
		
		this.gui_texture = Images.getImage("gui/" + texture_name);
	}
	
	@Override
	public void render(Graphics g) {
		float scale = 1F;
		gui_texture.draw(GameInfo.resolution.getWidth() / 2 - (gui_texture.getWidth() * scale / 2), GameInfo.resolution.getHeight() / 2 - (gui_texture.getHeight() * scale / 2), scale);
	}
}
