package ca.viaware.rpg.utilities;

import javax.swing.event.EventListenerList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;
import static org.lwjgl.opengl.GL11.*;

import ca.viaware.rpg.entity.AbstractEntity;
import ca.viaware.rpg.game.Globals;

public class SButton extends AbstractEntity {
	private boolean bindown = false ;
	private Texture buttTex;
	protected EventListenerList listenerList = new EventListenerList();
	String text = "";
	private TextRenderer tRender = new TextRenderer(50, 0, TextRenderer.Font.BLACK);

	public SButton(int X, int Y, int width, int height) {
		super(X, Y, width, height);
		TextureHandler tt = new TextureHandler();
		buttTex = tt.loadSprite("other/button");

	}

	public void AddOnClickListener(ClickListener listener) {
		listenerList.add(ClickListener.class, listener);
	}
public void move(double x,double y){
	tRender.move(x, y);
	this.x+=x;
	this.y+=y;
}
	@Override
	public void draw() {
		// Rendering goes here.

		// Changed to regular opengl quads because ur textured quad thing has
		// some weird offset so that when you change the width and height it
		// changes the x and y, this now puts it into the proper placement
		buttTex.bind();
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
		
		if (Mouse.isButtonDown(0) && Globals.cursor.intersects(this)){
			glBegin(GL_QUADS);
			glTexCoord2f(1f, 1f);
			glVertex2d(x, y);
			glTexCoord2f(0f, 1f);
			glVertex2d(x + width, y);
			glTexCoord2f(0f, 0f);
			glVertex2d(x + width, y + height);
			glTexCoord2f(1f, 0f);
			glVertex2d(x, y + height);
			glEnd();
		}

		// Your text renderer seems to have a similar issue, thats why the y
		// needs to be increased by 25 just to make it in the same place as the
		// quad. this is something that you need to look into because this means
		// that all of your textured quads are off slightly
		tRender.writeToScreen((int) x, (int) y, text);
		tRender.finish();
	}

	public void setText(String t) {
		text = t;
	}

	public void updateOnClickListener(OnClickListener OCL) {
		Object[] listeners = listenerList.getListenerList();

		if (Mouse.isButtonDown(0)) {

			
			for (int i = 0; i < listeners.length; i = i + 2) {
				if (this.intersects(Globals.cursor)) {
					bindown=true;
					

				}
				
			}
		}else if(bindown==true){
			for (int i = 0; i < listeners.length; i = i + 2) {
			((ClickListener) listeners[i + 1]).ClickListener(OCL);
			}
		}
	}

	@Override
	public void update(int delta) {

		// Update is for logic, not rendering.

	}

}
