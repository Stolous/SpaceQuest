package ca.viaware.rpg.effect;

import ca.viaware.rpg.utilities.Colour;

public class Effect {

	private Colour colourChange;
	private double healthMultiply, armourMultiply, accuracyMultiply;
	public Effect(Colour colourMod, double healthMod, double armourMod, double accuracyMod) {
		colourChange = colourMod;
		healthMultiply = healthMod;
		armourMultiply = armourMod;
		accuracyMultiply = accuracyMod;
	}
}
