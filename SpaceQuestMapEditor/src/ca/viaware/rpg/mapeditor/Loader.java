package ca.viaware.rpg.mapeditor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Loader {

	public static List<Tile> loadMap(String name) {
		List<Tile> tiles = new ArrayList(16);
		File location = new File("res/maps/" + name + ".map");
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream(location));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Globals.mapSizeX = Integer.parseInt(sc.nextLine());
		Globals.mapSizeY = Integer.parseInt(sc.nextLine());

		MapEditor.tiles.clear();
		Globals.waypoints.clear();
		Globals.enemies.clear();

		int x = 0;
		int y = 0;

		while (sc.hasNextLine()) {

			if (y < Globals.mapSizeY) {
				String[] line = sc.nextLine().split("&");

				for (String lin : line) {
					if (lin.equals("N")) {
						// System.out.println("Null tile at " + x + ", " + y);
					} else {
						System.out.println("Found tile at " + x + ", " + y);
						Tile tile = new Tile(x * 64, y * 64, 0, false);
						tile.passData(lin, x, y);
						tiles.add(tile);
					}
					x++;
				}
				x = 0;
				y++;
			} else {
				String[] data = sc.nextLine().split("/");

				if (data[0].equals("WP")) {
					Waypoint WP = new Waypoint();
					WP.setType(Integer.parseInt(data[1]));
					WP.setName(data[2]);
					WP.setPointTo(data[3]);
					WP.setPointToMap(data[4]);
					Globals.waypoints.add(WP);
				}else if (data[0].equals("EN")){
					Enemy enemy = new Enemy(Globals.enemyTypes.get(Integer.parseInt(data[1])), Integer.parseInt(data[2]));
					Globals.enemies.add(enemy);
				}
			}
		}
		
		for (Tile tile : tiles){
			tile.finishLoading();
		}
		return tiles;
	}

	public static boolean checkFile(String file) {
		File location = new File("res/maps/" + file + ".map");
		boolean isFile = true;
		try {
			Scanner sc = new Scanner(new FileInputStream(location));
		} catch (FileNotFoundException e) {
			isFile = false;
		}

		return isFile;
	}
}
