package ca.viaware.rpg.map;




import ca.viaware.rpg.game.Globals;
import ca.viaware.rpg.utilities.TextRenderer;


public class Map {
	public int xSize, ySize;
	private double xOffset, yOffset;
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
				mapTiles[countx][county].render(countx, county, (int)xOffset, (int)yOffset);
					Globals.temp++;
				} else {
				}
				
				countx++;
			}
			countx = 0;
			county++;
			
		}

		TextRenderer r = new TextRenderer(100,100, 100, 0, "res/text/fonts.png", "Hello how are you?");
		r.update();
		
		
// enabled removal of tiles worked on text renderer
	}
	
	public void moveMap(double x, double y, int delta){
		xOffset += x * delta;
		yOffset += y * delta;
		
	}
	public int getXOffset(){
		return (int)xOffset;
	}
	public int getYOffset(){
		return (int)yOffset;
	}
}
