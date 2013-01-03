package ca.viaware.rpg.item;

import org.newdawn.slick.opengl.Texture;

public class Weapon extends Item{

	@SuppressWarnings("unused")
	private double baseDamage, fireRate;
	public Weapon(Texture t, double baseDmg, double fireRate) {
		super(t);
		baseDamage = baseDmg;
		this.fireRate = fireRate;
	}

}
