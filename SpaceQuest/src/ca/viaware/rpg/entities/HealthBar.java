package ca.viaware.rpg.entities;

import org.lwjgl.opengl.Display;

import ca.viaware.rpg.utilities.TexturedQuad;
import ca.viaware.rpg.utilities.NoTexQuad;


public class HealthBar {
	private int tot,rat;
	private double r,g,b;
	TexturedQuad barf;
	NoTexQuad barb;
	
	public HealthBar(){
		barf =new TexturedQuad(Display.getWidth(),(Display.getHeight()/10),Display.getWidth()/2,Display.getHeight()-Display.getHeight()/20,0,"res/sprites/other/healthbar.png");
		barb = new NoTexQuad((int) (Display.getWidth()/1.21),(int)(Display.getHeight()/40),Display.getWidth()/2,Display.getHeight()-(int)(Display.getHeight()/54.3),0);//this may seem weird- but it is  so that if you change the display size it will stay the same
		
		tot= (int) (Display.getWidth()/1.21);
		rat = tot/100;
		barb.setcol(0, 0.51, 0);
		
				
	}

	public void update(){
		barb.setcol(r,g,b);
		barf.update();
		barb.update();
	}
	public void change(int percent){
		percent = percent *rat;
		barb.resize(percent, 0);
	}
}
