package ca.viaware.rpg.item;

import org.newdawn.slick.opengl.Texture;

public class Weapon extends Item{

	private double baseDamage;
	public Weapon(Texture t, double baseDmg) {
		super(t);
		baseDamage = baseDmg;
	}

}
