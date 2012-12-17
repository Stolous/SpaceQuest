package ca.viaware.rpg.game;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.opengl.Texture;

import ca.viaware.rpg.entity.Enemy;
import ca.viaware.rpg.entity.MeleeEnemy;
import ca.viaware.rpg.entities.Cursor;
import ca.viaware.rpg.entities.FastSlime;
import ca.viaware.rpg.entities.HealthBar;
import ca.viaware.rpg.entities.Player;
import ca.viaware.rpg.entities.Slime;
import ca.viaware.rpg.entities.WeakSlime;
import ca.viaware.rpg.entities.AmmoBar2;
import ca.viaware.rpg.map.Map;
import ca.viaware.rpg.map.TeleMarker;
import ca.viaware.rpg.utilities.Button;
import ca.viaware.rpg.utilities.TextRenderer;

public class Globals {

	ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();

	public static HealthBar healthBar;
	//please note mobs should not be spawned at 0,0 or where they cannot reach player
	public static ArrayList <Enemy> enemies=new ArrayList<Enemy>();
	public static AmmoBar2 ammoBar;
	public static boolean isRunning = true;
	public static int dispWidth = 800, dispHeight = 600;
	public static int dispWidthBK = 0, dispHeightBK = 0;
	public static int temp = 0;
	public static List<Texture> tileTextures = new ArrayList<Texture>(16);
	public static Texture startImg = null;
	public static Player playerEntity = null;
	public static Map gameMap = null;
	public static Cursor cursor;
	public static boolean isFullscreen = false;
	public static List<TeleMarker> teleMarkers = new ArrayList<TeleMarker>(16);
	public static List<TextRenderer> textRendererBufferList = new ArrayList<TextRenderer>(16);
	public static List<Button> buttonBuffer = new ArrayList<Button>(16);

	public static enum gState {
		INTRO, MAIN_MENU, GAME, OUTRO;
	}

	public static gState gameState = gState.INTRO;

}
