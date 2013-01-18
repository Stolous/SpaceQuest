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
	private Texture texture, bulletTexture;

	public Weapon(Texture t, Texture bulletTexture, int minDmg, int maxDmg, double fireRate) {
		super(t);
		minimumDamage = minDmg;
		maximumDamage = maxDmg;
		this.fireRate = fireRate;
		this.bulletTexture = bulletTexture;
		texture = t;
	}

	public void fireWeapon(double startX, double startY, double destX, double destY, int delta) {
		fireRateTimer += delta;

		if (fireRateTimer > fireRate) {

			Globals.bullets.add(new Bullet(bulletTexture, startX, destX, startY, destY, 50, minimumDamage, maximumDamage, Bullet.targetType.ENEMIES));
			
			fireRateTimer = 0;
		}

	}

	public void drawOnEntity(Entity e) {
		double x = e.getX();
		double y = e.getY();
		double width = 64, height = 64;

		double angle = 0;
		double triangleBase = MouseData.MouseX() - x;
		double triangleHeight = MouseData.MouseY() - y;
		angle = Math.tanh(triangleHeight / triangleBase) * 100;

		glEnable(GL_TEXTURE_2D);
		texture.bind();
		
		if (MouseData.MouseX() < x){
			angle += 180;
			height *= -1;
		}
		
		glPushMatrix();
		glTranslated(x, y, 0);
		glTranslatef(10.0f, 10.5f, -0.0f); // back to previous position
		glRotated(-angle, 0.0f, 0.0f, -1.0f); // rotate
		glTranslatef(-10.0f, -10.5f, 0.0f); // to the origin
		glTranslated(-x, -y, 0);

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
