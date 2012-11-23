package ca.viaware.rpg.map;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import ca.viaware.rpg.game.Globals;

public class Tile {

	private Texture tex = null;
	private boolean enabled = false;
	private int ID = 0;

	public Tile(boolean enabled, int ID){
		this.enabled = enabled;
		if (enabled){
		setID(ID);
		}
	}
	
	public void setTexture(Texture t) {
		tex = t;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public Texture getTexture() {
		return tex;
	}

	public void render(int cx, int cy, int xOff, int yOff) {
		
		if (enabled){
		Globals.tileTextures.get(ID).bind();
		}

		glBegin(GL_QUADS);
			glTexCoord2f(0f, 0f);
			glVertex2i(cx * 64 + xOff, cy * 64 + yOff);
			glTexCoord2f(1f, 0f);
			glVertex2i(cx * 64 + xOff + 64, cy * 64 + yOff);
			glTexCoord2f(1f, 1f);
			glVertex2i(cx * 64 + xOff + 64, cy * 64 + yOff + 64);
			glTexCoord2f(0f, 1f);
			glVertex2i(cx * 64 + xOff, cy * 64 + yOff + 64);
		glEnd();
	}

	public void setID(int id) {
		if (id == -1) {
			enabled = false;
		} else {
			ID = id;
			enabled = true;
		}
		if (enabled) {
			setTexture(Globals.tileTextures.get(ID));
		}
	}
}
