package hsim.resource;

import java.util.ArrayList;

import hsim.object.GameObject;
import hsim.object.Objects;
import hsim.util.SysInfo;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Images {

	public static Image splash;
	public static Image background_menu;
	public static Image logo;
	public static Image press_space;
	public static Image tile_floor;
	public static Image tile_wall;
	public static Image tile_wall_2;
	public static Image tile_floor_highlighted;
	public static ArrayList<Image> gameObjectImages = new ArrayList<Image>();
	
	public static void init() {
		splash = getImage("splash");
		background_menu = getImage("background_menu");
		logo = getImage("logo");
		press_space = getImage("press_space");
		tile_floor = getImage("tile_floor");
		tile_floor_highlighted = getImage("tile_floor_highlighted");
		
		for(GameObject object : Objects.gameObjects) {
			gameObjectImages.add(getImage("objects/" + object.texture_name));
		}
	}

	private static Image getImage(String imageName) {
		try {
			return new Image(SysInfo.userDir + "/res/images/" + imageName + ".png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		return null;
	}
}
