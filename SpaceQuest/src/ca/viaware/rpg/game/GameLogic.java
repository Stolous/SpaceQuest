package ca.viaware.rpg.game;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import ca.viaware.rpg.utilities.FullscreenHandler;

public class GameLogic {
	

	PlayerMovement pMovement = new PlayerMovement();
	FullscreenHandler fHandler = new FullscreenHandler();
	private int timer = 0, count = 0;
	

	public void doLogic(int delta) {

		if (Globals.gameState == Globals.gState.INTRO && count == 2) {
			Globals.m.render();
			
			
		} else if (count <= 2) {
			count++;
		}

		fHandler.handleFullscreen();

		// Allows user to leave the game screen if desired
		if (!Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			Mouse.setGrabbed(true);
		} else {
			Mouse.setGrabbed(false);
		}

		pMovement.checkMovement(delta);

		for (int i = 0; i < Globals.enemies.size(); i++) {
			Globals.enemies.get(i).update(delta);
		}

		Globals.cursor.update(delta);

	}

}
