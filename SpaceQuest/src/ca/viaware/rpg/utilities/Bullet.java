package ca.viaware.rpg.utilities;

import java.util.Random;

import org.newdawn.slick.opengl.Texture;

import ca.viaware.rpg.game.Globals;
import ca.viaware.rpg.map.Tile;

public class Bullet {
	private Texture t;
	private boolean removed = false;
	Class c;
	TexturedQuad b;
	private int mindamage, maxdamage;
	private double oldX,newX,newY,oldY,xSpeed,ySpeed,sx,sy;
	public Bullet(Texture t,Double oldX,Double newX,double oldY,double newY,double bulletSpeed,int mind ,int maxd){
		this.mindamage = mind;
		this.maxdamage = maxd;
		this.c = c;
		sx =oldX;
		sy=oldY;
		b = new TexturedQuad(t.getTextureHeight(),t.getTextureWidth(),sx,sy,t);
		
		this.t=t;
		this.oldX =oldX;
		this.newX = newX;
		this.oldY =oldY;
		this.newY =newY;
		float ySpeed = 0;
		float xSpeed = 0;


		// Maths to make bullet go in direction thing
		xSpeed = (float) (newX - oldX);
		ySpeed = (float) (newY - oldY);

		float factor = (float) (bulletSpeed / Math.sqrt(xSpeed * xSpeed + ySpeed * ySpeed));

		xSpeed = xSpeed * factor;
		ySpeed = ySpeed * factor;

		this.ySpeed=ySpeed;
		this.xSpeed = xSpeed;
		update();
		
		
	}
	public void update(){
		sx = sx+xSpeed;
		sy=sy+ySpeed;	
		if(sx == Globals.playerEntity.getX()&&sy == Globals.playerEntity.getY()){
			contact();
		}



			
			
				
	}
	private void contact(){
		
		
		Random r = new Random();
		int damage = r.nextInt(maxdamage-mindamage);
		damage+=mindamage;
		Globals.playerEntity.takedamage(damage);
		removed = true;
		
		

	}
	public boolean getremoved(){
		return removed;
	}
	
	public void render(){
		b.setlocation(sx, sy);
		b.update();
	}

}
