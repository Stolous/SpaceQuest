package ca.viaware.rpg.game;

import org.lwjgl.opengl.*;

import ca.viaware.rpg.entity.Enemy;
import ca.viaware.rpg.map.Loader;
import ca.viaware.rpg.map.Map;
import ca.viaware.rpg.utilities.Button;
import ca.viaware.rpg.utilities.TextRenderer;
import static org.lwjgl.opengl.GL11.*;

public class Renderer {

	public void render() {
		glClear(GL_COLOR_BUFFER_BIT);
		switch (Globals.gameState) {
		case INTRO:
			// Intro screen - now working!
			Globals.startImg.bind();
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

			break;
		case MAIN_MENU:

			Globals.gameState = Globals.gState.GAME;
			new Menu();
			break;
		case GAME:

			Globals.gameMap.renderMap();
			Globals.playerEntity.draw();
			for (Enemy enemy : Globals.enemies) {
				enemy.draw();
			}
			Globals.healthBar.update();
			Globals.ammoBar.update();

			break;
		}

		for (Button b : Globals.buttonBuffer) {
			b.draw();
		}
		Globals.buttonBuffer.clear();

		for (TextRenderer t : Globals.textRendererBufferList) {
			t.renderBuffer();
		}
		Globals.textRendererBufferList.clear();

		Globals.cursor.draw();
	}
}
