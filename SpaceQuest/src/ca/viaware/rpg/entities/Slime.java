package ca.viaware.rpg.entities;

import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

import ca.viaware.rpg.entity.MeleeEnemy;
import ca.viaware.rpg.utilities.TexturedQuad;

public class Slime extends MeleeEnemy {

	public Slime(double x, double y, double width, double height,int maxhealth, int maxdamage, int mindamage,
			int spawnx, int spawny,
			int agresiveness, double range, double speed) {
		super( x,  y,  width,  height,maxhealth, maxdamage, mindamage, spawnx, spawny, agresiveness,
				range, speed);
		// TODO Auto-generated constructor stub
		setT(new TexturedQuad(50,50,0,0,0,"res/sprites/enemies/slimemoving/1.png"));
	}
	
	
		public void draw() {
			getT().setlocation(getX(),getY());
			getT().update();
	}

}
