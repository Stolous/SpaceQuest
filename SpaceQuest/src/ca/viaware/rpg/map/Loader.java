package ca.viaware.rpg.map;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import ca.viaware.rpg.game.Globals;

public class Loader {

	public Map loadMap(String name) {
		Map map = new Map();
		File location = new File("res/maps/" + name + ".map");
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
					mapTiles[i][ycount] = new Tile(0, 0, 64, 64, false, line[i], i, ycount);
				} else {
					Tile tile = new Tile(0, 0, 64, 64, true, line[i], i, ycount);
					
					mapTiles[i][ycount] = tile;
				}
			}
			ycount++;
		}
		
		while(sc.hasNextLine()){
			String[] data = sc.nextLine().split("/");
			
			if (data[0].equals("WP")){
				TeleMarker WP = new TeleMarker();
				WP.setType(Integer.parseInt(data[1]));
				WP.setName(data[2]);
				WP.setPointToMap(data[4]);
				WP.setPointTo(data[3]);
				Globals.teleMarkers.add(WP);
			}
		}
		
		for (Tile[] tile1 : mapTiles){
			for (Tile tile : tile1){
				tile.updateTeleMarkers();
			}
		}

		map.setSize(x, y);
		map.mapTiles = mapTiles;

		System.out.println("Map Loaded");
		return map;
	}
}
