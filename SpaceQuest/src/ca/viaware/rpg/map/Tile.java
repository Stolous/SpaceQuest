package ca.viaware.rpg.map;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import ca.viaware.rpg.game.Globals;

public class Tile {

	private Texture tex = null;
	private boolean enabled = false;
	private int ID = 0;
	private int l2ID = 0;
	private boolean hasLayer2 = false;

	public Tile(boolean enabled, String data){
		this.enabled = enabled;
		if (data.equals("N")){
			ID = -1;
		}
		if (enabled){
		parseInputData(data);
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
		
		if (hasLayer2){
			Globals.tileTextures.get(l2ID).bind();
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
	
	public void setLayer2ID(int l2){
		l2ID = l2;
	}
	
	public void setLayer2(boolean l2){
		hasLayer2 = l2;
	}
	
	public void parseInputData(String d){
		String[] data = d.split("/");
		System.out.println("Input: " + d);
		
		setID(Integer.parseInt(data[0]));
		
		if (data[1].equals("N")){
			setLayer2ID(0);
			setLayer2(false);
		}else{
			setLayer2ID(Integer.parseInt(data[1]));
			setLayer2(true);
		}
	}
}
