package hsim.task;

import java.util.Random;

import hsim.object.GameObjectInstanceBed;
import hsim.object.GameObjectInstanceDoctor;
import hsim.state.PlayState;

public class TaskTreat extends Task {

	public TaskTreat() {
		super("treat", 3, Task.DOCTOR);
	}
	
	@Override
	public void onTaskComplete() {
		for(int i = 0; i < PlayState.mapSizeX; i++) {
			for(int j = 0; j < PlayState.mapSizeY; j++) {
				if(PlayState.objectTiles[i][j] != null && PlayState.objectTiles[i][j].currentTask != null) {
					if(PlayState.objectTiles[i][j] instanceof GameObjectInstanceDoctor) {
						if(PlayState.objectTiles[i][j - 1] != null && PlayState.objectTiles[i][j - 1] instanceof GameObjectInstanceBed) {
							Random rand = new Random();
							int num = rand.nextInt(3);
							if(((GameObjectInstanceBed) PlayState.objectTiles[i][j - 1]).patientUsingBed.deteriorationRate - num > -5) {
								((GameObjectInstanceBed) PlayState.objectTiles[i][j - 1]).patientUsingBed.deteriorationRate -= num;
							}
						}
					}
				}
			}
		}
	}
}
