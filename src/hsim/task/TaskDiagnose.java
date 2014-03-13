package hsim.task;

import hsim.object.GameObjectInstanceBed;
import hsim.object.GameObjectInstanceDoctor;
import hsim.state.PlayState;

public class TaskDiagnose extends Task {

	public TaskDiagnose() {
		super("diagnose", 10, Task.DOCTOR);
	}
	
	@Override
	public void onTaskComplete() {
		System.out.println("Yay finished diagnosing");
		for(int i = 0; i < PlayState.mapSizeX; i++) {
			for(int j = 0; j < PlayState.mapSizeY; j++) {
				if(PlayState.objectTiles[i][j] != null && PlayState.objectTiles[i][j].currentTask != null) {
					if(PlayState.objectTiles[i][j] instanceof GameObjectInstanceDoctor) {
						if(PlayState.objectTiles[i][j].currentTask.name == "diagnose") {
							if(PlayState.objectTiles[i][j - 1] != null && PlayState.objectTiles[i][j - 1] instanceof GameObjectInstanceBed) {
								((GameObjectInstanceBed) PlayState.objectTiles[i][j - 1]).patientUsingBed.hasBeenDiagnosed = true;
							}
						}
					}
				}
			}
		}
	}
}
