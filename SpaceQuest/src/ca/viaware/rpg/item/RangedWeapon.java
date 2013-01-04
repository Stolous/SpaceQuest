package ca.viaware.rpg.item;

import org.newdawn.slick.opengl.Texture;

public class RangedWeapon extends Weapon{

	private double bulletRange;
	public RangedWeapon(Texture t, double minDmg, double maxDmg, double fireRate, double range) {
		super(t, minDmg, maxDmg, fireRate);
		bulletRange = range;
	}

}
