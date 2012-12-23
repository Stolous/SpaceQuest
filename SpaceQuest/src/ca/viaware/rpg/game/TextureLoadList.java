package ca.viaware.rpg.game;

import ca.viaware.rpg.utilities.TextureHandler;

public class TextureLoadList {

	public static void loadTextures(){
		TextureHandler th = new TextureHandler();
		Globals.otherTextures.add(th.loadDiffTexture("res/img/intro2.png", "PNG"));
		Globals.otherTextures.add(th.loadDiffTexture("res/img/maploading.png", "PNG"));
		
		Globals.tileTextures.add(th.loadSprite("tiles/grass"));
		Globals.tileTextures.add(th.loadSprite("tiles/cobble"));
		Globals.tileTextures.add(th.loadSprite("tiles/flower red"));
		Globals.tileTextures.add(th.loadSprite("tiles/flower"));
		Globals.tileTextures.add(th.loadSprite("tiles/palm"));
		Globals.tileTextures.add(th.loadSprite("tiles/sand"));
		Globals.tileTextures.add(th.loadSprite("tiles/tree"));
		Globals.tileTextures.add(th.loadSprite("tiles/alien cactus plant 1"));
		Globals.tileTextures.add(th.loadSprite("tiles/alien cactus plant 2"));
		Globals.tileTextures.add(th.loadSprite("tiles/double alien cactus plant"));
		Globals.tileTextures.add(th.loadSprite("tiles/red rock"));
		Globals.tileTextures.add(th.loadSprite("tiles/sign"));
		Globals.tileTextures.add(th.loadSprite("tiles/dirt"));

		System.out.println("Loaded " + Globals.tileTextures.size() + " tile textures");

		// Player animation stages - Temporary, messy and needs redoing
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
