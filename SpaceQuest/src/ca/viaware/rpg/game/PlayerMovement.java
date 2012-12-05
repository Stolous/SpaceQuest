package ca.viaware.rpg.game;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import ca.viaware.rpg.map.Tile;

public class PlayerMovement {
	private boolean arrowG1 = false, arrowG2 = false;

	public void checkMovement(int delta) {
		double speed = Globals.playerEntity.getSpeed();
		List<Tile> tilesWithCollision = new ArrayList(16);
		tilesWithCollision.clear();
		int dir1, dir2;

		if (delta < 30) {
			if (Keyboard.isKeyDown(Keyboard.KEY_LEFT) || Keyboard.isKeyDown(Keyboard.KEY_A)) {
				Globals.gameMap.moveMap(speed, 0, delta);
				Globals.playerEntity.changePosition(-speed, 0, delta);
				dir1 = 1;
				arrowG1 = true;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT) || Keyboard.isKeyDown(Keyboard.KEY_D)) {
				Globals.gameMap.moveMap(-speed, 0, delta);
				Globals.playerEntity.changePosition(speed, 0, delta);
				Globals.playerEntity.setWalkingDir(3);
				dir1 = 2;
				arrowG1 = true;
			} else {
				arrowG1 = false;
				dir1 = 0;
			}

			if (Keyboard.isKeyDown(Keyboard.KEY_DOWN) || Keyboard.isKeyDown(Keyboard.KEY_S)) {
				Globals.gameMap.moveMap(0, -speed, delta);
				Globals.playerEntity.changePosition(0, speed, delta);
				Globals.playerEntity.setWalkingDir(1);
				dir2 = 1;
				arrowG2 = true;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_UP) || Keyboard.isKeyDown(Keyboard.KEY_W)) {
				Globals.gameMap.moveMap(0, speed, delta);
				Globals.playerEntity.changePosition(0, -speed, delta);
				Globals.playerEntity.setWalkingDir(2);
				dir2 = 2;
				arrowG2 = true;
			} else {
				dir2 = 0;
				arrowG2 = false;
			}

			if (!arrowG1 && !arrowG2) {
				Globals.playerEntity.setWalkingDir(0);
			}

		}
		// Collision:
		int tileX = 0, tileY = 0;
		int playerX = (int) (Globals.playerEntity.getActX() - Globals.playerEntity.getActX() % 64) / 64 + 2;
		int playerY = (int) (Globals.playerEntity.getActY() - Globals.playerEntity.getActY() % 64) / 64 + 1;

		for (Tile[] tile1 : Globals.gameMap.mapTiles) {
			tileX++;
			for (Tile tile : tile1) {
				tileY++;
				// i reorganized this - not necessary to check if tile has
				// collision if it does not intersect player!
				if (Globals.playerEntity.intersects(tile)) {
					if (tile.hasCollision()) {
						if (playerX > tileX) {
							Globals.gameMap.moveMap(-speed, 0, delta);
							Globals.playerEntity.changePosition(speed, 0, delta);
						} else if (playerX <= tileX) {
							Globals.gameMap.moveMap(speed, 0, delta);
							Globals.playerEntity.changePosition(-speed, 0, delta);
						}

						if (playerY >= tileY) {
							Globals.gameMap.moveMap(0, -speed, delta);
							Globals.playerEntity.changePosition(0, speed, delta);
						} else if (playerY < tileY) {
							Globals.gameMap.moveMap(0, speed, delta);
							Globals.playerEntity.changePosition(0, -speed, delta);
						}
					}
				}
			}
			tileY = 0;
		}

		Globals.playerEntity.update(delta);
	}
}
