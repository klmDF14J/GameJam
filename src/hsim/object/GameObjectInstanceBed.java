package hsim.object;

import hsim.patient.Patient;
import hsim.state.PlayState;

public class GameObjectInstanceBed extends GameObjectInstance {
	
	public Patient patientUsingBed;
	
	public GameObjectInstanceBed(GameObject storedGameObject) {
		super(storedGameObject);
	}
	
	public GameObjectInstanceBed(String string) {
		super(string);
	}

	public void render(org.newdawn.slick.GameContainer gc, org.newdawn.slick.state.StateBasedGame sbg, org.newdawn.slick.Graphics g, int i, int j) {
		if(isOccupied()) {
			System.out.println(patientUsingBed.name);
			g.drawString(patientUsingBed.name, i * PlayState.tileWidth, j * PlayState.tileHeight + PlayState.tileHeight + 20);
		}
		else {
			g.drawString("Unoccupied", i * PlayState.tileWidth, j * PlayState.tileHeight + PlayState.tileHeight + 20);
		}
	};
	
	public boolean isOccupied() {
		return patientUsingBed != null;
	}
}
