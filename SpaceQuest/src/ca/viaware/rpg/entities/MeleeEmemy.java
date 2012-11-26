package ca.viaware.rpg.entities;

import org.newdawn.slick.opengl.Texture;

public abstract class MeleeEmemy extends Enemy {

	private int agressiveness;
	private int x,y;
	public MeleeEmemy(int maxhealth, int damage, Texture[] left,
			Texture[] right, Texture[] up, Texture[] down,int spawnx,int spawny,int agreesiveness) {
		super(maxhealth, damage, left, right, up, down,spawnx,spawny);
		
		
		this.agressiveness =agreesiveness;
		this.x=spawnx;
		this.y=spawny;
		
		
		
		
		
		
		
		
		
		
	}
	public void update(){
		int playerx,playery,angle;
		
		
		
		
		
		
		
		
	}

}
