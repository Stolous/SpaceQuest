package ca.viaware.rpg.game;

import org.lwjgl.input.Keyboard;

public class PlayerMovement {
	private boolean arrowG1 = false, arrowG2 = false;

	public void checkMovement(int delta) {
		double speed = Globals.playerEntity.getSpeed();
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			Globals.gameMap.moveMap(speed, 0, delta);
			arrowG1 = true;
		} else if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			Globals.gameMap.moveMap(-speed, 0, delta);
			Globals.playerEntity.setWalkingDir(3);
			arrowG1 = true;
		} else {
			arrowG1 = false;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			Globals.gameMap.moveMap(0, -speed, delta);
			Globals.playerEntity.setWalkingDir(1);
			arrowG2 = true;
		} else if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			Globals.gameMap.moveMap(0, speed, delta);
			Globals.playerEntity.setWalkingDir(2);
			arrowG2 = true;
		} else {
			arrowG2 = false;
		}

		if (!arrowG1 && !arrowG2) {
			Globals.playerEntity.setWalkingDir(0);
		}

		Globals.playerEntity.update(delta);
	}
}
