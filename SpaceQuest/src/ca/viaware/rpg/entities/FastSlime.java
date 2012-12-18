package ca.viaware.rpg.entities;

import org.newdawn.slick.opengl.Texture;

import ca.viaware.rpg.entity.MeleeEnemy;
import ca.viaware.rpg.utilities.TextureHandler;
import ca.viaware.rpg.utilities.TexturedQuad;

public class FastSlime extends MeleeEnemy {

	private Texture[] textures = new Texture[17];
	private int index = 1;
	private int slower = 0;

	public FastSlime(int spawnx, int spawny) {
		super(50, 50, 30, 5, 2, spawnx, spawny, 1, 10, 150, 40, 500);

		setT(new TexturedQuad((int) width, (int) height, 0, 0, 0, "res/sprites/enemies/fastslimemoving/1.png"));

		String s = "enemies/fastslimemoving/";
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
		if (!(index == 17)) {// so the index doesn't go over nbr of sprites

		} else {
			index = 0;
		}

	}

}
