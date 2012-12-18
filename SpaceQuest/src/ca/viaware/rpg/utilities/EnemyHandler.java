package ca.viaware.rpg.utilities;

import ca.viaware.rpg.entities.Slime;
import ca.viaware.rpg.entities.UFO;
import ca.viaware.rpg.entity.Enemy;

public class EnemyHandler {

	public static Enemy handleEnemy(){
		return new UFO(200,200);
	}
}
