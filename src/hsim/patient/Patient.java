package hsim.patient;

public class Patient {
	
	public static final boolean MALE = false;
	public static final boolean FEMALE = true;
	
	public String name;
	public boolean sex;
	public int age;
	public int health;
	
	public Patient(String name, boolean sex, int age, int health) {
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.health = health;
	}
	
	public Patient(boolean sex, int age, int health) {
		this.name = PatientNames.generateName(sex);
		this.sex = sex;
		this.age = age;
		this.health = health;
	}
}
