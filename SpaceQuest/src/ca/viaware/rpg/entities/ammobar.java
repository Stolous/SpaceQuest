package ca.viaware.rpg.entities;

import org.lwjgl.opengl.Display;

import ca.viaware.rpg.utilities.TextRenderer;
import ca.viaware.rpg.utilities.TexturedQuad;



public class ammobar{
	
	
	TexturedQuad back;
	TextRenderer r,slash,total; 
	public ammobar(){
		
		back = new TexturedQuad(65,65,Display.getWidth()-60,Display.getHeight()-120,180,"res/sprites/other/Ammo Icon.png");
		r = new TextRenderer(50,100, 100, 0, "res/text/fonts.png", "Seth is jealous of my text rendering abilities");
		
		
	}
	public void update(){
		r.setlocation(60, 120);
		//r.update();
		back.update();
		
	}
	
}