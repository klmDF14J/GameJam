package hsim.handler;

import hsim.gui.GuiPatientPopup;
import hsim.object.GameObject;
import hsim.object.GameObjectInstance;
import hsim.object.GameObjectInstanceBed;
import hsim.object.Objects;
import hsim.state.PlayState;
import hsim.util.GameInfo;
import hsim.util.KeyInfo;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.geom.Rectangle;

public class InputHandler {

	public static void keyPressed(int key, char c, int mapSizeX, int mapSizeY, int offsetX, int offsetY, GameObjectInstance[][] objectTiles) {
		if(GuiHandler.currentGui == null) {
			System.out.println(Keyboard.getKeyName(key));
			if(key == KeyInfo.ctrl) {
				PlayState.showWalls = PlayState.showWalls ? false : true;
			}
			if(key == KeyInfo.left) {
				if(PlayState.highlightedI > 0) {
					PlayState.highlightedI--;
				}
			}
			if(key == KeyInfo.right) {
				if(PlayState.highlightedI + 1 < mapSizeX - offsetX) {
					PlayState.highlightedI++;
				}
			}
			if(key == KeyInfo.up) {
				if(PlayState.highlightedJ > 0) {
					PlayState.highlightedJ--;
				}
			}
			if(key == KeyInfo.down) {
				if(PlayState.highlightedJ + 1 < mapSizeY - offsetY) {
					PlayState.highlightedJ++;
				}
			}
			if(key == KeyInfo.advance) {
				GameObject object = Objects.gameObjects.get(PlayState.currentObject);
				if(object != null) {
					object.onPlaced(PlayState.highlightedI, PlayState.highlightedJ);
					objectTiles[PlayState.highlightedI][PlayState.highlightedJ] = new GameObjectInstance(object);
				}
			}
			if(key == KeyInfo.select_up) {
				if(PlayState.currentObject + 1 < Objects.gameObjects.size()) {
					PlayState.currentObject++;
				}
			}
			if(key == KeyInfo.select_down) {
				if(PlayState.currentObject > 0) {
					PlayState.currentObject--;
				}
			}
		}
		else {
			GuiHandler.currentGui.onKeyPressed(key, c);
		}
	}

	public static void mousePressed(int button, int x, int y, int mapSizeX, int mapSizeY, int tileWidth, int tileHeight, int offsetX, int offsetY, GameObjectInstance[][] objectTiles) {
		if(GuiHandler.currentGui == null) {
			Rectangle bounds_mouse = new Rectangle(x, y, 1, 1);
		    int renderOffsetX = (GameInfo.resolution.getWidth() / 2) - ((mapSizeX - offsetX) * tileWidth) / 2;
		    int renderOffsetY = (GameInfo.resolution.getHeight() / 2) - ((mapSizeY - offsetY) * tileHeight) / 2;
			for(int i = 0; i < mapSizeX - offsetX; i++) {
				  for(int j = 0; j < mapSizeY - offsetY; j++) {
					  Rectangle bounds_tile = new Rectangle(i * tileWidth + renderOffsetX, j * tileWidth + renderOffsetY, tileWidth, tileHeight); 
					  if(bounds_mouse.intersects(bounds_tile) && objectTiles[i][j] != null && objectTiles[i][j].storedGameObject.id == 1) {
						  if(((GameObjectInstanceBed) objectTiles[i][j]).patientUsingBed != null) {
							  GuiHandler.showGui(new GuiPatientPopup("patient_popup", x, y, i, j));
						  }
					  }
				  }
			}
		}
		else {
			GuiHandler.currentGui.onMousePressed(button, x, y);
		}
	}
}
