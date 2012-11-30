package ca.viaware.rpg.entities;

import static org.lwjgl.opengl.GL11.*;
import ca.viaware.rpg.entity.AbstractMoveableEntity;
import ca.viaware.rpg.utilities.MouseData;

public class Cursor extends AbstractMoveableEntity {

	public Cursor(double x, double y, double width, double height) {
		super(x, y, width, height);
	}

	@Override
	public void draw() {
		
		glBegin(GL_QUADS);
			glVertex2d(x,y);
			glVertex2d(x+width,y);
			glVertex2d(x+width,y+height);
			glVertex2d(x,y+height);
		glEnd();
		
		
	}

	@Override
	public void update(int delta) {
		setX(MouseData.MouseX());
		setY(MouseData.MouseY());
	}

}
