package ca.viaware.rpg.mapeditor;

import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

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

	int delta;
	int dCount = 0;
	boolean prevPressed = false;
	public static List<TileTexture> textures = new ArrayList(16);
	ToolBox tools;
	EnemyEditor enemyEditor;
	int selectedTile = 0;

	public MapEditor() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		tools = new ToolBox();
		setupDisplay();
		setupGL();
		loadTextures();
		tools.updateTextures();
		enemyEditor = new EnemyEditor();
		enemyEditor.createEditor();

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
				if (Globals.isSaved) {
					System.out.println("Saved");
					Globals.isRunning = false;
				} else {
					menu.setVisible(true);
					if (JOptionPane.showConfirmDialog(null, "Exit without saving?", "Quit", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						Globals.isRunning = false;
					}
				}
			}
		}

		System.out.println("Quitting...");
		Display.destroy();
		System.exit(0);
	}

	public void loadTextures() {
		TextureHandler th = new TextureHandler();
		textures.add(new TileTexture(th.loadTexture("grass1"), "Grass 1"));
		textures.add(new TileTexture(th.loadTexture("cobble"), "Cobble"));
		textures.add(new TileTexture(th.loadTexture("flower red"), "Red flower"));
		textures.add(new TileTexture(th.loadTexture("flower"), "Blue flower"));
		textures.add(new TileTexture(th.loadTexture("palm"), "Palm Tree"));
		textures.add(new TileTexture(th.loadTexture("sand"), "Sand"));
		textures.add(new TileTexture(th.loadTexture("tree"), "Tree"));
		textures.add(new TileTexture(th.loadTexture("alien cactus plant 1"), "Alien cactus right"));
		textures.add(new TileTexture(th.loadTexture("alien cactus plant 2"), "Alien cactus left"));
		textures.add(new TileTexture(th.loadTexture("double alien cactus plant"), "Double alien cactus"));
		textures.add(new TileTexture(th.loadTexture("red rock"), "Red rock"));
		textures.add(new TileTexture(th.loadTexture("sign"), "Sign"));
		textures.add(new TileTexture(th.loadTexture("dirt"), "Dirt"));

		Globals.enemyTypes.add(new EnemyType(th.loadTexture("thumb/SlimeNormal"), "Normal Slime"));
		Globals.enemyTypes.add(new EnemyType(th.loadTexture("thumb/SlimeFast"), "Fast Slime"));
		Globals.enemyTypes.add(new EnemyType(th.loadTexture("thumb/SlimeWeak"), "Weak Slime"));
		Globals.enemyTypes.add(new EnemyType(th.loadTexture("thumb/UFO"), "UFO"));

		Globals.otherTextures.add(th.loadTexture("img/waypointOUT"));
		Globals.otherTextures.add(th.loadTexture("img/waypointIN"));
	}

	private void changeMade() {
		Globals.isSaved = false;
	}

	public void logic() {
		int mX = mouseX();
		int mY = mouseY();
		int shiftDir = 0;
		int cSelected = Globals.cSelected;

		Rectangle rect = new Rectangle(mX, mY, 1, 1);

		if (!Keyboard.isKeyDown(Keyboard.KEY_S) && !Keyboard.isKeyDown(Keyboard.KEY_W) && !Keyboard.isKeyDown(Keyboard.KEY_A) && !Keyboard.isKeyDown(Keyboard.KEY_D)) {
			dCount = 1001;
			prevPressed = false;
		}
		// System.out.println(dCount);

		if (dCount > 150 || !prevPressed) {
			dCount = 0;
			if (Keyboard.isKeyDown(Keyboard.KEY_W) && Globals.yOffset > 0) {
				shiftDir = 1;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
				shiftDir = 2;
			}

			if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
				shiftDir = 3;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_A) && Globals.xOffset > 0) {
				shiftDir = 4;
			}

			switch (shiftDir) {
			case 1:
				Globals.yOffset--;
				prevPressed = true;
				break;
			case 2:
				Globals.yOffset++;
				prevPressed = true;
				break;
			case 3:
				Globals.xOffset++;
				prevPressed = true;
				break;
			case 4:
				Globals.xOffset--;
				prevPressed = true;
				break;
			}

		} else {
			dCount += delta;
		}

		int csOld = cSelected;

		cSelected += Mouse.getDWheel() / 120;

		if (csOld != cSelected) {
			tools.changed();
		}

			if (cSelected > (textures.size() - 1)) {
				cSelected = 0;
			}
		if (cSelected < 0) {
			cSelected = 0;
		}

		Globals.cSelected = cSelected;
		tools.updateSelected();

		switch (Globals.selectedTool) {

		case PAINT:
			if (Mouse.isButtonDown(1) && !Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
				boolean d = false;
				for (Tile tile : tiles) {
					if (tile.isTouching(rect)) {
						d = true;
					}
				}

				if (!d) {
					changeMade();
					tiles.add(new Tile(mX - mX % 64, mY - mY % 64, cSelected));
				}
			}

			if (Mouse.isButtonDown(1) && Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
				for (Tile tile : tiles) {
					if (tile.isTouching(rect)) {
						changeMade();
						tile.addSecondLayer(cSelected);
					}
				}
			}
			break;

		case ERASE:
			if (Mouse.isButtonDown(1) && !Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
				int toRemove = 0;
				boolean isRemoving = false;
				for (Tile tile : tiles) {
					if (tile.isTouching(rect)) {
						toRemove = tiles.indexOf(tile);
						isRemoving = true;
					}
				}

				if (isRemoving) {
					changeMade();
					tiles.remove(toRemove);
				}
			}

			if (Mouse.isButtonDown(1) && Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
				for (Tile tile : tiles) {
					if (tile.isTouching(rect)) {
						changeMade();
						tile.removeSecondLayer();
					}
				}
			}
			break;

		case COLLISION:
			if (Mouse.isButtonDown(1) && !Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
				for (Tile tile : tiles) {
					if (tile.isTouching(rect)) {
						tile.setCollision(true);
					}
				}
			} else if (Mouse.isButtonDown(1) && Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
				for (Tile tile : tiles) {
					if (tile.isTouching(rect)) {
						tile.setCollision(false);
					}
				}
			}
			break;
		case MARKERS:
			boolean zKey = Keyboard.isKeyDown(Keyboard.KEY_Z);

			if (Mouse.isButtonDown(1) && zKey) {
				System.out.println("In");
				for (Tile tile : tiles) {
					if (tile.isTouching(rect)) {
						tile.addMarker(new Waypoint());
						tile.setToolSelected(true);
					}
				}

			}

			if (Mouse.isButtonDown(1) && Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
				for (Tile tile : tiles) {
					if (tile.isTouching(rect)) {
						tile.removeMarker();
					}
				}
			}

			break;
		case ENEMIES:

			if (Mouse.isButtonDown(1) && !Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {

				for (Tile tile : tiles) {
					if (tile.isTouching(rect)) {
						tile.addEnemy(new Enemy(Globals.enemyTypes.get(0), 1));
					}
				}
			}

			if (Mouse.isButtonDown(1) && Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
				for (Tile tile : tiles) {
					if (tile.isTouching(rect)) {
						tile.deleteEnemy();
					}
				}
			}
			break;
		}
		Globals.waypoints.clear();
		for (Tile tile : tiles) {
			if (tile.isTeleMarkerIn() || tile.isTeleMarkerOut()) {
				tile.refreshMarker();
			}
		}

		int count = 0;
		int count2 = 0;
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

			if (tile.isTouching(rect) && Mouse.isButtonDown(0) && Keyboard.isKeyDown(Keyboard.KEY_SPACE) && !tile.isToolSelected()) {
				tile.setToolSelected(true);
				selectedTile = count2;
			}
			count2++;
		}

		count2 = 0;
		Globals.enemies.clear();
		for (Tile tile : tiles) {
			if (tile.isSelected()) {
				changeMade();
				tile.setX(mX - mX % 64);
				tile.setY(mY - mY % 64);
			}
			if (count2 == selectedTile) {
				tile.setToolSelected(true);
			} else {
				tile.setToolSelected(false);
			}
			count2++;

			if (tile.isToolSelected()) {
				tools.updateTile(tile.getID(), tile.getLayer2ID(), tile.is2Layers(), tile.checkColision(), tiles.indexOf(tile));
				boolean changed = false;
				if (!tools.getSelected().equals(tile)) {
					changed = true;
				}
				tools.setSelectedTile(tile);

				if (tile.isTeleMarkerIn() || tile.isTeleMarkerOut()) {
					tools.updateMarkerData(true, changed);
				} else {
					tools.updateMarkerData(false, changed);
				}

				if (tile.isEnemy() && changed) {
					enemyEditor.setVisible(false);
					enemyEditor = new EnemyEditor();
					enemyEditor.createEditor();
					enemyEditor.setVisible(true);
					enemyEditor.setEnemy(tile.getEnemy());
				} else if (tile.isEnemy() && !changed && tile.equals(tile)) {
					tile.addEnemy(enemyEditor.getSelectedEnemy());
				} else {
					enemyEditor.setVisible(false);
				}

			}
			tile.refreshEnemy();
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