package ca.viaware.rpg.entity;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.opengl.Texture;

import ca.viaware.rpg.entities.Player;
import ca.viaware.rpg.game.Globals;
import ca.viaware.rpg.utilities.TexturedQuad;

public class MeleeEnemy extends Enemy {

	private TexturedQuad t;
	private int delta, agressiveness, betattacks, attackspeed;
	private static double distancebetween, xdist, ydist, playerx, playery, Xoffset, Yoffset, range, actxdist, actydist, speed, mx, my;

	public MeleeEnemy(double mx, double my, double width, double height, int maxhealth, int maxdamage, int mindamage, int spawnx, int spawny, int agresiveness, double range, double speed, int attackspeed) {
		super(mx, my, width, height, maxhealth, maxdamage, mindamage, spawnx, spawny);
		this.attackspeed = attackspeed;
		MeleeEnemy.speed = speed / 100;
		MeleeEnemy.range = range;
		this.agressiveness = agresiveness;
		mx = spawnx;
		my = spawny;
		x = (mx);
		y = (my);
		betattacks = attackspeed / 2;// this way the initial attack will be
										// faster

	}

	// it may seem like the enemy has a weird box for the my value- but that is
	// because the sprite isn't centered
	public int getdelta() {
		return delta;
	}

	@Override
	public void update(int delta) {

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

		if (distancebetween > range) {
			mx = moverx(actxdist, mx, speed);
			my = moverx(actydist, my, speed);

		} else {// this means the mob is within range and will attack
			attack();
		}

		if (this.intersects(Globals.playerEntity)) {
			System.out.println("Collision detected");
		}

		mx = mx + getXoffset();// this is for movement of player
		my = my + getYoffset();

	}

	public void reset() {
		mx = mx - getXoffset();// this is for movement of player
		my = my - getYoffset();
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

	protected double getmx() {
		return mx;
	}

	protected double getmy() {
		return my;
	}

	private void attack() {
		if (betattacks >= attackspeed) {
			Random r = new Random();
			int damage;
			damage = r.nextInt((getMaxdamage() - getMindamage())) + getMindamage();
			Globals.playerEntity.takedamage(damage);
			betattacks = 0;
		}
		betattacks++;
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
