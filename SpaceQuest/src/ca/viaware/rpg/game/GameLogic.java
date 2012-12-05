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
	
//	float ySpeed = 0;
//	float xSpeed = 0;
//
//	double speed = 2;
//
//	// Maths to make bullet go in direction thing
//	xSpeed = (float) (bullet.gotoX - bullet.oldPlayerX);
//	ySpeed = (float) (bullet.gotoY - bullet.oldPlayerY);
//
//	float factor = (float) (speed / Math.sqrt(xSpeed * xSpeed + ySpeed * ySpeed));
//
//	xSpeed = xSpeed * factor;
//	ySpeed = ySpeed * factor;
//
//	bullet.setDX(xSpeed);
//	bullet.setDY(ySpeed);
//
//	bullet.update(delta);
	PlayerMovement pMovement = new PlayerMovement();
	FullscreenHandler fHandler = new FullscreenHandler();
	private int timer = 0, count = 0;

	public void doLogic(int delta) {

		if (Globals.gameState == Globals.gState.INTRO && count == 2) {
			timer += delta;
			if (timer > 1500) {
				System.out.println("Switching gamestate to main menu");
				Globals.gameState = Globals.gState.MAIN_MENU;
				Globals.cursor.setEnabled(true);
			}
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
