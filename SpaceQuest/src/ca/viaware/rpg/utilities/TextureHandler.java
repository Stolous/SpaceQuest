package ca.viaware.rpg.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class TextureHandler {

	public Texture loadTexture(String key){
		File location = new File("res/tex/" + key + ".png");
		
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
	
	public Texture loadSprite(String key){
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
	
	public Texture loadDiffTexture(String addr, String type){
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
}
