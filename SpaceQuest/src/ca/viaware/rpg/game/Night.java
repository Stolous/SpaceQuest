package ca.viaware.rpg.game;

import org.lwjgl.opengl.Display;

import ca.viaware.rpg.entity.AbstractEntity;
import ca.viaware.rpg.utilities.TexturedQuad;

public class Night extends AbstractEntity {
	private boolean stage = true;
	private double alpha=0;
	private TexturedQuad night;

	public Night() {
		super(Display.getWidth()/2, Display.getHeight()/2, Display.getWidth(), Display.getHeight());
		night = new TexturedQuad(Display.getWidth(),Display.getWidth(),Display.getWidth()/2, Display.getHeight()/2,0,"res/img/Night.png");
	}

	@Override
	public void draw() {
		night.update();
	}

	@Override
	public void update(int delta) {
		night.settrans(alpha);
	}

	public void setNight(int nightLevel){
		alpha = nightLevel;
	}
	public void increaseNight(){
		alpha++;
	}
	public void decreaseNight(){
		alpha--;
	}
	public void nightProgress(){
		if(alpha>1){
			stage =false;
		}else if (alpha < 0){
			stage = true;
		}
		if(stage){
			alpha+= 0.01;
		}else{
			alpha-=0.01;
		}
	}
}
