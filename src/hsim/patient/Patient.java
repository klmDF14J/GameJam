package hsim.patient;

import java.util.Random;

public class Patient {
	
	public static final boolean MALE = false;
	public static final boolean FEMALE = true;
	
	public String name;
	public boolean sex;
	public int age;
	public int health;
	public int deteriorationRate;
	public boolean isDeteriorating;
	public boolean hasBeenDiagnosed = false;
	
	public Patient(boolean sex, int age, int health) {
		this.name = PatientNames.generateName(sex);
		this.sex = sex;
		this.age = age;
		this.health = health;
		
		Random rand = new Random();
		this.isDeteriorating = rand.nextBoolean();
		if(isDeteriorating) {
			this.deteriorationRate = rand.nextInt(8);
		}
	}
}
