package ca.viaware.rpg.item;

import org.newdawn.slick.opengl.Texture;

public class Item {

	private Texture texture;
	
	public Item(Texture t){
		texture = t;
	}
	
	public Texture getTexture(){
		return texture;
	}
	
	public void setTexture(Texture t){
		texture = t;
	}
}
