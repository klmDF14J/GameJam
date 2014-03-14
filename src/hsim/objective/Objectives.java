package hsim.objective;

import java.util.ArrayList;

public class Objectives {
	
	public static ArrayList<Objective> objectives = new ArrayList<Objective>();
	
	public static void init() {
		objectives.add(new Objective("Paperwork...", "Place One Reception Desk"));
	}
}
