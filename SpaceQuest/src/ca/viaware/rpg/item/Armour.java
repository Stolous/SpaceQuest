package ca.viaware.rpg.item;

import org.newdawn.slick.opengl.Texture;

public class Armour extends Item{

	private double armourMultiplyer;
	public Armour(Texture t, double armour) {
		super(t);
		armourMultiplyer = armour;
	}
	
	public double getMultiplyer(){
		return armourMultiplyer;
	}

}
