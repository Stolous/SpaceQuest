package ca.viaware.rpg.game;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.*;
import org.lwjgl.*;

import ca.viaware.rpg.map.Loader;

public class SpaceQuest {

	public SpaceQuest() {
		
		DispSetup.setupDisplay();
		DispSetup.setupGL();
		GameLoop gloop = new GameLoop();
		gloop.startLoop();
       
	}

	public static void main(String[] args) {
		new SpaceQuest();
	}                                                                        
	}
