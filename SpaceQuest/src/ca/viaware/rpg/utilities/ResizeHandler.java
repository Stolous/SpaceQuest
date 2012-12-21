package ca.viaware.rpg.utilities;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.Display;

import ca.viaware.rpg.game.Globals;

public class ResizeHandler {

	public static void handleResize(){
		Globals.dispWidthBK = Globals.dispWidth;
		Globals.dispHeightBK = Globals.dispHeight;
		Globals.dispHeight = Display.getHeight();
		Globals.dispWidth = Display.getWidth();
		
		
		glViewport(0, 0, Globals.dispWidth, Globals.dispHeight);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, Globals.dispWidth, Globals.dispHeight, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		
		
		Globals.healthBar.windowResized();
		Globals.playerEntity.screenChanged();
	}
}
