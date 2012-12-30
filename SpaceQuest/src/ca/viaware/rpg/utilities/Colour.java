package ca.viaware.rpg.utilities;

import static org.lwjgl.opengl.GL11.*;

public class Colour {

	private double red, green, blue;
	
	public Colour(double r, double g, double b){
		red = r;
		green = g;
		blue = b;
	}
	
	public double getR(){
		return red;
	}
	
	public double getG(){
		return green;
	}
	
	public double getB(){
		return blue;
	}
	
	public void bind(){
		glColor3d(red,green,blue);
	}
}
