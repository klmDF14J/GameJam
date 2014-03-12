package hsim.handler;

import hsim.object.GameObjectInstance;

public class GameHandler {

	public static void updateTasks(int mapSizeX, int mapSizeY, GameObjectInstance[][] objectTiles) {
		for(int i = 0; i < mapSizeX; i++) {
			for(int j = 0; j < mapSizeY; j++) {
				if(objectTiles[i][j] != null && objectTiles[i][j].currentTask != null) {
					if(!objectTiles[i][j].currentTask.isFinished()) {
						objectTiles[i][j].currentTask.timeTakenSoFar++;
					}
					else {
						objectTiles[i][j].currentTask = null;
					}
				}
			}
		}
	}

}
