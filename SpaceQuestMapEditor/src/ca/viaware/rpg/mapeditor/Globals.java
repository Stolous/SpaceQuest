package ca.viaware.rpg.mapeditor;

import org.newdawn.slick.opengl.Texture;

public class Globals {
	public static int WIDTH = 1000, HEIGHT = 1000;
	public static boolean isRunning = true;
	public static int yOffset = 0, xOffset = 0;
	public static int mapSizeX, mapSizeY;
	public static boolean isSaved = true;
	public static int cSelected = 0;

	public static enum brush {
		PAINT, ERASE, COLLISION;
	}

	public static brush selectedTool = brush.PAINT;
}
