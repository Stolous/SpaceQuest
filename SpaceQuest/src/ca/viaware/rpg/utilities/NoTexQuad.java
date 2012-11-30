package ca.viaware.rpg.utilities;

import static org.lwjgl.opengl.GL11.glColor3f;

import org.lwjgl.opengl.GL11;

public class NoTexQuad {

	private double xsize, ysize, x, y, rotate, xh, yh;

	public NoTexQuad(int xsizes, int ysizes, int xs, int ys, int rotates) {

		rotate = rotates;
		setXsize(xsizes);
		setYsize(ysizes);
		x = xs;
		y = ys;
		update();
	}

	

	public void update() {
		xh = getXsize() / 2;
		yh = getYsize() / 2;
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, 0);
		GL11.glTranslatef(10.0f, 10.5f, -0.0f); 
		GL11.glRotated(rotate, 0.0f, 0.0f, -1.0f); 
		GL11.glTranslatef(-10.0f, -10.5f, 0.0f);
		GL11.glTranslated(-x, -y, 0);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2d(x - xh, y - yh);
		GL11.glVertex2d(x - xh, y + yh);
		GL11.glVertex2d(x + xh, y + yh);
		GL11.glVertex2d(x + xh, y - yh);
		GL11.glEnd();
		GL11.glPopMatrix();
	}

	public void rotate(double rotates) {
		rotate = rotate + rotates;
	}

	public void move(double xs, double ys) {
		x = x + xs;
		y = y + ys;
	}

	public void setlocation(double xs, double ys) {
		x = xs;
		y = ys;
	}

	public double getrotate() {
		return rotate;
	}

	public double getx() {
		return x;
	}

	public double gety() {
		return y;
	}

	public double getXsize() {
		return xsize;
	}

	public void setXsize(double xsize) {
		this.xsize = xsize;
	}

	public double getYsize() {
		return ysize;
	}

	public void setYsize(double ysize) {
		this.ysize = ysize;
	}

}
