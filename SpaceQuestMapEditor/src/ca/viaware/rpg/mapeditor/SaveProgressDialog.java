package ca.viaware.rpg.mapeditor;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class SaveProgressDialog extends JFrame{
	int max = 0;
	public void showFrame(){
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		setTitle("Saving...");
		setSize(300,100);
		setResizable(false);
		setLocation(dim.width / 2 - 300 / 2, dim.height / 2 - 100 / 2);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLayout(new FlowLayout());
		setVisible(true);
		
		showComponents();
	}
	
	public void showComponents(){
		Container cont = getContentPane();
		JLabel label = new JLabel();
		
		cont.add(label);
		
		label.setText("Saving...");
		repaint();
		label.repaint();
	}
	
	public void exit(){
		setVisible(false);
	}
	
}
