package ca.viaware.rpg.mapeditor;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Rectangle;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

public class Tile {
	private int x = 0, y = 0, actX = 0, actY = 0;
	private int ID = 0;
	private int l2ID = 0;
	private boolean is2Layers = false;
	private boolean selected = false;
	private boolean toolSelected = false;
	private boolean isCollision = false;
	private boolean isTeleMarkerIn = false, isTeleMarkerOut = false, hasMarker = false;
	private boolean isEnemy = false;
	private Enemy enemy;
	private Waypoint teleMarker;
	private int teleMarkerID = 0;
	private int enemyID = 0;

	public Tile(int x, int y, int ID) {
		this.x = x;
		this.y = y;
		this.ID = ID;

		actX = x / 64 + Globals.xOffset;
		actY = y / 64 + Globals.yOffset;
	}

	public void passData(String data, int x, int y) {
		actX = x;
		actY = y;

		String[] data2 = data.split("/");

		ID = Integer.parseInt(data2[0]);

		System.out.println("ID is now " + ID);

		if (data2[1].equals("N")) {
			is2Layers = false;
			l2ID = 0;
		} else {
			is2Layers = true;
			l2ID = Integer.parseInt(data2[1]);
		}

		if (data2[2].equals("C")) {
			isCollision = true;
		} else {
			isCollision = false;
		}

		if (data2[3].equals("N")) {
			isTeleMarkerIn = false;
			isTeleMarkerOut = false;
		} else {
			System.out.println("TILE HAS MARKER");
			hasMarker = true;
			teleMarkerID = Integer.parseInt(data2[3]);
		}
		
		if (data2[4].equals("N")){
			isEnemy = false;
		}else{
			isEnemy = true;
			enemyID = Integer.parseInt(data2[4]);
		}
	}

	public void finishLoading() {
		if (hasMarker) {
			teleMarker = Globals.waypoints.get(teleMarkerID);

			if (teleMarker.getType() == 1) {
				isTeleMarkerIn = true;
			} else {
				isTeleMarkerOut = true;
			}
		}

		if (isEnemy) {
			enemy = Globals.enemies.get(enemyID);
		}
	}

	public void renderTile() {
		glEnable(GL_TEXTURE_2D);

		MapEditor.textures.get(ID).getTexture().bind();
		
		glBegin(GL_QUADS);
		glTexCoord2f(0f, 0f);
		glVertex2i(x, y);
		glTexCoord2f(1f, 0f);
		glVertex2i(x + 64, y);
		glTexCoord2f(1f, 1f);
		glVertex2i(x + 64, y + 64);
		glTexCoord2f(0f, 1f);
		glVertex2i(x, y + 64);
		glEnd();
		glDisable(GL_TEXTURE_2D);

		if (is2Layers) {
			glEnable(GL_TEXTURE_2D);

			MapEditor.textures.get(l2ID).getTexture().bind();

			glBegin(GL_QUADS);
			glTexCoord2f(0f, 0f);
			glVertex2i(x, y);
			glTexCoord2f(1f, 0f);
			glVertex2i(x + 64, y);
			glTexCoord2f(1f, 1f);
			glVertex2i(x + 64, y + 64);
			glTexCoord2f(0f, 1f);
			glVertex2i(x, y + 64);
			glEnd();
			glDisable(GL_TEXTURE_2D);
		}

		if (toolSelected) {
			glBegin(GL_LINE_STRIP);
			glVertex2i(x + 1, y + 1);
			glVertex2i(x + 63, y + 1);
			glVertex2i(x + 63, y + 63);
			glVertex2i(x + 1, y + 63);
			glVertex2i(x + 1, y + 1);
			glVertex2i(x + 2, y + 2);
			glVertex2i(x + 62, y + 2);
			glVertex2i(x + 62, y + 62);
			glVertex2i(x + 2, y + 62);
			glVertex2i(x + 2, y + 2);
			glEnd();
		}

		if (isCollision) {
			glBegin(GL_LINE_STRIP);
			glVertex2i(x, y);
			glVertex2i(x + 64, y + 64);
			glEnd();

			glBegin(GL_LINE_STRIP);
			glVertex2i(x + 64, y);
			glVertex2i(x, y + 64);
			glEnd();
		}

		if (isTeleMarkerOut) {
			Globals.otherTextures.get(0).bind();
			glBegin(GL_QUADS);
			glTexCoord2f(0f, 0f);
			glVertex2i(x, y);
			glTexCoord2f(1f, 0f);
			glVertex2i(x + 64, y);
			glTexCoord2f(1f, 1f);
			glVertex2i(x + 64, y + 64);
			glTexCoord2f(0f, 1f);
			glVertex2i(x, y + 64);
			glEnd();
		}
		if (isTeleMarkerIn) {
			Globals.otherTextures.get(1).bind();
			glBegin(GL_QUADS);
			glTexCoord2f(0f, 0f);
			glVertex2i(x, y);
			glTexCoord2f(1f, 0f);
			glVertex2i(x + 64, y);
			glTexCoord2f(1f, 1f);
			glVertex2i(x + 64, y + 64);
			glTexCoord2f(0f, 1f);
			glVertex2i(x, y + 64);
			glEnd();
		}

		if (isEnemy) {
			Globals.enemyTypes.get(Globals.enemyTypes.indexOf(enemy.getType())).getTexture().bind();
			glBegin(GL_QUADS);
			glTexCoord2f(0f, 0f);
			glVertex2i(x, y);
			glTexCoord2f(1f, 0f);
			glVertex2i(x + 64, y);
			glTexCoord2f(1f, 1f);
			glVertex2i(x + 64, y + 64);
			glTexCoord2f(0f, 1f);
			glVertex2i(x, y + 64);
			glEnd();

		}

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getActX() {
		return actX;
	}

	public int getActY() {
		return actY;
	}

	public int getID() {
		return ID;
	}

	public void setX(int x) {
		if (x > this.x) {
			actX++;
		} else if (x < this.x) {
			actX--;
		}

		System.out.println(actX + ", " + actY);
		this.x = x;
	}

	public void setY(int y) {
		if (y > this.y) {
			actY++;
		} else if (y < this.y) {
			actY--;
		}

		System.out.println(actX + ", " + actY);
		this.y = y;
	}

	public void shiftUP() {
		y = y - 64;
	}

	public void shiftDOWN() {
		y = y + 64;
	}

	public void shiftLEFT() {
		x = x - 64;
	}

	public void shiftRIGHT() {
		x = x + 64;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public boolean isTouching(Rectangle rect) {
		boolean touching = false;
		Rectangle thisRect = new Rectangle(x, y, 64, 64);

		if (thisRect.intersects(rect)) {
			touching = true;
		}

		return touching;
	}

	public void setSelected(boolean s) {
		selected = s;
	}

	public boolean isSelected() {
		return selected;
	}

	public String getDataString() {
		String data = "";
		data = Integer.toString(ID);

		if (is2Layers) {
			System.out.println("Has 2 layers, adding layer 2 data");
			data = data + "/" + Integer.toString(l2ID);
		} else {
			System.out.println("Does not have 2 layers, adding null data");
			data = data + "/N";
		}

		if (isCollision) {
			System.out.println("Has collision, adding collision data");
			data = data + "/C";
		} else {
			System.out.println("Does not have collision, adding null data");
			data = data + "/N";
		}

		if (isTeleMarkerOut || isTeleMarkerIn) {
			System.out.println("Has a telemarker, adding telemarker data");
			data = data + "/" + Integer.toString(Globals.waypoints.indexOf(teleMarker));
		} else {
			System.out.println("Is not a telemarker, adding null data");
			data = data + "/N";
		}

		if (isEnemy) {
			System.out.println("Has enemy, adding enemy data");
			data = data + "/" + Integer.toString(Globals.enemies.indexOf(enemy));
		} else {
			System.out.println("Does not have enemy, adding null data");
			data = data + "/N";
		}
		
		data = data + "&";
		System.out.println("Finished producing data string, returning " + data);
		return data;
	}

	public void setToolSelected(boolean s) {
		toolSelected = s;
	}

	public boolean isToolSelected() {
		return toolSelected;
	}

	public void addSecondLayer(int ID) {
		l2ID = ID;
		is2Layers = true;
	}

	public void removeSecondLayer() {
		l2ID = 0;
		is2Layers = false;
	}

	public boolean is2Layers() {
		return is2Layers;
	}

	public int getLayer2ID() {
		return l2ID;
	}

	public void setCollision(boolean c) {
		isCollision = c;
	}

	public boolean checkColision() {
		return isCollision;
	}

	public void addMarker(Waypoint w) {

		if (!hasMarker) {
			w.setName("Marker Name");
			w.setPointTo("Marker PointTo");
			w.setPointToMap("Marker Map PointTo");
			teleMarker = w;
			Globals.waypoints.add(w);
			isTeleMarkerOut = true;
		}
		
		hasMarker = true;
	}

	public Waypoint getTeleMarker() {
		return teleMarker;
	}

	public void updateMarker(int Type, String name, String pTo, String pToMap) {
		if (Type == 1) {
			isTeleMarkerOut = false;
			isTeleMarkerIn = true;
		} else if (Type == 2) {
			isTeleMarkerOut = true;
			isTeleMarkerIn = false;
		}
		teleMarker.setType(Type);
		teleMarker.setName(name);
		teleMarker.setPointTo(pTo);
		teleMarker.setPointToMap(pToMap);
	}

	public void removeMarker() {
		isTeleMarkerIn = false;
		isTeleMarkerOut = false;
		hasMarker = false;
	}

	public void refreshMarker() {
		Globals.waypoints.add(teleMarker);
	}

	public boolean isTeleMarkerOut() {
		return isTeleMarkerOut;
	}

	public boolean isTeleMarkerIn() {
		return isTeleMarkerIn;
	}

	public void addEnemy(Enemy e) {
		enemy = e;
		isEnemy = true;
	}

	public void deleteEnemy() {
		isEnemy = false;
	}

	public void refreshEnemy() {
		if (isEnemy){
			Globals.enemies.add(enemy);
			enemyID = Globals.enemies.indexOf(enemy);
		}
	}

	public boolean isEnemy() {
		return isEnemy;
	}

	private void setEnemy(Enemy e) {
		enemy = e;
	}

	public Enemy getEnemy() {
		return enemy;
	}
	
}
