package hsim.state;

import hsim.handler.GameHandler;
import hsim.handler.InputHandler;
import hsim.handler.RenderHandler;
import hsim.object.GameObjectInstance;
import hsim.util.GameInfo;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class PlayState extends HSimGameState {

	public static int mapSizeX = 18;
	public static int mapSizeY = 10;
	
	public static int tileWidth = 64;
	public static int tileHeight = 64;
	
	public static int offsetX = 0;
	public static int offsetY = 0;

	public static GameObjectInstance[][] objectTiles = new GameObjectInstance[mapSizeX - offsetX][mapSizeY - offsetY];
	
	private static boolean showWalls = true;
	
	private static int currentObject;

	public PlayState(int id) {
		super(id);
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		GameHandler.setupWard(objectTiles);
	}

	int highlightedI;
	int highlightedJ;
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
	    RenderHandler.renderMap(mapSizeX, mapSizeY, offsetX, offsetY, tileWidth, tileHeight, highlightedI, highlightedJ, objectTiles, gc, sbg, g);
	    RenderHandler.renderGui(g);
	    RenderHandler.renderOverlay(g);
	}
	
	int timer;
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if(timer >= 10 * GameInfo.fps) {
			GameHandler.deterioratePatients(mapSizeX, mapSizeY, objectTiles);
			timer = 0;
		}
		timer++;
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
