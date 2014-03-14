package hsim.loader;

import hsim.handler.GuiHandler;
import hsim.object.Objects;
import hsim.objective.Objectives;
import hsim.resource.Images;
import hsim.util.GameInfo;
import hsim.util.SysInfo;

import java.io.File;

public class GameLoader {
	
	public static void loadGame() {
		Objects.init();
		Objectives.init();
		Images.init();
		
		GuiHandler.init();
		
		checkDevMode();
	}

	private static void checkDevMode() {
		File file = new File(SysInfo.userDir + "/devMode.txt");
		if(file.exists()) {
			GameInfo.devMode = true;
			System.out.println("Located devMode.txt file, launching in dev mode");
		}
	}
}
