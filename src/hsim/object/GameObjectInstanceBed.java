package hsim.object;

import hsim.patient.Patient;
import hsim.resource.Images;
import hsim.state.PlayState;
import hsim.util.GameInfo;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Rectangle;

public class GameObjectInstanceBed extends GameObjectInstance {
	
	public Patient patientUsingBed;
	
	public GameObjectInstanceBed(GameObject storedGameObject) {
		super(storedGameObject);
	}
	
	public GameObjectInstanceBed(String string) {
		super(string);
	}

	public void render(org.newdawn.slick.GameContainer gc, org.newdawn.slick.state.StateBasedGame sbg, org.newdawn.slick.Graphics g, int i, int j) {
		super.render(gc, sbg, g, i, j);
		
		int renderOffsetX = (GameInfo.resolution.getWidth() / 2) - ((PlayState.mapSizeX - PlayState.offsetX) * PlayState.tileWidth) / 2;
		int renderOffsetY = (GameInfo.resolution.getHeight() / 2) - ((PlayState.mapSizeY - PlayState.offsetY) * PlayState.tileHeight) / 2;
		if(isOccupied()) {
			Images.progress_bar.draw(i * PlayState.tileWidth + renderOffsetX, j * PlayState.tileHeight + renderOffsetY - 24);
			
			Rectangle progress = new Rectangle(i * PlayState.tileWidth + renderOffsetX + 1, j * PlayState.tileHeight + renderOffsetY - 23, patientUsingBed.health * 0.62F, 14);
			g.setColor(patientUsingBed.health >= 50 ? Color.green : Color.red);
			g.fill(progress);
			g.setColor(Color.white);
		}
	};
	
	public boolean isOccupied() {
		return patientUsingBed != null;
	}
}
