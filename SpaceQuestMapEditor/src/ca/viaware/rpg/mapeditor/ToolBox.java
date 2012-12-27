package ca.viaware.rpg.mapeditor;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ToolBox extends JFrame {
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
	private JTextField markerName = new JTextField();
	private JTextField markerMapPointer = new JTextField();
	private JTextField markerNamePointer = new JTextField();
	private JTextField markerType = new JTextField();
	private JButton markerSubmit = new JButton();
	private Tile selectedTile = new Tile(0, 0, 0, false);
	private boolean isAnim = false;

	private boolean changed = false;

	private List<Object> objects = new ArrayList<Object>(16);

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

		cont.add(markerType);
		cont.add(markerName);
		cont.add(markerMapPointer);
		cont.add(markerNamePointer);
		cont.add(markerSubmit);

		markerType.setVisible(false);
		markerName.setVisible(false);
		markerMapPointer.setVisible(false);
		markerNamePointer.setVisible(false);
		markerSubmit.setVisible(false);
		markerSubmit.setText("Submit");

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
				Globals.selectedTool = Globals.brush.ENEMIES;
			}
		});

		markerSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (Integer.parseInt(markerType.getText()) == 1 || Integer.parseInt(markerType.getText()) == 2) {
						selectedTile.updateMarker(Integer.parseInt(markerType.getText()), markerName.getText(), markerNamePointer.getText(), markerMapPointer.getText());
					} else {
						System.out.println("Invalid data");
					}
				} catch (NumberFormatException e1) {
					System.out.println("Invalid data");
				}
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

	public Tile getSelected() {
		return selectedTile;
	}

	public void updateMarkerData(boolean vis, boolean changed) {
		if (vis && changed) {
			markerType.setText(Integer.toString(selectedTile.getTeleMarker().getType()));
			markerName.setText(selectedTile.getTeleMarker().getName());
			markerNamePointer.setText(selectedTile.getTeleMarker().getPointTo());
			markerMapPointer.setText(selectedTile.getTeleMarker().getPointToMap());
		}

		markerType.setVisible(vis);
		markerName.setVisible(vis);
		markerMapPointer.setVisible(vis);
		markerNamePointer.setVisible(vis);
		markerSubmit.setVisible(vis);

	}

	public void setSelectedTile(Tile tile) {
		selectedTile = tile;
	}

	public void setPlacingAnim(boolean a) {
		if (a != isAnim) {
			textureSelect.removeAll();
			if (a) {
				for (TileTexture tile : MapEditor.animTextures) {
					textureSelect.addItem(object(tile.getName()));
				}
			} else {
				for (TileTexture tile : MapEditor.textures) {
					textureSelect.addItem(object(tile.getName()));
				}
			}
		}
		isAnim = a;
	}

}
