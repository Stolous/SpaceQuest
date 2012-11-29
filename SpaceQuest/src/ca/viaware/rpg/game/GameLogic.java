package ca.viaware.rpg.game;




public class GameLogic {
PlayerMovement pMovement = new PlayerMovement();

	public void doLogic(int delta) {
		
		pMovement.checkMovement(delta);
		
		Globals.m.update(delta);
		
	}
}
