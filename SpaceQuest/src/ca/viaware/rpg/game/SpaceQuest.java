package ca.viaware.rpg.game;

import ca.viaware.rpg.entities.UFO;

public class SpaceQuest {

	public SpaceQuest() {

		DispSetup.setupDisplay();
		DispSetup.setupGL();
		GameLoop gloop = new GameLoop();
		gloop.startLoop();

	}

	public static void main(String[] args) {
		new SpaceQuest();

	}
}
