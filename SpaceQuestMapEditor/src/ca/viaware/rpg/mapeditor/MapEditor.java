package ca.viaware.rpg.mapeditor;

import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.*;
import org.lwjgl.opengl.*;
import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;

public class MapEditor {

	public static List<Tile> tiles = new ArrayList<Tile>(16);
	private boolean tileSelected = false;
	private long lasttime = getTime();
	private int cSelected = 0;
	int delta;
	int dCount = 0;
	boolean prevPressed = false;
	public static List<TileTexture> textures = new ArrayList(16);

	public MapEditor() {
		setupDisplay();
		setupGL();
		loadTextures();

		Menu menu = new Menu();
		menu.showMenu();

		tiles.add(new Tile(0, 0, 0));

		while (Globals.isRunning) {
			delta = getDelta();

			render();
			logic();

			Display.update();
			Display.sync(60);

			if (Display.isCloseRequested()) {
				Globals.isRunning = false;
			}
		}

		System.out.println("Quitting...");
		Display.destroy();
		System.exit(0);
	}

	public void loadTextures(){
		TextureHandler th = new TextureHandler();
		
		textures.add(new TileTexture(th.loadTexture("grass1"), "Grass 1"));
		textures.add(new TileTexture(th.loadTexture("cobble"), "Cobble"));
		textures.add(new TileTexture(th.loadTexture("flower red"), "Red flower"));
		textures.add(new TileTexture(th.loadTexture("flower"), "Blue flower"));
		textures.add(new TileTexture(th.loadTexture("palm"), "Palm Tree"));
		textures.add(new TileTexture(th.loadTexture("sand"), "Sand"));
		textures.add(new TileTexture(th.loadTexture("tree"), "Tree"));

	}
	
	public void logic() {
		int mX = mouseX();
		int mY = mouseY();
		int shiftDir = 0;

		Rectangle rect = new Rectangle(mX, mY, 1, 1);

		if (!Keyboard.isKeyDown(Keyboard.KEY_DOWN) && !Keyboard.isKeyDown(Keyboard.KEY_UP) && !Keyboard.isKeyDown(Keyboard.KEY_LEFT) && !Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			dCount = 1001;
			prevPressed = false;
		}
		// System.out.println(dCount);

		if (dCount > 200 || !prevPressed) {
			dCount = 0;
			if (Keyboard.isKeyDown(Keyboard.KEY_UP) && Globals.yOffset > 0) {
				Globals.yOffset--;
				shiftDir = 1;
				prevPressed = true;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
				Globals.yOffset++;
				shiftDir = 2;
				prevPressed = true;
			}

			if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
				Globals.xOffset++;
				shiftDir = 3;
				prevPressed = true;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_LEFT) && Globals.xOffset > 0) {
				Globals.xOffset--;
				shiftDir = 4;
				prevPressed = true;
			}

		} else {
			dCount += delta;
		}
		
		
		cSelected += Mouse.getDWheel()/120;
		
		if(cSelected > (textures.size() - 1) ){
			cSelected = 0;
		}
		if(cSelected < 0 ){
			cSelected = 0;
		}
		

		
		if (Mouse.isButtonDown(1)) {
			boolean d = false;
			for (Tile tile : tiles){
				if (tile.isTouching(rect)){
					d = true;
				}
			}
			
			if (!d){
			tiles.add(new Tile(mX - mX % 64, mY - mY % 64, cSelected));
			}
		}
		
		int count = 0;

		for (Tile tile : tiles) {

			if (tile.isTouching(rect) && Mouse.isButtonDown(0) && !tileSelected) {
				if (tileSelected) {
					System.out.println("Selected flag true");
				} else {
					System.out.println("Selected flag false");
				}

				tileSelected = true;
				tile.setSelected(true);
				count++;
				System.out.println(count + " tiles selected");
			} else if (tile.isSelected() && Mouse.isButtonDown(0)) {
				tileSelected = true;
			} else if (tile.isSelected() && !Mouse.isButtonDown(0) && tileSelected) {
				tile.setSelected(false);
				tileSelected = false;
			} else {

			}

		}

		for (Tile tile : tiles) {
			if (tile.isSelected()) {
				tile.setX(mX - mX % 64);
				tile.setY(mY - mY % 64);
			}
		}

		// System.out.println("ShiftDir: " + shiftDir);

		for (Tile tile : tiles) {
			if (shiftDir == 1) {
				tile.shiftDOWN();
			}

			if (shiftDir == 2) {
				tile.shiftUP();
			}

			if (shiftDir == 3) {
				tile.shiftLEFT();
			}

			if (shiftDir == 4) {
				tile.shiftRIGHT();
			}
		}
	}

	public void render() {
		glClear(GL_COLOR_BUFFER_BIT);

		for (Tile tile : tiles) {
			tile.renderTile();
		}
		GL11.glColor3d(87, 87, 87);
		for (int y = 0; y < Globals.HEIGHT; y += 64) {

			for (int x = 0; x < Globals.WIDTH; x += 64) {
				glBegin(GL_LINE_STRIP);
					glVertex2i(x, 0);
					glVertex2i(x, Globals.HEIGHT);
				glEnd();

				glBegin(GL_LINE_STRIP);
					glVertex2i(0, y);
					glVertex2i(Globals.WIDTH, y);
				glEnd();
			}
		}
	}

	private int mouseX() {
		return Mouse.getX();
	}

	private int mouseY() {
		return Globals.HEIGHT - Mouse.getY() - 1;
	}

	private int getDelta() {
		long time = getTime();
		int delta = (int) (time - lasttime);
		lasttime = time;
		return delta;
	}

	private long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	public void setupDisplay() {
		try {
			Display.setDisplayMode(new DisplayMode(Globals.WIDTH, Globals.HEIGHT));
			Display.setTitle("SpaceQuest Game Editor");
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}

	public void setupGL() {
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, Globals.WIDTH, Globals.HEIGHT, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
	}

	public static void main(String[] args) {
		new MapEditor();
	}
}
