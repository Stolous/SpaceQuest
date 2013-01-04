package ca.viaware.rpg.entity;

import java.util.Random;

import org.newdawn.slick.opengl.Texture;

import ca.viaware.rpg.game.Globals;
import ca.viaware.rpg.map.Tile;
import ca.viaware.rpg.utilities.TexturedQuad;

public class Bullet extends AbstractMoveableEntity {
	private Texture t;
	private boolean removed = false;
	TexturedQuad b;
	private int mindamage, maxdamage;
	@SuppressWarnings("unused")
	private double oldX, newX, newY, oldY, xSpeed, ySpeed, XOffset, YOffset;
	@SuppressWarnings("unused")
	private boolean once;
	private targetType target;

	public Bullet(Texture t, double oldX, double newX, double oldY, double newY, double bulletSpeed, int mind, int maxd, targetType target) {
		super(oldX, oldY, 50, 50);
		width = 32;
		height = 32;
		this.mindamage = mind;
		this.maxdamage = maxd;
		this.t = t;

		XOffset = Globals.gameMap.getXOffset();

		YOffset = Globals.gameMap.getYOffset();

		bulletSpeed = bulletSpeed / 1000;

		b = new TexturedQuad((int) width, (int) height, x, y, this.t);

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
		y = y - YOffset;
		newX = newX + XOffset;// this is for movement of player
		newY = newY + YOffset;

		double angle = 0;
		double triangleBase = oldX - newX;
		double triangleHeight = oldY - newY;
		angle = Math.tanh(triangleHeight / triangleBase) * 100;

		b.rotate(angle * -1 / 2);

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
		
		this.target = target;

	}

	public void update(int delta) {
		
		XOffset = Globals.gameMap.getXOffset();
		YOffset = Globals.gameMap.getYOffset();
		// System.out.println("SX is" + sx);

		x = x + xSpeed * delta;
		y = y + ySpeed * delta;

		// removes if really far away
		if (x > (Globals.dispWidth * 5)) {
			removed = true;
		}
		if (y > (Globals.dispWidth * 5)) {
			removed = true;
		}
		if (x < (Globals.dispWidth * -5)) {
			removed = true;
		}
		if (y < (Globals.dispWidth * -5)) {
			removed = true;
		}
		y = y + YOffset;// this is for movement of player
		x = x + XOffset;

		// removes if colides with object
		if (Globals.playerEntity.intersects(this) && target == targetType.PLAYER) {
			contact(null);
		} else if (target == targetType.ENEMIES){
			for (Enemy enemy : Globals.enemies){
				if (enemy.intersects(this)){
					contact(enemy);
				}
			}
		}else{
			for (Tile[] tile1 : Globals.gameMap.mapTiles) {
				for (Tile tile : tile1) {
					if (tile.intersects(this)) {
						if (tile.hasCollision()) {
							removed = true;
						}
					}
				}
			}

		}

		b.setlocation(x, y);
	}

	public void reset() {
		y = y - YOffset;// this is for movement of player
		x = x - XOffset;

	}

	private void contact(Enemy e) {
		Random r = new Random();
		int damage = r.nextInt(maxdamage - mindamage);
		damage += mindamage;
		
		if (target == targetType.PLAYER){
		Globals.playerEntity.takedamage(damage);
		}else if (target == targetType.ENEMIES){
		e.takedamage(damage);
		}
		
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

	}
	
	public static enum targetType{
		ENEMIES, FRIENDLIES, PLAYER, ALLNPCS, ALL;
	}

}
