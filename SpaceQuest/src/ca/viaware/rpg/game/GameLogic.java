package ca.viaware.rpg.game;

import org.lwjgl.input.Keyboard;

public class GameLogic {
PlayerMovement pMovement = new PlayerMovement();

	public void doLogic(int delta) {
		
		pMovement.checkMovement(delta);
		
	}
}
