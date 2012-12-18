package ca.viaware.rpg.game;

import ca.viaware.rpg.entities.UFO;


public class SpaceQuest {

	public SpaceQuest() {
<<<<<<< HEAD
		
		
		DispSetup.setupDisplay();
		DispSetup.setupGL();
		GameLoop gloop = new GameLoop();
		Globals.enemies.add(new UFO(1,1));
		
=======

		DispSetup.setupDisplay();
		DispSetup.setupGL();
		GameLoop gloop = new GameLoop();
		Globals.enemies.add(new UFO(1, 1));
>>>>>>> c886dbb116635560960c098fa351e27a736d39a2
		gloop.startLoop();

	}

	public static void main(String[] args) {
		new SpaceQuest();

	}
}
