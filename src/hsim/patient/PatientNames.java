package hsim.patient;

import java.util.Random;

public class PatientNames {
	
	public static String[] first_names_male = {
		"Andy",
		"Aaron",
		"Ben",
		"Brad",
		"Charlie",
		"Cecil",
		"Daryl",
		"David",
		"Dan",
		"Dominic",
		"Ethan",
		"Fred",
		"Graham",
		"George",
		"Harry",
		"Henry",
		"James",
		"John",
		"Josh",
		"Joeseph",
		"Kyle",
		"Keith",
		"Louis",
		"Lewis",
		"Luke",
		"Matthew",
		"Mark",
		"Nigel",
		"Neil",
		"Oscar",
		"Oliver",
		"Paul",
		"Phillip",
		"Quentin",
		"Ross",
		"Raymond",
		"Steven",
		"Samuel",
		"Toby",
		"Walter",
		"William",
		"Xavier",
		"Zachary"
	};
	
	public static String[] first_names_female = {
		"Amy",
		"Alison",
		"Barbara",
		"Catherine",
		"Caitlin",
		"Dawn",
		"Doris",
		"Emily",
		"Fiona",
		"Felicity",
		"Gertrude",
		"Hannah",
		"Julie",
		"Jane",
		"Karen",
		"Lucy",
		"Mandy",
		"Mary",
		"Nora",
		"Paula",
		"Rita",
		"Sally",
		"Sarah",
		"Tara",
		"Veronica",
		"Wendy",
		"Zara"
	};
	
	public static String generateName(boolean sex) {
		Random rand = new Random();
		if(sex == Patient.MALE) {
			return first_names_male[rand.nextInt(first_names_male.length)];
		}
		else {
			return first_names_female[rand.nextInt(first_names_female.length)];
		}
	}
}
