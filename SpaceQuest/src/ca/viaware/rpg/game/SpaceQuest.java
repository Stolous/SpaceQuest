package ca.viaware.rpg.game;

import ca.viaware.rpg.entities.UFO;


public class SpaceQuest {

	public SpaceQuest() {
		
		
		
		

		DispSetup.setupDisplay();
		DispSetup.setupGL();
		GameLoop gloop = new GameLoop();
		Globals.enemies.add(new UFO(1, 1));
		gloop.startLoop();

	}

	public static void main(String[] args) {
		new SpaceQuest();

	}
}
