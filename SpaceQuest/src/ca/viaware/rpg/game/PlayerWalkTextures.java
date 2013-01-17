package ca.viaware.rpg.game;

import ca.viaware.rpg.utilities.TextureHandler;

public class PlayerWalkTextures {

	public static void loadTextures(){
		TextureHandler th = new TextureHandler();
		Globals.playerEntity.addTexture(th.loadSprite("player/down/1"));
		Globals.playerEntity.addTexture(th.loadSprite("player/down/2"));
		Globals.playerEntity.addTexture(th.loadSprite("player/down/3"));
		Globals.playerEntity.addTexture(th.loadSprite("player/down/4"));

		Globals.playerEntity.addTexture(th.loadSprite("player/up/1"));
		Globals.playerEntity.addTexture(th.loadSprite("player/up/2"));
		Globals.playerEntity.addTexture(th.loadSprite("player/up/3"));
		Globals.playerEntity.addTexture(th.loadSprite("player/up/4"));

		Globals.playerEntity.addTexture(th.loadSprite("player/right/1"));
		Globals.playerEntity.addTexture(th.loadSprite("player/right/2"));
		Globals.playerEntity.addTexture(th.loadSprite("player/right/3"));
		Globals.playerEntity.addTexture(th.loadSprite("player/right/4"));
		Globals.playerEntity.addTexture(th.loadSprite("player/right/5"));
		Globals.playerEntity.addTexture(th.loadSprite("player/right/6"));
		Globals.playerEntity.addTexture(th.loadSprite("player/right/7"));
		Globals.playerEntity.addTexture(th.loadSprite("player/right/8"));
		
		Globals.cursor.setTexture(th.loadSprite("other/cursor"));
	}
}
