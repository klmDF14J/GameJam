package hsim;

import hsim.loader.GameLoader;
import hsim.state.MenuState;
import hsim.state.PlayState;
import hsim.state.SplashState;
import hsim.util.GameInfo;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class HospitalSimulator extends StateBasedGame {
	
	public static AppGameContainer app;
	
	public HospitalSimulator(String name) {
		super(name);
	}
	
	public static void main(String[] args) throws SlickException {
		app = new AppGameContainer(new HospitalSimulator(GameInfo.title + " v" + GameInfo.version));
		
		app.setDisplayMode(1280, 720, false);
		app.setTargetFrameRate(GameInfo.fps);
		app.start();
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		GameLoader.loadGame();
		
		addState(new SplashState(0));
		addState(new MenuState(1));
		addState(new PlayState(2));
	}
}
