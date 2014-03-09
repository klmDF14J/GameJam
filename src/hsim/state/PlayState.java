package hsim.state;

import hsim.handler.GuiHandler;
import hsim.object.GameObjectInstance;
import hsim.object.GameObjectInstanceBed;
import hsim.object.Objects;
import hsim.patient.Patient;
import hsim.resource.Images;
import hsim.util.GameInfo;
import hsim.util.KeyInfo;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

public class PlayState extends HSimGameState {

	public static int mapSizeX = 18;
	public static int mapSizeY = 10;
	
	public static int tileWidth = 64;
	public static int tileHeight = 64;
	
	public static int offsetX = 0;
	public static int offsetY = 0;
	
	private static int[][] floorTiles = new int[mapSizeX - offsetX][mapSizeY - offsetY];
	private static int[][] wallTiles = new int[mapSizeX][mapSizeY];
	public static GameObjectInstance[][] objectTiles = new GameObjectInstance[mapSizeX - offsetX][mapSizeY - offsetY];
	
	private static boolean showWalls = true;
	
	private static int currentObject;

	public PlayState(int id) {
		super(id);
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		for(int i = 0; i < mapSizeY; i++) {
			wallTiles[0][i] = 1;
		}
		
		for(int i = 0; i < mapSizeX; i++) {
			wallTiles[i][0] = 1;
		}
		
		for(int i = 0; i < mapSizeY; i++) {
			wallTiles[mapSizeX - 1][i] = 1;
		}
		
		objectTiles[0][0] = new GameObjectInstanceBed("Hospital Bed");
		((GameObjectInstanceBed) objectTiles[0][0]).patientUsingBed = new Patient(Patient.MALE, 20, 100);
		
		objectTiles[2][0] = new GameObjectInstanceBed("Hospital Bed");
		((GameObjectInstanceBed) objectTiles[2][0]).patientUsingBed = new Patient(Patient.MALE, 20, 100);
		
		objectTiles[4][0] = new GameObjectInstanceBed("Hospital Bed");
		((GameObjectInstanceBed) objectTiles[4][0]).patientUsingBed = new Patient(Patient.MALE, 20, 100);
		
		objectTiles[6][0] = new GameObjectInstanceBed("Hospital Bed");
		((GameObjectInstanceBed) objectTiles[6][0]).patientUsingBed = new Patient(Patient.MALE, 20, 100);
		
		objectTiles[8][0] = new GameObjectInstanceBed("Hospital Bed");
		((GameObjectInstanceBed) objectTiles[8][0]).patientUsingBed = new Patient(Patient.MALE, 20, 100);
		
		objectTiles[10][0] = new GameObjectInstanceBed("Hospital Bed");
		((GameObjectInstanceBed) objectTiles[10][0]).patientUsingBed = new Patient(Patient.MALE, 20, 100);
		
		objectTiles[12][0] = new GameObjectInstanceBed("Hospital Bed");
		((GameObjectInstanceBed) objectTiles[12][0]).patientUsingBed = new Patient(Patient.MALE, 20, 100);
		
		objectTiles[14][0] = new GameObjectInstanceBed("Hospital Bed");
		((GameObjectInstanceBed) objectTiles[14][0]).patientUsingBed = new Patient(Patient.MALE, 20, 100);
		
		objectTiles[16][0] = new GameObjectInstanceBed("Hospital Bed");
		((GameObjectInstanceBed) objectTiles[16][0]).patientUsingBed = new Patient(Patient.MALE, 20, 100);
	}

	int hightlightedI;
	int hightlightedJ;
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		for(int i = 0; i < mapSizeX - offsetX; i++) {
			  for(int j = 0; j < mapSizeY - offsetY; j++) {
			    int x = i * tileWidth;
			    int y = j * tileHeight;
			    //int isoX = x - y;
			    //int isoY = (x + y) / 2;
			    int renderOffsetX = (GameInfo.resolution.getWidth() / 2) - ((mapSizeX - offsetX) * tileWidth) / 2;
			    int renderOffsetY = (GameInfo.resolution.getHeight() / 2) - ((mapSizeY - offsetY) * tileHeight) / 2;
			    if(i == hightlightedI && j == hightlightedJ) {
			    	Images.tile_floor_highlighted.draw(x + renderOffsetX, y + renderOffsetY);
			    }
			    else {
			    	Images.tile_floor.draw(x + renderOffsetX, y + renderOffsetY);
			    }
			  }
		}
		
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
			    	//img = img.getFlippedCopy(true, false);
			    	img.draw(x + renderOffsetX, y + renderOffsetY);
			    }
			  }
		}
		
		for(int i = 0; i < mapSizeX - offsetX; i++) {
			  for(int j = 0; j < mapSizeY - offsetY; j++) {
				  if(objectTiles[i][j] != null) {
					  objectTiles[i][j].render(gc, sbg, g, i, j);
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
			if(hightlightedI + 1 < mapSizeX - offsetX) {
				hightlightedI++;
			}
		}
		if(key == KeyInfo.up) {
			if(hightlightedJ > 0) {
				hightlightedJ--;
			}
		}
		if(key == KeyInfo.down) {
			if(hightlightedJ + 1 < mapSizeY - offsetY) {
				hightlightedJ++;
			}
		}
		if(key == KeyInfo.advance) {
			Objects.gameObjects.get(currentObject).onPlaced(hightlightedI, hightlightedJ);
			objectTiles[hightlightedI][hightlightedJ] = new GameObjectInstance(Objects.gameObjects.get(currentObject));
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
	
	@Override
	public void mousePressed(int button, int x, int y) {
		Rectangle bounds_mouse = new Rectangle(x, y, 1, 1);
	    int renderOffsetX = (GameInfo.resolution.getWidth() / 2) - ((mapSizeX - offsetX) * tileWidth) / 2;
	    int renderOffsetY = (GameInfo.resolution.getHeight() / 2) - ((mapSizeY - offsetY) * tileHeight) / 2;
		for(int i = 0; i < mapSizeX - offsetX; i++) {
			  for(int j = 0; j < mapSizeY - offsetY; j++) {
				  Rectangle bounds_tile = new Rectangle(i * tileWidth + renderOffsetX, j * tileWidth + renderOffsetY, 1, 1); 
				  if(bounds_mouse.intersects(bounds_tile)) {
					  GuiHandler.showGui();
				  }
			  }
		}
	}
}
