package ca.viaware.rpg.map;

import org.lwjgl.*;
import org.lwjgl.opengl.*;

import ca.viaware.rpg.entities.MeleeEnemy;
import ca.viaware.rpg.game.Globals;
import ca.viaware.rpg.utilities.TextRenderer;
import static org.lwjgl.opengl.GL11.*;

public class Map {
	public int xSize, ySize;
	private int xOffset, yOffset;
	public Tile mapTiles[][];

	public void setSize(int x, int y) {
		xSize = x;
		ySize = y;
		mapTiles = new Tile[x][y];
	}
	
	public void renderMap(){
		int countx = 0;
		int county = 0;
		
		while (county < ySize){
			while (countx < xSize){
				
				if (mapTiles[countx][county].isEnabled()){
				mapTiles[countx][county].render(countx, county, xOffset, yOffset);
					Globals.temp++;
				} else {
				}
				
				countx++;
			}
			countx = 0;
			county++;
		}

		
		TextRenderer r = new TextRenderer(30,100, 100, 0, "res/text/fonts.png", "Seth is jealous of my text rendering abilities!");
		
		
// enabled removal of tiles worked on text renderer
		r.update();
	}
	
	public void moveMap(double x, double y, int delta){
		xOffset += x * delta;
		yOffset += y * delta;
	}
	public int getXOffset(){
		return xOffset;
	}
	public int getYOffset(){
		return yOffset;
	}
}
