package ca.viaware.rpg.map;

import ca.viaware.rpg.game.Globals;

public class MapHandler {

	public static Map handleMapLoad(String name){
		Globals.teleMarkers.clear();
		Globals.enemies.clear();
		Loader ld = new Loader();
		Map m = ld.loadMap(name);
		
		for (Tile[] tile2 : m.mapTiles){
			for (Tile tile : tile2){
				if (tile.isEnemy()){
					
				}
			}
		}
		
		return m;
	}
	
	public static void finishLoad(){
		
	}
}
