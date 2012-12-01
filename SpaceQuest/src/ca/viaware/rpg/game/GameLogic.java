package ca.viaware.rpg.game;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class GameLogic {
	PlayerMovement pMovement = new PlayerMovement();
	private int timer = 0, count = 0;
	public void doLogic(int delta) {
		
		if (Globals.gameState == Globals.gState.INTRO && count == 2){
			timer += delta;
			if (timer > 1500){
				System.out.println("Switching gamestate to main menu");
				Globals.gameState = Globals.gState.MAIN_MENU;
				Globals.cursor.setEnabled(true);
			}
		}else if (count <= 2){
			//Count loops because delta is a huge number in the first loop through, and we need to wait for it to get back to normal
			count++;
		}
		
		// Allows user to leave the game screen if desired
		if (!Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			Mouse.setGrabbed(true);
		} else {
			Mouse.setGrabbed(false);
		}

		pMovement.checkMovement(delta);

		Globals.s.update(delta);

		Globals.cursor.update(delta);

	}

}
