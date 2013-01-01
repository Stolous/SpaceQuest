package ca.viaware.rpg.game;

import org.lwjgl.input.Keyboard;

import ca.viaware.rpg.map.MapHandler;
import ca.viaware.rpg.map.TeleMarker;
import ca.viaware.rpg.map.Tile;
import ca.viaware.rpg.utilities.TextRenderer;

public class PlayerMovement {
	private boolean arrowG1 = false, arrowG2 = false;
	private TextRenderer tRenderer = new TextRenderer(30, 0, TextRenderer.Font.WHITE);

	public void checkMovement(int delta) {
		double speed = Globals.playerEntity.getSpeed();
		boolean cUp = false, cDown = false, cRight = false, cLeft = false;
		// Collision:
		//int playerX = (int) (Globals.playerEntity.getActX() - Globals.playerEntity.getActX() % 64) / 64 + 2;
		//int playerY = (int) (Globals.playerEntity.getActY() - Globals.playerEntity.getActY() % 64) / 64 + 1;

		double playerX = Globals.playerEntity.getX();
		double playerY = Globals.playerEntity.getY();

		boolean teleMarkerCollision = false;
		TeleMarker tMarker = null;
		for (Tile[] tile1 : Globals.gameMap.mapTiles) {
			for (Tile tile : tile1) {
				// i reorganized this - not necessary to check if tile has
				// collision if it does not intersect player!
				if (Globals.playerEntity.intersects(tile)) {
					if (tile.hasCollision()) {
						double tileX = tile.getX();
						double tileY = tile.getY();
						if (playerX > tileX - 10) {
							cLeft = true;
						} else if (playerX <= tileX + 10) {
							cRight = true;
						}

						if (playerY >= tileY + 50) {
							cUp = true;
						} else if (playerY + 50 < tileY) {
							cDown = true;
						}
					}

					if (tile.hasTeleMarkerIn()) {
						teleMarkerCollision = true;
						tMarker = tile.getTeleMarker();
					}
				}
			}
		}

		if (teleMarkerCollision) {
			tRenderer.writeToScreen(Globals.dispWidth / 2 - 100, 100, "Press [E] to travel");

			if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
				Globals.gameMap = MapHandler.handleMapLoad(tMarker.getPointToMap());
				int x = 0;
				int y = 0;
				for (Tile[] tile1 : Globals.gameMap.mapTiles) {
					x++;
					for (Tile tile : tile1) {
						y++;
						if (tile.hasTeleMarkerOut()) {
							System.out.println("Has telemarker in");
							if (tile.getTeleMarker().getName().equals(tMarker.getPointTo())) {
								System.out.println(x + ", " + y);
								Globals.playerEntity.teleportXY(x * 64, y * 64);
							}
						}
					}
					y = 0;
				}
			}
		}

		if (delta < 60) {
			if (Keyboard.isKeyDown(Keyboard.KEY_LEFT) || Keyboard.isKeyDown(Keyboard.KEY_A) && !cLeft) {
				Globals.gameMap.moveMap(speed, 0, delta);
				Globals.playerEntity.changePosition(-speed, 0, delta);
				arrowG1 = true;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT) || Keyboard.isKeyDown(Keyboard.KEY_D) && !cRight) {
				Globals.gameMap.moveMap(-speed, 0, delta);
				Globals.playerEntity.changePosition(speed, 0, delta);
				Globals.playerEntity.setWalkingDir(3);
				arrowG1 = true;
			} else {
				arrowG1 = false;
			}

			if (Keyboard.isKeyDown(Keyboard.KEY_DOWN) || Keyboard.isKeyDown(Keyboard.KEY_S) && !cDown) {
				Globals.gameMap.moveMap(0, -speed, delta);
				Globals.playerEntity.changePosition(0, speed, delta);
				Globals.playerEntity.setWalkingDir(1);
				arrowG2 = true;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_UP) || Keyboard.isKeyDown(Keyboard.KEY_W) && !cUp) {
				Globals.gameMap.moveMap(0, speed, delta);
				Globals.playerEntity.changePosition(0, -speed, delta);
				Globals.playerEntity.setWalkingDir(2);
				arrowG2 = true;
			} else {
				arrowG2 = false;
			}

			if (!arrowG1 && !arrowG2) {
				Globals.playerEntity.setWalkingDir(0);
			}

		}

		tRenderer.finish();
		Globals.playerEntity.update(delta);
	}
}
