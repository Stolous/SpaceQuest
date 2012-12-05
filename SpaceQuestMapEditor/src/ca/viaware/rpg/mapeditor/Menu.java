package ca.viaware.rpg.mapeditor;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Menu extends JFrame {

	public void showMenu() {
		setTitle("Menu");
		setSize(195, 200);
		setResizable(false);
		setLayout(new GridLayout(5, 1));
		setVisible(true);

		showComponents();
	}

	private void showComponents() {
		JButton save = new JButton();
		JButton newMap = new JButton();
		JButton exit = new JButton();
		JButton load = new JButton();

		Container cont = getContentPane();

		save.setText("Save");
		newMap.setText("New");
		exit.setText("Exit");
		load.setText("Load");

		cont.add(save);
		cont.add(load);
		cont.add(newMap);
		cont.add(exit);

		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String n = JOptionPane.showInputDialog("Map name");
				Map.passMap(MapEditor.tiles, 100, 100);
				Map.save(n);
			}
		});

		newMap.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MapEditor.tiles.clear();
				Globals.xOffset = 0;
				Globals.yOffset = 0;
			}
		});

		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (Globals.isSaved) {
					System.out.println("Saved");
					Globals.isRunning = false;
				} else {
					if (JOptionPane.showConfirmDialog(null, "Exit without saving?", "Quit", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						Globals.isRunning = false;
					}
				}
			}
		});

		load.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Globals.xOffset = 0;
				Globals.yOffset = 0;
				String file = JOptionPane.showInputDialog("Map name");
				if (Loader.checkFile(file)) {
					MapEditor.tiles = Loader.loadMap(file);
				} else {
					JOptionPane.showMessageDialog(null, "Map not found!");
				}
			}
		});
	}
}
