package ca.viaware.rpg.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class TextureHandler {

	@SuppressWarnings("static-access")
	public Texture loadSprite(String key) {
		File location = new File("res/sprites/" + key + ".png");

		Texture texture = null;

		try {
			texture = new TextureLoader().getTexture("PNG", new FileInputStream(location));
		} catch (FileNotFoundException e) {
			System.out.println("Could not find texture: " + key);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Unknown IO exception");
			e.printStackTrace();
		}

		return texture;
	}

	@SuppressWarnings("static-access")
	public Texture loadDiffTexture(String addr, String type) {
		File location = new File(addr);

		Texture texture = null;

		try {
			texture = new TextureLoader().getTexture(type, new FileInputStream(location));
		} catch (FileNotFoundException e) {
			System.out.println("Could not find texture: " + addr);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Unknown IO exception");
			e.printStackTrace();
		}

		return texture;
	}

	public Animation loadAnimation(String animFolder, int speed) {
		int count = 1;
		boolean hasNext = true;
		List<Texture> aList = new ArrayList<Texture>(16);

		while (hasNext) {
			File file = new File("res/sprites/" + animFolder + "/" + Integer.toString(count) + ".png");
			if (file.exists()) {
				Texture texture = loadSprite(animFolder + "/" + Integer.toString(count));
				aList.add(texture);
			} else {
				hasNext = false;
			}
			count++;
			
		}
		
		System.out.println("Loaded animation: " + animFolder + " with " + aList.size() + " stages");

		return new Animation(aList, speed);
	}
}
