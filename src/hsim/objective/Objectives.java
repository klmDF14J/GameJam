package hsim.objective;

import hsim.state.PlayState;

import java.util.ArrayList;

public class Objectives {
	
	public static ArrayList<Objective> objectives = new ArrayList<Objective>();
	public static int current_objective = 1;
	
	public static void init() {
		objectives.add(new Objective("", "Completed All Objectives", 0));
		objectives.add(new Objective("Paperwork...", "Place One Reception Desk", 50) {
			@Override
			public boolean isComplete() {
				for(int i = 0; i < PlayState.mapSizeX; i++) {
					for(int j = 0; j < PlayState.mapSizeY; j++) {
						if(PlayState.objectTiles[i][j] != null && PlayState.objectTiles[i][j].storedGameObject.name == "Reception Desk") {
							return true;
						}
					}
				}
				return false;
			}
		});
	}
}
