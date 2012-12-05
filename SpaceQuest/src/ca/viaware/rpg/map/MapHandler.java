package ca.viaware.rpg.map;

import ca.viaware.rpg.game.Globals;

public class MapHandler {

	public static Map handleMapLoad(String name){
		Loader ld = new Loader();
		Map m = ld.loadMap(name);
		
		return m;
	}
	
	public static void finishLoad(){
		Globals.playerEntity.teleport(Globals.gameMap.getSpawn());
	}
}
