package hsim.state;

import hsim.object.Objects;
import hsim.resource.Images;
import hsim.util.KeyInfo;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.state.StateBasedGame;

public class PlayState extends HSimGameState {

	private static int mapSize = 10;
	
	private static int tileWidth = 48;
	private static int tileHeight = 32;
	
	private static int offsetX = 0;
	private static int offsetY = 0;
	
	private static int[][] floorTiles = new int[mapSize - offsetX][mapSize - offsetY];
	private static int[][] wallTiles = new int[mapSize][mapSize];
	private static int[][] objectTiles = new int[mapSize - offsetX][mapSize - offsetY];
	
	private static boolean showWalls = true;
	
	private static int currentObject;

	public PlayState(int id) {
		super(id);
		 
		for(int i = 0; i < wallTiles.length; i++) {
			wallTiles[0][i] = 1;
		}
		
		for(int i = 0; i < wallTiles.length; i++) {
			wallTiles[i][0] = 1;
		}
		
		for(int i = 0; i < wallTiles.length; i++) {
			wallTiles[wallTiles.length - 1][i] = 1;
		}
		
		for(int i = 0; i < objectTiles.length; i++) {
			for(int j = 0; j < objectTiles.length; j++) {
				objectTiles[i][j] = -1;
			}
		}
	}

	int hightlightedI;
	int hightlightedJ;
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		for(int i = 0; i < mapSize - offsetX; i++) {
			  for(int j = 0; j < mapSize - offsetY; j++) {
			    int x = j * tileWidth;
			    int y = i * tileHeight;
			    int isoX = x - y;
			    int isoY = (x + y) / 2;
			    if(i == hightlightedI && j == hightlightedJ) {
			    	Images.tile_floor_highlighted.draw(isoX + 500, isoY + 100);
			    }
			    else {
			    	Images.tile_floor.draw(isoX + 500, isoY + 100);
			    }
			  }
		}
		
		for(int i = 0; i < mapSize - offsetX; i++) {
			  for(int j = 0; j < mapSize - offsetY; j++) {
			    int x = j * tileWidth;
			    int y = i * tileHeight;
			    int isoX = x - y;
			    int isoY = (x + y) / 2;
			    if(objectTiles[i][j] != -1) {
			    	Image img = Images.gameObjectImages.get(objectTiles[i][j]);
			    	//img = img.getFlippedCopy(true, false);
			    	img.draw(isoX + 500, isoY + 100 - (tileHeight / 2) + 1);
			    }
			  }
		}
	}
	
	@Override
	public void keyPressed(int key, char c) {
		if(key == KeyInfo.ctrl) {
			showWalls = showWalls ? false : true;
		}
		if(key == KeyInfo.left) {
			if(hightlightedI > 0) {
				hightlightedI--;
			}
		}
		if(key == KeyInfo.right) {
			if(hightlightedI + 1 < mapSize - offsetX) {
				hightlightedI++;
			}
		}
		if(key == KeyInfo.up) {
			if(hightlightedJ > 0) {
				hightlightedJ--;
			}
		}
		if(key == KeyInfo.down) {
			if(hightlightedJ + 1 < mapSize - offsetY) {
				hightlightedJ++;
			}
		}
		if(key == KeyInfo.advance) {
			objectTiles[hightlightedI][hightlightedJ] = currentObject;
			Objects.gameObjects.get(currentObject).onPlaced(hightlightedI, hightlightedJ);
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
}
