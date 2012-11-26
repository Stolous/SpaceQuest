package ca.viaware.rpg.mapeditor;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Menu extends JFrame{

	public void showMenu(){
		setTitle("Menu");
		setSize(195,200);
		setResizable(false);
		setLayout(new GridLayout(5,1));
		setVisible(true);
		
		showComponents();
	}
	
	private void showComponents(){
		JButton save = new JButton();
		JButton newMap = new JButton();
		JButton exit = new JButton();
		
		Container cont = getContentPane();
		
		save.setText("Save");
		newMap.setText("New");
		exit.setText("Exit");
		
		cont.add(save);
		cont.add(newMap);
		cont.add(exit);
		
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Map.passMap(MapEditor.tiles, 50, 50);
				Map.save();
			}
		});
		
		newMap.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MapEditor.tiles.clear();
			}
		});
		
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
}
