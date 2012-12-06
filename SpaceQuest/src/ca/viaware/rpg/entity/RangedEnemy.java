package ca.viaware.rpg.entity;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import ca.viaware.rpg.entities.Player;
import ca.viaware.rpg.game.Globals;
import ca.viaware.rpg.utilities.Bullet;
import ca.viaware.rpg.utilities.TexturedQuad;

public class RangedEnemy extends Enemy {

	private TexturedQuad t;
	private boolean b =false;
	private int delta, agressiveness, betattacks, attackspeed,mindamage,maxdamage;
	private static double distancebetween, xdist, ydist, playerx, playery, range, actxdist, actydist, speed, mx, my,mindist,sightrange,bulletSpeed;
	public ArrayList <Bullet> bullets = new ArrayList<Bullet>();
	private Texture Bullet;
	
	public RangedEnemy(double mx, double my, double width, double height, int maxhealth, int maxdamage, int mindamage, int spawnx, int spawny, int agresiveness, double range, double speed, int attackspeed,double mindist,double sightrange,Texture bullet,double BulletSpeed) {
		super(width, height, maxhealth, maxdamage, mindamage, spawnx, spawny);
		this.bulletSpeed = BulletSpeed;
		this.Bullet = bullet;
		this.attackspeed = attackspeed;
		this.speed = speed / 100;
		this.range = range;
		this.mindist=mindist;
		this.agressiveness = agresiveness;
		this.sightrange = sightrange;
		this.mindamage = mindamage;
		this.maxdamage = maxdamage;
		mx = spawnx;
		my = spawny;
		x = (mx);
		y = (my);		
	}


	public void update(){
		
		b=false;
		setX(getT().getx());
		setY(getT().gety());

		this.delta = delta;
		setXoffset((Globals.gameMap.getXOffset()));
		setYoffset((Globals.gameMap.getYOffset()));
		// MATH (YAY!!!!!!!!!!)
		playerx = Globals.playerEntity.getX();
		playery = Globals.playerEntity.getY();
		playerx = Globals.playerEntity.getActX() + 384;// additin is because
														// doesn't start at
														// center
		playery = Globals.playerEntity.getActY() + 256;

		xdist = playerx + (Player.getW() / 2) - mx;

		actxdist = xdist;
		// if negative
		if (xdist < 0) {
			xdist *= -1;
		}
		ydist = playery + (Player.getH() / 2) - my;
		actydist = ydist;
		if (ydist < 1) {
			ydist *= -1;
		}

		distancebetween = Math.sqrt(((xdist * xdist) + (ydist * ydist)));// pythagorean
																			// theorem
		if(distancebetween<sightrange){
		if(distancebetween<mindist){
			run();
		}
		if (distancebetween > range) {
			mx = moverx(actxdist, mx, speed);
			my = moverx(actydist, my, speed);

		} else {// this means the mob is within range and will attack
			b=true;
			attack();
		}

		if (this.intersects(Globals.playerEntity)&&b==false) {
			attack();
		}}

		mx = mx + getXoffset();// this is for movement of player
		my = my + getYoffset();
		for(int i =0; i<bullets.size();i++){
		bullets.get(i).update();
		}
	}
	
private void attack(){
	bullets.add(new Bullet(this.Bullet,getX(),(double)Mouse.getDX(),getY(),(double)Mouse.getDY(),bulletSpeed,mindamage,maxdamage));	
}
	@Override
	public void draw() {
		
		for(int i =0; i<bullets.size();i++){
			
			bullets.get(i).render();
			
		}
		for(int i =0; i<bullets.size();i++){			
		if(bullets.get(i).getremoved() == true){
			bullets.remove(i);
		}}
	}
	private void run(){
		mx++;
		my++;
	}
	
	private static double moverx(double i, double mx, double speed) {

		if (i > 0) {
			mx = mx + speed;

			if (i < range) {

				mx = mx - speed;
			}
		} else {
			if (i < 0) {
				mx = mx - speed;
				if (i > range) {
					mx = mx + speed;
				}
			}
		}

		return mx;

	}
	

	public TexturedQuad getT() {
		return t;
	}

	public void setT(TexturedQuad t) {
		this.t = t;
	}
	
	
}
