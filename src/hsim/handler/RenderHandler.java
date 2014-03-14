package hsim.handler;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import hsim.object.GameObjectInstance;
import hsim.resource.Images;
import hsim.util.GameInfo;

public class RenderHandler {

	public static void renderMap(int mapSizeX, int mapSizeY, int offsetX, int offsetY, int tileWidth, int tileHeight, int highlightedI, int highlightedJ, GameObjectInstance[][] objectTiles, GameContainer gc, StateBasedGame sbg, Graphics g) {
		renderMapFloor(mapSizeX, mapSizeY, offsetX, offsetY, tileWidth, tileHeight, highlightedI, highlightedJ);
		renderMapObjects(mapSizeX, mapSizeY, offsetX, offsetY, tileWidth, tileHeight, highlightedI, highlightedJ, objectTiles);
		renderMapObjectsSpecialRender(mapSizeX, mapSizeY, offsetX, offsetY, objectTiles, gc, sbg, g);
	}
	
	private static void renderMapFloor(int mapSizeX, int mapSizeY, int offsetX, int offsetY, int tileWidth, int tileHeight, int highlightedI, int highlightedJ) {
		for(int i = 0; i < mapSizeX - offsetX; i++) {
			  for(int j = 0; j < mapSizeY - offsetY; j++) {
			    int x = i * tileWidth;
			    int y = j * tileHeight;
			    //int isoX = x - y;
			    //int isoY = (x + y) / 2;
			    int renderOffsetX = (GameInfo.resolution.getWidth() / 2) - ((mapSizeX - offsetX) * tileWidth) / 2;
			    int renderOffsetY = (GameInfo.resolution.getHeight() / 2) - ((mapSizeY - offsetY) * tileHeight) / 2;
			    if(i == highlightedI && j == highlightedJ) {
			    	Images.tile_floor_highlighted.draw(x + renderOffsetX, y + renderOffsetY);
			    }
			    else {
			    	Images.tile_floor.draw(x + renderOffsetX, y + renderOffsetY);
			    }
			  }
		}
	}
	
	private static void renderMapObjects(int mapSizeX, int mapSizeY, int offsetX, int offsetY, int tileWidth, int tileHeight, int highlightedI, int highlightedJ, GameObjectInstance[][] objectTiles) {
		for(int i = 0; i < mapSizeX - offsetX; i++) {
			  for(int j = 0; j < mapSizeY - offsetY; j++) {
			    int x = i * tileWidth;
			    int y = j * tileHeight;
			    //int isoX = x - y;
			    //int isoY = (x + y) / 2;
			    int renderOffsetX = (GameInfo.resolution.getWidth() / 2) - ((mapSizeX - offsetX) * tileWidth) / 2;
			    int renderOffsetY = (GameInfo.resolution.getHeight() / 2) - ((mapSizeY - offsetY) * tileHeight) / 2;
			    if(objectTiles[i][j] != null) {
			    	Image img = Images.gameObjectImages.get(objectTiles[i][j].storedGameObject.id);
			    	img.draw(x + renderOffsetX, y + renderOffsetY);
			    }
			  }
		}
	}
	
	private static void renderMapObjectsSpecialRender(int mapSizeX, int mapSizeY, int offsetX, int offsetY, GameObjectInstance[][] objectTiles, GameContainer gc, StateBasedGame sbg, Graphics g) {
		for(int i = 0; i < mapSizeX - offsetX; i++) {
			  for(int j = 0; j < mapSizeY - offsetY; j++) {
				  if(objectTiles[i][j] != null) {
					  objectTiles[i][j].render(gc, sbg, g, i, j);
				  }
			  }
		}
	}
	
	public static void renderGui(Graphics g) {
		GuiHandler.g = g;
		if(GuiHandler.currentGui != null) {
			GuiHandler.currentGui.render(g);
		}
	}

	public static void renderOverlay(Graphics g) {
		String money = "Total Money: ";
		g.drawString(money + GameHandler.money, GameInfo.resolution.getWidth() - 200, GameInfo.resolution.getHeight() - 40);
	}
}