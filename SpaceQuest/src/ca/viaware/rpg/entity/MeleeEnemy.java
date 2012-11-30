package ca.viaware.rpg.entity;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.opengl.Texture;

import ca.viaware.rpg.game.Globals;
import ca.viaware.rpg.utilities.TexturedQuad;

public class MeleeEnemy extends Enemy {

	private TexturedQuad t;
	private int delta,agressiveness;
	private static double distancebetween, xdist, ydist, playerx, playery,
			Xoffset, Yoffset, range, actxdist, actydist, speed;

	public MeleeEnemy(double x, double y, double width, double height,
			int maxhealth, int maxdamage, int mindamage, int spawnx,
			int spawny, int agresiveness, double range, double speed) {
		super(x, y, width, height, maxhealth, maxdamage, mindamage, spawnx,
				spawny);

		MeleeEnemy.speed = speed / 100;
		MeleeEnemy.range = range;
		this.agressiveness = agresiveness;
		x = spawnx;
		y = spawny;

	}

	// it may seem like the enemy has a weird box for the Y value- but that is
	// because the sprite isn't centered
	public int getdelta(){
		return delta;
	}
	@Override
	public void update(int delta) {
		this.delta= delta;
		setXoffset((Globals.gameMap.getXOffset()));
		setYoffset((Globals.gameMap.getYOffset()));
		// MATH (YAY!!!!!!!!!!)
		playerx = Globals.playerEntity.getX();
		playery = Globals.playerEntity.getY();
		playerx = Globals.playerEntity.getActX() + 384;// additin is because
														// doesn't start at
														// center
		playery = Globals.playerEntity.getActY() + 256;

		xdist = playerx - x;
		actxdist = xdist;
		// if negative
		if (xdist < 0) {
			xdist *= -1;
		}
		ydist = playery - y;
		actydist = ydist;
		if (ydist < 1) {
			ydist *= -1;
		}

		distancebetween = Math.sqrt(((xdist * xdist) + (ydist * ydist)));// pythagorean
																			// theorem

		if (distancebetween > range) {
			x = moverx(actxdist, x, speed);
			y = moverx(actydist, y, speed);

		} else {// this means the mob is within range and will attack
			attack();
		}

		x = x + getXoffset();// this is for movement of player
		y = y + getYoffset();

	}

	public void reset() {
		x = x - getXoffset();// this is for movement of player
		y = y - getYoffset();
	}

	private static double moverx(double i, double x, double speed) {

		if (i > 0) {
			x = x + speed;

			if (i < range) {

				x = x - speed;
			}
		} else {
			if (i < 0) {
				x = x - speed;
				if (i > range) {
					x = x + speed;
				}
			}
		}

		return x;

	}

	private static void attack() {
		Random r = new Random();
		int damage;

	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub

	}

	public static double getXoffset() {
		return Xoffset;
	}

	public static void setXoffset(double xoffset) {
		Xoffset = xoffset;
	}

	public static double getYoffset() {
		return Yoffset;
	}

	public static void setYoffset(double yoffset) {
		Yoffset = yoffset;
	}

	public TexturedQuad getT() {
		return t;
	}

	public void setT(TexturedQuad t) {
		this.t = t;
	}

}
