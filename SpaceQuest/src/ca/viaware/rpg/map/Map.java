package ca.viaware.rpg.map;

import org.lwjgl.*;
import org.lwjgl.opengl.*;

import ca.viaware.rpg.game.Globals;
import static org.lwjgl.opengl.GL11.*;

public class Map {
	public int xSize, ySize;
	public int xOffset, yOffset;
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
	}
	
	public void moveMap(double x, double y, int delta){
		xOffset += x * delta;
		yOffset += y * delta;
	}
	
}
