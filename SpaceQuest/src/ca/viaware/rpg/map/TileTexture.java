package ca.viaware.rpg.map;

import org.newdawn.slick.opengl.Texture;

import ca.viaware.rpg.utilities.Animation;

public class TileTexture {
private boolean isAnimated = false;
private Texture texture;
private Animation animation;

	public TileTexture(Animation a, Texture t, boolean isAnimated){
		
		if (isAnimated){
			animation = a;
		}else{
			texture = t;
		}
		
	}
	
	public void updateTexture(int delta){
		if (isAnimated){
			animation.stepAnimation(delta);
		}
	}
	
	public void bindTexture(){
		if (isAnimated){
			animation.bindCurrentStage();
		}else{
			texture.bind();
		}
	}
}
