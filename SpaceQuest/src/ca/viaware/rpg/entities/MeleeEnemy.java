package ca.viaware.rpg.entities;


import java.util.ArrayList;


import org.newdawn.slick.opengl.Texture;

import ca.viaware.rpg.game.Globals;
import ca.viaware.rpg.utilities.TextureHandler;
import ca.viaware.rpg.utilities.TexturedQuad;

public class MeleeEnemy extends Enemy {
	private static TexturedQuad t;
	Texture demo;
	private int agressiveness;
	private static double x,y,distancebetween,xdist,ydist,playerx,playery,Xoffset,Yoffset,range,actxdist,actydist,speed;
	public MeleeEnemy(int maxhealth, int damage, ArrayList<ArrayList<Texture>> sprites ,int spawnx,int spawny,int agresiveness,double range,double speed) {
		super(maxhealth, damage, sprites, spawnx,spawny);
		
		
		
		
		sprites = new ArrayList<ArrayList<Texture>>();

			ArrayList<Texture> sprite = new ArrayList<Texture>();
			sprite.add(0,demo);
			sprites.add(0,sprite);
		
		MeleeEnemy.speed=speed/100;
		MeleeEnemy.range=range;
		this.agressiveness =agresiveness;
		MeleeEnemy.x=spawnx;
		MeleeEnemy.y=spawny;
		
		
		
		
		t = new TexturedQuad(50,50,0,0,0,"res/sprites/enemies/slimemoving/1.png");
		
		
		
		
		
		
		
		
	}
	//it may seem like the enemy has a weird box for the Y value- but that is because the sprite isn't centered
	public static void Update(){
		Xoffset= (Globals.gameMap.getXOffset());
		Yoffset = (Globals.gameMap.getYOffset());
		//MATH (YAY!!!!!!!!!!)
		playerx = Globals.playerEntity.getX();
		playery = Globals.playerEntity.getY();
		playerx = Globals.playerEntity.getActX()+384;//additin is because doesn't start at center
		playery = Globals.playerEntity.getActY()+256;
		
		
		
		System.out.println("Px"+ playerx);
		System.out.println("Px"+ playerx);
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
		
		
		if(distancebetween>range){
			x=moverx(actxdist,x,speed);
			y=moverx(actydist,y,speed);
			
		}
		
		
		

		x=x + Xoffset;//this is for movement of player
		y=y + Yoffset;
		t.setlocation(x, y);
		t.update();
		x=x-Xoffset;//resets so it doesn't compound
		y=y-Yoffset;
	}

	private static double moverx(double i,double x,double speed){


		if(i>0){
			x = x+speed;
			
			if(i<range){
				
				x=x-speed;
			}}else{
		if(i<0){
			x=x-speed;
			if(i>range){
				x= x+speed;
			}
		}}
		
		return x;
	
		
	}

	

}
