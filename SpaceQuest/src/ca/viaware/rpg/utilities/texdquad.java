package ca.viaware.rpg.utilities;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2i;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.opengl.GL11;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public  class texdquad {

	private double xsize, ysize, x, y, rotate, xh, yh;

	Texture texture;

	public texdquad(int xsizes, int ysizes, int xs, int ys, int rotates,
			String path,String ext) {

		rotate = rotates;
		setXsize(xsizes);
		ysize = ysizes;
		x = xs;
		y = ys;
		setXh(getXsize() / 2);
		yh = ysize / 2;

		try {
			texture = TextureLoader.getTexture(ext, new FileInputStream(
					new File(path)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		texture.bind();
		update();
	}

	public void update() {
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, 0);
		GL11.glTranslatef(10.0f, 10.5f, -0.0f); // back to previous position
		GL11.glRotated(rotate, 0.0f, 0.0f, -1.0f); // rotate
		GL11.glTranslatef(-10.0f, -10.5f, 0.0f); // to the origin
		GL11.glTranslated(-x, -y, 0);
		
		
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex2d(x - getXh(), y - yh);
		GL11.glTexCoord2f(0, texture.getHeight());
		GL11.glVertex2d(x - getXh(), y + yh);
		GL11.glTexCoord2f(texture.getWidth(), texture.getHeight());
		GL11.glVertex2d(x + getXh(), y + yh);
		GL11.glTexCoord2f(texture.getWidth(), 0);
		GL11.glVertex2d(x + getXh(), y - yh);
		GL11.glEnd();
		GL11.glPopMatrix();
		GL11.glDisable(GL11.GL_TEXTURE_2D);

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
	public void changetext(String path,String ext){
		try {
			texture = TextureLoader.getTexture(ext, new FileInputStream(
					new File(path)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		texture.bind();
		
	}
}
