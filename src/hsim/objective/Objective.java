package hsim.objective;

import hsim.handler.GameHandler;

public class Objective {
	
	public String name;
	public String script;
	public int reward;
	
	public Objective(String name, String script, int reward) {
		this.name = name;
		this.script = script;
		this.reward = reward;
	}
	
	public boolean isComplete() {
		return false;
	}
	
	public void onComplete() {
		GameHandler.money += reward;
	}
}
