package ca.viaware.rpg.game;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;




public class GameLogic {
PlayerMovement pMovement = new PlayerMovement();

	public void doLogic(int delta) {
		
		if (!Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
			Mouse.setGrabbed(true);
		} else {
			Mouse.setGrabbed(false);
		}
		
		pMovement.checkMovement(delta);
		
		Globals.s.update(delta);
		
		Globals.cursor.update(delta);
		
	}
	
}
