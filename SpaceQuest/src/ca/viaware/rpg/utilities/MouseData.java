package ca.viaware.rpg.utilities;

import org.lwjgl.input.Mouse;

import ca.viaware.rpg.game.Globals;

public class MouseData {

	public static int MouseX(){
		return Mouse.getX();
	}
	
	public static int MouseY(){
		return Globals.dispHeight - Mouse.getY() - 1;
	}
}
