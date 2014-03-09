package hsim.state;

import hsim.resource.Images;
import hsim.util.GameInfo;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class SplashState extends HSimGameState {

	private static int splashTimer;
	private static int maxSplashTimer = 3 * GameInfo.fps;
	
	public SplashState(int id) {
		super(id);
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		if(!GameInfo.devMode) {
			if(splashTimer < maxSplashTimer) {
				splashTimer++;
				
				g.drawImage(Images.splash, 0, 0);
			}
			else {
				splashTimer = 0;
				
				sbg.enterState(1);
			}
		}
		else {
			sbg.enterState(1);
		}
	}
}
