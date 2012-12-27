package ca.viaware.rpg.mapeditor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Map {

	private static int count = 0;
	private static Tile[][] tiles = new Tile[1][1];
	private static List<String> sLines = new ArrayList(16);

	public static void handleOffset(int cx, int cy) {
		count++;

		System.out.println("COUNT: " + count);

		if (count == 1) {
			Globals.xOffset = Globals.xOffset + cx;
			Globals.yOffset = Globals.yOffset + cy;
			System.out.println("Offset: " + Globals.xOffset + " " + Globals.yOffset);
		}

		if (count >= MapEditor.tiles.size()) {
			count = 0;
		}
	}

	public static void passMap(List<Tile> tileArray, int sX, int sY) {
		int count = 0;
		tiles = new Tile[sX][sY];
		sLines.add(Integer.toString(sX));
		sLines.add(Integer.toString(sY));

		SaveProgressDialog sd = new SaveProgressDialog();
		sd.showFrame();

		for (int yC = 0; yC < sY; yC++) {
			String s = "";
			for (int xC = 0; xC < sX; xC++) {
				count++;
				boolean isTile = false;
				for (Tile tile : tileArray) {
					tiles[xC][yC] = new Tile(0, 0, 0, false);
					if (tile.getActX() == xC && tile.getActY() == yC) {
						System.out.println("Found tile at " + xC + ", " + yC);
						tiles[xC][yC] = tile;

						System.out.println("XC: " + xC + ", YC: " + yC + " has tile, adding all stuff to string...");
						String data = tiles[xC][yC].getDataString();
						s = s + data;
						isTile = true;
					}
				}

				if (!isTile) {
					System.out.println("There was no tile found at " + xC + ", " + yC + ", setting val to null");
					s = s + "N&";
				}

				System.out.println("Done. Line " + yC + " is now " + s);
				System.out.println(count + " total tiles handled");
			}
			sLines.add(s);

		}

		sd.exit();

	}

	public static void save(String name) {
		Saver saver = new Saver();
		try {
			saver.saveMap(sLines, name);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
