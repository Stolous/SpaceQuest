package ca.viaware.rpg.map;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Loader {

	public Map loadMap() {
		Map map = new Map();
		File location = new File("res/maps/Test.map");
		Scanner sc = null;
		try {
			sc = new Scanner(location);
		} catch (FileNotFoundException e) {
			System.out.println("Could not load map");
			e.printStackTrace();
		}

		int x = 0, y = 0;

		x = Integer.parseInt(sc.nextLine());
		y = Integer.parseInt(sc.nextLine());

		System.out.println("Map size is " + x + ", " + y);

		int ycount = 0;
		Tile mapTiles[][] = new Tile[x][y];

		while (ycount < y) {
			String[] line = sc.nextLine().split("&");
			for (int i = 0; i < line.length; i++) {
				if (line[i].equals("N")) {
					mapTiles[i][ycount] = new Tile(0, 0, 64, 64, false, line[i]);
				} else {
					mapTiles[i][ycount] = new Tile(0, 0, 64, 64, true, line[i]);
				}
			}
			ycount++;
		}

		map.setSize(x, y);
		map.mapTiles = mapTiles;

		System.out.println("Map Loaded");
		return map;
	}
}
