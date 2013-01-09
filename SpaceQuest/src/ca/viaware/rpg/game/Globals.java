package ca.viaware.rpg.game;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.opengl.Texture;

import ca.viaware.rpg.entity.Bullet;
import ca.viaware.rpg.entity.Enemy;
import ca.viaware.rpg.entities.Cursor;
import ca.viaware.rpg.entities.HealthBar;
import ca.viaware.rpg.entities.Player;
import ca.viaware.rpg.map.Map;
import ca.viaware.rpg.map.TeleMarker;
import ca.viaware.rpg.map.TileTexture;
import ca.viaware.rpg.utilities.Animation;
import ca.viaware.rpg.utilities.SButton;
import ca.viaware.rpg.utilities.TextRenderer;

public class Globals {

	ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();

	public static float musiclevel =0.1f;
	public static Night night;
	public static MainMenu mainMenu;
	public static HealthBar healthBar;
	public static ArrayList <Enemy> enemies=new ArrayList<Enemy>();
	public static boolean isRunning = true;
	public static int dispWidth = 800, dispHeight = 600;
	public static int dispWidthBK = 0, dispHeightBK = 0;
	public static int temp = 0;
	public static List<TileTexture> tileTextures = new ArrayList<TileTexture>(16);
	public static List<Texture> otherTextures = new ArrayList<Texture>(16);
	public static List<Texture> bulletTextures = new ArrayList<Texture>(16);
	public static Player playerEntity = null;
	public static Map gameMap = null;
	public static Cursor cursor;
	public static boolean isFullscreen = false;
	public static List<TeleMarker> teleMarkers = new ArrayList<TeleMarker>(16);
	public static List<TextRenderer> textRendererBufferList = new ArrayList<TextRenderer>(16);
	public static List<SButton> buttonBuffer = new ArrayList<SButton>(16);
	public static List<Audio> musicLibrary = new ArrayList<Audio>(16);
	public static List<Animation> tileAnimations = new ArrayList<Animation>(16);
	public static List<Bullet> bullets = new ArrayList<Bullet>(16);

	public static enum gState {
		INTRO, MAIN_MENU, GAME, OUTRO;
	}

	public static gState gameState = gState.INTRO;

}
