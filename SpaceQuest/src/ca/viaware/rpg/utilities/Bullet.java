package ca.viaware.rpg.utilities;

import org.newdawn.slick.opengl.Texture;

public class Bullet {
	private Texture t;
	TexturedQuad b;
	private double oldX,newX,newY,oldY,xSpeed,ySpeed,sx,sy;
	public Bullet(Texture t,Double oldX,Double newX,double oldY,double newY,double bulletSpeed){
		
		
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
		
		
	}
	public void render(){
		b.setlocation(sx, sy);
		b.update();
	}

}
