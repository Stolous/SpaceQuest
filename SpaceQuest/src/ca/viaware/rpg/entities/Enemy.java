package ca.viaware.rpg.entities;

import org.newdawn.slick.opengl.Texture;

public abstract class Enemy {

	
	private int currenthealth,maxhealth,damage;
	private Texture[] left,right,up,down,attack;
	
	
	public Enemy(int maxhealth,int damage,Texture [] left,Texture [] right,Texture [] up,Texture [] down,int spawnx,int spawnz){
		this.left = left;
		this.right = right;
		this.up = up;
		this.down = down;
		this.currenthealth = this.maxhealth=maxhealth;
		this.damage=damage;
		//this is where spawn code goes
		
		
	}
	public void setmaxhealth(int maxhealth){
		this.maxhealth= maxhealth;
	}
	public void setcurrenthealth(int currenthealth){
		this.currenthealth= currenthealth;
	}
	public void setdamage(int damage){
		this.damage= damage;
	}
	public void takedamage(int damagetaken){
		this.currenthealth -=damagetaken;
		isalive();
	}
	public void isalive(){
		if(currenthealth<=0){
			
		}
	}
	
}
