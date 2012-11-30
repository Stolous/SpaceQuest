package ca.viaware.rpg.entity;

import java.awt.Rectangle;




public abstract class Enemy extends AbstractMoveableEntity {
	protected Rectangle hitbox = new Rectangle();
	private int currenthealth, maxhealth, maxdamage, mindamage;

	public Enemy(double x, double y, double width, double height,
			int maxhealth, int maxdamage, int mindamage, int spawnx, int spawnz ) {
		super(x, y, width, height);
		// max/min damage is so there is a range
		this.x = x;
		this.y = y;
		this.width = -width;
		this.height = height;
		this.currenthealth = this.maxhealth = maxhealth;
		this.setMaxdamage(maxdamage);
		this.setMindamage(mindamage);
		// this is where spawn code goes

	}

	public void setmaxhealth(int maxhealth) {
		this.maxhealth = maxhealth;
	}

	public void setcurrenthealth(int currenthealth) {
		this.currenthealth = currenthealth;
	}

	public void setdamage(int mindamage, int maxdamage) {
		this.setMindamage(mindamage);
		this.setMaxdamage(maxdamage);
	}

	public void takedamage(int damagetaken) {
		this.currenthealth -= damagetaken;
		isalive();
		
	}

	public void isalive() {
		if (currenthealth <= 0) {

		} else {
			death();
		}
	}

	public void death() {

	}

	public int getMaxdamage() {
		return maxdamage;
	}

	public void setMaxdamage(int maxdamage) {
		this.maxdamage = maxdamage;
	}

	public int getMindamage() {
		return mindamage;
	}

	public void setMindamage(int mindamage) {
		this.mindamage = mindamage;
	}

}
