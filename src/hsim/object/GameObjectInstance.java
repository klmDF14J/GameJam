package hsim.object;

import hsim.resource.Images;
import hsim.state.PlayState;
import hsim.task.Task;
import hsim.util.GameInfo;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
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
		if(currentTask != null) {
			int renderOffsetX = (GameInfo.resolution.getWidth() / 2) - ((PlayState.mapSizeX - PlayState.offsetX) * PlayState.tileWidth) / 2;
			int renderOffsetY = (GameInfo.resolution.getHeight() / 2) - ((PlayState.mapSizeY - PlayState.offsetY) * PlayState.tileHeight) / 2;
			int x = i * PlayState.tileWidth + renderOffsetX;
			int y = j * PlayState.tileHeight + renderOffsetY + (PlayState.tileHeight / 2) - (Images.progress_bar.getHeight() / 2);
			Images.progress_bar.draw(x, y);
			
			float timeInSeconds = currentTask.time / GameInfo.fps;
			float timeTakenInSeconds = currentTask.timeTakenSoFar / GameInfo.fps;
			float fraction = timeTakenInSeconds > 0 ? timeInSeconds / timeTakenInSeconds : 300;
			float percentage = fraction > 0 ? 100 / fraction : 0;
			
			/*System.out.println("Total Time: " + timeInSeconds);
			System.out.println("Time Taken: " + timeTakenInSeconds);
			System.out.println("Fraction: 1/" + fraction);
			System.out.println("Percentage: " + percentage);*/

			Rectangle progress = new Rectangle(x + 1, y + 1, (float) percentage * 0.62F, 14);
			g.setColor(Color.blue);
			g.fill(progress);
			g.setColor(Color.white);
		}
	}
}
