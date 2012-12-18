package ca.viaware.rpg.utilities;

import static org.lwjgl.opengl.GL11.*;

public class NoTexQuad {

	private double xsize, ysize, x, y, rotate, xh, yh, r, g, b;

	public NoTexQuad(int xsizes, int ysizes, int xs, int ys, int rotates) {
		r = 1;
		g = 1;
		b = 1;
		rotate = rotates;
		setXsize(xsizes);
		setYsize(ysizes);
		x = xs;
		y = ys;
		update();
	}

	public void setcol(double r, double g, double b) {
		this.r = r;
		this.g = g;
		this.b = b;

	}

	public void setSize(double x, double y) {
		xh = x / 2;
		yh = y / 2;
	}

	public void update() {

		glColor3d(r, g, b);
		glPushMatrix();
		glTranslated(x, y, 0);
		glTranslatef(10.0f, 10.5f, -0.0f);
		glRotated(rotate, 0.0f, 0.0f, -1.0f);
		glTranslatef(-10.0f, -10.5f, 0.0f);
		glTranslated(-x, -y, 0);
		glBegin(GL_QUADS);
		glVertex2d(x - xh, y - yh);
		glVertex2d(x - xh, y + yh);
		glVertex2d(x + xh, y + yh);
		glVertex2d(x + xh, y - yh);
		glEnd();
		glPopMatrix();
		glColor3d(1, 1, 1);
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

	public void resize(double x, double y) {
		xsize = this.xsize + x;
		ysize = this.ysize + y;
	}

}
