package ca.viaware.rpg.entities;

import org.lwjgl.opengl.Display;

import ca.viaware.rpg.utilities.TextRenderer;
import ca.viaware.rpg.utilities.TexturedQuad;



public class ammobar{
	TextRenderer tRender = new TextRenderer(30,0,"res/text/fonts.png");
	
	TexturedQuad back;
	
	public ammobar(){
		
		back = new TexturedQuad(65,65,Display.getWidth()-60,Display.getHeight()-120,180,"res/sprites/other/Ammo Icon.png");
		
		
		
	}
	public void update(){
		//r.update();
		back.update();
	}
	
}