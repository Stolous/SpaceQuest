package ca.viaware.rpg.entity;


import java.util.Random;

import org.lwjgl.input.Keyboard;


import ca.viaware.rpg.AStarPathFinding.AStar;
import ca.viaware.rpg.AStarPathFinding.AStarHeuristic;
import ca.viaware.rpg.AStarPathFinding.ClosestHeuristic;
import ca.viaware.rpg.AStarPathFinding.mobMap;
import ca.viaware.rpg.entities.Player;
import ca.viaware.rpg.game.Globals;
import ca.viaware.rpg.map.Tile;
import ca.viaware.rpg.utilities.TexturedQuad;

public class MeleeEnemy extends Enemy {

	
	private boolean b = false,first=true;
	private TexturedQuad t;
	@SuppressWarnings("unused")
	private int delta, agressiveness, betattacks, attackspeed, blockx, blocky;
	@SuppressWarnings("unused")
	private double distancebetween, xdist, ydist, goalX, goalY, range, actxdist, actydist, speed, sightrange, xspeed, yspeed;

	private AStar pathFinder;
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

		AStarHeuristic heuristic = new ClosestHeuristic();
        pathFinder = new AStar(heuristic);	
	}

	public int getdelta() {
		return delta;
	}

	@Override
	public void update(int delta) {
		
		

			
		int newblockx = (int) (mx / 64);
		int newblocky = (int) (my / 64);
			
			if(!(newblockx==blockx)||!(newblocky==blocky)||first){
				System.out.println(first);
				blockx = newblockx;
				blocky = newblocky; 
				first = false;
			mobMap map = new mobMap();
	        pathFinder.updatemap(map);
	        pathFinder.calcShortestPath((int)(x/64), (int)(y/64),(int)Globals.playerEntity.getX()/64,(int) Globals.playerEntity.getY()/64);
	        System.out.println("MAP:");
	        pathFinder.printPath();
			mx = mx + getXoffset();// this is for movement of player
			my = my + getYoffset();
			goalX = pathFinder.getNextWaypointX()*64;
			goalY = pathFinder.getNextWaypointY()*64;
			System.out.println("Goal X "+ goalX+"Goal Y "+ goalY);
			
			}

		b = false;
		setX(getT().getx());
		setY(getT().gety());

		this.delta = delta;
		setXoffset((Globals.gameMap.getXOffset()));
		setYoffset((Globals.gameMap.getYOffset()));
		// MATH (YAY!!!!!!!!!!)
		

		xdist = goalX + (Player.getW() / 2) - mx;

		actxdist = xdist;
		// if negative
		if (xdist < 0) {
			xdist *= -1;
		}
		ydist = goalY + (Player.getH() / 2) - my;
		actydist = ydist;
		if (ydist < 0) {
			ydist *= -1;
		}

		distancebetween = Math.sqrt(((xdist * xdist) + (ydist * ydist)));// pythagorean
																			// theorem
		mx = moverx(actxdist, mx, speed, xdist);
		my = moverx(actydist, my, speed, ydist);
		if (distancebetween < sightrange) {
			

			if (distancebetween > range) {
				

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
