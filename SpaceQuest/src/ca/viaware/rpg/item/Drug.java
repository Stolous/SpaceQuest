package ca.viaware.rpg.item;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.opengl.Texture;

import ca.viaware.rpg.effect.Effect;

public class Drug extends Item{

	private List<Effect> drugEffects = new ArrayList<Effect>(16);
	
	public Drug(Texture t, List<Effect> effects) {
		super(t);
		drugEffects = effects;
	}

}
