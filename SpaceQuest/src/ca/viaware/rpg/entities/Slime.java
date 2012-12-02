package ca.viaware.rpg.entities;

import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

import ca.viaware.rpg.entity.MeleeEnemy;
import ca.viaware.rpg.utilities.TextureHandler;
import ca.viaware.rpg.utilities.TexturedQuad;

public class Slime extends MeleeEnemy {

	private Texture[] textures = new Texture[17];
	private int index = 1;
	private int slower = 0;

	public Slime(double x, double y, double width, double height, int spawnx, int spawny) {
		super(x, y, width, height, 100, 10, 5, spawnx, spawny, 1, 10, 50, 50,400);
		// TODO Auto-generated constructor stub

		setT(new TexturedQuad((int) width, (int) height, 0, 0, 0, "res/sprites/enemies/slimemoving/1.png"));

		String s = "enemies/slimemoving/";
		TextureHandler t = new TextureHandler();
		for (int count = 1; count < 18; count++) {
			textures[count - 1] = t.loadSprite(s + count);
		}
	}

	public void draw() {
		if (slower == 5) {
			slower = 0;
			getT().changetexture(textures[index]);
			index++;
		} else {
			slower++;
		}

		getT().setlocation(getmx(), getmy());

		getT().update();
		if (!(index == 17)) {// so the index doesn't go ver nbr of sprites

		} else {
			index = 0;
		}

	}

}
