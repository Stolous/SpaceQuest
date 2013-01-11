package ca.viaware.rpg.AStarPathFinding;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class TempDebugScreen extends JFrame{

	private int mapX = 0, mapY = 0, currentLine = 0;
	private JLabel[] lines;
	public TempDebugScreen(int mapX, int mapY){
		this.mapX = mapX;
		this.mapY = mapY;
		setSize(600,700);
		setTitle("MobMap");
		setLayout(new GridLayout(mapY + 2, 1));
		setVisible(true);
		setLocation(0,500);
		lines = new JLabel[mapY + 2];
		showComponents();
	}
	
	private void showComponents(){
		Container cont = getContentPane();
		for (int i = 0; i < mapY + 2; i++){
			lines[i] = new JLabel();
			lines[i].setFont(new Font("Monospaced", Font.BOLD, 10));
			cont.add(lines[i]);
		}
		
	}
	
	public void nextLine(){
		currentLine++;
	}
	
	public void addText(String t){
		
		lines[currentLine].setText(lines[currentLine].getText() + t);
	}
	
	public void reset(){
			currentLine = 0;
			
			for (JLabel label : lines){
				label.setText("");
			}
	}
}
