package ca.viaware.rpg.game;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.*;

public class DispSetup {
	public static void setupDisplay() {
		try {
			Display.setTitle("SpaceQuest");
			Display.setDisplayMode(new DisplayMode(Globals.dispWidth, Globals.dispHeight));
			Display.setResizable(true);
			Display.setVSyncEnabled(true);
			Display.create();
		} catch (LWJGLException e) {

		}
	}

	public static void setupGL() {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, Globals.dispWidth, Globals.dispHeight, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		// Enabled transparency
		GL11.glEnable(GL_BLEND);
		GL11.glBlendFunc(GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glEnable(GL_ALPHA_TEST);
		GL11.glAlphaFunc(GL_GREATER, 0.1f);
	}
}
