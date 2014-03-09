package hsim.object;

public class GameObject {
	
	public int id;
	public String name;
	public String texture_name;
	public boolean canHaveMultiple;
	
	public GameObject(int id, String name, String texture_name, boolean canHaveMultiple) {
		this.id = id;
		this.name = name;
		this.texture_name = texture_name;
		this.canHaveMultiple = canHaveMultiple;
	}
	
	public void onPlaced(int x, int y) {
		System.out.println("Placed a " + name + " at x: " + x + ", y: " + y);
	}
}
