package ca.viaware.rpg.game;

import org.lwjgl.input.Keyboard;

import ca.viaware.rpg.entities.MeleeEnemy;
import ca.viaware.rpg.entities.MeleeEnemy;

public class GameLogic {
PlayerMovement pMovement = new PlayerMovement();

	public void doLogic(int delta) {
		
		pMovement.checkMovement(delta);
		
		
		Globals.m.Update();
	}
}
