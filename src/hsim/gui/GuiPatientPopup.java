package hsim.gui;

import hsim.handler.GuiHandler;
import hsim.object.GameObjectInstanceBed;
import hsim.object.GameObjectInstanceDoctor;
import hsim.resource.Images;
import hsim.state.PlayState;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

public class GuiPatientPopup extends Gui {

	public Image gui_texture;
	
	private int x;
	private int y;
	private int i;
	private int j;
	
	public GuiPatientPopup(String texture_name, int x, int y, int i, int j) {
		super(texture_name);
		
		this.gui_texture = Images.getImage("gui/" + texture_name);
		this.x = x;
		this.y = y;
		this.i = i;
		this.j = j;
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
		
		Rectangle bounds_info = new Rectangle(x, y, 256, 64);
		Rectangle bounds_diagnose = new Rectangle(x, y + 64, 256, 64);
		if(bounds_mouse.intersects(bounds_info)) {
			GuiHandler.showGui(new GuiPatientInfo("patient_info", ((GameObjectInstanceBed)PlayState.objectTiles[i][j]).patientUsingBed));
		}
		if(bounds_mouse.intersects(bounds_diagnose)) {
			ArrayList<GameObjectInstanceDoctor> availableDoctors = new ArrayList<GameObjectInstanceDoctor>();
			for(int i = 0; i < PlayState.mapSizeX; i++) {
				for(int j = 0; j < PlayState.mapSizeY; j++) {
					if(PlayState.objectTiles[i][j] instanceof GameObjectInstanceDoctor) {
						if(!((GameObjectInstanceDoctor) PlayState.objectTiles[i][j]).busy) {
							availableDoctors.add((GameObjectInstanceDoctor) PlayState.objectTiles[i][j]);
						}
					}
				}
			}
			if(availableDoctors.size() > 0) {
				Random rand = new Random();
				int randomNum = rand.nextInt(availableDoctors.size());
				GameObjectInstanceDoctor doctor = availableDoctors.get(randomNum);
				((GameObjectInstanceDoctor) PlayState.objectTiles[doctor.posX][doctor.posY]).busy = true;
				/*while(doctor.posX < i) {
					if(isClearAt(availableDoctors, doctor.posX + 1, doctor.posY)) {
						PlayState.objectTiles[doctor.posX][doctor.posY] = null;
						doctor.posX++;
						PlayState.objectTiles[doctor.posX][doctor.posY] = doctor;
					}
					else {
						break;
					}
				}
				while(doctor.posX > i) {
					if(isClearAt(availableDoctors, doctor.posX - 1, doctor.posY)) {
						PlayState.objectTiles[doctor.posX][doctor.posY] = null;
						doctor.posX--;
						PlayState.objectTiles[doctor.posX][doctor.posY] = doctor;
					}
					else {
						break;
					}
				}
				while(doctor.posY < j - 1) {
					if(isClearAt(availableDoctors, doctor.posX, doctor.posY + 1)) {
						PlayState.objectTiles[doctor.posX][doctor.posY] = null;
						doctor.posY++;
						PlayState.objectTiles[doctor.posX][doctor.posY] = doctor;
					}
					else {
						break;
					}
				}
				while(doctor.posY > j + 1) {
					if(isClearAt(availableDoctors, doctor.posX, doctor.posY - 1)) {
						PlayState.objectTiles[doctor.posX][doctor.posY] = null;
						doctor.posY--;
						PlayState.objectTiles[doctor.posX][doctor.posY] = doctor;
					}
					else {
						break;
					}
				}*/
				
				PlayState.objectTiles[doctor.posX][doctor.posY] = null;
				doctor.posX = i;
				doctor.posY = j + 1;
				PlayState.objectTiles[doctor.posX][doctor.posY] = doctor;
			}
		}
		
	}
	
	/*private boolean isClearAt(ArrayList<GameObjectInstanceDoctor> doctors, int x, int y) {
		for(GameObjectInstanceDoctor doctor : doctors) {
			if(doctor.posX == x && doctor.posY == y) {
				return false;
			}
		}
		return true;
	}*/
}
