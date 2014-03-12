package hsim.state;

import hsim.gui.GuiPatientPopup;
import hsim.handler.GameHandler;
import hsim.handler.GuiHandler;
import hsim.handler.InputHandler;
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
		InputHandler.keyPressed(key, c, showWalls, highlightedI, highlightedJ, mapSizeX, mapSizeY, offsetX, offsetY, currentObject, objectTiles);
	}
	
	@Override
	public void mousePressed(int button, int x, int y) {
		InputHandler.mousePressed(button, x, y, mapSizeX, mapSizeY, tileWidth, tileHeight, offsetX, offsetY, objectTiles);
	}
}
