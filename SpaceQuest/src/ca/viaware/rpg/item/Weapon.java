package ca.viaware.rpg.item;

import org.newdawn.slick.opengl.Texture;

public class Weapon extends Item{

	@SuppressWarnings("unused")
	private double minimumDamage, maximumDamage, fireRate;
	public Weapon(Texture t, double minDmg, double maxDmg, double fireRate) {
		super(t);
		minimumDamage = minDmg;
		maximumDamage = maxDmg;
		this.fireRate = fireRate;
	}

}
