package ca.viaware.rpg.entities;

import org.lwjgl.opengl.Display;

import ca.viaware.rpg.utilities.TexturedQuad;
import ca.viaware.rpg.utilities.nontexdquad;


public class HealthBar {
	private int health;
	TexturedQuad barf;
	nontexdquad barb;
	
	public HealthBar(){
		barf =new TexturedQuad(Display.getWidth(),(Display.getHeight()/10),Display.getWidth()/2,Display.getHeight()-Display.getHeight()/20,0,"res/sprites/other/healthbar.png");
		barb = new nontexdquad(Display.getWidth(),(Display.getHeight()/10),Display.getWidth()/2,Display.getHeight()-Display.getHeight()/10,0);
		
		
		
	}

	public void update(){
		barb.update();
		barf.update();
	}
}
