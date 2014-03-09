package hsim.gui;

import hsim.resource.Images;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class GuiPatientPopup extends Gui {

	public Image gui_texture;
	
	private int x;
	private int y;
	
	public GuiPatientPopup(String texture_name, int x, int y) {
		super(texture_name);
		
		this.gui_texture = Images.getImage("gui/" + texture_name);
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void render(Graphics g) {
		float scale = 1F;
		gui_texture.draw(x, y, scale);
		String info = "Patient Information";
		g.drawString(info, x + (gui_texture.getWidth() / 2) - (g.getFont().getWidth(info) / 2), y + 25);
		
		String diagnose = "Diagnose Patient";
		g.drawString(diagnose, x + (gui_texture.getWidth() / 2) - (g.getFont().getWidth(diagnose) / 2), y + 90);
		
		String treat = "Treat Patient";
		g.drawString(treat, x + (gui_texture.getWidth() / 2) - (g.getFont().getWidth(treat) / 2), y + 155);
		
		String discharge = "Discharge Patient";
		g.drawString(discharge, x + (gui_texture.getWidth() / 2) - (g.getFont().getWidth(discharge) / 2), y + 220);
	}
	
	@Override
	public void onMousePressed(int button, int x, int y) {
		
	}
}
