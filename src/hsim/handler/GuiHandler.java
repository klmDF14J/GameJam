package hsim.handler;

import hsim.HospitalSimulator;
import hsim.gui.Gui;

import org.newdawn.slick.Graphics;

public class GuiHandler {

	public static Graphics g;
	
	public static void init() {
		g = HospitalSimulator.app.getGraphics();
	}
	
	public static void showGui(Gui gui) {
		gui.render(g);
	}

}
