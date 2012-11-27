package ca.viaware.rpg.entities;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import ca.viaware.rpg.game.Globals;
import ca.viaware.rpg.utilities.texdquad;

public class MeleeEnemy extends Enemy {
	//private static texdquad t = new texdquad(50,50,0,0,0,);
	private int agressiveness;
	private static double x,y,distancebetween,xdist,ydist,playerx,playery,xmov,ymov,range,actxdist,actydist,speed;
	public MeleeEnemy(int maxhealth, int damage, Texture[][] sprites,int spawnx,int spawny,int agresiveness,double range,double speed) {
		super(maxhealth, damage, sprites,spawnx,spawny);
		
		this.speed=speed/100;
		this.range=range;
		this.agressiveness =agresiveness;
		this.x=spawnx;
		this.y=spawny;
		//t.setlocation(x, y);
		
		
		
		
		
		
		
		
		
	}
	public static void Update(){
		
		//MATH (YAY!!!!!!!!!!)
		playerx = Globals.playerEntity.getActX();
		playery = Globals.playerEntity.getActY();
		System.out.println(playerx);
		System.out.println(playery);
		
		
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
		
		System.out.println("tot"+ distancebetween);
		System.out.println("x"+ x);
		System.out.println("y"+ y);
		distancebetween =Math.sqrt(((xdist*xdist)+(ydist*ydist)));//pythagorean theorem
		
		
		if(distancebetween>0){
			System.out.println("act"+actxdist);
			x=moverx(actxdist,x,speed);
			y=moverx(actydist,y,speed);
			
		}
		
		
		
		
		
		//t.setlocation(x, y);
		//t.update();
	

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
