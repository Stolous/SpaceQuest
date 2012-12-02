package ca.viaware.rpg.game;

import org.lwjgl.opengl.*;

import ca.viaware.rpg.map.Loader;
import ca.viaware.rpg.map.Map;
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
			for(int i=0;i<Globals.enemies.size();i++){
				Globals.enemies.get(i).draw();
			}
			Globals.h.update();

			break;
		}

		Globals.cursor.draw();
	}
}
