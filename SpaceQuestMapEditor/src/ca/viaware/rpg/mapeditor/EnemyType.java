package ca.viaware.rpg.mapeditor;

import org.newdawn.slick.opengl.Texture;

public class EnemyType {
	private String name;
	private Texture texture;

	public EnemyType(Texture tex, String n) {
		name = n;
		texture = tex;
	}

	public String getName() {
		return name;
	}

	public Texture getTexture() {
		return texture;
	}

}
