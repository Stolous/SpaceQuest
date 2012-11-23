package ca.viaware.rpg.mapeditor;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Rectangle;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

public class Tile {
	private int x = 0, y = 0, actX = 0, actY = 0;
	private int ID = 0;
	private boolean selected = false;

	public Tile(int x, int y, int ID) {
		this.x = x;
		this.y = y;
		this.ID = ID;
		
		actX = x / 64 + Globals.xOffset;
		actY = y / 64 + Globals.yOffset;
	}

	public void renderTile() {
		glEnable(GL_TEXTURE_2D);

		MapEditor.textures.get(ID).getTexture().bind();


		glBegin(GL_QUADS);
		glTexCoord2f(0f,0f);
		glVertex2i(x, y);
		glTexCoord2f(1f,0f);
		glVertex2i(x + 64, y);
		glTexCoord2f(1f,1f);
		glVertex2i(x + 64, y + 64);
		glTexCoord2f(0f,1f);
		glVertex2i(x, y + 64);
		glEnd();
		glDisable(GL_TEXTURE_2D);
		
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public int getActX(){
		return actX;
	}
	
	public int getActY(){
		return actY;
	}

	public int getID() {
		return ID;
	}

	public void setX(int x) {
		if (x > this.x){
			actX++;
		} else if (x < this.x){
			actX--;
		}
		
		System.out.println(actX);
		this.x = x;
	}

	public void setY(int y) {
		if (y > this.y){
			actY++;
		} else if (y < this.y){
			actY--;
		}
		
		System.out.println(actY);
		this.y = y;
	}
	
	public void shiftUP(){
		y = y - 64;
	}
	
	public void shiftDOWN(){
		y = y + 64;
	}
	
	public void shiftLEFT(){
		x = x - 64;
	}
	
	public void shiftRIGHT(){
		x = x + 64;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public boolean isTouching(Rectangle rect) {
		boolean touching = false;
		Rectangle thisRect = new Rectangle(x, y, 64, 64);
		
		if (thisRect.intersects(rect)){
			touching = true;
		}
		
		return touching;
	}
	
	public void setSelected(boolean s){
		selected = s;
	}
	
	public boolean isSelected(){
		return selected;
	}
	
	public String getDataString(){
		System.out.println("Getting ID, returning " + ID);
		return Integer.toString(ID) + "&";
	}
	

}
