package ca.viaware.rpg.utilities;

import java.util.Random;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;

import ca.viaware.rpg.entity.AbstractMoveableEntity;
import ca.viaware.rpg.game.Globals;
import ca.viaware.rpg.map.Tile;

public class Bullet extends AbstractMoveableEntity {
	// Why no AbstractMoveableEntity?? How will we detect bullet collision now??
	// And don't say you didn't know again, cause you did and could have saved
	// yourself the effort converting this all to the Entity standard.
	private Texture t;
	private boolean removed = false;
	TexturedQuad b;
	private int mindamage, maxdamage;
	private double oldX, newX, newY, oldY, xSpeed, ySpeed, XOffset, YOffset;
	private boolean once;
	public Bullet(Texture t, Double oldX, Double newX, double oldY, double newY, double bulletSpeed, int mind, int maxd) {
		super(oldX, oldY, 50, 50);
		this.mindamage = mind;
		this.maxdamage = maxd;
		this.t = t;
		
		XOffset = Globals.gameMap.getXOffset();
		
		YOffset = Globals.gameMap.getYOffset();
		System.out.println("oldX is "+ oldX);
		
		bulletSpeed = bulletSpeed / 1000;

		b = new TexturedQuad(50, 50, x, y, this.t);

		newY += 640;
		newX += 640;
		this.t = t;
		this.oldX = oldX;
		this.newX = newX;
		this.oldY = oldY;
		this.newY = newY;
		double ySpeed = 0;
		double xSpeed = 0;
		x = oldX;
		y = oldY;
		x = x - XOffset;// this is for movement of player
		y=y-YOffset;
		
	
		// Maths to make bullet go in direction thing
		xSpeed = (float) (newX - oldX);
		ySpeed = (float) (newY - oldY);
	
		double factor = (double) (((xSpeed * xSpeed) + (ySpeed * ySpeed)));
		
		factor = Math.sqrt(factor);
	
		factor = bulletSpeed / factor;
		
		xSpeed = xSpeed * factor;
		ySpeed = ySpeed * factor;

		this.ySpeed = ySpeed;
		this.xSpeed = xSpeed;
		once = true;
	
		}

	public void update(int delta) {
		
		XOffset = Globals.gameMap.getXOffset();
		YOffset = Globals.gameMap.getYOffset();
		//System.out.println("SX is" + sx);

		int blockx = (int) (x / 64);
		int blocky = (int) (y / 64);
		x = x + xSpeed * delta;
		y = y + ySpeed * delta;
		if (x == Globals.playerEntity.getX() && y == Globals.playerEntity.getY()) {
			contact();
		} else {
			int i = 0;
			for (Tile[] tile1 : Globals.gameMap.mapTiles) {

				if (blockx == tile1[i].getBX()) {
					if (blocky == tile1[i].getBY()) {
						// then it means that it is in this tile
						if (Globals.gameMap.mapTiles[blockx][blocky].hasCollision()) {
							// block on right of mob has collision
							//System.out.println("Block has collision");
						}
					}
				}

			}
		}
		
		
		y = y + YOffset;// this is for movement of player
		x = x + XOffset;
		
		
		b.setlocation(x, y);
	}

	public void reset() {
		y = y - YOffset;// this is for movement of player
		x = x - XOffset;
	
		
		
		
	}

	private void contact() {

		Random r = new Random();
		int damage = r.nextInt(maxdamage - mindamage);
		damage += mindamage;
		Globals.playerEntity.takedamage(damage);
		removed = true;

	}

	public boolean getremoved() {
		return removed;
	}

	public void render() {
		b.update();
		
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}

}
