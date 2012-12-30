package ca.viaware.rpg.utilities;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class DebugScreen extends JFrame{

	JLabel delta = new JLabel();
	JLabel mouseCoords = new JLabel();
	JLabel playerCoords = new JLabel();
	JLabel mapCoords = new JLabel();
	JLabel totalEnemies = new JLabel();
	JLabel playerHealth = new JLabel();
	JLabel runningTime = new JLabel();
	
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
		cont.add(mapCoords);
		cont.add(delta);
		cont.add(runningTime);
		cont.add(totalEnemies);
		cont.add(playerHealth);
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
	
	public void updateMapOffset(int x, int y){
		mapCoords.setText("Map Offset: " + Integer.toString(x) + ", " + Integer.toString(y));
	}
	
	public void updateTotalEnemies(int t){
		totalEnemies.setText("Total enemies: " + Integer.toString(t));
	}
	
	public void updatePlayerHealth(double h){
		playerHealth.setText("Player health: " + Double.toString(h));
	}
	
	public void updateRunningTime(int m, int s){
		runningTime.setText("Running time: " + Integer.toString(m) + ":" + Integer.toString(s));
	}
}
