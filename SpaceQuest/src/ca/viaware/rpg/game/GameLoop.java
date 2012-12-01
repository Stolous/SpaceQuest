package ca.viaware.rpg.game;

import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import ca.viaware.rpg.entity.MeleeEnemy;
import ca.viaware.rpg.entities.Cursor;
import ca.viaware.rpg.entities.HealthBar;
import ca.viaware.rpg.entities.Player;
import ca.viaware.rpg.entities.Slime;
import ca.viaware.rpg.map.Loader;
import ca.viaware.rpg.map.Map;
import ca.viaware.rpg.utilities.DebugScreen;
import ca.viaware.rpg.utilities.MouseData;
import ca.viaware.rpg.utilities.TextRenderer;
import ca.viaware.rpg.utilities.TextureHandler;

public class GameLoop {
	private long lasttime = 0;

	public void startLoop() {
		Globals.h = new HealthBar();
		Globals.playerEntity = new Player(Globals.dispWidth / 2 - 32, Globals.dispHeight / 2 - 32, 64, 64);
		Globals.cursor = new Cursor(0, 0, 32, 32);
		setupTextures();
		Loader loader = new Loader();
		Globals.gameMap = loader.loadMap();
		Renderer render = new Renderer();
		GameLogic logic = new GameLogic();
		DebugScreen dbs = new DebugScreen(170, 200);
		Globals.s = new Slime(0, 0, 50, 50, 1, 10, 10, 1, 1, 50);
		while (Globals.isRunning) {

			int delta = getDelta();

			// Update debug screen
			dbs.updateDelta(delta);
			dbs.updateMouseCoords(MouseData.MouseX(), MouseData.MouseY());
			dbs.updatePlayerCoords(Globals.playerEntity.getActX(), Globals.playerEntity.getActY());

			logic.doLogic(delta);
			render.render();
			Globals.s.reset();

			Display.update();
			Display.sync(60);

			if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
				Globals.isRunning = false;
			}
			if (Display.isCloseRequested()) {
				Globals.isRunning = false;
			}

		}
		Display.destroy();
		System.exit(0);
	}

	private void setupTextures() {
		TextureHandler th = new TextureHandler();
		Globals.startImg = th.loadDiffTexture("res/img/intro.png", "PNG");

		Globals.tileTextures.add(th.loadSprite("tiles/grass"));
		Globals.tileTextures.add(th.loadSprite("tiles/cobble"));
		Globals.tileTextures.add(th.loadSprite("tiles/flower red"));
		Globals.tileTextures.add(th.loadSprite("tiles/flower"));
		Globals.tileTextures.add(th.loadSprite("tiles/palm"));
		Globals.tileTextures.add(th.loadSprite("tiles/sand"));
		Globals.tileTextures.add(th.loadSprite("tiles/tree"));

		System.out.println("Loaded " + Globals.tileTextures.size() + " tile textures");

		// Player animation stages - Temporary, messy and needs redoing
		Globals.playerEntity.addTexture(th.loadSprite("player/down/1"));
		Globals.playerEntity.addTexture(th.loadSprite("player/down/2"));
		Globals.playerEntity.addTexture(th.loadSprite("player/down/3"));
		Globals.playerEntity.addTexture(th.loadSprite("player/down/4"));

		Globals.playerEntity.addTexture(th.loadSprite("player/up/1"));
		Globals.playerEntity.addTexture(th.loadSprite("player/up/2"));
		Globals.playerEntity.addTexture(th.loadSprite("player/up/3"));
		Globals.playerEntity.addTexture(th.loadSprite("player/up/4"));

		Globals.playerEntity.addTexture(th.loadSprite("player/right/1"));
		Globals.playerEntity.addTexture(th.loadSprite("player/right/2"));
		Globals.playerEntity.addTexture(th.loadSprite("player/right/3"));
		Globals.playerEntity.addTexture(th.loadSprite("player/right/4"));
		Globals.playerEntity.addTexture(th.loadSprite("player/right/5"));
		Globals.playerEntity.addTexture(th.loadSprite("player/right/6"));
		Globals.playerEntity.addTexture(th.loadSprite("player/right/7"));
		Globals.playerEntity.addTexture(th.loadSprite("player/right/8"));

		Globals.cursor.setTexture(th.loadSprite("other/cursor"));

	}

	public int getDelta() {
		long time = getTime();
		int delta = (int) (time - lasttime);
		lasttime = time;

		return delta;
	}

	private long getTime() {
		return (Sys.getTime() * 1000 / Sys.getTimerResolution());
	}

}
