package hsim.handler;

import hsim.object.GameObjectInstance;
import hsim.object.GameObjectInstanceBed;
import hsim.object.GameObjectInstanceDoctor;
import hsim.patient.Patient;

public class GameHandler {

	public static int money;
	
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
	

	public static void setupWard(GameObjectInstance[][] objectTiles) {
		setupBeds(objectTiles);
		setupDoctors(objectTiles);
	}
	
	private static void setupBeds(GameObjectInstance[][] objectTiles) {
		objectTiles[0][0] = new GameObjectInstanceBed("Hospital Bed");
		((GameObjectInstanceBed) objectTiles[0][0]).patientUsingBed = new Patient(Patient.MALE, 20, 60);
		
		objectTiles[2][0] = new GameObjectInstanceBed("Hospital Bed");
		((GameObjectInstanceBed) objectTiles[2][0]).patientUsingBed = new Patient(Patient.MALE, 20, 83);
		
		objectTiles[4][0] = new GameObjectInstanceBed("Hospital Bed");
		((GameObjectInstanceBed) objectTiles[4][0]).patientUsingBed = new Patient(Patient.FEMALE, 20, 71);
		
		objectTiles[6][0] = new GameObjectInstanceBed("Hospital Bed");
		((GameObjectInstanceBed) objectTiles[6][0]).patientUsingBed = new Patient(Patient.MALE, 20, 94);
		
		objectTiles[8][0] = new GameObjectInstanceBed("Hospital Bed");
		((GameObjectInstanceBed) objectTiles[8][0]).patientUsingBed = new Patient(Patient.FEMALE, 20, 76);
		
		objectTiles[10][0] = new GameObjectInstanceBed("Hospital Bed");
		((GameObjectInstanceBed) objectTiles[10][0]).patientUsingBed = new Patient(Patient.FEMALE, 20, 65);
		
		objectTiles[12][0] = new GameObjectInstanceBed("Hospital Bed");
		((GameObjectInstanceBed) objectTiles[12][0]).patientUsingBed = new Patient(Patient.MALE, 20, 68);
		
		objectTiles[14][0] = new GameObjectInstanceBed("Hospital Bed");
		((GameObjectInstanceBed) objectTiles[14][0]).patientUsingBed = new Patient(Patient.MALE, 20, 92);
		
		objectTiles[16][0] = new GameObjectInstanceBed("Hospital Bed");
		((GameObjectInstanceBed) objectTiles[16][0]).patientUsingBed = new Patient(Patient.FEMALE, 20, 87);
	}
	
	private static void setupDoctors(GameObjectInstance[][] objectTiles) {
		objectTiles[0][3] = new GameObjectInstanceDoctor("Doctor", 0, 3);
		((GameObjectInstanceDoctor) objectTiles[0][3]).name = "Doctor Delicious";
		
		objectTiles[2][3] = new GameObjectInstanceDoctor("Doctor", 2, 3);
		((GameObjectInstanceDoctor) objectTiles[2][3]).name = "Doctor Mini";
		
		objectTiles[4][3] = new GameObjectInstanceDoctor("Doctor", 4, 3);
		((GameObjectInstanceDoctor) objectTiles[4][3]).name = "Doctor Large";
	}


	public static void deterioratePatients(int mapSizeX, int mapSizeY, GameObjectInstance[][] objectTiles) {
		for(int i = 0; i < mapSizeX; i++) {
			for(int j = 0; j < mapSizeY; j++) {
				if(objectTiles[i][j] != null && objectTiles[i][j] instanceof GameObjectInstanceBed) {
					GameObjectInstanceBed goib = (GameObjectInstanceBed) objectTiles[i][j];
					if(goib.isOccupied()) {
						if(goib.patientUsingBed.health - goib.patientUsingBed.deteriorationRate > 0) {
							goib.patientUsingBed.health -= goib.patientUsingBed.deteriorationRate;
						}
						else {
							goib.patientUsingBed.health = 0;
							goib.patientUsingBed = null;
						}
					}
				}
			}
		}
	}

}
