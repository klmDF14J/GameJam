package hsim.object;

import hsim.patient.Patient;


public class GameObjectBed extends GameObject {

	public Patient patientUsingBed;
	
	public GameObjectBed(int id, String name, String texture_name, boolean canHaveMultiple) {
		super(id, name, texture_name, canHaveMultiple);
	}
	
	public boolean isOccupied() {
		return patientUsingBed != null;
	}
}
