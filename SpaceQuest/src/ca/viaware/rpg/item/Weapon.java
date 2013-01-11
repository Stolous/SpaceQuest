package ca.viaware.rpg.item;

import org.newdawn.slick.opengl.Texture;

import ca.viaware.rpg.entity.Bullet;

public class Weapon extends Item{

	@SuppressWarnings("unused")
	private double minimumDamage, maximumDamage, fireRate;
	private Bullet bullet;
	public Weapon(Texture t, Bullet bullet, double minDmg, double maxDmg, double fireRate) {
		super(t);
		minimumDamage = minDmg;
		maximumDamage = maxDmg;
		this.fireRate = fireRate;
		this.bullet = bullet;
	}
	
	public void fireWeapon(int startX, int startY, int destX, int destY){
		
	}

}
