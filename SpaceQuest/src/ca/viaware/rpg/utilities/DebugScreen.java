package ca.viaware.rpg.utilities;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class DebugScreen extends JFrame{

	JLabel delta = new JLabel();
	JLabel mouseCoords = new JLabel();
	JLabel playerCoords = new JLabel();
	
	public DebugScreen(int width, int height){
		setSize(width,height);
		setResizable(false);
		setLayout(new FlowLayout());
		setVisible(true);
		
		showComponents();
	}
	
	private void showComponents(){
		Container cont = getContentPane();
		
		cont.add(mouseCoords);
		cont.add(playerCoords);
		cont.add(delta);
	}
	
	public void updateDelta(int delta){
		this.delta.setText("Delta: " + Integer.toString(delta));
	}
	
	public void updateMouseCoords(int x, int y){
		mouseCoords.setText("Mouse Coords: " + Integer.toString(x) + ", " + Integer.toString(y));
	}
	
	public void updatePlayerCoords(double x, double y){
		playerCoords.setText("Player Coords: " + Integer.toString((int)x) + ", " + Integer.toString((int)y));
	}
}
