package hsim.handler;

import org.newdawn.slick.geom.Rectangle;

import hsim.gui.GuiPatientPopup;
import hsim.object.GameObject;
import hsim.object.GameObjectInstance;
import hsim.object.Objects;
import hsim.util.GameInfo;
import hsim.util.KeyInfo;

public class InputHandler {

	public static void keyPressed(int key, char c, boolean showWalls, int highlightedI, int highlightedJ, int mapSizeX, int mapSizeY, int offsetX, int offsetY, int currentObject, GameObjectInstance[][] objectTiles) {
		if(GuiHandler.currentGui == null) {
			if(key == KeyInfo.ctrl) {
				showWalls = showWalls ? false : true;
			}
			if(key == KeyInfo.left) {
				if(highlightedI > 0) {
					highlightedI--;
				}
			}
			if(key == KeyInfo.right) {
				if(highlightedI + 1 < mapSizeX - offsetX) {
					highlightedI++;
				}
			}
			if(key == KeyInfo.up) {
				if(highlightedJ > 0) {
					highlightedJ--;
				}
			}
			if(key == KeyInfo.down) {
				if(highlightedJ + 1 < mapSizeY - offsetY) {
					highlightedJ++;
				}
			}
			if(key == KeyInfo.advance) {
				GameObject object = Objects.gameObjects.get(currentObject);
				if(object != null) {
					object.onPlaced(highlightedI, highlightedJ);
					objectTiles[highlightedI][highlightedJ] = new GameObjectInstance(object);
				}
			}
			if(key == KeyInfo.select_up) {
				if(currentObject + 1 < Objects.gameObjects.size()) {
					currentObject++;
				}
			}
			if(key == KeyInfo.select_down) {
				if(currentObject > 0) {
					currentObject--;
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
						  GuiHandler.showGui(new GuiPatientPopup("patient_popup", x, y, i, j));
					  }
				  }
			}
		}
		else {
			GuiHandler.currentGui.onMousePressed(button, x, y);
		}
	}
}
