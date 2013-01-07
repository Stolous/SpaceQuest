package ca.viaware.rpg.game;

import org.lwjgl.opengl.Display;

import ca.viaware.rpg.entity.AbstractEntity;
import ca.viaware.rpg.utilities.TexturedQuad;

public class Night extends AbstractEntity {
	
	private TexturedQuad night;

	public Night() {
		super(Display.getWidth()/2, Display.getHeight()/2, Display.getWidth(), Display.getHeight());
	
		night = new TexturedQuad();
	}

	@Override
	public void draw() {
		
	}

	@Override
	public void update(int delta) {
		
	}

}
