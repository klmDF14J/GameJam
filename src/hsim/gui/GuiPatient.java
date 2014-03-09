package hsim.gui;

import hsim.patient.Patient;
import hsim.resource.Images;
import hsim.util.GameInfo;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class GuiPatient extends Gui {

	public Image gui_texture;
	public Patient patientUsingBed;
	
	public GuiPatient(String texture_name, Patient patientUsingBed) {
		super(texture_name);
		
		this.gui_texture = Images.getImage("gui/" + texture_name);
		this.patientUsingBed = patientUsingBed;
	}
	
	@Override
	public void render(Graphics g) {
		float scale = 1F;
		gui_texture.draw(GameInfo.resolution.getWidth() / 2 - (gui_texture.getWidth() * scale / 2), GameInfo.resolution.getHeight() / 2 - (gui_texture.getHeight() * scale / 2), scale);
		g.drawString(patientUsingBed.name, GameInfo.resolution.getWidth() / 2 - (g.getFont().getWidth(patientUsingBed.name) / 2), 200);
	}
}
