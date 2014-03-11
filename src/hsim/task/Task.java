package hsim.task;

public class Task {
	
	public static final boolean DOCTOR = false;
	public static final boolean NURSE = true;
	
	public String name;
	public int time;
	public boolean requires;
	
	public Task(String name, int time, boolean requires) {
		this.name = name;
		this.time = time;
		this.requires = requires;
	}
}
