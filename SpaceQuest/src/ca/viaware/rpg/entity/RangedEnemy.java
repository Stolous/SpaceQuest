package ca.viaware.rpg.entity;

import ca.viaware.rpg.entities.Player;
import ca.viaware.rpg.game.Globals;
import ca.viaware.rpg.utilities.TexturedQuad;

public class RangedEnemy extends Enemy {

	private boolean b =false;
	private TexturedQuad t;
	private int delta, agressiveness, betattacks, attackspeed;
	private static double distancebetween, xdist, ydist, playerx, playery, range, actxdist, actydist, speed, mx, my,mindist,sightrange;
	
	public RangedEnemy(double mx, double my, double width, double height, int maxhealth, int maxdamage, int mindamage, int spawnx, int spawny, int agresiveness, double range, double speed, int attackspeed,double mindist,double sightrange) {
		super(width, height, maxhealth, maxdamage, mindamage, spawnx, spawny);
		this.attackspeed = attackspeed;
		this.speed = speed / 100;
		this.range = range;
		this.mindist=mindist;
		this.agressiveness = agresiveness;
		this.sightrange = sightrange;
		mx = spawnx;
		my = spawny;
		x = (mx);
		y = (my);		
	}

//	float ySpeed = 0;
//	float xSpeed = 0;
//
//	double speed = 2;
//
//	// Maths to make bullet go in direction thing
//	xSpeed = (float) (bullet.gotoX - bullet.oldPlayerX);
//	ySpeed = (float) (bullet.gotoY - bullet.oldPlayerY);
//
//	float factor = (float) (speed / Math.sqrt(xSpeed * xSpeed + ySpeed * ySpeed));
//
//	xSpeed = xSpeed * factor;
//	ySpeed = ySpeed * factor;
//
//	bullet.setDX(xSpeed);
//	bullet.setDY(ySpeed);
//
//	bullet.update(delta);
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

	}
	
private void attack(){
	
}
	@Override
	public void draw() {
		
		
		
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
