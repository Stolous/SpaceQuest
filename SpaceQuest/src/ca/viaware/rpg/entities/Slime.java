package ca.viaware.rpg.entities;

import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

import ca.viaware.rpg.entity.MeleeEnemy;
import ca.viaware.rpg.utilities.TextureHandler;
import ca.viaware.rpg.utilities.TexturedQuad;

public class Slime extends MeleeEnemy {

	private Texture [] textures=new Texture[17];
	private int index =1;
	private int slower = 0;
	public Slime(double x, double y, double width, double height,int maxhealth, int maxdamage, int mindamage,
			int spawnx, int spawny,
			int agresiveness, double range, double speed) {
		super( x,  y,  width,  height,maxhealth, maxdamage, mindamage, spawnx, spawny, agresiveness,
				range, speed);
		// TODO Auto-generated constructor stub
		setT(new TexturedQuad(50,50,0,0,0,"res/sprites/enemies/slimemoving/1.png"));
										   
		String s = "enemies/slimemoving/";
		TextureHandler t = new TextureHandler();
		for(int count=1; count<18;count++ ){	
			textures[count-1]= t.loadSprite(s+count);
		}
	}
	
	
	
	
		public void draw() {
			if (slower == 5){
				slower=0;
				getT().changetexture(textures[index]);
				index++;
			}else{
				slower++;
			}
			
				
			
			
			
			
			getT().setlocation(getX(),getY());
			getT().update();
			if(!(index== 18)){//so the index doesn't go ver nbr of sprites
				
			}else{
			index = 0;
	}
			
			}

}
