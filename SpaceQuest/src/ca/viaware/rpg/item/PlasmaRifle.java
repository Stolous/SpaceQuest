package ca.viaware.rpg.item;

import org.newdawn.slick.opengl.Texture;

import ca.viaware.rpg.entity.Bullet;
import ca.viaware.rpg.game.Globals;

public class PlasmaRifle extends Weapon{

	public PlasmaRifle(int minDmg, int maxDmg) {
		super(Globals.gunTextures.get(0), new Bullet(Globals.bulletTextures.get(0), 0, 0, 0, 0, 0, 0, 0, Bullet.targetType.ENEMIES), minDmg, maxDmg, 500);
	}

}
