package ca.viaware.rpg.game;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.opengl.Texture;

import ca.viaware.rpg.entities.MeleeEnemy;
import ca.viaware.rpg.entities.Player;
import ca.viaware.rpg.map.Map;
import ca.viaware.rpg.utilities.TextureHandler;

public class Globals {
	
	TextureHandler r;
	Texture demo = r.loadSprite("/enemies/slime moving/slimemoving0001.png");
	ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
	
	
	public static MeleeEnemy m = new MeleeEnemy(1,1,null,10,10,1,1,100);
	public static boolean isRunning = true;
	public static int dispWidth = 800, dispHeight = 600;
	public static int temp = 0;
	public static List<Texture> tileTextures = new ArrayList(16);
	public static Texture startImg = null;
	public static Player playerEntity = null;
	public static Map gameMap = null;

	public static enum gState {
		INTRO, MAIN_MENU, GAME, OUTRO;
	}

	public static gState gameState = gState.INTRO;

}


