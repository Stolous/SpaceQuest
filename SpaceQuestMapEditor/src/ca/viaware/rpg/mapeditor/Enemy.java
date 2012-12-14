package ca.viaware.rpg.mapeditor;

import org.newdawn.slick.opengl.Texture;

public class Enemy {
	private int ID;
	private String name;
	private Texture texture;
	//seth sux
	public Enemy(Texture tex, int id, String n){
		ID = id;
		name = n;
		texture = tex;
	}
	
	public String getName(){
		return name;
	}
	
	public int getID(){
		return ID;
	}
	
	public Texture getTexture(){
		return texture;
	}
}
