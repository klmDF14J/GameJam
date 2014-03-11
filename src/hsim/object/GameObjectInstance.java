package hsim.object;

import hsim.task.Task;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class GameObjectInstance {
	
	public GameObject storedGameObject;
	public Task currentTask;
	
	public GameObjectInstance(GameObject storedGameObject) {
		this.storedGameObject = storedGameObject;
	}
	
	public GameObjectInstance(String object_name) {
		for(GameObject object : Objects.gameObjects) {
			if(object.name == object_name) {
				this.storedGameObject = object;
			}
		}
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g, int i, int j) {
		
	}
}
