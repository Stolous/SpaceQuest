package ca.viaware.rpg.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import ca.viaware.rpg.game.Globals;

public class TextRenderer {
	@SuppressWarnings("unused")
	private double xsize, ysize, x, y, rotate, xh, yh, lxpos, lypos, xloc;
	private Texture texture = null;
	String text;
	List<String> textBuffer = new ArrayList<String>(16);

	public TextRenderer(int fontsize, int rotates, Font font) {

		// Constructor now mainly only used to set font data, text is rendered
		// in a separate method which makes it so that only one text renderer
		// has to be used to render text in all kinds of different places

		// text = Text;
		String path = null;
		switch (font){
		case WHITE:
			path = "res/text/fontsWhite.png";
			break;
		case BLACK:
			path = "res/text/fonts.png";
			break;
		
		}
				
		rotate = rotates;
		setXsize(fontsize);
		ysize = fontsize;
		// x = xlocation;
		// y = ylocation;
		setXh(getXsize() / 2);
		yh = ysize / 2;
		// xloc = xlocation;
		try {
			texture = TextureLoader.getTexture(".PNG", new FileInputStream(new File(path)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void writeToScreen(int x, int y, String text) {
		
		this.x = x;
		this.y = y;
		textBuffer.add(text);
	}

	public void update() {
		x = getx();
		y = gety();
		char[] c = text.toCharArray();

		int letter;
		// 16 collumns 16 rows

		double mov = xsize * 0.4;
		for (int i = 0; i < c.length; i++) {
			texture.bind();
			x = x + mov;

			letter = c[i];
			lypos = letter % 16;

			lxpos = letter / 16;


			glEnable(GL_TEXTURE_2D);
			glPushMatrix();
			glTranslated(x, y, 0);
			glTranslatef(10.0f, 10.5f, -0.0f); // back to previous position
			glRotated(rotate, 0.0f, 0.0f, -1.0f); // rotate
			glTranslatef(-10.0f, -10.5f, 0.0f); // to the origin
			glTranslated(-x, -y, 0);
			// above is for movement/rotation of text

			// 128 px total

			double nbr = 0.063;

			double startx = lypos * nbr;// 16 rows/columns
			double starty = lxpos * nbr;

			double endx = startx + nbr;
			double endy = starty + nbr;

			glBegin(GL_QUADS);
			glTexCoord2d(startx, starty);
			glVertex2d(x - getXh(), y - yh);
			glTexCoord2d(startx, endy);
			glVertex2d(x - getXh(), y + yh);
			glTexCoord2d(endx, endy);
			glVertex2d(x + getXh(), y + yh);
			
			glTexCoord2d(endx, starty);
			glVertex2d(x + getXh(), y - yh);
			glEnd();
			glPopMatrix();
			glDisable(GL_TEXTURE_2D);

			/*
			 * Just in case i screw something up GL11.glBegin(GL11.GL_QUADS);
			 * GL11.glTexCoord2f(0, 0); GL11.glVertex2d(x - getXh(), y - yh);
			 * GL11.glTexCoord2f(0, texture.getHeight()); GL11.glVertex2d(x -
			 * getXh(), y + yh); GL11.glTexCoord2f(texture.getWidth(),
			 * texture.getHeight()); GL11.glVertex2d(x + getXh(), y + yh);
			 * 
			 * GL11.glTexCoord2f(texture.getWidth(), 0); GL11.glVertex2d(x +
			 * getXh(), y - yh); GL11.glEnd(); GL11.glPopMatrix();
			 * GL11.glDisable(GL11.GL_TEXTURE_2D);
			 */

		}

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
		return x+xh;
	}

	public double gety() {
		return y+yh;
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
	
	public void renderBuffer(){
		for (String t : textBuffer){
			text = t;
			update();
		}
		
		textBuffer.clear();
	}

	public void finish(){
		Globals.textRendererBufferList.add(this);
	}
	
	public static enum Font{
		WHITE, BLACK;
	}
}