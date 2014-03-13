package hsim.objective;

public class Objective {
	
	public String name;
	public String script;
	
	public Objective(String name, String script) {
		this.name = name;
		this.script = script;
	}
	
	public boolean isComplete() {
		return false;
	}
}
