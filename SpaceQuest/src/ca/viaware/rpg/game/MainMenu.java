package ca.viaware.rpg.game;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2i;

import java.util.Random;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import ca.viaware.rpg.game.Globals.gState;
import ca.viaware.rpg.utilities.ClickListener;
import ca.viaware.rpg.utilities.OnClickListener;
import ca.viaware.rpg.utilities.SButton;
import ca.viaware.rpg.utilities.TextureHandler;
import ca.viaware.rpg.utilities.TexturedQuad;

public class MainMenu {
	private double UFOtrans = 1;
	private int movementstage = 0;
	Texture[] backGround = new Texture[3];
	SButton start, options, help, quit;
	TexturedQuad UFO;
	private int location = 0;
	private boolean startclicked = false;

	private double sinX = 0;
	private double sinY = 0;

	public MainMenu() {
		TextureHandler t = new TextureHandler();
		String s = "other/main menu screen000";
		for (int i = 0; i < 3; i++) {
			backGround[i] = t.loadSprite(s + (i + 1));
		}

		UFO = new TexturedQuad(450, 450, 450, 250, 0, "res/sprites/other/main ufo.png");
		start = new SButton(100, 100, 200, 70);
		options = new SButton(80, 200, 200, 70);
		help = new SButton(80, 300, 200, 70);
		quit = new SButton(100, 400, 200, 70);
		start.setText("Start");
		help.setText("Help");
		quit.setText("Quit");
		options.setText("Options");
	}

	public void render() {
		// this is for UFO movement up and down

		sinX = sinX + 0.025;
		sinY = Math.sin(sinX);
		UFO.move(0, sinY);

		backGround[location].bind();
		location++;
		if (location == 3) {
			location = 0;
		}

		glBegin(GL_QUADS);

		glTexCoord2f(0f, 0f);
		glVertex2i(0, 0);
		glTexCoord2f(1f, 0f);
		glVertex2i(Globals.dispWidth, 0);
		glTexCoord2f(1f, 1f);
		glVertex2i(Globals.dispWidth, Globals.dispHeight);
		glTexCoord2f(0f, 1f);
		glVertex2i(0, Globals.dispHeight);
		glEnd();
		GL11.glColor4d(1, 1, 1, 1);
		quit.updateOnClickListener(new OnClickListener(quit));
		quit.AddOnClickListener(new ClickListener() {

			@Override
			public void ClickListener(OnClickListener OCL) {
				Globals.isRunning = false;

			}
		});

		start.updateOnClickListener(new OnClickListener(start));
		start.AddOnClickListener(new ClickListener() {

			@Override
			public void ClickListener(OnClickListener OCL) {
				startclicked = true;
				

			}

		});

		if (startclicked) {
			transition(Globals.gState.GAME);
		}
		Globals.buttonBuffer.add(quit);
		Globals.buttonBuffer.add(help);
		Globals.buttonBuffer.add(start);
		Globals.buttonBuffer.add(options);
		UFO.settrans(UFOtrans);
		UFO.update();

	}

	private void transition(final gState g) {

		start.move(-2, 0);
		quit.move(-2, 0);
		options.move(-2, 0);
		help.move(-2, 0);
		UFOtrans -= 0.01;
		
		UFO.move(1.5, -1);
		
		UFO.setSize(UFO.getXh() * 2 - 5, UFO.getYh() * 2 - 6);
		UFO.rotate(0.2);

		if (start.getX() < -350) {

			Globals.gameState = g;

		}

	}

}
