package ca.viaware.rpg.utilities;

import static org.lwjgl.opengl.GL11.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.opengl.GL11;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class TexturedQuad {

	private double xsize, ysize, x, y, rotate, xh, yh;

	Texture t;

	public TexturedQuad(int xsizes, int ysizes, int xs, int ys, int rotates, String path) {

		rotate = rotates;
		setXsize(xsizes);
		ysize = ysizes;
		x = xs;
		y = ys;
		setXh(getXsize() / 2);
		yh = ysize / 2;

		try {
			t = TextureLoader.getTexture(".PNG", new FileInputStream(new File(path)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {

		}

		t.bind();
		update();
	}

	public TexturedQuad(int xsizes, int ysizes, double xs, double ys, Texture t) {
		this.t = t;
		rotate = 0;
		setXsize(xsizes);
		ysize = ysizes;
		x = xs;
		y = ys;
		setXh(getXsize() / 2);
		yh = ysize / 2;
		t.bind();
		update();
	}

	public void setSize(double x, double y) {
		setXh(x / 2);
		yh = y / 2;
	}

	public void update() {

		glEnable(GL11.GL_TEXTURE_2D);
		t.bind();
		glPushMatrix();
		glTranslated(x, y, 0);
		glTranslatef(10.0f, 10.5f, -0.0f); // back to previous position
		glRotated(rotate, 0.0f, 0.0f, -1.0f); // rotate
		glTranslatef(-10.0f, -10.5f, 0.0f); // to the origin
		glTranslated(-x, -y, 0);

		glBegin(GL11.GL_QUADS);
		glTexCoord2f(0, 0);
		glVertex2d(x - getXh(), y - yh);
		glTexCoord2f(0, t.getHeight());
		glVertex2d(x - getXh(), y + yh);
		glTexCoord2f(t.getWidth(), t.getHeight());
		glVertex2d(x + getXh(), y + yh);
		glTexCoord2f(t.getWidth(), 0);
		glVertex2d(x + getXh(), y - yh);
		glEnd();
		glPopMatrix();
		glDisable(GL11.GL_TEXTURE_2D);

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

	public double getXh() {
		return xh;
	}

	public void setXh(double xh) {
		this.xh = xh;
	}

	public double getXsize() {
		return xsize;
	}

	public void setXsize(double xsize) {
		this.xsize = xsize;
	}

	public void changetexture(Texture tex) {
		t = tex;
		t.bind();

	}
}
