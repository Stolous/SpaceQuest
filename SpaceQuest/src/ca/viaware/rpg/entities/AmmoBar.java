package ca.viaware.rpg.entities;

import org.lwjgl.opengl.Display;

import ca.viaware.rpg.utilities.TextRenderer;
import ca.viaware.rpg.utilities.TexturedQuad;

public class AmmoBar {
	TextRenderer tRender = new TextRenderer(30, 0, TextRenderer.Font.BLACK);

	TexturedQuad back;

	public AmmoBar() {

		back = new TexturedQuad(65, 65, Display.getWidth() - 60, Display.getHeight() - 120, 180, "res/sprites/other/Ammo Icon.png");

	}

	public void update() {
		back.update();
	}

}