package hsim.state;

import hsim.gui.GuiPatientPopup;
import hsim.handler.GameHandler;
import hsim.handler.GuiHandler;
import hsim.handler.RenderHandler;
import hsim.object.GameObject;
import hsim.object.GameObjectInstance;
import hsim.object.GameObjectInstanceBed;
import hsim.object.GameObjectInstanceDoctor;
import hsim.object.Objects;
import hsim.patient.Patient;
import hsim.util.GameInfo;
import hsim.util.KeyInfo;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
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
		((GameObjectInstanceBed) objectTiles[4][0]).patientUsingBed = new Patient(Patient.FEMALE, 20, 100);
		
		objectTiles[6][0] = new GameObjectInstanceBed("Hospital Bed");
		((GameObjectInstanceBed) objectTiles[6][0]).patientUsingBed = new Patient(Patient.MALE, 20, 100);
		
		objectTiles[8][0] = new GameObjectInstanceBed("Hospital Bed");
		((GameObjectInstanceBed) objectTiles[8][0]).patientUsingBed = new Patient(Patient.FEMALE, 20, 100);
		
		objectTiles[10][0] = new GameObjectInstanceBed("Hospital Bed");
		((GameObjectInstanceBed) objectTiles[10][0]).patientUsingBed = new Patient(Patient.FEMALE, 20, 100);
		
		objectTiles[12][0] = new GameObjectInstanceBed("Hospital Bed");
		((GameObjectInstanceBed) objectTiles[12][0]).patientUsingBed = new Patient(Patient.MALE, 20, 100);
		
		objectTiles[14][0] = new GameObjectInstanceBed("Hospital Bed");
		((GameObjectInstanceBed) objectTiles[14][0]).patientUsingBed = new Patient(Patient.MALE, 20, 100);
		
		objectTiles[16][0] = new GameObjectInstanceBed("Hospital Bed");
		((GameObjectInstanceBed) objectTiles[16][0]).patientUsingBed = new Patient(Patient.FEMALE, 20, 100);
		
		objectTiles[0][3] = new GameObjectInstanceDoctor("Doctor", 0, 3);
		((GameObjectInstanceDoctor) objectTiles[0][3]).name = "Doctor Delicious";
		
		objectTiles[2][3] = new GameObjectInstanceDoctor("Doctor", 2, 3);
		((GameObjectInstanceDoctor) objectTiles[2][3]).name = "Doctor Mini";
		
		objectTiles[4][3] = new GameObjectInstanceDoctor("Doctor", 4, 3);
		((GameObjectInstanceDoctor) objectTiles[4][3]).name = "Doctor Large";
	}

	int highlightedI;
	int highlightedJ;
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
	    RenderHandler.renderMap(mapSizeX, mapSizeY, offsetX, offsetY, tileWidth, tileHeight, highlightedI, highlightedJ, objectTiles, gc, sbg, g);
	    RenderHandler.renderGui(g);
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		GameHandler.updateTasks(mapSizeX, mapSizeY, objectTiles);
	}
	
	@Override
	public void keyPressed(int key, char c) {
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
	
	@Override
	public void mousePressed(int button, int x, int y) {
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
