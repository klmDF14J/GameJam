package hsim.object;

public class GameObjectInstanceDoctor extends GameObjectInstance {

	public int posX;
	public int posY;
	public String name;
	public boolean busy = false;

	public GameObjectInstanceDoctor(String object_name, int posX, int posY) {
		super(object_name);
		
		this.posX = posX;
		this.posY = posY;
	}
}
