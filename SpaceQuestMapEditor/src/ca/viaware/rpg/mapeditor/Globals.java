package ca.viaware.rpg.mapeditor;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.opengl.Texture;

public class Globals {
	public static int WIDTH = 800, HEIGHT = 600;
	public static boolean isRunning = true;
	public static int yOffset = 0, xOffset = 0;
	public static int mapSizeX, mapSizeY;
	public static boolean isSaved = true;
	public static int cSelected = 0;
	public static List<Texture> otherTextures = new ArrayList(16);
	public static List<Enemy> enemies = new ArrayList(16);
	public static List<Waypoint> waypoints = new ArrayList(16);
	
	public static enum brush {
		PAINT, ERASE, COLLISION, MARKERS;
	}

	public static brush selectedTool = brush.PAINT;
}
