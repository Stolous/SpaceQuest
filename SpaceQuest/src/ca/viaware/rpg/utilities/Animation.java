package ca.viaware.rpg.utilities;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.opengl.Texture;

public class Animation {

	List<Texture> animList = new ArrayList<Texture>(16);
	int animSpeed = 0;
	int currentStage = 0;
	int timer = 0;
	
	public Animation(List<Texture> anim, int fps){
	animList = anim;	
	animSpeed = 1000 / fps;
	}
	
	public void stepAnimation(int delta){
		timer += delta;
		
		if (timer >= animSpeed){
			timer = 0;
			currentStage++;
			
			if (currentStage > animList.size() - 1){
				currentStage = 0;
			}
		}
	}
	
	public void bindCurrentStage(){
		animList.get(currentStage).bind();
	}
}
