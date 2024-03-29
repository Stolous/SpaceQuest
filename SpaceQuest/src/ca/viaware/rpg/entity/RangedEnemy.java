package ca.viaware.rpg.entity;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.opengl.Texture;
import ca.viaware.rpg.entities.Player;
import ca.viaware.rpg.entity.Bullet.targetType;
import ca.viaware.rpg.game.Globals;
import ca.viaware.rpg.utilities.TextureHandler;
import ca.viaware.rpg.utilities.TexturedQuad;

public class RangedEnemy extends Enemy {

	@SuppressWarnings("unused")
	private boolean movedOnce = false;

	private TexturedQuad t;
	@SuppressWarnings("unused")
	private int delta, aggresiveness, betattacks, attackspeed, blockx, blocky;
	@SuppressWarnings("unused")
	private double distancebetween, xdist, ydist, playerx, playery, range, actxdist, actydist, speed, sightrange, xspeed, yspeed, BulletSpeed, optrange, mxc, myc, xoff, yoff;
	private int mindamage, maxdamage, accuracy;
	private Texture Bullet;

	public RangedEnemy(double width, double height, int maxhealth, int maxdamage, int mindamage, int spawnx, int spawny, int aggresiveness, double range, double speed, int attackspeed, double optdist, double sightrange, String path, double BulletSpeed, double xoff, double yoff, int accuracy) {
		super(width, height, maxhealth, maxdamage, mindamage, spawnx, spawny);
		this.mindamage = mindamage;
		this.maxdamage = maxdamage;
		TextureHandler t = new TextureHandler();
		this.Bullet = t.loadSprite(path);
		this.attackspeed = attackspeed;
		this.speed = speed / 100;
		this.range = range;
		this.aggresiveness = aggresiveness;
		mx = spawnx;
		my = spawny;
		x = (mx);
		y = (my);
		this.sightrange = sightrange;
		this.accuracy = accuracy;
		this.xoff = xoff;
		this.yoff = yoff;
		this.BulletSpeed = BulletSpeed;

		// this way the initial attack will be faster
		this.optrange = optdist;
		betattacks = 0;
	}

	private void attack() {

		Random r = new Random();
		int accchange = (r.nextInt(accuracy));

		// so that it is inaccurate both positive and negative

		// System.out.println("change"+accchange);

		Double newx = Globals.playerEntity.getActX() + accchange;
		accchange = (r.nextInt(accuracy));
		Double newy = Globals.playerEntity.getActY() + accchange;
		// System.out.println("newx"+(newx));
		// System.out.println("newy"+(newy));

		if (betattacks >= attackspeed) {

			Globals.bullets.add(new Bullet(this.Bullet, x - xoff, newx, y - yoff, newy, BulletSpeed, mindamage, maxdamage, targetType.PLAYER));
			betattacks = 0;

		}

	}

	// it may seem like the enemy has a weird box for the my value- but that is
	// because the sprite isn't centered
	public int getdelta() {
		return delta;
	}

	@Override
	public void update(int delta) {

		mxc = 0;
		myc = 0;
		if (delta >= 0) {
			betattacks += delta;
		}

		blockx = (int) (mx / 64);
		blocky = (int) (my / 64);

		setX(getT().getx());
		setY(getT().gety());

		this.delta = delta;
		setXoffset((Globals.gameMap.getXOffset()));
		setYoffset((Globals.gameMap.getYOffset()));
		// MATH (YAY!!!!!!!!!!)
		playerx = Globals.playerEntity.getActX();
		playery = Globals.playerEntity.getActY();

		xdist = playerx + (Player.getW() / 2) - mx;

		actxdist = xdist;
		// if negative
		if (xdist < 0) {
			xdist *= -1;
		}
		ydist = playery + (Player.getH() / 2) - my;
		actydist = ydist;
		if (ydist < 0) {
			ydist *= -1;
		}

		distancebetween = Math.sqrt(((xdist * xdist) + (ydist * ydist)));// pythagorean
																			// theorem

		if (distancebetween < sightrange) {

			mx = moverx(actxdist, mx, speed, xdist, true);
			my = moverx(actydist, my, speed, ydist, false);
			attack();
		}

		mx = mx + getXoffset();// this is for movement of player
		my = my + getYoffset();

	}

	private double moverx(double i, double mx, double speed, double dist, boolean b) {
		dist = dist / 100;
		double change = 0;

		if (i > 0) {
			change = speed * dist;

			if (i < range) {

				change = -speed * dist;
			}
		} else {
			if (i < 0) {
				change = -speed * dist;
				if (i > range) {
					change = speed * dist;

				}
			}
		}
		if (change > speed) {
			change = speed;
		}

		if (change > 0) {
			change += speed;
		}

		mx += change;
		return mx;

	}

	protected double getmx() {
		return mx;
	}

	protected double getmy() {
		return my;
	}

	public TexturedQuad getT() {
		return t;
	}

	public void setT(TexturedQuad t) {
		this.t = t;
	}

	@Override
	public void draw() {

	}

}
