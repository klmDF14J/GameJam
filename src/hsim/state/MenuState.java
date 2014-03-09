package hsim.state;

import hsim.resource.Images;
import hsim.util.KeyInfo;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class MenuState extends HSimGameState {

	private StateBasedGame game;
	
	public MenuState(int id) {
		super(id);
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.game = sbg;
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(Images.background_menu, 0, 0);
		g.drawImage(Images.logo, (1280 / 2) - (Images.logo.getWidth() / 2), 20);
		g.drawImage(Images.press_space, (1280 / 2) - (Images.press_space.getWidth() / 2), 650);
	}
	
	@Override
	public void keyPressed(int key, char c) {
		if(key == KeyInfo.advance) {
			game.enterState(2);
		}
	}
}
