package ca.viaware.rpg.mapeditor;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ToolBox extends JFrame{
	private JLabel selectedTextureName = new JLabel();
	private JLabel tileTextureName = new JLabel();
	private JLabel tileID = new JLabel();
	
	private JButton paintTool = new JButton();
	private JButton eraseTool = new JButton();
	
	public ToolBox(){
		setTitle("Toolbox");
		setLayout(new GridLayout(15,1));
		setSize(200,500);
		setLocation(0,200);
		setVisible(true);
		
		addComponents();
	}
	
	private void addComponents(){
		Container cont = getContentPane();
		
		cont.add(selectedTextureName);
		cont.add(tileTextureName);
		cont.add(tileID);
		
		cont.add(paintTool);
		cont.add(eraseTool);
		
		paintTool.setText("Paint brush");
		eraseTool.setText("Erase brush");		
		selectedTextureName.setText("Placing Texture: ");
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
	}
	
	public void updateSelectedTexture(String t){
		selectedTextureName.setText("Placing Texture: " + t);
	}
	
	public void updateTile(int ID){
		tileID.setText("Tile ID: " + Integer.toString(ID));
		tileTextureName.setText("Tile Texture: " + MapEditor.textures.get(ID).getName());
	}
	
}
