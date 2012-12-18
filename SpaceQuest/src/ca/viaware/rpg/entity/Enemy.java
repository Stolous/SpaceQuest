package ca.viaware.rpg.entity;

import java.awt.Rectangle;

public abstract class Enemy extends AbstractMoveableEntity {
	protected Rectangle hitbox = new Rectangle();
	private int currenthealth, maxhealth, maxdamage, mindamage;
	protected double mx, my, Xoffset, Yoffset;
	private EnemyBar b;

	public Enemy(double width, double height, int maxhealth, int maxdamage, int mindamage, int spawnx, int spawny) {
		super(spawnx, spawny, width, height);
		// max/min damage is so there is a range
		this.x = spawnx;
		this.y = spawny;
		this.width = width;
		this.height = height;
		this.currenthealth = this.maxhealth = maxhealth;
		this.setMaxdamage(maxdamage);
		this.setMindamage(mindamage);
		setB(new EnemyBar(x, y, width, height, this, 1, new Effect[3]));
		// this is where spawn code goes

	}

	public void reset() {
		mx = mx - getXoffset();// this is for movement of player
		my = my - getYoffset();
	}

	public double getXoffset() {
		return Xoffset;
	}

	public void setXoffset(double xoffset) {
		Xoffset = xoffset;
	}

	public double getYoffset() {
		return Yoffset;
	}

	public void setYoffset(double yoffset) {
		Yoffset = yoffset;
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

	public EnemyBar getB() {
		return b;
	}

	public void setB(EnemyBar b) {
		this.b = b;
	}

}
