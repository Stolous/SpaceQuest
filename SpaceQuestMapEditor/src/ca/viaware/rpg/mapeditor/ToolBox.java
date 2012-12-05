package ca.viaware.rpg.mapeditor;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ToolBox extends JFrame {
	private JLabel selectedTextureName = new JLabel();
	private JLabel tileTextureName = new JLabel();
	private JLabel tileID = new JLabel();
	private JLabel tileLayer2ID = new JLabel();
	private JLabel tileLayer2Name = new JLabel();
	private JLabel hasCollision = new JLabel();
	private JLabel tileIndex = new JLabel();
	private JComboBox textureSelect = new JComboBox();
	private JButton paintTool = new JButton();
	private JButton eraseTool = new JButton();
	private JButton collisionTool = new JButton();
	private JButton markerTool = new JButton();
	private JButton enemyTool = new JButton();

	private boolean changed = false;

	private List<Object> objects = new ArrayList(16);

	public ToolBox() {
		setTitle("Toolbox");
		setLayout(new GridLayout(18, 1));
		setSize(200, 500);
		setLocation(0, 200);
		setVisible(true);

		addComponents();
	}

	private void addComponents() {
		Container cont = getContentPane();

		cont.add(textureSelect);
		cont.add(tileTextureName);
		cont.add(tileID);
		cont.add(tileLayer2Name);
		cont.add(tileLayer2ID);
		cont.add(tileIndex);
		cont.add(hasCollision);

		cont.add(paintTool);
		cont.add(eraseTool);
		cont.add(collisionTool);
		cont.add(markerTool);
		cont.add(enemyTool);

		collisionTool.setText("Collision brush");
		paintTool.setText("Paint brush");
		eraseTool.setText("Erase brush");
		markerTool.setText("GameMarker brush");
		enemyTool.setText("Enemy brush");
		tileTextureName.setText("Tile Texture: ");
		tileID.setText("Tile ID: ");

		paintTool.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Globals.selectedTool = Globals.brush.PAINT;
			}
		});

		eraseTool.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Globals.selectedTool = Globals.brush.ERASE;
			}
		});

		collisionTool.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Globals.selectedTool = Globals.brush.COLLISION;
			}
		});
		
		markerTool.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Globals.selectedTool = Globals.brush.MARKERS;
			}
		});
		
		enemyTool.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});

	}

	public void updateTile(int ID, int l2ID, boolean l2, boolean collision, int index) {
		tileID.setText("Tile ID: " + Integer.toString(ID));
		tileTextureName.setText("Tile Texture: " + MapEditor.textures.get(ID).getName());
		if (l2) {
			tileLayer2ID.setText("Tile layer 2 ID: " + Integer.toString(l2ID));
			tileLayer2Name.setText("Tile layer 2 Texture: " + MapEditor.textures.get(l2ID).getName());
		} else {
			tileLayer2ID.setText("Tile layer 2 ID: NONE");
			tileLayer2Name.setText("Tile layer 2 Texture: NONE");
		}
		if (collision) {
			hasCollision.setText("Tile has collision: YES");
		} else {
			hasCollision.setText("Tile has collision: NO");
		}
		tileIndex.setText("Tile index: " + Integer.toString(index));

	}

	public void updateSelected() {
		int cSelected = Globals.cSelected;
		// Prevent scrollwheel and combobox from conflicting
		if (changed) {
			textureSelect.setSelectedIndex(cSelected);
		} else {
			cSelected = textureSelect.getSelectedIndex();
		}
		textureSelect.repaint();
		changed = false;
		Globals.cSelected = cSelected;
	}

	public void updateTextures() {
		for (TileTexture t : MapEditor.textures) {
			Object o = object(t.getName());
			objects.add(o);
			textureSelect.addItem(o);
		}
	}

	private Object object(final String item) {
		return new Object() {
			public String toString() {
				return item;
			}
		};
	}

	public void changed() {
		changed = true;
	}

}
