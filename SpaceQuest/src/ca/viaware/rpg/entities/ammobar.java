package ca.viaware.rpg.entities;

import org.lwjgl.opengl.Display;

import ca.viaware.rpg.utilities.TextRenderer;
import ca.viaware.rpg.utilities.TexturedQuad;



public class ammobar{
	
	
	TexturedQuad back;
	TextRenderer outof,slash,total; 
	public ammobar(){
		
		back = new TexturedQuad(65,65,Display.getWidth()-60,Display.getHeight()-120,180,"res/sprites/other/Ammo Icon.png");
		outof = new TextRenderer(5,60,120,0,"fadafa");
		
		
	}
	public void update(){
		back.update();
		outof.update();
	}
	
}