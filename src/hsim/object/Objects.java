package hsim.object;

import java.util.ArrayList;

public class Objects {
	
	public static ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	
	public static void init() {
		addObject(0, "Reception Desk", "object_desk", false);
		addObject(1, "Hospital Bed", "object_bed", true);
		addObject(2, "Doctor", "object_doctor", true);
	}
	
	public static void addObject(int id, String name, String texture_name, boolean canHaveMultiple) {
		gameObjects.add(new GameObject(id, name, texture_name, canHaveMultiple));
	}
}
