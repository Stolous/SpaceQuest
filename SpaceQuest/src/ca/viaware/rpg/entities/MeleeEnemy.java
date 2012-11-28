package ca.viaware.rpg.entities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import ca.viaware.rpg.game.GameLoop;
import ca.viaware.rpg.game.Globals;
import ca.viaware.rpg.utilities.TextureHandler;
import ca.viaware.rpg.utilities.TexturedQuad;

public class MeleeEnemy extends Enemy {
	private static TexturedQuad t;
	Texture demo;
	private int agressiveness;
	private static double x,y,distancebetween,xdist,ydist,playerx,playery,xmov,ymov,range,actxdist,actydist,speed;
	public MeleeEnemy(int maxhealth, int damage, ArrayList<ArrayList<Texture>> sprites ,int spawnx,int spawny,int agresiveness,double range,double speed) {
		super(maxhealth, damage, sprites, spawnx,spawny);
		
		
		TextureHandler r=new TextureHandler();
		
		sprites = new ArrayList<ArrayList<Texture>>();

			ArrayList<Texture> sprite = new ArrayList<Texture>();
			sprite.add(0,demo);
			sprites.add(0,sprite);
		
		this.speed=speed/100;
		this.range=range;
		this.agressiveness =agresiveness;
		this.x=spawnx;
		this.y=spawny;
		//t.setlocation(x, y);
		ArrayList<Texture> spritesforward=sprites.get(0);
		
		
		t = new TexturedQuad(50,50,0,0,0,"res/sprites/enemies/slimemoving/1.png");
		
		
		
		
		
		
		
		
	}
	
	public static void Update(int delta){
		
		//MATH (YAY!!!!!!!!!!)
		playerx = Globals.playerEntity.getActX();
		playery = Globals.playerEntity.getActY();
		playerx = Globals.playerEntity.getX();
		playery = Globals.playerEntity.getY();
		
		
		x = x - Globals.playerEntity.getChangeX();
		y = y - Globals.playerEntity.getChangeY();
		
		
		xdist= playerx - x;
		actxdist=xdist;
		//if  negative
		if(xdist<0){
			xdist*=-1;
		}
		ydist = playery - y;
		actydist= ydist;
		if(ydist<1){
			ydist*=-1;
		}
		
	

		distancebetween =Math.sqrt(((xdist*xdist)+(ydist*ydist)));//pythagorean theorem
		
		
		if(distancebetween>0){
			x=moverx(actxdist,x,speed);
			y=moverx(actydist,y,speed);
			
		}
		
		
		
		
		
		t.setlocation(x, y);
		t.update();
	

	}

	private static double moverx(double i,double x,double speed){


		if(i>0){
			x = x+speed;
			
			if(i<0){
				
				x=0;
			}}else{
		if(i<0){
			x=x-speed;
			if(i>0){
				x= 0;
			}
		}}
		
		return x;
	
		
	}

}
