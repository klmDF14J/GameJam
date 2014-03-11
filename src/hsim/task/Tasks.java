package hsim.task;

import java.util.ArrayList;

public class Tasks {
	
	public static Task diagnose;
	
	public static void init() {
		diagnose = new Task("diagnose", 4, Task.DOCTOR);
	}
}
