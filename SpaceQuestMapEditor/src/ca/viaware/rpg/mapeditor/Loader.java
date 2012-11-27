package ca.viaware.rpg.mapeditor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Loader {

	public List<Tile> loadMap (String name){
		List tiles = new ArrayList(16);
		File location = new File("res/maps/" + name + ".map");
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream(location));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Globals.mapSizeX = Integer.parseInt(sc.nextLine());
		Globals.mapSizeY = Integer.parseInt(sc.nextLine());
		
		while(sc.hasNextLine()){
			String[] line = sc.nextLine().split("&");
			
			for (String lin : line){
				if (line.equals("N")){
					
				}else{
					
				}
			}
		}
		return tiles;
		
	}
}
