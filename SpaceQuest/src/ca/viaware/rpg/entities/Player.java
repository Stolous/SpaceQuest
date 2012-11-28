package ca.viaware.rpg.entities;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.opengl.Texture;

import ca.viaware.rpg.entity.AbstractEntity;
import static org.lwjgl.opengl.GL11.*;

public class Player extends AbstractEntity {

	List<Texture> animPositions = new ArrayList(16);
	int animStage = 0;
	int animCount = 0;
	double speed = 0.15;
	
	private double changeY, changeX;
	

	boolean walkChange = false;
	private int walkingDir = 0;
	
	private int actX = 0, actY = 0;

	public Player(double x, double y, double width, double height) {
		super(x, y, width, height);
	}

	@Override
	public void draw() {
		changeX = 0;
		changeY = 0;
		animPositions.get(animStage).bind();

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

	@Override
	public void update(int delta) {
		
		//Animation
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
	
	public double getSpeed(){
		return speed;
	}
	
	public void changePosition(double x, double y, int delta){
		actX += x * delta; 
		actY += y * delta;
		
		changeX = x * delta; 
		changeY = y * delta;
	}
	
	public int getActX(){
		return actX;
	}
	
	public int getActY(){
		return actY;
	}
	
	public double getChangeX(){
		System.out.println("Change x"+ changeX);
		return changeX;
	}
	
	public double getChangeY(){
		System.out.println("Change y"+ changeY);
		return changeY;
	}
	

}
