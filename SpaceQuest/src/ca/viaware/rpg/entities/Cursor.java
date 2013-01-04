package ca.viaware.rpg.entities;

import static org.lwjgl.opengl.GL11.*;

import org.newdawn.slick.opengl.Texture;

import ca.viaware.rpg.entity.AbstractMoveableEntity;
import ca.viaware.rpg.game.Globals;
import ca.viaware.rpg.utilities.MouseData;

public class Cursor extends AbstractMoveableEntity {
	private Texture mTex;
	private boolean enabled = false;

	public Cursor(double x, double y, double width, double height) {
		super(x, y, width, height);
	}

	@Override
	public void draw() {
		if (enabled) {

			mTex.bind();

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
		}

	}

	@Override
	public void update(int delta) {

		setX(MouseData.MouseX());
		setY(MouseData.MouseY());

	}

	public void setTexture(Texture t) {
		mTex = t;
	}

	public void setEnabled(boolean e) {
		enabled = e;
	}
	
	public double getActX(){
		return getX() - Globals.gameMap.getXOffset();
	}
	
	public double getActY(){
		return getY() - Globals.gameMap.getYOffset();
	}

}
