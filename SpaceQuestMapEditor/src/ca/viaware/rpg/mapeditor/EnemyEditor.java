package ca.viaware.rpg.mapeditor;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class EnemyEditor extends JFrame {
	private Enemy selectedEnemy = null;
	private JComboBox enemyList = new JComboBox();
	private JButton submit = new JButton();
	private JTextField enemyLevel = new JTextField();

	public void createEditor() {
		setTitle("Enemy Editor");
		setSize(200, 200);
		setLocation(0, 700);
		setLayout(new GridLayout(5, 1));
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		showComponents();
	}

	private void showComponents() {
		Container cont = getContentPane();
		cont.add(enemyList);
		cont.add(enemyLevel);
		cont.add(submit);

		submit.setText("Submit");

		for (EnemyType eType : Globals.enemyTypes) {
			enemyList.addItem(object(eType.getName()));
		}

		enemyList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				selectedEnemy.setType(Globals.enemyTypes.get(enemyList.getSelectedIndex()));
			}
		});

		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					selectedEnemy.setLevel(Integer.parseInt(enemyLevel.getText()));
				} catch (NumberFormatException e1) {
					System.out.println("Invalid number");
				}
			}
		});
	}

	public Enemy getSelectedEnemy() {
		return selectedEnemy;
	}

	public void setEnemy(Enemy e) {
		selectedEnemy = e;

		enemyList.setSelectedIndex(Globals.enemyTypes.indexOf(selectedEnemy.getType()));
		enemyLevel.setText(Integer.toString(selectedEnemy.getLevel()));
	}

	private Object object(final String item) {
		return new Object() {
			public String toString() {
				return item;
			}
		};
	}
}
