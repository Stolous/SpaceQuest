package ca.viaware.rpg.game;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;


public class GameLogic {
	PlayerMovement pMovement = new PlayerMovement();
	private int timer = 0, count = 0;
	private boolean fsSwitch = false;

	public void doLogic(int delta) {

		if (Globals.gameState == Globals.gState.INTRO && count == 2) {
			timer += delta;
			if (timer > 1500) {
				System.out.println("Switching gamestate to main menu");
				Globals.gameState = Globals.gState.MAIN_MENU;
				Globals.cursor.setEnabled(true);
			}
		} else if (count <= 2) {
			// Count loops because delta is a huge number in the first loop
			// through, and we need to wait for it to get back to normal
			count++;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_F)){
			Globals.dispWidthBK = Globals.dispWidth;
			Globals.dispHeightBK = Globals.dispHeight;
			if (!fsSwitch){
				System.out.println("Setting fullscreen");
			DisplayMode dispMode = Display.getDesktopDisplayMode();
			Globals.dispWidth = dispMode.getWidth();
			Globals.dispHeight = dispMode.getHeight();
			try {
				glViewport(0, 0, Globals.dispWidth, Globals.dispHeight);
				glMatrixMode(GL_PROJECTION);
				glLoadIdentity();
				glOrtho(0, Globals.dispWidth, Globals.dispHeight, 0, 1, -1);
				glMatrixMode(GL_MODELVIEW);
				Display.setDisplayMode(dispMode);
				Display.setFullscreen(true);
				Display.setVSyncEnabled(true);
			} catch (LWJGLException e) {
				e.printStackTrace();
			}
			System.out.println("Done");
			Globals.isFullscreen = true;
			fsSwitch = true;
			}else{
				System.out.println("Setting windowed");
				Globals.dispWidth = Globals.dispWidthBK;
				Globals.dispHeight = Globals.dispHeightBK;
				DisplayMode dispMode = new DisplayMode(Globals.dispWidth, Globals.dispHeight);
				try {
					glViewport(0, 0, Globals.dispWidth, Globals.dispHeight);
					Display.setDisplayMode(dispMode);
					Display.setFullscreen(false);
					Display.setVSyncEnabled(false);
				} catch (LWJGLException e) {
					e.printStackTrace();
				}
				System.out.println("Done");
				Globals.isFullscreen = false;
				fsSwitch = false;
			}
		}

		// Allows user to leave the game screen if desired
		if (!Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			Mouse.setGrabbed(true);
		} else {
			Mouse.setGrabbed(false);
		}

		pMovement.checkMovement(delta);

		for(int i=0;i<Globals.enemies.size();i++){
			Globals.enemies.get(i).update(delta);
		}

		Globals.cursor.update(delta);

	}

}
