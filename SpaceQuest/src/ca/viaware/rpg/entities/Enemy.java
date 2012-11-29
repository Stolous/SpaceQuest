package ca.viaware.rpg.entities;

import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

public abstract class Enemy {

	
	private int currenthealth,maxhealth,maxdamage,mindamage;
	private ArrayList<ArrayList<Texture>> sprites;
	
	
	public Enemy(int maxhealth,int maxdamage,int mindamage,ArrayList<ArrayList<Texture>> sprites,int spawnx,int spawnz){
		
		//max/min damage is so there is a range 
		
		this.currenthealth = this.maxhealth=maxhealth;
		this.maxdamage=maxdamage;
		this.mindamage=mindamage;
		//this is where spawn code goes
		this.sprites = sprites;
		
	}
	
	public void setmaxhealth(int maxhealth){
		this.maxhealth= maxhealth;
	}
	public void setcurrenthealth(int currenthealth){
		this.currenthealth= currenthealth;
	}
	public void setdamage(int mindamage,int maxdamage){
		this.mindamage= mindamage;
		this.maxdamage= maxdamage;
	}
	public void takedamage(int damagetaken){
		this.currenthealth -=damagetaken;
		isalive();
	}
	public void isalive(){
		if(currenthealth<=0){
			
		}else{
			death();
		}
	}
public void death(){
	
}
	
}
