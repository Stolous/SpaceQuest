package ca.viaware.rpg.map;

import static org.lwjgl.opengl.GL11.*;

import java.util.List;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import ca.viaware.rpg.entity.AbstractMoveableEntity;
import ca.viaware.rpg.entity.Enemy;
import ca.viaware.rpg.game.Globals;
import ca.viaware.rpg.utilities.EnemyHandler;

public class Tile extends AbstractMoveableEntity {

	private Texture tex = null;
	private boolean enabled = false;
	private int ID = 0;
	private int l2ID = 0;
	private boolean hasLayer2 = false;
	private boolean isCollision = false;
	private boolean teleMarkerOut = false, teleMarkerIn = false, hasTeleMarker = false;
	private TeleMarker teleMarker;
	private int teleMarkerID;
	int actX, actY;
	private boolean isEnemy;
	public int enemyID = 0;

	public Tile(double n1, double n2, double n3, double n4, boolean enabled, String data, int tileX, int tileY) {
		super(n1, n2, n3, n4);
		this.enabled = enabled;
		if (data.equals("N")) {
			ID = -1;
		}
		if (enabled) {
			parseInputData(data);
		}

		actX = tileX * 64;
		actY = tileY * 64;
	}

	public int getBX() {
		return actX / 64;
	}

	public int getBY() {
		return actY / 64;
	}

	public void setTexture(Texture t) {
		tex = t;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public Texture getTexture() {
		return tex;
	}

	public void render(int cx, int cy, int xOff, int yOff) {
		x = cx * 64 + xOff;
		y = cy * 64 + yOff;

		if (enabled) {
			Globals.tileTextures.get(ID).bind();
		}

		glBegin(GL_QUADS);
		glTexCoord2f(0f, 0f);
		glVertex2i(cx * 64 + xOff, cy * 64 + yOff);
		glTexCoord2f(1f, 0f);
		glVertex2i(cx * 64 + xOff + 64, cy * 64 + yOff);
		glTexCoord2f(1f, 1f);
		glVertex2i(cx * 64 + xOff + 64, cy * 64 + yOff + 64);
		glTexCoord2f(0f, 1f);
		glVertex2i(cx * 64 + xOff, cy * 64 + yOff + 64);
		glEnd();

		if (hasLayer2) {
			Globals.tileTextures.get(l2ID).bind();
			glBegin(GL_QUADS);
			glTexCoord2f(0f, 0f);
			glVertex2i(cx * 64 + xOff, cy * 64 + yOff);
			glTexCoord2f(1f, 0f);
			glVertex2i(cx * 64 + xOff + 64, cy * 64 + yOff);
			glTexCoord2f(1f, 1f);
			glVertex2i(cx * 64 + xOff + 64, cy * 64 + yOff + 64);
			glTexCoord2f(0f, 1f);
			glVertex2i(cx * 64 + xOff, cy * 64 + yOff + 64);
			glEnd();
		}
	}

	public void setID(int id) {
		if (id == -1) {
			enabled = false;
		} else {
			ID = id;
			enabled = true;
		}
		if (enabled) {
			setTexture(Globals.tileTextures.get(ID));
		}
	}

	public void setLayer2ID(int l2) {
		l2ID = l2;
	}

	public void setLayer2(boolean l2) {
		hasLayer2 = l2;
	}

	public void parseInputData(String d) {
		String[] data = d.split("/");

		setID(Integer.parseInt(data[0]));

		if (data[1].equals("N")) {
			setLayer2ID(0);
			setLayer2(false);
		} else {
			setLayer2ID(Integer.parseInt(data[1]));
			setLayer2(true);
		}

		if (data[2].equals("C")) {
			isCollision = true;
		} else {
			isCollision = false;
		}

		if (data[3].equals("N")) {
			teleMarkerOut = false;
			teleMarkerIn = false;
			hasTeleMarker = false;
		} else {
			teleMarkerID = Integer.parseInt(data[3]);
			hasTeleMarker = true;
		}
		
		if (data[4].equals("N")){
		
		}else{
			isEnemy = true;
			enemyID = Integer.parseInt(data[4]);
		}
	}

	public boolean hasCollision() {
		return isCollision;
	}

	@Override
	public void draw() {

	}

	public void addTeleMarker(TeleMarker tele) {
		teleMarker = tele;

		if (teleMarker.getType() == 1) {
			teleMarkerIn = true;
			teleMarkerOut = false;
		} else if (teleMarker.getType() == 2) {
			teleMarkerIn = false;
			teleMarkerOut = true;
		}
	}

	public boolean hasTeleMarkerOut() {
		return teleMarkerOut;
	}

	public boolean hasTeleMarkerIn() {
		return teleMarkerIn;
	}

	public TeleMarker getTeleMarker() {
		return teleMarker;
	}

	public void updateTeleMarkers() {
		if (hasTeleMarker) {
			teleMarker = Globals.teleMarkers.get(teleMarkerID);
			if (teleMarker.getType() == 1) {
				teleMarkerIn = true;
				teleMarkerOut = false;
			} else {
				teleMarkerIn = false;
				teleMarkerOut = true;
			}
		}
	}
	
	public boolean isEnemy(){
		return isEnemy;
	}
	
	public void updateEnemies(int x, int y, List<String> enemyList){
		if (isEnemy){
			String[] data = enemyList.get(enemyID).split("/");
			
			Enemy e = EnemyHandler.handleEnemy(x * 64, y * 64, Integer.parseInt(data[0]), Integer.parseInt(data[1]));
			
			Globals.enemies.add(e);
		}
	}
}
