package ca.viaware.rpg.mapeditor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class TextureHandler {

	public Texture loadTexture(String key) {
		Texture tex = null;
		try {
			tex = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/tex/" + key + ".png")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return tex;
	}
}
