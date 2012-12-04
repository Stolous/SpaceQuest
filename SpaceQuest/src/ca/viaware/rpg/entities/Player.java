package ca.viaware.rpg.entities;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import ca.viaware.rpg.entity.AbstractEntity;
import ca.viaware.rpg.game.Globals;
import static org.lwjgl.opengl.GL11.*;

public class Player extends AbstractEntity {
	private int maxHealth = 100;
	boolean color = false;
	private static int currentHealth;
	private int regenRate;
	List<Texture> animPositions = new ArrayList<Texture>(16);
	int animStage = 0;
	int animCount = 0;
	double speed = 0.15;
	private static double width, height;
	private boolean screenChanged = false;

	private double changeY, changeX;

	boolean walkChange = false;
	private int walkingDir = 0;

	private double actX = 0, actY = 0;

	public Player(double x, double y, double width, double height) {
		super(x, y, width, height);
		Player.width = width;
		Player.height = height;
		currentHealth = maxHealth;
		actX = Globals.dispWidth / 2 - width / 2;
		actY = Globals.dispHeight / 2 - height / 2;

	}

	public static double getW() {
		return width;
	}

	public static double getH() {
		return height;
	}

	@Override
	public void draw() {

		changeX = 0;
		changeY = 0;
		animPositions.get(animStage).bind();

		if (color) {
			GL11.glColor3f(1, 0.5f, 0.5f);

			Runnable r = new Runnable() {
				public void run() {
					try {
						Thread.sleep(100L);// this is so that the color shows
											// for a little while
						color = false;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			};
			Thread t = new Thread(r);
			t.start();

		}
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

		glBegin(GL_LINE_STRIP);
		glVertex2d(x, y);
		glVertex2d(x + 64, y);
		glVertex2d(x + 64, y + 64);
		glVertex2d(x, y + 64);
		glEnd();
	}

	@Override
	public void update(int delta) {

		//Temp system of compensating player coords for fullscreen change
		if (Globals.isFullscreen) {
			setX(Globals.dispWidth / 2 - width / 2);
			setY(Globals.dispHeight / 2 - height / 2);
			System.out.println(actX + ", " + actY);

			if (!screenChanged) {
				screenChanged = true;
				actX = actX + (Globals.dispWidth - Globals.dispWidthBK) / 2;
				actY = actY + (Globals.dispHeight - Globals.dispHeightBK) / 2;
			}
		}

		if (!Globals.isFullscreen) {
			if (screenChanged) {
				screenChanged = false;
				actX = actX + (Globals.dispWidth - Globals.dispWidthBK) / 2;
				actY = actY + (Globals.dispHeight - Globals.dispHeightBK) / 2;
			}
		}

		// Animation
		animCount += delta;
		if (animCount > 150) {
			animStage++;
			switch (walkingDir) {

			case 0:
				animStage = 1;
				break;
			case 1:
				if (walkChange) {
					animStage = 0;
					walkChange = false;
				}
				if (animStage > 3) {
					animStage = 0;
				}
				break;
			case 2:
				if (walkChange) {
					animStage = 4;
					walkChange = false;
				}
				if (animStage > 7) {
					animStage = 4;
				}
				break;
			case 3:
				if (walkChange) {
					animStage = 8;
					walkChange = false;
				}
				if (animStage > 15) {
					animStage = 8;
				}
				break;
			case 4:
				if (walkChange) {

				}
				break;

			}
			animCount = 0;
		}
	}

	public void addTexture(Texture tex) {
		animPositions.add(tex);
	}

	public void setWalkingDir(int d) {
		if (d != walkingDir) {
			walkChange = true;
		}
		walkingDir = d;
	}

	public double getSpeed() {
		return speed;
	}

	public void changePosition(double x, double y, int delta) {
		actX += x * delta;
		actY += y * delta;

		changeX = x * delta;
		changeY = y * delta;

	}

	public double getActX() {
		return actX;
	}

	public double getActY() {
		return actY;
	}

	public double getChangeX() {
		return changeX;
	}

	public double getChangeY() {
		return changeY;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public int getCurrentHealth() {
		return currentHealth;
	}

	public int getRegenRate() {
		return regenRate;
	}

	public void takedamage(int amount) {
		if (currentHealth > 0) {
			color = true;
			int percent = (amount * 100) / maxHealth;
			// System.out.println("Percent lost "+percent);
			Globals.h.change(-percent);
			if (currentHealth > 0) {
				currentHealth -= amount;

				// System.out.println("Enemy attack registered "+
				// amount+" damage was dealt "+ currentHealth +" health left");
			} else {
				death();
			}
		}
		if (currentHealth <= 0) {
			death();
		}
	}

	public static void death() {

		System.out.println("You are dead!");
		Globals.h.set(0);
	}

}
