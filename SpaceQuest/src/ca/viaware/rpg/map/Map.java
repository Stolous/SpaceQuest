package ca.viaware.rpg.map;

import ca.viaware.rpg.game.Globals;
import ca.viaware.rpg.utilities.Location;
import ca.viaware.rpg.utilities.TextRenderer;

public class Map {
	public int xSize, ySize;
	private double xOffset, yOffset;
	public Tile mapTiles[][];
	Location spawn;

	public void setSize(int x, int y) {
		xSize = x;
		ySize = y;
		mapTiles = new Tile[x][y];
	}

	public void renderMap() {
		int countx = 0;
		int county = 0;

		while (county < ySize) {
			while (countx < xSize) {

				if (mapTiles[countx][county].isEnabled()) {
					mapTiles[countx][county].render(countx, county, (int) xOffset, (int) yOffset);
					Globals.temp++;
				} else {
				}

				countx++;
			}
			countx = 0;
			county++;

		}

	}

	public void moveMap(double x, double y, int delta) {
		xOffset += x * delta;
		yOffset += y * delta;

	}

	public int getXOffset() {
		return (int) xOffset;
	}

	public int getYOffset() {
		return (int) yOffset;
	}

	public void setOffsets(int x, int y) {
		xOffset = x;
		yOffset = y;
	}

	public void setSpawn(int x, int y) {
		spawn = new Location(x, y);
	}

	public Location getSpawn() {
		return spawn;
	}
}
