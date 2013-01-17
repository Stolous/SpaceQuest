package ca.viaware.rpg.game;

import ca.viaware.rpg.map.TileTexture;
import ca.viaware.rpg.utilities.TextureHandler;

public class TextureLoadList {

	public static void loadTextures(){
		TextureHandler th = new TextureHandler();
		Globals.otherTextures.add(th.loadDiffTexture("res/img/intro2.png", "PNG"));
		Globals.otherTextures.add(th.loadDiffTexture("res/img/maploading.png", "PNG"));
		
		Globals.bulletTextures.add(th.loadSprite("bullets/LazerGreen"));
		
		Globals.tileTextures.add(new TileTexture(null, th.loadSprite("tiles/grass"), false));
		Globals.tileTextures.add(new TileTexture(null, th.loadSprite("tiles/cobble"), false));
		Globals.tileTextures.add(new TileTexture(null, th.loadSprite("tiles/flower red"), false));
		Globals.tileTextures.add(new TileTexture(null, th.loadSprite("tiles/flower"), false));
		Globals.tileTextures.add(new TileTexture(null, th.loadSprite("tiles/palm"), false));
		Globals.tileTextures.add(new TileTexture(null, th.loadSprite("tiles/sand"), false));
		Globals.tileTextures.add(new TileTexture(null, th.loadSprite("tiles/tree"), false));
		Globals.tileTextures.add(new TileTexture(null, th.loadSprite("tiles/alien cactus plant 1"), false));
		Globals.tileTextures.add(new TileTexture(null, th.loadSprite("tiles/alien cactus plant 2"), false));
		Globals.tileTextures.add(new TileTexture(null, th.loadSprite("tiles/double alien cactus plant"), false));
		Globals.tileTextures.add(new TileTexture(null, th.loadSprite("tiles/red rock"), false));
		Globals.tileTextures.add(new TileTexture(null, th.loadSprite("tiles/sign"), false));
		Globals.tileTextures.add(new TileTexture(null, th.loadSprite("tiles/dirt"), false));
		
		Globals.gunTextures.add(th.loadSprite("weapon/plasmarifle"));
		
		//Example of animated tile:
		//Globals.tileTextures.add(new TileTexture(th.loadAnimation("animfolder", fps), null, true));

		System.out.println("Loaded " + Globals.tileTextures.size() + " tile textures");

	}
}
