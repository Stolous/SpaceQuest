package ca.viaware.rpg.entity;

import java.util.ArrayList;
import org.newdawn.slick.opengl.Texture;
import ca.viaware.rpg.entities.Player;
import ca.viaware.rpg.game.Globals;
import ca.viaware.rpg.utilities.TextureHandler;
import ca.viaware.rpg.utilities.TexturedQuad;

public class RangedEnemy extends Enemy {

	private boolean movedOnce = false;

	private TexturedQuad t;
	private int delta, aggresiveness, betattacks, attackspeed, blockx, blocky;
	private double distancebetween, xdist, ydist, playerx, playery, range, actxdist, actydist, speed, sightrange, xspeed, yspeed, BulletSpeed, optrange, mxc, myc, xoff, yoff;
	private int mindamage, maxdamage;
	private Texture Bullet;
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();

	public RangedEnemy(double width, double height, int maxhealth, int maxdamage, int mindamage, int spawnx, int spawny, int aggresiveness, double range, double speed, int attackspeed, double optdist, double sightrange, String path, double BulletSpeed, double xoff, double yoff) {
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

		this.xoff = xoff;
		this.yoff = yoff;
		this.BulletSpeed = BulletSpeed;

		// this way the initial attack will be faster
		this.optrange = optdist;
		betattacks = 0;
	}

	private void attack() {

		if (betattacks >= attackspeed) {
			bullets.add(new Bullet(this.Bullet, x - xoff, Globals.playerEntity.getActX(), y - yoff, Globals.playerEntity.getActY(), BulletSpeed, mindamage, maxdamage));
			betattacks = 0;

		}

	}

	public void drawbullets() {

		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).update(delta);
			bullets.get(i).render();
			bullets.get(i).reset();
		}
		for (int i = 0; i < bullets.size(); i++) {
			if (bullets.get(i).getremoved() == true) {
				bullets.remove(i);
			}
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
		drawbullets();
		betattacks += delta;

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
		if (ydist < 1) {
			ydist *= -1;
		}

		distancebetween = Math.sqrt(((xdist * xdist) + (ydist * ydist)));// pythagorean
																			// theorem

		if (distancebetween < sightrange) {
			attack();
			if (distancebetween > range) {
				movedOnce = true;
				mx = moverx(actxdist, mx, speed, xdist, true);
				my = moverx(actydist, my, speed, ydist, false);

			} else {// this means the mob is within range and will attack
				double optxd = playerx + (Player.getW() / 2) - optrange;
				double optyd = playery + (Player.getH() / 2) - optrange;

				if (!(distancebetween == optrange) && movedOnce == false) {

					mx = moverx(optxd, mx, speed, xdist, true);
					my = moverx(optyd, my, speed, ydist, false);

				}

			}

		}

		mx = mx + getXoffset();// this is for movement of player
		my = my + getYoffset();
		getB().update(getdelta());
	}

	private double moverx(double i, double mx, double speed, double dist, boolean b) {
		dist = dist / 100;
		double change=0;

		if (i > 0) {
			change =  speed * dist;

			if (i < range) {

				change = -speed * dist;
			}
		} else {
			if (i < 0) {
				change = - speed * dist;
				if (i > range) {
					change = speed * dist;
				}
			}
		}
if(change >speed){
	change = speed;
}

if(change>0){
	change+=speed;
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
