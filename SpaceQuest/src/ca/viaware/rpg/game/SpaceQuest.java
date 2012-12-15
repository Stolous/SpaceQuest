package ca.viaware.rpg.game;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.*;
import org.lwjgl.*;

import ca.viaware.rpg.entities.WeakSlime;
import ca.viaware.rpg.entities.UFO;
import ca.viaware.rpg.map.Loader;
import ca.viaware.rpg.utilities.Button;

public class SpaceQuest {

	public SpaceQuest() {
		
		DispSetup.setupDisplay();
		DispSetup.setupGL();
		GameLoop gloop = new GameLoop();
		Globals.button=new Button(50,50,50,50);
		Globals.enemies.add(new UFO(1,1));
		gloop.startLoop();
       
	}

	public static void main(String[] args) {
		new SpaceQuest();
		
	}                                                                        
	}
