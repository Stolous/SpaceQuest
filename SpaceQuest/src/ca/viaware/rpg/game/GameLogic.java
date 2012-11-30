package ca.viaware.rpg.game;




public class GameLogic {
PlayerMovement pMovement = new PlayerMovement();

	public void doLogic(int delta) {
		
		pMovement.checkMovement(delta);
		
		Globals.s.update(delta);
		
		Globals.cursor.update(delta);
		
	}
	
}
