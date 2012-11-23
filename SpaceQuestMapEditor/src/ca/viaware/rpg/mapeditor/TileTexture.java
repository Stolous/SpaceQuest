package ca.viaware.rpg.mapeditor;

import org.newdawn.slick.opengl.Texture;

public class TileTexture{

	private Texture tex = null;
	private String name = "";
	
	public TileTexture(Texture t, String n){
		tex = t;
		name = n;
	}
	
	public Texture getTexture(){
		return tex;
	}
	
	public String getName(){
		return name;
	}
	
}
