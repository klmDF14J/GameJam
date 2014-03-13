package hsim.task;

import hsim.util.GameInfo;

public class Task {
	
	public static final boolean DOCTOR = false;
	public static final boolean NURSE = true;
	
	public String name;
	public int time;
	public boolean requires;
	
	public int timeTakenSoFar;
	
	public Task(String name, int time, boolean requires) {
		this.name = name;
		this.time = time * GameInfo.fps;
		this.requires = requires;
	}
	
	public boolean isFinished() {
		if(timeTakenSoFar >= time) {
			System.out.println("Task has finished: " + name);
			onTaskComplete();
			return true;
		}
		else {
			return false;
		}
	}
	
	public void onTaskComplete() {
		
	}
}
