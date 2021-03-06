package hsim.object;

import hsim.state.PlayState;

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
		if(!canHaveMultiple) {
			for(int i = 0; i < PlayState.mapSizeX - PlayState.offsetX; i++) {
				for(int j = 0; j < PlayState.mapSizeY - PlayState.offsetY; j++) {
					if(PlayState.objectTiles[i][j] != null && PlayState.objectTiles[i][j].storedGameObject.id == id) {
						PlayState.objectTiles[i][j] = null;
						System.out.println("Moved a " + name + " from x: " + i + ", y: " + j + " to x: " + x + ", y: " + y);
					}
				}
			}
		}
	}
}
