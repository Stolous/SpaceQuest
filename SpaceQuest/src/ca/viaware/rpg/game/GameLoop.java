package ca.viaware.rpg.game;

import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;

import ca.viaware.rpg.audio.AudioLoadList;
import ca.viaware.rpg.entities.Cursor;
import ca.viaware.rpg.entities.HealthBar;
import ca.viaware.rpg.entities.Player;
import ca.viaware.rpg.map.MapHandler;
import ca.viaware.rpg.utilities.ResizeHandler;
import ca.viaware.rpg.utilities.DebugScreen;
import ca.viaware.rpg.utilities.MouseData;
import ca.viaware.rpg.utilities.TimeHandler;

public class GameLoop {
	private long lasttime = 0;

	public void startLoop() {
		Globals.mainMenu = new MainMenu();
		Globals.healthBar = new HealthBar();
		Globals.playerEntity = new Player(Globals.dispWidth / 2 - 32, Globals.dispHeight / 2 - 32, 64, 64);
		Globals.cursor = new Cursor(0, 0, 32, 32);
		Globals.cursor.setEnabled(true);
		TextureLoadList.loadTextures();
		AudioLoadList.loadAudio();
		Renderer render = new Renderer();
		GameLogic logic = new GameLogic();
		DebugScreen dbs = new DebugScreen(170, 200);
		// Globals.ammoBar = new AmmoBar();

		Globals.gameMap = MapHandler.handleMapLoad("CollisionTesting");
		Globals.gameMap.setObstacleMap();
		
		while (Globals.isRunning) {
			
			int delta = getDelta();

			if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
				delta = 40;
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_PERIOD)){
				Globals.musiclevel+=0.008;
			}else if(Keyboard.isKeyDown(Keyboard.KEY_COMMA)){
					Globals.musiclevel-=0.008;
				
			}
			// Update debug screen
			dbs.updateDelta(delta);
			dbs.updateMouseCoords(MouseData.MouseX(), MouseData.MouseY());
			dbs.updatePlayerCoords(Globals.playerEntity.getActX(), Globals.playerEntity.getActY());
			dbs.updateMapOffset(Globals.gameMap.getXOffset(), Globals.gameMap.getYOffset());
			dbs.updateRunningTime(TimeHandler.getMins(), TimeHandler.getSecs());
			dbs.updatePlayerHealth(Globals.playerEntity.getCurrentHealth());
			dbs.updateTotalEnemies(Globals.enemies.size());
			
			logic.doLogic(delta);
			render.render();

			if (Display.wasResized()) {
				ResizeHandler.handleResize();
			}

			Display.update();
			Display.sync(60);
			if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
				Globals.isRunning = false;
			}
			if (Display.isCloseRequested()) {
				Globals.isRunning = false;
			}
		}
		AL.destroy();
		Display.destroy();
		System.exit(0);
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
