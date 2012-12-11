package ca.viaware.rpg.utilities;

import java.util.Random;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;

import ca.viaware.rpg.game.Globals;
import ca.viaware.rpg.map.Tile;

public class Bullet {
	private Texture t;
	private boolean removed = false;
	Class c;
	TexturedQuad b;
	private int mindamage, maxdamage;
	private double oldX,newX,newY,oldY,xSpeed,ySpeed,sx,sy,XOffset,YOffset;
	public Bullet(Texture t,Double oldX,Double newX,double oldY,double newY,double bulletSpeed,int mind ,int maxd){
		this.mindamage = mind;
		this.maxdamage = maxd;
		this.c = c;
		this.t=t;
		XOffset= ((Globals.gameMap.getXOffset()));
		YOffset=((Globals.gameMap.getYOffset()));
		sx =oldX-XOffset;
		sy=oldY-YOffset;
		bulletSpeed = bulletSpeed/100;
		
		b = new TexturedQuad(50,50,sx,sy,this.t);
		
		this.t=t;
		this.oldX =oldX;
		this.newX = newX;
		this.oldY =oldY;
		this.newY =newY;
		double ySpeed = 0;
		double xSpeed = 0;

		System.out.println("Bullet speed is "+ bulletSpeed);
		// Maths to make bullet go in direction thing
		xSpeed = (float) (newX - oldX);
		ySpeed = (float) (newY - oldY);
		System.out.println("Oldx"+oldX);
		System.out.println("Newx"+newX);
		double factor = (double) (((xSpeed * xSpeed) + (ySpeed * ySpeed)));
		System.out.println("Factor"+factor);
		factor = Math.sqrt(factor);
		System.out.println("Factor"+factor);
		factor = bulletSpeed/factor;
		System.out.println("Factor"+factor);
		xSpeed = xSpeed * factor;
		ySpeed = ySpeed * factor;
		System.out.println("NewX"+newX);
		System.out.println("Xspeed"+xSpeed);
		this.ySpeed=ySpeed;
		this.xSpeed = xSpeed;
		
		update();
		
		
	}
	public void update(){
		System.out.println("SX is"+sx);
		
	int blockx = (int) (sx/64);
	int blocky = (int) (sy/64);
		sx = sx+xSpeed;
		sy=sy+ySpeed;	
		if(sx == Globals.playerEntity.getX()&&sy == Globals.playerEntity.getY()){
			contact();
		}else{
			int i =0;
			for (Tile[] tile1 : Globals.gameMap.mapTiles) {
				
				
					if(blockx==tile1[i].getBX()){
						if(blocky==tile1[i].getBY()){
							//then it means that it is in this tile
							if(Globals.gameMap.mapTiles[blockx][blocky].hasCollision()){					
								//block on right of mob has collision
								System.out.println("Block has collision");
							}
						}
						}
				
		}
		}
		XOffset= ((Globals.gameMap.getXOffset()));
		YOffset=((Globals.gameMap.getYOffset()));
		sx = sx + XOffset;// this is for movement of player
		sy = sy +	YOffset;
		b.setlocation(sx, sy);
			
			
				
	}
	public void reset() {
		sx = sx - XOffset;// this is for movement of player
		sy = sy - YOffset;
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
		b.update();
	}

}
