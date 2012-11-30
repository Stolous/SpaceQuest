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
			//Intro screen - not working yet
			
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
			
			Globals.gameState = Globals.gState.GAME;
			break;
		case MAIN_MENU:
			new Menu();
			break;
		case GAME:
			Globals.gameMap.renderMap();
			Globals.playerEntity.draw();
			Globals.s.draw();
			Globals.h.update();
		
			
			break;
		}
	}
}
