package hsim.gui;

import hsim.handler.GuiHandler;
import hsim.object.GameObjectInstanceBed;
import hsim.object.GameObjectInstanceDoctor;
import hsim.resource.Images;
import hsim.state.PlayState;
import hsim.task.TaskDiagnose;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

public class GuiPatientPopup extends Gui {

	public Image gui_texture;
	
	private int x;
	private int y;
	public static int i;
	public static int j;
	
	private Button info;
	private Button diagnose;
	
	public GuiPatientPopup(String texture_name, int x, int y, int i, int j) {
		super(texture_name);
		
		this.gui_texture = Images.getImage("gui/" + texture_name);
		this.x = x;
		this.y = y;
		this.i = i;
		this.j = j;
		
		info = new Button(x, y, 256, 64) {
			@Override
			public boolean onClicked(Rectangle bounds_mouse) {
				if(super.onClicked(bounds_mouse)) {
					GuiHandler.showGui(new GuiPatientInfo("patient_info", ((GameObjectInstanceBed)PlayState.objectTiles[GuiPatientPopup.i][GuiPatientPopup.j]).patientUsingBed));
					return true;
				}
				else {
					return false;
				}
			}
		};
		diagnose = new Button(x, y + 64, 256, 64) {
			@Override
			public boolean onClicked(Rectangle bounds_mouse) {
				if(super.onClicked(bounds_mouse)) {			
					ArrayList<GameObjectInstanceDoctor> availableDoctors = new ArrayList<GameObjectInstanceDoctor>();
					for(int i = 0; i < PlayState.mapSizeX; i++) {
						for(int j = 0; j < PlayState.mapSizeY; j++) {
							if(PlayState.objectTiles[i][j] instanceof GameObjectInstanceDoctor) {
								if(PlayState.objectTiles[i][j].currentTask == null) {
									availableDoctors.add((GameObjectInstanceDoctor) PlayState.objectTiles[i][j]);
								}
							}
						}
					}
					if(availableDoctors.size() > 0) {
						Random rand = new Random();
						int randomNum = rand.nextInt(availableDoctors.size());
						GameObjectInstanceDoctor doctor = availableDoctors.get(randomNum);
						
						PlayState.objectTiles[doctor.posX][doctor.posY] = null;
						doctor.posX = GuiPatientPopup.i;
						doctor.posY = GuiPatientPopup.j + 1;
						PlayState.objectTiles[doctor.posX][doctor.posY] = doctor;
						PlayState.objectTiles[doctor.posX][doctor.posY].currentTask = new TaskDiagnose();
						return true;
					}
					else {
						return false;
					}
				}
				else {
					return false;
				}
			}
		};
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
	public void onMousePressed(int button, int mouseX, int mouseY) {
		Rectangle bounds_mouse = new Rectangle(mouseX, mouseY, 1, 1);

		info.onClicked(bounds_mouse);
		diagnose.onClicked(bounds_mouse);
	}
}
