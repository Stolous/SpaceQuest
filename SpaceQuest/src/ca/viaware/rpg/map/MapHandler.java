package ca.viaware.rpg.map;

import ca.viaware.rpg.game.Globals;
import static org.lwjgl.opengl.GL11.*;

public class MapHandler {

	public static Map handleMapLoad(String name) {
		glEnable(GL_TEXTURE_2D);
		Globals.otherTextures.get(1).bind();
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

		Globals.teleMarkers.clear();
		Globals.enemies.clear();
		Loader ld = new Loader();
		Map m = ld.loadMap(name);

		return m;
	}
}
