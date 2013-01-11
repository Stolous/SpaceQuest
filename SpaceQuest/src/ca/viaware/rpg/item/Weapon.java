package ca.viaware.rpg.item;

import static org.lwjgl.opengl.GL11.*;

import org.newdawn.slick.opengl.Texture;

import ca.viaware.rpg.entity.Bullet;
import ca.viaware.rpg.entity.Entity;
import ca.viaware.rpg.game.Globals;
import ca.viaware.rpg.utilities.MouseData;

public class Weapon extends Item {

	private int minimumDamage, maximumDamage, fireRateTimer;
	private double fireRate;
	private Bullet bullet;
	private Texture texture;

	public Weapon(Texture t, Bullet bullet, int minDmg, int maxDmg, double fireRate) {
		super(t);
		minimumDamage = minDmg;
		maximumDamage = maxDmg;
		this.fireRate = fireRate;
		this.bullet = bullet;
		texture = t;
	}

	public void fireWeapon(int startX, int startY, int destX, int destY, int delta) {
		fireRateTimer += delta;

		if (fireRateTimer > fireRate) {
			Bullet bullet = this.bullet;

			bullet.setDestination(startX, startY, destX, destY, bullet.getCurrentSpeed(), Bullet.targetType.ENEMIES);
			bullet.setDamage(minimumDamage, maximumDamage);

			Globals.bullets.add(bullet);
		}

	}

	public void drawOnEntity(Entity e) {
		double x = e.getX();
		double y = e.getY();
		double width = 32, height = 32;

		double angle = 0;
		double triangleBase = MouseData.MouseX() - x;
		double triangleHeight = MouseData.MouseY() - y;
		angle = Math.tanh(triangleHeight / triangleBase) * 100;

		glEnable(GL_TEXTURE_2D);
		texture.bind();
		glPushMatrix();
		glRotated(angle, 0.0f, 0.0f, -1.0f);

		glBegin(GL_QUADS);
		glTexCoord2f(0f, 0f);
		glVertex2d(x, y);
		glTexCoord2f(1f, 0f);
		glVertex2d(x + width, y);
		glTexCoord2f(1f, 1f);
		glVertex2d(x + width, y + height);
		glTexCoord2f(0f, 1f);
		glVertex2d(x, y + height);
		glEnd();

		glPopMatrix();

	}
}
