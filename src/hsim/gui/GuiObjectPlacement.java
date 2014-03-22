package hsim.gui;

import java.util.ArrayList;

import hsim.resource.Images;
import hsim.state.PlayState;
import hsim.util.GameInfo;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

public class GuiObjectPlacement extends Gui {
	
	private Image gui_texture;
	
	public static int[] amounts = {1, 0, 0, 0};
	
	public static Button desk;
	public static Button bed;
	public static Button doctor;

	public GuiObjectPlacement(String texture_name) {
		super(texture_name);
		
		this.gui_texture = Images.getImage("gui/" + texture_name);
		
		float scale = 1F;
		desk = new Button((int) (GameInfo.resolution.getWidth() / 2 - (gui_texture.getWidth() * scale / 2)) + 32, (int) (GameInfo.resolution.getHeight() / 2 - (gui_texture.getHeight() * scale / 2)) + 32, 208, 208) {
			@Override
			public boolean onClicked(Rectangle bounds_mouse) {
				if(super.onClicked(bounds_mouse)) {
					System.out.println("Clicked desk button");
					PlayState.currentObject = 0;
					return true;
				}
				else {
					return false;
				}
			}
		};
		
		bed = new Button((int) (GameInfo.resolution.getWidth() / 2 - (gui_texture.getWidth() * scale / 2)) + 272, (int) (GameInfo.resolution.getHeight() / 2 - (gui_texture.getHeight() * scale / 2)) + 32, 208, 208) {
			@Override
			public boolean onClicked(Rectangle bounds_mouse) {
				if(super.onClicked(bounds_mouse)) {
					System.out.println("Clicked bed button");
					PlayState.currentObject = 1;
					return true;
				}
				else {
					return false;
				}
			}
		};
	}
	
	@Override
	public void render(Graphics g) {
		float scale = 1F;
		gui_texture.draw(GameInfo.resolution.getWidth() / 2 - (gui_texture.getWidth() * scale / 2), GameInfo.resolution.getHeight() / 2 - (gui_texture.getHeight() * scale / 2), scale);
		
		g.drawString("" + amounts[0], GameInfo.resolution.getWidth() / 2 - (gui_texture.getWidth() * scale / 2) + 32, GameInfo.resolution.getHeight() / 2 - (gui_texture.getHeight() * scale / 2) + 32);
		g.drawString("" + amounts[1], GameInfo.resolution.getWidth() / 2 - (gui_texture.getWidth() * scale / 2) + 288, GameInfo.resolution.getHeight() / 2 - (gui_texture.getHeight() * scale / 2) + 32);
		g.drawString("" + amounts[2], GameInfo.resolution.getWidth() / 2 - (gui_texture.getWidth() * scale / 2) + 32, GameInfo.resolution.getHeight() / 2 - (gui_texture.getHeight() * scale / 2) + 288);
		g.drawString("" + amounts[3], GameInfo.resolution.getWidth() / 2 - (gui_texture.getWidth() * scale / 2) + 288, GameInfo.resolution.getHeight() / 2 - (gui_texture.getHeight() * scale / 2) + 288);
	}
	
	@Override
	public void onMousePressed(int button, int x, int y) {
		Rectangle bounds_mouse = new Rectangle(x, y, 1, 1);
		
		desk.onClicked(bounds_mouse);
		bed.onClicked(bounds_mouse);
	}
}
