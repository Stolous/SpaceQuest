package ca.viaware.rpg.utilities;

import ca.viaware.rpg.entities.FastSlime;
import ca.viaware.rpg.entities.Slime;
import ca.viaware.rpg.entities.UFO;
import ca.viaware.rpg.entities.WeakSlime;
import ca.viaware.rpg.entity.Enemy;
import ca.viaware.rpg.map.Tile;

public class EnemyHandler {

	public static Enemy handleEnemy(int ID, int level){
		Enemy e = null;
		
		switch (ID){
		case 0:
			e = new Slime(0, 0);
			break;
		case 1:
			e = new WeakSlime(0, 0);
			break;
		case 2:
			e = new FastSlime(0, 0);
			break;
		case 3:
			e = new UFO(0, 0);
			break;
		}
		
		return e;
	}
}
