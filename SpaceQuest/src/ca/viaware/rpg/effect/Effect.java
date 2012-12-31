package ca.viaware.rpg.effect;

import ca.viaware.rpg.utilities.Colour;

public class Effect {

	private boolean isActive;
	private Colour colourChange;
	private double healthMultiply, armourMultiply, accuracyMultiply, speedMultiply;
	public Effect(Colour colourMod, double healthMod, double armourMod, double accuracyMod, double speedMod) {
		colourChange = colourMod;
		healthMultiply = healthMod;
		armourMultiply = armourMod;
		accuracyMultiply = accuracyMod;
		speedMultiply = speedMod;
	}
	
	public double getHealthMultiply(){
		return healthMultiply;
	}
	
	public double getArmourMultiply(){
		return armourMultiply;
	}
	
	public double getAccuracyMultiply(){
		return accuracyMultiply;
	}
	
	public double getSpeedMultiply(){
		return speedMultiply;
	}
	
	public void bindColour(){
		colourChange.bind();
	}
	
	public void setActive(boolean a){
		isActive = a;
	}
	
	public boolean isActive(){
		return isActive;
	}
}
