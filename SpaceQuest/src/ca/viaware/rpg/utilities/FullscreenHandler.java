package ca.viaware.rpg.utilities;

import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glViewport;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import ca.viaware.rpg.game.Globals;

public class FullscreenHandler {
	private boolean fsSwitch = false;

	public void handleFullscreen() {
		if (Keyboard.isKeyDown(Keyboard.KEY_F)) {
			Globals.dispWidthBK = Globals.dispWidth;
			Globals.dispHeightBK = Globals.dispHeight;
			if (!fsSwitch) {
				Globals.healthBar.fullscreen();
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
			} else {
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
	}
}