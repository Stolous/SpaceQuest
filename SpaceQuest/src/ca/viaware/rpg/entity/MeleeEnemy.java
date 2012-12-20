package ca.viaware.rpg.entity;

import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import ca.viaware.rpg.entities.Player;
import ca.viaware.rpg.game.Globals;
import ca.viaware.rpg.map.Tile;
import ca.viaware.rpg.utilities.TexturedQuad;

public class MeleeEnemy extends Enemy {

	private boolean b = false;
	private TexturedQuad t;
	private int delta, agressiveness, betattacks, attackspeed, blockx, blocky;
	private double distancebetween, xdist, ydist, playerx, playery, range, actxdist, actydist, speed, sightrange, xspeed, yspeed;

	public MeleeEnemy(double width, double height, int maxhealth, int maxdamage, int mindamage, int spawnx, int spawny, int agresiveness, double range, double speed, int attackspeed, double sightrange) {
		super(width, height, maxhealth, maxdamage, mindamage, spawnx, spawny);

		this.attackspeed = attackspeed;
		this.speed = speed / 100;
		this.range = range;
		this.agressiveness = agresiveness;
		mx = spawnx;
		my = spawny;
		x = (mx);
		y = (my);
		this.sightrange = sightrange;

	}

	public int getdelta() {
		return delta;
	}

	@Override
	public void update(int delta) {
		
		
		blockx = (int) (mx / 64);
		blocky = (int) (my / 64);

		b = false;
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
			// mob colision
			int i = 0;
			for (Tile[] tile1 : Globals.gameMap.mapTiles) {

				if (ydist > 0) {
					if (blockx == tile1[i].getBX()) {
						if (blocky - 1 == tile1[i].getBY()) {
							// then it means that it is in this tile
							if (Globals.gameMap.mapTiles[blockx][blocky].hasCollision()) {
								// block on right of mob has collision
							}
						}
					}
				}
				if (xdist > 0) {
					if (blockx - 1 == tile1[i].getBX()) {
						if (blocky == tile1[i].getBY()) {
							// then it means that it is in this tile
							if (Globals.gameMap.mapTiles[blockx][blocky].hasCollision()) {
								// block on right of mob has collision
							}
						}
					}
				}

				i++;
			}
			if (distancebetween > range) {
				mx = moverx(actxdist, mx, speed, xdist);
				my = moverx(actydist, my, speed, ydist);

			} else {// this means the mob is within range and will attack
				b = true;
				attack();
			}

			if (this.intersects(Globals.playerEntity) && b == false) {
				attack();
			}
		}
		

		mx = mx + getXoffset();// this is for movement of player
		my = my + getYoffset();

	}

	private double moverx(double i, double mx, double speed, double dist) {
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
